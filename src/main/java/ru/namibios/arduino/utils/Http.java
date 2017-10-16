package ru.namibios.arduino.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
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

import ru.namibios.arduino.config.Application;

public class Http {
	
	private static final String AUTH_URL = "http://192.168.0.220:9090/fishingserver/authorized";
	private static final String KAPCHA_URL = "http://192.168.0.220:9090/fishingserver/kapcha";
	
	private HttpClient httpClient;
	
	private HttpResponse response;

	public Http() {
		httpClient = HttpClients.createDefault();
	}
	
	public String parseKapcha(String key, String matrix) throws ClientProtocolException, IOException{

		HttpPost post = Builder.config().setUrl(KAPCHA_URL)
				.setParameter(new BasicNameValuePair("HASH", key))
				.setParameter(new BasicNameValuePair("MATRIX", matrix))
				.build();

		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public int authorized(String key) throws ClientProtocolException, IOException{
		
		HttpPost post = Builder.config().setUrl(AUTH_URL)
				.setParameter(new BasicNameValuePair("HASH", key))
				.build();

		response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();
		return Integer.valueOf(EntityUtils.toString(entity, "UTF-8").trim());
	}
	
	private static class Builder {
		
		private HttpPost post;
		private ArrayList<BasicNameValuePair> postParameters;
		
		private Builder(){
			postParameters = new ArrayList<BasicNameValuePair>();
		}
	
		private static Builder config() {
			return new Builder();
		}
		
		private Builder setUrl(String url) {
			post = new HttpPost(url);
			return this;
		}
		
		private Builder setParameter(BasicNameValuePair value) {
			postParameters.add(value);
			return this;
		}
		
		private HttpPost build() throws UnsupportedEncodingException {
			post.setEntity(new UrlEncodedFormEntity(postParameters, "UTF-8"));
			return post;
		} 
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		String hash = Application.getInstance().HASH();
		Http http = new Http();
		int code = http.authorized(hash);
		System.out.println("Response: " + code);
	}
	
}