package ar.utn.dds.modelo;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class LectorConfig {
	
	public Properties leerConfig(){
		Properties constantes = new Properties(); /*variable que lee las propiedades*/
		try { constantes.load(new FileReader("src/main/java/ar/utn/dds/config.properties"));
		}catch (IOException e) {
		 System.out.println("Error al abrir archvio de configuracion");
		} 
		
		return constantes;
	}
}
