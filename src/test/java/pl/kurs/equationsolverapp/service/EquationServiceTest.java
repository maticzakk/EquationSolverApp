package pl.kurs.equationsolverapp.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kurs.equationsolverapp.config.JpaConfig;
import pl.kurs.equationsolverapp.dao.EquationDao;
import pl.kurs.equationsolverapp.exceptions.InvalidEquationFormatException;
import pl.kurs.equationsolverapp.exceptions.UnknownOperatorException;

import javax.annotation.Resource;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, EquationParser.class, EquationDao.class})
public class EquationServiceTest {

    @Resource
    private EquationParser equationParser;
    @Resource
    private EquationDao equationDao;
    EquationService equationService;

    @Before
    public void setUp() {
        equationService = new EquationService(equationDao, equationParser);
    }

    @Test
    public void shouldReturn6FromEquation() throws Exception {
        //given
        double result = equationService.evaluate("2 + 2 * 2");
        //when,then
        assertEquals(6.0, result, 0.001);
    }

    @Test
    public void shouldThrowInvalidEquationFormatExceptionWhenEquationFormatIsWrong() {
        //when,then
        assertThrows(InvalidEquationFormatException.class, () -> equationService.evaluate(" 2 ++"));
    }

    @Test
    public void shouldThrowUnknownOperatorExceptionWhenEquationOperatorIsWrong() {
        //when,then
        assertThrows(UnknownOperatorException.class, () -> equationService.evaluate("2 # 2"));
    }

    @Test
    public void shouldEvaluateEquationWithSpacesReturnsNumericValue() throws Exception {
        double result = equationService.evaluate("2 + 2  ");
        assertEquals(4.0, result, 0.001);
    }

    @Test
    public void shouldEvaluateEquationWithTabsReturnsNumericValue() throws Exception {
        double result = equationService.evaluate("2\t+\t2");
        assertEquals(4.0, result, 0.001);
    }


}