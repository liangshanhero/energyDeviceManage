package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhbuildingDAO;

import cn.edu.scau.cmi.service.WhbuildingService;

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

@Controller("WhbuildingRestController")
public class WhbuildingRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int whdevicePageNumber = 0;
	static int whdevicePageSize = 10;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhbuildingService whbuildingService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhbuilding(@PathVariable Integer whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO
				.findWhbuildingByPrimaryKey(whbuilding_id);
		whbuildingService.deleteWhbuilding(whbuilding);
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/project/{relative_project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhbuildingProject(@PathVariable Integer whbuilding_id,
			@PathVariable Integer relative_project_id) {
		whbuildingService.deleteWhbuildingProject(whbuilding_id,
				relative_project_id);
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/whdevices/{relative_whdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhbuildingWhdevices(@PathVariable Integer whbuilding_id,
			@PathVariable Integer relative_whdevice_id) {
		whbuildingService.deleteWhbuildingWhdevices(whbuilding_id,
				relative_whdevice_id);
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/project", method = RequestMethod.GET)
	@ResponseBody
	public Project getWhbuildingProject(@PathVariable Integer whbuilding_id) {
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding_id)
				.getRelativeProject();
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/whdevices", method = RequestMethod.GET)
	@ResponseBody
	public List<Whdevice> getWhbuildingWhdevices(
			@PathVariable Integer whbuilding_id) {
		return new java.util.ArrayList<Whdevice>(whbuildingDAO
				.findWhbuildingByPrimaryKey(whbuilding_id)
				.getRelativeWhdevices());
	}

	@RequestMapping(value = "/Whbuilding", method = RequestMethod.GET)
	@ResponseBody
	public List<Whbuilding> listWhbuildings() {
		return new java.util.ArrayList<Whbuilding>(
				whbuildingService.loadWhbuildings());
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whbuilding loadWhbuilding(@PathVariable Integer whbuilding_id) {
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding_id);
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/project/{relative_project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadWhbuildingProject(@PathVariable Integer whbuilding_id,
			@PathVariable Integer relative_project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(
				relative_project_id, -1, -1);

		return project;
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/whdevices/{relative_whdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice loadWhbuildingWhdevices(
			@PathVariable Integer whbuilding_id,
			@PathVariable Integer relative_whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(
				relative_whdevice_id, -1, -1);

		return whdevice;
	}

	@RequestMapping(value = "/Whbuilding", method = RequestMethod.POST)
	@ResponseBody
	public Whbuilding newWhbuilding(@RequestBody Whbuilding whbuilding) {
		whbuildingService.saveWhbuilding(whbuilding);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding.getId());
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/project", method = RequestMethod.POST)
	@ResponseBody
	public Project newWhbuildingProject(@PathVariable Integer whbuilding_id,
			@RequestBody Project project) {
		whbuildingService.saveWhbuildingProject(whbuilding_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/whdevices", method = RequestMethod.POST)
	@ResponseBody
	public Whdevice newWhbuildingWhdevices(@PathVariable Integer whbuilding_id,
			@RequestBody Whdevice whdevice) {
		whbuildingService.saveWhbuildingWhdevices(whbuilding_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whbuilding", method = RequestMethod.PUT)
	@ResponseBody
	public Whbuilding saveWhbuilding(@RequestBody Whbuilding whbuilding) {
		whbuildingService.saveWhbuilding(whbuilding);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding.getId());
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/project", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveWhbuildingProject(@PathVariable Integer whbuilding_id,
			@RequestBody Project project) {
		whbuildingService.saveWhbuildingProject(whbuilding_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Whbuilding/{whbuilding_id}/whdevices", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevice saveWhbuildingWhdevices(
			@PathVariable Integer whbuilding_id, @RequestBody Whdevice whdevices) {
		whbuildingService.saveWhbuildingWhdevices(whbuilding_id, whdevices);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevices.getId());
	}
}