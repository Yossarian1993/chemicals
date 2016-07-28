package com.splurth.controller;

import com.splurth.model.ChemicalElement;
import com.splurth.service.ChemicalDepartment;

public class SplurthCouncil
{
    private final ChemicalDepartment chemicalDepartment;

    public SplurthCouncil(ChemicalDepartment chemicalDepartment)
    {
        this.chemicalDepartment = chemicalDepartment;
    }

    public boolean decideIfNamingIsValid(String name, String symbol)
    {
        ChemicalElement chemicalElement = new ChemicalElement(name, symbol);

        return chemicalDepartment.isValid(chemicalElement);
    }

    public String getFirstSymbolAlphabetically(String name)
    {
        ChemicalElement chemicalElement = new ChemicalElement(name, null);

        return chemicalDepartment.firstValidSymbolInAlphabeticalOrder(chemicalElement);
    }

    public int getNumberOfDistinctSymbols(String name)
    {
        ChemicalElement chemicalElement = new ChemicalElement(name, null);

        return chemicalDepartment.getNumberOfDistinctPossibleSymbols(chemicalElement);
    }
}
