package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacsensordataDAO;

import cn.edu.scau.cmi.service.CacsensordataService;

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

@Controller("CacsensordataController")
public class CacsensordataController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;
	@Autowired
	private CacsensorDAO cacsensorDAO;

	@RequestMapping("/indexCacsensordata")
	public ModelAndView listCacsensordatas(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacsensordataService.countcacsensordatas() / pageSize;
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
			totalPage = cacsensordataService.countcacsensordatas() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject(
					"cacsensordatas",
					cacsensordataService.loadCacsensordatas(pageNumber
							* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacsensordata/listCacsensordatas.jsp");
		return mav;
	}

	public String indexCacsensordata() {
		return "redirect:/indexCacsensordata";
	}

	@RequestMapping("/selectCacsensordata")
	public ModelAndView selectCacsensordata(
			@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacsensor, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor));
		mav.setViewName("cacsensordata/viewCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/selectCacsensordataCacrecordtime")
	public ModelAndView selectCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacsensordata/cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/selectCacsensordataCacsensor")
	public ModelAndView selectCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensor_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensor", cacsensor);
		mav.setViewName("cacsensordata/cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensordata")
	public ModelAndView confirmDeleteCacsensordata(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacsensor) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor));
		mav.setViewName("cacsensordata/deleteCacsensordata.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensordataCacrecordtime")
	public ModelAndView confirmDeleteCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer related_cacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id));
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.setViewName("cacsensordata/cacrecordtime/deleteCacrecordtime.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensordataCacsensor")
	public ModelAndView confirmDeleteCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer related_cacsensor_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor",
				cacsensorDAO.findCacsensorByPrimaryKey(related_cacsensor_id));
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.setViewName("cacsensordata/cacsensor/deleteCacsensor.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacsensordata")
	public String deleteCacsensordata(@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor);
		cacsensordataService.deleteCacsensordata(cacsensordata);
		return "forward:/indexCacsensordata";
	}

	@RequestMapping("/deleteCacsensordataCacrecordtime")
	public ModelAndView deleteCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer Relativecacrecordtime_id) {
		ModelAndView mav = new ModelAndView();
		Cacsensordata cacsensordata = cacsensordataService
				.deleteCacsensordataCacrecordtime(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, Relativecacrecordtime_id);
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensordata", cacsensordata);
		mav.setViewName("cacsensordata/viewCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacsensordataCacsensor")
	public ModelAndView deleteCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer Relativecacsensor_id) {
		ModelAndView mav = new ModelAndView();
		Cacsensordata cacsensordata = cacsensordataService
				.deleteCacsensordataCacsensor(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, Relativecacsensor_id);
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensordata", cacsensordata);
		mav.setViewName("cacsensordata/viewCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensordata")
	public ModelAndView editCacsensordata(@RequestParam Integer cacrecordtime,
			@RequestParam Integer cacsensor) {
		ModelAndView mav = new ModelAndView();
		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);
		Set<Cacsensor> allCacsensors = cacsensorDAO.findAllCacsensors();
		mav.addObject("allCacsensors", allCacsensors);
		mav.addObject("allCacsensordatas",
				cacsensordataDAO.findAllCacsensordatas());
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor));
		mav.setViewName("cacsensordata/editCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensordataCacrecordtime")
	public ModelAndView editCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacsensordata/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensordataCacsensor")
	public ModelAndView editCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensor_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensor", cacsensor);
		mav.setViewName("cacsensordata/cacsensor/editCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensordata")
	public ModelAndView newCacsensordata() {
		ModelAndView mav = new ModelAndView();

		Set<Cacrecordtime> allCacrecordtimes = cacrecordtimeDAO
				.findAllCacrecordtimes();
		mav.addObject("allCacrecordtimes", allCacrecordtimes);
		Set<Cacsensor> allCacsensors = cacsensorDAO.findAllCacsensors();
		mav.addObject("allCacsensors", allCacsensors);

		mav.addObject("allCacsensordatas",
				cacsensordataDAO.findAllCacsensordatas());
		mav.addObject("cacsensordata", new Cacsensordata());
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensordata/editCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensordataCacrecordtime")
	public ModelAndView newCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensordata/cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensordataCacsensor")
	public ModelAndView newCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@RequestParam Integer cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensor_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensor", cacsensor);
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensordata/cacsensor/editCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/listCacsensordataCacrecordtime")
	public ModelAndView listCacsensordataCacrecordtime(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacsensordata/cacrecordtime/listCacrecordtime.jsp");

		return mav;
	}

	Cacrecordtime cacrecordtime;

	@RequestMapping("/listCacsensordataCacsensor")
	public ModelAndView listCacsensordataCacsensor(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor", cacsensorDAO.findCacsensorByPrimaryKey(id));
		mav.setViewName("cacsensordata/cacsensor/listCacsensor.jsp");

		return mav;
	}

	Cacsensor cacsensor;

	@Scope("prototype")
	@RequestMapping("/saveCacsensordata")
	public String saveCacsensordata(@ModelAttribute Cacsensordata cacsensordata) {
		cacsensordataService.saveCacsensordata(cacsensordata);

		return "forward:/indexCacsensordata";
	}

	@RequestMapping("/saveCacsensordataCacrecordtime")
	public ModelAndView saveCacsensordataCacrecordtime(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@ModelAttribute Cacrecordtime cacrecordtime) {
		Cacsensordata parent_cacsensordata = cacsensordataService
				.saveCacsensordataCacrecordtime(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, cacrecordtime);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensordata", parent_cacsensordata);
		mav.setViewName("cacsensordata/viewCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/saveCacsensordataCacsensor")
	public ModelAndView saveCacsensordataCacsensor(
			@RequestParam Integer cacsensordata_cacrecordtime,
			@RequestParam Integer cacsensordata_cacsensor,
			@ModelAttribute Cacsensor cacsensor) {
		Cacsensordata parent_cacsensordata = cacsensordataService
				.saveCacsensordataCacsensor(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, cacsensor);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata_cacrecordtime",
				cacsensordata_cacrecordtime);
		mav.addObject("cacsensordata_cacsensor", cacsensordata_cacsensor);

		mav.addObject("cacsensordata", parent_cacsensordata);
		mav.setViewName("cacsensordata/viewCacsensordata.jsp");

		return mav;
	}

	@RequestMapping("/cacsensordataController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}