package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysCompanyDao;
import com.ccsu.zy.model.SiteSysCompany;

@Repository("SiteSysCompanyDaoImpl")
public class SiteSysCompanyDaoImpl implements SiteSysCompanyDao{

	@Override
	public ArrayList<SiteSysCompany> querySiteSysCompany(SiteSysCompany siteSysCompany) {
		// TODO Auto-generated method stub
		SiteSysCompanyDao siteSysCompanyMapper = (SiteSysCompanyDao) ServiceProvider.getService("siteSysCompanyDao");
		return siteSysCompanyMapper.querySiteSysCompany(siteSysCompany);
	}

	@Override
	public void insertSiteSysCompany(SiteSysCompany siteSysCompany) {
		// TODO Auto-generated method stub
		SiteSysCompanyDao siteSysCompanyMapper = (SiteSysCompanyDao) ServiceProvider.getService("siteSysCompanyDao");
		siteSysCompanyMapper.insertSiteSysCompany(siteSysCompany);
	}

	@Override
	public void deleteSiteSysCompany(SiteSysCompany siteSysCompany) {
		// TODO Auto-generated method stub
		SiteSysCompanyDao siteSysCompanyMapper = (SiteSysCompanyDao) ServiceProvider.getService("siteSysCompanyDao");
		siteSysCompanyMapper.deleteSiteSysCompany(siteSysCompany);
	}

	@Override
	public void updateSiteSysCompany(SiteSysCompany siteSysCompany) {
		// TODO Auto-generated method stub
		SiteSysCompanyDao siteSysCompanyMapper = (SiteSysCompanyDao) ServiceProvider.getService("siteSysCompanyDao");
		siteSysCompanyMapper.updateSiteSysCompany(siteSysCompany);
	}

}
