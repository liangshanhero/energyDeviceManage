package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhdatatypeDAO;

import cn.edu.scau.cmi.service.WhdatatypeService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;
import cn.edu.scau.cmi.service.*;
import cn.edu.scau.cmi.web.*;
import org.springframework.web.bind.annotation.*;

@Controller("WhdatatypeRestController")
public class WhdatatypeRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int whdevicePageNumber = 0;
	static int whdevicePageSize = 10;
	static int whdevicedataPageNumber = 0;
	static int whdevicedataPageSize = 10;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdatatypeService whdatatypeService;

	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdatatype(@PathVariable Integer whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(whdatatype_id);
		whdatatypeService.deleteWhdatatype(whdatatype);
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevices/{relative_whdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdatatypeWhdevices(@PathVariable Integer whdatatype_id,
			@PathVariable Integer relative_whdevice_id) {
		whdatatypeService.deleteWhdatatypeWhdevices(whdatatype_id,
				relative_whdevice_id);
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevicedatas/{relative_whdevicedata_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdatatypeWhdevicedatas(
			@PathVariable Integer whdatatype_id,
			@PathVariable Integer relative_whdevicedata_id) {
		whdatatypeService.deleteWhdatatypeWhdevicedatas(whdatatype_id,
				relative_whdevicedata_id);
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevices", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevice> getWhdatatypeWhdevices(
			@PathVariable Integer whdatatype_id) {
		return new java.util.ArrayList<Whdevice>(whdatatypeDAO
				.findWhdatatypeByPrimaryKey(whdatatype_id)
				.getRelativeWhdevices());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevicedatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevicedata> getWhdatatypeWhdevicedatas(
			@PathVariable Integer whdatatype_id) {
		return new java.util.ArrayList<Whdevicedata>(whdatatypeDAO
				.findWhdatatypeByPrimaryKey(whdatatype_id)
				.getRelativeWhdevicedatas());
	}

	@RequestMapping(value = "/Whdatatype", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdatatype> listWhdatatypes() {
		return new java.util.ArrayList<Whdatatype>(
				whdatatypeService.loadWhdatatypes());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdatatype loadWhdatatype(@PathVariable Integer whdatatype_id) {
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype_id);
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevices/{relative_whdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice loadWhdatatypeWhdevices(
			@PathVariable Integer whdatatype_id,
			@PathVariable Integer relative_whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(
				relative_whdevice_id, -1, -1);

		return whdevice;
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevicedatas/{relative_whdevicedata_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevicedata loadWhdatatypeWhdevicedatas(
			@PathVariable Integer whdatatype_id,
			@PathVariable Integer relative_whdevicedata_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(relative_whdevicedata_id, -1, -1);

		return whdevicedata;
	}

	@RequestMapping(value = "/Whdatatype", method = RequestMethod.POST)
	@ResponseBody
	public Whdatatype newWhdatatype(@RequestBody Whdatatype whdatatype) {
		whdatatypeService.saveWhdatatype(whdatatype);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype.getId());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevices", method = RequestMethod.POST)
	@ResponseBody
	public Whdevice newWhdatatypeWhdevices(@PathVariable Integer whdatatype_id,
			@RequestBody Whdevice whdevice) {
		whdatatypeService.saveWhdatatypeWhdevices(whdatatype_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevicedatas", method = RequestMethod.POST)
	@ResponseBody
	public Whdevicedata newWhdatatypeWhdevicedatas(
			@PathVariable Integer whdatatype_id,
			@RequestBody Whdevicedata whdevicedata) {
		whdatatypeService.saveWhdatatypeWhdevicedatas(whdatatype_id,
				whdevicedata);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata
				.getId());
	}

	@RequestMapping(value = "/Whdatatype", method = RequestMethod.PUT)
	@ResponseBody
	public Whdatatype saveWhdatatype(@RequestBody Whdatatype whdatatype) {
		whdatatypeService.saveWhdatatype(whdatatype);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype.getId());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevices", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevice saveWhdatatypeWhdevices(
			@PathVariable Integer whdatatype_id, @RequestBody Whdevice whdevices) {
		whdatatypeService.saveWhdatatypeWhdevices(whdatatype_id, whdevices);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevices.getId());
	}

	@RequestMapping(value = "/Whdatatype/{whdatatype_id}/whdevicedatas", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevicedata saveWhdatatypeWhdevicedatas(
			@PathVariable Integer whdatatype_id,
			@RequestBody Whdevicedata whdevicedatas) {
		whdatatypeService.saveWhdatatypeWhdevicedatas(whdatatype_id,
				whdevicedatas);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedatas
				.getId());
	}
}