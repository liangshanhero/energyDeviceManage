package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CompanyDAO;

import cn.edu.scau.cmi.service.CompanyService;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.Set;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;
import cn.edu.scau.cmi.service.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("CompanyController")
public class CompanyController extends CmiController {
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

	//===========================集成之后添加的内容==========================

	@RequestMapping("/showdataRequestCompanys")
	public ModelAndView showdataRequestCompanys(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("companys", companyService.loadCompanys());
		mav.setViewName("company/showdataRequestCompany.jsp");
		System.out.println("获取的信息：" + companyService.loadCompanys());
		return mav;
	}
	
	
	@RequestMapping("/listStaffAllCompanys")
	public ModelAndView listStaffCompanys(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("companys", companyService.loadCompanys());
		mav.setViewName("company/listStaffCompanys.jsp");
		System.out.println("获取的信息：" + companyService.loadCompanys());
		return mav;
	}

	@RequestMapping("/listAllCompanys")
	public ModelAndView listAllCompanys(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("companys", companyService.loadCompanys());
		mav.setViewName("company/listAllCompanys.jsp");
		System.out.println("获取的信息：" + companyService.loadCompanys());
		return mav;
	}

	//============================以上是添加进去的代码=====================================

	@RequestMapping("/indexCompany")
	public ModelAndView listCompanys(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = companyService.countcompanys() / pageSize;
		String pageType = request.getParameter("page");

		/**
		 * @author _TESLA_
		 * 避免报空指针的临时补救措施
		 */
		pageType = pageType == null ? "homePage" : pageType;

		if (pageType.equals("homePage"))
			pageNumber = 0;

		else if (pageType.equals("previousPage")) {
			if (pageNumber >= 1)
				pageNumber = pageNumber - 1;
			else
				pageNumber = 0;
		}

		else if (pageType.equals("nextPage")) {
			if (pageNumber < totalPage)
				pageNumber = pageNumber + 1;
			else
				pageNumber = totalPage;
		}

		else if (pageType.equals("lastPage"))
			pageNumber = totalPage;

		else if (pageType.equals("jumpPage")) {
			pageNumber = Integer.parseInt(request.getParameter("WantToPage")) - 1;
		}

		else if (pageType.equals("eachPageShow")) {
			pageNumber = 0;
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			totalPage = companyService.countcompanys() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("companys", companyService.loadCompanys(pageNumber
					* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("company/listCompanys.jsp");
		return mav;
	}

	public String indexCompany() {
		return "redirect:/indexCompany";
	}

	@RequestMapping("/selectCompany")
	public ModelAndView selectCompany(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int projectTotalPage = projectService.countRelativeCompanyProjects(id)
				/ projectPageSize;
		String projectPageType = request.getParameter("projectPage");
		switch (projectPageType) {
		case "homePage":
			projectPageNumber = 0;
			break;
		case "previousPage":
			if (projectPageNumber >= 1)
				projectPageNumber = projectPageNumber - 1;
			else
				projectPageNumber = 0;
			break;
		case "nextPage":
			if (projectPageNumber < projectTotalPage)
				projectPageNumber = projectPageNumber + 1;
			else
				projectPageNumber = projectTotalPage;
			break;
		case "lastPage":
			projectPageNumber = projectTotalPage;
			break;
		case "jumpPage":
			projectPageNumber = Integer.parseInt(request
					.getParameter("projectWantToPage")) - 1;
			break;
		case "eachPageShow":
			projectPageNumber = 0;
			projectPageSize = Integer.parseInt(request
					.getParameter("projectPageSize"));
			projectTotalPage = projectService.countRelativeCompanyProjects(id)
					/ projectPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (projectPageNumber >= 0 && projectPageNumber <= projectTotalPage)
			mav.addObject(
					"projects",
					projectDAO.findCompanyProjects(id, projectPageNumber
							* projectPageSize, projectPageSize));
		mav.addObject("projectPageNumber", projectPageNumber);
		mav.addObject("projectPage", projectPageType);
		mav.addObject("projectWantToPagePage",
				request.getParameter("projectWantToPage"));
		mav.addObject("projectPageSize", projectPageSize);
		mav.addObject("projectTotalPage", projectTotalPage);
		int staffTotalPage = staffService.countRelativeCompanyStaffs(id)
				/ staffPageSize;
		String staffPageType = request.getParameter("staffPage");
		switch (staffPageType) {
		case "homePage":
			staffPageNumber = 0;
			break;
		case "previousPage":
			if (staffPageNumber >= 1)
				staffPageNumber = staffPageNumber - 1;
			else
				staffPageNumber = 0;
			break;
		case "nextPage":
			if (staffPageNumber < staffTotalPage)
				staffPageNumber = staffPageNumber + 1;
			else
				staffPageNumber = staffTotalPage;
			break;
		case "lastPage":
			staffPageNumber = staffTotalPage;
			break;
		case "jumpPage":
			staffPageNumber = Integer.parseInt(request
					.getParameter("staffWantToPage")) - 1;
			break;
		case "eachPageShow":
			staffPageNumber = 0;
			staffPageSize = Integer.parseInt(request
					.getParameter("staffPageSize"));
			staffTotalPage = staffService.countRelativeCompanyStaffs(id)
					/ staffPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (staffPageNumber >= 0 && staffPageNumber <= staffTotalPage)
			mav.addObject(
					"staffs",
					staffDAO.findCompanyStaffs(id, staffPageNumber
							* staffPageSize, staffPageSize));
		mav.addObject("staffPageNumber", staffPageNumber);
		mav.addObject("staffPage", staffPageType);
		mav.addObject("staffWantToPagePage",
				request.getParameter("staffWantToPage"));
		mav.addObject("staffPageSize", staffPageSize);
		mav.addObject("staffTotalPage", staffTotalPage);
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/selectCompanyStaff")
	public ModelAndView selectCompanyStaff(@RequestParam Integer company_id,
			@RequestParam Integer representative_id) {
		Staff representative = staffDAO.findStaffByPrimaryKey(
				representative_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("staff", representative);
		mav.setViewName("company/staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/selectCompanyProjects")
	public ModelAndView selectCompanyProjects(@RequestParam Integer company_id,
			HttpServletRequest request) {
		
		//获取的是项目ID编号；
		
		String projectID=request.getParameter("project_id");
		System.out.println("projectID:"+projectID);
		
		ModelAndView mav = new ModelAndView();
		
		int projectTotalPage = projectService
				.countRelativeCompanyProjects(company_id) / projectPageSize;
		String projectPageType = request.getParameter("page");
		switch (projectPageType) {
		case "homePage":
			projectPageNumber = 0;
			break;
		case "previousPage":
			if (projectPageNumber >= 1)
				projectPageNumber = projectPageNumber - 1;
			else
				projectPageNumber = 0;
			break;
		case "nextPage":
			if (projectPageNumber < projectTotalPage)
				projectPageNumber = projectPageNumber + 1;
			else
				projectPageNumber = projectTotalPage;
			break;
		case "lastPage":
			projectPageNumber = projectTotalPage;
			break;
		case "jumpPage":
			projectPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			projectPageNumber = 0;
			projectPageSize = Integer
					.parseInt(request.getParameter("pageSize"));
			projectTotalPage = projectService
					.countRelativeCompanyProjects(company_id) / projectPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (projectPageNumber >= 0 && projectPageNumber <= projectTotalPage)
			mav.addObject("projects", projectDAO.findCompanyProjects(
					company_id, projectPageNumber * projectPageSize,
					projectPageSize));
		mav.addObject("projectPageNumber", projectPageNumber);
		mav.addObject("projectPageSize", pageSize);
		mav.addObject("projectPage", projectPageType);
		mav.addObject("projectWantToPage", request.getParameter("WantToPage"));
		mav.addObject("projectTotalPage", projectTotalPage);
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(company_id));
		
		//pj添加的内容====start
	    mav.addObject("projectid",projectID );
	    mav.addObject("project",projectDAO.findProjectByPrimaryKey(new Integer(projectID)));
        //==================end==========
	    
		mav.setViewName("company/projects/devices.jsp");
		return mav;
	}
	
	
	@RequestMapping("/selectCompanyStaffs")
	public ModelAndView selectCompanyStaffs(@RequestParam Integer company_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int staffTotalPage = staffService
				.countRelativeCompanyStaffs(company_id) / staffPageSize;
		String staffPageType = request.getParameter("page");
		switch (staffPageType) {
		case "homePage":
			staffPageNumber = 0;
			break;
		case "previousPage":
			if (staffPageNumber >= 1)
				staffPageNumber = staffPageNumber - 1;
			else
				staffPageNumber = 0;
			break;
		case "nextPage":
			if (staffPageNumber < staffTotalPage)
				staffPageNumber = staffPageNumber + 1;
			else
				staffPageNumber = staffTotalPage;
			break;
		case "lastPage":
			staffPageNumber = staffTotalPage;
			break;
		case "jumpPage":
			staffPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			staffPageNumber = 0;
			staffPageSize = Integer.parseInt(request.getParameter("pageSize"));
			staffTotalPage = staffService
					.countRelativeCompanyStaffs(company_id) / staffPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (staffPageNumber >= 0 && staffPageNumber <= staffTotalPage)
			mav.addObject(
					"staffs",
					staffDAO.findCompanyStaffs(company_id, staffPageNumber
							* staffPageSize, staffPageSize));
		mav.addObject("staffPageNumber", staffPageNumber);
		mav.addObject("staffPageSize", pageSize);
		mav.addObject("staffPage", staffPageType);
		mav.addObject("staffWantToPage", request.getParameter("WantToPage"));
		mav.addObject("staffTotalPage", staffTotalPage);
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(company_id));
		mav.setViewName("company/staffs/viewStaffs.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCompany")
	public ModelAndView confirmDeleteCompany(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("company/deleteCompany.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCompanyStaff")
	public ModelAndView confirmDeleteCompanyStaff(
			@RequestParam Integer company_id,
			@RequestParam Integer related_staff_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(related_staff_id));
		mav.addObject("company_id", company_id);

		mav.setViewName("company/staff/deleteStaff.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCompanyProjects")
	public ModelAndView confirmDeleteCompanyProjects(
			@RequestParam Integer company_id,
			@RequestParam Integer related_projects_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project",
				projectDAO.findProjectByPrimaryKey(related_projects_id));
		mav.addObject("company_id", company_id);

		mav.setViewName("company/projects/deleteProjects.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCompanyStaffs")
	public ModelAndView confirmDeleteCompanyStaffs(
			@RequestParam Integer company_id,
			@RequestParam Integer related_staffs_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff",
				staffDAO.findStaffByPrimaryKey(related_staffs_id));
		mav.addObject("company_id", company_id);

		mav.setViewName("company/staffs/deleteStaffs.jsp");
		return mav;
	}

	@RequestMapping("/deleteCompany")
	public String deleteCompany(@RequestParam Integer id) {
		Company company = companyDAO.findCompanyByPrimaryKey(id);
		companyService.deleteCompany(company);
		return "forward:/indexCompany";
	}

	@RequestMapping("/deleteCompanyStaff")
	public ModelAndView deleteCompanyStaff(@RequestParam Integer company_id,
			@RequestParam Integer Relativestaff_id) {
		ModelAndView mav = new ModelAndView();
		Company company = companyService.deleteCompanyStaff(company_id,
				Relativestaff_id);
		mav.addObject("company_id", company_id);

		mav.addObject("company", company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/deleteCompanyProjects")
	public ModelAndView deleteCompanyProjects(@RequestParam Integer company_id,
			@RequestParam Integer Relativeprojects_id) {
		ModelAndView mav = new ModelAndView();
		Company company = companyService.deleteCompanyProjects(company_id,
				Relativeprojects_id);
		mav.addObject("company_id", company_id);

		mav.addObject("company", company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/deleteCompanyStaffs")
	public ModelAndView deleteCompanyStaffs(@RequestParam Integer company_id,
			@RequestParam Integer Relativestaffs_id) {
		ModelAndView mav = new ModelAndView();
		Company company = companyService.deleteCompanyStaffs(company_id,
				Relativestaffs_id);
		mav.addObject("company_id", company_id);

		mav.addObject("company", company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/editCompany")
	public ModelAndView editCompany(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Staff> allRepresentatives = staffDAO.findAllStaffs();
		mav.addObject("allRepresentatives", allRepresentatives);
		Set<Project> allProjects = projectDAO.findAllProjects();
		Set<Project> havedProjects = companyDAO.findCompanyByPrimaryKey(id)
				.getRelativeProjects();
		mav.addObject("allProjects", allProjects);
		mav.addObject("havedProjects", havedProjects);
		Set<Staff> allStaffs = staffDAO.findAllStaffs();
		Set<Staff> havedStaffs = companyDAO.findCompanyByPrimaryKey(id)
				.getRelativeStaffs();
		mav.addObject("allStaffs", allStaffs);
		mav.addObject("havedStaffs", havedStaffs);
		mav.addObject("allCompanys", companyDAO.findAllCompanys());
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("company/editCompany.jsp");
		return mav;
	}

	@RequestMapping("/editCompanyStaff")
	public ModelAndView editCompanyStaff(@RequestParam Integer company_id,
			@RequestParam Integer representative_id) {
		Staff representative = staffDAO.findStaffByPrimaryKey(
				representative_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("staff", representative);
		mav.setViewName("company/staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/editCompanyProjects")
	public ModelAndView editCompanyProjects(@RequestParam Integer company_id,
			@RequestParam Integer projects_id) {
		Project project = projectDAO.findProjectByPrimaryKey(projects_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("project", project);
		mav.setViewName("company/projects/editProjects.jsp");

		return mav;
	}

	@RequestMapping("/editCompanyStaffs")
	public ModelAndView editCompanyStaffs(@RequestParam Integer company_id,
			@RequestParam Integer staffs_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(staffs_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("staff", staff);
		mav.setViewName("company/staffs/editStaffs.jsp");

		return mav;
	}

	@RequestMapping("/newCompany")
	public ModelAndView newCompany() {
		ModelAndView mav = new ModelAndView();

		Set<Staff> allRepresentatives = staffDAO.findAllStaffs();
		mav.addObject("allRepresentatives", allRepresentatives);

		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);
		Set<Staff> allStaffs = staffDAO.findAllStaffs();
		mav.addObject("allStaffs", allStaffs);
		mav.addObject("allCompanys", companyDAO.findAllCompanys());
		mav.addObject("company", new Company());
		mav.addObject("newFlag", true);
		mav.setViewName("company/editCompany.jsp");

		return mav;
	}

	@RequestMapping("/newCompanyStaff")
	public ModelAndView newCompanyStaff(@RequestParam Integer company_id,
			@RequestParam Integer representative_id) {
		Staff representative = staffDAO.findStaffByPrimaryKey(
				representative_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("staff", representative);
		mav.addObject("newFlag", true);
		mav.setViewName("company/staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/newCompanyProjects")
	public ModelAndView newCompanyProjects(@RequestParam Integer company_id,
			@RequestParam Integer projects_id) {
		Project project = projectDAO.findProjectByPrimaryKey(projects_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("project", project);
		mav.addObject("newFlag", true);
		mav.setViewName("company/projects/editProjects.jsp");

		return mav;
	}

	@RequestMapping("/newCompanyStaffs")
	public ModelAndView newCompanyStaffs(@RequestParam Integer company_id,
			@RequestParam Integer staffs_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(staffs_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("staff", staff);
		mav.addObject("newFlag", true);
		mav.setViewName("company/staffs/editStaffs.jsp");

		return mav;
	}

	@RequestMapping("/listCompanyStaff")
	public ModelAndView listCompanyStaff(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("company/staff/listStaff.jsp");

		return mav;
	}

	Staff staff;

	@RequestMapping("/listCompanyProjects")
	public ModelAndView listCompanyProjects(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("company/projects/listProjects.jsp");

		return mav;
	}

	@RequestMapping("/listCompanyStaffs")
	public ModelAndView listCompanyStaffs(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("company/staffs/listStaffs.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveCompany")
	public String saveCompany(@ModelAttribute Company company) {
		companyService.saveCompany(company);

		return "forward:/indexCompany";
	}

	@RequestMapping("/saveCompanyStaff")
	public ModelAndView saveCompanyStaff(@RequestParam Integer company_id,
			@ModelAttribute Staff staff) {
		Company parent_company = companyService.saveCompanyStaff(company_id,
				staff);

		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("company", parent_company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/saveCompanyProjects")
	public ModelAndView saveCompanyProjects(@RequestParam Integer company_id,
			@ModelAttribute Project projects) {
		Company parent_company = companyService.saveCompanyProjects(company_id,
				projects);

		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("company", parent_company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/saveCompanyStaffs")
	public ModelAndView saveCompanyStaffs(@RequestParam Integer company_id,
			@ModelAttribute Staff staffs) {
		Company parent_company = companyService.saveCompanyStaffs(company_id,
				staffs);

		ModelAndView mav = new ModelAndView();
		mav.addObject("company_id", company_id);

		mav.addObject("company", parent_company);
		mav.setViewName("company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/companyController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}