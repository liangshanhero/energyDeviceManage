package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacdeviceDAO;

import cn.edu.scau.cmi.service.CacdeviceService;

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

@Controller("CacdeviceRestController")
public class CacdeviceRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacdevicedataPageNumber = 0;
	static int cacdevicedataPageSize = 10;
	static int cacmalfunctionPageNumber = 0;
	static int cacmalfunctionPageSize = 10;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacdeviceService cacdeviceService;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdevice(@PathVariable Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(cacdevice_id);
		cacdeviceService.deleteCacdevice(cacdevice);
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cac/{relative_cac_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdeviceCac(@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cac_id) {
		cacdeviceService.deleteCacdeviceCac(cacdevice_id, relative_cac_id);
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacdevicedatas/{relative_cacdevicedata_cacdevice}/{relative_cacdevicedata_cacrecordtime}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdeviceCacdevicedatas(
			@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cacdevicedata_cacdevice,
			@PathVariable Integer relative_cacdevicedata_cacrecordtime) {
		cacdeviceService.deleteCacdeviceCacdevicedatas(cacdevice_id,
				relative_cacdevicedata_cacdevice,
				relative_cacdevicedata_cacrecordtime);
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacmalfunctions/{relative_cacmalfunction_cacrecordtime}/{relative_cacmalfunction_cacdevice}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdeviceCacmalfunctions(
			@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cacmalfunction_cacrecordtime,
			@PathVariable Integer relative_cacmalfunction_cacdevice) {
		cacdeviceService.deleteCacdeviceCacmalfunctions(cacdevice_id,
				relative_cacmalfunction_cacrecordtime,
				relative_cacmalfunction_cacdevice);
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cac", method = RequestMethod.GET)
	@ResponseBody
	public Cac getCacdeviceCac(@PathVariable Integer cacdevice_id) {
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice_id)
				.getRelativeCac();
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacdevicedatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacdevicedata> getCacdeviceCacdevicedatas(
			@PathVariable Integer cacdevice_id) {
		return new java.util.ArrayList<Cacdevicedata>(cacdeviceDAO
				.findCacdeviceByPrimaryKey(cacdevice_id)
				.getRelativeCacdevicedatas());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacmalfunctions", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacmalfunction> getCacdeviceCacmalfunctions(
			@PathVariable Integer cacdevice_id) {
		return new java.util.ArrayList<Cacmalfunction>(cacdeviceDAO
				.findCacdeviceByPrimaryKey(cacdevice_id)
				.getRelativeCacmalfunctions());
	}

	@RequestMapping(value = "/Cacdevice", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacdevice> listCacdevices() {
		return new java.util.ArrayList<Cacdevice>(
				cacdeviceService.loadCacdevices());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice loadCacdevice(@PathVariable Integer cacdevice_id) {
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice_id);
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cac/{relative_cac_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cac loadCacdeviceCac(@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(relative_cac_id, -1, -1);

		return cac;
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacdevicedatas/{relative_cacdevicedata_cacdevice}/{relative_cacdevicedata_cacrecordtime}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevicedata loadCacdeviceCacdevicedatas(
			@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cacdevicedata_cacdevice,
			@PathVariable Integer relative_cacdevicedata_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						relative_cacdevicedata_cacdevice,
						relative_cacdevicedata_cacrecordtime, -1, -1);

		return cacdevicedata;
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacmalfunctions/{relative_cacmalfunction_cacrecordtime}/{relative_cacmalfunction_cacdevice}", method = RequestMethod.GET)
	@ResponseBody
	public Cacmalfunction loadCacdeviceCacmalfunctions(
			@PathVariable Integer cacdevice_id,
			@PathVariable Integer relative_cacmalfunction_cacrecordtime,
			@PathVariable Integer relative_cacmalfunction_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						relative_cacmalfunction_cacrecordtime,
						relative_cacmalfunction_cacdevice, -1, -1);

		return cacmalfunction;
	}

	@RequestMapping(value = "/Cacdevice", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevice newCacdevice(@RequestBody Cacdevice cacdevice) {
		cacdeviceService.saveCacdevice(cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cac", method = RequestMethod.POST)
	@ResponseBody
	public Cac newCacdeviceCac(@PathVariable Integer cacdevice_id,
			@RequestBody Cac cac) {
		cacdeviceService.saveCacdeviceCac(cacdevice_id, cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacdevicedatas", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevicedata newCacdeviceCacdevicedatas(
			@PathVariable Integer cacdevice_id,
			@RequestBody Cacdevicedata cacdevicedata) {
		cacdeviceService.saveCacdeviceCacdevicedatas(cacdevice_id,
				cacdevicedata);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata.getCacdevice(), cacdevicedata.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacmalfunctions", method = RequestMethod.POST)
	@ResponseBody
	public Cacmalfunction newCacdeviceCacmalfunctions(
			@PathVariable Integer cacdevice_id,
			@RequestBody Cacmalfunction cacmalfunction) {
		cacdeviceService.saveCacdeviceCacmalfunctions(cacdevice_id,
				cacmalfunction);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction.getCacrecordtime(),
				cacmalfunction.getCacdevice());
	}

	@RequestMapping(value = "/Cacdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevice saveCacdevice(@RequestBody Cacdevice cacdevice) {
		cacdeviceService.saveCacdevice(cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cac", method = RequestMethod.PUT)
	@ResponseBody
	public Cac saveCacdeviceCac(@PathVariable Integer cacdevice_id,
			@RequestBody Cac cac) {
		cacdeviceService.saveCacdeviceCac(cacdevice_id, cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacdevicedatas", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevicedata saveCacdeviceCacdevicedatas(
			@PathVariable Integer cacdevice_id,
			@RequestBody Cacdevicedata cacdevicedatas) {
		cacdeviceService.saveCacdeviceCacdevicedatas(cacdevice_id,
				cacdevicedatas);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedatas.getCacdevice(),
				cacdevicedatas.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacdevice/{cacdevice_id}/cacmalfunctions", method = RequestMethod.PUT)
	@ResponseBody
	public Cacmalfunction saveCacdeviceCacmalfunctions(
			@PathVariable Integer cacdevice_id,
			@RequestBody Cacmalfunction cacmalfunctions) {
		cacdeviceService.saveCacdeviceCacmalfunctions(cacdevice_id,
				cacmalfunctions);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunctions.getCacrecordtime(),
				cacmalfunctions.getCacdevice());
	}
}