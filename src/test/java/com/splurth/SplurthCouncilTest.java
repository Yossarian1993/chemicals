package com.splurth;

import com.splurth.controller.SplurthCouncil;
import com.splurth.service.ChemicalDepartment;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SplurthCouncilTest
{
    private static final boolean VALID = true;
    private static final boolean INVALID = false;
    private static final String INVALID_CHEMICAL_NAME = "aRkSt";

    private SplurthCouncil splurthCouncil;

    @BeforeMethod
    public void setUp() throws Exception
    {
        splurthCouncil = new SplurthCouncil(new ChemicalDepartment());
    }

    @DataProvider(name = "chemicalValidProvider")
    public Object[][] chemicalValidProvider()
    {
        return new Object[][]{
                {
                        "Valid naming case where both symbol letters are in the middle",
                        "Oxigen",
                        "Xe",
                        VALID
                },
                {
                        "Valid naming case where the symbol is the first two letters of the name",
                        "Argon",
                        "Ar",
                        VALID
                },
                {
                        "Valid naming case where the symbol consists of two identical letters",
                        "Spenglerium",
                        "Ee",
                        VALID
                },
                {
                        "Invalid naming case where the letters of the symbol are in wrong order",
                        "Stantzon",
                        "Zt",
                        INVALID
                },
                {
                        "Invalid naming case where the name is too short",
                        "A",
                        "Ar",
                        INVALID
                },
                {
                        "Invalid naming case where the name does not start with capital letter",
                        "rubium",
                        "Ub",
                        INVALID
                },
                {
                        "Invalid naming case where the name is null",
                        null,
                        "Ar",
                        INVALID
                },
                {
                        "Invalid naming case where the symbol is too short",
                        "Argon",
                        "A",
                        INVALID
                },
                {
                        "Invalid naming case where the symbol does not start with capital letter",
                        "Rubium",
                        "ui",
                        INVALID
                },
                {
                        "Invalid naming case where the symbol is null",
                        "Hello",
                        null,
                        INVALID
                },
        };
    }

    @Test(dataProvider = "chemicalValidProvider")
    public void testIsValid(String testCaseName, String name, String symbol, boolean expectedValid)
    {
        boolean actualValid = splurthCouncil.decideIfNamingIsValid(name, symbol);

        Assert.assertEquals(actualValid, expectedValid, testCaseName);
    }

    @DataProvider(name = "chemicalSymbolProvider")
    public Object[][] chemicalSymbolProvider()
    {
        return new Object[][]{
                {
                        "Gozerium",
                        "Ei"
                },
                {
                        "Slimyrine",
                        "Ie"
                },
        };
    }

    @Test(dataProvider = "chemicalSymbolProvider")
    public void testFirstValidSymbolAlphabetically(String name, String expectedSymbol)
    {
        String actualSymbol = splurthCouncil.getFirstSymbolAlphabetically(name);

        Assert.assertEquals(actualSymbol, expectedSymbol);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFirstValidSymbolAlphabeticallyShouldThrowException()
    {
        splurthCouncil.getFirstSymbolAlphabetically(INVALID_CHEMICAL_NAME);
    }

    @DataProvider(name = "numberOfSymbolsProvider")
    public Object[][] numberOfSymbolsProvider()
    {
        return new Object[][]{
                {
                    "Zuulon",
                    11
                },
                {
                    "Abcdef",
                    15
                },
                {
                    "Uuuuu",
                    1
                },
        };
    }

    @Test(dataProvider = "numberOfSymbolsProvider")
    public void testNumberOfDistinctSymbols(String name, int expectedNumberOfSymbols)
    {
        int actualNumberOfSymbols = splurthCouncil.getNumberOfDistinctSymbols(name);

        Assert.assertEquals(actualNumberOfSymbols, expectedNumberOfSymbols);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNumberOfDistinctSymbolsShouldThrowException()
    {
        splurthCouncil.getNumberOfDistinctSymbols(INVALID_CHEMICAL_NAME);
    }
}