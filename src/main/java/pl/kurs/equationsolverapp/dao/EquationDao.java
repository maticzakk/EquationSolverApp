package pl.kurs.equationsolverapp.dao;

import org.springframework.stereotype.Repository;
import pl.kurs.equationsolverapp.model.entity.EquationEvent;

import javax.persistence.*;
import javax.transaction.Transactional;

@Repository
@Transactional
public class EquationDao implements IEquationDao {

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void save(EquationEvent operationEvent) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();
        entityManager.persist(operationEvent);
        tx.commit();
        entityManager.close();
    }

    @Override
    public EquationEvent get(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EquationEvent equationEvent = entityManager.find(EquationEvent.class, id);
        entityManager.close();
        return equationEvent;
    }
}
