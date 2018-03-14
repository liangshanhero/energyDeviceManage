package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.ProjectDAO;

import cn.edu.scau.cmi.service.ProjectService;

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

@Controller("ProjectController")
public class ProjectController extends CmiController {
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

	//添加上去的内容代码
	@RequestMapping("/lisAlltProjects")
	public ModelAndView lisAlltProjects(HttpServletRequest request)
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("projects", projectService.loadProjects());
		mav.setViewName("project/listAllProjects.jsp");
		return mav;
	}

	//以上是添加的代码内容
	
	
	@RequestMapping("/indexProject")
	public ModelAndView listProjects(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = projectService.countprojects() / pageSize;
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
			totalPage = projectService.countprojects() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("projects", projectService.loadProjects(pageNumber
					* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("project/listProjects.jsp");
		return mav;
	}

	public String indexProject() {
		return "redirect:/indexProject";
	}

	@RequestMapping("/selectProject")
	public ModelAndView selectProject(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacTotalPage = cacService.countRelativeProjectCacs(id)
				/ cacPageSize;
		String cacPageType = request.getParameter("cacPage");
		switch (cacPageType) {
		case "homePage":
			cacPageNumber = 0;
			break;
		case "previousPage":
			if (cacPageNumber >= 1)
				cacPageNumber = cacPageNumber - 1;
			else
				cacPageNumber = 0;
			break;
		case "nextPage":
			if (cacPageNumber < cacTotalPage)
				cacPageNumber = cacPageNumber + 1;
			else
				cacPageNumber = cacTotalPage;
			break;
		case "lastPage":
			cacPageNumber = cacTotalPage;
			break;
		case "jumpPage":
			cacPageNumber = Integer.parseInt(request
					.getParameter("cacWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacPageNumber = 0;
			cacPageSize = Integer.parseInt(request.getParameter("cacPageSize"));
			cacTotalPage = cacService.countRelativeProjectCacs(id)
					/ cacPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacPageNumber >= 0 && cacPageNumber <= cacTotalPage)
			mav.addObject("cacs", cacDAO.findProjectCacs(id, cacPageNumber
					* cacPageSize, cacPageSize));
		mav.addObject("cacPageNumber", cacPageNumber);
		mav.addObject("cacPage", cacPageType);
		mav.addObject("cacWantToPagePage",
				request.getParameter("cacWantToPage"));
		mav.addObject("cacPageSize", cacPageSize);
		mav.addObject("cacTotalPage", cacTotalPage);
		int ledbuildingTotalPage = ledbuildingService
				.countRelativeProjectLedbuildings(id) / ledbuildingPageSize;
		String ledbuildingPageType = request.getParameter("ledbuildingPage");
		switch (ledbuildingPageType) {
		case "homePage":
			ledbuildingPageNumber = 0;
			break;
		case "previousPage":
			if (ledbuildingPageNumber >= 1)
				ledbuildingPageNumber = ledbuildingPageNumber - 1;
			else
				ledbuildingPageNumber = 0;
			break;
		case "nextPage":
			if (ledbuildingPageNumber < ledbuildingTotalPage)
				ledbuildingPageNumber = ledbuildingPageNumber + 1;
			else
				ledbuildingPageNumber = ledbuildingTotalPage;
			break;
		case "lastPage":
			ledbuildingPageNumber = ledbuildingTotalPage;
			break;
		case "jumpPage":
			ledbuildingPageNumber = Integer.parseInt(request
					.getParameter("ledbuildingWantToPage")) - 1;
			break;
		case "eachPageShow":
			ledbuildingPageNumber = 0;
			ledbuildingPageSize = Integer.parseInt(request
					.getParameter("ledbuildingPageSize"));
			ledbuildingTotalPage = ledbuildingService
					.countRelativeProjectLedbuildings(id) / ledbuildingPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledbuildingPageNumber >= 0
				&& ledbuildingPageNumber <= ledbuildingTotalPage)
			mav.addObject("ledbuildings", ledbuildingDAO
					.findProjectLedbuildings(id, ledbuildingPageNumber
							* ledbuildingPageSize, ledbuildingPageSize));
		mav.addObject("ledbuildingPageNumber", ledbuildingPageNumber);
		mav.addObject("ledbuildingPage", ledbuildingPageType);
		mav.addObject("ledbuildingWantToPagePage",
				request.getParameter("ledbuildingWantToPage"));
		mav.addObject("ledbuildingPageSize", ledbuildingPageSize);
		mav.addObject("ledbuildingTotalPage", ledbuildingTotalPage);
		int whbuildingTotalPage = whbuildingService
				.countRelativeProjectWhbuildings(id) / whbuildingPageSize;
		String whbuildingPageType = request.getParameter("whbuildingPage");
		switch (whbuildingPageType) {
		case "homePage":
			whbuildingPageNumber = 0;
			break;
		case "previousPage":
			if (whbuildingPageNumber >= 1)
				whbuildingPageNumber = whbuildingPageNumber - 1;
			else
				whbuildingPageNumber = 0;
			break;
		case "nextPage":
			if (whbuildingPageNumber < whbuildingTotalPage)
				whbuildingPageNumber = whbuildingPageNumber + 1;
			else
				whbuildingPageNumber = whbuildingTotalPage;
			break;
		case "lastPage":
			whbuildingPageNumber = whbuildingTotalPage;
			break;
		case "jumpPage":
			whbuildingPageNumber = Integer.parseInt(request
					.getParameter("whbuildingWantToPage")) - 1;
			break;
		case "eachPageShow":
			whbuildingPageNumber = 0;
			whbuildingPageSize = Integer.parseInt(request
					.getParameter("whbuildingPageSize"));
			whbuildingTotalPage = whbuildingService
					.countRelativeProjectWhbuildings(id) / whbuildingPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whbuildingPageNumber >= 0
				&& whbuildingPageNumber <= whbuildingTotalPage)
			mav.addObject("whbuildings", whbuildingDAO.findProjectWhbuildings(
					id, whbuildingPageNumber * whbuildingPageSize,
					whbuildingPageSize));
		mav.addObject("whbuildingPageNumber", whbuildingPageNumber);
		mav.addObject("whbuildingPage", whbuildingPageType);
		mav.addObject("whbuildingWantToPagePage",
				request.getParameter("whbuildingWantToPage"));
		mav.addObject("whbuildingPageSize", whbuildingPageSize);
		mav.addObject("whbuildingTotalPage", whbuildingTotalPage);
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/selectProjectCompany")
	public ModelAndView selectProjectCompany(@RequestParam Integer project_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("company", company);
		mav.setViewName("project/company/viewCompany.jsp");

		return mav;
	}

	@RequestMapping("/selectProjectStaff")
	public ModelAndView selectProjectStaff(@RequestParam Integer project_id,
			@RequestParam Integer responsibility_id) {
		Staff responsibility = staffDAO.findStaffByPrimaryKey(
				responsibility_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("staff", responsibility);
		mav.setViewName("project/staff/viewStaff.jsp");

		return mav;
	}

	@RequestMapping("/selectProjectCacs")
	public ModelAndView selectProjectCacs(@RequestParam Integer project_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacTotalPage = cacService.countRelativeProjectCacs(project_id)
				/ cacPageSize;
		String cacPageType = request.getParameter("page");
		switch (cacPageType) {
		case "homePage":
			cacPageNumber = 0;
			break;
		case "previousPage":
			if (cacPageNumber >= 1)
				cacPageNumber = cacPageNumber - 1;
			else
				cacPageNumber = 0;
			break;
		case "nextPage":
			if (cacPageNumber < cacTotalPage)
				cacPageNumber = cacPageNumber + 1;
			else
				cacPageNumber = cacTotalPage;
			break;
		case "lastPage":
			cacPageNumber = cacTotalPage;
			break;
		case "jumpPage":
			cacPageNumber = Integer
					.parseInt(request.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacPageNumber = 0;
			cacPageSize = Integer.parseInt(request.getParameter("pageSize"));
			cacTotalPage = cacService.countRelativeProjectCacs(project_id)
					/ cacPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacPageNumber >= 0 && cacPageNumber <= cacTotalPage)
			mav.addObject(
					"cacs",
					cacDAO.findProjectCacs(project_id, cacPageNumber
							* cacPageSize, cacPageSize));
		mav.addObject("cacPageNumber", cacPageNumber);
		mav.addObject("cacPageSize", pageSize);
		mav.addObject("cacPage", cacPageType);
		mav.addObject("cacWantToPage", request.getParameter("WantToPage"));
		mav.addObject("cacTotalPage", cacTotalPage);
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(project_id));
		mav.setViewName("project/cacs/viewCacs.jsp");

		return mav;
	}

	@RequestMapping("/selectProjectLedbuildings")
	public ModelAndView selectProjectLedbuildings(
			@RequestParam Integer project_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ledbuildingTotalPage = ledbuildingService
				.countRelativeProjectLedbuildings(project_id)
				/ ledbuildingPageSize;
		String ledbuildingPageType = request.getParameter("page");
		switch (ledbuildingPageType) {
		case "homePage":
			ledbuildingPageNumber = 0;
			break;
		case "previousPage":
			if (ledbuildingPageNumber >= 1)
				ledbuildingPageNumber = ledbuildingPageNumber - 1;
			else
				ledbuildingPageNumber = 0;
			break;
		case "nextPage":
			if (ledbuildingPageNumber < ledbuildingTotalPage)
				ledbuildingPageNumber = ledbuildingPageNumber + 1;
			else
				ledbuildingPageNumber = ledbuildingTotalPage;
			break;
		case "lastPage":
			ledbuildingPageNumber = ledbuildingTotalPage;
			break;
		case "jumpPage":
			ledbuildingPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			ledbuildingPageNumber = 0;
			ledbuildingPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			ledbuildingTotalPage = ledbuildingService
					.countRelativeProjectLedbuildings(project_id)
					/ ledbuildingPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledbuildingPageNumber >= 0
				&& ledbuildingPageNumber <= ledbuildingTotalPage)
			mav.addObject("ledbuildings", ledbuildingDAO
					.findProjectLedbuildings(project_id, ledbuildingPageNumber
							* ledbuildingPageSize, ledbuildingPageSize));
		mav.addObject("ledbuildingPageNumber", ledbuildingPageNumber);
		mav.addObject("ledbuildingPageSize", pageSize);
		mav.addObject("ledbuildingPage", ledbuildingPageType);
		mav.addObject("ledbuildingWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("ledbuildingTotalPage", ledbuildingTotalPage);
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(project_id));
		mav.setViewName("project/ledbuildings/viewLedbuildings.jsp");

		return mav;
	}

	@RequestMapping("/selectProjectWhbuildings")
	public ModelAndView selectProjectWhbuildings(
			@RequestParam Integer project_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whbuildingTotalPage = whbuildingService
				.countRelativeProjectWhbuildings(project_id)
				/ whbuildingPageSize;
		String whbuildingPageType = request.getParameter("page");
		switch (whbuildingPageType) {
		case "homePage":
			whbuildingPageNumber = 0;
			break;
		case "previousPage":
			if (whbuildingPageNumber >= 1)
				whbuildingPageNumber = whbuildingPageNumber - 1;
			else
				whbuildingPageNumber = 0;
			break;
		case "nextPage":
			if (whbuildingPageNumber < whbuildingTotalPage)
				whbuildingPageNumber = whbuildingPageNumber + 1;
			else
				whbuildingPageNumber = whbuildingTotalPage;
			break;
		case "lastPage":
			whbuildingPageNumber = whbuildingTotalPage;
			break;
		case "jumpPage":
			whbuildingPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whbuildingPageNumber = 0;
			whbuildingPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			whbuildingTotalPage = whbuildingService
					.countRelativeProjectWhbuildings(project_id)
					/ whbuildingPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whbuildingPageNumber >= 0
				&& whbuildingPageNumber <= whbuildingTotalPage)
			mav.addObject("whbuildings", whbuildingDAO.findProjectWhbuildings(
					project_id, whbuildingPageNumber * whbuildingPageSize,
					whbuildingPageSize));
		mav.addObject("whbuildingPageNumber", whbuildingPageNumber);
		mav.addObject("whbuildingPageSize", pageSize);
		mav.addObject("whbuildingPage", whbuildingPageType);
		mav.addObject("whbuildingWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("whbuildingTotalPage", whbuildingTotalPage);
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(project_id));
		mav.setViewName("project/whbuildings/viewWhbuildings.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteProject")
	public ModelAndView confirmDeleteProject(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("project/deleteProject.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteProjectCompany")
	public ModelAndView confirmDeleteProjectCompany(
			@RequestParam Integer project_id,
			@RequestParam Integer related_company_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company",
				companyDAO.findCompanyByPrimaryKey(related_company_id));
		mav.addObject("project_id", project_id);

		mav.setViewName("project/company/deleteCompany.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteProjectStaff")
	public ModelAndView confirmDeleteProjectStaff(
			@RequestParam Integer project_id,
			@RequestParam Integer related_staff_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(related_staff_id));
		mav.addObject("project_id", project_id);

		mav.setViewName("project/staff/deleteStaff.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteProjectCacs")
	public ModelAndView confirmDeleteProjectCacs(
			@RequestParam Integer project_id,
			@RequestParam Integer related_cacs_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(related_cacs_id));
		mav.addObject("project_id", project_id);

		mav.setViewName("project/cacs/deleteCacs.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteProjectLedbuildings")
	public ModelAndView confirmDeleteProjectLedbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer related_ledbuildings_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding", ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuildings_id));
		mav.addObject("project_id", project_id);

		mav.setViewName("project/ledbuildings/deleteLedbuildings.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteProjectWhbuildings")
	public ModelAndView confirmDeleteProjectWhbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer related_whbuildings_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding", whbuildingDAO
				.findWhbuildingByPrimaryKey(related_whbuildings_id));
		mav.addObject("project_id", project_id);

		mav.setViewName("project/whbuildings/deleteWhbuildings.jsp");
		return mav;
	}

	@RequestMapping("/deleteProject")
	public String deleteProject(@RequestParam Integer id) {
		Project project = projectDAO.findProjectByPrimaryKey(id);
		projectService.deleteProject(project);
		return "forward:/indexProject";
	}

	@RequestMapping("/deleteProjectCompany")
	public ModelAndView deleteProjectCompany(@RequestParam Integer project_id,
			@RequestParam Integer Relativecompany_id) {
		ModelAndView mav = new ModelAndView();
		Project project = projectService.deleteProjectCompany(project_id,
				Relativecompany_id);
		mav.addObject("project_id", project_id);

		mav.addObject("project", project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/deleteProjectStaff")
	public ModelAndView deleteProjectStaff(@RequestParam Integer project_id,
			@RequestParam Integer Relativestaff_id) {
		ModelAndView mav = new ModelAndView();
		Project project = projectService.deleteProjectStaff(project_id,
				Relativestaff_id);
		mav.addObject("project_id", project_id);

		mav.addObject("project", project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/deleteProjectCacs")
	public ModelAndView deleteProjectCacs(@RequestParam Integer project_id,
			@RequestParam Integer Relativecacs_id) {
		ModelAndView mav = new ModelAndView();
		Project project = projectService.deleteProjectCacs(project_id,
				Relativecacs_id);
		mav.addObject("project_id", project_id);

		mav.addObject("project", project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/deleteProjectLedbuildings")
	public ModelAndView deleteProjectLedbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer Relativeledbuildings_id) {
		ModelAndView mav = new ModelAndView();
		Project project = projectService.deleteProjectLedbuildings(project_id,
				Relativeledbuildings_id);
		mav.addObject("project_id", project_id);

		mav.addObject("project", project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/deleteProjectWhbuildings")
	public ModelAndView deleteProjectWhbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer Relativewhbuildings_id) {
		ModelAndView mav = new ModelAndView();
		Project project = projectService.deleteProjectWhbuildings(project_id,
				Relativewhbuildings_id);
		mav.addObject("project_id", project_id);

		mav.addObject("project", project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/editProject")
	public ModelAndView editProject(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Company> allCompanys = companyDAO.findAllCompanys();
		mav.addObject("allCompanys", allCompanys);
		Set<Staff> allResponsibilitys = staffDAO.findAllStaffs();
		mav.addObject("allResponsibilitys", allResponsibilitys);
		Set<Cac> allCacs = cacDAO.findAllCacs();
		Set<Cac> havedCacs = projectDAO.findProjectByPrimaryKey(id)
				.getRelativeCacs();
		mav.addObject("allCacs", allCacs);
		mav.addObject("havedCacs", havedCacs);
		Set<Ledbuilding> allLedbuildings = ledbuildingDAO.findAllLedbuildings();
		Set<Ledbuilding> havedLedbuildings = projectDAO
				.findProjectByPrimaryKey(id).getRelativeLedbuildings();
		mav.addObject("allLedbuildings", allLedbuildings);
		mav.addObject("havedLedbuildings", havedLedbuildings);
		Set<Whbuilding> allWhbuildings = whbuildingDAO.findAllWhbuildings();
		Set<Whbuilding> havedWhbuildings = projectDAO.findProjectByPrimaryKey(
				id).getRelativeWhbuildings();
		mav.addObject("allWhbuildings", allWhbuildings);
		mav.addObject("havedWhbuildings", havedWhbuildings);
		mav.addObject("allProjects", projectDAO.findAllProjects());
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/editProjectCompany")
	public ModelAndView editProjectCompany(@RequestParam Integer project_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("company", company);
		mav.setViewName("project/company/editCompany.jsp");

		return mav;
	}

	@RequestMapping("/editProjectStaff")
	public ModelAndView editProjectStaff(@RequestParam Integer project_id,
			@RequestParam Integer responsibility_id) {
		Staff responsibility = staffDAO.findStaffByPrimaryKey(
				responsibility_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("staff", responsibility);
		mav.setViewName("project/staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/editProjectCacs")
	public ModelAndView editProjectCacs(@RequestParam Integer project_id,
			@RequestParam Integer cacs_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cacs_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("cac", cac);
		mav.setViewName("project/cacs/editCacs.jsp");

		return mav;
	}

	@RequestMapping("/editProjectLedbuildings")
	public ModelAndView editProjectLedbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer ledbuildings_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuildings_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.setViewName("project/ledbuildings/editLedbuildings.jsp");

		return mav;
	}

	@RequestMapping("/editProjectWhbuildings")
	public ModelAndView editProjectWhbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer whbuildings_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuildings_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("whbuilding", whbuilding);
		mav.setViewName("project/whbuildings/editWhbuildings.jsp");

		return mav;
	}

	@RequestMapping("/newProject")
	public ModelAndView newProject() {
		ModelAndView mav = new ModelAndView();

		Set<Company> allCompanys = companyDAO.findAllCompanys();
		mav.addObject("allCompanys", allCompanys);
		Set<Staff> allResponsibilitys = staffDAO.findAllStaffs();
		mav.addObject("allResponsibilitys", allResponsibilitys);

		Set<Cac> allCacs = cacDAO.findAllCacs();
		mav.addObject("allCacs", allCacs);
		Set<Ledbuilding> allLedbuildings = ledbuildingDAO.findAllLedbuildings();
		mav.addObject("allLedbuildings", allLedbuildings);
		Set<Whbuilding> allWhbuildings = whbuildingDAO.findAllWhbuildings();
		mav.addObject("allWhbuildings", allWhbuildings);
		mav.addObject("allProjects", projectDAO.findAllProjects());
		mav.addObject("project", new Project());
		mav.addObject("newFlag", true);
		
		/**
		 * @author _TESLA_
		 */
		Set<Staff> staffs = staffDAO.findAllStaffs();
		mav.addObject("allStaffs", staffs);
		
		mav.setViewName("project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/newProjectCompany")
	public ModelAndView newProjectCompany(@RequestParam Integer project_id,
			@RequestParam Integer company_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("company", company);
		mav.addObject("newFlag", true);
		mav.setViewName("project/company/editCompany.jsp");

		return mav;
	}

	@RequestMapping("/newProjectStaff")
	public ModelAndView newProjectStaff(@RequestParam Integer project_id,
			@RequestParam Integer responsibility_id) {
		Staff responsibility = staffDAO.findStaffByPrimaryKey(
				responsibility_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("staff", responsibility);
		mav.addObject("newFlag", true);
		mav.setViewName("project/staff/editStaff.jsp");

		return mav;
	}

	@RequestMapping("/newProjectCacs")
	public ModelAndView newProjectCacs(@RequestParam Integer project_id,
			@RequestParam Integer cacs_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cacs_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("cac", cac);
		mav.addObject("newFlag", true);
		mav.setViewName("project/cacs/editCacs.jsp");

		return mav;
	}

	@RequestMapping("/newProjectLedbuildings")
	public ModelAndView newProjectLedbuildings(
			@RequestParam Integer project_id,
			@RequestParam Integer ledbuildings_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuildings_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.addObject("newFlag", true);
		mav.setViewName("project/ledbuildings/editLedbuildings.jsp");

		return mav;
	}

	@RequestMapping("/newProjectWhbuildings")
	public ModelAndView newProjectWhbuildings(@RequestParam Integer project_id,
			@RequestParam Integer whbuildings_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuildings_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("whbuilding", whbuilding);
		mav.addObject("newFlag", true);
		mav.setViewName("project/whbuildings/editWhbuildings.jsp");

		return mav;
	}

	@RequestMapping("/listProjectCompany")
	public ModelAndView listProjectCompany(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("company", companyDAO.findCompanyByPrimaryKey(id));
		mav.setViewName("project/company/listCompany.jsp");

		return mav;
	}

	Company company;

	@RequestMapping("/listProjectStaff")
	public ModelAndView listProjectStaff(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("staff", staffDAO.findStaffByPrimaryKey(id));
		mav.setViewName("project/staff/listStaff.jsp");

		return mav;
	}

	Staff staff;

	@RequestMapping("/listProjectCacs")
	public ModelAndView listProjectCacs(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("project/cacs/listCacs.jsp");

		return mav;
	}

	@RequestMapping("/listProjectLedbuildings")
	public ModelAndView listProjectLedbuildings(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(id));
		mav.setViewName("project/ledbuildings/listLedbuildings.jsp");

		return mav;
	}

	@RequestMapping("/listProjectWhbuildings")
	public ModelAndView listProjectWhbuildings(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(id));
		mav.setViewName("project/whbuildings/listWhbuildings.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveProject")
	public String saveProject(@ModelAttribute Project project) {
		projectService.saveProject(project);

		return "forward:/indexProject";
	}

	@RequestMapping("/saveProjectCompany")
	public ModelAndView saveProjectCompany(@RequestParam Integer project_id,
			@ModelAttribute Company company) {
		Project parent_project = projectService.saveProjectCompany(project_id,
				company);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("project", parent_project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/saveProjectStaff")
	public ModelAndView saveProjectStaff(@RequestParam Integer project_id,
			@ModelAttribute Staff staff) {
		Project parent_project = projectService.saveProjectStaff(project_id,
				staff);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("project", parent_project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/saveProjectCacs")
	public ModelAndView saveProjectCacs(@RequestParam Integer project_id,
			@ModelAttribute Cac cacs) {
		Project parent_project = projectService.saveProjectCacs(project_id,
				cacs);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("project", parent_project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/saveProjectLedbuildings")
	public ModelAndView saveProjectLedbuildings(
			@RequestParam Integer project_id,
			@ModelAttribute Ledbuilding ledbuildings) {
		Project parent_project = projectService.saveProjectLedbuildings(
				project_id, ledbuildings);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("project", parent_project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/saveProjectWhbuildings")
	public ModelAndView saveProjectWhbuildings(
			@RequestParam Integer project_id,
			@ModelAttribute Whbuilding whbuildings) {
		Project parent_project = projectService.saveProjectWhbuildings(
				project_id, whbuildings);

		ModelAndView mav = new ModelAndView();
		mav.addObject("project_id", project_id);

		mav.addObject("project", parent_project);
		mav.setViewName("project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/projectController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}