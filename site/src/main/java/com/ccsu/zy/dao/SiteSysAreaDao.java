package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysArea;

public interface SiteSysAreaDao {
	public final static String SERVICE_NAME = "siteSysAreaDao";
	
	public ArrayList<SiteSysArea> querySiteSysArea( SiteSysArea siteSysArea );
	
	public void insertSiteSysArea( SiteSysArea siteSysArea );
	
	public void deleteSiteSysArea( SiteSysArea siteSysArea );
	
	public void updateSiteSysArea( SiteSysArea siteSysArea );
}
