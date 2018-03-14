package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhstrategytypeDAO;

import cn.edu.scau.cmi.service.WhstrategytypeService;

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


@Controller("WhstrategytypeController")
public class WhstrategytypeController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int editPageSize = 10;

	static int whstrategydetailPageNumber = 0;
	static int whstrategydetailPageSize = 10;

	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;

	@Autowired
	private WhstrategytypeService whstrategytypeService;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	@Autowired
	private WhstrategydetailService whstrategydetailService;

	@RequestMapping("/indexWhstrategytype")
	public ModelAndView listWhstrategytypes(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whstrategytypeService.countwhstrategytypes() / pageSize;
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
			totalPage = whstrategytypeService.countwhstrategytypes() / pageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whstrategytypes",
					whstrategytypeService.loadWhstrategytypes(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whstrategytype/listWhstrategytypes.jsp");
		return mav;
	}

	public String indexWhstrategytype() {
		return "redirect:/indexWhstrategytype";
	}

	@RequestMapping("/selectWhstrategytype")
	public ModelAndView selectWhstrategytype(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategytypeWhstrategydetails(id)
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
			whstrategydetailTotalPage = whstrategydetailService.countRelativeWhstrategytypeWhstrategydetails(id)
					/ whstrategydetailPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whstrategydetailPageNumber >= 0 && whstrategydetailPageNumber <= whstrategydetailTotalPage)
			mav.addObject("whstrategydetails", whstrategydetailDAO.findWhstrategytypeWhstrategydetails(id,
					whstrategydetailPageNumber * whstrategydetailPageSize, whstrategydetailPageSize));
		mav.addObject("whstrategydetailPageNumber", whstrategydetailPageNumber);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("whstrategydetailWantToPagePage", request.getParameter("whstrategydetailWantToPage"));
		mav.addObject("whstrategydetailPageSize", whstrategydetailPageSize);
		mav.addObject("whstrategydetailTotalPage", whstrategydetailTotalPage);
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id));
		mav.setViewName("whstrategytype/viewWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/selectWhstrategytypeWhstrategydetails")
	public ModelAndView selectWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int whstrategydetailTotalPage = whstrategydetailService
				.countRelativeWhstrategytypeWhstrategydetails(whstrategytype_id) / whstrategydetailPageSize;
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
			whstrategydetailTotalPage = whstrategydetailService
					.countRelativeWhstrategytypeWhstrategydetails(whstrategytype_id) / whstrategydetailPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (whstrategydetailPageNumber >= 0 && whstrategydetailPageNumber <= whstrategydetailTotalPage)
			mav.addObject("whstrategydetails",
					whstrategydetailDAO.findWhstrategytypeWhstrategydetails(whstrategytype_id,
							whstrategydetailPageNumber * whstrategydetailPageSize, whstrategydetailPageSize));
		mav.addObject("whstrategydetailPageNumber", whstrategydetailPageNumber);
		mav.addObject("whstrategydetailPageSize", pageSize);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("whstrategydetailWantToPage", request.getParameter("WantToPage"));
		mav.addObject("whstrategydetailTotalPage", whstrategydetailTotalPage);
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id));
		mav.setViewName("whstrategytype/whstrategydetails/viewWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategytype")
	public ModelAndView confirmDeleteWhstrategytype(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id));
		mav.setViewName("whstrategytype/deleteWhstrategytype.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhstrategytypeWhstrategydetails")
	public ModelAndView confirmDeleteWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			@RequestParam Integer related_whstrategydetails_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail",
				whstrategydetailDAO.findWhstrategydetailByPrimaryKey(related_whstrategydetails_id));
		mav.addObject("whstrategytype_id", whstrategytype_id);

		mav.setViewName("whstrategytype/whstrategydetails/deleteWhstrategydetails.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhstrategytype")
	public String deleteWhstrategytype(@RequestParam Integer id, HttpServletRequest request) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id);
		whstrategytypeService.deleteWhstrategytype(whstrategytype);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategytype";
	}

	@RequestMapping("/deleteWhstrategytypeWhstrategydetails")
	public ModelAndView deleteWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			@RequestParam Integer Relativewhstrategydetails_id) {
		ModelAndView mav = new ModelAndView();
		Whstrategytype whstrategytype = whstrategytypeService.deleteWhstrategytypeWhstrategydetails(whstrategytype_id,
				Relativewhstrategydetails_id);
		mav.addObject("whstrategytype_id", whstrategytype_id);

		mav.addObject("whstrategytype", whstrategytype);
		mav.setViewName("whstrategytype/viewWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategytype")
	public ModelAndView editWhstrategytype(@RequestParam Integer id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
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
		Set<Whstrategydetail> havedWhstrategydetails = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id)
				.getWhstrategydetails();
		mav.addObject("allWhstrategydetails", allWhstrategydetails);
		mav.addObject("havedWhstrategydetails", havedWhstrategydetails);
		mav.addObject("whstrategydetailPage", whstrategydetailPageType);
		mav.addObject("allWhstrategytypes", whstrategytypeDAO.findAllWhstrategytypes());
		mav.addObject("whstrategytype", whstrategytypeDAO.findWhstrategytypeByPrimaryKey(id));
		mav.addObject("editType", "edit");
		mav.setViewName("whstrategytype/editWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/editWhstrategytypeWhstrategydetails")
	public ModelAndView editWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			@RequestParam Integer whstrategydetails_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype_id", whstrategytype_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.setViewName("whstrategytype/whstrategydetails/editWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategytype")
	public ModelAndView newWhstrategytype(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();

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
		mav.addObject("allWhstrategytypes", whstrategytypeDAO.findAllWhstrategytypes());
		mav.addObject("whstrategytype", new Whstrategytype());
		mav.addObject("newFlag", true);
		mav.addObject("editType", "new");
		mav.setViewName("whstrategytype/editWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/newWhstrategytypeWhstrategydetails")
	public ModelAndView newWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			@RequestParam Integer whstrategydetails_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype_id", whstrategytype_id);

		mav.addObject("whstrategydetail", whstrategydetail);
		mav.addObject("newFlag", true);
		mav.setViewName("whstrategytype/whstrategydetails/editWhstrategydetails.jsp");

		return mav;
	}

	@RequestMapping("/listWhstrategytypeWhstrategydetails")
	public ModelAndView listWhstrategytypeWhstrategydetails(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategydetail", whstrategydetailDAO.findWhstrategydetailByPrimaryKey(id));
		mav.setViewName("whstrategytype/whstrategydetails/listWhstrategydetails.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveWhstrategytype")
	public String saveWhstrategytype(@ModelAttribute Whstrategytype whstrategytype, HttpServletRequest request) {
		whstrategytypeService.saveWhstrategytype(whstrategytype);
		request.getSession().setAttribute("page", "homePage");
		return "forward:/indexWhstrategytype";
	}

	@RequestMapping("/saveWhstrategytypeWhstrategydetails")
	public ModelAndView saveWhstrategytypeWhstrategydetails(@RequestParam Integer whstrategytype_id,
			@ModelAttribute Whstrategydetail whstrategydetails) {
		Whstrategytype parent_whstrategytype = whstrategytypeService
				.saveWhstrategytypeWhstrategydetails(whstrategytype_id, whstrategydetails);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whstrategytype_id", whstrategytype_id);

		mav.addObject("whstrategytype", parent_whstrategytype);
		mav.setViewName("whstrategytype/viewWhstrategytype.jsp");

		return mav;
	}

	@RequestMapping("/whstrategytypeController/binary.action")
	public ModelAndView streamBinary(@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}
