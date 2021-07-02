package fr.eni.projet.dal;

import java.io.IOException;
import java.util.Properties;

public class Settings {
	private static Properties properties;
	
	static {
		properties=new Properties();
		try {
			properties.load(Settings.class.getResourceAsStream("settings.properties"));
		} catch (IOException e) {
			// TODO PRISCILA gérer exception
			e.printStackTrace();
		}
	}
	
	public static String getProperty(String key) {
		return properties.getProperty(key,null);
	}
}
