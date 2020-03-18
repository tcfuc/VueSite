package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysArea;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysAreaService extends BaseAction implements ModelDriven<SiteSysArea>{
	private static final long serialVersionUID = 1L;
	
	SiteSysArea siteSysArea = new SiteSysArea();
	
	@Action("queryArea")
	public void querySiteSysCompany() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysAreaDao().querySiteSysArea(getModel()));
	}
	
	@Action("insertArea")
	public void insertSiteSysCompany() {
		if(getModel().getParent_code() == null && getModel().getParent_code() != "0") {
			SiteSysArea siteSysAreaParent = new SiteSysArea();
			siteSysAreaParent.setArea_code(getModel().getParent_code());
			siteSysAreaParent.setTree_leaf("0");
			PersistenceHelper.getInstance().getSiteSysAreaDao().updateSiteSysArea(siteSysAreaParent);
		}
		
		PersistenceHelper.getInstance().getSiteSysAreaDao().insertSiteSysArea(getModel());
	}
	
	@Action("deleteArea")
	public void deleteSiteSysCompany() {
		PersistenceHelper.getInstance().getSiteSysAreaDao().deleteSiteSysArea(getModel());
	}
	
	@Action("updateArea")
	public void updateSiteSysCompany() {
		PersistenceHelper.getInstance().getSiteSysAreaDao().updateSiteSysArea(getModel());
	}
	
	@Override
	public SiteSysArea getModel() {
		// TODO Auto-generated method stub
		return siteSysArea;
	}
}
