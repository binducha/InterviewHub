package com.app.dao;

import java.util.List;

import org.hibernate.Session;

import com.app.model.Experience;
import com.app.util.HibernateUtil;

public class ExperienceDAO {

    public void saveExperience(Experience exp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(exp);

        session.getTransaction().commit();
        session.close();
    }

    //  NEW METHOD FOR FILTERING
    public List<Experience> getFilteredExperiences(String company, String candidate, String round) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        String hql = "from Experience where 1=1";

        if (company != null && !company.isEmpty()) {
            hql += " and company = :c";
        }
        if (candidate != null && !candidate.isEmpty()) {
            hql += " and candidateName = :n";
        }
        if (round != null && !round.isEmpty()) {
            hql += " and round = :r";
        }

        var query = session.createQuery(hql, Experience.class);

        if (company != null && !company.isEmpty()) {
            query.setParameter("c", company);
        }
        if (candidate != null && !candidate.isEmpty()) {
            query.setParameter("n", candidate);
        }
        if (round != null && !round.isEmpty()) {
            query.setParameter("r", round);
        }

        List<Experience> list = query.list();

        session.close();
        return list;
    }
}