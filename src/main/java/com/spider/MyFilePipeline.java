/**
 * 
 */
package com.spider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.util.FileUtil;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;
import us.codecraft.webmagic.utils.FilePersistentBase;

/**
 * @desc : TODO
 * @author: Zhu
 * @date : 2017年9月26日
 */
public class MyFilePipeline extends FilePersistentBase implements Pipeline {
	private static Logger log = LoggerFactory.getLogger(MyFilePipeline.class);
	/**
	 * create a FilePipeline with default path"/data/webmagic/"
	 */
	public MyFilePipeline() {
		setPath("/data/webmagic/");
	}

	public MyFilePipeline(String path) {
		setPath(path);
	}

	@Override
	public void process(ResultItems resultItems, Task task) {
		// TODO Auto-generated method stub
		Request request = resultItems.getRequest();
		String urlStr = request.getUrl();
		String info = resultItems.get("info");

		try {

			if (!urlStr.matches(".*?/cfda$")) {
				if(info != null) {
					log.info(info);
					FileUtil.appendFile(info + "\n");
				}
			}

			log.info("Processing complete");
		} catch (Exception e) {
			log.warn("write file error", e);
		} finally {
			try {

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			log.info("----------------------------------------------------------------");
		}
	}

}
