package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysOffice;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysOfficeService extends BaseAction implements ModelDriven<SiteSysOffice>{
	private static final long serialVersionUID = 1L;
	
	SiteSysOffice siteSysOffice = new SiteSysOffice();
	
	@Action("queryOffice")
	public void querySiteSysOffice() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysOfficeDao().querySiteSysOffice(getModel()));
	}
	
	@Action("insertOffice")
	public void insertSiteSysOffice() {
		if(getModel().getParent_code() == null && getModel().getParent_code() != "0") {
			SiteSysOffice siteSysOfficeParent = new SiteSysOffice();
			siteSysOfficeParent.setOffice_code(getModel().getParent_code());
			siteSysOfficeParent.setTree_leaf("0");
			PersistenceHelper.getInstance().getSiteSysOfficeDao().updateSiteSysOffice(siteSysOfficeParent);
		}
		PersistenceHelper.getInstance().getSiteSysOfficeDao().insertSiteSysOffice(getModel());
	}
	
	@Action("deleteOffice")
	public void deleteSiteSysOffice() {
		PersistenceHelper.getInstance().getSiteSysOfficeDao().deleteSiteSysOffice(getModel());
	}
	
	@Action("updateOffice")
	public void updateSiteSysOffice() {
		PersistenceHelper.getInstance().getSiteSysOfficeDao().updateSiteSysOffice(getModel());
	}
	
	@Override
	public SiteSysOffice getModel() {
		// TODO Auto-generated method stub
		return siteSysOffice;
	}
	
}
