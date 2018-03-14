package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacDAO;

import cn.edu.scau.cmi.service.CacService;

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

@Controller("CacRestController")
public class CacRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacdevicePageNumber = 0;
	static int cacdevicePageSize = 10;
	static int cacsensorPageNumber = 0;
	static int cacsensorPageSize = 10;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacService cacService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacdeviceService cacdeviceService;

	@Autowired
	private CacsensorDAO cacsensorDAO;

	@Autowired
	private CacsensorService cacsensorService;

	@RequestMapping(value = "/Cac/{cac_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCac(@PathVariable Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id);
		cacService.deleteCac(cac);
	}

	@RequestMapping(value = "/Cac/{cac_id}/project/{relative_project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacProject(@PathVariable Integer cac_id,
			@PathVariable Integer relative_project_id) {
		cacService.deleteCacProject(cac_id, relative_project_id);
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacdevices/{relative_cacdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacCacdevices(@PathVariable Integer cac_id,
			@PathVariable Integer relative_cacdevice_id) {
		cacService.deleteCacCacdevices(cac_id, relative_cacdevice_id);
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacsensors/{relative_cacsensor_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacCacsensors(@PathVariable Integer cac_id,
			@PathVariable Integer relative_cacsensor_id) {
		cacService.deleteCacCacsensors(cac_id, relative_cacsensor_id);
	}

	@RequestMapping(value = "/Cac/{cac_id}/project", method = RequestMethod.GET)
	@ResponseBody
	public Project getCacProject(@PathVariable Integer cac_id) {
		return cacDAO.findCacByPrimaryKey(cac_id).getRelativeProject();
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacdevices", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacdevice> getCacCacdevices(@PathVariable Integer cac_id) {
		return new java.util.ArrayList<Cacdevice>(cacDAO.findCacByPrimaryKey(
				cac_id).getRelativeCacdevices());
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacsensors", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacsensor> getCacCacsensors(@PathVariable Integer cac_id) {
		return new java.util.ArrayList<Cacsensor>(cacDAO.findCacByPrimaryKey(
				cac_id).getRelativeCacsensors());
	}

	@RequestMapping(value = "/Cac", method = RequestMethod.GET)
	@ResponseBody
	public List<Cac> listCacs() {
		return new java.util.ArrayList<Cac>(cacService.loadCacs());
	}

	@RequestMapping(value = "/Cac/{cac_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cac loadCac(@PathVariable Integer cac_id) {
		return cacDAO.findCacByPrimaryKey(cac_id);
	}

	@RequestMapping(value = "/Cac/{cac_id}/project/{relative_project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadCacProject(@PathVariable Integer cac_id,
			@PathVariable Integer relative_project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(
				relative_project_id, -1, -1);

		return project;
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacdevices/{relative_cacdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacdevice loadCacCacdevices(@PathVariable Integer cac_id,
			@PathVariable Integer relative_cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				relative_cacdevice_id, -1, -1);

		return cacdevice;
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacsensors/{relative_cacsensor_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensor loadCacCacsensors(@PathVariable Integer cac_id,
			@PathVariable Integer relative_cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				relative_cacsensor_id, -1, -1);

		return cacsensor;
	}

	@RequestMapping(value = "/Cac", method = RequestMethod.POST)
	@ResponseBody
	public Cac newCac(@RequestBody Cac cac) {
		cacService.saveCac(cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/project", method = RequestMethod.POST)
	@ResponseBody
	public Project newCacProject(@PathVariable Integer cac_id,
			@RequestBody Project project) {
		cacService.saveCacProject(cac_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacdevices", method = RequestMethod.POST)
	@ResponseBody
	public Cacdevice newCacCacdevices(@PathVariable Integer cac_id,
			@RequestBody Cacdevice cacdevice) {
		cacService.saveCacCacdevices(cac_id, cacdevice);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacsensors", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensor newCacCacsensors(@PathVariable Integer cac_id,
			@RequestBody Cacsensor cacsensor) {
		cacService.saveCacCacsensors(cac_id, cacsensor);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor.getId());
	}

	@RequestMapping(value = "/Cac", method = RequestMethod.PUT)
	@ResponseBody
	public Cac saveCac(@RequestBody Cac cac) {
		cacService.saveCac(cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/project", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveCacProject(@PathVariable Integer cac_id,
			@RequestBody Project project) {
		cacService.saveCacProject(cac_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacdevices", method = RequestMethod.PUT)
	@ResponseBody
	public Cacdevice saveCacCacdevices(@PathVariable Integer cac_id,
			@RequestBody Cacdevice cacdevices) {
		cacService.saveCacCacdevices(cac_id, cacdevices);
		return cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevices.getId());
	}

	@RequestMapping(value = "/Cac/{cac_id}/cacsensors", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensor saveCacCacsensors(@PathVariable Integer cac_id,
			@RequestBody Cacsensor cacsensors) {
		cacService.saveCacCacsensors(cac_id, cacsensors);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensors.getId());
	}
}