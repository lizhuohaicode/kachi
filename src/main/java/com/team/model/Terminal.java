package com.team.model;

import java.io.Serializable;

/**
 * 终端基础信息	m_terminal
 * 创建日期：2017-12-15下午3:38:22
 * author:wuzhiheng
 */
public class Terminal implements Serializable{

	private Integer id;//主键
	
	private Integer tsid;//编号
	
	private String mac;
	
	private String model;//终端型号
	
	private String batch;//生产批次
	
	private String sVersion;//软件版本
	
	private String key;//License
	
	private Integer status;//0:正常；1：未初始化；2：停用；3：注销
	
	private Integer upLog;//上传日志：0：不上传；1上传
	
	private String imei;
	
	private Integer activated;//是否已经激活：0：未激活；1：已激活
	
	private Integer homeLocation;//归属地，国家编码
	
	private String ssid;
	
	private String wifiPassword;//WiFi密码
	
	private String licFix;//LIC后缀
	
	private Integer usedVpn;//是否支持VPN
	
	private Integer usedSoft;//是否支持软卡
	
	private Integer departmentId;//部门ID
	
	private String medi;//WCDMA的MEID
	
	private Integer saleType;//销售类型
	
	private Integer resetWifi;//是否重置wifi：0：不重置；1：重置
	
	private String androidVersion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTsid() {
		return tsid;
	}

	public void setTsid(Integer tsid) {
		this.tsid = tsid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getsVersion() {
		return sVersion;
	}

	public void setsVersion(String sVersion) {
		this.sVersion = sVersion;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getUpLog() {
		return upLog;
	}

	public void setUpLog(Integer upLog) {
		this.upLog = upLog;
	}

	public String getImei() {
		return imei;
	}

	public void setImei(String imei) {
		this.imei = imei;
	}

	public Integer getActivated() {
		return activated;
	}

	public void setActivated(Integer activated) {
		this.activated = activated;
	}

	public Integer getHomeLocation() {
		return homeLocation;
	}

	public void setHomeLocation(Integer homeLocation) {
		this.homeLocation = homeLocation;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getWifiPassword() {
		return wifiPassword;
	}

	public void setWifiPassword(String wifiPassword) {
		this.wifiPassword = wifiPassword;
	}

	public String getLicFix() {
		return licFix;
	}

	public void setLicFix(String licFix) {
		this.licFix = licFix;
	}

	public Integer getUsedVpn() {
		return usedVpn;
	}

	public void setUsedVpn(Integer usedVpn) {
		this.usedVpn = usedVpn;
	}

	public Integer getUsedSoft() {
		return usedSoft;
	}

	public void setUsedSoft(Integer usedSoft) {
		this.usedSoft = usedSoft;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getMedi() {
		return medi;
	}

	public void setMedi(String medi) {
		this.medi = medi;
	}

	public Integer getSaleType() {
		return saleType;
	}

	public void setSaleType(Integer saleType) {
		this.saleType = saleType;
	}

	public Integer getResetWifi() {
		return resetWifi;
	}

	public void setResetWifi(Integer resetWifi) {
		this.resetWifi = resetWifi;
	}

	public String getAndroidVersion() {
		return androidVersion;
	}

	public void setAndroidVersion(String androidVersion) {
		this.androidVersion = androidVersion;
	}
	
	
}