package com.ccsu.zy.service;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.hyperic.sigar.SigarException;

import com.ccsu.zy.model.CPUInfo;
import com.ccsu.zy.model.DiskInfo;
import com.ccsu.zy.model.ServerInfo;
import com.ccsu.zy.util.ServerMonitoring;

public class ServerMonitorService extends BaseAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	ServerInfo serverInfo = new ServerInfo();
	ArrayList<DiskInfo> diskInfo = new ArrayList<DiskInfo>();
	ArrayList<CPUInfo> cpuInfo = new ArrayList<CPUInfo>();
	
	@Action("getServerInfo")
	public void getServerInfo() {
		try {
			serverInfo = ServerMonitoring.propertyAndMemory();
			ServerMonitoring.file();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SigarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writerToUiJson(serverInfo);
	}
	
	@Action("getDiskInfo")
	public void getDiskInfo() {
		try {
			diskInfo = ServerMonitoring.file();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writerToUiJson(diskInfo);
	}
	
	@Action("getCPUInfo")
	public void getCPUInfo() {
		try {
			cpuInfo = ServerMonitoring.cpu();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writerToUiJson(cpuInfo);
	}
	
}
