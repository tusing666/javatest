package com.amit.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * FTP Server to receive the request and analyse it
 *
 */

@SpringBootApplication
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		
		MyFtpServer ftpServer = new MyFtpServer();
		ftpServer.start();
		
//		FtpClientUtil clientUtil = new FtpClientUtil();
//		clientUtil.show();
//		System.out.println("Hello World!");
	}
}
