package uties;

public class ConfigInfo {
	
	public String FileDirectory, License, ApplicationID, Username, Password, HostServer, PriceServer;
	public int TimeRefresh, DebugFlag;
	
	public ConfigInfo() {
		this.FileDirectory = "";
		this.License = "";
		this.ApplicationID = "";
		this.Username = "";
		this.Password = "";
		this.HostServer = "";
		this.PriceServer = "";
		this.TimeRefresh = 1000;
		this.DebugFlag = 0;
	}
	
	public ConfigInfo(
		String fileDirectory, String license, String applicationID, String username, String password, String hostServer, 
		String priceServer, int TimeRefresh, int DebugFlag
	) {
		this.FileDirectory = fileDirectory;
		this.License = license;
		this.ApplicationID = applicationID;
		this.Username = username;
		this.Password = password;
		this.HostServer = hostServer;
		this.PriceServer = priceServer;
		this.TimeRefresh = TimeRefresh;
		this.DebugFlag = DebugFlag;
	}
}
