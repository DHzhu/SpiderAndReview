/**   
* @Title: IItemInfoServiceImpl.java 
* @Package com.service.impl 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-08-15 
*/
package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.dao.ItemInfoDao;
import com.model.ItemInfo;
import com.service.IItemInfoService;

/** 
* @ClassName: IItemInfoServiceImpl 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author: zhuyj
* @date: 2019-08-15 
*/
@Service
public class ItemInfoServiceImpl implements IItemInfoService{
	
	@Autowired
	private ItemInfoDao itemInfoDao;


	/** 
	* @Title: search 
	* @Description: 根据条件查询全部
	* @return 
	* @see com.service.IItemInfoService#search() 
	*/
	@Override
	public List<ItemInfo> search(ItemInfo info) {
		// TODO Auto-generated method stub
		
		return itemInfoDao.selectList(condition(info));
	}


	/** 
	* @Title: search 
	* @Description: 根据条件分页查询
	* @param page
	* @return 
	* @see com.service.IItemInfoService#search(com.baomidou.mybatisplus.plugins.Page) 
	*/
	@Override
	public Page<ItemInfo> search(Page<ItemInfo> page , ItemInfo info) {
		// TODO Auto-generated method stub
		return page.setRecords(itemInfoDao.selectPage(page, condition(info)));
	}
	
	/** 
	* @Title: condition 
	* @Description: 配置查询条件 
	* @param info
	* @return EntityWrapper<ItemInfo>
	*/
	private EntityWrapper<ItemInfo> condition(ItemInfo info){
		EntityWrapper<ItemInfo> wrapper = new EntityWrapper<>();
		if(info != null) {
			if(info.getEsId() != null) {
				wrapper.like("APPROVAL_NUM", info.getEsId());
			}
			
			if(info.getApprovalNum() != null) {
				wrapper.like("APPROVAL_NUM", info.getApprovalNum());
			}
			
			if(info.getEnterprise() != null) {
				wrapper.like("ENTERPRISE", info.getEnterprise());
			}
		}
        wrapper.orderBy("ES_ID", false);
		return wrapper;
	}

}
