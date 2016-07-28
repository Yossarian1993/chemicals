package com.splurth.model;

public class ChemicalElement
{
    private final String name;
    private final String symbol;

    public ChemicalElement(String name, String symbol)
    {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName()
    {
        return name;
    }

    public String getSymbol()
    {
        return symbol;
    }

}
