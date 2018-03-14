package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhdeviceDAO;

import cn.edu.scau.cmi.service.WhdeviceService;

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

@Controller("WhdeviceRestController")
public class WhdeviceRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int whdatatypePageNumber = 0;
	static int whdatatypePageSize = 10;
	static int whdevicedataPageNumber = 0;
	static int whdevicedataPageSize = 10;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdatatypeService whdatatypeService;
	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@RequestMapping(value = "/Whdevice/{whdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdevice(@PathVariable Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id);
		whdeviceService.deleteWhdevice(whdevice);
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whbuilding/{relative_whbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdeviceWhbuilding(@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whbuilding_id) {
		whdeviceService.deleteWhdeviceWhbuilding(whdevice_id,
				relative_whbuilding_id);
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdatatypes/{relative_whdatatype_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdeviceWhdatatypes(@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whdatatype_id) {
		whdeviceService.deleteWhdeviceWhdatatypes(whdevice_id,
				relative_whdatatype_id);
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdevicedatas/{relative_whdevicedata_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhdeviceWhdevicedatas(@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whdevicedata_id) {
		whdeviceService.deleteWhdeviceWhdevicedatas(whdevice_id,
				relative_whdevicedata_id);
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whbuilding", method = RequestMethod.GET)
	@ResponseBody
	public Whbuilding getWhdeviceWhbuilding(@PathVariable Integer whdevice_id) {
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id)
				.getWhbuilding();
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdatatypes", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdatatype> getWhdeviceWhdatatypes(
			@PathVariable Integer whdevice_id) {
		return new java.util.ArrayList<Whdatatype>(whdeviceDAO
				.findWhdeviceByPrimaryKey(whdevice_id).getWhdatatypes());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdevicedatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevicedata> getWhdeviceWhdevicedatas(
			@PathVariable Integer whdevice_id) {
		return new java.util.ArrayList<Whdevicedata>(whdeviceDAO
				.findWhdeviceByPrimaryKey(whdevice_id)
				.getWhdevicedatas());
	}

	@RequestMapping(value = "/Whdevice", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevice> listWhdevices() {
		return new java.util.ArrayList<Whdevice>(
				whdeviceService.loadWhdevices());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice loadWhdevice(@PathVariable Integer whdevice_id) {
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id);
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whbuilding/{relative_whbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whbuilding loadWhdeviceWhbuilding(@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				relative_whbuilding_id, -1, -1);

		return whbuilding;
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdatatypes/{relative_whdatatype_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdatatype loadWhdeviceWhdatatypes(
			@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				relative_whdatatype_id, -1, -1);

		return whdatatype;
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdevicedatas/{relative_whdevicedata_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevicedata loadWhdeviceWhdevicedatas(
			@PathVariable Integer whdevice_id,
			@PathVariable Integer relative_whdevicedata_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(relative_whdevicedata_id, -1, -1);

		return whdevicedata;
	}

	@RequestMapping(value = "/Whdevice", method = RequestMethod.POST)
	@ResponseBody
	public Whdevice newWhdevice(@RequestBody Whdevice whdevice) {
		whdeviceService.saveWhdevice(whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whbuilding", method = RequestMethod.POST)
	@ResponseBody
	public Whbuilding newWhdeviceWhbuilding(@PathVariable Integer whdevice_id,
			@RequestBody Whbuilding whbuilding) {
		whdeviceService.saveWhdeviceWhbuilding(whdevice_id, whbuilding);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdatatypes", method = RequestMethod.POST)
	@ResponseBody
	public Whdatatype newWhdeviceWhdatatypes(@PathVariable Integer whdevice_id,
			@RequestBody Whdatatype whdatatype) {
		whdeviceService.saveWhdeviceWhdatatypes(whdevice_id, whdatatype);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdevicedatas", method = RequestMethod.POST)
	@ResponseBody
	public Whdevicedata newWhdeviceWhdevicedatas(
			@PathVariable Integer whdevice_id,
			@RequestBody Whdevicedata whdevicedata) {
		whdeviceService.saveWhdeviceWhdevicedatas(whdevice_id, whdevicedata);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedata
				.getId());
	}

	@RequestMapping(value = "/Whdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevice saveWhdevice(@RequestBody Whdevice whdevice) {
		whdeviceService.saveWhdevice(whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whbuilding", method = RequestMethod.PUT)
	@ResponseBody
	public Whbuilding saveWhdeviceWhbuilding(@PathVariable Integer whdevice_id,
			@RequestBody Whbuilding whbuilding) {
		whdeviceService.saveWhdeviceWhbuilding(whdevice_id, whbuilding);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdatatypes", method = RequestMethod.PUT)
	@ResponseBody
	public Whdatatype saveWhdeviceWhdatatypes(
			@PathVariable Integer whdevice_id,
			@RequestBody Whdatatype whdatatypes) {
		whdeviceService.saveWhdeviceWhdatatypes(whdevice_id, whdatatypes);
		return whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatypes.getId());
	}

	@RequestMapping(value = "/Whdevice/{whdevice_id}/whdevicedatas", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevicedata saveWhdeviceWhdevicedatas(
			@PathVariable Integer whdevice_id,
			@RequestBody Whdevicedata whdevicedatas) {
		whdeviceService.saveWhdeviceWhdevicedatas(whdevice_id, whdevicedatas);
		return whdevicedataDAO.findWhdevicedataByPrimaryKey(whdevicedatas
				.getId());
	}
}