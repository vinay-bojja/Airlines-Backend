package com.lti.service;

import com.lti.dto.LoginStatusDT;

public interface LoginService {

	public LoginStatusDT validateUser(String emailId, String password);
	public LoginStatusDT validateAdmin(String username, String password);
}
