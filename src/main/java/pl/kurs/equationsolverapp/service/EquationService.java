package pl.kurs.equationsolverapp.service;

import org.springframework.stereotype.Service;
import pl.kurs.equationsolverapp.dao.IEquationDao;
import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;
import pl.kurs.equationsolverapp.model.entity.EquationEvent;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.sql.Timestamp;
import java.time.Instant;

@Service
public class EquationService implements IEquationService {

    private final IEquationDao equationDao;
    private final IEquationParse equationParse;

    public EquationService(IEquationDao equationDao, IEquationParse equationParse) {
        this.equationDao = equationDao;
        this.equationParse = equationParse;
    }

    @Override
    public double evaluate(String expression) throws UnknownOperatorException, InvalidEquationFormatException {
        equationParse.parseEquation(expression);
        equationParse.parseFormat(expression);
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object result = engine.eval(expression);
            EquationEvent equationEvent = new EquationEvent(expression, Timestamp.from(Instant.now()));
            equationDao.save(equationEvent);
            return Double.parseDouble(result.toString());
        } catch (ScriptException e) {
            throw new InvalidEquationFormatException("Niepoprawny format!");
        }
    }
}
