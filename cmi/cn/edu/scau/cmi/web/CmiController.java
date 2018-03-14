package cn.edu.scau.cmi.web;

import java.text.SimpleDateFormat;

import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import cn.edu.scau.cmi.initBinder.*;
import cn.edu.scau.cmi.domain.*;

@Controller("CmiController")
public class CmiController {

	@Autowired
	private CacEditor cacEditor;

	@Autowired
	private CacsEditor cacsEditor;
	@Autowired
	private CacdeviceEditor cacdeviceEditor;

	@Autowired
	private CacdevicesEditor cacdevicesEditor;
	@Autowired
	private CacdevicedataEditor cacdevicedataEditor;

	@Autowired
	private CacdevicedatasEditor cacdevicedatasEditor;
	@Autowired
	private CacmalfunctionEditor cacmalfunctionEditor;

	@Autowired
	private CacmalfunctionsEditor cacmalfunctionsEditor;
	@Autowired
	private CacrecordtimeEditor cacrecordtimeEditor;

	@Autowired
	private CacrecordtimesEditor cacrecordtimesEditor;
	@Autowired
	private CacsensorEditor cacsensorEditor;

	@Autowired
	private CacsensorsEditor cacsensorsEditor;
	@Autowired
	private CacsensordataEditor cacsensordataEditor;

	@Autowired
	private CacsensordatasEditor cacsensordatasEditor;
	@Autowired
	private CompanyEditor companyEditor;

	@Autowired
	private CompanysEditor companysEditor;
	@Autowired
	private LedbuildingEditor ledbuildingEditor;

	@Autowired
	private LedbuildingsEditor ledbuildingsEditor;
	@Autowired
	private LedmeterEditor ledmeterEditor;

	@Autowired
	private LedmetersEditor ledmetersEditor;
	@Autowired
	private LedmeterdataEditor ledmeterdataEditor;

	@Autowired
	private LedmeterdatasEditor ledmeterdatasEditor;
	@Autowired
	private ProjectEditor projectEditor;

	@Autowired
	private ProjectsEditor projectsEditor;
	@Autowired
	private StaffEditor staffEditor;

	@Autowired
	private StaffsEditor staffsEditor;
	@Autowired
	private WhbuildingEditor whbuildingEditor;

	@Autowired
	private WhbuildingsEditor whbuildingsEditor;
	@Autowired
	private WhdatatypeEditor whdatatypeEditor;

	@Autowired
	private WhdatatypesEditor whdatatypesEditor;
	@Autowired
	private WhdeviceEditor whdeviceEditor;

	@Autowired
	private WhdevicesEditor whdevicesEditor;
	@Autowired
	private WhdevicedataEditor whdevicedataEditor;

	@Autowired
	private WhdevicedatasEditor whdevicedatasEditor;

