package pl.kurs.equationsolverapp.dao;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.kurs.equationsolverapp.config.JpaConfig;
import pl.kurs.equationsolverapp.model.entity.EquationEvent;
import javax.annotation.Resource;

import java.sql.Timestamp;
import java.time.Instant;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class, EquationDao.class})
@ComponentScan(basePackages = "pl.kurs.equationsolverapp")
public class EquationDaoTest {
    @Resource
    private EquationDao equationDao;
    @BeforeEach
    public void setUp() {
        ApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        equationDao = context.getBean(EquationDao.class);
    }

    @Test
    public void test() {
        String equation = "2 + 2 * 2";
        EquationEvent equationEvent = new EquationEvent(equation, Timestamp.from(Instant.now()));
        equationDao.save(equationEvent);
        EquationEvent test = equationDao.get(1L);
        assertEquals("2 + 2 * 2", test.getExpression());

    }
}