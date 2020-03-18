package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysPost;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysPostService extends BaseAction implements ModelDriven<SiteSysPost>{
	private static final long serialVersionUID = 1L;

	SiteSysPost siteSysPost = new SiteSysPost();

	@Action("queryPost")
	public void querySiteSysUser() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysPostDao().querySiteSysPost(getModel()));
	}
	
	@Action("insertPost")
	public void insertSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysPostDao().insertSiteSysPost(getModel());
	}
	
	@Action("deletePost")
	public void deleteSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysPostDao().deleteSiteSysPost(getModel());
	}
	
	@Action("updatePost")
	public void updateSiteSysUser() {
		PersistenceHelper.getInstance().getSiteSysPostDao().updateSiteSysPost(getModel());
	}

	@Override
	public SiteSysPost getModel() {
		// TODO Auto-generated method stub
		return siteSysPost;
	}

}
