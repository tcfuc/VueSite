package com.ccsu.zy.model;

public class DiskInfo {
	private String DiskName;
	private String DiskSysTypeName;
	private String DiskTypeName;
	
	private long DiskTotal;
	private long DiskAvail;
	private long DiskUsed;
	private double DiskUsePercent;
	public long getDiskTotal() {
		return DiskTotal;
	}
	public void setDiskTotal(long diskTotal) {
		DiskTotal = diskTotal;
	}
	public long getDiskAvail() {
		return DiskAvail;
	}
	public void setDiskAvail(long diskAvail) {
		DiskAvail = diskAvail;
	}
	public long getDiskUsed() {
		return DiskUsed;
	}
	public void setDiskUsed(long diskUsed) {
		DiskUsed = diskUsed;
	}
	
	public double getDiskUsePercent() {
		return DiskUsePercent;
	}
	public void setDiskUsePercent(double diskUsePercent) {
		DiskUsePercent = diskUsePercent;
	}
	public String getDiskName() {
		return DiskName;
	}
	public void setDiskName(String diskName) {
		DiskName = diskName;
	}
	public String getDiskSysTypeName() {
		return DiskSysTypeName;
	}
	public void setDiskSysTypeName(String diskSysTypeName) {
		DiskSysTypeName = diskSysTypeName;
	}
	public String getDiskTypeName() {
		return DiskTypeName;
	}
	public void setDiskTypeName(String diskTypeName) {
		DiskTypeName = diskTypeName;
	}
	
	
}
