package com.example.market.App.controllers;

import com.example.market.App.consoleInterface.ConsoleInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller
public class AppMainController implements CommandLineRunner {

    // Options //
    private final static String LOG_IN = "log in";
    private final static String LOG_IN_AS_EMPLOYEE = "log in as employee";
    private final static String EXIT = "exit";
    private final static String SIGN_IN = "sign in";
    private final static String LOG_OUT = "log out";
    private final static String SEE_EMPLOYEE = "see employees";
    private final static String HIRE_EMPLOYEE = "hire employee";
    private final static String RETIRE_EMPLOYEE = "retire employee";
    private final static String ADD_PRODUCT = "add product";
    private final static String DELETE_PRODUCT = "delete product";
    private final static String SEE_ALL_PRODUCTS = "see all products";
    private final static String SEE_SOLD_PRODUCT = "see last order";
    private final static String OUT_OF_STOCK_PRODUCTS = "see out of stock products";
    private final static String CHANGE_PRODUCT_PRICE = "change price";
    private final static String CHANGE_PRODUCT_MINIMAL_PRICE = "change minimal price";
    private final static String CHANGE_PRODUCT_DISCOUNT = "change discount";
    private final static String CHANGE_PRODUCT_QUANTITY = "change quantity";
    private final static String SEE_TURNOVER = "see turnover";
    private final static String SEE_TURNOVER_FOR_CURRENT_PERIOD = "see turnover for current period";
    private final static String BUY_PRODUCT = "buy product";
    private final static String SEE_SHOPPING_BAG = "see shopping bag";
    private final static String FINALIZE_ORDER = "finalize order";
    private final static String CHANGE_ACCOUNT_PASSWORD = "change password";
    private final static String SEND_MAIL = "send email";
    private final static String SET_TOWN = "set address";

    //options

    private final ConsoleInterface consoleInterface;
    private final Scanner scanner;
    private final ProductController productController;
    private final PersonController personController;
    private final OrderController orderController;

    @Autowired
    public AppMainController(ConsoleInterface consoleInterface, Scanner scanner, ProductController productController, PersonController personController, OrderController orderController) {
        this.consoleInterface = consoleInterface;
        this.scanner = scanner;
        this.productController = productController;
        this.personController = personController;
        this.orderController = orderController;
    }

    @Override
    public void run(String... args) throws Exception {

        this.consoleInterface.start();
        this.consoleInterface.loginOrSignIn();


        while (true){

            String command = this.scanner.nextLine().toLowerCase();

            if(command.equals(EXIT)){
                break;
            }

            switch (command){

                case SIGN_IN:
                    this.personController.signIn();
                    break;
                case LOG_IN:
                    this.personController.login();
                    break;
                case LOG_IN_AS_EMPLOYEE:
                    this.personController.loginAsEmployee();
                    break;
                case LOG_OUT:
                    this.personController.logout();
                    this.consoleInterface.loginOrSignIn();
                    break;
                case HIRE_EMPLOYEE:
                    this.personController.hireEmployee();
                    this.personController.personOptions();
                    break;
                case RETIRE_EMPLOYEE:
                    this.personController.retireEmployee();
                    this.personController.personOptions();
                    break;
                case SEE_EMPLOYEE:
                    this.personController.seeEmployee();
                    this.personController.personOptions();
                    break;
                case ADD_PRODUCT:
                    this.productController.addProduct();
                    this.personController.personOptions();
                    break;
                case DELETE_PRODUCT:
                    this.productController.deleteProduct();
                    this.personController.personOptions();
                    break;
                case SEE_ALL_PRODUCTS:
                    this.productController.seeAllProducts();
                    this.personController.personOptions();
                    break;
                case CHANGE_PRODUCT_PRICE:
                    this.productController.changeProductPrice();
                    this.personController.personOptions();
                    break;
                case CHANGE_PRODUCT_MINIMAL_PRICE:
                    this.productController.changeProductMinimalPrice();
                    this.personController.personOptions();
                    break;
                case CHANGE_PRODUCT_DISCOUNT:
                    this.productController.changeProductDiscount();
                    this.personController.personOptions();
                    break;
                case CHANGE_PRODUCT_QUANTITY:
                    this.productController.changeProductQuantity();
                    this.personController.personOptions();
                    break;
                case BUY_PRODUCT:
                    this.productController.buyProduct();
                    this.personController.personOptions();
                    break;
                case SEE_SHOPPING_BAG:
                    this.productController.seeShoppingBag();
                    this.personController.personOptions();
                    break;
                case FINALIZE_ORDER:
                    this.productController.finalizeOrder();
                    this.personController.personOptions();
                    break;
                case SEE_TURNOVER:
                    this.orderController.seeTurnover();
                    this.personController.personOptions();
                    break;
                case SEE_TURNOVER_FOR_CURRENT_PERIOD:
                    this.orderController.seeTurnoverForCurrentPeriod();
                    this.personController.personOptions();
                    break;
                case SEE_SOLD_PRODUCT:
                    this.orderController.seeSoldProducts();
                    this.personController.personOptions();
                    break;
                case CHANGE_ACCOUNT_PASSWORD:
                    this.personController.changeAccountPassword();
                    this.personController.personOptions();
                    break;
                case OUT_OF_STOCK_PRODUCTS:
                    this.productController.outOfStockProducts();
                    this.personController.personOptions();
                    break;
                case SEND_MAIL:
                    this.personController.sendMails();
                    this.personController.personOptions();
                    break;
                case SET_TOWN:
                    this.personController.setTown();
                    this.personController.personOptions();
                   break;
            }
        }
    }

}
