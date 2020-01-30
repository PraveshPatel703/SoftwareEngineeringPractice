package edu.ithaca.dragon.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if balance is smaller than amount
     */
    public void withdraw (double amount) throws InsufficientFundsException {
        if (amount < 0){
            throw new IllegalArgumentException("Please enter a positive amount.");
        }
        if (amount > balance){
            throw new InsufficientFundsException("Not enough in balance.");
        }
        String strAmount = Double.toString(amount);
        int decIndex = strAmount.indexOf('.');
        int count = 0;
        for (int i = decIndex+1; i < strAmount.length(); i++){
            count++;
            if (count > 2){
                throw new IllegalArgumentException("No more than 2 decimal places permitted.");
            }
        }
        balance -= amount;


    }

    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1) {
            return false;
        }
        int count = 0;
        for (int i = 0; i < email.length(); i++){

            if (email.charAt(i) == '@'){
                count ++;
            }
            if (count > 1){
                return false;
            }
        }
        if (email.charAt(0) == '@'){
            return false;
        }

        else {
            if ((email.indexOf('!') != -1) || (email.indexOf('#') != -1) || (email.indexOf('%') != -1) || (email.indexOf('&') != -1) || (email.indexOf('$') != -1)  || (email.indexOf('(') != -1)  || (email.indexOf(')') != -1)){
                return false;
            }
            if (email.indexOf('.') != -1){
                int index = email.indexOf('.');
                if ((email.charAt(index+1) == '.') || (email.charAt(index+1) == '-') || (email.charAt(index+1) == '_') || (email.charAt(index+1) == '@')){
                    return false;
                }
            }
            if (email.indexOf('-') != -1){
                int index = email.indexOf('-');
                if ((email.charAt(index+1) == '.') || (email.charAt(index+1) == '-') || (email.charAt(index+1) == '_') || (email.charAt(index+1) == '@')){
                    return false;
                }
            }
            if (email.indexOf('_') != -1){
                int index = email.indexOf('_');
                if ((email.charAt(index+1) == '.') || (email.charAt(index+1) == '-') || (email.charAt(index+1) == '_') || (email.charAt(index+1) == '@')){
                    return false;
                }
            }
            int length = email.length();
            if ((email.charAt(length - 1) == '.') || (email.charAt(length - 2) == '.')){
                return false;
            }

            int idx = email.indexOf('@');
            String suffix = "";
            for (int i = idx+1; i < email.length(); i++){
                suffix += email.charAt(i);
            }
            count = 0;
            for (int i = 0; i < suffix.length(); i++){

                if (email.charAt(i) == '.'){
                    count ++;
                }
                if (count > 1){
                    return false;
                }
            }
            if ((suffix.indexOf('_') != -1) || (suffix.indexOf('@') != -1)){
                return false;
            }


            return true;
        }
    }
}
