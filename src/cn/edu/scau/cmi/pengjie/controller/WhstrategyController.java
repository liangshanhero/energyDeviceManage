package cn.edu.scau.cmi.pengjie.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.scau.cmi.dao.CompanyDAO;
import cn.edu.scau.cmi.dao.ProjectDAO;
import cn.edu.scau.cmi.dao.WhbuildingDAO;
import cn.edu.scau.cmi.dao.WhdeviceDAO;
import cn.edu.scau.cmi.dao.WhstrategyDAO;
import cn.edu.scau.cmi.dao.WhstrategyDAOImpl;
import cn.edu.scau.cmi.dao.WhstrategydetailDAO;
import cn.edu.scau.cmi.dao.WhstrategytypeDAO;
import cn.edu.scau.cmi.domain.Company;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.domain.Whstrategy;
import cn.edu.scau.cmi.domain.Whstrategydetail;
import cn.edu.scau.cmi.domain.Whstrategytype;
import cn.edu.scau.cmi.hombio.domain.Strategy;
import cn.edu.scau.cmi.hombio.domain.StrategyDetail;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 
 * @author _TESLA_
 *
 */
@Controller
@RequestMapping("/whstrategy")
public class WhstrategyController {

	private static final Log log = LogFactory.getLog(WhstrategyController.class);

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@RequestMapping
	public String index() {
		log.info("/whstrategy");
		return "whstrategy/strategies.jsp";
	}

