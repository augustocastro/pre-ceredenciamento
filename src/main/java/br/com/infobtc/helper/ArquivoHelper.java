package br.com.infobtc.helper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArquivoHelper {

	private static final String CAMINHO_PASTA_RESOURCE = "src/main/resources/";

	public static String lerArquivo(String arquvivo) throws IOException {
		String file = CAMINHO_PASTA_RESOURCE + arquvivo;
		StringBuilder conteudo = new StringBuilder();
		
		FileReader ler = new FileReader(file);
		BufferedReader reader = new BufferedReader(ler);
		
		String linha;

		while ((linha = reader.readLine()) != null) {
			conteudo.append(linha);
		}

		if (reader != null) {
			reader.close();
		}
		return conteudo.toString();
	}
}
