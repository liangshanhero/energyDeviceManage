package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacrecordtimeDAO;

import cn.edu.scau.cmi.service.CacrecordtimeService;

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

@Controller("CacrecordtimeController")
public class CacrecordtimeController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacdevicedataPageNumber = 0;
	static int cacdevicedataPageSize = 10;
	static int cacmalfunctionPageNumber = 0;
	static int cacmalfunctionPageSize = 10;
	static int cacsensordataPageNumber = 0;
	static int cacsensordataPageSize = 10;

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@Autowired
	private CacrecordtimeService cacrecordtimeService;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@RequestMapping("/indexCacrecordtime")
	public ModelAndView listCacrecordtimes(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacrecordtimeService.countcacrecordtimes() / pageSize;
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
			totalPage = cacrecordtimeService.countcacrecordtimes() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject(
					"cacrecordtimes",
					cacrecordtimeService.loadCacrecordtimes(pageNumber
							* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacrecordtime/listCacrecordtimes.jsp");
		return mav;
	}

	public String indexCacrecordtime() {
		return "redirect:/indexCacrecordtime";
	}

	@RequestMapping("/selectCacrecordtime")
	public ModelAndView selectCacrecordtime(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdevicedataTotalPage = cacdevicedataService
				.countRelativeCacrecordtimeCacdevicedatas(id)
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
					.countRelativeCacrecordtimeCacdevicedatas(id)
					/ cacdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicedataPageNumber >= 0
				&& cacdevicedataPageNumber <= cacdevicedataTotalPage)
			mav.addObject("cacdevicedatas", cacdevicedataDAO
					.findCacrecordtimeCacdevicedatas(id,
							cacdevicedataPageNumber * cacdevicedataPageSize,
							cacdevicedataPageSize));
		mav.addObject("cacdevicedataPageNumber", cacdevicedataPageNumber);
		mav.addObject("cacdevicedataPage", cacdevicedataPageType);
		mav.addObject("cacdevicedataWantToPagePage",
				request.getParameter("cacdevicedataWantToPage"));
		mav.addObject("cacdevicedataPageSize", cacdevicedataPageSize);
		mav.addObject("cacdevicedataTotalPage", cacdevicedataTotalPage);
		int cacmalfunctionTotalPage = cacmalfunctionService
				.countRelativeCacrecordtimeCacmalfunctions(id)
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
					.countRelativeCacrecordtimeCacmalfunctions(id)
					/ cacmalfunctionPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacmalfunctionPageNumber >= 0
				&& cacmalfunctionPageNumber <= cacmalfunctionTotalPage)
			mav.addObject("cacmalfunctions", cacmalfunctionDAO
					.findCacrecordtimeCacmalfunctions(id,
							cacmalfunctionPageNumber * cacmalfunctionPageSize,
							cacmalfunctionPageSize));
		mav.addObject("cacmalfunctionPageNumber", cacmalfunctionPageNumber);
		mav.addObject("cacmalfunctionPage", cacmalfunctionPageType);
		mav.addObject("cacmalfunctionWantToPagePage",
				request.getParameter("cacmalfunctionWantToPage"));
		mav.addObject("cacmalfunctionPageSize", cacmalfunctionPageSize);
		mav.addObject("cacmalfunctionTotalPage", cacmalfunctionTotalPage);
		int cacsensordataTotalPage = cacsensordataService
				.countRelativeCacrecordtimeCacsensordatas(id)
				/ cacsensordataPageSize;
		String cacsensordataPageType = request
				.getParameter("cacsensordataPage");
		switch (cacsensordataPageType) {
		case "homePage":
			cacsensordataPageNumber = 0;
			break;
		case "previousPage":
			if (cacsensordataPageNumber >= 1)
				cacsensordataPageNumber = cacsensordataPageNumber - 1;
			else
				cacsensordataPageNumber = 0;
			break;
		case "nextPage":
			if (cacsensordataPageNumber < cacsensordataTotalPage)
				cacsensordataPageNumber = cacsensordataPageNumber + 1;
			else
				cacsensordataPageNumber = cacsensordataTotalPage;
			break;
		case "lastPage":
			cacsensordataPageNumber = cacsensordataTotalPage;
			break;
		case "jumpPage":
			cacsensordataPageNumber = Integer.parseInt(request
					.getParameter("cacsensordataWantToPage")) - 1;
			break;
		case "eachPageShow":
			cacsensordataPageNumber = 0;
			cacsensordataPageSize = Integer.parseInt(request
					.getParameter("cacsensordataPageSize"));
			cacsensordataTotalPage = cacsensordataService
					.countRelativeCacrecordtimeCacsensordatas(id)
					/ cacsensordataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensordataPageNumber >= 0
				&& cacsensordataPageNumber <= cacsensordataTotalPage)
			mav.addObject("cacsensordatas", cacsensordataDAO
					.findCacrecordtimeCacsensordatas(id,
							cacsensordataPageNumber * cacsensordataPageSize,
							cacsensordataPageSize));
		mav.addObject("cacsensordataPageNumber", cacsensordataPageNumber);
		mav.addObject("cacsensordataPage", cacsensordataPageType);
		mav.addObject("cacsensordataWantToPagePage",
				request.getParameter("cacsensordataWantToPage"));
		mav.addObject("cacsensordataPageSize", cacsensordataPageSize);
		mav.addObject("cacsensordataTotalPage", cacsensordataTotalPage);
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/selectCacrecordtimeCacdevicedatas")
	public ModelAndView selectCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacdevicedataTotalPage = cacdevicedataService
				.countRelativeCacrecordtimeCacdevicedatas(cacrecordtime_id)
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
					.countRelativeCacrecordtimeCacdevicedatas(cacrecordtime_id)
					/ cacdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacdevicedataPageNumber >= 0
				&& cacdevicedataPageNumber <= cacdevicedataTotalPage)
			mav.addObject("cacdevicedatas", cacdevicedataDAO
					.findCacrecordtimeCacdevicedatas(cacrecordtime_id,
							cacdevicedataPageNumber * cacdevicedataPageSize,
							cacdevicedataPageSize));
		mav.addObject("cacdevicedataPageNumber", cacdevicedataPageNumber);
		mav.addObject("cacdevicedataPageSize", pageSize);
		mav.addObject("cacdevicedataPage", cacdevicedataPageType);
		mav.addObject("cacdevicedataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacdevicedataTotalPage", cacdevicedataTotalPage);
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id));
		mav.setViewName("cacrecordtime/cacdevicedatas/viewCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/selectCacrecordtimeCacmalfunctions")
	public ModelAndView selectCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacmalfunctionTotalPage = cacmalfunctionService
				.countRelativeCacrecordtimeCacmalfunctions(cacrecordtime_id)
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
					.countRelativeCacrecordtimeCacmalfunctions(cacrecordtime_id)
					/ cacmalfunctionPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacmalfunctionPageNumber >= 0
				&& cacmalfunctionPageNumber <= cacmalfunctionTotalPage)
			mav.addObject("cacmalfunctions", cacmalfunctionDAO
					.findCacrecordtimeCacmalfunctions(cacrecordtime_id,
							cacmalfunctionPageNumber * cacmalfunctionPageSize,
							cacmalfunctionPageSize));
		mav.addObject("cacmalfunctionPageNumber", cacmalfunctionPageNumber);
		mav.addObject("cacmalfunctionPageSize", pageSize);
		mav.addObject("cacmalfunctionPage", cacmalfunctionPageType);
		mav.addObject("cacmalfunctionWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacmalfunctionTotalPage", cacmalfunctionTotalPage);
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id));
		mav.setViewName("cacrecordtime/cacmalfunctions/viewCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/selectCacrecordtimeCacsensordatas")
	public ModelAndView selectCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacsensordataTotalPage = cacsensordataService
				.countRelativeCacrecordtimeCacsensordatas(cacrecordtime_id)
				/ cacsensordataPageSize;
		String cacsensordataPageType = request.getParameter("page");
		switch (cacsensordataPageType) {
		case "homePage":
			cacsensordataPageNumber = 0;
			break;
		case "previousPage":
			if (cacsensordataPageNumber >= 1)
				cacsensordataPageNumber = cacsensordataPageNumber - 1;
			else
				cacsensordataPageNumber = 0;
			break;
		case "nextPage":
			if (cacsensordataPageNumber < cacsensordataTotalPage)
				cacsensordataPageNumber = cacsensordataPageNumber + 1;
			else
				cacsensordataPageNumber = cacsensordataTotalPage;
			break;
		case "lastPage":
			cacsensordataPageNumber = cacsensordataTotalPage;
			break;
		case "jumpPage":
			cacsensordataPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			cacsensordataPageNumber = 0;
			cacsensordataPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			cacsensordataTotalPage = cacsensordataService
					.countRelativeCacrecordtimeCacsensordatas(cacrecordtime_id)
					/ cacsensordataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensordataPageNumber >= 0
				&& cacsensordataPageNumber <= cacsensordataTotalPage)
			mav.addObject("cacsensordatas", cacsensordataDAO
					.findCacrecordtimeCacsensordatas(cacrecordtime_id,
							cacsensordataPageNumber * cacsensordataPageSize,
							cacsensordataPageSize));
		mav.addObject("cacsensordataPageNumber", cacsensordataPageNumber);
		mav.addObject("cacsensordataPageSize", pageSize);
		mav.addObject("cacsensordataPage", cacsensordataPageType);
		mav.addObject("cacsensordataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacsensordataTotalPage", cacsensordataTotalPage);
		mav.addObject("cacrecordtime", cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id));
		mav.setViewName("cacrecordtime/cacsensordatas/viewCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacrecordtime")
	public ModelAndView confirmDeleteCacrecordtime(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacrecordtime/deleteCacrecordtime.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacrecordtimeCacdevicedatas")
	public ModelAndView confirmDeleteCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer related_cacdevicedatas_cacdevice,
			@RequestParam Integer related_cacdevicedatas_cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						related_cacdevicedatas_cacdevice,
						related_cacdevicedatas_cacrecordtime));
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.setViewName("cacrecordtime/cacdevicedatas/deleteCacdevicedatas.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacrecordtimeCacmalfunctions")
	public ModelAndView confirmDeleteCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer related_cacmalfunctions_cacrecordtime,
			@RequestParam Integer related_cacmalfunctions_cacdevice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						related_cacmalfunctions_cacrecordtime,
						related_cacmalfunctions_cacdevice));
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.setViewName("cacrecordtime/cacmalfunctions/deleteCacmalfunctions.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacrecordtimeCacsensordatas")
	public ModelAndView confirmDeleteCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer related_cacsensordatas_cacrecordtime,
			@RequestParam Integer related_cacsensordatas_cacsensor) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						related_cacsensordatas_cacrecordtime,
						related_cacsensordatas_cacsensor));
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.setViewName("cacrecordtime/cacsensordatas/deleteCacsensordatas.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacrecordtime")
	public String deleteCacrecordtime(@RequestParam Integer id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id);
		cacrecordtimeService.deleteCacrecordtime(cacrecordtime);
		return "forward:/indexCacrecordtime";
	}

	@RequestMapping("/deleteCacrecordtimeCacdevicedatas")
	public ModelAndView deleteCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer Relativecacdevicedatas_cacdevice,
			@RequestParam Integer Relativecacdevicedatas_cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		Cacrecordtime cacrecordtime = cacrecordtimeService
				.deleteCacrecordtimeCacdevicedatas(cacrecordtime_id,
						Relativecacdevicedatas_cacdevice,
						Relativecacdevicedatas_cacrecordtime);
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacrecordtimeCacmalfunctions")
	public ModelAndView deleteCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer Relativecacmalfunctions_cacrecordtime,
			@RequestParam Integer Relativecacmalfunctions_cacdevice) {
		ModelAndView mav = new ModelAndView();
		Cacrecordtime cacrecordtime = cacrecordtimeService
				.deleteCacrecordtimeCacmalfunctions(cacrecordtime_id,
						Relativecacmalfunctions_cacrecordtime,
						Relativecacmalfunctions_cacdevice);
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacrecordtimeCacsensordatas")
	public ModelAndView deleteCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer Relativecacsensordatas_cacrecordtime,
			@RequestParam Integer Relativecacsensordatas_cacsensor) {
		ModelAndView mav = new ModelAndView();
		Cacrecordtime cacrecordtime = cacrecordtimeService
				.deleteCacrecordtimeCacsensordatas(cacrecordtime_id,
						Relativecacsensordatas_cacrecordtime,
						Relativecacsensordatas_cacsensor);
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/editCacrecordtime")
	public ModelAndView editCacrecordtime(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Cacdevicedata> allCacdevicedatas = cacdevicedataDAO
				.findAllCacdevicedatas();
		Set<Cacdevicedata> havedCacdevicedatas = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id).getRelativeCacdevicedatas();
		mav.addObject("allCacdevicedatas", allCacdevicedatas);
		mav.addObject("havedCacdevicedatas", havedCacdevicedatas);
		Set<Cacmalfunction> allCacmalfunctions = cacmalfunctionDAO
				.findAllCacmalfunctions();
		Set<Cacmalfunction> havedCacmalfunctions = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id).getRelativeCacmalfunctions();
		mav.addObject("allCacmalfunctions", allCacmalfunctions);
		mav.addObject("havedCacmalfunctions", havedCacmalfunctions);
		Set<Cacsensordata> allCacsensordatas = cacsensordataDAO
				.findAllCacsensordatas();
		Set<Cacsensordata> havedCacsensordatas = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id).getRelativeCacsensordatas();
		mav.addObject("allCacsensordatas", allCacsensordatas);
		mav.addObject("havedCacsensordatas", havedCacsensordatas);
		mav.addObject("allCacrecordtimes",
				cacrecordtimeDAO.findAllCacrecordtimes());
		mav.addObject("cacrecordtime",
				cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id));
		mav.setViewName("cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/editCacrecordtimeCacdevicedatas")
	public ModelAndView editCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacdevicedatas_cacdevice,
			@RequestParam Integer cacdevicedatas_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedatas_cacdevice,
						cacdevicedatas_cacrecordtime, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.setViewName("cacrecordtime/cacdevicedatas/editCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/editCacrecordtimeCacmalfunctions")
	public ModelAndView editCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacmalfunctions_cacrecordtime,
			@RequestParam Integer cacmalfunctions_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunctions_cacrecordtime,
						cacmalfunctions_cacdevice, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.setViewName("cacrecordtime/cacmalfunctions/editCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/editCacrecordtimeCacsensordatas")
	public ModelAndView editCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacsensordatas_cacrecordtime,
			@RequestParam Integer cacsensordatas_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordatas_cacrecordtime,
						cacsensordatas_cacsensor, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacsensordata", cacsensordata);
		mav.setViewName("cacrecordtime/cacsensordatas/editCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/newCacrecordtime")
	public ModelAndView newCacrecordtime() {
		ModelAndView mav = new ModelAndView();

		Set<Cacdevicedata> allCacdevicedatas = cacdevicedataDAO
				.findAllCacdevicedatas();
		mav.addObject("allCacdevicedatas", allCacdevicedatas);
		Set<Cacmalfunction> allCacmalfunctions = cacmalfunctionDAO
				.findAllCacmalfunctions();
		mav.addObject("allCacmalfunctions", allCacmalfunctions);
		Set<Cacsensordata> allCacsensordatas = cacsensordataDAO
				.findAllCacsensordatas();
		mav.addObject("allCacsensordatas", allCacsensordatas);
		mav.addObject("allCacrecordtimes",
				cacrecordtimeDAO.findAllCacrecordtimes());
		mav.addObject("cacrecordtime", new Cacrecordtime());
		mav.addObject("newFlag", true);
		mav.setViewName("cacrecordtime/editCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/newCacrecordtimeCacdevicedatas")
	public ModelAndView newCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacdevicedatas_cacdevice,
			@RequestParam Integer cacdevicedatas_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedatas_cacdevice,
						cacdevicedatas_cacrecordtime, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacdevicedata", cacdevicedata);
		mav.addObject("newFlag", true);
		mav.setViewName("cacrecordtime/cacdevicedatas/editCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/newCacrecordtimeCacmalfunctions")
	public ModelAndView newCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacmalfunctions_cacrecordtime,
			@RequestParam Integer cacmalfunctions_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunctions_cacrecordtime,
						cacmalfunctions_cacdevice, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacmalfunction", cacmalfunction);
		mav.addObject("newFlag", true);
		mav.setViewName("cacrecordtime/cacmalfunctions/editCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/newCacrecordtimeCacsensordatas")
	public ModelAndView newCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id,
			@RequestParam Integer cacsensordatas_cacrecordtime,
			@RequestParam Integer cacsensordatas_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordatas_cacrecordtime,
						cacsensordatas_cacsensor, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacsensordata", cacsensordata);
		mav.addObject("newFlag", true);
		mav.setViewName("cacrecordtime/cacsensordatas/editCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/listCacrecordtimeCacdevicedatas")
	public ModelAndView listCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacdevice, @RequestParam Integer cacrecordtime) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacdevicedata", cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime));
		mav.setViewName("cacrecordtime/cacdevicedatas/listCacdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/listCacrecordtimeCacmalfunctions")
	public ModelAndView listCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacdevice) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacmalfunction", cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice));
		mav.setViewName("cacrecordtime/cacmalfunctions/listCacmalfunctions.jsp");

		return mav;
	}

	@RequestMapping("/listCacrecordtimeCacsensordatas")
	public ModelAndView listCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacsensor) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor));
		mav.setViewName("cacrecordtime/cacsensordatas/listCacsensordatas.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveCacrecordtime")
	public String saveCacrecordtime(@ModelAttribute Cacrecordtime cacrecordtime) {
		cacrecordtimeService.saveCacrecordtime(cacrecordtime);

		return "forward:/indexCacrecordtime";
	}

	@RequestMapping("/saveCacrecordtimeCacdevicedatas")
	public ModelAndView saveCacrecordtimeCacdevicedatas(
			@RequestParam Integer cacrecordtime_id,
			@ModelAttribute Cacdevicedata cacdevicedatas) {
		Cacrecordtime parent_cacrecordtime = cacrecordtimeService
				.saveCacrecordtimeCacdevicedatas(cacrecordtime_id,
						cacdevicedatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", parent_cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/saveCacrecordtimeCacmalfunctions")
	public ModelAndView saveCacrecordtimeCacmalfunctions(
			@RequestParam Integer cacrecordtime_id,
			@ModelAttribute Cacmalfunction cacmalfunctions) {
		Cacrecordtime parent_cacrecordtime = cacrecordtimeService
				.saveCacrecordtimeCacmalfunctions(cacrecordtime_id,
						cacmalfunctions);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", parent_cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/saveCacrecordtimeCacsensordatas")
	public ModelAndView saveCacrecordtimeCacsensordatas(
			@RequestParam Integer cacrecordtime_id,
			@ModelAttribute Cacsensordata cacsensordatas) {
		Cacrecordtime parent_cacrecordtime = cacrecordtimeService
				.saveCacrecordtimeCacsensordatas(cacrecordtime_id,
						cacsensordatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacrecordtime_id", cacrecordtime_id);

		mav.addObject("cacrecordtime", parent_cacrecordtime);
		mav.setViewName("cacrecordtime/viewCacrecordtime.jsp");

		return mav;
	}

	@RequestMapping("/cacrecordtimeController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}