package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysPostDao;
import com.ccsu.zy.model.SiteSysPost;

//注解 用来bean注入
@Repository("SiteSysPostDaoImpl")
public class SiteSysPostDaoImpl implements SiteSysPostDao{

	@Override
	public ArrayList<SiteSysPost> querySiteSysPost(SiteSysPost SiteSysPost) {
		// TODO Auto-generated method stub
		SiteSysPostDao siteSysPostDaoMapper = (SiteSysPostDao) ServiceProvider.getService("siteSysPostDao");
		return siteSysPostDaoMapper.querySiteSysPost(SiteSysPost);
	}

	@Override
	public void insertSiteSysPost(SiteSysPost SiteSysPost) {
		// TODO Auto-generated method stub
		SiteSysPostDao siteSysPostDaoMapper = (SiteSysPostDao) ServiceProvider.getService("siteSysPostDao");
		siteSysPostDaoMapper.insertSiteSysPost(SiteSysPost);
	}

	@Override
	public void deleteSiteSysPost(SiteSysPost SiteSysPost) {
		// TODO Auto-generated method stub
		SiteSysPostDao siteSysPostDaoMapper = (SiteSysPostDao) ServiceProvider.getService("siteSysPostDao");
		siteSysPostDaoMapper.deleteSiteSysPost(SiteSysPost);
	}

	@Override
	public void updateSiteSysPost(SiteSysPost SiteSysPost) {
		// TODO Auto-generated method stub
		SiteSysPostDao siteSysPostDaoMapper = (SiteSysPostDao) ServiceProvider.getService("siteSysPostDao");
		siteSysPostDaoMapper.updateSiteSysPost(SiteSysPost);
	}
	
}
