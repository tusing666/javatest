package com.amit.ftp;

//import com.talkingdata.tds.ftpserver.plets.MyFtpPlet;
import org.apache.commons.io.IOUtils;
import org.apache.ftpserver.DataConnectionConfigurationFactory;
import org.apache.ftpserver.FtpServer;
import org.apache.ftpserver.FtpServerFactory;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.Ftplet;
import org.apache.ftpserver.listener.Listener;
import org.apache.ftpserver.listener.ListenerFactory;
import org.apache.ftpserver.ssl.SslConfigurationFactory;
import org.apache.ftpserver.usermanager.ClearTextPasswordEncryptor;
import org.apache.ftpserver.usermanager.DbUserManagerFactory;
import org.apache.ftpserver.usermanager.PropertiesUserManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Note: the class marked with @ Configuration will be added to the ioc
 * container, and all the @ Bean annotated methods in the class will be
 * dynamically proxied, so the same instance will be returned when calling this
 * method. ftp Service access address: ftp://localhost:3131/
 */
@Configuration("MyFtp")
public class MyFtpServer {

	private static final Logger logger = LoggerFactory.getLogger(MyFtpServer.class);

	// Spring boot is configured to inject data directly
//	@Autowired
//	private DataSource dataSource;
	protected FtpServer server;

	// We use spring to load @ Configuration to initialize ftp server
	public MyFtpServer(DataSource dataSource) {
//		this.dataSource = dataSource;
		initFtp();
		logger.info("Apache ftp server is already instantiation complete!");
	}
	public MyFtpServer() {
		initFtp();
		logger.info("Apache ftp server instantiation complete!");
	}

	/**
	 * ftp server init
	 * @throws FileNotFoundException 
	 * 
	 * @throws IOException
	 */
	public void initFtp() {

		FtpServerFactory serverFactory = new FtpServerFactory();
		ListenerFactory listenerFactory = new ListenerFactory();

		// 1. Set service port
		listenerFactory.setPort(3131);

		// 2. Set the interface range of passive mode data upload, and the ECS needs to
		// open the corresponding port to the client
		DataConnectionConfigurationFactory dataConnectionConfFactory = new DataConnectionConfigurationFactory();
		dataConnectionConfFactory.setPassivePorts("10000-10500");
		listenerFactory.setDataConnectionConfiguration(dataConnectionConfFactory.createDataConnectionConfiguration());

		// 3. Add SSL security configuration
//        SslConfigurationFactory ssl = new SslConfigurationFactory();
//        ssl.setKeystoreFile(new File("src/main/resources/ftpserver.jks"));
//        ssl.setKeystorePassword("password");
		// ssl.setSslProtocol("SSL");
		// set the SSL configuration for the listener
//        listenerFactory.setSslConfiguration(ssl.createSslConfiguration());
//        listenerFactory.setImplicitSsl(true);

		// 4. Replace the default listener
		Listener listener = listenerFactory.createListener();
		serverFactory.addListener("default", listener);

		// 5. Configure custom user events
		Map<String, Ftplet> ftpLets = new HashMap();
		ftpLets.put("ftpService", new MyFtpPlet());
		serverFactory.setFtplets(ftpLets);

		// 6. Read user's configuration information
		// Note: the configuration file is located in the resources directory. If the
		// project is published as a jar package using the built-in container, FTPServer
		// cannot directly read the configuration file in the jar package.
		// Solution: copy the file to the specified directory (specified in this article
		// to the root directory) before FTPServer can read it.
        PropertiesUserManagerFactory userManagerFactory = new PropertiesUserManagerFactory();
        String tempPath = System.getProperty("java.io.tmpdir") + System.currentTimeMillis() + ".properties";
//        File tempConfig = new File(tempPath);
        File tempConfig = new File("users.properties");
        ClassPathResource resource = new ClassPathResource("users.properties");
        try {
			IOUtils.copy(resource.getInputStream(), new FileOutputStream(tempConfig));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        userManagerFactory.setFile(tempConfig);
        userManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor()); // password in clear text
        serverFactory.setUserManager(userManagerFactory.createUserManager());

		// 6.2. Store user instance based on Database
//		DbUserManagerFactory dbUserManagerFactory = new DbUserManagerFactory();
//		// todo....
//		dbUserManagerFactory.setDataSource(dataSource);
//		dbUserManagerFactory.setAdminName("admin");
//		dbUserManagerFactory.setSqlUserAdmin("SELECT userid FROM FTP_USER WHERE userid='{userid}' AND userid='admin'");
//		dbUserManagerFactory.setSqlUserInsert("INSERT INTO FTP_USER (userid, userpassword, homedirectory, "
//				+ "enableflag, writepermission, idletime, uploadrate, downloadrate) VALUES "
//				+ "('{userid}', '{userpassword}', '{homedirectory}', {enableflag}, "
//				+ "{writepermission}, {idletime}, uploadrate}, {downloadrate})");
//		dbUserManagerFactory.setSqlUserDelete("DELETE FROM FTP_USER WHERE userid = '{userid}'");
//		dbUserManagerFactory.setSqlUserUpdate(
//				"UPDATE FTP_USER SET userpassword='{userpassword}',homedirectory='{homedirectory}',enableflag={enableflag},writepermission={writepermission},idletime={idletime},uploadrate={uploadrate},downloadrate={downloadrate},maxloginnumber={maxloginnumber}, maxloginperip={maxloginperip} WHERE userid='{userid}'");
//		dbUserManagerFactory.setSqlUserSelect("SELECT * FROM FTP_USER WHERE userid = '{userid}'");
//		dbUserManagerFactory.setSqlUserSelectAll("SELECT userid FROM FTP_USER ORDER BY userid");
//		dbUserManagerFactory
//				.setSqlUserAuthenticate("SELECT userid, userpassword FROM FTP_USER WHERE userid='{userid}'");
//		dbUserManagerFactory.setPasswordEncryptor(new ClearTextPasswordEncryptor());
//		serverFactory.setUserManager(dbUserManagerFactory.createUserManager());

		// 7. Instantiate FTP Server
		server = serverFactory.createServer();

	}

	/**
	 * ftp server start
	 */
	public void start() {
		try {
			server.start();
			logger.info("Apache Ftp server is starting!");
		} catch (FtpException e) {
			e.printStackTrace();
		}
	}

	/**
	 * ftp server stop
	 */
	public void stop() {
		server.stop();
		logger.info("Apache Ftp server is stoping!");
	}

}