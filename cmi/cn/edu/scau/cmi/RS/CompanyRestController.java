package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CompanyDAO;

import cn.edu.scau.cmi.service.CompanyService;

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

@Controller("CompanyRestController")
public class CompanyRestController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int projectPageNumber = 0;
	static int projectPageSize = 10;
	static int staffPageNumber = 0;
	static int staffPageSize = 10;

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private StaffService staffService;

	@RequestMapping(value = "/Company/{company_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCompany(@PathVariable Integer company_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(company_id);
		companyService.deleteCompany(company);
	}

	@RequestMapping(value = "/Company/{company_id}/staff/{relative_staff_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCompanyStaff(@PathVariable Integer company_id,
			@PathVariable Integer relative_staff_id) {
		companyService.deleteCompanyStaff(company_id, relative_staff_id);
	}

	@RequestMapping(value = "/Company/{company_id}/projects/{relative_project_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCompanyProjects(@PathVariable Integer company_id,
			@PathVariable Integer relative_project_id) {
		companyService.deleteCompanyProjects(company_id, relative_project_id);
	}

	@RequestMapping(value = "/Company/{company_id}/staffs/{relative_staff_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCompanyStaffs(@PathVariable Integer company_id,
			@PathVariable Integer relative_staff_id) {
		companyService.deleteCompanyStaffs(company_id, relative_staff_id);
	}

	@RequestMapping(value = "/Company/{company_id}/staff", method = RequestMethod.GET)
	@ResponseBody
	public Staff getCompanyStaff(@PathVariable Integer company_id) {
		return companyDAO.findCompanyByPrimaryKey(company_id)
				.getRelativeStaff();
	}

	@RequestMapping(value = "/Company/{company_id}/projects", method = RequestMethod.GET)
	@ResponseBody
	public List<Project> getCompanyProjects(@PathVariable Integer company_id) {
		return new java.util.ArrayList<Project>(companyDAO
				.findCompanyByPrimaryKey(company_id).getRelativeProjects());
	}

	@RequestMapping(value = "/Company/{company_id}/staffs", method = RequestMethod.GET)
	@ResponseBody
	public List<Staff> getCompanyStaffs(@PathVariable Integer company_id) {
		return new java.util.ArrayList<Staff>(companyDAO
				.findCompanyByPrimaryKey(company_id).getRelativeStaffs());
	}

	@RequestMapping(value = "/Company", method = RequestMethod.GET)
	@ResponseBody
	public List<Company> listCompanys() {
		return new java.util.ArrayList<Company>(companyService.loadCompanys());
	}

	@RequestMapping(value = "/Company/{company_id}", method = RequestMethod.GET)
	@ResponseBody
	public Company loadCompany(@PathVariable Integer company_id) {
		return companyDAO.findCompanyByPrimaryKey(company_id);
	}

	@RequestMapping(value = "/Company/{company_id}/staff/{relative_staff_id}", method = RequestMethod.GET)
	@ResponseBody
	public Staff loadCompanyStaff(@PathVariable Integer company_id,
			@PathVariable Integer relative_staff_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(relative_staff_id, -1, -1);

		return staff;
	}

	@RequestMapping(value = "/Company/{company_id}/projects/{relative_project_id}", method = RequestMethod.GET)
	@ResponseBody
	public Project loadCompanyProjects(@PathVariable Integer company_id,
			@PathVariable Integer relative_project_id) {
		Project project = projectDAO.findProjectByPrimaryKey(
				relative_project_id, -1, -1);

		return project;
	}

	@RequestMapping(value = "/Company/{company_id}/staffs/{relative_staff_id}", method = RequestMethod.GET)
	@ResponseBody
	public Staff loadCompanyStaffs(@PathVariable Integer company_id,
			@PathVariable Integer relative_staff_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(relative_staff_id, -1, -1);

		return staff;
	}

	@RequestMapping(value = "/Company", method = RequestMethod.POST)
	@ResponseBody
	public Company newCompany(@RequestBody Company company) {
		companyService.saveCompany(company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/staff", method = RequestMethod.POST)
	@ResponseBody
	public Staff newCompanyStaff(@PathVariable Integer company_id,
			@RequestBody Staff staff) {
		companyService.saveCompanyStaff(company_id, staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/projects", method = RequestMethod.POST)
	@ResponseBody
	public Project newCompanyProjects(@PathVariable Integer company_id,
			@RequestBody Project project) {
		companyService.saveCompanyProjects(company_id, project);
		return projectDAO.findProjectByPrimaryKey(project.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/staffs", method = RequestMethod.POST)
	@ResponseBody
	public Staff newCompanyStaffs(@PathVariable Integer company_id,
			@RequestBody Staff staff) {
		companyService.saveCompanyStaffs(company_id, staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Company", method = RequestMethod.PUT)
	@ResponseBody
	public Company saveCompany(@RequestBody Company company) {
		companyService.saveCompany(company);
		return companyDAO.findCompanyByPrimaryKey(company.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/staff", method = RequestMethod.PUT)
	@ResponseBody
	public Staff saveCompanyStaff(@PathVariable Integer company_id,
			@RequestBody Staff staff) {
		companyService.saveCompanyStaff(company_id, staff);
		return staffDAO.findStaffByPrimaryKey(staff.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/projects", method = RequestMethod.PUT)
	@ResponseBody
	public Project saveCompanyProjects(@PathVariable Integer company_id,
			@RequestBody Project projects) {
		companyService.saveCompanyProjects(company_id, projects);
		return projectDAO.findProjectByPrimaryKey(projects.getId());
	}

	@RequestMapping(value = "/Company/{company_id}/staffs", method = RequestMethod.PUT)
	@ResponseBody
	public Staff saveCompanyStaffs(@PathVariable Integer company_id,
			@RequestBody Staff staffs) {
		companyService.saveCompanyStaffs(company_id, staffs);
		return staffDAO.findStaffByPrimaryKey(staffs.getId());
	}
}