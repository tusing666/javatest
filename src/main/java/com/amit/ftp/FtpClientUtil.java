package com.amit.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import java.io.*;

public class FtpClientUtil {

	// ip address of ftp server
	private static String FTP_ADDRESS = "localhost";
	// Port number
	private static int FTP_PORT = 3131;
	// User name
	private static String FTP_USERNAME = "bxl";
	// Password
	private static String FTP_PASSWORD = "123456";
	// Relative path
	private static String FTP_BASEPATH = "";

	public void show() {

		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");

		System.out.println("Client ip is:" + ftp.getLocalAddress().getHostAddress());
	}

	public static boolean uploadFile(String remoteFileName, InputStream input) {

		boolean flag = false;
		FTPClient ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");

		try {
			int reply;
			ftp.connect(FTP_ADDRESS, FTP_PORT);// Connect to FTP server
			ftp.login(FTP_USERNAME, FTP_PASSWORD);// Sign in
			reply = ftp.getReplyCode();
			System.out.println("Sign in ftp The service return status code is:" + reply);
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return flag;
			}
			ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
			// Set to passive mode
			ftp.enterLocalPassiveMode();
			ftp.makeDirectory(FTP_BASEPATH);
			ftp.changeWorkingDirectory(FTP_BASEPATH);
			// originFilePath is the file name of the uploaded file. It is recommended to
			// use the generated unique name. It is better to transcode the Chinese name
			boolean a = ftp.storeFile(remoteFileName, input);
//            boolean a = ftp.storeFile(new String(remoteFileName.getBytes(),"iso-8859-1"),input);
			System.out.println("The original file name to upload is:" + remoteFileName + ", Upload results:" + a);
			input.close();
			ftp.logout();
			flag = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
				}
			}
		}
		return flag;
	}

//    public static Boolean uploadFile(String remoteFileName, InputStream inputStream, String ftpAddress, int ftpPort,
//                                     String ftpName, String ftpPassWord, String ftpBasePath) {
//        FTP_ADDRESS = ftpAddress;
//        FTP_PORT = ftpPort;
//        FTP_USERNAME = ftpName;
//        FTP_PASSWORD = ftpPassWord;
//        FTP_BASEPATH = ftpBasePath;
//        uploadFile(remoteFileName,inputStream);
//        return true;
//    }

	public static boolean deleteFile(String filename) {

		boolean flag = false;
		FTPClient ftpClient = new FTPClient();

		try {
			// Connect to FTP server
			ftpClient.connect(FTP_ADDRESS, FTP_PORT);
			// Log in to FTP server
			ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
			// Verify FTP server login success
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// Switch FTP directory
			ftpClient.changeWorkingDirectory(FTP_BASEPATH);
			ftpClient.dele(filename);
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}

		return flag;
	}

	public static boolean downloadFile(String filename, String localPath) {
		boolean flag = false;
//        FTPSClient ftpClient = new FTPSClient("TLS", true);
		FTPClient ftpClient = new FTPClient();
		try {
			// Connect to FTP server
			ftpClient.connect(FTP_ADDRESS, FTP_PORT);
			// Log in to FTP server
			ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
			// Verify FTP server login success
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// Switch FTP directory
			ftpClient.changeWorkingDirectory(FTP_BASEPATH);
			// This is the demo method. Normally, you should query the fileName in the
			// database
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (filename.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localPath + "/" + file.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
			System.out.println("File download completed!!!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}
}