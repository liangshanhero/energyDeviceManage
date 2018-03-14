package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.ProjectDAO;

import cn.edu.scau.cmi.service.ProjectService;

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

@Controller("ProjectRestController")
public class ProjectRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacPageNumber = 0;
	static int cacPageSize = 10;
	static int ledbuildingPageNumber = 0;
	static int ledbuildingPageSize = 10;
	static int whbuildingPageNumber = 0;
	static int whbuildingPageSize = 10;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CompanyDAO companyDAO;
	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacService cacService;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedbuildingService ledbuildingService;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhbuildingService whbuildingService;

	@RequestMapping(value = "/Project/{project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProject(@PathVariable Integer project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(project_id);
		projectService.deleteProject(project);
	}

	@RequestMapping(value = "/Project/{project_id}/company/{relative_company_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProjectCompany(@PathVariable Integer project_id,
			@PathVariable Integer relative_company_id) {
		projectService.deleteProjectCompany(project_id, relative_company_id);
	}

	@RequestMapping(value = "/Project/{project_id}/staff/{relative_staff_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProjectStaff(@PathVariable Integer project_id,
			@PathVariable Integer relative_staff_id) {
		projectService.deleteProjectStaff(project_id, relative_staff_id);
	}

	@RequestMapping(value = "/Project/{project_id}/cacs/{relative_cac_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProjectCacs(@PathVariable Integer project_id,
			@PathVariable Integer relative_cac_id) {
		projectService.deleteProjectCacs(project_id, relative_cac_id);
	}

	@RequestMapping(value = "/Project/{project_id}/ledbuildings/{relative_ledbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProjectLedbuildings(@PathVariable Integer project_id,
			@PathVariable Integer relative_ledbuilding_id) {
		projectService.deleteProjectLedbuildings(project_id,
				relative_ledbuilding_id);
	}

	@RequestMapping(value = "/Project/{project_id}/whbuildings/{relative_whbuilding_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteProjectWhbuildings(@PathVariable Integer project_id,
			@PathVariable Integer relative_whbuilding_id) {
		projectService.deleteProjectWhbuildings(project_id,
				relative_whbuilding_id);
	}

	@RequestMapping(value = "/Project/{project_id}/company", method = RequestMethod.GET)
	@ResponseBody
	public Company getProjectCompany(@PathVariable Integer project_id) {
		return projectDAO.findProjectByPrimaryKey(project_id)
				.getRelativeCompany();
	}

	@RequestMapping(value = "/Project/{project_id}/staff", method = RequestMethod.GET)
	@ResponseBody
	public Staff getProjectStaff(@PathVariable Integer project_id) {
		return projectDAO.findProjectByPrimaryKey(project_id)
				.getRelativeStaff();
	}

	@RequestMapping(value = "/Project/{project_id}/cacs", method = RequestMethod.GET)
	@ResponseBody
	public List<Cac> getProjectCacs(@PathVariable Integer project_id) {
		return new java.util.ArrayList<Cac>(projectDAO.findProjectByPrimaryKey(
				project_id).getRelativeCacs());
	}

	@RequestMapping(value = "/Project/{project_id}/ledbuildings", method = RequestMethod.GET)
	@ResponseBody
	public List<Ledbuilding> getProjectLedbuildings(
			@PathVariable Integer project_id) {
		return new java.util.ArrayList<Ledbuilding>(projectDAO
				.findProjectByPrimaryKey(project_id).getRelativeLedbuildings());
	}

	@RequestMapping(value = "/Project/{project_id}/whbuildings", method = RequestMethod.GET)
	@ResponseBody
	public List<Whbuilding> getProjectWhbuildings(
			@PathVariable Integer project_id) {
		return new java.util.ArrayList<Whbuilding>(projectDAO
				.findProjectByPrimaryKey(project_id).getRelativeWhbuildings());
	}

	@RequestMapping(value = "/Project", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> listProjects() {
		return new java.util.ArrayList<Project>(projectService.loadProjects());
	}

	@RequestMapping(value = "/Project/{project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadProject(@PathVariable Integer project_id) {
		return projectDAO.findProjectByPrimaryKey(project_id);
	}

	@RequestMapping(value = "/Project/{project_id}/company/{relative_company_id}", method = RequestMethod.GET)
	@ResponseBody
	public Company loadProjectCompany(@PathVariable Integer project_id,
			@PathVariable Integer relative_company_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(
				relative_company_id, -1, -1);

		return company;
	}

	@RequestMapping(value = "/Project/{project_id}/staff/{relative_staff_id}", method = RequestMethod.GET)
	@ResponseBody
	public Staff loadProjectStaff(@PathVariable Integer project_id,
			@PathVariable Integer relative_staff_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(relative_staff_id, -1, -1);

		return staff;
	}

	@RequestMapping(value = "/Project/{project_id}/cacs/{relative_cac_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cac loadProjectCacs(@PathVariable Integer project_id,
			@PathVariable Integer relative_cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(relative_cac_id, -1, -1);

		return cac;
	}

	@RequestMapping(value = "/Project/{project_id}/ledbuildings/{relative_ledbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Ledbuilding loadProjectLedbuildings(
			@PathVariable Integer project_id,
			@PathVariable Integer relative_ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				relative_ledbuilding_id, -1, -1);

		return ledbuilding;
	}

	@RequestMapping(value = "/Project/{project_id}/whbuildings/{relative_whbuilding_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whbuilding loadProjectWhbuildings(@PathVariable Integer project_id,
			@PathVariable Integer relative_whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				relative_whbuilding_id, -1, -1);

		return whbuilding;
	}

	@RequestMapping(value = "/Project", method = RequestMethod.POST)
	@ResponseBody
	public Project newProject(@RequestBody Project project) {
		projectService.saveProject(project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/company", method = RequestMethod.POST)
	@ResponseBody
	public Company newProjectCompany(@PathVariable Integer project_id,
			@RequestBody Company company) {
		projectService.saveProjectCompany(project_id, company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/staff", method = RequestMethod.POST)
	@ResponseBody
	public Staff newProjectStaff(@PathVariable Integer project_id,
			@RequestBody Staff staff) {
		projectService.saveProjectStaff(project_id, staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/cacs", method = RequestMethod.POST)
	@ResponseBody
	public Cac newProjectCacs(@PathVariable Integer project_id,
			@RequestBody Cac cac) {
		projectService.saveProjectCacs(project_id, cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/ledbuildings", method = RequestMethod.POST)
	@ResponseBody
	public Ledbuilding newProjectLedbuildings(@PathVariable Integer project_id,
			@RequestBody Ledbuilding ledbuilding) {
		projectService.saveProjectLedbuildings(project_id, ledbuilding);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/whbuildings", method = RequestMethod.POST)
	@ResponseBody
	public Whbuilding newProjectWhbuildings(@PathVariable Integer project_id,
			@RequestBody Whbuilding whbuilding) {
		projectService.saveProjectWhbuildings(project_id, whbuilding);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding.getId());
	}

	@RequestMapping(value = "/Project", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveProject(@RequestBody Project project) {
		projectService.saveProject(project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/company", method = RequestMethod.PUT)
	@ResponseBody
	public Company saveProjectCompany(@PathVariable Integer project_id,
			@RequestBody Company company) {
		projectService.saveProjectCompany(project_id, company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/staff", method = RequestMethod.PUT)
	@ResponseBody
	public Staff saveProjectStaff(@PathVariable Integer project_id,
			@RequestBody Staff staff) {
		projectService.saveProjectStaff(project_id, staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/cacs", method = RequestMethod.PUT)
	@ResponseBody
	public Cac saveProjectCacs(@PathVariable Integer project_id,
			@RequestBody Cac cacs) {
		projectService.saveProjectCacs(project_id, cacs);
		return cacDAO.findCacByPrimaryKey(cacs.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/ledbuildings", method = RequestMethod.PUT)
	@ResponseBody
	public Ledbuilding saveProjectLedbuildings(
			@PathVariable Integer project_id,
			@RequestBody Ledbuilding ledbuildings) {
		projectService.saveProjectLedbuildings(project_id, ledbuildings);
		return ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuildings.getId());
	}

	@RequestMapping(value = "/Project/{project_id}/whbuildings", method = RequestMethod.PUT)
	@ResponseBody
	public Whbuilding saveProjectWhbuildings(@PathVariable Integer project_id,
			@RequestBody Whbuilding whbuildings) {
		projectService.saveProjectWhbuildings(project_id, whbuildings);
		return whbuildingDAO.findWhbuildingByPrimaryKey(whbuildings.getId());
	}
}