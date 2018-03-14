package cn.edu.scau.cmi.pengjie.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.cmi.dao.CacdeviceDAO;
import cn.edu.scau.cmi.dao.CacdevicedataDAO;
import cn.edu.scau.cmi.dao.CacsensorDAO;
import cn.edu.scau.cmi.dao.CacsensordataDAO;
import cn.edu.scau.cmi.dao.LedmeterDAO;
import cn.edu.scau.cmi.dao.LedmeterdataDAO;
import cn.edu.scau.cmi.dao.ProjectDAO;
import cn.edu.scau.cmi.dao.WhdatatypeDAO;
import cn.edu.scau.cmi.dao.WhdeviceDAO;
import cn.edu.scau.cmi.dao.WhdevicedataDAO;
import cn.edu.scau.cmi.domain.Cac;
import cn.edu.scau.cmi.domain.Cacdevice;
import cn.edu.scau.cmi.domain.Cacdevicedata;
import cn.edu.scau.cmi.domain.Cacsensor;
import cn.edu.scau.cmi.domain.Cacsensordata;
import cn.edu.scau.cmi.domain.Ledbuilding;
import cn.edu.scau.cmi.domain.Ledmeter;
import cn.edu.scau.cmi.domain.Ledmeterdata;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdatatype;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.domain.Whdevicedata;
import cn.edu.scau.cmi.hombio.domain.Device;
import cn.edu.scau.cmi.hombio.domain.DeviceData;

@Controller("DeviceController")
public class DeviceController {
	
	@Autowired 
	ProjectDAO projectDao;
	@Autowired
	WhdeviceDAO whdeviceDAO;
	@Autowired
	LedmeterDAO ledmeterDAO;
	@Autowired
	CacsensorDAO cacsensorDAO;
	@Autowired
	CacdeviceDAO cacdeviceDAO;
	@Autowired
	WhdevicedataDAO whdevicedataDAO;
	@Autowired
	LedmeterdataDAO ledmeterdataDAO;
	@Autowired
	CacsensordataDAO cacsensordataDAO;
	@Autowired
	CacdevicedataDAO cacdevicedataDAO;
	@Autowired
	ProjectDAO projectDAO;
	@Autowired
	WhdatatypeDAO whdatatypeDAO;
	
	
	
	@SuppressWarnings("unchecked")
	private  List<Whdevicedata> findWhdeviceWhdevicedatas(int whdeviceId, int whdataTypeID,int start, int max){
		String sql = "from Whdevicedata where whdevice = "+whdeviceId+" and whdatatype="+whdataTypeID;
		return whdevicedataDAO.createQuery(sql, start, max).getResultList();
	}
	
	@SuppressWarnings("unchecked")
	private  String getNewestStatusValue(int whdeviceId, int dataTypeID){
		String sql = "from Whdevicedata where whdevice = "+whdeviceId+" and whdatatype="+dataTypeID+" order by time desc";
		List<Whdevicedata> whdevicedataList = whdevicedataDAO.createQuery(sql, 0, 1).getResultList();
		if(whdevicedataList.size()!=0){
			return whdevicedataList.get(0).getValue().toString();
		}else{
			return null;
		}
	    
	}
	
	@SuppressWarnings("unchecked")
	private  String getNewestStatusValue(int ledmeterId){
		String sql = "from Ledmeterdata where ledmeter = "+ledmeterId+"  order by time desc";
		List<Ledmeterdata> ledmeterdataList = ledmeterdataDAO.createQuery(sql, 0, 1).getResultList();
		if(ledmeterdataList.size()!=0){
			return ledmeterdataList.get(0).getValue().toString();
		}else{
			return null;
		}
	    
	}
	
	@SuppressWarnings("unchecked")
	private  String getNewestStatusValue(int cacsensorId, String flag){
		String sql = "from Cacsensordata where cacsensor = "+cacsensorId+" order by cacrecordtime desc";
		List<Cacsensordata> cacsensordataList = cacsensordataDAO.createQuery(sql, 0, 1).getResultList();
		if(cacsensordataList.size()!=0){
			return cacsensordataList.get(0).getValue().toString();
		}else{
			return null;
		}
	    
	}
	
