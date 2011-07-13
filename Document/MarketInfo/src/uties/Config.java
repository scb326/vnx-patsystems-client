package uties;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Properties;

public class Config {
	
	private static final String ConfigFile = "Config.xml";
	private static final String token = ";";
	public static ArrayList<String> ListDirectory;
	
	public static boolean SaveSettings(ConfigInfo info) {
		try {
			CryptoLibrary cryptor = new CryptoLibrary();
			Properties props = new Properties();
			
	    	props.setProperty(ConfigField.FileDirectory, info.FileDirectory);
	    	props.setProperty(ConfigField.ApplicationID, info.ApplicationID);
	    	props.setProperty(ConfigField.License, info.License);
	    	props.setProperty(ConfigField.Username, info.Username);
	    	props.setProperty(ConfigField.Password, cryptor.encrypt(info.Password));
	    	props.setProperty(ConfigField.HostServer, info.HostServer);
	    	props.setProperty(ConfigField.PriceServer, info.PriceServer);
	    	props.setProperty(ConfigField.TimeRefresh, Integer.toString(info.TimeRefresh));
	    	props.setProperty(ConfigField.DebugFlag, Integer.toString(info.DebugFlag));	    	
	    	
	    	OutputStream os = new FileOutputStream(ConfigFile);
	    	props.storeToXML(os, "","UTF-8");
	    	return true;

		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ConfigInfo LoadSettings()
	{
		ConfigInfo info = new ConfigInfo();
		
		try {
			CryptoLibrary cryptor = new CryptoLibrary();
			Properties props = new Properties();		 
	    	InputStream is = new FileInputStream(ConfigFile);
	    	props.loadFromXML(is); 
	    	
	    	info.FileDirectory = props.getProperty(ConfigField.FileDirectory);
	    	info.ApplicationID = props.getProperty(ConfigField.ApplicationID);
	    	info.License = props.getProperty(ConfigField.License);
	    	info.Username = props.getProperty(ConfigField.Username);
	    	info.Password = cryptor.decrypt(props.getProperty(ConfigField.Password));
	    	info.HostServer = props.getProperty(ConfigField.HostServer);
	    	info.PriceServer = props.getProperty(ConfigField.PriceServer);
	    	info.TimeRefresh = Integer.parseInt(props.getProperty(ConfigField.TimeRefresh));
	    	info.DebugFlag = Integer.parseInt(props.getProperty(ConfigField.DebugFlag));
	    	
	    	ListDirectory = new ArrayList<String>();
	    	String[] list = info.FileDirectory.split(token);
	    	
	    	for(int i = 0; i < list.length; i++) {
	    		ListDirectory.add(list[i]);
	    	}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return info;
	}
}
