package com.example.alurastickers;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClienteHttp {

	public String buscaDados(String url) {
		HttpGet request = new HttpGet(url);
		
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse httpResponse = httpClient.execute(request)) {
			HttpEntity entity = httpResponse.getEntity();
			return EntityUtils.toString(entity);
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}

	}
}
