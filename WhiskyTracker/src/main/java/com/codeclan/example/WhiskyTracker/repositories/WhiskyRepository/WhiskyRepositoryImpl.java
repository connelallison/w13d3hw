package com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;


import com.codeclan.example.WhiskyTracker.models.Whisky;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class WhiskyRepositoryImpl implements WhiskyRepositoryCustom {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Whisky> findWhiskiesFromDistilleryOfAge(Long distilleryId, int age) {
        List<Whisky> results = null;
        Criteria criteria = null;
        try {
            Session session = entityManager.unwrap(Session.class);
            criteria = session.createCriteria(Whisky.class);
//            criteria.createAlias("whiskies", "whisky");
            Criterion criterion1 = Restrictions.eq("distillery.id", distilleryId);
            Criterion criterion2 = Restrictions.eq("age", age);
            criteria.add(Restrictions.and(criterion1, criterion2));
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        results = criteria.list();
        return results;
    }

    @Override
    public List<Whisky> findWhiskiesFromRegion(String region) {
        List<Whisky> results = null;
        Criteria criteria = null;

        try {
            Session session = entityManager.unwrap(Session.class);
            criteria = session.createCriteria(Whisky.class);
            criteria.createAlias("distillery", "distillery");
            criteria.add(Restrictions.eq("distillery.region", region));
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        results = criteria.list();
        return results;
    }
}
