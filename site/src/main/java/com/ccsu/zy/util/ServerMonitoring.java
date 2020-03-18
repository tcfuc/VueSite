package com.ccsu.zy.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import com.ccsu.zy.model.CPUInfo;
import com.ccsu.zy.model.DiskInfo;
import com.ccsu.zy.model.ServerInfo;

public class ServerMonitoring {
	
    public static ServerInfo propertyAndMemory() throws UnknownHostException, SigarException {
    	ServerInfo serverInfos = new ServerInfo();
        Properties props = System.getProperties();
        InetAddress addr = InetAddress.getLocalHost();

        //服务器名
        serverInfos.setServerName(addr.getHostName());
        //服务器IP
        serverInfos.setServerIP(addr.getHostAddress());
        
        //Java版本
        serverInfos.setJavaVersion(props.getProperty("java.version"));
        //Java路径
        serverInfos.setInstallPath(props.getProperty("java.home"));
        //Java虚拟机名称
        serverInfos.setJavaName(props.getProperty("java.vm.name"));
        
        //操作系统名
        serverInfos.setOperaSystem(props.getProperty("os.name"));
        //系统架构
        serverInfos.setSystemArchitecture(props.getProperty("os.arch"));
        
        Sigar sigar = new Sigar();
        Mem mem = sigar.getMem();
        
        // 内存总量
        serverInfos.setTotalMemory((mem.getTotal() / 1048576L));
        // 当前内存使用量
        serverInfos.setUsedmemory((mem.getUsed() / 1048576L));
        // 当前内存剩余量
        serverInfos.setRemainMemory((mem.getFree() / 1048576L));
        
        return serverInfos;
    }
    
    public static ArrayList<DiskInfo> file() throws Exception {
    	ArrayList<DiskInfo> diskInfos = new ArrayList<DiskInfo>();
        Sigar sigar = new Sigar();
        FileSystem fslist[] = sigar.getFileSystemList();
        for (int i = 0; i < fslist.length; i++) {
        	DiskInfo diskInfo3 = new DiskInfo();
            FileSystem fs = fslist[i];
            // 分区的盘符名称
            diskInfo3.setDiskName(fs.getDirName());
            // 文件系统类型，比如 FAT32、NTFS
            diskInfo3.setDiskSysTypeName(fs.getSysTypeName());
            // 文件系统类型名，比如本地硬盘、光驱、网络文件系统等
            diskInfo3.setDiskTypeName(fs.getTypeName());
            FileSystemUsage usage = null;
            usage = sigar.getFileSystemUsage(fs.getDirName());
            if(fs.getType() == 2) {
            	// TYPE_LOCAL_DISK : 本地硬盘
                // 文件系统总大小
                diskInfo3.setDiskTotal(usage.getTotal() / 1024L);
                // 文件系统可用大小
                diskInfo3.setDiskAvail(usage.getAvail() / 1024L);
                // 文件系统已经使用量
                diskInfo3.setDiskUsed(usage.getUsed() / 1024L);
                
                double usePercent = usage.getUsePercent() * 100D;
                // 文件系统资源的利用率
                diskInfo3.setDiskUsePercent(usePercent);
            }
            diskInfos.add(diskInfo3);
        }
        return diskInfos;
    }
    
    public static ArrayList<CPUInfo> cpu() throws SigarException {
        Sigar sigar = new Sigar();
        
        ArrayList<CPUInfo> cpuInfos = new ArrayList<CPUInfo>();
        //获取CPU各线程信息
        CpuPerc cpuList[] = null;
        cpuList = sigar.getCpuPercList();
        for (int i = 0; i < cpuList.length; i++) {// 不管是单块CPU还是多CPU都适用
            CPUInfo cpuInfo = new CPUInfo();
        	//CPU总使用率
        	cpuInfo.CPUName = "cpu" + i;
        	cpuInfo.CPUCombined = cpuList[i].getCombined();// 总的使用率
        	cpuInfos.add(cpuInfo);
        }
        return cpuInfos;
    }
}
