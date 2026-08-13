package com.app.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import com.app.model.User;
import com.app.util.HibernateUtil;

public class UserDAO {

	public User validateUser(String username, String password) {

	    Session session = HibernateUtil.getSessionFactory().openSession();

	    Query<User> query = session.createQuery(
	        "from User where username=:u and password=:p", User.class);

	    query.setParameter("u", username);
	    query.setParameter("p", password);

	    java.util.List<User> list = query.list();

	    session.close();

	    if(list.size() > 0) {
	        return list.get(0);
	    }else {
	        return null;
	    }
	}
	
	
	
	public boolean registerUser(User user) {

	    Session session = null;

	    try {
	        session = HibernateUtil.getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(user);

	        session.getTransaction().commit();

	        return true;

	    } catch (Exception e) {

	        if (session != null && session.getTransaction() != null) {
	            session.getTransaction().rollback();
	        }

	        e.printStackTrace();
	        return false;

	    } finally {

	        if (session != null) {  
	            session.close();
	        }
	    }
	}
}