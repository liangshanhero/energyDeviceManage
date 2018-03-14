package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.LedbuildingDAO;

import cn.edu.scau.cmi.service.LedbuildingService;

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

@Controller("LedbuildingController")
public class LedbuildingController extends CmiController {
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

	@RequestMapping("/indexLedbuilding")
	public ModelAndView listLedbuildings(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = ledbuildingService.countledbuildings() / pageSize;
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
			totalPage = ledbuildingService.countledbuildings() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("ledbuildings", ledbuildingService.loadLedbuildings(
					pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("ledbuilding/listLedbuildings.jsp");
		return mav;
	}

	public String indexLedbuilding() {
		return "redirect:/indexLedbuilding";
	}

	@RequestMapping("/selectLedbuilding")
	public ModelAndView selectLedbuilding(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ledmeterTotalPage = ledmeterService
				.countRelativeLedbuildingLedmeters(id) / ledmeterPageSize;
		String ledmeterPageType = request.getParameter("ledmeterPage");
		switch (ledmeterPageType) {
		case "homePage":
			ledmeterPageNumber = 0;
			break;
		case "previousPage":
			if (ledmeterPageNumber >= 1)
				ledmeterPageNumber = ledmeterPageNumber - 1;
			else
				ledmeterPageNumber = 0;
			break;
		case "nextPage":
			if (ledmeterPageNumber < ledmeterTotalPage)
				ledmeterPageNumber = ledmeterPageNumber + 1;
			else
				ledmeterPageNumber = ledmeterTotalPage;
			break;
		case "lastPage":
			ledmeterPageNumber = ledmeterTotalPage;
			break;
		case "jumpPage":
			ledmeterPageNumber = Integer.parseInt(request
					.getParameter("ledmeterWantToPage")) - 1;
			break;
		case "eachPageShow":
			ledmeterPageNumber = 0;
			ledmeterPageSize = Integer.parseInt(request
					.getParameter("ledmeterPageSize"));
			ledmeterTotalPage = ledmeterService
					.countRelativeLedbuildingLedmeters(id) / ledmeterPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledmeterPageNumber >= 0 && ledmeterPageNumber <= ledmeterTotalPage)
			mav.addObject(
					"ledmeters",
					ledmeterDAO.findLedbuildingLedmeters(id, ledmeterPageNumber
							* ledmeterPageSize, ledmeterPageSize));
		mav.addObject("ledmeterPageNumber", ledmeterPageNumber);
		mav.addObject("ledmeterPage", ledmeterPageType);
		mav.addObject("ledmeterWantToPagePage",
				request.getParameter("ledmeterWantToPage"));
		mav.addObject("ledmeterPageSize", ledmeterPageSize);
		mav.addObject("ledmeterTotalPage", ledmeterTotalPage);
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(id));
		mav.setViewName("ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/selectLedbuildingProject")
	public ModelAndView selectLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("project", project);
		mav.setViewName("ledbuilding/project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/selectLedbuildingLedmeters")
	public ModelAndView selectLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ledmeterTotalPage = ledmeterService
				.countRelativeLedbuildingLedmeters(ledbuilding_id)
				/ ledmeterPageSize;
		String ledmeterPageType = request.getParameter("page");
		switch (ledmeterPageType) {
		case "homePage":
			ledmeterPageNumber = 0;
			break;
		case "previousPage":
			if (ledmeterPageNumber >= 1)
				ledmeterPageNumber = ledmeterPageNumber - 1;
			else
				ledmeterPageNumber = 0;
			break;
		case "nextPage":
			if (ledmeterPageNumber < ledmeterTotalPage)
				ledmeterPageNumber = ledmeterPageNumber + 1;
			else
				ledmeterPageNumber = ledmeterTotalPage;
			break;
		case "lastPage":
			ledmeterPageNumber = ledmeterTotalPage;
			break;
		case "jumpPage":
			ledmeterPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			ledmeterPageNumber = 0;
			ledmeterPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			ledmeterTotalPage = ledmeterService
					.countRelativeLedbuildingLedmeters(ledbuilding_id)
					/ ledmeterPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledmeterPageNumber >= 0 && ledmeterPageNumber <= ledmeterTotalPage)
			mav.addObject("ledmeters", ledmeterDAO.findLedbuildingLedmeters(
					ledbuilding_id, ledmeterPageNumber * ledmeterPageSize,
					ledmeterPageSize));
		mav.addObject("ledmeterPageNumber", ledmeterPageNumber);
		mav.addObject("ledmeterPageSize", pageSize);
		mav.addObject("ledmeterPage", ledmeterPageType);
		mav.addObject("ledmeterWantToPage", request.getParameter("WantToPage"));
		mav.addObject("ledmeterTotalPage", ledmeterTotalPage);
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(ledbuilding_id));
		mav.setViewName("ledbuilding/ledmeters/viewLedmeters.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteLedbuilding")
	public ModelAndView confirmDeleteLedbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(id));
		mav.setViewName("ledbuilding/deleteLedbuilding.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteLedbuildingProject")
	public ModelAndView confirmDeleteLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer related_project_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project",
				projectDAO.findProjectByPrimaryKey(related_project_id));
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.setViewName("ledbuilding/project/deleteProject.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteLedbuildingLedmeters")
	public ModelAndView confirmDeleteLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer related_ledmeters_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter",
				ledmeterDAO.findLedmeterByPrimaryKey(related_ledmeters_id));
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.setViewName("ledbuilding/ledmeters/deleteLedmeters.jsp");
		return mav;
	}

	@RequestMapping("/deleteLedbuilding")
	public String deleteLedbuilding(@RequestParam Integer id) {
		Ledbuilding ledbuilding = ledbuildingDAO
				.findLedbuildingByPrimaryKey(id);
		ledbuildingService.deleteLedbuilding(ledbuilding);
		return "forward:/indexLedbuilding";
	}

	@RequestMapping("/deleteLedbuildingProject")
	public ModelAndView deleteLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer Relativeproject_id) {
		ModelAndView mav = new ModelAndView();
		Ledbuilding ledbuilding = ledbuildingService.deleteLedbuildingProject(
				ledbuilding_id, Relativeproject_id);
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.setViewName("ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/deleteLedbuildingLedmeters")
	public ModelAndView deleteLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer Relativeledmeters_id) {
		ModelAndView mav = new ModelAndView();
		Ledbuilding ledbuilding = ledbuildingService
				.deleteLedbuildingLedmeters(ledbuilding_id,
						Relativeledmeters_id);
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.setViewName("ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editLedbuilding")
	public ModelAndView editLedbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);
		Set<Ledmeter> allLedmeters = ledmeterDAO.findAllLedmeters();
		Set<Ledmeter> havedLedmeters = ledbuildingDAO
				.findLedbuildingByPrimaryKey(id).getRelativeLedmeters();
		mav.addObject("allLedmeters", allLedmeters);
		mav.addObject("havedLedmeters", havedLedmeters);
		mav.addObject("allLedbuildings", ledbuildingDAO.findAllLedbuildings());
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(id));
		mav.setViewName("ledbuilding/editLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editLedbuildingProject")
	public ModelAndView editLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("project", project);
		mav.setViewName("ledbuilding/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/editLedbuildingLedmeters")
	public ModelAndView editLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer ledmeters_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeters_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledmeter", ledmeter);
		mav.setViewName("ledbuilding/ledmeters/editLedmeters.jsp");

		return mav;
	}

	@RequestMapping("/newLedbuilding")
	public ModelAndView newLedbuilding() {
		ModelAndView mav = new ModelAndView();

		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);

		Set<Ledmeter> allLedmeters = ledmeterDAO.findAllLedmeters();
		mav.addObject("allLedmeters", allLedmeters);
		mav.addObject("allLedbuildings", ledbuildingDAO.findAllLedbuildings());
		mav.addObject("ledbuilding", new Ledbuilding());
		mav.addObject("newFlag", true);
		mav.setViewName("ledbuilding/editLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/newLedbuildingProject")
	public ModelAndView newLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("project", project);
		mav.addObject("newFlag", true);
		mav.setViewName("ledbuilding/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/newLedbuildingLedmeters")
	public ModelAndView newLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id,
			@RequestParam Integer ledmeters_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeters_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledmeter", ledmeter);
		mav.addObject("newFlag", true);
		mav.setViewName("ledbuilding/ledmeters/editLedmeters.jsp");

		return mav;
	}

	@RequestMapping("/listLedbuildingProject")
	public ModelAndView listLedbuildingProject(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("ledbuilding/project/listProject.jsp");

		return mav;
	}

	Project project;

	@RequestMapping("/listLedbuildingLedmeters")
	public ModelAndView listLedbuildingLedmeters(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter", ledmeterDAO.findLedmeterByPrimaryKey(id));
		mav.setViewName("ledbuilding/ledmeters/listLedmeters.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveLedbuilding")
	public String saveLedbuilding(@ModelAttribute Ledbuilding ledbuilding) {
		ledbuildingService.saveLedbuilding(ledbuilding);

		return "forward:/indexLedbuilding";
	}

	@RequestMapping("/saveLedbuildingProject")
	public ModelAndView saveLedbuildingProject(
			@RequestParam Integer ledbuilding_id,
			@ModelAttribute Project project) {
		Ledbuilding parent_ledbuilding = ledbuildingService
				.saveLedbuildingProject(ledbuilding_id, project);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledbuilding", parent_ledbuilding);
		mav.setViewName("ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/saveLedbuildingLedmeters")
	public ModelAndView saveLedbuildingLedmeters(
			@RequestParam Integer ledbuilding_id,
			@ModelAttribute Ledmeter ledmeters) {
		Ledbuilding parent_ledbuilding = ledbuildingService
				.saveLedbuildingLedmeters(ledbuilding_id, ledmeters);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding_id", ledbuilding_id);

		mav.addObject("ledbuilding", parent_ledbuilding);
		mav.setViewName("ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/ledbuildingController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}