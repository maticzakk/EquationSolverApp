package pl.kurs.equationsolverapp.model.operator;

import org.springframework.stereotype.Component;

@Component
public class DivideOperator implements IOperator {
    @Override
    public char operator() {
        return '/';
    }
}
