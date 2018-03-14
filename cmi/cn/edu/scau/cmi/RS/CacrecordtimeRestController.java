package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacrecordtimeDAO;

import cn.edu.scau.cmi.service.CacrecordtimeService;

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

@Controller("CacrecordtimeRestController")
public class CacrecordtimeRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacdevicedataPageNumber = 0;
	static int cacdevicedataPageSize = 10;
	static int cacmalfunctionPageNumber = 0;
	static int cacmalfunctionPageSize = 10;
	static int cacsensordataPageNumber = 0;
	static int cacsensordataPageSize = 10;

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@Autowired
	private CacrecordtimeService cacrecordtimeService;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacrecordtime(@PathVariable Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id);
		cacrecordtimeService.deleteCacrecordtime(cacrecordtime);
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacdevicedatas/{relative_cacdevicedata_cacdevice}/{relative_cacdevicedata_cacrecordtime}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacrecordtimeCacdevicedatas(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacdevicedata_cacdevice,
			@PathVariable Integer relative_cacdevicedata_cacrecordtime) {
		cacrecordtimeService.deleteCacrecordtimeCacdevicedatas(
				cacrecordtime_id, relative_cacdevicedata_cacdevice,
				relative_cacdevicedata_cacrecordtime);
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacmalfunctions/{relative_cacmalfunction_cacrecordtime}/{relative_cacmalfunction_cacdevice}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacrecordtimeCacmalfunctions(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacmalfunction_cacrecordtime,
			@PathVariable Integer relative_cacmalfunction_cacdevice) {
		cacrecordtimeService.deleteCacrecordtimeCacmalfunctions(
				cacrecordtime_id, relative_cacmalfunction_cacrecordtime,
				relative_cacmalfunction_cacdevice);
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacsensordatas/{relative_cacsensordata_cacrecordtime}/{relative_cacsensordata_cacsensor}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacrecordtimeCacsensordatas(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacsensordata_cacrecordtime,
			@PathVariable Integer relative_cacsensordata_cacsensor) {
		cacrecordtimeService.deleteCacrecordtimeCacsensordatas(
				cacrecordtime_id, relative_cacsensordata_cacrecordtime,
				relative_cacsensordata_cacsensor);
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacdevicedatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacdevicedata> getCacrecordtimeCacdevicedatas(
			@PathVariable Integer cacrecordtime_id) {
		return new java.util.ArrayList<Cacdevicedata>(cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id)
				.getRelativeCacdevicedatas());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacmalfunctions", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacmalfunction> getCacrecordtimeCacmalfunctions(
			@PathVariable Integer cacrecordtime_id) {
		return new java.util.ArrayList<Cacmalfunction>(cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id)
				.getRelativeCacmalfunctions());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacsensordatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacsensordata> getCacrecordtimeCacsensordatas(
			@PathVariable Integer cacrecordtime_id) {
		return new java.util.ArrayList<Cacsensordata>(cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id)
				.getRelativeCacsensordatas());
	}

	@RequestMapping(value = "/Cacrecordtime", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacrecordtime> listCacrecordtimes() {
		return new java.util.ArrayList<Cacrecordtime>(
				cacrecordtimeService.loadCacrecordtimes());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime loadCacrecordtime(
			@PathVariable Integer cacrecordtime_id) {
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime_id);
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacdevicedatas/{relative_cacdevicedata_cacdevice}/{relative_cacdevicedata_cacrecordtime}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevicedata loadCacrecordtimeCacdevicedatas(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacdevicedata_cacdevice,
			@PathVariable Integer relative_cacdevicedata_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						relative_cacdevicedata_cacdevice,
						relative_cacdevicedata_cacrecordtime, -1, -1);

		return cacdevicedata;
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacmalfunctions/{relative_cacmalfunction_cacrecordtime}/{relative_cacmalfunction_cacdevice}", method = RequestMethod.GET)
	@ResponseBody
	public Cacmalfunction loadCacrecordtimeCacmalfunctions(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacmalfunction_cacrecordtime,
			@PathVariable Integer relative_cacmalfunction_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						relative_cacmalfunction_cacrecordtime,
						relative_cacmalfunction_cacdevice, -1, -1);

		return cacmalfunction;
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacsensordatas/{relative_cacsensordata_cacrecordtime}/{relative_cacsensordata_cacsensor}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensordata loadCacrecordtimeCacsensordatas(
			@PathVariable Integer cacrecordtime_id,
			@PathVariable Integer relative_cacsensordata_cacrecordtime,
			@PathVariable Integer relative_cacsensordata_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						relative_cacsensordata_cacrecordtime,
						relative_cacsensordata_cacsensor, -1, -1);

		return cacsensordata;
	}

	@RequestMapping(value = "/Cacrecordtime", method = RequestMethod.POST)
	@ResponseBody
	public Cacrecordtime newCacrecordtime(
			@RequestBody Cacrecordtime cacrecordtime) {
		cacrecordtimeService.saveCacrecordtime(cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacdevicedatas", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevicedata newCacrecordtimeCacdevicedatas(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacdevicedata cacdevicedata) {
		cacrecordtimeService.saveCacrecordtimeCacdevicedatas(cacrecordtime_id,
				cacdevicedata);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata.getCacdevice(), cacdevicedata.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacmalfunctions", method = RequestMethod.POST)
	@ResponseBody
	public Cacmalfunction newCacrecordtimeCacmalfunctions(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacmalfunction cacmalfunction) {
		cacrecordtimeService.saveCacrecordtimeCacmalfunctions(cacrecordtime_id,
				cacmalfunction);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction.getCacrecordtime(),
				cacmalfunction.getCacdevice());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacsensordatas", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensordata newCacrecordtimeCacsensordatas(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacsensordata cacsensordata) {
		cacrecordtimeService.saveCacrecordtimeCacsensordatas(cacrecordtime_id,
				cacsensordata);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata.getCacrecordtime(), cacsensordata.getCacsensor());
	}

	@RequestMapping(value = "/Cacrecordtime", method = RequestMethod.PUT)
	@ResponseBody
	public Cacrecordtime saveCacrecordtime(
			@RequestBody Cacrecordtime cacrecordtime) {
		cacrecordtimeService.saveCacrecordtime(cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacdevicedatas", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevicedata saveCacrecordtimeCacdevicedatas(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacdevicedata cacdevicedatas) {
		cacrecordtimeService.saveCacrecordtimeCacdevicedatas(cacrecordtime_id,
				cacdevicedatas);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedatas.getCacdevice(),
				cacdevicedatas.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacmalfunctions", method = RequestMethod.PUT)
	@ResponseBody
	public Cacmalfunction saveCacrecordtimeCacmalfunctions(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacmalfunction cacmalfunctions) {
		cacrecordtimeService.saveCacrecordtimeCacmalfunctions(cacrecordtime_id,
				cacmalfunctions);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunctions.getCacrecordtime(),
				cacmalfunctions.getCacdevice());
	}

	@RequestMapping(value = "/Cacrecordtime/{cacrecordtime_id}/cacsensordatas", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensordata saveCacrecordtimeCacsensordatas(
			@PathVariable Integer cacrecordtime_id,
			@RequestBody Cacsensordata cacsensordatas) {
		cacrecordtimeService.saveCacrecordtimeCacsensordatas(cacrecordtime_id,
				cacsensordatas);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordatas.getCacrecordtime(),
				cacsensordatas.getCacsensor());
	}
}