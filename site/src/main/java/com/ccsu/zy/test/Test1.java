package com.ccsu.zy.test;

import java.util.ArrayList;

import org.hyperic.sigar.SigarException;
import org.junit.Test;

import com.ccsu.zy.model.CPUInfo;
import com.ccsu.zy.util.ServerMonitoring;

public class Test1 {

	@Test
	public void testAdd() throws SigarException {
		new ServerMonitoring();
		ArrayList<CPUInfo> cpuInfo = ServerMonitoring.cpu();;
        
		System.out.print(cpuInfo);

	}
}