	/**
	 * Register custom, context-specific property editors
	 * 
	 */
	@InitBinder
	public void initBinder(WebDataBinder binder, HttpServletRequest request) {
		binder.registerCustomEditor(Cac.class, "relativeCac", cacEditor);
		binder.registerCustomEditor(Set.class, "relativeCacs", cacsEditor);
		binder.registerCustomEditor(Cacdevice.class, "relativeCacdevice",
				cacdeviceEditor);
		binder.registerCustomEditor(Set.class, "relativeCacdevices",
				cacdevicesEditor);
		binder.registerCustomEditor(Cacdevicedata.class,
				"relativeCacdevicedata", cacdevicedataEditor);
		binder.registerCustomEditor(Set.class, "relativeCacdevicedatas",
				cacdevicedatasEditor);
		binder.registerCustomEditor(Cacmalfunction.class,
				"relativeCacmalfunction", cacmalfunctionEditor);
		binder.registerCustomEditor(Set.class, "relativeCacmalfunctions",
				cacmalfunctionsEditor);
		binder.registerCustomEditor(Cacrecordtime.class,
				"relativeCacrecordtime", cacrecordtimeEditor);
		binder.registerCustomEditor(Set.class, "relativeCacrecordtimes",
				cacrecordtimesEditor);
		binder.registerCustomEditor(Cacsensor.class, "relativeCacsensor",
				cacsensorEditor);
		binder.registerCustomEditor(Set.class, "relativeCacsensors",
				cacsensorsEditor);
		binder.registerCustomEditor(Cacsensordata.class,
				"relativeCacsensordata", cacsensordataEditor);
		binder.registerCustomEditor(Set.class, "relativeCacsensordatas",
				cacsensordatasEditor);
		binder.registerCustomEditor(Company.class, "relativeCompany",
				companyEditor);
		binder.registerCustomEditor(Set.class, "relativeCompanys",
				companysEditor);
		binder.registerCustomEditor(Ledbuilding.class, "relativeLedbuilding",
				ledbuildingEditor);
		binder.registerCustomEditor(Set.class, "relativeLedbuildings",
				ledbuildingsEditor);
		binder.registerCustomEditor(Ledmeter.class, "relativeLedmeter",
				ledmeterEditor);
		binder.registerCustomEditor(Set.class, "relativeLedmeters",
				ledmetersEditor);
		binder.registerCustomEditor(Ledmeterdata.class, "relativeLedmeterdata",
				ledmeterdataEditor);
		binder.registerCustomEditor(Set.class, "relativeLedmeterdatas",
				ledmeterdatasEditor);
		binder.registerCustomEditor(Project.class, "relativeProject",
				projectEditor);
		binder.registerCustomEditor(Set.class, "relativeProjects",
				projectsEditor);
		binder.registerCustomEditor(Staff.class, "relativeStaff", staffEditor);
		binder.registerCustomEditor(Set.class, "relativeStaffs", staffsEditor);
		binder.registerCustomEditor(Whbuilding.class, "relativeWhbuilding",
				whbuildingEditor);
		binder.registerCustomEditor(Set.class, "relativeWhbuildings",
				whbuildingsEditor);
		binder.registerCustomEditor(Whdatatype.class, "relativeWhdatatype",
				whdatatypeEditor);
		binder.registerCustomEditor(Set.class, "relativeWhdatatypes",
				whdatatypesEditor);
		binder.registerCustomEditor(Whdevice.class, "relativeWhdevice",
				whdeviceEditor);
		binder.registerCustomEditor(Set.class, "relativeWhdevices",
				whdevicesEditor);
		binder.registerCustomEditor(Whdevicedata.class, "relativeWhdevicedata",
				whdevicedataEditor);
		binder.registerCustomEditor(Set.class, "relativeWhdevicedatas",
				whdevicedatasEditor);

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, true));

		binder.registerCustomEditor(
				boolean.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.EnhancedBooleanEditor(
						false));
		binder.registerCustomEditor(
				Boolean.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.EnhancedBooleanEditor(
						true));
		// binder.registerCustomEditor(java.util.Date.class, new
		// cn.edu.scau.cmi.liangzaoqing.initbinder.CustomDateEditor());
		// binder.registerCustomEditor(java.util.Date.class, new
		// cn.edu.scau.cmi.liangzaoqing.initbinder.CustomDateEditor());
		binder.registerCustomEditor(
				java.util.Calendar.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.CustomCalendarEditor());
		binder.registerCustomEditor(
				byte[].class,
				new org.springframework.web.multipart.support.ByteArrayMultipartFileEditor());
		binder.registerCustomEditor(
				java.math.BigDecimal.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.NaNHandlingNumberEditor(
						java.math.BigDecimal.class, true));
		binder.registerCustomEditor(
				Integer.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.NaNHandlingNumberEditor(
						Integer.class, true));
		binder.registerCustomEditor(String.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.StringEditor());
		binder.registerCustomEditor(
				Long.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.NaNHandlingNumberEditor(
						Long.class, true));
		binder.registerCustomEditor(
				Double.class,
				new cn.edu.scau.cmi.liangzaoqing.initbinder.NaNHandlingNumberEditor(
						Double.class, true));
	}

}