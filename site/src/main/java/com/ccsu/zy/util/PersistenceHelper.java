
package com.ccsu.zy.util;

import com.ccsu.zy.container.ServiceProvider;
import com.ccsu.zy.dao.SiteSysAreaDao;
import com.ccsu.zy.dao.SiteSysCompanyDao;
import com.ccsu.zy.dao.SiteSysCompanyOfficeDao;
import com.ccsu.zy.dao.SiteSysOfficeDao;
import com.ccsu.zy.dao.SiteSysPostDao;
import com.ccsu.zy.dao.SiteSysUserDao;

public class PersistenceHelper
{
    private static PersistenceHelper instance = null;

    private PersistenceHelper()
    {
    }

    public static PersistenceHelper getInstance()
    {
        if ( null == instance )
        {
            instance = new PersistenceHelper();
        }
        return instance;
    }
	
    public SiteSysCompanyDao getSiteSysCompanyDao() {
		return (SiteSysCompanyDao)ServiceProvider.getService(SiteSysCompanyDao.SERVICE_NAME);
	}
    
    public SiteSysCompanyOfficeDao getSiteSysCompanyOfficeDao() {
		return (SiteSysCompanyOfficeDao)ServiceProvider.getService(SiteSysCompanyOfficeDao.SERVICE_NAME);
	}
    
    public SiteSysOfficeDao getSiteSysOfficeDao() {
		return (SiteSysOfficeDao)ServiceProvider.getService(SiteSysOfficeDao.SERVICE_NAME);
	}
    
	public SiteSysPostDao getSiteSysPostDao() {
		return (SiteSysPostDao)ServiceProvider.getService(SiteSysPostDao.SERVICE_NAME);
	}
    
    public SiteSysUserDao getSiteSysUserDao() {
		return (SiteSysUserDao)ServiceProvider.getService(SiteSysUserDao.SERVICE_NAME);
	}
	
	public SiteSysAreaDao getSiteSysAreaDao() {
		return (SiteSysAreaDao)ServiceProvider.getService(SiteSysAreaDao.SERVICE_NAME);
	}
	
}
