package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacdevicedataDAO;

import cn.edu.scau.cmi.service.CacdevicedataService;

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

@Controller("CacdevicedataRestController")
public class CacdevicedataRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdevicedata(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime);
		cacdevicedataService.deleteCacdevicedata(cacdevicedata);
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacdevice/{relative_cacdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdevicedataCacdevice(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@PathVariable Integer relative_cacdevice_id) {
		cacdevicedataService.deleteCacdevicedataCacdevice(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime,
				relative_cacdevice_id);
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacdevicedataCacrecordtime(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@PathVariable Integer relative_cacrecordtime_id) {
		cacdevicedataService.deleteCacdevicedataCacrecordtime(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime,
				relative_cacrecordtime_id);
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacdevice", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice getCacdevicedataCacdevice(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime) {
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime)
				.getRelativeCacdevice();
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacrecordtime", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime getCacdevicedataCacrecordtime(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime) {
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime)
				.getRelativeCacrecordtime();
	}

	@RequestMapping(value = "/Cacdevicedata", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacdevicedata> listCacdevicedatas() {
		return new java.util.ArrayList<Cacdevicedata>(
				cacdevicedataService.loadCacdevicedatas());
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevicedata loadCacdevicedata(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime) {
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime);
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacdevice/{relative_cacdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice loadCacdevicedataCacdevice(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@PathVariable Integer relative_cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				relative_cacdevice_id, -1, -1);

		return cacdevice;
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime loadCacdevicedataCacrecordtime(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@PathVariable Integer relative_cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(relative_cacrecordtime_id, -1,
						-1);

		return cacrecordtime;
	}

	@RequestMapping(value = "/Cacdevicedata", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevicedata newCacdevicedata(
			@RequestBody Cacdevicedata cacdevicedata) {
		cacdevicedataService.saveCacdevicedata(cacdevicedata);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata.getCacdevice(), cacdevicedata.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacdevice", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevice newCacdevicedataCacdevice(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@RequestBody Cacdevice cacdevice) {
		cacdevicedataService
				.saveCacdevicedataCacdevice(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacrecordtime", method = RequestMethod.POST)
	@ResponseBody
	public Cacrecordtime newCacdevicedataCacrecordtime(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacdevicedataService.saveCacdevicedataCacrecordtime(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacdevicedata", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevicedata saveCacdevicedata(
			@RequestBody Cacdevicedata cacdevicedata) {
		cacdevicedataService.saveCacdevicedata(cacdevicedata);
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(
				cacdevicedata.getCacdevice(), cacdevicedata.getCacrecordtime());
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevice saveCacdevicedataCacdevice(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@RequestBody Cacdevice cacdevice) {
		cacdevicedataService
				.saveCacdevicedataCacdevice(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cacdevicedata/{cacdevicedata_cacdevice}/{cacdevicedata_cacrecordtime}/cacrecordtime", method = RequestMethod.PUT)
	@ResponseBody
	public Cacrecordtime saveCacdevicedataCacrecordtime(
			@PathVariable Integer cacdevicedata_cacdevice,
			@PathVariable Integer cacdevicedata_cacrecordtime,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacdevicedataService.saveCacdevicedataCacrecordtime(
				cacdevicedata_cacdevice, cacdevicedata_cacrecordtime,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

}