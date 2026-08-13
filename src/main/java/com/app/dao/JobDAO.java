package com.app.dao;

import java.util.List;

import org.hibernate.Session;

import com.app.model.Job;
import com.app.util.HibernateUtil;

public class JobDAO {

    public void saveJob(Job job) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        session.save(job);

        session.getTransaction().commit();
        session.close();
    }

    public List<Job> getAllJobs() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Job> list = session
                .createQuery("from Job", Job.class)
                .list();

        session.close();

        return list;
    }
}