	@SuppressWarnings("unchecked")
	private  String getNewestStatusValue(int cacdeviceId, String flag, String flag1){
		String sql = "from Cacdevicedata where cacdevice = "+cacdeviceId+" order by cacrecordtime desc";
		List<Cacdevicedata> cacdevicedataList = cacdevicedataDAO.createQuery(sql, 0, 1).getResultList();
		if(cacdevicedataList.size()!=0){
			return cacdevicedataList.get(0).getValue().toString();
		}else{
			return null;
		}
	}
	
	@RequestMapping(value = "/getAllDevicesFromPage", method = RequestMethod.POST)
	public void getAllDevicesFromPage(HttpServletRequest request){
		
		
	}
	
	

	@RequestMapping(value = "/getAllDivices", method = RequestMethod.POST)
    @ResponseBody
	public List<Device> getAllDivices(HttpServletRequest request){
//		String projectName = request.getParameter("projectName");
//		Integer projectId = new Integer(request.getParameter("id"));

		//pj添加的内容===============start========
		
//	    Integer pageProjectId = new Integer(request.getParameter("projectID"));
//		Project pageProject=projectDAO.findProjectById(pageProjectId);
//		//通过获取项目的类型来判断是属于那种类型，“LED”，“热水系统”，“中央空调”
//		String projectType=pageProject.getType();
//		System.out.println("projectType:"+projectType);
//		
//		//pj添加的内容==============end=======
		
		Integer projectId = new Integer(request.getParameter("projectID"));
		Project project = projectDAO.findProjectById(projectId);
		List<Device> deviceList = new ArrayList<Device>();
		switch(project.getType()){
		case "热水" :
			Set<Whbuilding> whbuildingSet = project.getRelativeWhbuildings();
			Set<Whdevice> whdeviceSet = new LinkedHashSet<Whdevice>();
			for (Whbuilding whbuilding : whbuildingSet) {
				whdeviceSet.addAll(whbuilding.getRelativeWhdevices());
			}
			for (Whdevice whdevice : whdeviceSet) {
				for (Whdatatype whdatatype : whdevice.getWhdatatypes()) {
					Device device = new Device();
					device.setBuildingName(whdevice.getWhbuilding().getName());
					device.setDeviceID(whdevice.getNumber());
					Map<String,String> statusMap = new HashMap<String, String>();
					statusMap.put(whdatatype.getName(),getNewestStatusValue(whdevice.getId(), whdatatype.getId()));
					device.setStatus(statusMap);
					deviceList.add(device); 
				}
			}
			break;
			
		case "LED" :
			Set<Ledbuilding> ledbuildingSet = project.getRelativeLedbuildings();
			Set<Ledmeter> ledmeterSet = new LinkedHashSet<Ledmeter>();
			for (Ledbuilding ledbuilding : ledbuildingSet) {
				ledmeterSet.addAll(ledbuilding.getRelativeLedmeters());
			}
			for (Ledmeter ledmeter : ledmeterSet) {
				Device device = new Device();
				device.setBuildingName(ledmeter.getRelativeLedbuilding().getName());
				device.setDeviceID(ledmeter.getNumber());
				Map<String,String> statusMap = new HashMap<String, String>();
				statusMap.put("最新值",getNewestStatusValue(ledmeter.getId()));
				device.setStatus(statusMap);
				deviceList.add(device); 
			}
			break;
			
		case "中央空调" : 
			Set<Cacdevice> cacDeviceSet = new LinkedHashSet<Cacdevice>();
			for (Cac cac : project.getRelativeCacs()) {
				cacDeviceSet.addAll(cac.getRelativeCacdevices());
			}
			for (Cacdevice cacDevice : cacDeviceSet) {
				Device device = new Device();
				device.setDeviceID(cacDevice.getName());
				Map<String,String> statusMap = new HashMap<String, String>();
				statusMap.put("最新值",getNewestStatusValue(cacDevice.getId(),"","")+" "+cacDevice.getUnit());
				device.setStatus(statusMap);
				deviceList.add(device); 
			}
			Set<Cacsensor> cacsensorSet = new LinkedHashSet<Cacsensor>();
			for (Cac cac : project.getRelativeCacs()) {
				cacsensorSet.addAll(cac.getRelativeCacsensors());
			}
			for (Cacsensor cacsensor : cacsensorSet) {
				Device device = new Device();
				device.setDeviceID(cacsensor.getName());
				Map<String,String> statusMap = new HashMap<String, String>();
				statusMap.put("最新值",getNewestStatusValue(cacsensor.getId(),""));
				device.setStatus(statusMap);
				deviceList.add(device); 
			}
			
			break;

		}
		
		return deviceList;
	}

