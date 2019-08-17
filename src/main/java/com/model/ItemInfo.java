/**   
* @Title: ItemInfo.java 
* @Package com.model 
* @Description: TODO(用一句话描述该文件做什么) 
* @author zhuyj   
* @date 2019-08-15 
*/
package com.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 
* @ClassName: ItemInfo 
* @Description: ITEM_INFO 表对应实体类
* @author: zhuyj
* @date: 2019-08-15 
*/
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("ITEM_INFO")
public class ItemInfo extends Model<ItemInfo>{

	private static final long serialVersionUID = -2406466700647121948L;
	
	private String esId;
	private String productName;
	private String approvalNum;
	private String enterprise;
	private String enterpriseAddress;
	private String approvalDate;
	private String expiredDate;
	@JsonFormat(
	    pattern = "yyyy-MM-dd HH:mm:ss",
	    timezone = "GMT+8"
	)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	@JsonFormat(
	    pattern = "yyyy-MM-dd HH:mm:ss",
	    timezone = "GMT+8"
	)
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date saveTime;

	/** 
	* @Title: pkVal 
	* @Description: 定义主键
	* @return 
	* @see com.baomidou.mybatisplus.activerecord.Model#pkVal() 
	*/
	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return this.esId;
	}
	
	@Override
	public String toString() {
		return "{\"esId\":\"" + esId + "\"," + 
				"\"productName\":\"" + productName + "\"," +  
				"\"approvalNum\":\"" + approvalNum + "\"," + 
				"\"enterprise\":\"" + enterprise + "\"," + 
				"\"enterpriseAddress\":\"" + enterpriseAddress + "\"," + 
				"\"expiredDate\":\"" + expiredDate + "\"," + 
				"\"approvalDate\":\"" + approvalDate + "\"," + 
				"\"updateTime\":\"" + updateTime + "\"," + 
				"\"saveTime\":\"" + saveTime +
				"}";
	}

}
