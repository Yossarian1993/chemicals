package com.splurth;

import com.splurth.controller.SplurthCouncil;
import com.splurth.service.ChemicalDepartment;

public class App
{
    public static void main(String[] args)
    {
        SplurthCouncil splurthCouncil = new SplurthCouncil(new ChemicalDepartment());

        System.out.println(splurthCouncil.decideIfNamingIsValid("Spenglerium", "Ee"));

        System.out.println(splurthCouncil.getFirstSymbolAlphabetically("Slimyrine"));

        System.out.println(splurthCouncil.getNumberOfDistinctSymbols("Zuulon"));
    }
}
