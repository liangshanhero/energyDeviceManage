package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.CacsensorDAO;

import cn.edu.scau.cmi.service.CacsensorService;

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

@Controller("CacsensorRestController")
public class CacsensorRestController extends CmiController {
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

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensor(@PathVariable Integer cacsensor_id) {
		Cacsensor cacsensor = cacsensorDAO
				.findCacsensorByPrimaryKey(cacsensor_id);
		cacsensorService.deleteCacsensor(cacsensor);
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cac/{relative_cac_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensorCac(@PathVariable Integer cacsensor_id,
			@PathVariable Integer relative_cac_id) {
		cacsensorService.deleteCacsensorCac(cacsensor_id, relative_cac_id);
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cacsensordatas/{relative_cacsensordata_cacrecordtime}/{relative_cacsensordata_cacsensor}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteCacsensorCacsensordatas(
			@PathVariable Integer cacsensor_id,
			@PathVariable Integer relative_cacsensordata_cacrecordtime,
			@PathVariable Integer relative_cacsensordata_cacsensor) {
		cacsensorService.deleteCacsensorCacsensordatas(cacsensor_id,
				relative_cacsensordata_cacrecordtime,
				relative_cacsensordata_cacsensor);
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cac", method = RequestMethod.GET)
	@ResponseBody
	public Cac getCacsensorCac(@PathVariable Integer cacsensor_id) {
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor_id)
				.getRelativeCac();
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cacsensordatas", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacsensordata> getCacsensorCacsensordatas(
			@PathVariable Integer cacsensor_id) {
		return new java.util.ArrayList<Cacsensordata>(cacsensorDAO
				.findCacsensorByPrimaryKey(cacsensor_id)
				.getRelativeCacsensordatas());
	}

	@RequestMapping(value = "/Cacsensor", method = RequestMethod.GET)
	@ResponseBody
	public List<Cacsensor> listCacsensors() {
		return new java.util.ArrayList<Cacsensor>(
				cacsensorService.loadCacsensors());
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensor loadCacsensor(@PathVariable Integer cacsensor_id) {
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor_id);
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cac/{relative_cac_id}", method = RequestMethod.GET)
	@ResponseBody
	public Cac loadCacsensorCac(@PathVariable Integer cacsensor_id,
			@PathVariable Integer relative_cac_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(relative_cac_id, -1, -1);

		return cac;
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cacsensordatas/{relative_cacsensordata_cacrecordtime}/{relative_cacsensordata_cacsensor}", method = RequestMethod.GET)
	@ResponseBody
	public Cacsensordata loadCacsensorCacsensordatas(
			@PathVariable Integer cacsensor_id,
			@PathVariable Integer relative_cacsensordata_cacrecordtime,
			@PathVariable Integer relative_cacsensordata_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						relative_cacsensordata_cacrecordtime,
						relative_cacsensordata_cacsensor, -1, -1);

		return cacsensordata;
	}

	@RequestMapping(value = "/Cacsensor", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensor newCacsensor(@RequestBody Cacsensor cacsensor) {
		cacsensorService.saveCacsensor(cacsensor);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor.getId());
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cac", method = RequestMethod.POST)
	@ResponseBody
	public Cac newCacsensorCac(@PathVariable Integer cacsensor_id,
			@RequestBody Cac cac) {
		cacsensorService.saveCacsensorCac(cacsensor_id, cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cacsensordatas", method = RequestMethod.POST)
	@ResponseBody
	public Cacsensordata newCacsensorCacsensordatas(
			@PathVariable Integer cacsensor_id,
			@RequestBody Cacsensordata cacsensordata) {
		cacsensorService.saveCacsensorCacsensordatas(cacsensor_id,
				cacsensordata);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordata.getCacrecordtime(), cacsensordata.getCacsensor());
	}

	@RequestMapping(value = "/Cacsensor", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensor saveCacsensor(@RequestBody Cacsensor cacsensor) {
		cacsensorService.saveCacsensor(cacsensor);
		return cacsensorDAO.findCacsensorByPrimaryKey(cacsensor.getId());
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cac", method = RequestMethod.PUT)
	@ResponseBody
	public Cac saveCacsensorCac(@PathVariable Integer cacsensor_id,
			@RequestBody Cac cac) {
		cacsensorService.saveCacsensorCac(cacsensor_id, cac);
		return cacDAO.findCacByPrimaryKey(cac.getId());
	}

	@RequestMapping(value = "/Cacsensor/{cacsensor_id}/cacsensordatas", method = RequestMethod.PUT)
	@ResponseBody
	public Cacsensordata saveCacsensorCacsensordatas(
			@PathVariable Integer cacsensor_id,
			@RequestBody Cacsensordata cacsensordatas) {
		cacsensorService.saveCacsensorCacsensordatas(cacsensor_id,
				cacsensordatas);
		return cacsensordataDAO.findCacsensordataByPrimaryKey(
				cacsensordatas.getCacrecordtime(),
				cacsensordatas.getCacsensor());
	}
}