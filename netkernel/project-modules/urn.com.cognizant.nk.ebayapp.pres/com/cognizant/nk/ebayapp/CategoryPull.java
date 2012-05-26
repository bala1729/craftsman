package com.cognizant.nk.ebayapp;

import org.netkernel.layer0.nkf.INKFRequest;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;


public class CategoryPull extends StandardAccessorImpl {
	
	    @Override
	    public void onSource(INKFRequestContext context) throws Exception
	    {
	    	// SOAP API Call
	    	
	    	String url = "https://api.sandbox.ebay.com/wsapi";
	    	
	   
	    	String appid = "Cognizan-6f9d-488c-b526-5ca13e498447";
    	
	    	
	    	String endpoint = "https://api.sandbox.ebay.com/wsapi?appid=Cognizan-6f9d-488c-b526-5ca13e498447&version=547&callname=GetCategories&siteid=0&Routing=default";
	    	
	    	System.out.println("endpoint : "+endpoint);
	    	//INKFRequest req = context.createRequest("active:wsSOAPClient");
	    	INKFRequest req = context.createRequest("active:httpPost");
	    	
	    	req.addArgument("message", "res:/resources/xml/GetCategoriesSoapRequest.xml");
	    	
	    	req.addArgument("endpoint", endpoint);
	    	req.addArgument("action", "");
	    	
	    	Object rep = context.issueRequest(req);
	    	
	    	
	    	
	    	context.createResponseFrom(rep).setMimeType("application/xml");

	    }


}
