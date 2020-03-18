package com.ccsu.zy.dao;

import java.util.ArrayList;

import com.ccsu.zy.model.SiteSysUser;

public interface SiteSysUserDao {
	public final static String SERVICE_NAME="SiteSysUserDaoImpl";
	
	public ArrayList<SiteSysUser> querySiteSysUser ( SiteSysUser siteSysUser );
	
	public void insertSiteSysUser ( SiteSysUser siteSysUser );
	
	public void deleteSiteSysUser ( SiteSysUser siteSysUser );
	
	public void updateSiteSysUser ( SiteSysUser siteSysUser );
	
}
