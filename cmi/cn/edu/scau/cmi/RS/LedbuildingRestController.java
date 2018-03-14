package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.LedbuildingDAO;

import cn.edu.scau.cmi.service.LedbuildingService;

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

@Controller("LedbuildingRestController")
public class LedbuildingRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int ledmeterPageNumber = 0;
	static int ledmeterPageSize = 10;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedbuildingService ledbuildingService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedmeterService ledmeterService;

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedbuilding(@PathVariable Integer ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO
				.findLedbuildingByPrimaryKey(ledbuilding_id);
		ledbuildingService.deleteLedbuilding(ledbuilding);
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/project/{relative_project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedbuildingProject(@PathVariable Integer ledbuilding_id,
			@PathVariable Integer relative_project_id) {
		ledbuildingService.deleteLedbuildingProject(ledbuilding_id,
				relative_project_id);
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/ledmeters/{relative_ledmeter_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteLedbuildingLedmeters(
			@PathVariable Integer ledbuilding_id,
			@PathVariable Integer relative_ledmeter_id) {
		ledbuildingService.deleteLedbuildingLedmeters(ledbuilding_id,
				relative_ledmeter_id);
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/project", method = RequestMethod.GET)
	@ResponseBody
	public Project getLedbuildingProject(@PathVariable Integer ledbuilding_id) {
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding_id)
				.getRelativeProject();
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/ledmeters", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledmeter> getLedbuildingLedmeters(
			@PathVariable Integer ledbuilding_id) {
		return new java.util.ArrayList<Ledmeter>(ledbuildingDAO
				.findLedbuildingByPrimaryKey(ledbuilding_id)
				.getRelativeLedmeters());
	}

	@RequestMapping(value = "/Ledbuilding", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledbuilding> listLedbuildings() {
		return new java.util.ArrayList<Ledbuilding>(
				ledbuildingService.loadLedbuildings());
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledbuilding loadLedbuilding(@PathVariable Integer ledbuilding_id) {
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding_id);
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/project/{relative_project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadLedbuildingProject(@PathVariable Integer ledbuilding_id,
			@PathVariable Integer relative_project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(
				relative_project_id, -1, -1);

		return project;
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/ledmeters/{relative_ledmeter_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledmeter loadLedbuildingLedmeters(
			@PathVariable Integer ledbuilding_id,
			@PathVariable Integer relative_ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(
				relative_ledmeter_id, -1, -1);

		return ledmeter;
	}

	@RequestMapping(value = "/Ledbuilding", method = RequestMethod.POST)
	@ResponseBody
	public Ledbuilding newLedbuilding(@RequestBody Ledbuilding ledbuilding) {
		ledbuildingService.saveLedbuilding(ledbuilding);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding.getId());
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/project", method = RequestMethod.POST)
	@ResponseBody
	public Project newLedbuildingProject(@PathVariable Integer ledbuilding_id,
			@RequestBody Project project) {
		ledbuildingService.saveLedbuildingProject(ledbuilding_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/ledmeters", method = RequestMethod.POST)
	@ResponseBody
	public Ledmeter newLedbuildingLedmeters(
			@PathVariable Integer ledbuilding_id, @RequestBody Ledmeter ledmeter) {
		ledbuildingService.saveLedbuildingLedmeters(ledbuilding_id, ledmeter);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeter.getId());
	}

	@RequestMapping(value = "/Ledbuilding", method = RequestMethod.PUT)
	@ResponseBody
	public Ledbuilding saveLedbuilding(@RequestBody Ledbuilding ledbuilding) {
		ledbuildingService.saveLedbuilding(ledbuilding);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding.getId());
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/project", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveLedbuildingProject(@PathVariable Integer ledbuilding_id,
			@RequestBody Project project) {
		ledbuildingService.saveLedbuildingProject(ledbuilding_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Ledbuilding/{ledbuilding_id}/ledmeters", method = RequestMethod.PUT)
	@ResponseBody
	public Ledmeter saveLedbuildingLedmeters(
			@PathVariable Integer ledbuilding_id,
			@RequestBody Ledmeter ledmeters) {
		ledbuildingService.saveLedbuildingLedmeters(ledbuilding_id, ledmeters);
		return ledmeterDAO.findLedmeterByPrimaryKey(ledmeters.getId());
	}
}