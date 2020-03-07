package com.example.market.App.consoleInterface;


public class ConsoleInterface {


    public void start(){

        StringBuilder sb = new StringBuilder();
        sb.append("******************************************************************************************************************************************************************************************");
        sb.append(System.lineSeparator());
        sb.append("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tWelcome to Mr & Mrs T Store!");
        sb.append(System.lineSeparator());
        sb.append("\t\t\t\t\t\t\t\t\t\t\tEnjoy your tour through the magnificent and quality products of our store!");
        sb.append(System.lineSeparator());
        sb.append("******************************************************************************************************************************************************************************************");

        System.out.println(sb.toString());
    }

    public void loginOrSignIn(){

        StringBuilder sb = new StringBuilder();

        sb.append("\t\t\t\t | Log in | \t\t\t\t | Sign in | \t\t\t\t | Log in as employee | \t\t\t\t | Log out | \t\t\t\t | Exit |");
        System.out.println(sb.toString());

    }

    public String adminOptions(){

        StringBuilder sb = new StringBuilder();

        sb.append("\n1. Hire employee.\n");
        sb.append("2. See employees.\n");
        sb.append("3. Retire employee.\n");
        sb.append("4. Add product. \n");
        sb.append("5. Delete product.\n");
        sb.append("6. Change price.\n");
        sb.append("7. Change minimal price.\n");
        sb.append("8. Change discount.\n");
        sb.append("8. Change quantity.\n");
        sb.append("9. See all products.\n");
        sb.append("10. See out of stock products .\n");
        sb.append("11. See turnover.\n");
        sb.append("11. See turnover for current period.\n");
        sb.append("12. See last order.\n");
        sb.append("13. Change password.\n");
        sb.append("14. Send email.\n");
        sb.append("15. Log out.\n");

        return sb.toString();
    }

    public void clientOptions(){

        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        sb.append("1. See all products. \n");
        sb.append("2. Buy product.\n");
        sb.append("3. See shopping bag.\n");
        sb.append("4. Finalize order.\n");
        sb.append("5. Change password.\n");
        sb.append("6. Set address.\n");
        sb.append("7. Log out.\n");

        System.out.println(sb.toString());
    }
}
