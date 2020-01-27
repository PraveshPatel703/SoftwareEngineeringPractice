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

        //should throw invalid input exception i think for these next two
        BankAccount firstTrust = new BankAccount("a@b.com", -23.12);
        firstTrust.getBalance();

        BankAccount keyBank = new BankAccount("a@b.com", -1200);
        keyBank.getBalance();

    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankOfAmerica = new BankAccount("a@b.com", 2000.01);
        BankAccount chaseBank = new BankAccount("a@b.com", 2349.34);

        //positive
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());

        //zero
        bankOfAmerica.withdraw(2000.01);
        assertEquals(0,bankOfAmerica.getBalance());

        //negative
        chaseBank.withdraw(3000);
        assertEquals(-650.66,chaseBank.getBalance());
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));
        assertFalse(BankAccount.isEmailValid("@@spectrum.ddd"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("12.alpha.@delta.b"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("%FA#IL!@.com"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        //invalid; not a border case
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
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

}