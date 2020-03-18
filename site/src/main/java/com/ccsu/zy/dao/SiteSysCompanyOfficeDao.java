package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysCompanyOffice;

public interface SiteSysCompanyOfficeDao {
	public final static String SERVICE_NAME = "siteSysCompanyOfficeDao";
	
	public ArrayList<SiteSysCompanyOffice> querySiteSysCompanyOffice( SiteSysCompanyOffice siteSysCompanyOffice );
	
	public void insertSiteSysCompanyOffice( SiteSysCompanyOffice siteSysCompanyOffice );
	
	public void deleteSiteSysCompanyOffice( SiteSysCompanyOffice siteSysCompanyOffice );
	
	public void updateSiteSysCompanyOffice( SiteSysCompanyOffice siteSysCompanyOffice );
}
