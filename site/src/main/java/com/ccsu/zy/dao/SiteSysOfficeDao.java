package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysOffice;

public interface SiteSysOfficeDao {
	public final static String SERVICE_NAME="SiteSysOfficeDaoImpl";
	
	public ArrayList<SiteSysOffice> querySiteSysOffice ( SiteSysOffice siteSysOffice );
	
	public void insertSiteSysOffice ( SiteSysOffice siteSysOffice );
	
	public void deleteSiteSysOffice ( SiteSysOffice siteSysOffice );
	
	public void updateSiteSysOffice ( SiteSysOffice siteSysOffice );

}
