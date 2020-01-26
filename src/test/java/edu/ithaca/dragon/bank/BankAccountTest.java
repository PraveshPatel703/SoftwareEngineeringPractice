package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
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