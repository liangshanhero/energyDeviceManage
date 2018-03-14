package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacmalfunctionDAO;

import cn.edu.scau.cmi.service.CacmalfunctionService;

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

@Controller("CacmalfunctionController")
public class CacmalfunctionController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@RequestMapping("/indexCacmalfunction")
	public ModelAndView listCacmalfunctions(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacmalfunctionService.countcacmalfunctions() / pageSize;
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
			totalPage = cacmalfunctionService.countcacmalfunctions() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject(
					"cacmalfunctions",
					cacmalfunctionService.loadCacmalfunctions(pageNumber
							* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacmalfunction/listCacmalfunctions.jsp");
		return mav;
	}

	public String indexCacmalfunction() {
		return "redirect:/indexCacmalfunction";
	}

	@RequestMapping("/selectCacmalfunction")
	public ModelAndView selectCacmalfunction(
			@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacdevice, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice));
		mav.setViewName("cacmalfunction/viewCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/selectCacmalfunctionCacdevice")
	public ModelAndView selectCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacmalfunction/cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/selectCacmalfunctionCacrecordtime")
	public ModelAndView selectCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacmalfunction/cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacmalfunction")
	public ModelAndView confirmDeleteCacmalfunction(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacdevice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice));
		mav.setViewName("cacmalfunction/deleteCacmalfunction.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacmalfunctionCacdevice")
	public ModelAndView confirmDeleteCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer related_cacdevice_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice",
				cacdeviceDAO.findCacdeviceByPrimaryKey(related_cacdevice_id));
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.setViewName("cacmalfunction/cacdevice/deleteCacdevice.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacmalfunctionCacrecordtime")
	public ModelAndView confirmDeleteCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer related_cacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id));
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.setViewName("cacmalfunction/cacrecordtime/deleteCacrecordtime.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacmalfunction")
	public String deleteCacmalfunction(@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice);
		cacmalfunctionService.deleteCacmalfunction(cacmalfunction);
		return "forward:/indexCacmalfunction";
	}

	@RequestMapping("/deleteCacmalfunctionCacdevice")
	public ModelAndView deleteCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer Relativecacdevice_id) {
		ModelAndView mav = new ModelAndView();
		Cacmalfunction cacmalfunction = cacmalfunctionService
				.deleteCacmalfunctionCacdevice(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice, Relativecacdevice_id);
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.setViewName("cacmalfunction/viewCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacmalfunctionCacrecordtime")
	public ModelAndView deleteCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer Relativecacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		Cacmalfunction cacmalfunction = cacmalfunctionService
				.deleteCacmalfunctionCacrecordtime(
						cacmalfunction_cacrecordtime, cacmalfunction_cacdevice,
						Relativecacrecordtime_id);
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.setViewName("cacmalfunction/viewCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/editCacmalfunction")
	public ModelAndView editCacmalfunction(@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacdevice) {
		ModelAndView mav = new ModelAndView();
		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);
		mav.addObject("allCacmalfunctions",
				cacmalfunctionDAO.findAllCacmalfunctions());
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice));
		mav.setViewName("cacmalfunction/editCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/editCacmalfunctionCacdevice")
	public ModelAndView editCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacmalfunction/cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/editCacmalfunctionCacrecordtime")
	public ModelAndView editCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacmalfunction/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/newCacmalfunction")
	public ModelAndView newCacmalfunction() {
		ModelAndView mav = new ModelAndView();

		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);

		mav.addObject("allCacmalfunctions",
				cacmalfunctionDAO.findAllCacmalfunctions());
		mav.addObject("cacmalfunction", new Cacmalfunction());
		mav.addObject("newFlag", true);
		mav.setViewName("cacmalfunction/editCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/newCacmalfunctionCacdevice")
	public ModelAndView newCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacdevice", cacdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("cacmalfunction/cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/newCacmalfunctionCacrecordtime")
	public ModelAndView newCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.addObject("newFlag", true);
		mav.setViewName("cacmalfunction/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/listCacmalfunctionCacdevice")
	public ModelAndView listCacmalfunctionCacdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cacmalfunction/cacdevice/listCacdevice.jsp");

		return mav;
	}

	Cacdevice cacdevice;

	@RequestMapping("/listCacmalfunctionCacrecordtime")
	public ModelAndView listCacmalfunctionCacrecordtime(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacmalfunction/cacrecordtime/listCacrecordtime.jsp");

		return mav;
	}

	Cacrecordtime cacrecordtime;

	@Scope("prototype")
	@RequestMapping("/saveCacmalfunction")
	public String saveCacmalfunction(
			@ModelAttribute Cacmalfunction cacmalfunction) {
		cacmalfunctionService.saveCacmalfunction(cacmalfunction);

		return "forward:/indexCacmalfunction";
	}

	@RequestMapping("/saveCacmalfunctionCacdevice")
	public ModelAndView saveCacmalfunctionCacdevice(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@ModelAttribute Cacdevice cacdevice) {
		Cacmalfunction parent_cacmalfunction = cacmalfunctionService
				.saveCacmalfunctionCacdevice(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice, cacdevice);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacmalfunction", parent_cacmalfunction);
		mav.setViewName("cacmalfunction/viewCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/saveCacmalfunctionCacrecordtime")
	public ModelAndView saveCacmalfunctionCacrecordtime(
			@RequestParam Integer cacmalfunction_cacrecordtime,
			@RequestParam Integer cacmalfunction_cacdevice,
			@ModelAttribute Cacrecordtime cacrecordtime) {
		Cacmalfunction parent_cacmalfunction = cacmalfunctionService
				.saveCacmalfunctionCacrecordtime(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice, cacrecordtime);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction_cacrecordtime",
				cacmalfunction_cacrecordtime);
		mav.addObject("cacmalfunction_cacdevice", cacmalfunction_cacdevice);

		mav.addObject("cacmalfunction", parent_cacmalfunction);
		mav.setViewName("cacmalfunction/viewCacmalfunction.jsp");

		return mav;
	}

	@RequestMapping("/cacmalfunctionController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}