package pl.kurs.equationsolverapp.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
public class EquationEvent implements Serializable {

    private static final long serialVersionUID = 99999L;
    @Id
    @GeneratedValue
    private Long id;
    private String expression;
    private Timestamp date;

    public EquationEvent(String expression, Timestamp date) {
        this.expression = expression;
        this.date = date;
    }

    public EquationEvent() {
    }

    public Long getId() {
        return id;
    }

    public String getExpression() {
        return expression;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
