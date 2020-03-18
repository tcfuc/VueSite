package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysUser;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysUserService extends BaseAction implements ModelDriven<SiteSysUser> {
	private static final long serialVersionUID = 1L;

	SiteSysUser siteSysUser = new SiteSysUser();

	@Action("queryUser")
	public void querySiteSysUser() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysUserDao().querySiteSysUser(getModel()));
	}
	
	@Action("insertUser")
	public void insertSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysUserDao().insertSiteSysUser(getModel());
	}
	
	@Action("deleteUser")
	public void deleteSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysUserDao().deleteSiteSysUser(getModel());
	}
	
	@Action("updateUser")
	public void updateSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysUserDao().updateSiteSysUser(getModel());
	}

	@Override
	public SiteSysUser getModel() {
		// TODO Auto-generated method stub
		return siteSysUser;
	}
}
