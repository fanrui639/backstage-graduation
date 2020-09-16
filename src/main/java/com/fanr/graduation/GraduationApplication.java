package com.fanr.graduation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GraduationApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraduationApplication.class, args);

//        //启动并发布webservice远程服务  端口号与服务端口号不能一样
//        Endpoint.publish("http://127.0.0.1:8890/WsServiceImpl/Ws?wsdl",new WsServiceImpl());
//        System.out.println("启动并发布webservice远程服务，服务发布成功....");
    }

}
