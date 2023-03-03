package pl.kurs.equationsolverapp.service;

import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;

public interface IEquationParse {
    String parseEquation(String parse) throws UnknownOperatorException;
    String parseFormat(String parse) throws InvalidEquationFormatException;
}
