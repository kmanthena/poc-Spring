package com.nisum.services;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.nisum.domain.User;
import com.nisum.repository.GoogleSignInService;

@Service
public class GoogleSignInServiceImpl implements GoogleSignInService {

	Logger log = Logger.getLogger(GoogleSignInServiceImpl.class);

	@Value("${google.token.api}")
	private String tokenAPI;

	@Value("${google.user.profile.api}")
	private String userProfileAPI;

	@Value("${google.console.project.clientid}")
	private String clientId;

	@Value("${google.console.project.clientsecreatkey}")
	private String clientSecreatKey;

	@Value("${google.redirect.uri}")
	private String googleRedirectURI;

	public User user;

	public void testProperties() {
		log.info("Calling the testproperties method");
		log.info("google token api::" + tokenAPI);
		log.info("google user profile  api::" + userProfileAPI);
		log.info("client id and and client secreat key::" + clientId);
		log.info("client secreat key::" + clientSecreatKey);
		log.info("google redirect uri::" + googleRedirectURI);
	}

	@Override
	public String callTokenAPI(String code) {
		String httpResponse = "";
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod(tokenAPI);
		post.addRequestHeader("Host", "accounts.google.com");
		post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		NameValuePair[] data = { new NameValuePair("code", code), new NameValuePair("client_id", clientId),
				new NameValuePair("client_secret", clientSecreatKey),
				new NameValuePair("redirect_uri", googleRedirectURI),
				new NameValuePair("grant_type", "authorization_code") };

		post.setRequestBody(data);
		int httpStatus = 0;
		try {
			httpStatus = client.executeMethod(post);
			log.info(httpStatus);
			httpResponse = post.getResponseBodyAsString();
		} catch (IOException e) {
			log.error(e);
		}
		return httpResponse;
	}

	@Override
	public User callUserInfoAPI(String httpResponse) {
		JSONObject accesstoken = new JSONObject(httpResponse);
		JSONObject user1 = null;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(userProfileAPI + "?access_token=" + accesstoken.getString("access_token"));
		User data = null;
		try {
			client.executeMethod(get);
			user1 = new JSONObject(get.getResponseBodyAsString());
			data = (User) new Gson().fromJson(user1.toString(), User.class);
			this.user = data;
		} catch (Exception e) {
			log.error(e);
		}
		return data;
	}

}
