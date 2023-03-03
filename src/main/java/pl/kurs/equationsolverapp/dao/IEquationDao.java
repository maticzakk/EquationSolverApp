package pl.kurs.equationsolverapp.dao;

import pl.kurs.equationsolverapp.model.entity.EquationEvent;

public interface IEquationDao {
    void save(EquationEvent equation);
    EquationEvent get(Long id);
}
