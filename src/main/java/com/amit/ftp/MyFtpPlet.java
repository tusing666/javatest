package com.amit.ftp;

import org.apache.ftpserver.ftplet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class MyFtpPlet extends DefaultFtplet {

    private static final Logger logger = LoggerFactory.getLogger(MyFtpPlet.class);

    @Override
    public FtpletResult onConnect(FtpSession session) throws FtpException, IOException {
    	// TODO Auto-generated method stub
    	
    	System.out.println("client address -- " + session.getClientAddress());
    	
    	return super.onConnect(session);
    }
    
    @Override
    public FtpletResult onLogin(FtpSession session, FtpRequest request) throws FtpException, IOException {
    	// TODO Auto-generated method stub
    	return super.onLogin(session, request);
    }
    
    @Override
    public FtpletResult afterCommand(FtpSession session, FtpRequest request, FtpReply reply)
    		throws FtpException, IOException {
    	// TODO Auto-generated method stub
    	
//    	reply.
    	
    	return super.afterCommand(session, request, reply);
    }
    
    @Override
    public FtpletResult onUploadStart(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        //Get the upload path of the uploaded file
        String path = session.getUser().getHomeDirectory();
        //Get upload users
        String name = session.getUser().getName();
        //Get upload file name
        String filename = request.getArgument();
        logger.info("user:'{}'，Upload file to directory:'{}'，The file name is:'{}'，Status: start upload~", name, path, filename);
        
        System.out.println("client address -- " + session.getClientAddress());
        return super.onUploadStart(session, request);
    }


    @Override
    public FtpletResult onUploadEnd(FtpSession session, FtpRequest request)
            throws FtpException, IOException {
        //Get the upload path of the uploaded file
        String path = session.getUser().getHomeDirectory();
        //Get upload users
        String name = session.getUser().getName();
        //Get upload file name
        String filename = request.getArgument();
        logger.info("user:'{}'，Upload file to directory:'{}'，The file name is:'{}，Status: successful!'", name, path, filename);
        return super.onUploadEnd(session, request);
    }

    @Override
    public FtpletResult onDownloadStart(FtpSession session, FtpRequest request) throws FtpException, IOException {
        //todo servies...
        return super.onDownloadStart(session, request);
    }

    @Override
    public FtpletResult onDownloadEnd(FtpSession session, FtpRequest request) throws FtpException, IOException {
        //todo servies...
        return super.onDownloadEnd(session, request);
    }

}