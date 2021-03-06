package com.team.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 副卡基础信息	m_channel_card
 * 创建日期：2017-12-15下午4:25:32
 * author:wuzhiheng
 */
public class ChannelCard implements Serializable{
	
	private Integer id;//主键
	
	private Long imsi;
	
	private String number;
	
	private String iccid;
	
	private Integer operatorCode;//运营商编码
	
	private Integer countryCode;//国家编码
	
	private String mcNumber;//短信中心号码
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date rechargeTime;//最后一次充值时间
	
	private Double balance;//账户余额
	
	private Integer status;//状态
	
	private String detail;//详情

	private Integer tsid;//设备号
	
	private String countryName;//国家名字
	
	private String operatorName;//运营商名称
	
	private String departmentName;
	
	public ChannelCard() {
		super();
	}

	public ChannelCard(Long imsi, String number, String iccid,
			Integer operatorCode, Integer countryCode, String mcNumber,
			Date rechargeTime, Double balance, Integer status, String detail,Integer tsid) {
		super();
		this.imsi = imsi;
		this.number = number;
		this.iccid = iccid;
		this.operatorCode = operatorCode;
		this.countryCode = countryCode;
		this.mcNumber = mcNumber;
		this.rechargeTime = rechargeTime;
		this.balance = balance;
		this.status = status;
		this.detail = detail;
		this.tsid = tsid;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

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

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
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

	public Integer getTsid() {
		return tsid;
	}

	public void setTsid(Integer tsid) {
		this.tsid = tsid;
	}
}
