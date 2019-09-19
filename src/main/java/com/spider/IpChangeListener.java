/**   
* @Title: IpChangeListener.java 
* @Package com.spider 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-09-19 
*/
package com.spider;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/** 
* @ClassName: IpChangeListener 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2019-09-19 
*/
@Component
public class IpChangeListener {
	
	@EventListener
	public void listener(IpChangeEvent ipChangeEvent) {
		System.out.println("listener");
	}
}
