/**   
* @Title: IpContainer.java 
* @Package com.spider 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-09-19 
*/
package com.spider;

import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.util.HTTPClientsUtil;

/** 
* @ClassName: IpContainer 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2019-09-19 
*/
@Component
public class IpContainer {
	private static BlockingDeque<String> innerQueue = new LinkedBlockingDeque<String>();
	
	public String get() {
		
		String proxy = innerQueue.poll();
		if(proxy == null) {
			init();
			proxy = innerQueue.poll();
		}
		
		return proxy;
	}
	
	@SuppressWarnings("unchecked")
	private void init() {
		
		//String ipStr = HTTPClientsUtil.doGet("http://proxylist.fatezero.org/proxy.list", "get", null, null, 0);
		String ipStr = HTTPClientsUtil.doGet("https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list", "get", null, null, 0);
		int num = 0;
		for(String ip : ipStr.split("\n")) {
			Map<String, Object> map = (Map<String, Object>)JSON.parse(ip);
			
			String type = (String) map.get("type");
			String host = (String) map.get("host");
			int port = (int) map.get("port");
			double response = (double) map.get("response_time");
			if("http".equalsIgnoreCase(type) && response < 1) {
				String returnStr = HTTPClientsUtil.doGet("http://icanhazip.com/", "get", null, host, port);
				if(host.equalsIgnoreCase(returnStr)) {
					innerQueue.add(host + ":" + port);
					System.out.println(host);
					num++;
				}
			}
			if(num > 10) {
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		IpContainer ipContainer = new IpContainer();
		ipContainer.init();
	}
}
