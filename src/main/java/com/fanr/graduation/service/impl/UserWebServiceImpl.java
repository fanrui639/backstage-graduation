package com.fanr.graduation.service.impl;

import java.util.ArrayList;

import javax.jws.WebService;

import com.fanr.graduation.entity.User;
import com.fanr.graduation.service.UserWebService;

@WebService(targetNamespace="http://UserWebService.service.gf.com/",
	endpointInterface = "com.fanr.graduation.service.UserWebService")
public class UserWebServiceImpl implements UserWebService {

	@Override
	public String getName(String userId) {
		// TODO Auto-generated method stub
		return "Java";
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return new User();
	}

	@Override
	public ArrayList<User> getAlLUser() {
		// TODO Auto-generated method stub
		return new ArrayList();
	}

}

