package pl.kurs.equationsolverapp.service;


import org.springframework.stereotype.Service;
import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;
import pl.kurs.equationsolverapp.model.operator.*;

import java.util.List;

@Service
public class EquationParser implements IEquationParse {

    List<Character> list = List.of(
            new AddOperator().operator(),
            new SubOperator().operator(),
            new DivideOperator().operator(),
            new MultiplyOperator().operator());

    @Override
    public String parseEquation(String input) throws UnknownOperatorException {
        String parseString = input
                .replaceAll("[^0-9+\\-*/.\\s]", "");
        for (int i = 0; i < parseString.length(); i++) {
            char c = parseString.charAt(i);
            if (!list.contains(c) && !Character.isDigit(c) && c != '.') {
                throw new UnknownOperatorException("Nieznany operator");
            }
        }
        return parseString;
    }

    @Override
    public String parseFormat(String input) throws InvalidEquationFormatException {
        if (!input.matches("[0-9+\\-*/\\.\\s]*[0-9]+([.,][0-9]+)?")) {
            throw new InvalidEquationFormatException("Zły format wyrażenia ");
        }
        String[] numbers = input.split("[+\\-*/.]");
        String[] operators = input.replaceAll("[0-9.\\s]+", "").split("");
        if (numbers.length - operators.length != 1) {
            throw new InvalidEquationFormatException("Zły format wyrażenia");
        }

        return input;
    }
}
