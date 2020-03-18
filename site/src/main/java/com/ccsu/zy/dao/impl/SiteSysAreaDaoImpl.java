package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysAreaDao;
import com.ccsu.zy.model.SiteSysArea;

@Repository("SiteSysAreaDaoImpl")
public class SiteSysAreaDaoImpl implements SiteSysAreaDao{

	@Override
	public ArrayList<SiteSysArea> querySiteSysArea(SiteSysArea siteSysArea) {
		// TODO Auto-generated method stub
		SiteSysAreaDao siteSysAreaMapper = (SiteSysAreaDao) ServiceProvider.getService("siteSysAreaDao");
		return siteSysAreaMapper.querySiteSysArea(siteSysArea);
	}

	@Override
	public void insertSiteSysArea(SiteSysArea siteSysArea) {
		// TODO Auto-generated method stub
		SiteSysAreaDao siteSysAreaMapper = (SiteSysAreaDao) ServiceProvider.getService("siteSysAreaDao");
		siteSysAreaMapper.insertSiteSysArea(siteSysArea);
	}

	@Override
	public void deleteSiteSysArea(SiteSysArea siteSysArea) {
		// TODO Auto-generated method stub
		SiteSysAreaDao siteSysAreaMapper = (SiteSysAreaDao) ServiceProvider.getService("siteSysAreaDao");
		siteSysAreaMapper.deleteSiteSysArea(siteSysArea);
	}

	@Override
	public void updateSiteSysArea(SiteSysArea siteSysArea) {
		// TODO Auto-generated method stub
		SiteSysAreaDao siteSysAreaMapper = (SiteSysAreaDao) ServiceProvider.getService("siteSysAreaDao");
		siteSysAreaMapper.updateSiteSysArea(siteSysArea);
	}
}
