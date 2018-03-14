package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.LedmeterdataDAO;

import cn.edu.scau.cmi.service.LedmeterdataService;

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

@Controller("LedmeterdataRestController")
public class LedmeterdataRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterdataService ledmeterdataService;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedmeterdata(@PathVariable Integer ledmeterdata_id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(ledmeterdata_id);
		ledmeterdataService.deleteLedmeterdata(ledmeterdata);
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}/ledmeter/{relative_ledmeter_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedmeterdataLedmeter(
			@PathVariable Integer ledmeterdata_id,
			@PathVariable Integer relative_ledmeter_id) {
		ledmeterdataService.deleteLedmeterdataLedmeter(ledmeterdata_id,
				relative_ledmeter_id);
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}/ledmeter", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeter getLedmeterdataLedmeter(
			@PathVariable Integer ledmeterdata_id) {
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdata_id)
				.getRelativeLedmeter();
	}

	@RequestMapping(value = "/Ledmeterdata", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledmeterdata> listLedmeterdatas() {
		return new java.util.ArrayList<Ledmeterdata>(
				ledmeterdataService.loadLedmeterdatas());
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeterdata loadLedmeterdata(@PathVariable Integer ledmeterdata_id) {
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdata_id);
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}/ledmeter/{relative_ledmeter_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeter loadLedmeterdataLedmeter(
			@PathVariable Integer ledmeterdata_id,
			@PathVariable Integer relative_ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(
				relative_ledmeter_id, -1, -1);

		return ledmeter;
	}

	@RequestMapping(value = "/Ledmeterdata", method = RequestMethod.POST)
	@ResponseBody
	public Ledmeterdata newLedmeterdata(@RequestBody Ledmeterdata ledmeterdata) {
		ledmeterdataService.saveLedmeterdata(ledmeterdata);
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdata
				.getId());
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}/ledmeter", method = RequestMethod.POST)
	@ResponseBody
	public Ledmeter newLedmeterdataLedmeter(
			@PathVariable Integer ledmeterdata_id,
			@RequestBody Ledmeter ledmeter) {
		ledmeterdataService.saveLedmeterdataLedmeter(ledmeterdata_id, ledmeter);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter.getId());
	}

	@RequestMapping(value = "/Ledmeterdata", method = RequestMethod.PUT)
	@ResponseBody
	public Ledmeterdata saveLedmeterdata(@RequestBody Ledmeterdata ledmeterdata) {
		ledmeterdataService.saveLedmeterdata(ledmeterdata);
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(ledmeterdata
				.getId());
	}

	@RequestMapping(value = "/Ledmeterdata/{ledmeterdata_id}/ledmeter", method = RequestMethod.PUT)
	@ResponseBody
	public Ledmeter saveLedmeterdataLedmeter(
			@PathVariable Integer ledmeterdata_id,
			@RequestBody Ledmeter ledmeter) {
		ledmeterdataService.saveLedmeterdataLedmeter(ledmeterdata_id, ledmeter);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter.getId());
	}

}