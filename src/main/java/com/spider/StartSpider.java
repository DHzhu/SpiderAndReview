/**   
* @Title: StartSpider.java 
* @Package com.spider 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-08-17 
*/
package com.spider;

import com.config.SpiderProperties;

import us.codecraft.webmagic.Spider;
/** 
* @ClassName: StartSpider 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2019-08-17 
*/
public class StartSpider implements Runnable{
	
	private SpiderProperties spiderProperties;
	
	public StartSpider(SpiderProperties spiderProperties) {
		this.spiderProperties = spiderProperties;
	}
	
	@SuppressWarnings("resource")
	@Override
	public void run() {
		Spider.create(new CfdaProcessor())
		.addUrl(spiderProperties.getStarUrl())
		.addPipeline(new MyFilePipeline(spiderProperties.getSavePath()))
		.setDownloader(new CfdaSeleniumDownloader()
							.setSleepTime(Integer.valueOf(spiderProperties.getSleepTime())))
		.thread(Integer.valueOf(spiderProperties.getThreadNum()))
		.run();
	}

}
