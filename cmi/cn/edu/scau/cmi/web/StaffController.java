package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.StaffDAO;

import cn.edu.scau.cmi.service.StaffService;

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

/*forward是在控制器中的跳转;
 *redirect是跳转到页面中去了; 
*/

@Controller("StaffController")
public class StaffController extends CmiController {
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

	/*
	 * 后添加的内容==========
	 */

	@RequestMapping("/listAllStaffs")
	public ModelAndView listAllStaffs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staffs", staffService.loadStaffs());
		mav.setViewName("staff/listAllStaffs.jsp");
		return mav;
	}

	// =============================================================
	// end of file

	@RequestMapping("/indexStaff")
	public ModelAndView listStaffs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = staffService.countstaffs() / pageSize;
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
			totalPage = staffService.countstaffs() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("staffs",
					staffService.loadStaffs(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("staff/listStaffs.jsp");
		return mav;
	}

	public String indexStaff() {
		return "redirect:/indexStaff";
	}

	@RequestMapping("/selectStaff")
	public ModelAndView selectStaff(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int companyTotalPage = companyService.countRelativeStaffCompanys(id)
				/ companyPageSize;
		String companyPageType = request.getParameter("companyPage");
		switch (companyPageType) {
		case "homePage":
			companyPageNumber = 0;
			break;
		case "previousPage":
			if (companyPageNumber >= 1)
				companyPageNumber = companyPageNumber - 1;
			else
				companyPageNumber = 0;
			break;
		case "nextPage":
			if (companyPageNumber < companyTotalPage)
				companyPageNumber = companyPageNumber + 1;
			else
				companyPageNumber = companyTotalPage;
			break;
		case "lastPage":
			companyPageNumber = companyTotalPage;
			break;
		case "jumpPage":
			companyPageNumber = Integer.parseInt(request
					.getParameter("companyWantToPage")) - 1;
			break;
		case "eachPageShow":
			companyPageNumber = 0;
			companyPageSize = Integer.parseInt(request
					.getParameter("companyPageSize"));
			companyTotalPage = companyService.countRelativeStaffCompanys(id)
					/ companyPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (companyPageNumber >= 0 && companyPageNumber <= companyTotalPage)
			mav.addObject(
					"companys",
					companyDAO.findStaffCompanys(id, companyPageNumber
							* companyPageSize, companyPageSize));
		mav.addObject("companyPageNumber", companyPageNumber);
		mav.addObject("companyPage", companyPageType);
		mav.addObject("companyWantToPagePage",
				request.getParameter("companyWantToPage"));
		mav.addObject("companyPageSize", companyPageSize);
		mav.addObject("companyTotalPage", companyTotalPage);
		int projectTotalPage = projectService.countRelativeStaffProjects(id)
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
			projectTotalPage = projectService.countRelativeStaffProjects(id)
					/ projectPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (projectPageNumber >= 0 && projectPageNumber <= projectTotalPage)
			mav.addObject(
					"projects",
					projectDAO.findStaffProjects(id, projectPageNumber
							* projectPageSize, projectPageSize));
		mav.addObject("projectPageNumber", projectPageNumber);
		mav.addObject("projectPage", projectPageType);
		mav.addObject("projectWantToPagePage",
				request.getParameter("projectWantToPage"));
		mav.addObject("projectPageSize", projectPageSize);
		mav.addObject("projectTotalPage", projectTotalPage);
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/selectStaffCompany")
	public ModelAndView selectStaffCompany(@RequestParam Integer staff_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("company", company);
		mav.setViewName("staff/company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/selectStaffCompanys")
	public ModelAndView selectStaffCompanys(@RequestParam Integer staff_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int companyTotalPage = companyService
				.countRelativeStaffCompanys(staff_id) / companyPageSize;
		String companyPageType = request.getParameter("page");
		switch (companyPageType) {
		case "homePage":
			companyPageNumber = 0;
			break;
		case "previousPage":
			if (companyPageNumber >= 1)
				companyPageNumber = companyPageNumber - 1;
			else
				companyPageNumber = 0;
			break;
		case "nextPage":
			if (companyPageNumber < companyTotalPage)
				companyPageNumber = companyPageNumber + 1;
			else
				companyPageNumber = companyTotalPage;
			break;
		case "lastPage":
			companyPageNumber = companyTotalPage;
			break;
		case "jumpPage":
			companyPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			companyPageNumber = 0;
			companyPageSize = Integer
					.parseInt(request.getParameter("pageSize"));
			companyTotalPage = companyService
					.countRelativeStaffCompanys(staff_id) / companyPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (companyPageNumber >= 0 && companyPageNumber <= companyTotalPage)
			mav.addObject(
					"companys",
					companyDAO.findStaffCompanys(staff_id, companyPageNumber
							* companyPageSize, companyPageSize));
		mav.addObject("companyPageNumber", companyPageNumber);
		mav.addObject("companyPageSize", pageSize);
		mav.addObject("companyPage", companyPageType);
		mav.addObject("companyWantToPage", request.getParameter("WantToPage"));
		mav.addObject("companyTotalPage", companyTotalPage);
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(staff_id));
		mav.setViewName("staff/companys/viewCompanys.jsp");

		return mav;
	}

	@RequestMapping("/selectStaffProjects")
	public ModelAndView selectStaffProjects(@RequestParam Integer staff_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int projectTotalPage = projectService
				.countRelativeStaffProjects(staff_id) / projectPageSize;
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
					.countRelativeStaffProjects(staff_id) / projectPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (projectPageNumber >= 0 && projectPageNumber <= projectTotalPage)
			mav.addObject(
					"projects",
					projectDAO.findStaffProjects(staff_id, projectPageNumber
							* projectPageSize, projectPageSize));
		mav.addObject("projectPageNumber", projectPageNumber);
		mav.addObject("projectPageSize", pageSize);
		mav.addObject("projectPage", projectPageType);
		mav.addObject("projectWantToPage", request.getParameter("WantToPage"));
		mav.addObject("projectTotalPage", projectTotalPage);
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(staff_id));
		mav.setViewName("staff/projects/viewProjects.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteStaff")
	public ModelAndView confirmDeleteStaff(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("staff/deleteStaff.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteStaffCompany")
	public ModelAndView confirmDeleteStaffCompany(
			@RequestParam Integer staff_id,
			@RequestParam Integer related_company_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company",
				companyDAO.findCompanyByPrimaryKey(related_company_id));
		mav.addObject("staff_id", staff_id);

		mav.setViewName("staff/company/deleteCompany.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteStaffCompanys")
	public ModelAndView confirmDeleteStaffCompanys(
			@RequestParam Integer staff_id,
			@RequestParam Integer related_companys_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company",
				companyDAO.findCompanyByPrimaryKey(related_companys_id));
		mav.addObject("staff_id", staff_id);

		mav.setViewName("staff/companys/deleteCompanys.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteStaffProjects")
	public ModelAndView confirmDeleteStaffProjects(
			@RequestParam Integer staff_id,
			@RequestParam Integer related_projects_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project",
				projectDAO.findProjectByPrimaryKey(related_projects_id));
		mav.addObject("staff_id", staff_id);

		mav.setViewName("staff/projects/deleteProjects.jsp");
		return mav;
	}
	
//==================修改过的代码=======
	@RequestMapping("/deleteStaff")
	public String deleteStaff(@RequestParam Integer id) {
		System.out.println(id);
		Staff staff = staffDAO.findStaffByPrimaryKey(id);
		System.out.println(staff);
		if(staff!=null||null!=staff)
		staffService.deleteStaff(staff);
		return "forward:/listAllStaffs";
		/*return "forward:/indexStaff";*/
	}
//================End修改过的代码===========
	
	
	@RequestMapping("/deleteStaffCompany")
	public ModelAndView deleteStaffCompany(@RequestParam Integer staff_id,
			@RequestParam Integer Relativecompany_id) {
		ModelAndView mav = new ModelAndView();
		Staff staff = staffService.deleteStaffCompany(staff_id,
				Relativecompany_id);
		mav.addObject("staff_id", staff_id);

		mav.addObject("staff", staff);
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/deleteStaffCompanys")
	public ModelAndView deleteStaffCompanys(@RequestParam Integer staff_id,
			@RequestParam Integer Relativecompanys_id) {
		ModelAndView mav = new ModelAndView();
		Staff staff = staffService.deleteStaffCompanys(staff_id,
				Relativecompanys_id);
		mav.addObject("staff_id", staff_id);

		mav.addObject("staff", staff);
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/deleteStaffProjects")
	public ModelAndView deleteStaffProjects(@RequestParam Integer staff_id,
			@RequestParam Integer Relativeprojects_id) {
		ModelAndView mav = new ModelAndView();
		Staff staff = staffService.deleteStaffProjects(staff_id,
				Relativeprojects_id);
		mav.addObject("staff_id", staff_id);

		mav.addObject("staff", staff);
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/editStaff")
	public ModelAndView editStaff(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Company> allCompanys = companyDAO.findAllCompanys();
		mav.addObject("allCompanys", allCompanys);

		Set<Company> havedCompanys = staffDAO.findStaffByPrimaryKey(id)
				.getRelativeCompanys();
		mav.addObject("allCompanys", allCompanys);
		mav.addObject("havedCompanys", havedCompanys);
		Set<Project> allProjects = projectDAO.findAllProjects();
		Set<Project> havedProjects = staffDAO.findStaffByPrimaryKey(id)
				.getRelativeProjects();
		mav.addObject("allProjects", allProjects);
		mav.addObject("havedProjects", havedProjects);
		mav.addObject("allStaffs", staffDAO.findAllStaffs());
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/editStaffCompany")
	public ModelAndView editStaffCompany(@RequestParam Integer staff_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("company", company);
		mav.setViewName("staff/company/editCompany.jsp");

		return mav;
	}

	@RequestMapping("/editStaffCompanys")
	public ModelAndView editStaffCompanys(@RequestParam Integer staff_id,
			@RequestParam Integer companys_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(companys_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("company", company);
		mav.setViewName("staff/companys/editCompanys.jsp");

		return mav;
	}

	@RequestMapping("/editStaffProjects")
	public ModelAndView editStaffProjects(@RequestParam Integer staff_id,
			@RequestParam Integer projects_id) {
		Project project = projectDAO.findProjectByPrimaryKey(projects_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("project", project);
		mav.setViewName("staff/projects/editProjects.jsp");

		return mav;
	}

	@RequestMapping("/newStaff")
	public ModelAndView newStaff() {
		ModelAndView mav = new ModelAndView();

		Set<Company> allCompanys = companyDAO.findAllCompanys();
		mav.addObject("allCompanys", allCompanys);

		mav.addObject("allCompanys", allCompanys);
		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);
		mav.addObject("allStaffs", staffDAO.findAllStaffs());
		mav.addObject("staff", new Staff());
		mav.addObject("newFlag", true);
		mav.setViewName("staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/newStaffCompany")
	public ModelAndView newStaffCompany(@RequestParam Integer staff_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("company", company);
		mav.addObject("newFlag", true);
		mav.setViewName("staff/company/editCompany.jsp");

		return mav;
	}

	@RequestMapping("/newStaffCompanys")
	public ModelAndView newStaffCompanys(@RequestParam Integer staff_id,
			@RequestParam Integer companys_id) {
		Company company = companyDAO.findCompanyByPrimaryKey(companys_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("company", company);
		mav.addObject("newFlag", true);
		mav.setViewName("staff/companys/editCompanys.jsp");

		return mav;
	}

	@RequestMapping("/newStaffProjects")
	public ModelAndView newStaffProjects(@RequestParam Integer staff_id,
			@RequestParam Integer projects_id) {
		Project project = projectDAO.findProjectByPrimaryKey(projects_id, -1,
				-1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("project", project);
		mav.addObject("newFlag", true);
		mav.setViewName("staff/projects/editProjects.jsp");

		return mav;
	}

	@RequestMapping("/listStaffCompany")
	public ModelAndView listStaffCompany(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("staff/company/listCompany.jsp");

		return mav;
	}

	Company company;

	@RequestMapping("/listStaffCompanys")
	public ModelAndView listStaffCompanys(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("staff/companys/listCompanys.jsp");

		return mav;
	}

	@RequestMapping("/listStaffProjects")
	public ModelAndView listStaffProjects(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("staff/projects/listProjects.jsp");

		return mav;
	}
//=====================修改过的代码-===============
	@Scope("prototype")
	@RequestMapping("/saveStaff")
	public String saveStaff(@ModelAttribute Staff staff) {
		staffService.saveStaff(staff);
		return "forward:/listAllStaffs";
		/*return "forward:staff/listAllStaffs.jsp";*/
	}
/*===================End 修改过的代码=============*/	
	
	@RequestMapping("/saveStaffCompany")
	public ModelAndView saveStaffCompany(@RequestParam Integer staff_id,
			@ModelAttribute Company company) {
		Staff parent_staff = staffService.saveStaffCompany(staff_id, company);
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);
		mav.addObject("staff", parent_staff);
		mav.setViewName("staff/viewStaff.jsp");
		return mav;
	}

	@RequestMapping("/saveStaffCompanys")
	public ModelAndView saveStaffCompanys(@RequestParam Integer staff_id,
			@ModelAttribute Company companys) {
		Staff parent_staff = staffService.saveStaffCompanys(staff_id, companys);

		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("staff", parent_staff);
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/saveStaffProjects")
	public ModelAndView saveStaffProjects(@RequestParam Integer staff_id,
			@ModelAttribute Project projects) {
		Staff parent_staff = staffService.saveStaffProjects(staff_id, projects);

		ModelAndView mav = new ModelAndView();
		mav.addObject("staff_id", staff_id);

		mav.addObject("staff", parent_staff);
		mav.setViewName("staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/staffController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}