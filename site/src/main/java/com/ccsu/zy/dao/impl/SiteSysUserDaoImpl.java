package com.ccsu.zy.dao.impl;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysUserDao;
import com.ccsu.zy.model.SiteSysUser;

//注解 用来bean注入
@Repository("SiteSysUserDaoImpl")
public class SiteSysUserDaoImpl implements SiteSysUserDao {

	@Override
	public ArrayList<SiteSysUser> querySiteSysUser(SiteSysUser siteSysUser) {
		// TODO Auto-generated method stub
		SiteSysUserDao siteSysUserMapper = (SiteSysUserDao) ServiceProvider.getService("siteSysUserDao");
		return siteSysUserMapper.querySiteSysUser(siteSysUser);
	}

	@Override
	public void insertSiteSysUser(SiteSysUser siteSysUser) {
		// TODO Auto-generated method stub
		SiteSysUserDao siteSysUserMapper = (SiteSysUserDao) ServiceProvider.getService("siteSysUserDao");
		siteSysUserMapper.insertSiteSysUser(siteSysUser);
	}

	@Override
	public void deleteSiteSysUser(SiteSysUser siteSysUser) {
		// TODO Auto-generated method stub
		SiteSysUserDao siteSysUserMapper = (SiteSysUserDao) ServiceProvider.getService("siteSysUserDao");
		siteSysUserMapper.deleteSiteSysUser(siteSysUser);
	}

	@Override
	public void updateSiteSysUser(SiteSysUser siteSysUser) {
		// TODO Auto-generated method stub
		SiteSysUserDao siteSysUserMapper = (SiteSysUserDao) ServiceProvider.getService("siteSysUserDao");
		siteSysUserMapper.updateSiteSysUser(siteSysUser);
	}

}
