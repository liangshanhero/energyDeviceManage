package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacdevicedataDAO;

import cn.edu.scau.cmi.service.CacdevicedataService;

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

@Controller("CacdevicedataController")
public class CacdevicedataController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@RequestMapping("/indexCacdevicedata")
	public ModelAndView listCacdevicedatas(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacdevicedataService.countcacdevicedatas() / pageSize;
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
			totalPage = cacdevicedataService.countcacdevicedatas() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject(
					"cacdevicedatas",
					cacdevicedataService.loadCacdevicedatas(pageNumber
							* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacdevicedata/listCacdevicedatas.jsp");
		return mav;
	}

	public String indexCacdevicedata() {
		return "redirect:/indexCacdevicedata";
	}

	@RequestMapping("/selectCacdevicedata")
	public ModelAndView selectCacdevicedata(@RequestParam Integer cacdevice,
			@RequestParam Integer cacrecordtime, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime));
		mav.setViewName("cacdevicedata/viewCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/selectCacdevicedataCacdevice")
	public ModelAndView selectCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacdevicedata/cacdevice/viewCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/selectCacdevicedataCacrecordtime")
	public ModelAndView selectCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacdevicedata/cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacdevicedata")
	public ModelAndView confirmDeleteCacdevicedata(
			@RequestParam Integer cacdevice, @RequestParam Integer cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime));
		mav.setViewName("cacdevicedata/deleteCacdevicedata.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacdevicedataCacdevice")
	public ModelAndView confirmDeleteCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer related_cacdevice_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice",
				cacdeviceDAO.findCacdeviceByPrimaryKey(related_cacdevice_id));
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.setViewName("cacdevicedata/cacdevice/deleteCacdevice.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacdevicedataCacrecordtime")
	public ModelAndView confirmDeleteCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer related_cacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id));
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.setViewName("cacdevicedata/cacrecordtime/deleteCacrecordtime.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacdevicedata")
	public String deleteCacdevicedata(@RequestParam Integer cacdevice,
			@RequestParam Integer cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime);
		cacdevicedataService.deleteCacdevicedata(cacdevicedata);
		return "forward:/indexCacdevicedata";
	}

	@RequestMapping("/deleteCacdevicedataCacdevice")
	public ModelAndView deleteCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer Relativecacdevice_id) {
		ModelAndView mav = new ModelAndView();
		Cacdevicedata cacdevicedata = cacdevicedataService
				.deleteCacdevicedataCacdevice(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, Relativecacdevice_id);
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.setViewName("cacdevicedata/viewCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacdevicedataCacrecordtime")
	public ModelAndView deleteCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer Relativecacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		Cacdevicedata cacdevicedata = cacdevicedataService
				.deleteCacdevicedataCacrecordtime(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, Relativecacrecordtime_id);
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.setViewName("cacdevicedata/viewCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/editCacdevicedata")
	public ModelAndView editCacdevicedata(@RequestParam Integer cacdevice,
			@RequestParam Integer cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);
		mav.addObject("allCacdevicedatas",
				cacdevicedataDAO.findAllCacdevicedatas());
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime));
		mav.setViewName("cacdevicedata/editCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/editCacdevicedataCacdevice")
	public ModelAndView editCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevice", cacdevice);
		mav.setViewName("cacdevicedata/cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/editCacdevicedataCacrecordtime")
	public ModelAndView editCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacdevicedata/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/newCacdevicedata")
	public ModelAndView newCacdevicedata() {
		ModelAndView mav = new ModelAndView();

		Set<Cacdevice> allCacdevices = cacdeviceDAO.findAllCacdevices();
		mav.addObject("allCacdevices", allCacdevices);
		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);

		mav.addObject("allCacdevicedatas",
				cacdevicedataDAO.findAllCacdevicedatas());
		mav.addObject("cacdevicedata", new Cacdevicedata());
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevicedata/editCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/newCacdevicedataCacdevice")
	public ModelAndView newCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacdevice_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevice", cacdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevicedata/cacdevice/editCacdevice.jsp");

		return mav;
	}

	@RequestMapping("/newCacdevicedataCacrecordtime")
	public ModelAndView newCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.addObject("newFlag", true);
		mav.setViewName("cacdevicedata/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/listCacdevicedataCacdevice")
	public ModelAndView listCacdevicedataCacdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevice", cacdeviceDAO.findCacdeviceByPrimaryKey(id));
		mav.setViewName("cacdevicedata/cacdevice/listCacdevice.jsp");

		return mav;
	}

	Cacdevice cacdevice;

	@RequestMapping("/listCacdevicedataCacrecordtime")
	public ModelAndView listCacdevicedataCacrecordtime(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacdevicedata/cacrecordtime/listCacrecordtime.jsp");

		return mav;
	}

	Cacrecordtime cacrecordtime;

	@Scope("prototype")
	@RequestMapping("/saveCacdevicedata")
	public String saveCacdevicedata(@ModelAttribute Cacdevicedata cacdevicedata) {
		cacdevicedataService.saveCacdevicedata(cacdevicedata);

		return "forward:/indexCacdevicedata";
	}

	@RequestMapping("/saveCacdevicedataCacdevice")
	public ModelAndView saveCacdevicedataCacdevice(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@ModelAttribute Cacdevice cacdevice) {
		Cacdevicedata parent_cacdevicedata = cacdevicedataService
				.saveCacdevicedataCacdevice(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, cacdevice);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevicedata", parent_cacdevicedata);
		mav.setViewName("cacdevicedata/viewCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/saveCacdevicedataCacrecordtime")
	public ModelAndView saveCacdevicedataCacrecordtime(
			@RequestParam Integer cacdevicedata_cacdevice,
			@RequestParam Integer cacdevicedata_cacrecordtime,
			@ModelAttribute Cacrecordtime cacrecordtime) {
		Cacdevicedata parent_cacdevicedata = cacdevicedataService
				.saveCacdevicedataCacrecordtime(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, cacrecordtime);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata_cacdevice", cacdevicedata_cacdevice);
		mav.addObject("cacdevicedata_cacrecordtime",
				cacdevicedata_cacrecordtime);

		mav.addObject("cacdevicedata", parent_cacdevicedata);
		mav.setViewName("cacdevicedata/viewCacdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/cacdevicedataController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}