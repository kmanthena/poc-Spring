package com.nisum.repository;

import com.nisum.domain.User;

public interface GoogleSignInService {

	public String callTokenAPI(String code);

	public User callUserInfoAPI(String httpResponse);

	public void testProperties();

}
