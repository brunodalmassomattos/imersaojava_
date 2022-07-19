package com.example.alurastickers;

import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class AluraStickersApplication {

	public static void main(String[] args) throws Exception {
		String uri = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		HttpGet request = new HttpGet(uri);

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse httpResponse = httpClient.execute(request)) {

			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String resultado = EntityUtils.toString(entity);
				System.out.println(resultado);

				List<Map<String, String>> listaFilmes = new JsonParser().parse(resultado);
				
				for (Map<String, String> filme : listaFilmes) {
					System.out.println(filme.get("title"));
					System.out.println(filme.get("image"));
					System.out.println(filme.get("imDbRating"));
				}
			}
		}
	}

}
