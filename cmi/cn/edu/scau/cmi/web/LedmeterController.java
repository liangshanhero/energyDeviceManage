package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.LedmeterDAO;

import cn.edu.scau.cmi.service.LedmeterService;

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

@Controller("LedmeterController")
public class LedmeterController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int ledmeterdataPageNumber = 0;
	static int ledmeterdataPageSize = 10;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedmeterService ledmeterService;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterdataService ledmeterdataService;

	@RequestMapping("/indexLedmeter")
	public ModelAndView listLedmeters(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = ledmeterService.countledmeters() / pageSize;
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
			totalPage = ledmeterService.countledmeters() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("ledmeters", ledmeterService.loadLedmeters(pageNumber
					* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("ledmeter/listLedmeters.jsp");
		return mav;
	}

	public String indexLedmeter() {
		return "redirect:/indexLedmeter";
	}

	@RequestMapping("/selectLedmeter")
	public ModelAndView selectLedmeter(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ledmeterdataTotalPage = ledmeterdataService
				.countRelativeLedmeterLedmeterdatas(id) / ledmeterdataPageSize;
		String ledmeterdataPageType = request.getParameter("ledmeterdataPage");
		switch (ledmeterdataPageType) {
		case "homePage":
			ledmeterdataPageNumber = 0;
			break;
		case "previousPage":
			if (ledmeterdataPageNumber >= 1)
				ledmeterdataPageNumber = ledmeterdataPageNumber - 1;
			else
				ledmeterdataPageNumber = 0;
			break;
		case "nextPage":
			if (ledmeterdataPageNumber < ledmeterdataTotalPage)
				ledmeterdataPageNumber = ledmeterdataPageNumber + 1;
			else
				ledmeterdataPageNumber = ledmeterdataTotalPage;
			break;
		case "lastPage":
			ledmeterdataPageNumber = ledmeterdataTotalPage;
			break;
		case "jumpPage":
			ledmeterdataPageNumber = Integer.parseInt(request
					.getParameter("ledmeterdataWantToPage")) - 1;
			break;
		case "eachPageShow":
			ledmeterdataPageNumber = 0;
			ledmeterdataPageSize = Integer.parseInt(request
					.getParameter("ledmeterdataPageSize"));
			ledmeterdataTotalPage = ledmeterdataService
					.countRelativeLedmeterLedmeterdatas(id)
					/ ledmeterdataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledmeterdataPageNumber >= 0
				&& ledmeterdataPageNumber <= ledmeterdataTotalPage)
			mav.addObject("ledmeterdatas", ledmeterdataDAO
					.findLedmeterLedmeterdatas(id, ledmeterdataPageNumber
							* ledmeterdataPageSize, ledmeterdataPageSize));
		mav.addObject("ledmeterdataPageNumber", ledmeterdataPageNumber);
		mav.addObject("ledmeterdataPage", ledmeterdataPageType);
		mav.addObject("ledmeterdataWantToPagePage",
				request.getParameter("ledmeterdataWantToPage"));
		mav.addObject("ledmeterdataPageSize", ledmeterdataPageSize);
		mav.addObject("ledmeterdataTotalPage", ledmeterdataTotalPage);
		mav.addObject("ledmeter", ledmeterDAO.findLedmeterByPrimaryKey(id));
		mav.setViewName("ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/selectLedmeterLedbuilding")
	public ModelAndView selectLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.setViewName("ledmeter/ledbuilding/viewLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/selectLedmeterLedmeterdatas")
	public ModelAndView selectLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int ledmeterdataTotalPage = ledmeterdataService
				.countRelativeLedmeterLedmeterdatas(ledmeter_id)
				/ ledmeterdataPageSize;
		String ledmeterdataPageType = request.getParameter("page");
		switch (ledmeterdataPageType) {
		case "homePage":
			ledmeterdataPageNumber = 0;
			break;
		case "previousPage":
			if (ledmeterdataPageNumber >= 1)
				ledmeterdataPageNumber = ledmeterdataPageNumber - 1;
			else
				ledmeterdataPageNumber = 0;
			break;
		case "nextPage":
			if (ledmeterdataPageNumber < ledmeterdataTotalPage)
				ledmeterdataPageNumber = ledmeterdataPageNumber + 1;
			else
				ledmeterdataPageNumber = ledmeterdataTotalPage;
			break;
		case "lastPage":
			ledmeterdataPageNumber = ledmeterdataTotalPage;
			break;
		case "jumpPage":
			ledmeterdataPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			ledmeterdataPageNumber = 0;
			ledmeterdataPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			ledmeterdataTotalPage = ledmeterdataService
					.countRelativeLedmeterLedmeterdatas(ledmeter_id)
					/ ledmeterdataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (ledmeterdataPageNumber >= 0
				&& ledmeterdataPageNumber <= ledmeterdataTotalPage)
			mav.addObject("ledmeterdatas", ledmeterdataDAO
					.findLedmeterLedmeterdatas(ledmeter_id,
							ledmeterdataPageNumber * ledmeterdataPageSize,
							ledmeterdataPageSize));
		mav.addObject("ledmeterdataPageNumber", ledmeterdataPageNumber);
		mav.addObject("ledmeterdataPageSize", pageSize);
		mav.addObject("ledmeterdataPage", ledmeterdataPageType);
		mav.addObject("ledmeterdataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("ledmeterdataTotalPage", ledmeterdataTotalPage);
		mav.addObject("ledmeter",
				ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id));
		mav.setViewName("ledmeter/ledmeterdatas/viewLedmeterdatas.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteLedmeter")
	public ModelAndView confirmDeleteLedmeter(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter", ledmeterDAO.findLedmeterByPrimaryKey(id));
		mav.setViewName("ledmeter/deleteLedmeter.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteLedmeterLedbuilding")
	public ModelAndView confirmDeleteLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer related_ledbuilding_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding", ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuilding_id));
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.setViewName("ledmeter/ledbuilding/deleteLedbuilding.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteLedmeterLedmeterdatas")
	public ModelAndView confirmDeleteLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer related_ledmeterdatas_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata", ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(related_ledmeterdatas_id));
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.setViewName("ledmeter/ledmeterdatas/deleteLedmeterdatas.jsp");
		return mav;
	}

	@RequestMapping("/deleteLedmeter")
	public String deleteLedmeter(@RequestParam Integer id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(id);
		ledmeterService.deleteLedmeter(ledmeter);
		return "forward:/indexLedmeter";
	}

	@RequestMapping("/deleteLedmeterLedbuilding")
	public ModelAndView deleteLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer Relativeledbuilding_id) {
		ModelAndView mav = new ModelAndView();
		Ledmeter ledmeter = ledmeterService.deleteLedmeterLedbuilding(
				ledmeter_id, Relativeledbuilding_id);
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeter", ledmeter);
		mav.setViewName("ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/deleteLedmeterLedmeterdatas")
	public ModelAndView deleteLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer Relativeledmeterdatas_id) {
		ModelAndView mav = new ModelAndView();
		Ledmeter ledmeter = ledmeterService.deleteLedmeterLedmeterdatas(
				ledmeter_id, Relativeledmeterdatas_id);
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeter", ledmeter);
		mav.setViewName("ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/editLedmeter")
	public ModelAndView editLedmeter(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Ledbuilding> allLedbuildings = ledbuildingDAO.findAllLedbuildings();
		mav.addObject("allLedbuildings", allLedbuildings);
		Set<Ledmeterdata> allLedmeterdatas = ledmeterdataDAO
				.findAllLedmeterdatas();
		Set<Ledmeterdata> havedLedmeterdatas = ledmeterDAO
				.findLedmeterByPrimaryKey(id).getRelativeLedmeterdatas();
		mav.addObject("allLedmeterdatas", allLedmeterdatas);
		mav.addObject("havedLedmeterdatas", havedLedmeterdatas);
		mav.addObject("allLedmeters", ledmeterDAO.findAllLedmeters());
		mav.addObject("ledmeter", ledmeterDAO.findLedmeterByPrimaryKey(id));
		mav.setViewName("ledmeter/editLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/editLedmeterLedbuilding")
	public ModelAndView editLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.setViewName("ledmeter/ledbuilding/editLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editLedmeterLedmeterdatas")
	public ModelAndView editLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer ledmeterdatas_id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(ledmeterdatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeterdata", ledmeterdata);
		mav.setViewName("ledmeter/ledmeterdatas/editLedmeterdatas.jsp");

		return mav;
	}

	@RequestMapping("/newLedmeter")
	public ModelAndView newLedmeter() {
		ModelAndView mav = new ModelAndView();

		Set<Ledbuilding> allLedbuildings = ledbuildingDAO.findAllLedbuildings();
		mav.addObject("allLedbuildings", allLedbuildings);

		Set<Ledmeterdata> allLedmeterdatas = ledmeterdataDAO
				.findAllLedmeterdatas();
		mav.addObject("allLedmeterdatas", allLedmeterdatas);
		mav.addObject("allLedmeters", ledmeterDAO.findAllLedmeters());
		mav.addObject("ledmeter", new Ledmeter());
		mav.addObject("newFlag", true);
		mav.setViewName("ledmeter/editLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/newLedmeterLedbuilding")
	public ModelAndView newLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer ledbuilding_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledbuilding", ledbuilding);
		mav.addObject("newFlag", true);
		mav.setViewName("ledmeter/ledbuilding/editLedbuilding.jsp");

		return mav;
	}

	@RequestMapping("/newLedmeterLedmeterdatas")
	public ModelAndView newLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id,
			@RequestParam Integer ledmeterdatas_id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(ledmeterdatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeterdata", ledmeterdata);
		mav.addObject("newFlag", true);
		mav.setViewName("ledmeter/ledmeterdatas/editLedmeterdatas.jsp");

		return mav;
	}

	@RequestMapping("/listLedmeterLedbuilding")
	public ModelAndView listLedmeterLedbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledbuilding",
				ledbuildingDAO.findLedbuildingByPrimaryKey(id));
		mav.setViewName("ledmeter/ledbuilding/listLedbuilding.jsp");

		return mav;
	}

	Ledbuilding ledbuilding;

	@RequestMapping("/listLedmeterLedmeterdatas")
	public ModelAndView listLedmeterLedmeterdatas(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeterdata",
				ledmeterdataDAO.findLedmeterdataByPrimaryKey(id));
		mav.setViewName("ledmeter/ledmeterdatas/listLedmeterdatas.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveLedmeter")
	public String saveLedmeter(@ModelAttribute Ledmeter ledmeter) {
		ledmeterService.saveLedmeter(ledmeter);

		return "forward:/indexLedmeter";
	}

	@RequestMapping("/saveLedmeterLedbuilding")
	public ModelAndView saveLedmeterLedbuilding(
			@RequestParam Integer ledmeter_id,
			@ModelAttribute Ledbuilding ledbuilding) {
		Ledmeter parent_ledmeter = ledmeterService.saveLedmeterLedbuilding(
				ledmeter_id, ledbuilding);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeter", parent_ledmeter);
		mav.setViewName("ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/saveLedmeterLedmeterdatas")
	public ModelAndView saveLedmeterLedmeterdatas(
			@RequestParam Integer ledmeter_id,
			@ModelAttribute Ledmeterdata ledmeterdatas) {
		Ledmeter parent_ledmeter = ledmeterService.saveLedmeterLedmeterdatas(
				ledmeter_id, ledmeterdatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("ledmeter_id", ledmeter_id);

		mav.addObject("ledmeter", parent_ledmeter);
		mav.setViewName("ledmeter/viewLedmeter.jsp");

		return mav;
	}

	@RequestMapping("/ledmeterController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}