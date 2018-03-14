package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhdevicedataDAO;

import cn.edu.scau.cmi.service.WhdevicedataService;

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

@Controller("WhdevicedataRestController")
public class WhdevicedataRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;
	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdevicedata(@PathVariable Integer whdevicedata_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedata_id);
		whdevicedataService.deleteWhdevicedata(whdevicedata);
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdatatype/{relative_whdatatype_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdevicedataWhdatatype(
			@PathVariable Integer whdevicedata_id,
			@PathVariable Integer relative_whdatatype_id) {
		whdevicedataService.deleteWhdevicedataWhdatatype(whdevicedata_id,
				relative_whdatatype_id);
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdevice/{relative_whdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdevicedataWhdevice(
			@PathVariable Integer whdevicedata_id,
			@PathVariable Integer relative_whdevice_id) {
		whdevicedataService.deleteWhdevicedataWhdevice(whdevicedata_id,
				relative_whdevice_id);
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdatatype", method = RequestMethod.GET)
	@ResponseBody
	public Whdatatype getWhdevicedataWhdatatype(
			@PathVariable Integer whdevicedata_id) {
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata_id)
				.getRelativeWhdatatype();
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdevice", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice getWhdevicedataWhdevice(
			@PathVariable Integer whdevicedata_id) {
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata_id)
				.getRelativeWhdevice();
	}

	@RequestMapping(value = "/Whdevicedata", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevicedata> listWhdevicedatas() {
		return new java.util.ArrayList<Whdevicedata>(
				whdevicedataService.loadWhdevicedatas());
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevicedata loadWhdevicedata(@PathVariable Integer whdevicedata_id) {
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata_id);
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdatatype/{relative_whdatatype_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdatatype loadWhdevicedataWhdatatype(
			@PathVariable Integer whdevicedata_id,
			@PathVariable Integer relative_whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				relative_whdatatype_id, -1, -1);

		return whdatatype;
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdevice/{relative_whdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice loadWhdevicedataWhdevice(
			@PathVariable Integer whdevicedata_id,
			@PathVariable Integer relative_whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(
				relative_whdevice_id, -1, -1);

		return whdevice;
	}

	@RequestMapping(value = "/Whdevicedata", method = RequestMethod.POST)
	@ResponseBody
	public Whdevicedata newWhdevicedata(@RequestBody Whdevicedata whdevicedata) {
		whdevicedataService.saveWhdevicedata(whdevicedata);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata
				.getId());
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdatatype", method = RequestMethod.POST)
	@ResponseBody
	public Whdatatype newWhdevicedataWhdatatype(
			@PathVariable Integer whdevicedata_id,
			@RequestBody Whdatatype whdatatype) {
		whdevicedataService.saveWhdevicedataWhdatatype(whdevicedata_id,
				whdatatype);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype.getId());
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdevice", method = RequestMethod.POST)
	@ResponseBody
	public Whdevice newWhdevicedataWhdevice(
			@PathVariable Integer whdevicedata_id,
			@RequestBody Whdevice whdevice) {
		whdevicedataService.saveWhdevicedataWhdevice(whdevicedata_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whdevicedata", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevicedata saveWhdevicedata(@RequestBody Whdevicedata whdevicedata) {
		whdevicedataService.saveWhdevicedata(whdevicedata);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata
				.getId());
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdatatype", method = RequestMethod.PUT)
	@ResponseBody
	public Whdatatype saveWhdevicedataWhdatatype(
			@PathVariable Integer whdevicedata_id,
			@RequestBody Whdatatype whdatatype) {
		whdevicedataService.saveWhdevicedataWhdatatype(whdevicedata_id,
				whdatatype);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype.getId());
	}

	@RequestMapping(value = "/Whdevicedata/{whdevicedata_id}/whdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevice saveWhdevicedataWhdevice(
			@PathVariable Integer whdevicedata_id,
			@RequestBody Whdevice whdevice) {
		whdevicedataService.saveWhdevicedataWhdevice(whdevicedata_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

}