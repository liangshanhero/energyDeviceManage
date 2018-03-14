package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.StaffDAO;

import cn.edu.scau.cmi.service.StaffService;

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

@Controller("StaffRestController")
public class StaffRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int companyPageNumber = 0;
	static int companyPageSize = 10;
	static int projectPageNumber = 0;
	static int projectPageSize = 10;

	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private StaffService staffService;

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@RequestMapping(value = "/Staff/{staff_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteStaff(@PathVariable Integer staff_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(staff_id);
		staffService.deleteStaff(staff);
	}

	@RequestMapping(value = "/Staff/{staff_id}/company/{relative_company_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteStaffCompany(@PathVariable Integer staff_id,
			@PathVariable Integer relative_company_id) {
		staffService.deleteStaffCompany(staff_id, relative_company_id);
	}

	@RequestMapping(value = "/Staff/{staff_id}/companys/{relative_company_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteStaffCompanys(@PathVariable Integer staff_id,
			@PathVariable Integer relative_company_id) {
		staffService.deleteStaffCompanys(staff_id, relative_company_id);
	}

	@RequestMapping(value = "/Staff/{staff_id}/projects/{relative_project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteStaffProjects(@PathVariable Integer staff_id,
			@PathVariable Integer relative_project_id) {
		staffService.deleteStaffProjects(staff_id, relative_project_id);
	}

	@RequestMapping(value = "/Staff/{staff_id}/company", method = RequestMethod.GET)
	@ResponseBody
	public Company getStaffCompany(@PathVariable Integer staff_id) {
		return staffDAO.findStaffByPrimaryKey(staff_id).getRelativeCompany();
	}

	@RequestMapping(value = "/Staff/{staff_id}/companys", method = RequestMethod.GET)
	@ResponseBody
	public List<Company> getStaffCompanys(@PathVariable Integer staff_id) {
		return new java.util.ArrayList<Company>(staffDAO.findStaffByPrimaryKey(
				staff_id).getRelativeCompanys());
	}

	@RequestMapping(value = "/Staff/{staff_id}/projects", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> getStaffProjects(@PathVariable Integer staff_id) {
		return new java.util.ArrayList<Project>(staffDAO.findStaffByPrimaryKey(
				staff_id).getRelativeProjects());
	}

	@RequestMapping(value = "/Staff", method = RequestMethod.GET)
	@ResponseBody
	public List<Staff> listStaffs() {
		return new java.util.ArrayList<Staff>(staffService.loadStaffs());
	}

	@RequestMapping(value = "/Staff/{staff_id}", method = RequestMethod.GET)
	@ResponseBody
	public Staff loadStaff(@PathVariable Integer staff_id) {
		return staffDAO.findStaffByPrimaryKey(staff_id);
	}

	@RequestMapping(value = "/Staff/{staff_id}/company/{relative_company_id}", method = RequestMethod.GET)
	@ResponseBody
	public Company loadStaffCompany(@PathVariable Integer staff_id,
			@PathVariable Integer relative_company_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(
				relative_company_id, -1, -1);

		return company;
	}

	@RequestMapping(value = "/Staff/{staff_id}/companys/{relative_company_id}", method = RequestMethod.GET)
	@ResponseBody
	public Company loadStaffCompanys(@PathVariable Integer staff_id,
			@PathVariable Integer relative_company_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(
				relative_company_id, -1, -1);

		return company;
	}

	@RequestMapping(value = "/Staff/{staff_id}/projects/{relative_project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadStaffProjects(@PathVariable Integer staff_id,
			@PathVariable Integer relative_project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(
				relative_project_id, -1, -1);

		return project;
	}

	@RequestMapping(value = "/Staff", method = RequestMethod.POST)
	@ResponseBody
	public Staff newStaff(@RequestBody Staff staff) {
		staffService.saveStaff(staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/company", method = RequestMethod.POST)
	@ResponseBody
	public Company newStaffCompany(@PathVariable Integer staff_id,
			@RequestBody Company company) {
		staffService.saveStaffCompany(staff_id, company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/companys", method = RequestMethod.POST)
	@ResponseBody
	public Company newStaffCompanys(@PathVariable Integer staff_id,
			@RequestBody Company company) {
		staffService.saveStaffCompanys(staff_id, company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/projects", method = RequestMethod.POST)
	@ResponseBody
	public Project newStaffProjects(@PathVariable Integer staff_id,
			@RequestBody Project project) {
		staffService.saveStaffProjects(staff_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Staff", method = RequestMethod.PUT)
	@ResponseBody
	public Staff saveStaff(@RequestBody Staff staff) {
		staffService.saveStaff(staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/company", method = RequestMethod.PUT)
	@ResponseBody
	public Company saveStaffCompany(@PathVariable Integer staff_id,
			@RequestBody Company company) {
		staffService.saveStaffCompany(staff_id, company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/companys", method = RequestMethod.PUT)
	@ResponseBody
	public Company saveStaffCompanys(@PathVariable Integer staff_id,
			@RequestBody Company companys) {
		staffService.saveStaffCompanys(staff_id, companys);
		return companyDAO.findCompanyByPrimaryKey(companys.getId());
	}

	@RequestMapping(value = "/Staff/{staff_id}/projects", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveStaffProjects(@PathVariable Integer staff_id,
			@RequestBody Project projects) {
		staffService.saveStaffProjects(staff_id, projects);
		return projectDAO.findProjectByPrimaryKey(projects.getId());
	}
}