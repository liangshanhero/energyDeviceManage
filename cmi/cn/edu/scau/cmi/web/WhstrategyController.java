package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhstrategyDAO;

import cn.edu.scau.cmi.service.WhstrategyService;

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


@Controller("WhstrategyController")
public class WhstrategyController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int editPageSize = 10;

	static int whstrategydetailPageNumber = 0;
	static int whstrategydetailPageSize = 10;

	@Autowired
	private WhstrategyDAO whstrategyDAO;

	@Autowired
	private WhstrategyService whstrategyService;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategydetailService whstrategydetailService;

	@RequestMapping("/indexWhstrategy")
	public ModelAndView listWhstrategys(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whstrategyService.countwhstrategys() / pageSize;
		String pageType = null;
		String sessionAttribute = (String) request.getSession().getAttribute("page");
		String requestAttribute = request.getParameter("page");
		if (sessionAttribute != null)
			pageType = sessionAttribute;
		if (requestAttribute != null)
			pageType = requestAttribute;

		switch (pageType) {
		case "homePage":
			pageNumber = 0;
			break;
		case "previousPage":
			if (pageNumber >= 1)
				pageNumber = pageNumber - 1;
			else
				pageNumber = 0;
			break;
		case "nextPage":
			if (pageNumber < totalPage)
				pageNumber = pageNumber + 1;
			else
				pageNumber = totalPage;
			break;
		case "lastPage":
			pageNumber = totalPage;
			break;
		case "jumpPage":
			pageNumber = Integer.parseInt(request.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			pageNumber = 0;
			pageSize = Integer.parseInt(request.getParameter("pageSize"));
			totalPage = whstrategyService.countwhstrategys() / pageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whstrategys", whstrategyService.loadWhstrategys(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whstrategy/listWhstrategys.jsp");
		return mav;
	}

	public String indexWhstrategy() {
		return "redirect:/indexWhstrategy";
	}

	@RequestMapping("/selectWhstrategy")
	public ModelAndView selectWhstrategy(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategyWhstrategydetails(id)
				/ whstrategydetailPageSize;
		String whstrategydetailPageType = request.getParameter("whstrategydetailPage");
		switch (whstrategydetailPageType) {
		case "homePage":
			whstrategydetailPageNumber = 0;
			break;
		case "previousPage":
			if (whstrategydetailPageNumber >= 1)
				whstrategydetailPageNumber = whstrategydetailPageNumber - 1;
			else
				whstrategydetailPageNumber = 0;
			break;
		case "nextPage":
			if (whstrategydetailPageNumber < whstrategydetailTotalPage)
				whstrategydetailPageNumber = whstrategydetailPageNumber + 1;
			else
				whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		case "lastPage":
			whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		case "jumpPage":
			whstrategydetailPageNumber = Integer.parseInt(request.getParameter("whstrategydetailWantToPage")) - 1;
			break;
		case "eachPageShow":
			whstrategydetailPageNumber = 0;
			whstrategydetailPageSize = Integer.parseInt(request.getParameter("whstrategydetailPageSize"));
			whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategyWhstrategydetails(id)
					/ whstrategydetailPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whstrategydetailPageNumber >= 0 && whstrategydetailPageNumber <= whstrategydetailTotalPage)
			mav.addObject("whstrategydetails", whstrategydetailDAO.findWhstrategyWhstrategydetails(id,
					whstrategydetailPageNumber * whstrategydetailPageSize, whstrategydetailPageSize));
		mav.addObject("whstrategydetailPageNumber", whstrategydetailPageNumber);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("whstrategydetailWantToPagePage", request.getParameter("whstrategydetailWantToPage"));
		mav.addObject("whstrategydetailPageSize", whstrategydetailPageSize);
		mav.addObject("whstrategydetailTotalPage", whstrategydetailTotalPage);
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(id));
		mav.setViewName("whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/selectWhstrategyWhdevice")
	public ModelAndView selectWhstrategyWhdevice(@RequestParam Integer whstrategy_id,
			@RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whstrategy/whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/selectWhstrategyWhstrategydetails")
	public ModelAndView selectWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategyWhstrategydetails(whstrategy_id)
				/ whstrategydetailPageSize;
		String whstrategydetailPageType = request.getParameter("page");
		switch (whstrategydetailPageType) {
		case "homePage":
			whstrategydetailPageNumber = 0;
			break;
		case "previousPage":
			if (whstrategydetailPageNumber >= 1)
				whstrategydetailPageNumber = whstrategydetailPageNumber - 1;
			else
				whstrategydetailPageNumber = 0;
			break;
		case "nextPage":
			if (whstrategydetailPageNumber < whstrategydetailTotalPage)
				whstrategydetailPageNumber = whstrategydetailPageNumber + 1;
			else
				whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		case "lastPage":
			whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		case "jumpPage":
			whstrategydetailPageNumber = Integer.parseInt(request.getParameter("WantToPage")) - 1;
			break;
		case "eachPageShow":
			whstrategydetailPageNumber = 0;
			whstrategydetailPageSize = Integer.parseInt(request.getParameter("pageSize"));
			whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategyWhstrategydetails(whstrategy_id)
					/ whstrategydetailPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whstrategydetailPageNumber >= 0 && whstrategydetailPageNumber <= whstrategydetailTotalPage)
			mav.addObject("whstrategydetails", whstrategydetailDAO.findWhstrategyWhstrategydetails(whstrategy_id,
					whstrategydetailPageNumber * whstrategydetailPageSize, whstrategydetailPageSize));
		mav.addObject("whstrategydetailPageNumber", whstrategydetailPageNumber);
		mav.addObject("whstrategydetailPageSize", pageSize);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("whstrategydetailWantToPage", request.getParameter("WantToPage"));
		mav.addObject("whstrategydetailTotalPage", whstrategydetailTotalPage);
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id));
		mav.setViewName("whstrategy/whstrategydetails/viewWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategy")
	public ModelAndView confirmDeleteWhstrategy(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(id));
		mav.setViewName("whstrategy/deleteWhstrategy.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategyWhdevice")
	public ModelAndView confirmDeleteWhstrategyWhdevice(@RequestParam Integer whstrategy_id,
			@RequestParam Integer related_whdevice_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevice_id));
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.setViewName("whstrategy/whdevice/deleteWhdevice.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategyWhstrategydetails")
	public ModelAndView confirmDeleteWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			@RequestParam Integer related_whstrategydetails_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail",
				whstrategydetailDAO.findWhstrategydetailByPrimaryKey(related_whstrategydetails_id));
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.setViewName("whstrategy/whstrategydetails/deleteWhstrategydetails.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhstrategy")
	public String deleteWhstrategy(@RequestParam Integer id, HttpServletRequest request) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(id);
		whstrategyService.deleteWhstrategy(whstrategy);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategy";
	}

	@RequestMapping("/deleteWhstrategyWhdevice")
	public ModelAndView deleteWhstrategyWhdevice(@RequestParam Integer whstrategy_id,
			@RequestParam Integer Relativewhdevice_id) {
		ModelAndView mav = new ModelAndView();
		Whstrategy whstrategy = whstrategyService.deleteWhstrategyWhdevice(whstrategy_id, Relativewhdevice_id);
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategy", whstrategy);
		mav.setViewName("whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhstrategyWhstrategydetails")
	public ModelAndView deleteWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			@RequestParam Integer Relativewhstrategydetails_id) {
		ModelAndView mav = new ModelAndView();
		Whstrategy whstrategy = whstrategyService.deleteWhstrategyWhstrategydetails(whstrategy_id,
				Relativewhstrategydetails_id);
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategy", whstrategy);
		mav.setViewName("whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategy")
	public ModelAndView editWhstrategy(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);
		int whstrategydetailTotalPage = whstrategydetailService.countwhstrategydetails() / editPageSize;
		String whstrategydetailPageType = request.getParameter("whstrategydetailPage");
		switch (whstrategydetailPageType) {
		case "previousPage":
			if (whstrategydetailPageNumber >= 1)
				whstrategydetailPageNumber = whstrategydetailPageNumber - 1;
			else
				whstrategydetailPageNumber = 0;
			break;
		case "nextPage":
			if (whstrategydetailPageNumber < whstrategydetailTotalPage)
				whstrategydetailPageNumber = whstrategydetailPageNumber + 1;
			else
				whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		Set<Whstrategydetail> allWhstrategydetails = whstrategydetailDAO
				.findAllWhstrategydetails(whstrategydetailPageNumber * editPageSize, editPageSize);
		Set<Whstrategydetail> havedWhstrategydetails = whstrategyDAO.findWhstrategyByPrimaryKey(id)
				.getWhstrategydetails();
		mav.addObject("allWhstrategydetails", allWhstrategydetails);
		mav.addObject("havedWhstrategydetails", havedWhstrategydetails);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("allWhstrategys", whstrategyDAO.findAllWhstrategys());
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(id));
		mav.addObject("editType", "edit");
		mav.setViewName("whstrategy/editWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategyWhdevice")
	public ModelAndView editWhstrategyWhdevice(@RequestParam Integer whstrategy_id, @RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whstrategy/whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategyWhstrategydetails")
	public ModelAndView editWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			@RequestParam Integer whstrategydetails_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.setViewName("whstrategy/whstrategydetails/editWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategy")
	public ModelAndView newWhstrategy(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);

		int whstrategydetailTotalPage = whstrategydetailService.countwhstrategydetails() / editPageSize;
		String whstrategydetailPageType = request.getParameter("whstrategydetailPage");
		switch (whstrategydetailPageType) {
		case "previousPage":
			if (whstrategydetailPageNumber >= 1)
				whstrategydetailPageNumber = whstrategydetailPageNumber - 1;
			else
				whstrategydetailPageNumber = 0;
			break;
		case "nextPage":
			if (whstrategydetailPageNumber < whstrategydetailTotalPage)
				whstrategydetailPageNumber = whstrategydetailPageNumber + 1;
			else
				whstrategydetailPageNumber = whstrategydetailTotalPage;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		Set<Whstrategydetail> allWhstrategydetails = whstrategydetailDAO
				.findAllWhstrategydetails(whstrategydetailPageNumber * editPageSize, editPageSize);
		mav.addObject("allWhstrategydetails", allWhstrategydetails);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("allWhstrategys", whstrategyDAO.findAllWhstrategys());
		mav.addObject("whstrategy", new Whstrategy());
		mav.addObject("newFlag", true);
		mav.addObject("editType", "new");
		mav.setViewName("whstrategy/editWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategyWhdevice")
	public ModelAndView newWhstrategyWhdevice(@RequestParam Integer whstrategy_id, @RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whdevice", whdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("whstrategy/whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategyWhstrategydetails")
	public ModelAndView newWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			@RequestParam Integer whstrategydetails_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.addObject("newFlag", true);
		mav.setViewName("whstrategy/whstrategydetails/editWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/listWhstrategyWhdevice")
	public ModelAndView listWhstrategyWhdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whstrategy/whdevice/listWhdevice.jsp");

		return mav;
	}

	Whdevice whdevice;

	@RequestMapping("/listWhstrategyWhstrategydetails")
	public ModelAndView listWhstrategyWhstrategydetails(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail", whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id));
		mav.setViewName("whstrategy/whstrategydetails/listWhstrategydetails.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveWhstrategy")
	public String saveWhstrategy(@ModelAttribute Whstrategy whstrategy, HttpServletRequest request) {
		whstrategyService.saveWhstrategy(whstrategy);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategy";
	}

	@RequestMapping("/saveWhstrategyWhdevice")
	public ModelAndView saveWhstrategyWhdevice(@RequestParam Integer whstrategy_id, @ModelAttribute Whdevice whdevice) {
		Whstrategy parent_whstrategy = whstrategyService.saveWhstrategyWhdevice(whstrategy_id, whdevice);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategy", parent_whstrategy);
		mav.setViewName("whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/saveWhstrategyWhstrategydetails")
	public ModelAndView saveWhstrategyWhstrategydetails(@RequestParam Integer whstrategy_id,
			@ModelAttribute Whstrategydetail whstrategydetails) {
		Whstrategy parent_whstrategy = whstrategyService.saveWhstrategyWhstrategydetails(whstrategy_id,
				whstrategydetails);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy_id", whstrategy_id);

		mav.addObject("whstrategy", parent_whstrategy);
		mav.setViewName("whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/whstrategyController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}
