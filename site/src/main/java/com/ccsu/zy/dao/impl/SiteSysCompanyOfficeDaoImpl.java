package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysCompanyOfficeDao;
import com.ccsu.zy.model.SiteSysCompanyOffice;

@Repository("SiteSysCompanyOfficeDaoImpl")
public class SiteSysCompanyOfficeDaoImpl implements SiteSysCompanyOfficeDao{

	@Override
	public ArrayList<SiteSysCompanyOffice> querySiteSysCompanyOffice(SiteSysCompanyOffice siteSysCompanyOffice) {
		// TODO Auto-generated method stub
		SiteSysCompanyOfficeDao siteSysCompanyOfficeMapper = (SiteSysCompanyOfficeDao) ServiceProvider.getService("siteSysCompanyOfficeDao");
		return siteSysCompanyOfficeMapper.querySiteSysCompanyOffice(siteSysCompanyOffice);
	}

	@Override
	public void insertSiteSysCompanyOffice(SiteSysCompanyOffice siteSysCompanyOffice) {
		// TODO Auto-generated method stub
		SiteSysCompanyOfficeDao siteSysCompanyOfficeMapper = (SiteSysCompanyOfficeDao) ServiceProvider.getService("siteSysCompanyOfficeDao");
		siteSysCompanyOfficeMapper.insertSiteSysCompanyOffice(siteSysCompanyOffice);
	}

	@Override
	public void deleteSiteSysCompanyOffice(SiteSysCompanyOffice siteSysCompanyOffice) {
		// TODO Auto-generated method stub
		SiteSysCompanyOfficeDao siteSysCompanyOfficeMapper = (SiteSysCompanyOfficeDao) ServiceProvider.getService("siteSysCompanyOfficeDao");
		siteSysCompanyOfficeMapper.deleteSiteSysCompanyOffice(siteSysCompanyOffice);
	}

	@Override
	public void updateSiteSysCompanyOffice(SiteSysCompanyOffice siteSysCompanyOffice) {
		// TODO Auto-generated method stub
		SiteSysCompanyOfficeDao siteSysCompanyOfficeMapper = (SiteSysCompanyOfficeDao) ServiceProvider.getService("siteSysCompanyOfficeDao");
		siteSysCompanyOfficeMapper.updateSiteSysCompanyOffice(siteSysCompanyOffice);
	}

}
