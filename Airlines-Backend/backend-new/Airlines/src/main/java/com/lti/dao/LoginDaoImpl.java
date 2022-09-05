package com.lti.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.lti.dto.LoginStatusDT;
import com.lti.entity.Admin;
import com.lti.entity.User;

@Repository
public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
	protected EntityManager entityManager;

	@Override
	public LoginStatusDT validateUser(String email, String password) {

		String fetchedQuery = "select u from User u where u.emailId=:fetchEmail and u.userPassword=:fetchPassword";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("fetchEmail", email);
		query.setParameter("fetchPassword", password);
		List<User> fetchedResult = query.getResultList();

		LoginStatusDT loginStatus = new LoginStatusDT();

		if (fetchedResult.isEmpty()) {
			loginStatus.setResult(false);
			loginStatus.setFetchedUserId(0);
			loginStatus.setFetchedName(null);
		} else {
			loginStatus.setResult(true);
			loginStatus.setFetchedUserId(fetchedResult.get(0).getUserId());
			String name = fetchedResult.get(0).getfName() +" "+ fetchedResult.get(0).getlName();
			loginStatus.setFetchedName(name);
		}

		return loginStatus;
	}

	@Override
	public LoginStatusDT validateAdmin(String username, String password) {
		String fetchedQuery = "select a from Admin a where a.adminUsername=:fetchUsername and a.adminPassword=:fetchPassword";
		Query query = entityManager.createQuery(fetchedQuery);
		query.setParameter("fetchUsername", username);
		query.setParameter("fetchPassword", password);

		List<Admin> fetchedResult = query.getResultList();

		LoginStatusDT loginStatus = new LoginStatusDT();

		if (fetchedResult.isEmpty()) {
			loginStatus.setResult(false);
			loginStatus.setFetchedUserId(0);
		} else {
			loginStatus.setResult(true);
			loginStatus.setFetchedUserId(fetchedResult.get(0).getAdminId());
		}

		return loginStatus;

	}

}
