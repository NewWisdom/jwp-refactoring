package kitchenpos.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@SpringBootTest
public class EntityManagerSupport {

    @Autowired
    private EntityManager em;

    protected <T> T save(T entity) {
        em.persist(entity);
        em.flush();
        em.clear();
        return entity;
    }
}
