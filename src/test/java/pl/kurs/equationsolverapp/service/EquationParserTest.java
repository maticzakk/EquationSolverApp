package pl.kurs.equationsolverapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.test.context.ContextConfiguration;
import pl.kurs.equationsolverapp.config.JpaConfig;
import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;

@ContextConfiguration(classes = {JpaConfig.class})
public class EquationParserTest {

    private EquationParser parser;

    @Before
    public void setUp() {
        parser = new EquationParser();
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenOperatorIsWrong() {
        //given
        String input = "2x + 3 = 7";
        //when,then
        Assertions.assertThrows(UnknownOperatorException.class, () -> parser.parseEquation(input));
    }

    @Test
    public void shouldReturnValidEquationFromInputString() throws InvalidEquationFormatException {
        //given
        String input = "2 + 20 * 2";
        //when
        String result = parser.parseFormat(input);
        //then
        Assertions.assertEquals(input, result);
    }

    @Test
    public void shouldReturnParseEquationFromValidEquationParesString() throws UnknownOperatorException {
        //given
        String input = "5 + 2 * 3";
        String expectedOutput = "+*";
        //when
        String result = parser.parseEquation(input);
        //then
        Assertions.assertEquals(expectedOutput, result);
    }

    @Test
    public void shouldThrowInvalidFormatExceptionWhenFormatIsWrong() {
        //given
        String input = "5 & 2 * 3";
        //when,then
        Assertions.assertThrows(InvalidEquationFormatException.class, () -> parser.parseFormat(input));
    }

    @Test
    public void shouldThrowInvalidFormatExceptionWhenEquationIsWrong() {
        //given
        String input = "5 + 2 *";
        //when,then
        Assertions.assertThrows(InvalidEquationFormatException.class, () -> parser.parseFormat(input));
    }
}