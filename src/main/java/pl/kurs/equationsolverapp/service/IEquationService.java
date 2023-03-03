package pl.kurs.equationsolverapp.service;

import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;

import javax.script.ScriptException;

public interface IEquationService {
    double evaluate(String expression) throws UnknownOperatorException, InvalidEquationFormatException, ScriptException;
}