	@RequestMapping(value = "/getDeviceData", method = RequestMethod.POST)
	@ResponseBody
	public List<DeviceData> getDeviceData(HttpServletRequest request){
		int start = new Integer(request.getParameter("start"));
		int max = new Integer(request.getParameter("max"));
		String deviceID = request.getParameter("deviceID");
		String dataType = request.getParameter("dataType");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		List<DeviceData> deviceDataList = new ArrayList<>();
		if(whdeviceDAO.findWhdeviceByNumber(deviceID).size()!=0){
			Integer whdeviceId = null;
			for (Whdevice whdevice: whdeviceDAO.findWhdeviceByNumber(deviceID)) {
				whdeviceId = whdevice.getId();
			}
			Integer dataTypeId = null;
			for (Whdatatype whdatatype : whdatatypeDAO.findWhdatatypeByName(dataType)) {
				dataTypeId = whdatatype.getId();
				break;
			}

			List<Whdevicedata> whdevicedataSet = findWhdeviceWhdevicedatas(whdeviceId,dataTypeId, start, max);
			
			for (Whdevicedata whdevicedata : whdevicedataSet) {
				DeviceData deviceData = new DeviceData();
				deviceData.setTime(sdf.format(whdevicedata.getTime()));
				deviceData.setValue(whdevicedata.getValue().toString());
				deviceDataList.add(deviceData);
			}
		}
		if(ledmeterDAO.findLedmeterByNumber(deviceID).size()!=0){
			Integer ledmeterId = null;
			for (Ledmeter ledmeter : ledmeterDAO.findLedmeterByNumber(deviceID)) {
				ledmeterId = ledmeter.getId();
			}
			Set<Ledmeterdata> ledmeterdataSet = 
					ledmeterdataDAO.findLedmeterLedmeterdatas(ledmeterId, start, max);
			for (Ledmeterdata ledmeterdata : ledmeterdataSet) {
				DeviceData deviceData = new DeviceData();
				deviceData.setTime(sdf.format(ledmeterdata.getTime()));
				deviceData.setValue(ledmeterdata.getValue().toString());
				deviceDataList.add(deviceData);
			}
		}
		Set<Cacdevice> cacdeviceSet = cacdeviceDAO.findCacdeviceByName(deviceID);
		if(cacdeviceSet.size()!=0){
			Integer cacdeviceId = null;
			for (Cacdevice cacdevice : cacdeviceSet) {
				cacdeviceId = cacdevice.getId();
				break;
			};
			
			List<Cacdevicedata> cacdevicedataSet = 
					cacdevicedataDAO.findCacdevicedataByCacdevice1(cacdeviceId, start, max);
			for (Cacdevicedata cacdevicedata : cacdevicedataSet) {
				DeviceData deviceData = new DeviceData();
				deviceData.setTime(sdf.format(cacdevicedata.getRelativeCacrecordtime().getRecordTime()));
				deviceData.setValue(cacdevicedata.getValue().toString());
				deviceDataList.add(deviceData);
			}
		}
		
		Set<Cacsensor> cacsensorSet = cacsensorDAO.findCacsensorByName(deviceID);
		if(cacsensorSet.size()!=0){
			Integer cacsensorId = null;
			for (Cacsensor cacsensor : cacsensorSet) {
				cacsensorId = cacsensor.getId();
				break;
			};
			List<Cacsensordata> cacsensordataSet = 
					cacsensordataDAO.findCacsensordataByCacsensor1(cacsensorId, start, max);
			for (Cacsensordata cacsensordata : cacsensordataSet) {
				DeviceData deviceData = new DeviceData();
				deviceData.setTime(sdf.format(cacsensordata.getRelativeCacrecordtime().getRecordTime()));
				if(cacsensordata.getValue().equals("亮")){
					deviceData.setValue("1");
				}else{
					deviceData.setValue("0");
				}
				deviceDataList.add(deviceData);
			}
		}
		System.out.println(deviceDataList.size());
		return deviceDataList;
	}
	

}
