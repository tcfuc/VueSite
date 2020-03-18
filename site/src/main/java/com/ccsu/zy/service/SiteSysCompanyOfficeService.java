package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysCompanyOffice;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysCompanyOfficeService extends BaseAction implements ModelDriven<SiteSysCompanyOffice>{
	private static final long serialVersionUID = 1L;
	
	SiteSysCompanyOffice siteSysCompanyOffice = new SiteSysCompanyOffice();
	
	@Action("queryCompanyOffice")
	public void querySiteSysCompanyOffice() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysCompanyOfficeDao().querySiteSysCompanyOffice(getModel()));
	}
	
	@Action("insertCompanyOffice")
	public void insertSiteSysCompanyOffice() {
		PersistenceHelper.getInstance().getSiteSysCompanyOfficeDao().insertSiteSysCompanyOffice(getModel());
	}
	
	@Action("deleteCompanyOffice")
	public void deleteSiteSysCompanyOffice() {
		PersistenceHelper.getInstance().getSiteSysCompanyOfficeDao().deleteSiteSysCompanyOffice(getModel());
	}
	
	@Action("updateCompanyOffice")
	public void updateSiteSysCompanyOffice() {
		PersistenceHelper.getInstance().getSiteSysCompanyOfficeDao().updateSiteSysCompanyOffice(getModel());
	}
	
	@Override
	public SiteSysCompanyOffice getModel() {
		// TODO Auto-generated method stub
		return siteSysCompanyOffice;
	}
	
}
