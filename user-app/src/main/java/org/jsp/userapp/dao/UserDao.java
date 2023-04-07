package org.jsp.userapp.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.jsp.userapp.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class UserDao {

	@Autowired
	EntityManager mgr;
	
	public User saveUser(User user) {
		EntityTransaction tr=mgr.getTransaction();
		tr.begin();
		mgr.persist(user);
		tr.commit();
		return user;		
	}
	
	public User updateUser(User user) {
		EntityTransaction tr=mgr.getTransaction();
		tr.begin();
		mgr.merge(user);
		tr.commit();
		return user;		
	}
	
	public User findUserById(int id) {
		return mgr.find(User.class, id);
	}
	
	
	public boolean deleteUser(int id) {
		User recUser=findUserById(id);		
		
		if(recUser!=null) {
			EntityTransaction tr=mgr.getTransaction();
			tr.begin();
			mgr.remove(recUser);
			tr.commit();
			return true;
		}
		return false;
	}
	
	public User verifyUser(long phone,String password) {
		String hql="select u from User u where u.phone=?1 and u.password=?2";
		Query q=mgr.createQuery(hql);
		q.setParameter(1, phone);
		q.setParameter(2, password);
		List<User> users=q.getResultList();
		if(users.size()>0) {
			return users.get(0);
		}
		return null;
	}
	
	
	
}
