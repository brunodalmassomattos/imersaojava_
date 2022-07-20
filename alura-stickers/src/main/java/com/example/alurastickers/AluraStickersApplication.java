package com.example.alurastickers;

import java.io.InputStream;
import java.net.URL;
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

				List<Map<String, String>> listaFilmes = new JsonParser().parse(resultado);
				
				int posicao;
				String limpa;
				int contador = 1;
				int tamanho = String.valueOf(listaFilmes.size()).length();
				
				GeradorFigurinhas geradorFigurinhas = new GeradorFigurinhas();

				for (Map<String, String> filme : listaFilmes) {
					System.out.println(String.format("%0" + tamanho + "d - %s", contador++, filme.get("title")));

					try {
						posicao = filme.get("image").lastIndexOf("@");

						if (posicao <= 0) {
							posicao = filme.get("image").lastIndexOf(".");
						}

						limpa = filme.get("image").substring(0, posicao + 1);

						InputStream inputStream = new URL(limpa).openStream();
						String caminhoNomeArquivo = "src/main/resources/imagens/saida/" + filme.get("title") + ".png";

						geradorFigurinhas.criar(inputStream, caminhoNomeArquivo);
					} catch (Exception e) {
						System.out.println(String.format("Erro no filme %s ", filme.get("title")));
						continue;
					}
				}
			}
		}
	}

}
