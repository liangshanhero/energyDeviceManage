package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhstrategyDAO;

import cn.edu.scau.cmi.service.WhstrategyService;

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


@Controller("WhstrategyRestController")
public class WhstrategyRestController extends CmiController {
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

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategy(@PathVariable Integer whstrategy_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id);
		whstrategyService.deleteWhstrategy(whstrategy);
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whdevice/{relative_whdevice_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategyWhdevice(@PathVariable Integer whstrategy_id,
			@PathVariable Integer relative_whdevice_id) {
		whstrategyService.deleteWhstrategyWhdevice(whstrategy_id, relative_whdevice_id);
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whstrategydetails/{relative_whstrategydetail_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategyWhstrategydetails(@PathVariable Integer whstrategy_id,
			@PathVariable Integer relative_whstrategydetail_id) {
		whstrategyService.deleteWhstrategyWhstrategydetails(whstrategy_id, relative_whstrategydetail_id);
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whdevice", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice getWhstrategyWhdevice(@PathVariable Integer whstrategy_id) {
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id).getWhdevice();
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whstrategydetails", method = RequestMethod.GET)
	@ResponseBody
	public List<Whstrategydetail> getWhstrategyWhstrategydetails(@PathVariable Integer whstrategy_id) {
		return new java.util.ArrayList<Whstrategydetail>(
				whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id).getWhstrategydetails());
	}

	@RequestMapping(value = "/Whstrategy", method = RequestMethod.GET)
	@ResponseBody
	public List<Whstrategy> listWhstrategys() {
		return new java.util.ArrayList<Whstrategy>(whstrategyService.loadWhstrategys());
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategy loadWhstrategy(@PathVariable Integer whstrategy_id) {
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy_id);
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whdevice/{relative_whdevice_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whdevice loadWhstrategyWhdevice(@PathVariable Integer whstrategy_id,
			@PathVariable Integer relative_whdevice_id) {
		Whdevice whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(relative_whdevice_id, -1, -1);

		return whdevice;
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whstrategydetails/{relative_whstrategydetail_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategydetail loadWhstrategyWhstrategydetails(@PathVariable Integer whstrategy_id,
			@PathVariable Integer relative_whstrategydetail_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(relative_whstrategydetail_id, -1, -1);

		return whstrategydetail;
	}

	@RequestMapping(value = "/Whstrategy", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategy newWhstrategy(@RequestBody Whstrategy whstrategy) {
		whstrategyService.saveWhstrategy(whstrategy);
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy.getId());
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whdevice", method = RequestMethod.POST)
	@ResponseBody
	public Whdevice newWhstrategyWhdevice(@PathVariable Integer whstrategy_id, @RequestBody Whdevice whdevice) {
		whstrategyService.saveWhstrategyWhdevice(whstrategy_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whstrategydetails", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategydetail newWhstrategyWhstrategydetails(@PathVariable Integer whstrategy_id,
			@RequestBody Whstrategydetail whstrategydetail) {
		whstrategyService.saveWhstrategyWhstrategydetails(whstrategy_id, whstrategydetail);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail.getId());
	}

	@RequestMapping(value = "/Whstrategy", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategy saveWhstrategy(@RequestBody Whstrategy whstrategy) {
		whstrategyService.saveWhstrategy(whstrategy);
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy.getId());
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whdevice", method = RequestMethod.PUT)
	@ResponseBody
	public Whdevice saveWhstrategyWhdevice(@PathVariable Integer whstrategy_id, @RequestBody Whdevice whdevice) {
		whstrategyService.saveWhstrategyWhdevice(whstrategy_id, whdevice);
		return whdeviceDAO.findWhdeviceByPrimaryKey(whdevice.getId());
	}

	@RequestMapping(value = "/Whstrategy/{whstrategy_id}/whstrategydetails", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategydetail saveWhstrategyWhstrategydetails(@PathVariable Integer whstrategy_id,
			@RequestBody Whstrategydetail whstrategydetails) {
		whstrategyService.saveWhstrategyWhstrategydetails(whstrategy_id, whstrategydetails);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails.getId());
	}
}
