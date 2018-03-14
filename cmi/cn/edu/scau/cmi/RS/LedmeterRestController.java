package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.LedmeterDAO;

import cn.edu.scau.cmi.service.LedmeterService;

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

@Controller("LedmeterRestController")
public class LedmeterRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int ledmeterdataPageNumber = 0;
	static int ledmeterdataPageSize = 10;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedmeterService ledmeterService;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterdataService ledmeterdataService;

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedmeter(@PathVariable Integer ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id);
		ledmeterService.deleteLedmeter(ledmeter);
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledbuilding/{relative_ledbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedmeterLedbuilding(@PathVariable Integer ledmeter_id,
			@PathVariable Integer relative_ledbuilding_id) {
		ledmeterService.deleteLedmeterLedbuilding(ledmeter_id,
				relative_ledbuilding_id);
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledmeterdatas/{relative_ledmeterdata_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedmeterLedmeterdatas(@PathVariable Integer ledmeter_id,
			@PathVariable Integer relative_ledmeterdata_id) {
		ledmeterService.deleteLedmeterLedmeterdatas(ledmeter_id,
				relative_ledmeterdata_id);
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledbuilding", method = RequestMethod.GET)
	@ResponseBody
	public Ledbuilding getLedmeterLedbuilding(@PathVariable Integer ledmeter_id) {
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id)
				.getRelativeLedbuilding();
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledmeterdatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledmeterdata> getLedmeterLedmeterdatas(
			@PathVariable Integer ledmeter_id) {
		return new java.util.ArrayList<Ledmeterdata>(ledmeterDAO
				.findLedmeterByPrimaryKey(ledmeter_id)
				.getRelativeLedmeterdatas());
	}

	@RequestMapping(value = "/Ledmeter", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledmeter> listLedmeters() {
		return new java.util.ArrayList<Ledmeter>(
				ledmeterService.loadLedmeters());
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeter loadLedmeter(@PathVariable Integer ledmeter_id) {
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id);
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledbuilding/{relative_ledbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledbuilding loadLedmeterLedbuilding(
			@PathVariable Integer ledmeter_id,
			@PathVariable Integer relative_ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				relative_ledbuilding_id, -1, -1);

		return ledbuilding;
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledmeterdatas/{relative_ledmeterdata_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeterdata loadLedmeterLedmeterdatas(
			@PathVariable Integer ledmeter_id,
			@PathVariable Integer relative_ledmeterdata_id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(relative_ledmeterdata_id, -1, -1);

		return ledmeterdata;
	}

	@RequestMapping(value = "/Ledmeter", method = RequestMethod.POST)
	@ResponseBody
	public Ledmeter newLedmeter(@RequestBody Ledmeter ledmeter) {
		ledmeterService.saveLedmeter(ledmeter);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter.getId());
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledbuilding", method = RequestMethod.POST)
	@ResponseBody
	public Ledbuilding newLedmeterLedbuilding(
			@PathVariable Integer ledmeter_id,
			@RequestBody Ledbuilding ledbuilding) {
		ledmeterService.saveLedmeterLedbuilding(ledmeter_id, ledbuilding);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding.getId());
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledmeterdatas", method = RequestMethod.POST)
	@ResponseBody
	public Ledmeterdata newLedmeterLedmeterdatas(
			@PathVariable Integer ledmeter_id,
			@RequestBody Ledmeterdata ledmeterdata) {
		ledmeterService.saveLedmeterLedmeterdatas(ledmeter_id, ledmeterdata);
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdata
				.getId());
	}

	@RequestMapping(value = "/Ledmeter", method = RequestMethod.PUT)
	@ResponseBody
	public Ledmeter saveLedmeter(@RequestBody Ledmeter ledmeter) {
		ledmeterService.saveLedmeter(ledmeter);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter.getId());
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledbuilding", method = RequestMethod.PUT)
	@ResponseBody
	public Ledbuilding saveLedmeterLedbuilding(
			@PathVariable Integer ledmeter_id,
			@RequestBody Ledbuilding ledbuilding) {
		ledmeterService.saveLedmeterLedbuilding(ledmeter_id, ledbuilding);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding.getId());
	}

	@RequestMapping(value = "/Ledmeter/{ledmeter_id}/ledmeterdatas", method = RequestMethod.PUT)
	@ResponseBody
	public Ledmeterdata saveLedmeterLedmeterdatas(
			@PathVariable Integer ledmeter_id,
			@RequestBody Ledmeterdata ledmeterdatas) {
		ledmeterService.saveLedmeterLedmeterdatas(ledmeter_id, ledmeterdatas);
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdatas
				.getId());
	}
}