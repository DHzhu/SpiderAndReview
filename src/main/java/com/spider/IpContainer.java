/**   
* @Title: IpContainer.java 
* @Package com.spider 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-09-19 
*/
package com.spider;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.model.ItemIp;
import com.service.ItemIpService;
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
	@Autowired
	private ItemIpService itemIpService;
 	
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
		
		String ipStr = HTTPClientsUtil.doGet("http://proxylist.fatezero.org/proxy.list", "get", null, null, 0);
		//String ipStr = HTTPClientsUtil.doGet("https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list", "get", null, null, 0);
		int num = 0;
		if(StringUtils.isNoneEmpty(ipStr)) {
			for(String ip : ipStr.split("\n")) {
				Map<String, Object> map = (Map<String, Object>)JSON.parse(ip);
				
				String type = (String) map.get("type");
				String host = (String) map.get("host");
				int port = (int) map.get("port");
				BigDecimal response = (BigDecimal) map.get("response_time");
				
				System.out.println(String.format("%s-%s:%s-%s", type, host, port, response));
				if("http".equalsIgnoreCase(type) && response.compareTo(BigDecimal.valueOf(3)) == -1) {
					
					ItemIp itemIp = itemIpService.search(host);
					if(itemIp == null || StringUtils.isEmpty(itemIp.getIp())) {
						String returnStr = HTTPClientsUtil.doGet("http://icanhazip.com/", "get", null, host, port);
						
						if(StringUtils.isNotEmpty(returnStr)) {
							returnStr = returnStr.replaceAll("\n", "");
							System.out.println(String.format("%s:%s %s", host, returnStr, host.equalsIgnoreCase(returnStr)));
							if(host.equalsIgnoreCase(returnStr)) {
								itemIpService.save(host);
								innerQueue.add(host + ":" + port);
								num++;
							}
						}
					}
				}
				if(num > 10) {
					break;
				}
			}
		}
		
	}
	
	public static void main(String[] args) {
		IpContainer ipContainer = new IpContainer();
		ipContainer.init();
	}
}
