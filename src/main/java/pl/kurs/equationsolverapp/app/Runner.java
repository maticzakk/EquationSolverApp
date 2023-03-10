package pl.kurs.equationsolverapp.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;
import pl.kurs.equationsolverapp.service.IEquationService;

import javax.script.ScriptException;
import java.util.Scanner;

@ComponentScan(basePackages = "pl.kurs.equationsolverapp")
public class Runner {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(Runner.class);
        IEquationService equationService = ctx.getBean(IEquationService.class);

        try {
            Scanner input = new Scanner(System.in);
            String equation = input.nextLine();
            System.out.println(equationService.evaluate(equation));
        } catch (InvalidEquationFormatException | UnknownOperatorException | ScriptException e) {
            e.printStackTrace();
        }
    }
}
