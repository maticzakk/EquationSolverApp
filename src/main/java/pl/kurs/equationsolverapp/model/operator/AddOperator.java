package pl.kurs.equationsolverapp.model.operator;

import org.springframework.stereotype.Component;

@Component
public class AddOperator implements IOperator {
    @Override
    public char operator() {
        return '+';
    }
}
