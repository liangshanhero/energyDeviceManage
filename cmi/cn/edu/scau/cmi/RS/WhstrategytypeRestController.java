package cn.edu.scau.cmi.RS;

import cn.edu.scau.cmi.dao.WhstrategytypeDAO;

import cn.edu.scau.cmi.service.WhstrategytypeService;

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


@Controller("WhstrategytypeRestController")
public class WhstrategytypeRestController extends CmiController {
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

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategytype(@PathVariable Integer whstrategytype_id) {
		Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id);
		whstrategytypeService.deleteWhstrategytype(whstrategytype);
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}/whstrategydetails/{relative_whstrategydetail_id}", method = RequestMethod.DELETE)
	@ResponseBody
	public void deleteWhstrategytypeWhstrategydetails(@PathVariable Integer whstrategytype_id,
			@PathVariable Integer relative_whstrategydetail_id) {
		whstrategytypeService.deleteWhstrategytypeWhstrategydetails(whstrategytype_id, relative_whstrategydetail_id);
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}/whstrategydetails", method = RequestMethod.GET)
	@ResponseBody
	public List<Whstrategydetail> getWhstrategytypeWhstrategydetails(@PathVariable Integer whstrategytype_id) {
		return new java.util.ArrayList<Whstrategydetail>(
				whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id).getWhstrategydetails());
	}

	@RequestMapping(value = "/Whstrategytype", method = RequestMethod.GET)
	@ResponseBody
	public List<Whstrategytype> listWhstrategytypes() {
		return new java.util.ArrayList<Whstrategytype>(whstrategytypeService.loadWhstrategytypes());
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategytype loadWhstrategytype(@PathVariable Integer whstrategytype_id) {
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype_id);
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}/whstrategydetails/{relative_whstrategydetail_id}", method = RequestMethod.GET)
	@ResponseBody
	public Whstrategydetail loadWhstrategytypeWhstrategydetails(@PathVariable Integer whstrategytype_id,
			@PathVariable Integer relative_whstrategydetail_id) {
		Whstrategydetail whstrategydetail = whstrategydetailDAO
				.findWhstrategydetailByPrimaryKey(relative_whstrategydetail_id, -1, -1);

		return whstrategydetail;
	}

	@RequestMapping(value = "/Whstrategytype", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategytype newWhstrategytype(@RequestBody Whstrategytype whstrategytype) {
		whstrategytypeService.saveWhstrategytype(whstrategytype);
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype.getId());
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}/whstrategydetails", method = RequestMethod.POST)
	@ResponseBody
	public Whstrategydetail newWhstrategytypeWhstrategydetails(@PathVariable Integer whstrategytype_id,
			@RequestBody Whstrategydetail whstrategydetail) {
		whstrategytypeService.saveWhstrategytypeWhstrategydetails(whstrategytype_id, whstrategydetail);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetail.getId());
	}

	@RequestMapping(value = "/Whstrategytype", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategytype saveWhstrategytype(@RequestBody Whstrategytype whstrategytype) {
		whstrategytypeService.saveWhstrategytype(whstrategytype);
		return whstrategytypeDAO.findWhstrategytypeByPrimaryKey(whstrategytype.getId());
	}

	@RequestMapping(value = "/Whstrategytype/{whstrategytype_id}/whstrategydetails", method = RequestMethod.PUT)
	@ResponseBody
	public Whstrategydetail saveWhstrategytypeWhstrategydetails(@PathVariable Integer whstrategytype_id,
			@RequestBody Whstrategydetail whstrategydetails) {
		whstrategytypeService.saveWhstrategytypeWhstrategydetails(whstrategytype_id, whstrategydetails);
		return whstrategydetailDAO.findWhstrategydetailByPrimaryKey(whstrategydetails.getId());
	}
}
