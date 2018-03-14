package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacsensordataDAO;

import cn.edu.scau.cmi.service.CacsensordataService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;
import cn.edu.scau.cmi.service.*;
import cn.edu.scau.cmi.web.*;
import org.springframework.web.bind.annotation.*;

@Controller("CacsensordataRestController")
public class CacsensordataRestController extends CmiController {
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

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensordata(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor);
		cacsensordataService.deleteCacsensordata(cacsensordata);
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensordataCacrecordtime(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@PathVariable Integer relative_cacrecordtime_id) {
		cacsensordataService.deleteCacsensordataCacrecordtime(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor,
				relative_cacrecordtime_id);
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacsensor/{relative_cacsensor_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensordataCacsensor(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@PathVariable Integer relative_cacsensor_id) {
		cacsensordataService.deleteCacsensordataCacsensor(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor,
				relative_cacsensor_id);
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacrecordtime", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime getCacsensordataCacrecordtime(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor) {
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor)
				.getRelativeCacrecordtime();
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacsensor", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensor getCacsensordataCacsensor(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor) {
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor)
				.getRelativeCacsensor();
	}

	@RequestMapping(value = "/Cacsensordata", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacsensordata> listCacsensordatas() {
		return new java.util.ArrayList<Cacsensordata>(
				cacsensordataService.loadCacsensordatas());
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensordata loadCacsensordata(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor) {
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor);
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacrecordtime/{relative_cacrecordtime_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacrecordtime loadCacsensordataCacrecordtime(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@PathVariable Integer relative_cacrecordtime_id) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(relative_cacrecordtime_id, -1,
						-1);

		return cacrecordtime;
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacsensor/{relative_cacsensor_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensor loadCacsensordataCacsensor(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@PathVariable Integer relative_cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				relative_cacsensor_id, -1, -1);

		return cacsensor;
	}

	@RequestMapping(value = "/Cacsensordata", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensordata newCacsensordata(
			@RequestBody Cacsensordata cacsensordata) {
		cacsensordataService.saveCacsensordata(cacsensordata);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata.getCacrecordtime(), cacsensordata.getCacsensor());
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacrecordtime", method = RequestMethod.POST)
	@ResponseBody
	public Cacrecordtime newCacsensordataCacrecordtime(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacsensordataService.saveCacsensordataCacrecordtime(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacsensor", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensor newCacsensordataCacsensor(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@RequestBody Cacsensor cacsensor) {
		cacsensordataService
				.saveCacsensordataCacsensor(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, cacsensor);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor.getId());
	}

	@RequestMapping(value = "/Cacsensordata", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensordata saveCacsensordata(
			@RequestBody Cacsensordata cacsensordata) {
		cacsensordataService.saveCacsensordata(cacsensordata);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata.getCacrecordtime(), cacsensordata.getCacsensor());
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacrecordtime", method = RequestMethod.PUT)
	@ResponseBody
	public Cacrecordtime saveCacsensordataCacrecordtime(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@RequestBody Cacrecordtime cacrecordtime) {
		cacsensordataService.saveCacsensordataCacrecordtime(
				cacsensordata_cacrecordtime, cacsensordata_cacsensor,
				cacrecordtime);
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(cacrecordtime
				.getId());
	}

	@RequestMapping(value = "/Cacsensordata/{cacsensordata_cacrecordtime}/{cacsensordata_cacsensor}/cacsensor", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensor saveCacsensordataCacsensor(
			@PathVariable Integer cacsensordata_cacrecordtime,
			@PathVariable Integer cacsensordata_cacsensor,
			@RequestBody Cacsensor cacsensor) {
		cacsensordataService
				.saveCacsensordataCacsensor(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, cacsensor);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor.getId());
	}

}