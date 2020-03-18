package com.ccsu.zy.service;

import org.apache.struts2.convention.annotation.Action;

import com.ccsu.zy.model.SiteSysCompany;
import com.ccsu.zy.util.PersistenceHelper;
import com.opensymphony.xwork2.ModelDriven;

public class SiteSysCompanyService extends BaseAction implements ModelDriven<SiteSysCompany>{
	private static final long serialVersionUID = 1L;
	
	SiteSysCompany siteSysCompany = new SiteSysCompany();
	
	@Action("queryCompany")
	public void querySiteSysCompany() {
		writerToUiJson(PersistenceHelper.getInstance().getSiteSysCompanyDao().querySiteSysCompany(getModel()));
	}
	
	@Action("insertCompany")
	public void insertSiteSysCompany() {
		if(getModel().getParent_code() == null && getModel().getParent_code() != "0") {
			SiteSysCompany siteSysCompanyParent = new SiteSysCompany();
			siteSysCompanyParent.setCompany_code(getModel().getParent_code());
			siteSysCompanyParent.setTree_leaf("0");
			PersistenceHelper.getInstance().getSiteSysCompanyDao().updateSiteSysCompany(siteSysCompanyParent);
		}
		PersistenceHelper.getInstance().getSiteSysCompanyDao().insertSiteSysCompany(getModel());
	}
	
	@Action("deleteCompany")
	public void deleteSiteSysCompany() {
		PersistenceHelper.getInstance().getSiteSysCompanyDao().deleteSiteSysCompany(getModel());
	}
	
	@Action("updateCompany")
	public void updateSiteSysCompany() {
		PersistenceHelper.getInstance().getSiteSysCompanyDao().updateSiteSysCompany(getModel());
	}
	
	@Override
	public SiteSysCompany getModel() {
		// TODO Auto-generated method stub
		return siteSysCompany;
	}
	
}
