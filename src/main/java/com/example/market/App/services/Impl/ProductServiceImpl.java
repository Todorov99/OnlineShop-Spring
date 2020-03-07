package com.example.market.App.services.Impl;

import com.example.market.App.services.PersonService;
import com.example.market.App.services.ProductService;
import com.example.market.App.domain.entities.People.Orders;
import com.example.market.App.domain.entities.People.Person;
import com.example.market.App.domain.entities.People.PersonTypes;
import com.example.market.App.dtos.productDtos.*;
import com.example.market.App.repositories.*;
import com.example.market.App.domain.entities.Products.Brands;
import com.example.market.App.domain.entities.Products.Categories;
import com.example.market.App.domain.entities.Products.Products;
import com.example.market.App.util.impl.FileUtilImpl;
import com.example.market.App.util.impl.MailUtilImpl;
import com.example.market.App.util.impl.ValidatorUtilImpl;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final static String DELETED_PRODUCTS_JSON_PATH = "D:\\Spring\\market\\src\\main\\resources\\Json_Files\\deletedProducts.json";
    private final static String ORDERS_JSON_PATH = "D:\\Spring\\market\\src\\main\\resources\\Json_Files\\orders.json";

    private final PersonService personService;
    private final CategoriesRepository categoriesRepository;
    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;
    private final PersonRepository personRepository;
    private final OrdersRepository ordersRepository;
    private final ValidatorUtilImpl validatorUtil;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final FileUtilImpl fileUtil;
    private final MailUtilImpl mailUtil;

    private List<BuyProductDto> shoppingBag;

    @Autowired
    public ProductServiceImpl(PersonService personService, CategoriesRepository categoriesRepository, ProductsRepository productsRepository, BrandsRepository brandsRepository, PersonRepository personRepository, OrdersRepository ordersRepository, ValidatorUtilImpl validatorUtil, ModelMapper modelMapper, Gson gson, FileUtilImpl fileUtil, MailUtilImpl mailUtil) {
        this.personService = personService;
        this.categoriesRepository = categoriesRepository;
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
        this.personRepository = personRepository;
        this.ordersRepository = ordersRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.mailUtil = mailUtil;
        this.shoppingBag = new ArrayList<>();
    }

    @Override
    @Transactional
    public String addProduct(AddProductDto addProductDto) {

        StringBuilder sb = new StringBuilder();

        if(this.validatorUtil.isValidDto(addProductDto) instanceof String){
            return this.validatorUtil.isValidDto(addProductDto).toString();
        }

        if(addProductDto.getPromotionalPrice().compareTo(addProductDto.getMinimalPrice()) < 0){
            return sb.append("Sorry but with this discount your product price is under the minimal for the product price.\n Try again!").toString();
        }

        Products dbProduct = (Products) checkForAvailableProduct(addProductDto);

        if(dbProduct != null){
            return sb.append(String.format("Sorry but you have in stock from %s %s", addProductDto.getBrand().getName(), addProductDto.getModel())).toString();
        }

        Products product = this.modelMapper.map(addProductDto, Products.class);

        Categories dbCategory = this.categoriesRepository.findByName(addProductDto.getCategory().getName()).orElse(null);
        Brands dbBrand = this.brandsRepository.findByName(addProductDto.getBrand().getName()).orElse(null);

        if(dbCategory == null && dbBrand == null) {

            this.productsRepository.saveAndFlush(product);
            return sb.append(String.format("You successfully added %s %s", product.getBrand().getName(), product.getModel()))
                    .toString();
        }

        if (dbCategory == null){

            product.setBrand(dbBrand);
            this.productsRepository.saveAndFlush(product);
            return sb.append(String.format("You successfully added %s %s", product.getBrand().getName(), product.getModel()))
                    .toString();
        }

        if(dbBrand == null){
            product.setCategory(dbCategory);
            this.productsRepository.saveAndFlush(product);
            return sb.append(String.format("You successfully added %s %s", product.getBrand().getName(), product.getModel()))
                    .toString();
        }

        product.setCategory(dbCategory);
        product.setBrand(dbBrand);
        this.productsRepository.saveAndFlush(product);

        sb.append(String.format("You sucessfully added %s %s", product.getBrand().getName(), product.getModel()));

        return sb.toString();
    }

    @Override
    @Transactional
    public String deleteProduct(DeleteProductDto deleteProductDto) throws IOException {

        StringBuilder sb = new StringBuilder();

        if(checkForAvailableProduct(deleteProductDto) == null){
            return sb.append(String.format("Product %s %s can not be found", deleteProductDto.getBrand().getName(),
                    deleteProductDto.getModel())).toString();
        }

        Products product = (Products) checkForAvailableProduct(deleteProductDto);

        String json = this.gson.toJson(deleteProductDto);

        this.fileUtil.saveJsonStringInFile(json, DELETED_PRODUCTS_JSON_PATH);

        this.productsRepository.deleteProductById(product.getId());
        sb.append(String.format("You successfully deleted %s %s", product.getBrand().getName(), product.getModel()));

        return sb.toString();
    }

    @Override
    @Transactional
    public List<Object> seeAllProduct() {

        List<Object> allAvailableProducts = new ArrayList<>();

        Person loggedPerson = this.personRepository.findByUsername(this.personService.getLoggedPerson()).orElse(null);

        if(loggedPerson == null){
            return null;
        }

        if(loggedPerson.getTypes().equals(PersonTypes.CLIENT)){
            this.productsRepository.allProducts().forEach(product -> {
                ExportAllProductsToClientDto p = this.modelMapper
                        .map(product, ExportAllProductsToClientDto.class);
                allAvailableProducts.add(p);
            });

            return allAvailableProducts;
        }

        this.productsRepository.allProducts().forEach(product -> {
           ExportAllAvailableProductsToEmployeesDto p = this.modelMapper
                   .map(product, ExportAllAvailableProductsToEmployeesDto.class);
           allAvailableProducts.add(p);
        });

        return allAvailableProducts;
    }

    @Override
    @Transactional
    public String changeProductPrice(ChangeProductPricesDto changeProductPricesDto) {

        StringBuilder sb = new StringBuilder();


        if(checkForValidAndAvailableProduct(changeProductPricesDto) instanceof String){
            return checkForValidAndAvailableProduct(changeProductPricesDto).toString();
        }

        Products product = (Products) checkForValidAndAvailableProduct(changeProductPricesDto);

        if(changeProductPricesDto.getPrice().compareTo(product.getMinimalPrice()) < 0){
            return sb.append("Product price can not be under the minimal price.").toString();
        }

        BigDecimal promotionalPrice = changeProductPricesDto.getPrice().subtract(changeProductPricesDto.getPrice().multiply(BigDecimal.valueOf(product.getDiscount() / 100)));

        if (promotionalPrice.compareTo(product.getMinimalPrice()) < 0){
            this.productsRepository.updateProductDiscountPriceAndMinimalPrice(0, new BigDecimal("0"),
                    changeProductPricesDto.getPrice(), product.getId());
            return sb.append("Product promotional price can not be under the minimal price.").toString();
        }

        this.productsRepository.updateProductsPrice(changeProductPricesDto.getPrice(), promotionalPrice, product.getId());
        sb.append(String.format("You successfully update the price of %s %s", product.getBrand().getName(), product.getModel()));

        return sb.toString();
    }


    @Override
    @Transactional
    public String changeProductMinimalPrice(ChangeProductPricesDto dto) {

        StringBuilder sb = new StringBuilder();

        if(checkForValidAndAvailableProduct(dto) instanceof String){
            return checkForValidAndAvailableProduct(dto).toString();
        }

        Products product = (Products) checkForValidAndAvailableProduct(dto);

        if (product.getPrice().compareTo(dto.getPrice()) < 0){
             return sb.append("Product price now is under the minimal price. Please change product price!").toString();
        }

        if (product.getPromotionalPrice().compareTo(dto.getPrice()) < 0){
            this.productsRepository.updateProductDiscount(0, product.getId());
        }

        this.productsRepository.updateProductMinimalPrice(dto.getPrice(), product.getId());
        sb.append(String.format("You successfully update the minimal price of %s %s", product.getBrand().getName(), product.getModel()));

        return sb.toString();
    }

    @Override
    @Transactional
    public String changeProductDiscount(ChangeProductDiscountDto changeProductDiscountDto) {
        StringBuilder sb = new StringBuilder();

        if(checkForValidAndAvailableProduct(changeProductDiscountDto) instanceof String){
            return checkForValidAndAvailableProduct(changeProductDiscountDto).toString();
        }

        Products product = (Products) checkForValidAndAvailableProduct(changeProductDiscountDto);

        BigDecimal promotionalPrice = product.getPrice().subtract(product.getPrice().multiply(BigDecimal.valueOf(changeProductDiscountDto.getDiscount() / 100)));

        if(promotionalPrice.compareTo(product.getMinimalPrice()) < 0){
            return sb.append(String.format("With discount of %s percentage product promotional price is under the minimal price!\n" +
                    "Please try again with lower discount.", changeProductDiscountDto.getDiscount())).toString();
        }

        this.productsRepository.updateProductDiscount(changeProductDiscountDto.getDiscount(), product.getId());
        sb.append("You successfully set product discount.");

        return sb.toString();
    }

    @Override
    @Transactional
    public String changeProductQuantity(ChangeProductQuantityDto changeProductQuantityDto) {

        StringBuilder sb = new StringBuilder();

        if(checkForValidAndAvailableProduct(changeProductQuantityDto) instanceof String){
            return checkForValidAndAvailableProduct(changeProductQuantityDto).toString();
        }

        Products product = (Products) checkForValidAndAvailableProduct(changeProductQuantityDto);

        this.productsRepository.updateProductQuantity(changeProductQuantityDto.getQuantity(), product.getId());

        sb.append(String.format("You successfully update the quantity of %s %s", product.getBrand().getName(), product.getModel()));

        return sb.toString();
    }

    @Override
    @Transactional
    public String buyProduct(BuyProductDto buyProductDto) {

        StringBuilder sb = new StringBuilder();

        if(this.personService.getLoggedPerson().equals("")){
            return sb.append("Sorry but there is no logged person.").toString();
        }

        if(checkForValidAndAvailableProduct(buyProductDto) instanceof String){
            return checkForValidAndAvailableProduct(buyProductDto).toString();
        }

        Products product = (Products) checkForValidAndAvailableProduct(buyProductDto);

        if(product.getQuantity() == 0){
            return sb.append(String.format("Sorry but %s %s is out of stock", buyProductDto.getBrandDto().getName(),
                    buyProductDto.getModel())).toString();
        }


        if(product.getQuantity() < buyProductDto.getQuantity()) {
            return sb.append(String.format("Sorry but we have only %s peace of %s %s", product.getQuantity(),
                    product.getBrand().getName(), product.getModel())).toString();
        }


        for(BuyProductDto p : this.shoppingBag){

            if(p.getCategory().getName().equals(buyProductDto.getCategory().getName()) &&
                    p.getBrandDto().getName().equals(buyProductDto.getBrandDto().getName()) && p.getModel().equals(buyProductDto.getModel())){
                p.setQuantity(p.getQuantity() + buyProductDto.getQuantity());
                return sb.append(String.format("You successfully added %s %s to your shopping bag", buyProductDto.getBrandDto().getName(), buyProductDto.getModel())).toString();
            }
        }


        this.shoppingBag.add(buyProductDto);
        sb.append(String.format("You successfully added %s %s to your shopping bag", buyProductDto.getBrandDto().getName(), buyProductDto.getModel()));
        return sb.toString();
    }


    @Override
    public List<ExportAllProductsToClientDto> seeShoppingBag() {

        List<ExportAllProductsToClientDto> shoppingBag = new ArrayList<>();

        for (BuyProductDto p : this.shoppingBag){
            Products product = this.productsRepository.findByCategoryNameAndBrandNameAndModel(p.getCategory().getName(),
                    p.getBrandDto().getName(), p.getModel()).orElse(null);

            if(product == null){
                return null;
            }

            ExportAllProductsToClientDto productsInShoppingBag = this.modelMapper.map(p, ExportAllProductsToClientDto.class);

            productsInShoppingBag.setPrice(product.getPrice());
            productsInShoppingBag.setDiscount(product.getDiscount());
            productsInShoppingBag.setPromotionalPrice(product.getPromotionalPrice());

            shoppingBag.add(productsInShoppingBag);
        }

        if (shoppingBag.size() == 0){
            System.out.println("You dont have products in your shopping bag.");
        }

        return shoppingBag;
    }

    @Override
    @Transactional
    public String finalizeOrder() throws IOException {

        StringBuilder sb = new StringBuilder();

        BigDecimal sum = new BigDecimal("0");
        BigDecimal savedAmountOfMoney = new BigDecimal("0");

        LocalDate date = LocalDate.now();

        Person person = this.personRepository.findByUsername(this.personService.getLoggedPerson()).orElse(null);

        if (person == null){
            return sb.append("There is no logged person.").toString();
        }

        if(person.getTown() == null){
            return sb.append("Please enter your living town before finalizing your order.").toString();
        }

        for(BuyProductDto p : this.shoppingBag){
            Products products = this.productsRepository.findByCategoryNameAndBrandNameAndModel(p.getCategory().getName(),
                    p.getBrandDto().getName(), p.getModel()).orElse(null);

            if(products == null){
                return sb.append("Product can not be found.").toString();
            }

            if(products.getDiscount() != 0){
                sum = sum.add(products.getPromotionalPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
                savedAmountOfMoney = savedAmountOfMoney.add(products.getPrice().subtract(products.getPromotionalPrice()));
            }else {
                sum = sum.add(products.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
            }

            this.productsRepository.buyProduct(p.getQuantity(), products.getId());
        }

        Orders order = new Orders();
        order.setDate(date);
        order.setPerson(person);
        order.setSum(sum);

        this.ordersRepository.saveAndFlush(order);

        String json = this.gson.toJson(seeShoppingBag());

        this.fileUtil.saveJsonStringInFile(json, ORDERS_JSON_PATH);

        this.shoppingBag = new ArrayList<>();

        if(savedAmountOfMoney.compareTo(BigDecimal.valueOf(0)) == 0){
           return sb.append(String.format("Thank you for shopping from us %s %s", person.getNames().getFirstName(), person.getNames().getLastName())).toString();
        }

        sb.append(String.format("Thank you for shopping from us %s %s. You saved %s lv thanks to our great promotions.",
                person.getNames().getFirstName(), person.getNames().getLastName(), savedAmountOfMoney));

        StringBuilder personAddress = new StringBuilder();

        this.personRepository.personAddress(person.getId())
                .forEach(personAddress::append);

        this.mailUtil.sentAutomaticEmailForConfirmedOrder(person.getEmail(), personAddress.toString());
        return sb.toString();
    }

    @Override
    @Transactional
    public List<ProductsOutOfStockDto> outOfStockProducts() {

        List<ProductsOutOfStockDto> outOfStockProducts = new ArrayList<>();

        List<Products> products = this.productsRepository.findAllOutOfStocksProducts();

      products.forEach(product -> {
                  ProductsOutOfStockDto outOfStockProduct = this.modelMapper.map(product, ProductsOutOfStockDto.class);
                  outOfStockProducts.add(outOfStockProduct);
              });

        if(outOfStockProducts.size() == 0){
            System.out.println("There is no products out of stock.");
            return new ArrayList<>();
        }

        return outOfStockProducts;
    }

    private <E> Object checkForAvailableProduct(E dto) {

        Products product = this.modelMapper.map(dto, Products.class);

        return this.productsRepository.findByCategoryNameAndBrandNameAndModel(product.getCategory().getName(),
                product.getBrand().getName(), product.getModel()).orElse(null);
    }

    private <E> Object checkForValidAndAvailableProduct(E object){

        StringBuilder sb = new StringBuilder();

        if (this.validatorUtil.isValidDto(object) instanceof String) {
            return sb.append(this.validatorUtil.isValidDto(object)).toString();
        }


        if(checkForAvailableProduct(object) == null){
            return sb.append("Product can not be found").toString();

        }

        return checkForAvailableProduct(object);
    }
}
