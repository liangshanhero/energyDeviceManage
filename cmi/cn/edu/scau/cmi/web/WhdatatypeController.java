package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhdatatypeDAO;

import cn.edu.scau.cmi.service.WhdatatypeService;

import java.util.HashSet;

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

@Controller("WhdatatypeController")
public class WhdatatypeController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int whdevicePageNumber = 0;
	static int whdevicePageSize = 10;
	static int whdevicedataPageNumber = 0;
	static int whdevicedataPageSize = 10;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	@Autowired
	private WhdatatypeService whdatatypeService;

	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@RequestMapping("/indexWhdatatype")
	public ModelAndView listWhdatatypes(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whdatatypeService.countwhdatatypes() / pageSize;
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
			totalPage = whdatatypeService.countwhdatatypes() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whdatatypes", whdatatypeService.loadWhdatatypes(
					pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whdatatype/listWhdatatypes.jsp");
		return mav;
	}

	public String indexWhdatatype() {
		return "redirect:/indexWhdatatype";
	}

	@RequestMapping("/selectWhdatatype")
	public ModelAndView selectWhdatatype(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Set<Whdevice> whdeviceSet = new HashSet<Whdevice>();
		int whdeviceTotalPage = whdatatypeService.countWhdatatypeWhdevices(id)
				/ whdevicePageSize;
		String whdevicePageType = request.getParameter("whdevicePage");
		switch (whdevicePageType) {
		case "homePage":
			whdevicePageNumber = 0;
			break;
		case "previousPage":
			if (whdevicePageNumber >= 1)
				whdevicePageNumber = whdevicePageNumber - 1;
			else
				whdevicePageNumber = 0;
			break;
		case "nextPage":
			if (whdevicePageNumber < whdeviceTotalPage)
				whdevicePageNumber = whdevicePageNumber + 1;
			else
				whdevicePageNumber = whdeviceTotalPage;
			break;
		case "lastPage":
			whdevicePageNumber = whdeviceTotalPage;
			break;
		case "jumpPage":
			whdevicePageNumber = Integer.parseInt(request
					.getParameter("whdeviceWantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicePageNumber = 0;
			whdevicePageSize = Integer.parseInt(request
					.getParameter("whdevicePageSize"));
			whdeviceTotalPage = whdatatypeService.countWhdatatypeWhdevices(id)
					/ whdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicePageNumber >= 0 && whdevicePageNumber <= whdeviceTotalPage) {
			Set<Whdatatype2whdevice> whdatatype2whdeviceSet = whdatatype2whdeviceDAO
					.findWhdatatypeWhdatatype2whdevices(id, whdevicePageNumber
							* whdevicePageSize, whdevicePageSize);
			for (Whdatatype2whdevice whdatatype2whdevice : whdatatype2whdeviceSet) {
				whdeviceSet.add(whdeviceDAO
						.findWhdeviceByPrimaryKey(whdatatype2whdevice
								.getWhdevice()));
			}
		}
		mav.addObject("whdevices", whdeviceSet);
		mav.addObject("whdevicePageNumber", whdevicePageNumber);
		mav.addObject("whdevicePage", whdevicePageType);
		mav.addObject("whdeviceWantToPagePage",
				request.getParameter("whdeviceWantToPage"));
		mav.addObject("whdevicePageSize", whdevicePageSize);
		mav.addObject("whdeviceTotalPage", whdeviceTotalPage);
		int whdevicedataTotalPage = whdevicedataService
				.countRelativeWhdatatypeWhdevicedatas(id)
				/ whdevicedataPageSize;
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
					.countRelativeWhdatatypeWhdevicedatas(id)
					/ whdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicedataPageNumber >= 0
				&& whdevicedataPageNumber <= whdevicedataTotalPage)
			mav.addObject("whdevicedatas", whdevicedataDAO
					.findWhdatatypeWhdevicedatas(id, whdevicedataPageNumber
							* whdevicedataPageSize, whdevicedataPageSize));
		mav.addObject("whdevicedataPageNumber", whdevicedataPageNumber);
		mav.addObject("whdevicedataPage", whdevicedataPageType);
		mav.addObject("whdevicedataWantToPagePage",
				request.getParameter("whdevicedataWantToPage"));
		mav.addObject("whdevicedataPageSize", whdevicedataPageSize);
		mav.addObject("whdevicedataTotalPage", whdevicedataTotalPage);
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(id));
		mav.setViewName("whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdatatypeWhdevices")
	public ModelAndView selectWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Set<Whdevice> whdeviceSet = new HashSet<Whdevice>();
		int whdeviceTotalPage = whdatatypeService
				.countWhdatatypeWhdevices(whdatatype_id) / whdevicePageSize;
		String whdevicePageType = request.getParameter("page");
		switch (whdevicePageType) {
		case "homePage":
			whdevicePageNumber = 0;
			break;
		case "previousPage":
			if (whdevicePageNumber >= 1)
				whdevicePageNumber = whdevicePageNumber - 1;
			else
				whdevicePageNumber = 0;
			break;
		case "nextPage":
			if (whdevicePageNumber < whdeviceTotalPage)
				whdevicePageNumber = whdevicePageNumber + 1;
			else
				whdevicePageNumber = whdeviceTotalPage;
			break;
		case "lastPage":
			whdevicePageNumber = whdeviceTotalPage;
			break;
		case "jumpPage":
			whdevicePageNumber = Integer.parseInt(request
					.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whdevicePageNumber = 0;
			whdevicePageSize = Integer.parseInt(request
					.getParameter("pageSize"));
			whdeviceTotalPage = whdatatypeService
					.countWhdatatypeWhdevices(whdatatype_id) / whdevicePageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicePageNumber >= 0 && whdevicePageNumber <= whdeviceTotalPage) {
			Set<Whdatatype2whdevice> whdatatype2whdeviceSet = whdatatype2whdeviceDAO
					.findWhdatatypeWhdatatype2whdevices(whdatatype_id,
							whdevicePageNumber * whdevicePageSize,
							whdevicePageSize);
			for (Whdatatype2whdevice whdatatype2whdevice : whdatatype2whdeviceSet) {
				whdeviceSet.add(whdeviceDAO
						.findWhdeviceByPrimaryKey(whdatatype2whdevice
								.getWhdevice()));
			}
		}
		mav.addObject("whdevices", whdeviceSet);
		mav.addObject("whdevicePageNumber", whdevicePageNumber);
		mav.addObject("whdevicePageSize", pageSize);
		mav.addObject("whdevicePage", whdevicePageType);
		mav.addObject("whdeviceWantToPage", request.getParameter("WantToPage"));
		mav.addObject("whdeviceTotalPage", whdeviceTotalPage);
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype_id));

		mav.setViewName("whdatatype/whdevices/viewWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdatatypeWhdevicedatas")
	public ModelAndView selectWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whdevicedataTotalPage = whdevicedataService
				.countRelativeWhdatatypeWhdevicedatas(whdatatype_id)
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
					.countRelativeWhdatatypeWhdevicedatas(whdatatype_id)
					/ whdevicedataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whdevicedataPageNumber >= 0
				&& whdevicedataPageNumber <= whdevicedataTotalPage)
			mav.addObject("whdevicedatas", whdevicedataDAO
					.findWhdatatypeWhdevicedatas(whdatatype_id,
							whdevicedataPageNumber * whdevicedataPageSize,
							whdevicedataPageSize));
		mav.addObject("whdevicedataPageNumber", whdevicedataPageNumber);
		mav.addObject("whdevicedataPageSize", pageSize);
		mav.addObject("whdevicedataPage", whdevicedataPageType);
		mav.addObject("whdevicedataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("whdevicedataTotalPage", whdevicedataTotalPage);
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(whdatatype_id));
		mav.setViewName("whdatatype/whdevicedatas/viewWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhdatatype")
	public ModelAndView confirmDeleteWhdatatype(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(id));
		mav.setViewName("whdatatype/deleteWhdatatype.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdatatypeWhdevices")
	public ModelAndView confirmDeleteWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer related_whdevices_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice",
				whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevices_id));
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.setViewName("whdatatype/whdevices/deleteWhdevices.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdatatypeWhdevicedatas")
	public ModelAndView confirmDeleteWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer related_whdevicedatas_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata", whdevicedataDAO
				.findWhdevicedataByPrimaryKey(related_whdevicedatas_id));
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.setViewName("whdatatype/whdevicedatas/deleteWhdevicedatas.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhdatatype")
	public String deleteWhdatatype(@RequestParam Integer id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(id);
		whdatatypeService.deleteWhdatatype(whdatatype);
		return "forward:/indexWhdatatype";
	}

	@RequestMapping("/deleteWhdatatypeWhdevices")
	public ModelAndView deleteWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer Relativewhdevices_id) {
		ModelAndView mav = new ModelAndView();
		Whdatatype whdatatype = whdatatypeService.deleteWhdatatypeWhdevices(
				whdatatype_id, Relativewhdevices_id);
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdatatype", whdatatype);
		mav.setViewName("whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhdatatypeWhdevicedatas")
	public ModelAndView deleteWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer Relativewhdevicedatas_id) {
		ModelAndView mav = new ModelAndView();
		Whdatatype whdatatype = whdatatypeService
				.deleteWhdatatypeWhdevicedatas(whdatatype_id,
						Relativewhdevicedatas_id);
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdatatype", whdatatype);
		mav.setViewName("whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/editWhdatatype")
	public ModelAndView editWhdatatype(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Whdevice> havedWhdevices = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(id).getRelativeWhdevices();
		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("havedWhdevices", havedWhdevices);
		mav.addObject("allWhdevices", allWhdevices);
		Set<Whdevicedata> allWhdevicedatas = whdevicedataDAO
				.findAllWhdevicedatas();
		Set<Whdevicedata> havedWhdevicedatas = whdatatypeDAO
				.findWhdatatypeByPrimaryKey(id).getRelativeWhdevicedatas();
		mav.addObject("allWhdevicedatas", allWhdevicedatas);
		mav.addObject("havedWhdevicedatas", havedWhdevicedatas);
		mav.addObject("allWhdatatypes", whdatatypeDAO.findAllWhdatatypes());
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(id));
		mav.setViewName("whdatatype/editWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/editWhdatatypeWhdevices")
	public ModelAndView editWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer whdevices_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevices_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdatatype/whdevices/editWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/editWhdatatypeWhdevicedatas")
	public ModelAndView editWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer whdevicedatas_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.setViewName("whdatatype/whdevicedatas/editWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/newWhdatatype")
	public ModelAndView newWhdatatype() {
		ModelAndView mav = new ModelAndView();

		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);
		Set<Whdevicedata> allWhdevicedatas = whdevicedataDAO
				.findAllWhdevicedatas();
		mav.addObject("allWhdevicedatas", allWhdevicedatas);
		mav.addObject("allWhdatatypes", whdatatypeDAO.findAllWhdatatypes());
		mav.addObject("whdatatype", new Whdatatype());
		mav.addObject("newFlag", true);
		mav.setViewName("whdatatype/editWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/newWhdatatypeWhdevices")
	public ModelAndView newWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer whdevices_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevices_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdevice", whdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("whdatatype/whdevices/editWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/newWhdatatypeWhdevicedatas")
	public ModelAndView newWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id,
			@RequestParam Integer whdevicedatas_id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(whdevicedatas_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.addObject("newFlag", true);
		mav.setViewName("whdatatype/whdevicedatas/editWhdevicedatas.jsp");

		return mav;
	}

	@RequestMapping("/listWhdatatypeWhdevices")
	public ModelAndView listWhdatatypeWhdevices(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whdatatype/whdevices/listWhdevices.jsp");

		return mav;
	}

	@RequestMapping("/listWhdatatypeWhdevicedatas")
	public ModelAndView listWhdatatypeWhdevicedatas(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata",
				whdevicedataDAO.findWhdevicedataByPrimaryKey(id));
		mav.setViewName("whdatatype/whdevicedatas/listWhdevicedatas.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveWhdatatype")
	public String saveWhdatatype(@ModelAttribute Whdatatype whdatatype) {
		whdatatypeService.saveWhdatatype(whdatatype);

		return "forward:/indexWhdatatype";
	}

	@RequestMapping("/saveWhdatatypeWhdevices")
	public ModelAndView saveWhdatatypeWhdevices(
			@RequestParam Integer whdatatype_id,
			@ModelAttribute Whdevice whdevices) {
		Whdatatype parent_whdatatype = whdatatypeService
				.saveWhdatatypeWhdevices(whdatatype_id, whdevices);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdatatype", parent_whdatatype);
		mav.setViewName("whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/saveWhdatatypeWhdevicedatas")
	public ModelAndView saveWhdatatypeWhdevicedatas(
			@RequestParam Integer whdatatype_id,
			@ModelAttribute Whdevicedata whdevicedatas) {
		Whdatatype parent_whdatatype = whdatatypeService
				.saveWhdatatypeWhdevicedatas(whdatatype_id, whdevicedatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype_id", whdatatype_id);

		mav.addObject("whdatatype", parent_whdatatype);
		mav.setViewName("whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/whdatatypeController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}