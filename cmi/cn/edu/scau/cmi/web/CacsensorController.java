package cn.edu.scau.cmi.web;

import cn.edu.scau.cmi.dao.CacsensorDAO;

import cn.edu.scau.cmi.service.CacsensorService;

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

@Controller("CacsensorController")
public class CacsensorController extends CmiController {
	static int pageNumber = 0;
	static int pageSize = 10;
	static int cacsensordataPageNumber = 0;
	static int cacsensordataPageSize = 10;

	@Autowired
	private CacsensorDAO cacsensorDAO;

	@Autowired
	private CacsensorService cacsensorService;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@RequestMapping("/indexCacsensor")
	public ModelAndView listCacsensors(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int totalPage = cacsensorService.countcacsensors() / pageSize;
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
			totalPage = cacsensorService.countcacsensors() / pageSize;
		}

		if (pageNumber >= 0 && pageNumber <= totalPage)
			mav.addObject("cacsensors", cacsensorService.loadCacsensors(
					pageNumber * pageSize, pageSize));

		mav.addObject("totalPage", totalPage);
		mav.addObject("prePageSize", pageSize);
		mav.addObject("prePage", pageNumber);
		mav.setViewName("cacsensor/listCacsensors.jsp");
		return mav;
	}

	public String indexCacsensor() {
		return "redirect:/indexCacsensor";
	}

	@RequestMapping("/selectCacsensor")
	public ModelAndView selectCacsensor(@RequestParam Integer id,
			HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacsensordataTotalPage = cacsensordataService
				.countRelativeCacsensorCacsensordatas(id)
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
					.countRelativeCacsensorCacsensordatas(id)
					/ cacsensordataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensordataPageNumber >= 0
				&& cacsensordataPageNumber <= cacsensordataTotalPage)
			mav.addObject("cacsensordatas", cacsensordataDAO
					.findCacsensorCacsensordatas(id, cacsensordataPageNumber
							* cacsensordataPageSize, cacsensordataPageSize));
		mav.addObject("cacsensordataPageNumber", cacsensordataPageNumber);
		mav.addObject("cacsensordataPage", cacsensordataPageType);
		mav.addObject("cacsensordataWantToPagePage",
				request.getParameter("cacsensordataWantToPage"));
		mav.addObject("cacsensordataPageSize", cacsensordataPageSize);
		mav.addObject("cacsensordataTotalPage", cacsensordataTotalPage);
		mav.addObject("cacsensor", cacsensorDAO.findCacsensorByPrimaryKey(id));
		mav.setViewName("cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/selectCacsensorCac")
	public ModelAndView selectCacsensorCac(@RequestParam Integer cacsensor_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cac", cac);
		mav.setViewName("cacsensor/cac/viewCac.jsp");

		return mav;
	}

	@RequestMapping("/selectCacsensorCacsensordatas")
	public ModelAndView selectCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int cacsensordataTotalPage = cacsensordataService
				.countRelativeCacsensorCacsensordatas(cacsensor_id)
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
					.countRelativeCacsensorCacsensordatas(cacsensor_id)
					/ cacsensordataPageSize;
			break;
		default:
			System.out.println("error input!");
			break;
		}
		if (cacsensordataPageNumber >= 0
				&& cacsensordataPageNumber <= cacsensordataTotalPage)
			mav.addObject("cacsensordatas", cacsensordataDAO
					.findCacsensorCacsensordatas(cacsensor_id,
							cacsensordataPageNumber * cacsensordataPageSize,
							cacsensordataPageSize));
		mav.addObject("cacsensordataPageNumber", cacsensordataPageNumber);
		mav.addObject("cacsensordataPageSize", pageSize);
		mav.addObject("cacsensordataPage", cacsensordataPageType);
		mav.addObject("cacsensordataWantToPage",
				request.getParameter("WantToPage"));
		mav.addObject("cacsensordataTotalPage", cacsensordataTotalPage);
		mav.addObject("cacsensor",
				cacsensorDAO.findCacsensorByPrimaryKey(cacsensor_id));
		mav.setViewName("cacsensor/cacsensordatas/viewCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensor")
	public ModelAndView confirmDeleteCacsensor(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor", cacsensorDAO.findCacsensorByPrimaryKey(id));
		mav.setViewName("cacsensor/deleteCacsensor.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensorCac")
	public ModelAndView confirmDeleteCacsensorCac(
			@RequestParam Integer cacsensor_id,
			@RequestParam Integer related_cac_id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(related_cac_id));
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.setViewName("cacsensor/cac/deleteCac.jsp");
		return mav;
	}

	@RequestMapping("/confirmDeleteCacsensorCacsensordatas")
	public ModelAndView confirmDeleteCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id,
			@RequestParam Integer related_cacsensordatas_cacrecordtime,
			@RequestParam Integer related_cacsensordatas_cacsensor) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						related_cacsensordatas_cacrecordtime,
						related_cacsensordatas_cacsensor));
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.setViewName("cacsensor/cacsensordatas/deleteCacsensordatas.jsp");
		return mav;
	}

	@RequestMapping("/deleteCacsensor")
	public String deleteCacsensor(@RequestParam Integer id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(id);
		cacsensorService.deleteCacsensor(cacsensor);
		return "forward:/indexCacsensor";
	}

	@RequestMapping("/deleteCacsensorCac")
	public ModelAndView deleteCacsensorCac(@RequestParam Integer cacsensor_id,
			@RequestParam Integer Relativecac_id) {
		ModelAndView mav = new ModelAndView();
		Cacsensor cacsensor = cacsensorService.deleteCacsensorCac(cacsensor_id,
				Relativecac_id);
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensor", cacsensor);
		mav.setViewName("cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/deleteCacsensorCacsensordatas")
	public ModelAndView deleteCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id,
			@RequestParam Integer Relativecacsensordatas_cacrecordtime,
			@RequestParam Integer Relativecacsensordatas_cacsensor) {
		ModelAndView mav = new ModelAndView();
		Cacsensor cacsensor = cacsensorService.deleteCacsensorCacsensordatas(
				cacsensor_id, Relativecacsensordatas_cacrecordtime,
				Relativecacsensordatas_cacsensor);
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensor", cacsensor);
		mav.setViewName("cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensor")
	public ModelAndView editCacsensor(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		Set<Cac> allCacs = cacDAO.findAllCacs();
		mav.addObject("allCacs", allCacs);
		Set<Cacsensordata> allCacsensordatas = cacsensordataDAO
				.findAllCacsensordatas();
		Set<Cacsensordata> havedCacsensordatas = cacsensorDAO
				.findCacsensorByPrimaryKey(id).getRelativeCacsensordatas();
		mav.addObject("allCacsensordatas", allCacsensordatas);
		mav.addObject("havedCacsensordatas", havedCacsensordatas);
		mav.addObject("allCacsensors", cacsensorDAO.findAllCacsensors());
		mav.addObject("cacsensor", cacsensorDAO.findCacsensorByPrimaryKey(id));
		mav.setViewName("cacsensor/editCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensorCac")
	public ModelAndView editCacsensorCac(@RequestParam Integer cacsensor_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cac", cac);
		mav.setViewName("cacsensor/cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/editCacsensorCacsensordatas")
	public ModelAndView editCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id,
			@RequestParam Integer cacsensordatas_cacrecordtime,
			@RequestParam Integer cacsensordatas_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordatas_cacrecordtime,
						cacsensordatas_cacsensor, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensordata", cacsensordata);
		mav.setViewName("cacsensor/cacsensordatas/editCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensor")
	public ModelAndView newCacsensor() {
		ModelAndView mav = new ModelAndView();

		Set<Cac> allCacs = cacDAO.findAllCacs();
		mav.addObject("allCacs", allCacs);

		Set<Cacsensordata> allCacsensordatas = cacsensordataDAO
				.findAllCacsensordatas();
		mav.addObject("allCacsensordatas", allCacsensordatas);
		mav.addObject("allCacsensors", cacsensorDAO.findAllCacsensors());
		mav.addObject("cacsensor", new Cacsensor());
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensor/editCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensorCac")
	public ModelAndView newCacsensorCac(@RequestParam Integer cacsensor_id,
			@RequestParam Integer cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cac", cac);
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensor/cac/editCac.jsp");

		return mav;
	}

	@RequestMapping("/newCacsensorCacsensordatas")
	public ModelAndView newCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id,
			@RequestParam Integer cacsensordatas_cacrecordtime,
			@RequestParam Integer cacsensordatas_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordatas_cacrecordtime,
						cacsensordatas_cacsensor, -1, -1);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensordata", cacsensordata);
		mav.addObject("newFlag", true);
		mav.setViewName("cacsensor/cacsensordatas/editCacsensordatas.jsp");

		return mav;
	}

	@RequestMapping("/listCacsensorCac")
	public ModelAndView listCacsensorCac(@RequestParam Integer id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cac", cacDAO.findCacByPrimaryKey(id));
		mav.setViewName("cacsensor/cac/listCac.jsp");

		return mav;
	}

	Cac cac;

	@RequestMapping("/listCacsensorCacsensordatas")
	public ModelAndView listCacsensorCacsensordatas(
			@RequestParam Integer cacrecordtime, @RequestParam Integer cacsensor) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensordata", cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor));
		mav.setViewName("cacsensor/cacsensordatas/listCacsensordatas.jsp");

		return mav;
	}

	@Scope("prototype")
	@RequestMapping("/saveCacsensor")
	public String saveCacsensor(@ModelAttribute Cacsensor cacsensor) {
		cacsensorService.saveCacsensor(cacsensor);

		return "forward:/indexCacsensor";
	}

	@RequestMapping("/saveCacsensorCac")
	public ModelAndView saveCacsensorCac(@RequestParam Integer cacsensor_id,
			@ModelAttribute Cac cac) {
		Cacsensor parent_cacsensor = cacsensorService.saveCacsensorCac(
				cacsensor_id, cac);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensor", parent_cacsensor);
		mav.setViewName("cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/saveCacsensorCacsensordatas")
	public ModelAndView saveCacsensorCacsensordatas(
			@RequestParam Integer cacsensor_id,
			@ModelAttribute Cacsensordata cacsensordatas) {
		Cacsensor parent_cacsensor = cacsensorService
				.saveCacsensorCacsensordatas(cacsensor_id, cacsensordatas);

		ModelAndView mav = new ModelAndView();
		mav.addObject("cacsensor_id", cacsensor_id);

		mav.addObject("cacsensor", parent_cacsensor);
		mav.setViewName("cacsensor/viewCacsensor.jsp");

		return mav;
	}

	@RequestMapping("/cacsensorController/binary.action")
	public ModelAndView streamBinary(
			@ModelAttribute HttpServletRequest request,
			@ModelAttribute HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("streamedBinaryContentView");
		return mav;

	}

}