package br.com.precredenciamento.util;

import java.util.Random;

public class CodeUtil {

	private static Random rand = new Random();
	private static char[] letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZÁÉÍÓÚÃÕÂÊÎÔÛÀÈÌÒÙÇ".toCharArray();

	public static String gerarCodigo() {
		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < 6; i++) {
			int ch = rand.nextInt(letras.length);
			sb.append(letras[ch]);
		}

		return sb.toString();
	}
}
