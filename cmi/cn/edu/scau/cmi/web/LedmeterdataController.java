package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.LedmeterdataDAO;

import cn.edu.scau.cmi.service.LedmeterdataService;

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

@Controller("LedmeterdataController")
public class LedmeterdataController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterdataService ledmeterdataService;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@RequestMapping("/indexLedmeterdata")
	public ModelAndView listLedmeterdatas(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = ledmeterdataService.countledmeterdatas() / pageSize;
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
			totalPage = ledmeterdataService.countledmeterdatas() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("ledmeterdatas", ledmeterdataService
					.loadLedmeterdatas(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("ledmeterdata/listLedmeterdatas.jsp");
		return mav;
	}

	public String indexLedmeterdata() {
		return "redirect:/indexLedmeterdata";
	}

	@RequestMapping("/selectLedmeterdata")
	public ModelAndView selectLedmeterdata(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata",
				ledmeterdataDAO.findLedmeterdataByPrimaryKey(id));
		mav.setViewName("ledmeterdata/viewLedmeterdata.jsp");

		return mav;
	}

	@RequestMapping("/selectLedmeterdataLedmeter")
	public ModelAndView selectLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@RequestParam Integer ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.addObject("ledmeter", ledmeter);
		mav.setViewName("ledmeterdata/ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteLedmeterdata")
	public ModelAndView confirmDeleteLedmeterdata(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata",
				ledmeterdataDAO.findLedmeterdataByPrimaryKey(id));
		mav.setViewName("ledmeterdata/deleteLedmeterdata.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteLedmeterdataLedmeter")
	public ModelAndView confirmDeleteLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@RequestParam Integer related_ledmeter_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter",
				ledmeterDAO.findLedmeterByPrimaryKey(related_ledmeter_id));
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.setViewName("ledmeterdata/ledmeter/deleteLedmeter.jsp");
		return mav;
	}

	@RequestMapping("/deleteLedmeterdata")
	public String deleteLedmeterdata(@RequestParam Integer id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(id);
		ledmeterdataService.deleteLedmeterdata(ledmeterdata);
		return "forward:/indexLedmeterdata";
	}

	@RequestMapping("/deleteLedmeterdataLedmeter")
	public ModelAndView deleteLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@RequestParam Integer Relativeledmeter_id) {
		ModelAndView mav = new ModelAndView();
		Ledmeterdata ledmeterdata = ledmeterdataService
				.deleteLedmeterdataLedmeter(ledmeterdata_id,
						Relativeledmeter_id);
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.addObject("ledmeterdata", ledmeterdata);
		mav.setViewName("ledmeterdata/viewLedmeterdata.jsp");

		return mav;
	}

	@RequestMapping("/editLedmeterdata")
	public ModelAndView editLedmeterdata(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Ledmeter> allLedmeters = ledmeterDAO.findAllLedmeters();
		mav.addObject("allLedmeters", allLedmeters);
		mav.addObject("allLedmeterdatas",
				ledmeterdataDAO.findAllLedmeterdatas());
		mav.addObject("ledmeterdata",
				ledmeterdataDAO.findLedmeterdataByPrimaryKey(id));
		mav.setViewName("ledmeterdata/editLedmeterdata.jsp");

		return mav;
	}

	@RequestMapping("/editLedmeterdataLedmeter")
	public ModelAndView editLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@RequestParam Integer ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.addObject("ledmeter", ledmeter);
		mav.setViewName("ledmeterdata/ledmeter/editLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/newLedmeterdata")
	public ModelAndView newLedmeterdata() {
		ModelAndView mav = new ModelAndView();

		Set<Ledmeter> allLedmeters = ledmeterDAO.findAllLedmeters();
		mav.addObject("allLedmeters", allLedmeters);

		mav.addObject("allLedmeterdatas",
				ledmeterdataDAO.findAllLedmeterdatas());
		mav.addObject("ledmeterdata", new Ledmeterdata());
		mav.addObject("newFlag", true);
		mav.setViewName("ledmeterdata/editLedmeterdata.jsp");

		return mav;
	}

	@RequestMapping("/newLedmeterdataLedmeter")
	public ModelAndView newLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@RequestParam Integer ledmeter_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.addObject("ledmeter", ledmeter);
		mav.addObject("newFlag", true);
		mav.setViewName("ledmeterdata/ledmeter/editLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/listLedmeterdataLedmeter")
	public ModelAndView listLedmeterdataLedmeter(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter", ledmeterDAO.findLedmeterByPrimaryKey(id));
		mav.setViewName("ledmeterdata/ledmeter/listLedmeter.jsp");

		return mav;
	}

	Ledmeter ledmeter;

	@Scope("prototype")
	@RequestMapping("/saveLedmeterdata")
	public String saveLedmeterdata(@ModelAttribute Ledmeterdata ledmeterdata) {
		ledmeterdataService.saveLedmeterdata(ledmeterdata);

		return "forward:/indexLedmeterdata";
	}

	@RequestMapping("/saveLedmeterdataLedmeter")
	public ModelAndView saveLedmeterdataLedmeter(
			@RequestParam Integer ledmeterdata_id,
			@ModelAttribute Ledmeter ledmeter) {
		Ledmeterdata parent_ledmeterdata = ledmeterdataService
				.saveLedmeterdataLedmeter(ledmeterdata_id, ledmeter);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata_id", ledmeterdata_id);

		mav.addObject("ledmeterdata", parent_ledmeterdata);
		mav.setViewName("ledmeterdata/viewLedmeterdata.jsp");

		return mav;
	}

	@RequestMapping("/ledmeterdataController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}