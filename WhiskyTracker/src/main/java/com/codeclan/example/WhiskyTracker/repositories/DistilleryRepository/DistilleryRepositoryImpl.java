package com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class DistilleryRepositoryImpl implements DistilleryRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    public List<Distillery> findDistilleriesWithWhiskiesOfAge(int age) {
        List<Distillery> results = null;
        Criteria criteria = null;

        try {
            Session session = entityManager.unwrap(Session.class);
            criteria = session.createCriteria(Distillery.class);
            criteria.createAlias("whiskies", "whisky");
            criteria.add(Restrictions.eq("whisky.age", age));
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        results = criteria.list();
        return results;
    }
}
