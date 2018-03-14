package cn.edu.scau.cmi.hombio.domain;

import java.util.Map;


public class Device {
	
	private String buildingName;
	private String 	deviceID;
	private Map<String,String> status;
	


	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getDeviceID() {
		return deviceID;
	}
	public void setDeviceID(String deviceID) {
		this.deviceID = deviceID;
	}
	public Map<String, String> getStatus() {
		return status;
	}
	public void setStatus(Map<String, String> status) {
		this.status = status;
	}
	
	
	

}
