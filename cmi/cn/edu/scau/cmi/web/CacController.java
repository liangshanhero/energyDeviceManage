package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacDAO;

import cn.edu.scau.cmi.service.CacService;

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

@Controller("CacController")
public class CacController extends CmiController {
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

	@RequestMapping("/indexCac")
	public ModelAndView listCacs(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacService.countcacs() / pageSize;
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
			totalPage = cacService.countcacs() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("cacs",
					cacService.loadCacs(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cac/listCacs.jsp");
		return mav;
	}

	public String indexCac() {
		return "redirect:/indexCac";
	}

	@RequestMapping("/selectCac")
	public ModelAndView selectCac(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdeviceTotalPage = cacdeviceService
				.countRelativeCacCacdevices(id) / cacdevicePageSize;
		String cacdevicePageType = request.getParameter("cacdevicePage");
		switch (cacdevicePageType) {
		case "homePage":
			cacdevicePageNumber = 0;
			break;
		case "previousPage":
			if (cacdevicePageNumber >= 1)
				cacdevicePageNumber = cacdevicePageNumber - 1;
			else
				cacdevicePageNumber = 0;
			break;
		case "nextPage":
			if (cacdevicePageNumber < cacdeviceTotalPage)
				cacdevicePageNumber = cacdevicePageNumber + 1;
			else
				cacdevicePageNumber = cacdeviceTotalPage;
			break;
		case "lastPage":
			cacdevicePageNumber = cacdeviceTotalPage;
			break;
		case "jumpPage":
			cacdevicePageNumber = Integer.parseInt(request
					.getParameter("cacdeviceWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacdevicePageNumber = 0;
			cacdevicePageSize = Integer.parseInt(request
					.getParameter("cacdevicePageSize"));
			cacdeviceTotalPage = cacdeviceService
					.countRelativeCacCacdevices(id) / cacdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicePageNumber >= 0
				&& cacdevicePageNumber <= cacdeviceTotalPage)
			mav.addObject(
					"cacdevices",
					cacdeviceDAO.findCacCacdevices(id, cacdevicePageNumber
							* cacdevicePageSize, cacdevicePageSize));
		mav.addObject("cacdevicePageNumber", cacdevicePageNumber);
		mav.addObject("cacdevicePage", cacdevicePageType);
		mav.addObject("cacdeviceWantToPagePage",
				request.getParameter("cacdeviceWantToPage"));
		mav.addObject("cacdevicePageSize", cacdevicePageSize);
		mav.addObject("cacdeviceTotalPage", cacdeviceTotalPage);
		int cacsensorTotalPage = cacsensorService
				.countRelativeCacCacsensors(id) / cacsensorPageSize;
		String cacsensorPageType = request.getParameter("cacsensorPage");
		switch (cacsensorPageType) {
		case "homePage":
			cacsensorPageNumber = 0;
			break;
		case "previousPage":
			if (cacsensorPageNumber >= 1)
				cacsensorPageNumber = cacsensorPageNumber - 1;
			else
				cacsensorPageNumber = 0;
			break;
		case "nextPage":
			if (cacsensorPageNumber < cacsensorTotalPage)
				cacsensorPageNumber = cacsensorPageNumber + 1;
			else
				cacsensorPageNumber = cacsensorTotalPage;
			break;
		case "lastPage":
			cacsensorPageNumber = cacsensorTotalPage;
			break;
		case "jumpPage":
			cacsensorPageNumber = Integer.parseInt(request
					.getParameter("cacsensorWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacsensorPageNumber = 0;
			cacsensorPageSize = Integer.parseInt(request
					.getParameter("cacsensorPageSize"));
			cacsensorTotalPage = cacsensorService
					.countRelativeCacCacsensors(id) / cacsensorPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensorPageNumber >= 0
				&& cacsensorPageNumber <= cacsensorTotalPage)
			mav.addObject(
					"cacsensors",
					cacsensorDAO.findCacCacsensors(id, cacsensorPageNumber
							* cacsensorPageSize, cacsensorPageSize));
		mav.addObject("cacsensorPageNumber", cacsensorPageNumber);
		mav.addObject("cacsensorPage", cacsensorPageType);
		mav.addObject("cacsensorWantToPagePage",
				request.getParameter("cacsensorWantToPage"));
		mav.addObject("cacsensorPageSize", cacsensorPageSize);
		mav.addObject("cacsensorTotalPage", cacsensorTotalPage);
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/selectCacProject")
	public ModelAndView selectCacProject(@RequestParam Integer cac_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("project", project);
		mav.setViewName("cac/project/viewProject.jsp");

		return mav;
	}

	@RequestMapping("/selectCacCacdevices")
	public ModelAndView selectCacCacdevices(@RequestParam Integer cac_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdeviceTotalPage = cacdeviceService
				.countRelativeCacCacdevices(cac_id) / cacdevicePageSize;
		String cacdevicePageType = request.getParameter("page");
		switch (cacdevicePageType) {
		case "homePage":
			cacdevicePageNumber = 0;
			break;
		case "previousPage":
			if (cacdevicePageNumber >= 1)
				cacdevicePageNumber = cacdevicePageNumber - 1;
			else
				cacdevicePageNumber = 0;
			break;
		case "nextPage":
			if (cacdevicePageNumber < cacdeviceTotalPage)
				cacdevicePageNumber = cacdevicePageNumber + 1;
			else
				cacdevicePageNumber = cacdeviceTotalPage;
			break;
		case "lastPage":
			cacdevicePageNumber = cacdeviceTotalPage;
			break;
		case "jumpPage":
			cacdevicePageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacdevicePageNumber = 0;
			cacdevicePageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			cacdeviceTotalPage = cacdeviceService
					.countRelativeCacCacdevices(cac_id) / cacdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicePageNumber >= 0
				&& cacdevicePageNumber <= cacdeviceTotalPage)
			mav.addObject(
					"cacdevices",
					cacdeviceDAO.findCacCacdevices(cac_id, cacdevicePageNumber
							* cacdevicePageSize, cacdevicePageSize));
		mav.addObject("cacdevicePageNumber", cacdevicePageNumber);
		mav.addObject("cacdevicePageSize", pageSize);
		mav.addObject("cacdevicePage", cacdevicePageType);
		mav.addObject("cacdeviceWantToPage", request.getParameter("WantToPage"));
		mav.addObject("cacdeviceTotalPage", cacdeviceTotalPage);
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(cac_id));
		mav.setViewName("cac/cacdevices/viewCacdevices.jsp");

		return mav;
	}

	@RequestMapping("/selectCacCacsensors")
	public ModelAndView selectCacCacsensors(@RequestParam Integer cac_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacsensorTotalPage = cacsensorService
				.countRelativeCacCacsensors(cac_id) / cacsensorPageSize;
		String cacsensorPageType = request.getParameter("page");
		switch (cacsensorPageType) {
		case "homePage":
			cacsensorPageNumber = 0;
			break;
		case "previousPage":
			if (cacsensorPageNumber >= 1)
				cacsensorPageNumber = cacsensorPageNumber - 1;
			else
				cacsensorPageNumber = 0;
			break;
		case "nextPage":
			if (cacsensorPageNumber < cacsensorTotalPage)
				cacsensorPageNumber = cacsensorPageNumber + 1;
			else
				cacsensorPageNumber = cacsensorTotalPage;
			break;
		case "lastPage":
			cacsensorPageNumber = cacsensorTotalPage;
			break;
		case "jumpPage":
			cacsensorPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacsensorPageNumber = 0;
			cacsensorPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			cacsensorTotalPage = cacsensorService
					.countRelativeCacCacsensors(cac_id) / cacsensorPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensorPageNumber >= 0
				&& cacsensorPageNumber <= cacsensorTotalPage)
			mav.addObject(
					"cacsensors",
					cacsensorDAO.findCacCacsensors(cac_id, cacsensorPageNumber
							* cacsensorPageSize, cacsensorPageSize));
		mav.addObject("cacsensorPageNumber", cacsensorPageNumber);
		mav.addObject("cacsensorPageSize", pageSize);
		mav.addObject("cacsensorPage", cacsensorPageType);
		mav.addObject("cacsensorWantToPage", request.getParameter("WantToPage"));
		mav.addObject("cacsensorTotalPage", cacsensorTotalPage);
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(cac_id));
		mav.setViewName("cac/cacsensors/viewCacsensors.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCac")
	public ModelAndView confirmDeleteCac(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("cac/deleteCac.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacProject")
	public ModelAndView confirmDeleteCacProject(@RequestParam Integer cac_id,
			@RequestParam Integer related_project_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project",
				projectDAO.findProjectByPrimaryKey(related_project_id));
		mav.addObject("cac_id", cac_id);

		mav.setViewName("cac/project/deleteProject.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacCacdevices")
	public ModelAndView confirmDeleteCacCacdevices(
			@RequestParam Integer cac_id,
			@RequestParam Integer related_cacdevices_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice",
				cacdeviceDAO.findCacdeviceByPrimaryKey(related_cacdevices_id));
		mav.addObject("cac_id", cac_id);

		mav.setViewName("cac/cacdevices/deleteCacdevices.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacCacsensors")
	public ModelAndView confirmDeleteCacCacsensors(
			@RequestParam Integer cac_id,
			@RequestParam Integer related_cacsensors_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor",
				cacsensorDAO.findCacsensorByPrimaryKey(related_cacsensors_id));
		mav.addObject("cac_id", cac_id);

		mav.setViewName("cac/cacsensors/deleteCacsensors.jsp");
		return mav;
	}

	@RequestMapping("/deleteCac")
	public String deleteCac(@RequestParam Integer id) {
		Cac cac = cacDAO.findCacByPrimaryKey(id);
		cacService.deleteCac(cac);
		return "forward:/indexCac";
	}

	@RequestMapping("/deleteCacProject")
	public ModelAndView deleteCacProject(@RequestParam Integer cac_id,
			@RequestParam Integer Relativeproject_id) {
		ModelAndView mav = new ModelAndView();
		Cac cac = cacService.deleteCacProject(cac_id, Relativeproject_id);
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacCacdevices")
	public ModelAndView deleteCacCacdevices(@RequestParam Integer cac_id,
			@RequestParam Integer Relativecacdevices_id) {
		ModelAndView mav = new ModelAndView();
		Cac cac = cacService.deleteCacCacdevices(cac_id, Relativecacdevices_id);
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacCacsensors")
	public ModelAndView deleteCacCacsensors(@RequestParam Integer cac_id,
			@RequestParam Integer Relativecacsensors_id) {
		ModelAndView mav = new ModelAndView();
		Cac cac = cacService.deleteCacCacsensors(cac_id, Relativecacsensors_id);
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/editCac")
	public ModelAndView editCac(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);
		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		Set<Cacdevice> havedCacdevices = cacDAO.findCacByPrimaryKey(id)
				.getRelativeCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		mav.addObject("havedCacdevices", havedCacdevices);
		Set<Cacsensor> allCacsensors = cacsensorDAO.findAllCacsensors();
		Set<Cacsensor> havedCacsensors = cacDAO.findCacByPrimaryKey(id)
				.getRelativeCacsensors();
		mav.addObject("allCacsensors", allCacsensors);
		mav.addObject("havedCacsensors", havedCacsensors);
		mav.addObject("allCacs", cacDAO.findAllCacs());
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/editCacProject")
	public ModelAndView editCacProject(@RequestParam Integer cac_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("project", project);
		mav.setViewName("cac/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/editCacCacdevices")
	public ModelAndView editCacCacdevices(@RequestParam Integer cac_id,
			@RequestParam Integer cacdevices_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevices_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cac/cacdevices/editCacdevices.jsp");

		return mav;
	}

	@RequestMapping("/editCacCacsensors")
	public ModelAndView editCacCacsensors(@RequestParam Integer cac_id,
			@RequestParam Integer cacsensors_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensors_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cacsensor", cacsensor);
		mav.setViewName("cac/cacsensors/editCacsensors.jsp");

		return mav;
	}

	@RequestMapping("/newCac")
	public ModelAndView newCac() {
		ModelAndView mav = new ModelAndView();

		Set<Project> allProjects = projectDAO.findAllProjects();
		mav.addObject("allProjects", allProjects);

		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		Set<Cacsensor> allCacsensors = cacsensorDAO.findAllCacsensors();
		mav.addObject("allCacsensors", allCacsensors);
		mav.addObject("allCacs", cacDAO.findAllCacs());
		mav.addObject("cac", new Cac());
		mav.addObject("newFlag", true);
		mav.setViewName("cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/newCacProject")
	public ModelAndView newCacProject(@RequestParam Integer cac_id,
			@RequestParam Integer project_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("project", project);
		mav.addObject("newFlag", true);
		mav.setViewName("cac/project/editProject.jsp");

		return mav;
	}

	@RequestMapping("/newCacCacdevices")
	public ModelAndView newCacCacdevices(@RequestParam Integer cac_id,
			@RequestParam Integer cacdevices_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevices_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cacdevice", cacdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("cac/cacdevices/editCacdevices.jsp");

		return mav;
	}

	@RequestMapping("/newCacCacsensors")
	public ModelAndView newCacCacsensors(@RequestParam Integer cac_id,
			@RequestParam Integer cacsensors_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensors_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cacsensor", cacsensor);
		mav.addObject("newFlag", true);
		mav.setViewName("cac/cacsensors/editCacsensors.jsp");

		return mav;
	}

	@RequestMapping("/listCacProject")
	public ModelAndView listCacProject(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("project", projectDAO.findProjectByPrimaryKey(id));
		mav.setViewName("cac/project/listProject.jsp");

		return mav;
	}

	Project project;

	@RequestMapping("/listCacCacdevices")
	public ModelAndView listCacCacdevices(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cac/cacdevices/listCacdevices.jsp");

		return mav;
	}

	@RequestMapping("/listCacCacsensors")
	public ModelAndView listCacCacsensors(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor", cacsensorDAO.findCacsensorByPrimaryKey(id));
		mav.setViewName("cac/cacsensors/listCacsensors.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveCac")
	public String saveCac(@ModelAttribute Cac cac) {
		cacService.saveCac(cac);

		return "forward:/indexCac";
	}

	@RequestMapping("/saveCacProject")
	public ModelAndView saveCacProject(@RequestParam Integer cac_id,
			@ModelAttribute Project project) {
		Cac parent_cac = cacService.saveCacProject(cac_id, project);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", parent_cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/saveCacCacdevices")
	public ModelAndView saveCacCacdevices(@RequestParam Integer cac_id,
			@ModelAttribute Cacdevice cacdevices) {
		Cac parent_cac = cacService.saveCacCacdevices(cac_id, cacdevices);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", parent_cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/saveCacCacsensors")
	public ModelAndView saveCacCacsensors(@RequestParam Integer cac_id,
			@ModelAttribute Cacsensor cacsensors) {
		Cac parent_cac = cacService.saveCacCacsensors(cac_id, cacsensors);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cac_id", cac_id);

		mav.addObject("cac", parent_cac);
		mav.setViewName("cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/cacController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}