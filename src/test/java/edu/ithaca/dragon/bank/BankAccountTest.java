package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {

        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        BankAccount bankOfAmerica = new BankAccount("a@b.com", 2000.01);
        assertEquals(2000.01,bankOfAmerica.getBalance());

        BankAccount chaseBank = new BankAccount("a@b.com", 0);
        assertEquals(0, chaseBank.getBalance());

        /*//should throw invalid input exception i think for these next two
        BankAccount firstTrust = new BankAccount("a@b.com", -23.12);
        firstTrust.getBalance();

        BankAccount keyBank = new BankAccount("a@b.com", -1200);
        keyBank.getBalance();*/

    }

    @Test
    void withdrawTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankOfAmerica = new BankAccount("a@b.com", 2000.01);

        //positive
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance(), 0.0001);
        bankAccount.withdraw(50);
        assertEquals(50, bankAccount.getBalance(), 0.0001);
        bankAccount.withdraw(25);
        assertEquals(25, bankAccount.getBalance(), 0.0001);

        //zero
        bankAccount.withdraw(0);
        assertEquals(25,bankAccount.getBalance(), 0.0001);
        bankOfAmerica.withdraw(0);
        assertEquals(2000.01,bankOfAmerica.getBalance(), 0.0001);

        //negative
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(-1345.23));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(-345.24));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(-24));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(-0.22));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(-0.001));

        //2 or more decimals
        bankOfAmerica.withdraw(.01);
        assertEquals(2000, bankOfAmerica.getBalance(), 0.0001);
        bankOfAmerica.withdraw(.50);
        assertEquals(1999.50, bankOfAmerica.getBalance(), 0.0001);
        bankOfAmerica.withdraw(.75);
        assertEquals(1998.75, bankOfAmerica.getBalance(), 0.0001);
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(0.2334));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(0.001301));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.withdraw(0.000000000000001));

        //amount > balance
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.withdraw(30));
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.withdraw(50.99));
        assertThrows(InsufficientFundsException.class, ()-> bankAccount.withdraw(123.34));

        assertThrows(InsufficientFundsException.class, ()-> bankOfAmerica.withdraw(50000));
        assertThrows(InsufficientFundsException.class, ()-> bankOfAmerica.withdraw(2000));
        assertThrows(InsufficientFundsException.class, ()-> bankOfAmerica.withdraw(1000000));

    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //new tests

        //consecutive symbols
        assertFalse(BankAccount.isEmailValid("@@spectrum.ddd"));
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertFalse(BankAccount.isEmailValid("a##sd@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab!!2020@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab2020_@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab!#2020@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab.-2020@mail.com"));
        assertTrue(BankAccount.isEmailValid("ab-def@mail.com"));

        assertFalse(BankAccount.isEmailValid("ab2020@mail..com"));
        assertFalse(BankAccount.isEmailValid("johnC@ma!!l.com"));
        assertFalse(BankAccount.isEmailValid("abc20@12$$m.com"));
        assertFalse(BankAccount.isEmailValid("johnC@mal-.com"));
        assertFalse(BankAccount.isEmailValid("johnC@mal&.com"));
        assertTrue(BankAccount.isEmailValid("abdef@mail-sender.com"));

        //invalid symbols
        assertFalse(BankAccount.isEmailValid("ab!2020@mail.com"));
        assertFalse(BankAccount.isEmailValid("ab2@20@mail.com"));
        assertFalse(BankAccount.isEmailValid("@b20$20@mail.co$"));
        assertFalse(BankAccount.isEmailValid("ab%2020@mail.com%"));
        assertFalse(BankAccount.isEmailValid("#ab202^0@mail.com"));
        assertFalse(BankAccount.isEmailValid("a(b2020@mail.com("));
        assertFalse(BankAccount.isEmailValid("ab2#020@mail.com"));
        assertTrue(BankAccount.isEmailValid("patel.58@gmail-test.com"));


        //only 1 @
        assertFalse(BankAccount.isEmailValid("ab@erwin@mail.com"));
        assertFalse(BankAccount.isEmailValid("2020@mail@test.com"));
        assertTrue(BankAccount.isEmailValid("patel.58@gmail-test.com"));

        //valid domain
        assertFalse(BankAccount.isEmailValid("def@yahoo.c"));
        assertTrue(BankAccount.isEmailValid("jones34.12@y.deff"));
        assertFalse(BankAccount.isEmailValid("ab2020@mail..com"));
        assertTrue(BankAccount.isEmailValid("patel20@ithaca.edu"));
        assertTrue(BankAccount.isEmailValid("Sanddi23@test.org"));
        assertTrue(BankAccount.isEmailValid("Kobe24@Lakers.cc"));
        assertTrue(BankAccount.isEmailValid("BundleUp@coner.gov"));
        assertTrue(BankAccount.isEmailValid("firstproject@comp345.io"));

        //old tests

        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("12.alpha.@delta.b"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("%FA#IL!@.com"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //invalid; not a border case
        assertTrue(BankAccount.isEmailValid("abc.def@yahoo.com"));
        //valid; not a border case
        assertTrue(BankAccount.isEmailValid("abc@gmail.com"));
        //valid; not a border case
        assertTrue(BankAccount.isEmailValid("abc.def@madison.org"));
        //valid; not a border case
        assertTrue(BankAccount.isEmailValid("abcdef@governor.gov"));
        //valid; not a border case
    }


    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

    @Test
    void depositTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankOfAmerica = new BankAccount("a@b.com", 2000.01);

        //positive
        bankAccount.deposit(100);
        assertEquals(300, bankAccount.getBalance(), 0.0001);
        bankAccount.deposit(50);
        assertEquals(350, bankAccount.getBalance(), 0.0001);
        bankAccount.deposit(25);
        assertEquals(375, bankAccount.getBalance(), 0.0001);

        //zero
        bankAccount.deposit(0);
        assertEquals(375,bankAccount.getBalance(), 0.0001);
        bankOfAmerica.deposit(0);
        assertEquals(2000.01,bankOfAmerica.getBalance(), 0.0001);

        //negative
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(-1345.23));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(-345.24));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(-24));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(-0.22));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(-0.001));

        //2 or more decimals
        bankOfAmerica.deposit(24999);
        assertEquals(26999.01, bankOfAmerica.getBalance(), 0.0001);
        bankOfAmerica.deposit(.50);
        assertEquals(26999.51, bankOfAmerica.getBalance(), 0.0001);
        bankOfAmerica.deposit(.75);
        assertEquals(27000.26, bankOfAmerica.getBalance(), 0.0001);
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(0.001));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(0.2334));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(0.001301));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(0.000000000000001));

        //amount > $25,000
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(30000));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(50000.99));
        assertThrows(IllegalArgumentException.class, ()-> bankAccount.deposit(25001.34));

        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(50000));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(25200));
        assertThrows(IllegalArgumentException.class, ()-> bankOfAmerica.deposit(1000000));

    }

}