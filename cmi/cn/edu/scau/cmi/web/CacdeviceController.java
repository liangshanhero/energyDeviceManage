package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacdeviceDAO;

import cn.edu.scau.cmi.service.CacdeviceService;

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

@Controller("CacdeviceController")
public class CacdeviceController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacdevicedataPageNumber = 0;
	static int cacdevicedataPageSize = 10;
	static int cacmalfunctionPageNumber = 0;
	static int cacmalfunctionPageSize = 10;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacdeviceService cacdeviceService;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@RequestMapping("/indexCacdevice")
	public ModelAndView listCacdevices(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacdeviceService.countcacdevices() / pageSize;
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
			totalPage = cacdeviceService.countcacdevices() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("cacdevices", cacdeviceService.loadCacdevices(
					pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacdevice/listCacdevices.jsp");
		return mav;
	}

	public String indexCacdevice() {
		return "redirect:/indexCacdevice";
	}

	@RequestMapping("/selectCacdevice")
	public ModelAndView selectCacdevice(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdevicedataTotalPage = cacdevicedataService
				.countRelativeCacdeviceCacdevicedatas(id)
				/ cacdevicedataPageSize;
		String cacdevicedataPageType = request
				.getParameter("cacdevicedataPage");
		switch (cacdevicedataPageType) {
		case "homePage":
			cacdevicedataPageNumber = 0;
			break;
		case "previousPage":
			if (cacdevicedataPageNumber >= 1)
				cacdevicedataPageNumber = cacdevicedataPageNumber - 1;
			else
				cacdevicedataPageNumber = 0;
			break;
		case "nextPage":
			if (cacdevicedataPageNumber < cacdevicedataTotalPage)
				cacdevicedataPageNumber = cacdevicedataPageNumber + 1;
			else
				cacdevicedataPageNumber = cacdevicedataTotalPage;
			break;
		case "lastPage":
			cacdevicedataPageNumber = cacdevicedataTotalPage;
			break;
		case "jumpPage":
			cacdevicedataPageNumber = Integer.parseInt(request
					.getParameter("cacdevicedataWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacdevicedataPageNumber = 0;
			cacdevicedataPageSize = Integer.parseInt(request
					.getParameter("cacdevicedataPageSize"));
			cacdevicedataTotalPage = cacdevicedataService
					.countRelativeCacdeviceCacdevicedatas(id)
					/ cacdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicedataPageNumber >= 0
				&& cacdevicedataPageNumber <= cacdevicedataTotalPage)
			mav.addObject("cacdevicedatas", cacdevicedataDAO
					.findCacdeviceCacdevicedatas(id, cacdevicedataPageNumber
							* cacdevicedataPageSize, cacdevicedataPageSize));
		mav.addObject("cacdevicedataPageNumber", cacdevicedataPageNumber);
		mav.addObject("cacdevicedataPage", cacdevicedataPageType);
		mav.addObject("cacdevicedataWantToPagePage",
				request.getParameter("cacdevicedataWantToPage"));
		mav.addObject("cacdevicedataPageSize", cacdevicedataPageSize);
		mav.addObject("cacdevicedataTotalPage", cacdevicedataTotalPage);
		int cacmalfunctionTotalPage = cacmalfunctionService
				.countRelativeCacdeviceCacmalfunctions(id)
				/ cacmalfunctionPageSize;
		String cacmalfunctionPageType = request
				.getParameter("cacmalfunctionPage");
		switch (cacmalfunctionPageType) {
		case "homePage":
			cacmalfunctionPageNumber = 0;
			break;
		case "previousPage":
			if (cacmalfunctionPageNumber >= 1)
				cacmalfunctionPageNumber = cacmalfunctionPageNumber - 1;
			else
				cacmalfunctionPageNumber = 0;
			break;
		case "nextPage":
			if (cacmalfunctionPageNumber < cacmalfunctionTotalPage)
				cacmalfunctionPageNumber = cacmalfunctionPageNumber + 1;
			else
				cacmalfunctionPageNumber = cacmalfunctionTotalPage;
			break;
		case "lastPage":
			cacmalfunctionPageNumber = cacmalfunctionTotalPage;
			break;
		case "jumpPage":
			cacmalfunctionPageNumber = Integer.parseInt(request
					.getParameter("cacmalfunctionWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacmalfunctionPageNumber = 0;
			cacmalfunctionPageSize = Integer.parseInt(request
					.getParameter("cacmalfunctionPageSize"));
			cacmalfunctionTotalPage = cacmalfunctionService
					.countRelativeCacdeviceCacmalfunctions(id)
					/ cacmalfunctionPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacmalfunctionPageNumber >= 0
				&& cacmalfunctionPageNumber <= cacmalfunctionTotalPage)
			mav.addObject("cacmalfunctions", cacmalfunctionDAO
					.findCacdeviceCacmalfunctions(id, cacmalfunctionPageNumber
							* cacmalfunctionPageSize, cacmalfunctionPageSize));
		mav.addObject("cacmalfunctionPageNumber", cacmalfunctionPageNumber);
		mav.addObject("cacmalfunctionPage", cacmalfunctionPageType);
		mav.addObject("cacmalfunctionWantToPagePage",
				request.getParameter("cacmalfunctionWantToPage"));
		mav.addObject("cacmalfunctionPageSize", cacmalfunctionPageSize);
		mav.addObject("cacmalfunctionTotalPage", cacmalfunctionTotalPage);
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/selectCacdeviceCac")
	public ModelAndView selectCacdeviceCac(@RequestParam Integer cacdevice_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cac", cac);
		mav.setViewName("cacdevice/cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/selectCacdeviceCacdevicedatas")
	public ModelAndView selectCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdevicedataTotalPage = cacdevicedataService
				.countRelativeCacdeviceCacdevicedatas(cacdevice_id)
				/ cacdevicedataPageSize;
		String cacdevicedataPageType = request.getParameter("page");
		switch (cacdevicedataPageType) {
		case "homePage":
			cacdevicedataPageNumber = 0;
			break;
		case "previousPage":
			if (cacdevicedataPageNumber >= 1)
				cacdevicedataPageNumber = cacdevicedataPageNumber - 1;
			else
				cacdevicedataPageNumber = 0;
			break;
		case "nextPage":
			if (cacdevicedataPageNumber < cacdevicedataTotalPage)
				cacdevicedataPageNumber = cacdevicedataPageNumber + 1;
			else
				cacdevicedataPageNumber = cacdevicedataTotalPage;
			break;
		case "lastPage":
			cacdevicedataPageNumber = cacdevicedataTotalPage;
			break;
		case "jumpPage":
			cacdevicedataPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacdevicedataPageNumber = 0;
			cacdevicedataPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			cacdevicedataTotalPage = cacdevicedataService
					.countRelativeCacdeviceCacdevicedatas(cacdevice_id)
					/ cacdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicedataPageNumber >= 0
				&& cacdevicedataPageNumber <= cacdevicedataTotalPage)
			mav.addObject("cacdevicedatas", cacdevicedataDAO
					.findCacdeviceCacdevicedatas(cacdevice_id,
							cacdevicedataPageNumber * cacdevicedataPageSize,
							cacdevicedataPageSize));
		mav.addObject("cacdevicedataPageNumber", cacdevicedataPageNumber);
		mav.addObject("cacdevicedataPageSize", pageSize);
		mav.addObject("cacdevicedataPage", cacdevicedataPageType);
		mav.addObject("cacdevicedataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacdevicedataTotalPage", cacdevicedataTotalPage);
		mav.addObject("cacdevice",
				cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice_id));
		mav.setViewName("cacdevice/cacdevicedatas/viewCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/selectCacdeviceCacmalfunctions")
	public ModelAndView selectCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacmalfunctionTotalPage = cacmalfunctionService
				.countRelativeCacdeviceCacmalfunctions(cacdevice_id)
				/ cacmalfunctionPageSize;
		String cacmalfunctionPageType = request.getParameter("page");
		switch (cacmalfunctionPageType) {
		case "homePage":
			cacmalfunctionPageNumber = 0;
			break;
		case "previousPage":
			if (cacmalfunctionPageNumber >= 1)
				cacmalfunctionPageNumber = cacmalfunctionPageNumber - 1;
			else
				cacmalfunctionPageNumber = 0;
			break;
		case "nextPage":
			if (cacmalfunctionPageNumber < cacmalfunctionTotalPage)
				cacmalfunctionPageNumber = cacmalfunctionPageNumber + 1;
			else
				cacmalfunctionPageNumber = cacmalfunctionTotalPage;
			break;
		case "lastPage":
			cacmalfunctionPageNumber = cacmalfunctionTotalPage;
			break;
		case "jumpPage":
			cacmalfunctionPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacmalfunctionPageNumber = 0;
			cacmalfunctionPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			cacmalfunctionTotalPage = cacmalfunctionService
					.countRelativeCacdeviceCacmalfunctions(cacdevice_id)
					/ cacmalfunctionPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacmalfunctionPageNumber >= 0
				&& cacmalfunctionPageNumber <= cacmalfunctionTotalPage)
			mav.addObject("cacmalfunctions", cacmalfunctionDAO
					.findCacdeviceCacmalfunctions(cacdevice_id,
							cacmalfunctionPageNumber * cacmalfunctionPageSize,
							cacmalfunctionPageSize));
		mav.addObject("cacmalfunctionPageNumber", cacmalfunctionPageNumber);
		mav.addObject("cacmalfunctionPageSize", pageSize);
		mav.addObject("cacmalfunctionPage", cacmalfunctionPageType);
		mav.addObject("cacmalfunctionWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacmalfunctionTotalPage", cacmalfunctionTotalPage);
		mav.addObject("cacdevice",
				cacdeviceDAO.findCacdeviceByPrimaryKey(cacdevice_id));
		mav.setViewName("cacdevice/cacmalfunctions/viewCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacdevice")
	public ModelAndView confirmDeleteCacdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cacdevice/deleteCacdevice.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacdeviceCac")
	public ModelAndView confirmDeleteCacdeviceCac(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer related_cac_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(related_cac_id));
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.setViewName("cacdevice/cac/deleteCac.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacdeviceCacdevicedatas")
	public ModelAndView confirmDeleteCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer related_cacdevicedatas_cacdevice,
			@RequestParam Integer related_cacdevicedatas_cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						related_cacdevicedatas_cacdevice,
						related_cacdevicedatas_cacrecordtime));
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.setViewName("cacdevice/cacdevicedatas/deleteCacdevicedatas.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacdeviceCacmalfunctions")
	public ModelAndView confirmDeleteCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer related_cacmalfunctions_cacrecordtime,
			@RequestParam Integer related_cacmalfunctions_cacdevice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						related_cacmalfunctions_cacrecordtime,
						related_cacmalfunctions_cacdevice));
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.setViewName("cacdevice/cacmalfunctions/deleteCacmalfunctions.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacdevice")
	public String deleteCacdevice(@RequestParam Integer id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(id);
		cacdeviceService.deleteCacdevice(cacdevice);
		return "forward:/indexCacdevice";
	}

	@RequestMapping("/deleteCacdeviceCac")
	public ModelAndView deleteCacdeviceCac(@RequestParam Integer cacdevice_id,
			@RequestParam Integer Relativecac_id) {
		ModelAndView mav = new ModelAndView();
		Cacdevice cacdevice = cacdeviceService.deleteCacdeviceCac(cacdevice_id,
				Relativecac_id);
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacdeviceCacdevicedatas")
	public ModelAndView deleteCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer Relativecacdevicedatas_cacdevice,
			@RequestParam Integer Relativecacdevicedatas_cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		Cacdevice cacdevice = cacdeviceService.deleteCacdeviceCacdevicedatas(
				cacdevice_id, Relativecacdevicedatas_cacdevice,
				Relativecacdevicedatas_cacrecordtime);
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacdeviceCacmalfunctions")
	public ModelAndView deleteCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer Relativecacmalfunctions_cacrecordtime,
			@RequestParam Integer Relativecacmalfunctions_cacdevice) {
		ModelAndView mav = new ModelAndView();
		Cacdevice cacdevice = cacdeviceService.deleteCacdeviceCacmalfunctions(
				cacdevice_id, Relativecacmalfunctions_cacrecordtime,
				Relativecacmalfunctions_cacdevice);
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/editCacdevice")
	public ModelAndView editCacdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Cac> allCacs = cacDAO.findAllCacs();
		mav.addObject("allCacs", allCacs);
		Set<Cacdevicedata> allCacdevicedatas = cacdevicedataDAO
				.findAllCacdevicedatas();
		Set<Cacdevicedata> havedCacdevicedatas = cacdeviceDAO
				.findCacdeviceByPrimaryKey(id).getRelativeCacdevicedatas();
		mav.addObject("allCacdevicedatas", allCacdevicedatas);
		mav.addObject("havedCacdevicedatas", havedCacdevicedatas);
		Set<Cacmalfunction> allCacmalfunctions = cacmalfunctionDAO
				.findAllCacmalfunctions();
		Set<Cacmalfunction> havedCacmalfunctions = cacdeviceDAO
				.findCacdeviceByPrimaryKey(id).getRelativeCacmalfunctions();
		mav.addObject("allCacmalfunctions", allCacmalfunctions);
		mav.addObject("havedCacmalfunctions", havedCacmalfunctions);
		mav.addObject("allCacdevices", cacdeviceDAO.findAllCacdevices());
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/editCacdeviceCac")
	public ModelAndView editCacdeviceCac(@RequestParam Integer cacdevice_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cac", cac);
		mav.setViewName("cacdevice/cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/editCacdeviceCacdevicedatas")
	public ModelAndView editCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer cacdevicedatas_cacdevice,
			@RequestParam Integer cacdevicedatas_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedatas_cacdevice,
						cacdevicedatas_cacrecordtime, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.setViewName("cacdevice/cacdevicedatas/editCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/editCacdeviceCacmalfunctions")
	public ModelAndView editCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer cacmalfunctions_cacrecordtime,
			@RequestParam Integer cacmalfunctions_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunctions_cacrecordtime,
						cacmalfunctions_cacdevice, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.setViewName("cacdevice/cacmalfunctions/editCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/newCacdevice")
	public ModelAndView newCacdevice() {
		ModelAndView mav = new ModelAndView();

		Set<Cac> allCacs = cacDAO.findAllCacs();
		mav.addObject("allCacs", allCacs);

		Set<Cacdevicedata> allCacdevicedatas = cacdevicedataDAO
				.findAllCacdevicedatas();
		mav.addObject("allCacdevicedatas", allCacdevicedatas);
		Set<Cacmalfunction> allCacmalfunctions = cacmalfunctionDAO
				.findAllCacmalfunctions();
		mav.addObject("allCacmalfunctions", allCacmalfunctions);
		mav.addObject("allCacdevices", cacdeviceDAO.findAllCacdevices());
		mav.addObject("cacdevice", new Cacdevice());
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/newCacdeviceCac")
	public ModelAndView newCacdeviceCac(@RequestParam Integer cacdevice_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cac", cac);
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevice/cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/newCacdeviceCacdevicedatas")
	public ModelAndView newCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer cacdevicedatas_cacdevice,
			@RequestParam Integer cacdevicedatas_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedatas_cacdevice,
						cacdevicedatas_cacrecordtime, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevice/cacdevicedatas/editCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/newCacdeviceCacmalfunctions")
	public ModelAndView newCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id,
			@RequestParam Integer cacmalfunctions_cacrecordtime,
			@RequestParam Integer cacmalfunctions_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunctions_cacrecordtime,
						cacmalfunctions_cacdevice, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevice/cacmalfunctions/editCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/listCacdeviceCac")
	public ModelAndView listCacdeviceCac(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("cacdevice/cac/listCac.jsp");

		return mav;
	}

	Cac cac;

	@RequestMapping("/listCacdeviceCacdevicedatas")
	public ModelAndView listCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice, @RequestParam Integer cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime));
		mav.setViewName("cacdevice/cacdevicedatas/listCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/listCacdeviceCacmalfunctions")
	public ModelAndView listCacdeviceCacmalfunctions(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacdevice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice));
		mav.setViewName("cacdevice/cacmalfunctions/listCacmalfunctions.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveCacdevice")
	public String saveCacdevice(@ModelAttribute Cacdevice cacdevice) {
		cacdeviceService.saveCacdevice(cacdevice);

		return "forward:/indexCacdevice";
	}

	@RequestMapping("/saveCacdeviceCac")
	public ModelAndView saveCacdeviceCac(@RequestParam Integer cacdevice_id,
			@ModelAttribute Cac cac) {
		Cacdevice parent_cacdevice = cacdeviceService.saveCacdeviceCac(
				cacdevice_id, cac);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", parent_cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/saveCacdeviceCacdevicedatas")
	public ModelAndView saveCacdeviceCacdevicedatas(
			@RequestParam Integer cacdevice_id,
			@ModelAttribute Cacdevicedata cacdevicedatas) {
		Cacdevice parent_cacdevice = cacdeviceService
				.saveCacdeviceCacdevicedatas(cacdevice_id, cacdevicedatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", parent_cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/saveCacdeviceCacmalfunctions")
	public ModelAndView saveCacdeviceCacmalfunctions(
			@RequestParam Integer cacdevice_id,
			@ModelAttribute Cacmalfunction cacmalfunctions) {
		Cacdevice parent_cacdevice = cacdeviceService
				.saveCacdeviceCacmalfunctions(cacdevice_id, cacmalfunctions);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice_id", cacdevice_id);

		mav.addObject("cacdevice", parent_cacdevice);
		mav.setViewName("cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/cacdeviceController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}