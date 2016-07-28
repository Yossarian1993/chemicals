package com.splurth.service;

import java.util.HashSet;
import java.util.Set;

import com.splurth.model.ChemicalElement;

public class ChemicalDepartment
{
    private static final String NAME_REQUIREMENTS_REGEX = "[A-Z][a-z]+";
    private static final String SYMBOL_REQUIREMENTS_REGEX = "[A-Z][a-z]";

    public boolean isValid(ChemicalElement chemicalElement)
    {
        String name = chemicalElement.getName();
        String symbol = chemicalElement.getSymbol();

        if (isNameInvalid(name) || isSymbolInvalid(symbol))
        {
            return false;
        }

        return name.matches("(?i:.*" + symbol.charAt(0) + ".*" + symbol.charAt(1) + ".*)");
    }

    public String firstValidSymbolInAlphabeticalOrder(ChemicalElement chemicalElement)
    {
        String name = chemicalElement.getName();

        if (isNameInvalid(name))
        {
            throw new IllegalStateException("The name does not meet the requirements");
        }

        int lastValidPositionForTheFirstCharacter = name.length() - 1;

        int firstCharacterPosition = getAlphabeticallyFirstCharacterInNameBetween(name, 0, lastValidPositionForTheFirstCharacter);
        int secondCharacterPosition = getAlphabeticallyFirstCharacterInNameBetween(name, firstCharacterPosition + 1, name.length());

        return getSymbolByPositions(name, firstCharacterPosition, secondCharacterPosition);
    }

    public int getNumberOfDistinctPossibleSymbols(ChemicalElement chemicalElement)
    {
        String name = chemicalElement.getName();

        if (isNameInvalid(name))
        {
            throw new IllegalStateException("The name does not meet the requirements");
        }

        Set<String> symbols = new HashSet<>();

        for (int i = 0; i < name.length() - 1; i++)
        {
            for (int j = i + 1; j < name.length(); j++)
            {
                symbols.add(getSymbolByPositions(name, i, j));
            }
        }

        return symbols.size();
    }

    private boolean isNameInvalid(String name)
    {
        return name == null || !name.matches(NAME_REQUIREMENTS_REGEX);
    }

    private boolean isSymbolInvalid(String symbol)
    {
        return symbol == null || !symbol.matches(SYMBOL_REQUIREMENTS_REGEX);
    }

    private int getAlphabeticallyFirstCharacterInNameBetween(String name, int from, int to)
    {
        name = name.toLowerCase();

        int min = name.charAt(from);
        int minIndex = from;

        for (int i = from + 1; i < to; i++)
        {
            char letter = name.charAt(i);
            if (letter < min)
            {
                min = letter;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private String getSymbolByPositions(String name, int firstCharacterPosition, int secondCharacterPosition)
    {
        return new StringBuilder()
                .append(Character.toUpperCase(name.charAt(firstCharacterPosition)))
                .append(name.charAt(secondCharacterPosition))
                .toString();
    }
}
