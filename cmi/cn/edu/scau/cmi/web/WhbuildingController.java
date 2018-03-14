package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhbuildingDAO;

import cn.edu.scau.cmi.service.WhbuildingService;

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

@Controller("WhbuildingController")
public class WhbuildingController extends CmiController {
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

	@RequestMapping("/indexWhbuilding")
	public ModelAndView listWhbuildings(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whbuildingService.countwhbuildings() / pageSize;
		String pageType = request.getParameter("page");

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
			totalPage = whbuildingService.countwhbuildings() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whbuildings", whbuildingService.loadWhbuildings(
					pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whbuilding/listWhbuildings.jsp");
		return mav;
	}

	public String indexWhbuilding() {
		return "redirect:/indexWhbuilding";
	}

	@RequestMapping("/selectWhbuilding")
	public ModelAndView selectWhbuilding(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whdeviceTotalPage = whdeviceService
				.countRelativeWhbuildingWhdevices(id) / whdevicePageSize;
		String whdevicePageType = request.getParameter("whdevicePage");
		switch (whdevicePageType) {
		case "homePage":
			whdevicePageNumber = 0;
			break;
		case "previousPage":
			if (whdevicePageNumber >= 1)
				whdevicePageNumber = whdevicePageNumber - 1;
			else
				whdevicePageNumber = 0;
			break;
		case "nextPage":
			if (whdevicePageNumber < whdeviceTotalPage)
				whdevicePageNumber = whdevicePageNumber + 1;
			else
				whdevicePageNumber = whdeviceTotalPage;
			break;
		case "lastPage":
			whdevicePageNumber = whdeviceTotalPage;
			break;
		case "jumpPage":
			whdevicePageNumber = Integer.parseInt(request
					.getParameter("whdeviceWantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicePageNumber = 0;
			whdevicePageSize = Integer.parseInt(request
					.getParameter("whdevicePageSize"));
			whdeviceTotalPage = whdeviceService
					.countRelativeWhbuildingWhdevices(id) / whdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicePageNumber >= 0 && whdevicePageNumber <= whdeviceTotalPage)
			mav.addObject(
					"whdevices",
					whdeviceDAO.findWhbuildingWhdevices(id, whdevicePageNumber
							* whdevicePageSize, whdevicePageSize));
		mav.addObject("whdevicePageNumber", whdevicePageNumber);
		mav.addObject("whdevicePage", whdevicePageType);
		mav.addObject("whdeviceWantToPagePage",
				request.getParameter("whdeviceWantToPage"));
		mav.addObject("whdevicePageSize", whdevicePageSize);
		mav.addObject("whdeviceTotalPage", whdeviceTotalPage);
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(id));
		mav.setViewName("whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/selectWhbuildingProject")
	public ModelAndView selectWhbuildingProject(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("project", project);
		mav.setViewName("whbuilding/project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/selectWhbuildingWhdevices")
	public ModelAndView selectWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whdeviceTotalPage = whdeviceService
				.countRelativeWhbuildingWhdevices(whbuilding_id)
				/ whdevicePageSize;
		String whdevicePageType = request.getParameter("page");
		switch (whdevicePageType) {
		case "homePage":
			whdevicePageNumber = 0;
			break;
		case "previousPage":
			if (whdevicePageNumber >= 1)
				whdevicePageNumber = whdevicePageNumber - 1;
			else
				whdevicePageNumber = 0;
			break;
		case "nextPage":
			if (whdevicePageNumber < whdeviceTotalPage)
				whdevicePageNumber = whdevicePageNumber + 1;
			else
				whdevicePageNumber = whdeviceTotalPage;
			break;
		case "lastPage":
			whdevicePageNumber = whdeviceTotalPage;
			break;
		case "jumpPage":
			whdevicePageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicePageNumber = 0;
			whdevicePageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			whdeviceTotalPage = whdeviceService
					.countRelativeWhbuildingWhdevices(whbuilding_id)
					/ whdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicePageNumber >= 0 && whdevicePageNumber <= whdeviceTotalPage)
			mav.addObject("whdevices", whdeviceDAO.findWhbuildingWhdevices(
					whbuilding_id, whdevicePageNumber * whdevicePageSize,
					whdevicePageSize));
		mav.addObject("whdevicePageNumber", whdevicePageNumber);
		mav.addObject("whdevicePageSize", pageSize);
		mav.addObject("whdevicePage", whdevicePageType);
		mav.addObject("whdeviceWantToPage", request.getParameter("WantToPage"));
		mav.addObject("whdeviceTotalPage", whdeviceTotalPage);
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(whbuilding_id));
		mav.setViewName("whbuilding/whdevices/viewWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhbuilding")
	public ModelAndView confirmDeleteWhbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(id));
		mav.setViewName("whbuilding/deleteWhbuilding.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhbuildingProject")
	public ModelAndView confirmDeleteWhbuildingProject(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer related_project_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project",
				projectDAO.findProjectByPrimaryKey(related_project_id));
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.setViewName("whbuilding/project/deleteProject.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhbuildingWhdevices")
	public ModelAndView confirmDeleteWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer related_whdevices_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice",
				whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevices_id));
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.setViewName("whbuilding/whdevices/deleteWhdevices.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhbuilding")
	public String deleteWhbuilding(@RequestParam Integer id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(id);
		whbuildingService.deleteWhbuilding(whbuilding);
		return "forward:/indexWhbuilding";
	}

	@RequestMapping("/deleteWhbuildingProject")
	public ModelAndView deleteWhbuildingProject(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer Relativeproject_id) {
		ModelAndView mav = new ModelAndView();
		Whbuilding whbuilding = whbuildingService.deleteWhbuildingProject(
				whbuilding_id, Relativeproject_id);
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whbuilding", whbuilding);
		mav.setViewName("whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhbuildingWhdevices")
	public ModelAndView deleteWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer Relativewhdevices_id) {
		ModelAndView mav = new ModelAndView();
		Whbuilding whbuilding = whbuildingService.deleteWhbuildingWhdevices(
				whbuilding_id, Relativewhdevices_id);
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whbuilding", whbuilding);
		mav.setViewName("whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editWhbuilding")
	public ModelAndView editWhbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);
		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		Set<Whdevice> havedWhdevices = whbuildingDAO
				.findWhbuildingByPrimaryKey(id).getRelativeWhdevices();
		mav.addObject("allWhdevices", allWhdevices);
		mav.addObject("havedWhdevices", havedWhdevices);
		mav.addObject("allWhbuildings", whbuildingDAO.findAllWhbuildings());
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(id));
		mav.setViewName("whbuilding/editWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editWhbuildingProject")
	public ModelAndView editWhbuildingProject(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("project", project);
		mav.setViewName("whbuilding/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/editWhbuildingWhdevices")
	public ModelAndView editWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer whdevices_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevices_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whbuilding/whdevices/editWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/newWhbuilding")
	public ModelAndView newWhbuilding() {
		ModelAndView mav = new ModelAndView();

		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);

		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);
		mav.addObject("allWhbuildings", whbuildingDAO.findAllWhbuildings());
		mav.addObject("whbuilding", new Whbuilding());
		mav.addObject("newFlag", true);
		mav.setViewName("whbuilding/editWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/newWhbuildingProject")
	public ModelAndView newWhbuildingProject(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("project", project);
		mav.addObject("newFlag", true);
		mav.setViewName("whbuilding/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/newWhbuildingWhdevices")
	public ModelAndView newWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id,
			@RequestParam Integer whdevices_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevices_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whdevice", whdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("whbuilding/whdevices/editWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/listWhbuildingProject")
	public ModelAndView listWhbuildingProject(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("whbuilding/project/listProject.jsp");

		return mav;
	}

	Project project;

	@RequestMapping("/listWhbuildingWhdevices")
	public ModelAndView listWhbuildingWhdevices(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whbuilding/whdevices/listWhdevices.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveWhbuilding")
	public String saveWhbuilding(@ModelAttribute Whbuilding whbuilding) {
		whbuildingService.saveWhbuilding(whbuilding);

		return "forward:/indexWhbuilding";
	}

	@RequestMapping("/saveWhbuildingProject")
	public ModelAndView saveWhbuildingProject(
			@RequestParam Integer whbuilding_id, @ModelAttribute Project project) {
		Whbuilding parent_whbuilding = whbuildingService.saveWhbuildingProject(
				whbuilding_id, project);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whbuilding", parent_whbuilding);
		mav.setViewName("whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/saveWhbuildingWhdevices")
	public ModelAndView saveWhbuildingWhdevices(
			@RequestParam Integer whbuilding_id,
			@ModelAttribute Whdevice whdevices) {
		Whbuilding parent_whbuilding = whbuildingService
				.saveWhbuildingWhdevices(whbuilding_id, whdevices);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding_id", whbuilding_id);

		mav.addObject("whbuilding", parent_whbuilding);
		mav.setViewName("whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/whbuildingController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}