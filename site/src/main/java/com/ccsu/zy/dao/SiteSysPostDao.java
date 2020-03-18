package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysPost;

public interface SiteSysPostDao {
	public final static String SERVICE_NAME="SiteSysPostDaoImpl";
	
	public ArrayList<SiteSysPost> querySiteSysPost ( SiteSysPost siteSysPost );
	
	public void insertSiteSysPost ( SiteSysPost siteSysPost );
	
	public void deleteSiteSysPost ( SiteSysPost siteSysPost );
	
	public void updateSiteSysPost ( SiteSysPost siteSysPost );
}
