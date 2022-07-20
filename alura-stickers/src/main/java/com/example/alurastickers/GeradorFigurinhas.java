package com.example.alurastickers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradorFigurinhas {
	private static final int TAMANHO_PIXEL = 200;

	public void criar(InputStream inputStream, String nomeArquivo) throws Exception {
		// Ler imagem
		// BufferedImage original = ImageIO.read(new File("src/main/resources/imagens/entrada/TopMovies_1.jpg"));
		BufferedImage original = ImageIO.read(inputStream);
		int largura = original.getWidth();
		int altura = original.getHeight();

		// criar nova imagem transparente (em memoria)
		int novaAltura = altura + TAMANHO_PIXEL;
		BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

		// copiar imagem original para nova imagem (em memoria)
		Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
		graphics.drawImage(original, 0, 0, null);

		// Configurar fonte
		graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 64));
		graphics.setColor(Color.yellow);

		// escrever uma frase na nova imagem
		graphics.drawString("Topzeira", (largura / 2) - 100, novaAltura - 100);

		// escrever a nova imagem em um arquivo
		// ImageIO.write(novaImagem, "png", new File("src/main/resources/imagens/saida/figurinha.png"));
		ImageIO.write(novaImagem, "png", new File(nomeArquivo));
	}

}
