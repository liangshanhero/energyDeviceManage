package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhstrategydetailDAO;

import cn.edu.scau.cmi.service.WhstrategydetailService;

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


@Controller("WhstrategydetailController")
public class WhstrategydetailController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int editPageSize = 10;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategydetailService whstrategydetailService;

	@Autowired
	private WhstrategyDAO whstrategyDAO;
	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;

	@RequestMapping("/indexWhstrategydetail")
	public ModelAndView listWhstrategydetails(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whstrategydetailService.countwhstrategydetails() / pageSize;
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
			totalPage = whstrategydetailService.countwhstrategydetails() / pageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whstrategydetails",
					whstrategydetailService.loadWhstrategydetails(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whstrategydetail/listWhstrategydetails.jsp");
		return mav;
	}

	public String indexWhstrategydetail() {
		return "redirect:/indexWhstrategydetail";
	}

	@RequestMapping("/selectWhstrategydetail")
	public ModelAndView selectWhstrategydetail(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail", whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id));
		mav.setViewName("whstrategydetail/viewWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/selectWhstrategydetailWhstrategy")
	public ModelAndView selectWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategy_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategy", whstrategy);
		mav.setViewName("whstrategydetail/whstrategy/viewWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/selectWhstrategydetailWhstrategytype")
	public ModelAndView selectWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategytype_id) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategytype", whstrategytype);
		mav.setViewName("whstrategydetail/whstrategytype/viewWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategydetail")
	public ModelAndView confirmDeleteWhstrategydetail(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail", whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id));
		mav.setViewName("whstrategydetail/deleteWhstrategydetail.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategydetailWhstrategy")
	public ModelAndView confirmDeleteWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer related_whstrategy_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(related_whstrategy_id));
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.setViewName("whstrategydetail/whstrategy/deleteWhstrategy.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategydetailWhstrategytype")
	public ModelAndView confirmDeleteWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer related_whstrategytype_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(related_whstrategytype_id));
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.setViewName("whstrategydetail/whstrategytype/deleteWhstrategytype.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhstrategydetail")
	public String deleteWhstrategydetail(@RequestParam Integer id, HttpServletRequest request) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id);
		whstrategydetailService.deleteWhstrategydetail(whstrategydetail);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategydetail";
	}

	@RequestMapping("/deleteWhstrategydetailWhstrategy")
	public ModelAndView deleteWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer Relativewhstrategy_id) {
		ModelAndView mav = new ModelAndView();
		Whstrategydetail whstrategydetail = whstrategydetailService
				.deleteWhstrategydetailWhstrategy(whstrategydetail_id, Relativewhstrategy_id);
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.setViewName("whstrategydetail/viewWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhstrategydetailWhstrategytype")
	public ModelAndView deleteWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer Relativewhstrategytype_id) {
		ModelAndView mav = new ModelAndView();
		Whstrategydetail whstrategydetail = whstrategydetailService
				.deleteWhstrategydetailWhstrategytype(whstrategydetail_id, Relativewhstrategytype_id);
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.setViewName("whstrategydetail/viewWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategydetail")
	public ModelAndView editWhstrategydetail(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		Set<Whstrategy> allWhstrategys = whstrategyDAO.findAllWhstrategys();
		mav.addObject("allWhstrategys", allWhstrategys);
		Set<Whstrategytype> allWhstrategytypes = whstrategytypeDAO.findAllWhstrategytypes();
		mav.addObject("allWhstrategytypes", allWhstrategytypes);
		mav.addObject("allWhstrategydetails", whstrategydetailDAO.findAllWhstrategydetails());
		mav.addObject("whstrategydetail", whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id));
		mav.addObject("editType", "edit");
		mav.setViewName("whstrategydetail/editWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategydetailWhstrategy")
	public ModelAndView editWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategy_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategy", whstrategy);
		mav.setViewName("whstrategydetail/whstrategy/editWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategydetailWhstrategytype")
	public ModelAndView editWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategytype_id) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategytype", whstrategytype);
		mav.setViewName("whstrategydetail/whstrategytype/editWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategydetail")
	public ModelAndView newWhstrategydetail(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

		Set<Whstrategy> allWhstrategys = whstrategyDAO.findAllWhstrategys();
		mav.addObject("allWhstrategys", allWhstrategys);
		Set<Whstrategytype> allWhstrategytypes = whstrategytypeDAO.findAllWhstrategytypes();
		mav.addObject("allWhstrategytypes", allWhstrategytypes);

		mav.addObject("allWhstrategydetails", whstrategydetailDAO.findAllWhstrategydetails());
		mav.addObject("whstrategydetail", new Whstrategydetail());
		mav.addObject("newFlag", true);
		mav.addObject("editType", "new");
		mav.setViewName("whstrategydetail/editWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategydetailWhstrategy")
	public ModelAndView newWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategy_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategy", whstrategy);
		mav.addObject("newFlag", true);
		mav.setViewName("whstrategydetail/whstrategy/editWhstrategy.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategydetailWhstrategytype")
	public ModelAndView newWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@RequestParam Integer whstrategytype_id) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategytype", whstrategytype);
		mav.addObject("newFlag", true);
		mav.setViewName("whstrategydetail/whstrategytype/editWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/listWhstrategydetailWhstrategy")
	public ModelAndView listWhstrategydetailWhstrategy(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategy", whstrategyDAO.findWhstrategyByPrimaryKey(id));
		mav.setViewName("whstrategydetail/whstrategy/listWhstrategy.jsp");

		return mav;
	}

	Whstrategy whstrategy;

	@RequestMapping("/listWhstrategydetailWhstrategytype")
	public ModelAndView listWhstrategydetailWhstrategytype(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id));
		mav.setViewName("whstrategydetail/whstrategytype/listWhstrategytype.jsp");

		return mav;
	}

	Whstrategytype whstrategytype;

	@Scope("prototype")
	@RequestMapping("/saveWhstrategydetail")
	public String saveWhstrategydetail(@ModelAttribute Whstrategydetail whstrategydetail, HttpServletRequest request) {
		whstrategydetailService.saveWhstrategydetail(whstrategydetail);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategydetail";
	}

	@RequestMapping("/saveWhstrategydetailWhstrategy")
	public ModelAndView saveWhstrategydetailWhstrategy(@RequestParam Integer whstrategydetail_id,
			@ModelAttribute Whstrategy whstrategy) {
		Whstrategydetail parent_whstrategydetail = whstrategydetailService
				.saveWhstrategydetailWhstrategy(whstrategydetail_id, whstrategy);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategydetail", parent_whstrategydetail);
		mav.setViewName("whstrategydetail/viewWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/saveWhstrategydetailWhstrategytype")
	public ModelAndView saveWhstrategydetailWhstrategytype(@RequestParam Integer whstrategydetail_id,
			@ModelAttribute Whstrategytype whstrategytype) {
		Whstrategydetail parent_whstrategydetail = whstrategydetailService
				.saveWhstrategydetailWhstrategytype(whstrategydetail_id, whstrategytype);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail_id", whstrategydetail_id);

		mav.addObject("whstrategydetail", parent_whstrategydetail);
		mav.setViewName("whstrategydetail/viewWhstrategydetail.jsp");

		return mav;
	}

	@RequestMapping("/whstrategydetailController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}
