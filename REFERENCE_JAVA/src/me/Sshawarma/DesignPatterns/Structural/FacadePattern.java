package me.Sshawarma.DesignPatterns.Structural;

//Self explanatory, it performs complex actions between many classes in simplified methods
//In this example, we perform a bank extraction

/*

Behind the Scenes:
1. Check the account is valid
2. Check if the security code is valid
3. Check if funds are available.
4. Give money

 */

class BankWelcomeMessage {

    public BankWelcomeMessage() {
        System.out.println("Welcome to the bank!");
        System.out.println("Please wait while we verify your credentials...");
    }

}

class AccountNumberCheck {

    private int accountNumber;

    //More realistic is array/list. But this is just a demo
    public AccountNumberCheck () {
        accountNumber = 12345678;
    }

    public boolean accountValid(int acctNum) {
        if(accountNumber == acctNum){
            return true;
        }
        else{
            return false;
        }
    }

    public int getAccountNumber() {
        return accountNumber;
    }

}

class SecurityCodeCheck {

    private int code;

    //More realistic is array/list. But this is just a demo
    public SecurityCodeCheck () {
        this.code = 1234;
    }

    public boolean codeValid(int code) {
        if(this.code == code){
            return true;
        }
        else{
            return false;
        }
    }

    public int getCode() {
        return code;
    }

}

class FundsCheck {

    private int funds;

    //for now these are hardcoded, otherwise it will be a database or DI to handle it
    public FundsCheck() {
        this.funds = 1000;
    }

    public int getFunds() {
        return funds;
    }

    public void decreaseFunds(int removed) {
        this.funds -= removed;
    }

    public void increaseFunds(int added) {
        this.funds += added;
    }

    public boolean withdrawMoney(int withdrawAmount) {
        if(funds-withdrawAmount < 0) {
            System.out.println("You don't have enough balance in your account.");
            System.out.println("Balance: " + funds);
            return false;
        }
        else{
            decreaseFunds(withdrawAmount);
            System.out.println("Withdraw complete!");
            System.out.println("Balance: " + funds);
            return true;
        }
    }

    public void makeDeposit(int cash) {
        increaseFunds(cash);
        System.out.println("Cash added to account!");
        System.out.println("Balance: " + funds);
    }

}

//Now the facade will tie in all these (rather contrived) classes
class BankAccountFacade {

    //Used as input from customer
    private int accountNumber, securityCode;

    AccountNumberCheck accountNumberCheck;
    SecurityCodeCheck securityCodeCheck;
    FundsCheck fundsCheck;

    BankWelcomeMessage bankWelcomeMessage;

    //Initialize all necessary things through the constructor
    public BankAccountFacade(int accountNumber, int securityCode) {

        this.accountNumber = accountNumber;
        this.securityCode = securityCode;

        //Init Objects
        bankWelcomeMessage = new BankWelcomeMessage();
        accountNumberCheck = new AccountNumberCheck();
        securityCodeCheck = new SecurityCodeCheck();
        fundsCheck = new FundsCheck();

    }

    //Now make a way to get values
    public int getAccountNumber() {
        return accountNumber;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public int getFunds() {
        return fundsCheck.getFunds();
    }

    //Now use all those classes within the facade!
    public void withdrawCash(int amount) {
        if(accountNumberCheck.accountValid(getAccountNumber()) && securityCodeCheck.codeValid(securityCode) && fundsCheck.withdrawMoney(amount)){
            System.out.println("Transaction Complete\n");
        }
        else{
            System.out.println("Transaction Failed\n");
        }
    }

    public void depositCash(int amount) {
        if(accountNumberCheck.accountValid(getAccountNumber()) && securityCodeCheck.codeValid(securityCode)){
            fundsCheck.makeDeposit(amount);
            System.out.println("Transaction Complete\n");
        }
        else{
            System.out.println("Transaction Failed\n");
        }
    }


}


public class FacadePattern {

    public FacadePattern() {

        BankAccountFacade accessingBank = new BankAccountFacade(12345678, 1234);

        //Now just perform actions through this one facade
        //No need to interact with the welcome, account, security, and funds

        accessingBank.withdrawCash(50);

        accessingBank.withdrawCash(900);

        accessingBank.withdrawCash(100);

        accessingBank.depositCash(200);

    }

}
