package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhstrategydetailDAO;

import cn.edu.scau.cmi.service.WhstrategydetailService;

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


@Controller("WhstrategydetailRestController")
public class WhstrategydetailRestController extends CmiController {
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

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategydetail(@PathVariable Integer whstrategydetail_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id);
		whstrategydetailService.deleteWhstrategydetail(whstrategydetail);
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategy/{relative_whstrategy_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategydetailWhstrategy(@PathVariable Integer whstrategydetail_id,
			@PathVariable Integer relative_whstrategy_id) {
		whstrategydetailService.deleteWhstrategydetailWhstrategy(whstrategydetail_id, relative_whstrategy_id);
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategytype/{relative_whstrategytype_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategydetailWhstrategytype(@PathVariable Integer whstrategydetail_id,
			@PathVariable Integer relative_whstrategytype_id) {
		whstrategydetailService.deleteWhstrategydetailWhstrategytype(whstrategydetail_id, relative_whstrategytype_id);
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategy", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategy getWhstrategydetailWhstrategy(@PathVariable Integer whstrategydetail_id) {
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id).getWhstrategy();
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategytype", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategytype getWhstrategydetailWhstrategytype(@PathVariable Integer whstrategydetail_id) {
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id).getWhstrategytype();
	}

	@RequestMapping(value = "/Whstrategydetail", method = RequestMethod.GET)
	@ResponseBody
	public List<Whstrategydetail> listWhstrategydetails() {
		return new java.util.ArrayList<Whstrategydetail>(whstrategydetailService.loadWhstrategydetails());
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategydetail loadWhstrategydetail(@PathVariable Integer whstrategydetail_id) {
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail_id);
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategy/{relative_whstrategy_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategy loadWhstrategydetailWhstrategy(@PathVariable Integer whstrategydetail_id,
			@PathVariable Integer relative_whstrategy_id) {
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(relative_whstrategy_id, -1, -1);

		return whstrategy;
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategytype/{relative_whstrategytype_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategytype loadWhstrategydetailWhstrategytype(@PathVariable Integer whstrategydetail_id,
			@PathVariable Integer relative_whstrategytype_id) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(relative_whstrategytype_id, -1,
				-1);

		return whstrategytype;
	}

	@RequestMapping(value = "/Whstrategydetail", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategydetail newWhstrategydetail(@RequestBody Whstrategydetail whstrategydetail) {
		whstrategydetailService.saveWhstrategydetail(whstrategydetail);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail.getId());
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategy", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategy newWhstrategydetailWhstrategy(@PathVariable Integer whstrategydetail_id,
			@RequestBody Whstrategy whstrategy) {
		whstrategydetailService.saveWhstrategydetailWhstrategy(whstrategydetail_id, whstrategy);
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy.getId());
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategytype", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategytype newWhstrategydetailWhstrategytype(@PathVariable Integer whstrategydetail_id,
			@RequestBody Whstrategytype whstrategytype) {
		whstrategydetailService.saveWhstrategydetailWhstrategytype(whstrategydetail_id, whstrategytype);
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype.getId());
	}

	@RequestMapping(value = "/Whstrategydetail", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategydetail saveWhstrategydetail(@RequestBody Whstrategydetail whstrategydetail) {
		whstrategydetailService.saveWhstrategydetail(whstrategydetail);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail.getId());
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategy", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategy saveWhstrategydetailWhstrategy(@PathVariable Integer whstrategydetail_id,
			@RequestBody Whstrategy whstrategy) {
		whstrategydetailService.saveWhstrategydetailWhstrategy(whstrategydetail_id, whstrategy);
		return whstrategyDAO.findWhstrategyByPrimaryKey(whstrategy.getId());
	}

	@RequestMapping(value = "/Whstrategydetail/{whstrategydetail_id}/whstrategytype", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategytype saveWhstrategydetailWhstrategytype(@PathVariable Integer whstrategydetail_id,
			@RequestBody Whstrategytype whstrategytype) {
		whstrategydetailService.saveWhstrategydetailWhstrategytype(whstrategydetail_id, whstrategytype);
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype.getId());
	}

}
