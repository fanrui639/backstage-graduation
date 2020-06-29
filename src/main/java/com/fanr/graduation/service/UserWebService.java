package com.fanr.graduation.service;

import com.fanr.graduation.entity.User;

import java.util.ArrayList;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


@WebService
public interface UserWebService {
    @WebMethod
    String getName(@WebParam(name = "userId") String userId);

    @WebMethod
    User getUser(String userId);

    @WebMethod
    ArrayList<User> getAlLUser();
}
