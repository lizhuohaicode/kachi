package com.team.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 副卡基础信息	m_channel_card
 * 创建日期：2017-12-15下午4:25:32
 * author:wuzhiheng
 */
public class ChanelCard implements Serializable{
	
	private Integer id;//主键
	
	private Long imsi;
	
	private String number;
	
	private String iccid;
	
	private Integer operatorCode;//运营商编码
	
	private Integer countryCode;//国家编码
	
	private String mcNumber;//短信中心号码
	
	private Date rechargeTime;//最后一次充值时间
	
	private Double blance;//账户余额
	
	private Integer status;//状态
	
	private String detail;//详情

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getImsi() {
		return imsi;
	}

	public void setImsi(Long imsi) {
		this.imsi = imsi;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getIccid() {
		return iccid;
	}

	public void setIccid(String iccid) {
		this.iccid = iccid;
	}

	public Integer getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(Integer operatorCode) {
		this.operatorCode = operatorCode;
	}

	public Integer getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
	}

	public String getMcNumber() {
		return mcNumber;
	}

	public void setMcNumber(String mcNumber) {
		this.mcNumber = mcNumber;
	}

	public Date getRechargeTime() {
		return rechargeTime;
	}

	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	public Double getBlance() {
		return blance;
	}

	public void setBlance(Double blance) {
		this.blance = blance;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}
	
}
