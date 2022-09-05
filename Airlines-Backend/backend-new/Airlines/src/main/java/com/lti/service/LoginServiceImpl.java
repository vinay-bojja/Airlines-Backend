package com.lti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.dao.LoginDao;
import com.lti.dto.LoginStatusDT;
import com.lti.entity.Admin;
import com.lti.entity.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginDao dao;

	@Override
	public LoginStatusDT validateUser(String emailId, String password) {
		LoginStatusDT loginStatus = dao.validateUser(emailId, password);
		return loginStatus;
	}

	@Override
	public LoginStatusDT validateAdmin(String username, String password) {
		LoginStatusDT loginStatus = dao.validateAdmin(username, password);
		return loginStatus;
	}

}
