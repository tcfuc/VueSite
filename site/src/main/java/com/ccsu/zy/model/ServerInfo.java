package com.ccsu.zy.model;

public class ServerInfo {
	private String ServerName;
	private String OperaSystem;
	private String ServerIP;
	private String SystemArchitecture;
	
	private String JavaName;
	private String JavaVersion;
	private String InstallPath;
	
	private long TotalMemory;
	private long RemainMemory;
	private long Usedmemory;

	public long getTotalMemory() {
		return TotalMemory;
	}
	public void setTotalMemory(long totalMemory) {
		TotalMemory = totalMemory;
	}
	public long getRemainMemory() {
		return RemainMemory;
	}
	public void setRemainMemory(long remainMemory) {
		RemainMemory = remainMemory;
	}
	public long getUsedmemory() {
		return Usedmemory;
	}
	public void setUsedmemory(long usedmemory) {
		Usedmemory = usedmemory;
	}
	public String getServerName() {
		return ServerName;
	}
	public void setServerName(String serverName) {
		ServerName = serverName;
	}
	public String getOperaSystem() {
		return OperaSystem;
	}
	public void setOperaSystem(String operaSystem) {
		OperaSystem = operaSystem;
	}
	public String getServerIP() {
		return ServerIP;
	}
	public void setServerIP(String serverIP) {
		ServerIP = serverIP;
	}
	public String getSystemArchitecture() {
		return SystemArchitecture;
	}
	public void setSystemArchitecture(String systemArchitecture) {
		SystemArchitecture = systemArchitecture;
	}
	public String getJavaName() {
		return JavaName;
	}
	public void setJavaName(String javaName) {
		JavaName = javaName;
	}
	public String getJavaVersion() {
		return JavaVersion;
	}
	public void setJavaVersion(String javaVersion) {
		JavaVersion = javaVersion;
	}
	public String getInstallPath() {
		return InstallPath;
	}
	public void setInstallPath(String installPath) {
		InstallPath = installPath;
	}
	
	
}
