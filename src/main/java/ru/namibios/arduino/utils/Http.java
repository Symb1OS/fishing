package ru.namibios.arduino.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import ru.namibios.arduino.model.Chars;

public class Http {
	
	private static final String AUTH_URL = "http://localhost:8080/fishingserver/kapcha?HASH=%s";
	
	private static final String KAPCHA_URL = "http://localhost:8080/fishingserver/kapcha?HASH=%s&MATRIX=%s";
	
	private HttpClient httpClient;
	
	private HttpResponse response;

	public Http() {
		httpClient = HttpClients.createDefault();
	}
	
	public String parseKapcha(String key, String matrix) throws ClientProtocolException, IOException{
		String url = String.format(KAPCHA_URL, key, matrix);
		System.out.println(url);
		response = httpClient.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public String authorized(String key) throws ClientProtocolException, IOException{
		
		String url = String.format(AUTH_URL, key);
		System.out.println(url);
		response = httpClient.execute(new HttpGet(url));
		HttpEntity entity = response.getEntity();
		return EntityUtils.toString(entity, "UTF-8");
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		ObjectMapper mapper = new ObjectMapper();
		
		Http http = new Http();
		String test = http.parseKapcha("hashkey", mapper.writeValueAsString(Chars.d.getTemplates().get(0)));
		System.out.println("test " + test);
		
	}
}