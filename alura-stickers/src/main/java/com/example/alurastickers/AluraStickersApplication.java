package com.example.alurastickers;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class AluraStickersApplication {

	public static void main(String[] args) throws Exception {
		//		String urlIMDV = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
		//		IExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

		String urlNasa = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-06-12&end_date=2022-06-14";
		IExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

		String resultado = new ClienteHttp().buscaDados(urlNasa);

		if (resultado.isEmpty()) {
			List<Conteudo> listaConteudos = extrator.extraiConteudos(resultado);

			int contador = 1;
			int tamanho = String.valueOf(listaConteudos.size()).length();

			for (Conteudo item : listaConteudos) {
				System.out.println(String.format("%0" + tamanho + "d - %s", contador++, item.getTitulo()));

				InputStream inputStream = new URL(item.getUrlImagem()).openStream();
				String caminhoNomeArquivo = "src/main/resources/imagens/saida/" + item.getTitulo() + ".png";

				new GeradorFigurinhas().criar(inputStream, caminhoNomeArquivo.replace(":", ""));
			}
		}
	}

}
