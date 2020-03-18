package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysOfficeDao;
import com.ccsu.zy.model.SiteSysOffice;

@Repository("SiteSysOfficeDaoImpl")
public class SiteSysOfficeDaoImpl implements SiteSysOfficeDao{

	@Override
	public ArrayList<SiteSysOffice> querySiteSysOffice(SiteSysOffice siteSysOffice) {
		// TODO Auto-generated method stub
		SiteSysOfficeDao siteSysOfficeMapper = (SiteSysOfficeDao) ServiceProvider.getService("siteSysOfficeDao");
		return siteSysOfficeMapper.querySiteSysOffice(siteSysOffice);
	}

	@Override
	public void insertSiteSysOffice(SiteSysOffice siteSysOffice) {
		// TODO Auto-generated method stub
		SiteSysOfficeDao siteSysOfficeMapper = (SiteSysOfficeDao) ServiceProvider.getService("siteSysOfficeDao");
		siteSysOfficeMapper.insertSiteSysOffice(siteSysOffice);
		
	}

	@Override
	public void deleteSiteSysOffice(SiteSysOffice siteSysOffice) {
		// TODO Auto-generated method stub
		SiteSysOfficeDao siteSysOfficeMapper = (SiteSysOfficeDao) ServiceProvider.getService("siteSysOfficeDao");
		siteSysOfficeMapper.deleteSiteSysOffice(siteSysOffice);
		
	}

	@Override
	public void updateSiteSysOffice(SiteSysOffice siteSysOffice) {
		// TODO Auto-generated method stub
		SiteSysOfficeDao siteSysOfficeMapper = (SiteSysOfficeDao) ServiceProvider.getService("siteSysOfficeDao");
		siteSysOfficeMapper.updateSiteSysOffice(siteSysOffice);
		
	}

}
