package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.WhdevicedataDAO;

import cn.edu.scau.cmi.service.WhdevicedataService;

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

@Controller("WhdevicedataController")
public class WhdevicedataController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	@Autowired
	private WhdevicedataService whdevicedataService;

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;
	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@RequestMapping("/indexWhdevicedata")
	public ModelAndView listWhdevicedatas(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = whdevicedataService.countwhdevicedatas() / pageSize;
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
			totalPage = whdevicedataService.countwhdevicedatas() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("whdevicedatas", whdevicedataService
					.loadWhdevicedatas(pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("whdevicedata/listWhdevicedatas.jsp");
		return mav;
	}

	public String indexWhdevicedata() {
		return "redirect:/indexWhdevicedata";
	}

	@RequestMapping("/selectWhdevicedata")
	public ModelAndView selectWhdevicedata(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata",
				whdevicedataDAO.findWhdevicedataByPrimaryKey(id));
		mav.setViewName("whdevicedata/viewWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdevicedataWhdatatype")
	public ModelAndView selectWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdatatype", whdatatype);
		mav.setViewName("whdevicedata/whdatatype/viewWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/selectWhdevicedataWhdevice")
	public ModelAndView selectWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdevicedata/whdevice/viewWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteWhdevicedata")
	public ModelAndView confirmDeleteWhdevicedata(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata",
				whdevicedataDAO.findWhdevicedataByPrimaryKey(id));
		mav.setViewName("whdevicedata/deleteWhdevicedata.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdevicedataWhdatatype")
	public ModelAndView confirmDeleteWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer related_whdatatype_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(related_whdatatype_id));
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.setViewName("whdevicedata/whdatatype/deleteWhdatatype.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteWhdevicedataWhdevice")
	public ModelAndView confirmDeleteWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer related_whdevice_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice",
				whdeviceDAO.findWhdeviceByPrimaryKey(related_whdevice_id));
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.setViewName("whdevicedata/whdevice/deleteWhdevice.jsp");
		return mav;
	}

	@RequestMapping("/deleteWhdevicedata")
	public String deleteWhdevicedata(@RequestParam Integer id) {
		Whdevicedata whdevicedata = whdevicedataDAO
				.findWhdevicedataByPrimaryKey(id);
		whdevicedataService.deleteWhdevicedata(whdevicedata);
		return "forward:/indexWhdevicedata";
	}

	@RequestMapping("/deleteWhdevicedataWhdatatype")
	public ModelAndView deleteWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer Relativewhdatatype_id) {
		ModelAndView mav = new ModelAndView();
		Whdevicedata whdevicedata = whdevicedataService
				.deleteWhdevicedataWhdatatype(whdevicedata_id,
						Relativewhdatatype_id);
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.setViewName("whdevicedata/viewWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/deleteWhdevicedataWhdevice")
	public ModelAndView deleteWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer Relativewhdevice_id) {
		ModelAndView mav = new ModelAndView();
		Whdevicedata whdevicedata = whdevicedataService
				.deleteWhdevicedataWhdevice(whdevicedata_id,
						Relativewhdevice_id);
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevicedata", whdevicedata);
		mav.setViewName("whdevicedata/viewWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/editWhdevicedata")
	public ModelAndView editWhdevicedata(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Whdatatype> allWhdatatypes = whdatatypeDAO.findAllWhdatatypes();
		mav.addObject("allWhdatatypes", allWhdatatypes);
		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);
		mav.addObject("allWhdevicedatas",
				whdevicedataDAO.findAllWhdevicedatas());
		mav.addObject("whdevicedata",
				whdevicedataDAO.findWhdevicedataByPrimaryKey(id));
		mav.setViewName("whdevicedata/editWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/editWhdevicedataWhdatatype")
	public ModelAndView editWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdatatype", whdatatype);
		mav.setViewName("whdevicedata/whdatatype/editWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/editWhdevicedataWhdevice")
	public ModelAndView editWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevice", whdevice);
		mav.setViewName("whdevicedata/whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/newWhdevicedata")
	public ModelAndView newWhdevicedata() {
		ModelAndView mav = new ModelAndView();

		Set<Whdatatype> allWhdatatypes = whdatatypeDAO.findAllWhdatatypes();
		mav.addObject("allWhdatatypes", allWhdatatypes);
		Set<Whdevice> allWhdevices = whdeviceDAO.findAllWhdevices();
		mav.addObject("allWhdevices", allWhdevices);

		mav.addObject("allWhdevicedatas",
				whdevicedataDAO.findAllWhdevicedatas());
		mav.addObject("whdevicedata", new Whdevicedata());
		mav.addObject("newFlag", true);
		mav.setViewName("whdevicedata/editWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/newWhdevicedataWhdatatype")
	public ModelAndView newWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdatatype_id) {
		Whdatatype whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(
				whdatatype_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdatatype", whdatatype);
		mav.addObject("newFlag", true);
		mav.setViewName("whdevicedata/whdatatype/editWhdatatype.jsp");

		return mav;
	}

	@RequestMapping("/newWhdevicedataWhdevice")
	public ModelAndView newWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@RequestParam Integer whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(whdevice_id,
				-1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevice", whdevice);
		mav.addObject("newFlag", true);
		mav.setViewName("whdevicedata/whdevice/editWhdevice.jsp");

		return mav;
	}

	@RequestMapping("/listWhdevicedataWhdatatype")
	public ModelAndView listWhdevicedataWhdatatype(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdatatype",
				whdatatypeDAO.findWhdatatypeByPrimaryKey(id));
		mav.setViewName("whdevicedata/whdatatype/listWhdatatype.jsp");

		return mav;
	}

	Whdatatype whdatatype;

	@RequestMapping("/listWhdevicedataWhdevice")
	public ModelAndView listWhdevicedataWhdevice(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevice", whdeviceDAO.findWhdeviceByPrimaryKey(id));
		mav.setViewName("whdevicedata/whdevice/listWhdevice.jsp");

		return mav;
	}

	Whdevice whdevice;

	@Scope("prototype")
	@RequestMapping("/saveWhdevicedata")
	public String saveWhdevicedata(@ModelAttribute Whdevicedata whdevicedata) {
		whdevicedataService.saveWhdevicedata(whdevicedata);

		return "forward:/indexWhdevicedata";
	}

	@RequestMapping("/saveWhdevicedataWhdatatype")
	public ModelAndView saveWhdevicedataWhdatatype(
			@RequestParam Integer whdevicedata_id,
			@ModelAttribute Whdatatype whdatatype) {
		Whdevicedata parent_whdevicedata = whdevicedataService
				.saveWhdevicedataWhdatatype(whdevicedata_id, whdatatype);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevicedata", parent_whdevicedata);
		mav.setViewName("whdevicedata/viewWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/saveWhdevicedataWhdevice")
	public ModelAndView saveWhdevicedataWhdevice(
			@RequestParam Integer whdevicedata_id,
			@ModelAttribute Whdevice whdevice) {
		Whdevicedata parent_whdevicedata = whdevicedataService
				.saveWhdevicedataWhdevice(whdevicedata_id, whdevice);

		ModelAndView mav = new ModelAndView();
		mav.addObject("whdevicedata_id", whdevicedata_id);

		mav.addObject("whdevicedata", parent_whdevicedata);
		mav.setViewName("whdevicedata/viewWhdevicedata.jsp");

		return mav;
	}

	@RequestMapping("/whdevicedataController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}