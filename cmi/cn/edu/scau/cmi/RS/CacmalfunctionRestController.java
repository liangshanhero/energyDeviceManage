package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacmalfunctionDAO;

import cn.edu.scau.cmi.service.CacmalfunctionService;

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

@Controller("CacmalfunctionRestController")
public class CacmalfunctionRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacmalfunction(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice);
		cacmalfunctionService.deleteCacmalfunction(cacmalfunction);
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacdevice/{relative_cacdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacmalfunctionCacdevice(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@PathVariable Integer relative_cacdevice_id) {
		cacmalfunctionService.deleteCacmalfunctionCacdevice(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				relative_cacdevice_id);
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacmalfunctionCacrecordtime(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@PathVariable Integer relative_cacrecordtime_id) {
		cacmalfunctionService.deleteCacmalfunctionCacrecordtime(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				relative_cacrecordtime_id);
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacdevice", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice getCacmalfunctionCacdevice(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice) {
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice)
				.getRelativeCacdevice();
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacrecordtime", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime getCacmalfunctionCacrecordtime(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice) {
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice)
				.getRelativeCacrecordtime();
	}

	@RequestMapping(value = "/Cacmalfunction", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacmalfunction> listCacmalfunctions() {
		return new java.util.ArrayList<Cacmalfunction>(
				cacmalfunctionService.loadCacmalfunctions());
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}", method = RequestMethod.GET)
	@ResponseBody
	public Cacmalfunction loadCacmalfunction(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice) {
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice);
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacdevice/{relative_cacdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice loadCacmalfunctionCacdevice(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@PathVariable Integer relative_cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				relative_cacdevice_id, -1, -1);

		return cacdevice;
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime loadCacmalfunctionCacrecordtime(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@PathVariable Integer relative_cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(relative_cacrecordtime_id, -1,
						-1);

		return cacrecordtime;
	}

	@RequestMapping(value = "/Cacmalfunction", method = RequestMethod.POST)
	@ResponseBody
	public Cacmalfunction newCacmalfunction(
			@RequestBody Cacmalfunction cacmalfunction) {
		cacmalfunctionService.saveCacmalfunction(cacmalfunction);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction.getCacrecordtime(),
				cacmalfunction.getCacdevice());
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacdevice", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevice newCacmalfunctionCacdevice(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@RequestBody Cacdevice cacdevice) {
		cacmalfunctionService.saveCacmalfunctionCacdevice(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacrecordtime", method = RequestMethod.POST)
	@ResponseBody
	public Cacrecordtime newCacmalfunctionCacrecordtime(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacmalfunctionService.saveCacmalfunctionCacrecordtime(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacmalfunction", method = RequestMethod.PUT)
	@ResponseBody
	public Cacmalfunction saveCacmalfunction(
			@RequestBody Cacmalfunction cacmalfunction) {
		cacmalfunctionService.saveCacmalfunction(cacmalfunction);
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
				cacmalfunction.getCacrecordtime(),
				cacmalfunction.getCacdevice());
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevice saveCacmalfunctionCacdevice(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@RequestBody Cacdevice cacdevice) {
		cacmalfunctionService.saveCacmalfunctionCacdevice(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacmalfunction/{cacmalfunction_cacrecordtime}/{cacmalfunction_cacdevice}/cacrecordtime", method = RequestMethod.PUT)
	@ResponseBody
	public Cacrecordtime saveCacmalfunctionCacrecordtime(
			@PathVariable Integer cacmalfunction_cacrecordtime,
			@PathVariable Integer cacmalfunction_cacdevice,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacmalfunctionService.saveCacmalfunctionCacrecordtime(
				cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

}