	@RequestMapping(value = "/companies", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String companies() {
		log.info("/whstrategy/companies");

		Set<Company> companies = companyDAO.findAllCompanys();
		JSONArray json = new JSONArray();

		for (Company company : companies) {
			JSONObject obj = new JSONObject();
			obj.put("id", company.getId());
			obj.put("name", company.getName());
			json.add(obj);
		}

		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/projects", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String projects(@PathVariable Integer companyId) {
		log.info(String.format("/{companyId: %d}/projects", companyId));

		Company company = companyDAO.findCompanyById(companyId);
		if (company == null) {
			return "{}";
		}
		Set<Project> projects = company.getRelativeProjects();
		JSONArray json = new JSONArray();

		for (Project project : projects) {
			JSONObject obj = new JSONObject();
			obj.put("id", project.getId());
			obj.put("name", project.getName());
			json.add(obj);
		}

		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/buildings", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String buildings(@PathVariable Integer companyId, @PathVariable Integer projectId) {
		log.info(String.format("/{companyId: %d}/{projectId: %d}/buildings", projectId, companyId));

		Project project = projectDAO.findProjectById(projectId);
		if (project == null) {
			return "{}";
		}
		Set<Whbuilding> buildings = project.getRelativeWhbuildings();
		JSONArray json = new JSONArray();

		for (Whbuilding building : buildings) {
			JSONObject obj = new JSONObject();
			obj.put("id", building.getId());
			obj.put("name", building.getName());
			json.add(obj);
		}

		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/devices", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String devices(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId) {
		log.info(String.format("/{companyId: %d}/{projectId: %d}/{buildingId: %d}/devices", companyId, projectId,
				buildingId));

		Whbuilding building = whbuildingDAO.findWhbuildingById(buildingId);
		if (building == null) {
			return "{}";
		}
		Set<Whdevice> devices = building.getRelativeWhdevices();
		JSONArray json = new JSONArray();

		for (Whdevice device : devices) {
			JSONObject obj = new JSONObject();
			obj.put("id", device.getId());
			obj.put("number", device.getNumber());

			json.add(obj);
		}

		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String deviceDetail(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId, @PathVariable Integer deviceId) {
		log.info(String.format("GET: /{companyId: %d}/{projectId: %d}/{buildingId: %d}/{deviceId: %d}", companyId,
				projectId, buildingId, deviceId));

		Whdevice device = whdeviceDAO.findWhdeviceById(deviceId);
		if (device == null) {
			return "{}";
		}
		Set<Whstrategy> strategies = device.getWhstrategys();

		JSONObject json = new JSONObject();
		JSONObject jsonDevice = new JSONObject();
		jsonDevice.put("id", device.getId());
		jsonDevice.put("number", device.getNumber());
		json.put("device", jsonDevice);

		JSONArray jsonStrategies = new JSONArray();
		for (Whstrategy strategy : strategies) {
			JSONObject jsonStrategy = new JSONObject();
			jsonStrategy.put("id", strategy.getId());
			jsonStrategy.put("name", strategy.getName());
			jsonStrategy.put("createDate", strategy.getCreateDate());
			jsonStrategy.put("enable", strategy.getEnable());
			jsonStrategy.put("remark", strategy.getRemark());

			Set<Whstrategydetail> details = strategy.getWhstrategydetails();
			JSONArray jsonDetails = new JSONArray();
			for (Whstrategydetail detail : details) {
				JSONObject jsonDetail = new JSONObject();
				jsonDetail.put("id", detail.getId());
				jsonDetail.put("max", detail.getMax());
				jsonDetail.put("min", detail.getMin());
				String type = detail.getWhstrategytype().getName();
				jsonDetail.put("type", type);
				jsonDetail.put("time", detail.getTime());
				jsonDetails.add(jsonDetail);
			}
			jsonStrategy.put("strategyDetails", jsonDetails);
			jsonStrategies.add(jsonStrategy);
		}
		json.put("strategies", jsonStrategies);
		return json.toString();
	}

	@Autowired
	private WhstrategyDAO whstrategyDAO;

	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;

	/**
	 * POST:<br>
	 * $.ajax({url:'whstrategy/2/1/10/3987', data:JSON.stringify({'enable':true,
	 * 'details':[{'type':'water', max:100, min:10}]}),
	 * contentType:'application/json;charset=utf-8', dataType:'json',
	 * type:'POST'})
	 * 
	 * @param companyId
	 * @param projectId
	 * @param buildingId
	 * @param deviceId
	 * @param strategy
	 * @return
	 */
	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String createStrategy(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId, @PathVariable Integer deviceId, @RequestBody Strategy strategy) {
		log.info(String.format("POST: /{companyId: %d}/{projectId: %d}/{buildingId: %d}/{deviceId: %d}", companyId,
				projectId, buildingId, deviceId));
		log.info(strategy);

		Whstrategy whstrategy = new Whstrategy();
		whstrategy.setWhdeviceId(deviceId);
		whstrategy.setCreateDate(new Date(System.currentTimeMillis()));
		whstrategy.setEnable(strategy.getEnable());
		whstrategy.setName(strategy.getName());
		whstrategyDAO.store(whstrategy);
		
		List<StrategyDetail> details = strategy.getDetails();
		if (details.size() > 0) {
			for (StrategyDetail detail : details) {

				Whstrategytype type = null;
				Set<Whstrategytype> types = whstrategytypeDAO.findWhstrategytypeByName(detail.getType());
				if (types.size() > 0) {
					for (Whstrategytype t : types) {
						type = t;
					}
				} else {
					type = new Whstrategytype();
					type.setName(detail.getType());
					whstrategytypeDAO.store(type);
				}
				
				Whstrategydetail whstrategydetail = new Whstrategydetail();
				whstrategydetail.setWhstrategytypeId(type.getId());
				whstrategydetail.setWhstrategyId(whstrategy.getId());
				whstrategydetail.setMin(new BigDecimal(detail.getMin()));
				whstrategydetail.setMax(new BigDecimal(detail.getMax()));
				whstrategydetail.setTime(new Date());

				whstrategydetailDAO.store(whstrategydetail);
			}
		}

		return JSONObject.fromObject(strategy).toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}/{strategyId}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String queryStrategy(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId, @PathVariable Integer deviceId, @PathVariable Integer strategyId) {
		log.info(String.format("GET: /{companyId: %d}/{projectId: %d}/{buildingId: %d}/{deviceId: %d}/{strategyId: %d}", companyId,
				projectId, buildingId, deviceId, strategyId));

		Whstrategy strategy = whstrategyDAO.findWhstrategyById(strategyId);
		if (strategy == null) {
			return "{\"result\":\"null\"}";
		}
		JSONObject json = new JSONObject();
		json.put("id", strategy.getId());
		json.put("name", strategy.getName());
		json.put("enable", strategy.getEnable());
		json.put("createDate", strategy.getCreateDate());
		Set<Whstrategydetail> details = strategy.getWhstrategydetails();
		if (details != null) {
			JSONArray jsonDetails = new JSONArray();
			for (Whstrategydetail detail : details) {
				JSONObject obj = new JSONObject();
				obj.put("id", detail.getId());
				obj.put("min", detail.getMin());
				obj.put("max", detail.getMax());
				obj.put("type", detail.getWhstrategytype().getName());
				obj.put("time", detail.getTime());
				jsonDetails.add(obj);
			}
			json.put("strategyDetails", jsonDetails);
		}
		
		return json.toString();
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}/{strategyId}", produces = "application/json; charset=utf-8", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteStrategy(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId, @PathVariable Integer deviceId, @PathVariable Integer strategyId) {
		log.info(String.format("DELETE: /{companyId: %d}/{projectId: %d}/{buildingId: %d}/{deviceId: %d}/{strategyId: %d}", companyId,
				projectId, buildingId, deviceId, strategyId));

		Whstrategy strategy = whstrategyDAO.findWhstrategyById(strategyId);
		if (strategy != null) {
			whstrategyDAO.remove(strategy);
			return "{\"result\":\"success\"}";
		}
		return "{\"result\":\"null\"}";
	}

	@RequestMapping(value = "/{companyId}/{projectId}/{buildingId}/{deviceId}/{strategyId}", produces = "application/json; charset=utf-8", method = RequestMethod.PUT)
	@ResponseBody
	public String updateStrategy(@PathVariable Integer companyId, @PathVariable Integer projectId,
			@PathVariable Integer buildingId, @PathVariable Integer deviceId, @PathVariable Integer strategyId, @RequestBody Strategy strategy) {
		log.info(String.format("PUT: /{companyId: %d}/{projectId: %d}/{buildingId: %d}/{deviceId: %d}/{strategyId: %d}", companyId,
				projectId, buildingId, deviceId, strategyId));
		log.info(strategy);
		
		Whstrategy whstrategy = whstrategyDAO.findWhstrategyById(strategyId);
		if (whstrategy == null) {
			return "{\"result\":\"null\"}";
		}
		whstrategy.setEnable(strategy.getEnable());
		whstrategyDAO.merge(whstrategy);
		
		for (StrategyDetail detail : strategy.getDetails()) {
			Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailById(detail.getId());
			if (whstrategydetail == null) {
				whstrategydetail = new Whstrategydetail();
				whstrategydetail.setMax(new BigDecimal(detail.getMax()));
				whstrategydetail.setMin(new BigDecimal(detail.getMin()));

				Whstrategytype whstrategytype = null;
				Set<Whstrategytype> whstrategytypes = whstrategytypeDAO.findWhstrategytypeByName(detail.getType());
				if (whstrategytypes.size() > 0) {
					for (Whstrategytype t : whstrategytypes) {
						whstrategytype = t;
					}
				} else {
					whstrategytype = new Whstrategytype();
					whstrategytype.setName(detail.getType());
					whstrategytypeDAO.store(whstrategytype);
				}
				
				whstrategydetail.setWhstrategytypeId(whstrategytype.getId());
				whstrategydetail.setWhstrategyId(strategyId);
				whstrategydetailDAO.store(whstrategydetail);
				
			} else {
				whstrategydetail.setMin(new BigDecimal(detail.getMin()));
				whstrategydetail.setMax(new BigDecimal(detail.getMax()));
				whstrategydetail.setTime(new Date());
				whstrategydetailDAO.merge(whstrategydetail);
			}
		}
		
		return JSONObject.fromObject(strategy).toString();
	}
}
