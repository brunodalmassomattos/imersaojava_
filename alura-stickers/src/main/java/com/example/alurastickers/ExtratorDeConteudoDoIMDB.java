package com.example.alurastickers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements IExtratorDeConteudo {

	@Override
	public List<Conteudo> extraiConteudos(String json) {
		// extrair só os dados que interessam (titulo, poster, classificação)
		List<Map<String, String>> listaDeAtributos = new JsonParser().parse(json);

		List<Conteudo> conteudos = new ArrayList<>();

		// popular a lista de conteudos
		for (Map<String, String> atributos : listaDeAtributos) {
			String titulo = atributos.get("title");
			String urlImagem = atributos.get("image").replaceAll("(@+)(.*).jpg$", "$1.jpg");
			Conteudo conteudo = new Conteudo(titulo, urlImagem);

			conteudos.add(conteudo);
		}

		return conteudos;
	}

}
