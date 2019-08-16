/**   
* @Title: BaseController.java 
* @Package com.dao.controller 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-08-15 
*/
package com.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.baomidou.mybatisplus.plugins.Page;
import com.model.ItemInfo;
import com.service.IItemInfoService;

/**
 * @ClassName: BaseController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author: zhuyj
 * @date: 2019-08-15
 */
@Controller
@RequestMapping("/base")
public class BaseController {
	
	@Autowired
	private IItemInfoService iItemInfoService;

	@RequestMapping(method = RequestMethod.GET, path = "/search")
	@ResponseBody
	public Map<String, Object> search(ItemInfo info) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", iItemInfoService.search(info));
		return map;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/searchByPage")
	@ResponseBody
	public Map<String, Object> search(Integer pageNum, Integer pageSize, ItemInfo info) {
		Map<String, Object> map = new HashMap<String, Object>();
		Page<ItemInfo> page = new Page<>(pageNum == null ? 0 : pageNum, pageSize == null ? 10 : pageSize);
		map.put("result", iItemInfoService.search(page, info));
		return map;
	}
}
