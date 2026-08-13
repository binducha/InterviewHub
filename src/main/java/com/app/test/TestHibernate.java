package com.app.test;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.app.model.User;
import com.app.util.HibernateUtil;

public class TestHibernate {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        User u = new User();
        u.setUsername("admin");
        u.setPassword("123");

        session.save(u);

        tx.commit();
        session.close();

        System.out.println("Data Inserted Successfully!");
    }
}