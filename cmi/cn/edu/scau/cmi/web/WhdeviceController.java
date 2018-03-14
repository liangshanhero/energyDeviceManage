package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhdeviceDAO;

import cn.edu.scau.cmi.service.WhdeviceService;

import java.util.HashSet;
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

@Controller("WhdeviceController")
public class WhdeviceController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int whdatatypePageNumber = 0;
	static int whdatatypePageSize = 10;
	static int whdevicedataPageNumber = 0;
	static int whdevicedataPageSize = 10;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdatatypeService whdatatypeService;
	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@RequestMapping("/indexWhdevice")
	public ModelAndView listWhdevices(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whdeviceService.countwhdevices() / pageSize;
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
			totalPage = whdeviceService.countwhdevices() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whdevices", whdeviceService.loadWhdevices(pageNumber
					* pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whdevice/listWhdevices.jsp");
		return mav;
	}

	public String indexWhdevice() {
		return "redirect:/indexWhdevice";
	}

	@RequestMapping("/selectWhdevice")
	public ModelAndView selectWhdevice(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Set<Whdatatype> whdatatypeSet = new HashSet<Whdatatype>();
		int whdatatypeTotalPage = whdeviceService.countWhdeviceWhdatatypes(id)
				/ whdatatypePageSize;
		String whdatatypePageType = request.getParameter("whdatatypePage");
		switch (whdatatypePageType) {
		case "homePage":
			whdatatypePageNumber = 0;
			break;
		case "previousPage":
			if (whdatatypePageNumber >= 1)
				whdatatypePageNumber = whdatatypePageNumber - 1;
			else
				whdatatypePageNumber = 0;
			break;
		case "nextPage":
			if (whdatatypePageNumber < whdatatypeTotalPage)
				whdatatypePageNumber = whdatatypePageNumber + 1;
			else
				whdatatypePageNumber = whdatatypeTotalPage;
			break;
		case "lastPage":
			whdatatypePageNumber = whdatatypeTotalPage;
			break;
		case "jumpPage":
			whdatatypePageNumber = Integer.parseInt(request
					.getParameter("whdatatypeWantToPage")) - 1;
			break;
		case "eachPageShow":
			whdatatypePageNumber = 0;
			whdatatypePageSize = Integer.parseInt(request
					.getParameter("whdatatypePageSize"));
			whdatatypeTotalPage = whdeviceService.countWhdeviceWhdatatypes(id)
					/ whdatatypePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdatatypePageNumber >= 0
				&& whdatatypePageNumber <= whdatatypeTotalPage) {
			Set<Whdatatype2whdevice> whdatatype2whdeviceSet = whdatatype2whdeviceDAO
					.findWhdeviceWhdatatype2whdevices(id, whdatatypePageNumber
							* whdatatypePageSize, whdatatypePageSize);
			for (Whdatatype2whdevice whdatatype2whdevice : whdatatype2whdeviceSet) {
				whdatatypeSet.add(whdatatypeDAO
						.findWhdatatypeByPrimaryKey(whdatatype2whdevice
								.getWhdatatype()));
			}
		}
		mav.addObject("whdatatypes", whdatatypeSet);
		mav.addObject("whdatatypePageNumber", whdatatypePageNumber);
		mav.addObject("whdatatypePage", whdatatypePageType);
		mav.addObject("whdatatypeWantToPagePage",
				request.getParameter("whdatatypeWantToPage"));
		mav.addObject("whdatatypePageSize", whdatatypePageSize);
		mav.addObject("whdatatypeTotalPage", whdatatypeTotalPage);
		int whdevicedataTotalPage = whdevicedataService
				.countRelativeWhdeviceWhdevicedatas(id) / whdevicedataPageSize;
		String whdevicedataPageType = request.getParameter("whdevicedataPage");
		switch (whdevicedataPageType) {
		case "homePage":
			whdevicedataPageNumber = 0;
			break;
		case "previousPage":
			if (whdevicedataPageNumber >= 1)
				whdevicedataPageNumber = whdevicedataPageNumber - 1;
			else
				whdevicedataPageNumber = 0;
			break;
		case "nextPage":
			if (whdevicedataPageNumber < whdevicedataTotalPage)
				whdevicedataPageNumber = whdevicedataPageNumber + 1;
			else
				whdevicedataPageNumber = whdevicedataTotalPage;
			break;
		case "lastPage":
			whdevicedataPageNumber = whdevicedataTotalPage;
			break;
		case "jumpPage":
			whdevicedataPageNumber = Integer.parseInt(request
					.getParameter("whdevicedataWantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicedataPageNumber = 0;
			whdevicedataPageSize = Integer.parseInt(request
					.getParameter("whdevicedataPageSize"));
			whdevicedataTotalPage = whdevicedataService
					.countRelativeWhdeviceWhdevicedatas(id)
					/ whdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicedataPageNumber >= 0
				&& whdevicedataPageNumber <= whdevicedataTotalPage)
			mav.addObject("whdevicedatas", whdevicedataDAO
					.findWhdeviceWhdevicedatas(id, whdevicedataPageNumber
							* whdevicedataPageSize, whdevicedataPageSize));
		mav.addObject("whdevicedataPageNumber", whdevicedataPageNumber);
		mav.addObject("whdevicedataPage", whdevicedataPageType);
		mav.addObject("whdevicedataWantToPagePage",
				request.getParameter("whdevicedataWantToPage"));
		mav.addObject("whdevicedataPageSize", whdevicedataPageSize);
		mav.addObject("whdevicedataTotalPage", whdevicedataTotalPage);
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdeviceWhbuilding")
	public ModelAndView selectWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whbuilding", whbuilding);
		mav.setViewName("whdevice/whbuilding/viewWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdeviceWhdatatypes")
	public ModelAndView selectWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Set<Whdatatype> whdatatypeSet = new HashSet<Whdatatype>();
		int whdatatypeTotalPage = whdeviceService
				.countWhdeviceWhdatatypes(whdevice_id) / whdatatypePageSize;
		String whdatatypePageType = request.getParameter("page");
		switch (whdatatypePageType) {
		case "homePage":
			whdatatypePageNumber = 0;
			break;
		case "previousPage":
			if (whdatatypePageNumber >= 1)
				whdatatypePageNumber = whdatatypePageNumber - 1;
			else
				whdatatypePageNumber = 0;
			break;
		case "nextPage":
			if (whdatatypePageNumber < whdatatypeTotalPage)
				whdatatypePageNumber = whdatatypePageNumber + 1;
			else
				whdatatypePageNumber = whdatatypeTotalPage;
			break;
		case "lastPage":
			whdatatypePageNumber = whdatatypeTotalPage;
			break;
		case "jumpPage":
			whdatatypePageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whdatatypePageNumber = 0;
			whdatatypePageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			whdatatypeTotalPage = whdeviceService
					.countWhdeviceWhdatatypes(whdevice_id) / whdatatypePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdatatypePageNumber >= 0
				&& whdatatypePageNumber <= whdatatypeTotalPage) {
			Set<Whdatatype2whdevice> whdatatype2whdeviceSet = whdatatype2whdeviceDAO
					.findWhdeviceWhdatatype2whdevices(whdevice_id,
							whdatatypePageNumber * whdatatypePageSize,
							whdatatypePageSize);
			for (Whdatatype2whdevice whdatatype2whdevice : whdatatype2whdeviceSet) {
				whdatatypeSet.add(whdatatypeDAO
						.findWhdatatypeByPrimaryKey(whdatatype2whdevice
								.getWhdatatype()));
			}
		}
		mav.addObject("whdatatypes", whdatatypeSet);
		mav.addObject("whdatatypePageNumber", whdatatypePageNumber);
		mav.addObject("whdatatypePageSize", pageSize);
		mav.addObject("whdatatypePage", whdatatypePageType);
		mav.addObject("whdatatypeWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("whdatatypeTotalPage", whdatatypeTotalPage);
		mav.addObject("whdevice",
				whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id));

		mav.setViewName("whdevice/whdatatypes/viewWhdatatypes.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdeviceWhdevicedatas")
	public ModelAndView selectWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whdevicedataTotalPage = whdevicedataService
				.countRelativeWhdeviceWhdevicedatas(whdevice_id)
				/ whdevicedataPageSize;
		String whdevicedataPageType = request.getParameter("page");
		switch (whdevicedataPageType) {
		case "homePage":
			whdevicedataPageNumber = 0;
			break;
		case "previousPage":
			if (whdevicedataPageNumber >= 1)
				whdevicedataPageNumber = whdevicedataPageNumber - 1;
			else
				whdevicedataPageNumber = 0;
			break;
		case "nextPage":
			if (whdevicedataPageNumber < whdevicedataTotalPage)
				whdevicedataPageNumber = whdevicedataPageNumber + 1;
			else
				whdevicedataPageNumber = whdevicedataTotalPage;
			break;
		case "lastPage":
			whdevicedataPageNumber = whdevicedataTotalPage;
			break;
		case "jumpPage":
			whdevicedataPageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicedataPageNumber = 0;
			whdevicedataPageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			whdevicedataTotalPage = whdevicedataService
					.countRelativeWhdeviceWhdevicedatas(whdevice_id)
					/ whdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicedataPageNumber >= 0
				&& whdevicedataPageNumber <= whdevicedataTotalPage)
			mav.addObject("whdevicedatas", whdevicedataDAO
					.findWhdeviceWhdevicedatas(whdevice_id,
							whdevicedataPageNumber * whdevicedataPageSize,
							whdevicedataPageSize));
		mav.addObject("whdevicedataPageNumber", whdevicedataPageNumber);
		mav.addObject("whdevicedataPageSize", pageSize);
		mav.addObject("whdevicedataPage", whdevicedataPageType);
		mav.addObject("whdevicedataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("whdevicedataTotalPage", whdevicedataTotalPage);
		mav.addObject("whdevice",
				whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id));
		mav.setViewName("whdevice/whdevicedatas/viewWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhdevice")
	public ModelAndView confirmDeleteWhdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whdevice/deleteWhdevice.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdeviceWhbuilding")
	public ModelAndView confirmDeleteWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer related_whbuilding_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(related_whbuilding_id));
		mav.addObject("whdevice_id", whdevice_id);

		mav.setViewName("whdevice/whbuilding/deleteWhbuilding.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdeviceWhdatatypes")
	public ModelAndView confirmDeleteWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer related_whdatatypes_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype", whdatatypeDAO
				.findWhdatatypeByPrimaryKey(related_whdatatypes_id));
		mav.addObject("whdevice_id", whdevice_id);

		mav.setViewName("whdevice/whdatatypes/deleteWhdatatypes.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdeviceWhdevicedatas")
	public ModelAndView confirmDeleteWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer related_whdevicedatas_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata", whdevicedataDAO
				.findWhdevicedataByPrimaryKey(related_whdevicedatas_id));
		mav.addObject("whdevice_id", whdevice_id);

		mav.setViewName("whdevice/whdevicedatas/deleteWhdevicedatas.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhdevice")
	public String deleteWhdevice(@RequestParam Integer id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(id);
		whdeviceService.deleteWhdevice(whdevice);
		return "forward:/indexWhdevice";
	}

	@RequestMapping("/deleteWhdeviceWhbuilding")
	public ModelAndView deleteWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer Relativewhbuilding_id) {
		ModelAndView mav = new ModelAndView();
		Whdevice whdevice = whdeviceService.deleteWhdeviceWhbuilding(
				whdevice_id, Relativewhbuilding_id);
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhdeviceWhdatatypes")
	public ModelAndView deleteWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer Relativewhdatatypes_id) {
		ModelAndView mav = new ModelAndView();
		Whdevice whdevice = whdeviceService.deleteWhdeviceWhdatatypes(
				whdevice_id, Relativewhdatatypes_id);
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhdeviceWhdevicedatas")
	public ModelAndView deleteWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer Relativewhdevicedatas_id) {
		ModelAndView mav = new ModelAndView();
		Whdevice whdevice = whdeviceService.deleteWhdeviceWhdevicedatas(
				whdevice_id, Relativewhdevicedatas_id);
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/editWhdevice")
	public ModelAndView editWhdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Whbuilding> allWhbuildings = whbuildingDAO.findAllWhbuildings();
		mav.addObject("allWhbuildings", allWhbuildings);
		Set<Whdatatype> havedWhdatatypes = whdeviceDAO
				.findWhdeviceByPrimaryKey(id).getWhdatatypes();
		Set<Whdatatype> allWhdatatypes = whdatatypeDAO.findAllWhdatatypes();
		mav.addObject("havedWhdatatypes", havedWhdatatypes);
		mav.addObject("allWhdatatypes", allWhdatatypes);
		Set<Whdevicedata> allWhdevicedatas = whdevicedataDAO
				.findAllWhdevicedatas();
		Set<Whdevicedata> havedWhdevicedatas = whdeviceDAO
				.findWhdeviceByPrimaryKey(id).getWhdevicedatas();
		mav.addObject("allWhdevicedatas", allWhdevicedatas);
		mav.addObject("havedWhdevicedatas", havedWhdevicedatas);
		mav.addObject("allWhdevices", whdeviceDAO.findAllWhdevices());
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/editWhdeviceWhbuilding")
	public ModelAndView editWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whbuilding", whbuilding);
		mav.setViewName("whdevice/whbuilding/editWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/editWhdeviceWhdatatypes")
	public ModelAndView editWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whdatatypes_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatypes_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdatatype", whdatatype);
		mav.setViewName("whdevice/whdatatypes/editWhdatatypes.jsp");

		return mav;
	}

	@RequestMapping("/editWhdeviceWhdevicedatas")
	public ModelAndView editWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whdevicedatas_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.setViewName("whdevice/whdevicedatas/editWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/newWhdevice")
	public ModelAndView newWhdevice() {
		ModelAndView mav = new ModelAndView();

		Set<Whbuilding> allWhbuildings = whbuildingDAO.findAllWhbuildings();
		mav.addObject("allWhbuildings", allWhbuildings);

		Set<Whdatatype> allWhdatatypes = whdatatypeDAO.findAllWhdatatypes();
		mav.addObject("allWhdatatypes", allWhdatatypes);
		Set<Whdevicedata> allWhdevicedatas = whdevicedataDAO
				.findAllWhdevicedatas();
		mav.addObject("allWhdevicedatas", allWhdevicedatas);
		mav.addObject("allWhdevices", whdeviceDAO.findAllWhdevices());
		mav.addObject("whdevice", new Whdevice());
		mav.addObject("newFlag", true);
		mav.setViewName("whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/newWhdeviceWhbuilding")
	public ModelAndView newWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whbuilding_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuilding_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whbuilding", whbuilding);
		mav.addObject("newFlag", true);
		mav.setViewName("whdevice/whbuilding/editWhbuilding.jsp");

		return mav;
	}

	@RequestMapping("/newWhdeviceWhdatatypes")
	public ModelAndView newWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whdatatypes_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatypes_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdatatype", whdatatype);
		mav.addObject("newFlag", true);
		mav.setViewName("whdevice/whdatatypes/editWhdatatypes.jsp");

		return mav;
	}

	@RequestMapping("/newWhdeviceWhdevicedatas")
	public ModelAndView newWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id,
			@RequestParam Integer whdevicedatas_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.addObject("newFlag", true);
		mav.setViewName("whdevice/whdevicedatas/editWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/listWhdeviceWhbuilding")
	public ModelAndView listWhdeviceWhbuilding(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whbuilding",
				whbuildingDAO.findWhbuildingByPrimaryKey(id));
		mav.setViewName("whdevice/whbuilding/listWhbuilding.jsp");

		return mav;
	}

	Whbuilding whbuilding;

	@RequestMapping("/listWhdeviceWhdatatypes")
	public ModelAndView listWhdeviceWhdatatypes(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(id));
		mav.setViewName("whdevice/whdatatypes/listWhdatatypes.jsp");

		return mav;
	}

	@RequestMapping("/listWhdeviceWhdevicedatas")
	public ModelAndView listWhdeviceWhdevicedatas(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata",
				whdevicedataDAO.findWhdevicedataByPrimaryKey(id));
		mav.setViewName("whdevice/whdevicedatas/listWhdevicedatas.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveWhdevice")
	public String saveWhdevice(@ModelAttribute Whdevice whdevice) {
		whdeviceService.saveWhdevice(whdevice);

		return "forward:/indexWhdevice";
	}

	@RequestMapping("/saveWhdeviceWhbuilding")
	public ModelAndView saveWhdeviceWhbuilding(
			@RequestParam Integer whdevice_id,
			@ModelAttribute Whbuilding whbuilding) {
		Whdevice parent_whdevice = whdeviceService.saveWhdeviceWhbuilding(
				whdevice_id, whbuilding);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", parent_whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/saveWhdeviceWhdatatypes")
	public ModelAndView saveWhdeviceWhdatatypes(
			@RequestParam Integer whdevice_id,
			@ModelAttribute Whdatatype whdatatypes) {
		Whdevice parent_whdevice = whdeviceService.saveWhdeviceWhdatatypes(
				whdevice_id, whdatatypes);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", parent_whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/saveWhdeviceWhdevicedatas")
	public ModelAndView saveWhdeviceWhdevicedatas(
			@RequestParam Integer whdevice_id,
			@ModelAttribute Whdevicedata whdevicedatas) {
		Whdevice parent_whdevice = whdeviceService.saveWhdeviceWhdevicedatas(
				whdevice_id, whdevicedatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice_id", whdevice_id);

		mav.addObject("whdevice", parent_whdevice);
		mav.setViewName("whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/whdeviceController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}