package ru.namibios.arduino.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class Http {
	
	// localhost:5050 - проброс на сервак 
	
	private static final String AUTH_URL = "http://localhost:5050/fishingserver/authorized";
	
	private static final String KAPCHA_URL = "http://localhost:5050/fishingserver/kapcha";
	
	private HttpClient httpClient;
	
	private HttpResponse response;

	public Http() {
		httpClient = HttpClients.createDefault();
	}
	
	public String parseKapcha(String key, String matrix) throws ClientProtocolException, IOException{

		HttpPost post = new HttpPost(KAPCHA_URL);
		
		ArrayList<BasicNameValuePair> postParameters = new ArrayList<BasicNameValuePair>();
	    postParameters.add(new BasicNameValuePair("HASH", key));
	    postParameters.add(new BasicNameValuePair("MATRIX", matrix));

	    post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public String authorized(String key) throws ClientProtocolException, IOException{
		
		HttpPost post = new HttpPost(AUTH_URL);
		
		ArrayList<BasicNameValuePair> postParameters = new ArrayList<BasicNameValuePair>();
	    postParameters.add(new BasicNameValuePair("HASH", key));

	    post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public static void main(String[] args) throws Exception {
		
		/*KapchaServer kapchaServer = new KapchaServer("resources/debug/1/20170711_222631_327.jpg");
		String key = kapchaServer.getKey();
		
		System.out.println("Key " + key);*/
		
	}
}