package br.com.infobtc.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Bean {

	public static class Reflection {
		public Object copiarPropriedades(Object original, Object destino)
				throws NoSuchMethodException, SecurityException, ClassNotFoundException, IllegalAccessException,
				IllegalArgumentException, InvocationTargetException {
			
			for (Method metodo : original.getClass().getMethods()) {
				if (metodo.getName().contains("get") && metodo.getName() != "getClass") {
					String nomeMetodoSetter = metodo.getName().replace("get", "set");
					Method metodoSetter = Class.forName(destino.getClass().getName()).getMethod(nomeMetodoSetter,
							metodo.getReturnType());

					metodoSetter.invoke(destino, metodo.invoke(original));
				}
			}
			return destino;
		}
	}
}
