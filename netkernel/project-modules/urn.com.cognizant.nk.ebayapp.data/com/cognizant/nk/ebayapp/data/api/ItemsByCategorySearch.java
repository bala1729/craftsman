package com.cognizant.nk.ebayapp.data.api;

import org.netkernel.layer0.nkf.INKFRequest;
import org.netkernel.layer0.nkf.INKFRequestContext;
import org.netkernel.module.standard.endpoint.StandardAccessorImpl;

public class ItemsByCategorySearch extends StandardAccessorImpl {
    @Override
    public void onSource(INKFRequestContext context) throws Exception
    {
    	String categoryId = context.getThisRequest().getArgumentValue("catId");
    	System.out.println("Category id : "+categoryId);
    	// REST API Call
    	INKFRequest req = context.createRequest("active:httpGet");
    	
    	req.addArgument("url", "http://svcs.sandbox.ebay.com/services/search/FindingService/v1?OPERATION-NAME=findItemsByCategory&SERVICE-VERSION=1.11.0&SECURITY-APPNAME=Cognizan-6f9d-488c-b526-5ca13e498447&RESPONSE-DATA-FORMAT=XML&REST-PAYLOAD&categoryId=20081&paginationInput.entriesPerPage=2");
    	Object rep=context.issueRequest(req);
    	    	
    	context.createResponseFrom(rep).setMimeType("application/xml");

    }

}
