/**   
* @Title: IpChangeEvent.java 
* @Package com.spider 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-09-19 
*/
package com.spider;

import org.springframework.context.ApplicationEvent;

/** 
* @ClassName: IpChangeEvent 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2019-09-19 
*/
public class IpChangeEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	/** 
	* <p>Title: </p> 
	* <p>Description: </p> 
	* @param source 
	*/
	public IpChangeEvent(Object source) {
		super(source);
		// TODO Auto-generated constructor stub
	}

}
