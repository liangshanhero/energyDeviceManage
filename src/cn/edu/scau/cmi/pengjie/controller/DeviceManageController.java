package cn.edu.scau.cmi.pengjie.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.cmi.dao.WhbuildingDAO;
import cn.edu.scau.cmi.dao.WhdeviceDAO;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdevice;
import net.sf.json.JSONObject;

/**
 * 
 * @author _TESLA_
 *
 */
@RequestMapping("/whdevice")
@Controller
public class DeviceManageController {

	private static final Log log = LogFactory.getLog(DeviceManageController.class);

	@RequestMapping
	public String index() {
		log.info("/whdevice");
		return "whdevice/devices.jsp";
	}

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String createDevice(@PathVariable Integer buildingId, @RequestBody Device device) {
		log.info(String.format("POST: /{companyId}/{projectId}/{buildingId: %d}", buildingId));
		log.info(device);

		Whbuilding whbuilding = whbuildingDAO.findWhbuildingById(buildingId);
		if (whbuilding == null) {
			return "{\"result\":\"null\"}";
		}
		Whdevice whdevice = new Whdevice();
		whdevice.setWhbuildingId(whbuilding.getId());
		whdevice.setNumber(device.getNumber());
		whdeviceDAO.store(whdevice);

		return JSONObject.fromObject(device).toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryDevice(@PathVariable Integer deviceId) {
		log.info(String.format("GET: /{companyId}/{projectId}/{buildingId}/{deviceId: %d}", deviceId));
		Whdevice device = whdeviceDAO.findWhdeviceById(deviceId);
		if (device == null) {
			return "{}";
		}
		JSONObject json = new JSONObject();
		json.put("id", device.getId());
		json.put("number", device.getNumber());

		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}", method = RequestMethod.DELETE, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteDevice(@PathVariable Integer deviceId) {
		log.info(String.format("DELETE: /{companyId}/{projectId}/{buildingId}/{deviceId: %d}", deviceId));
		Whdevice device = whdeviceDAO.findWhdeviceById(deviceId);
		if (device == null) {
			return "{\"result\":\"null\"}";
		}
		whdeviceDAO.remove(device);
		return "{\"result\":\"success\"}";
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}", method = RequestMethod.PUT, produces = "application/json; charset=utf-8")
	@ResponseBody
	public String updateDevice(@PathVariable Integer deviceId, @RequestBody Device device) {
		log.info(String.format("POST: /{companyId}/{projectId}/{buildingId}/{deviceId: %d}", deviceId));
		log.info(device);

		Whdevice whdevice = whdeviceDAO.findWhdeviceById(deviceId);
		if (whdevice == null) {
			return "{\"result\":\"null\"}";
		}
		whdevice.setNumber(device.getNumber());
		whdeviceDAO.merge(whdevice);

		return JSONObject.fromObject(device).toString();
	}

	/**
	 * 用于接收参数，自动组装成对象
	 * 
	 * @author _TESLA_
	 *
	 */
	public static class Device {

		private String number;

		public String getNumber() {
			return number;
		}

		public void setNumber(String number) {
			this.number = number;
		}

		@Override
		public String toString() {
			return "Device [number=" + number + "]";
		}

	}
}
