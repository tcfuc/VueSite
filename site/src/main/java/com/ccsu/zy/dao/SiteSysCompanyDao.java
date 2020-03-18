package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysCompany;

public interface SiteSysCompanyDao {
	public final static String SERVICE_NAME = "siteSysCompanyDao";
	
	public ArrayList<SiteSysCompany> querySiteSysCompany( SiteSysCompany siteSysCompany );
	
	public void insertSiteSysCompany( SiteSysCompany siteSysCompany );
	
	public void deleteSiteSysCompany( SiteSysCompany siteSysCompany );
	
	public void updateSiteSysCompany( SiteSysCompany siteSysCompany );
}
