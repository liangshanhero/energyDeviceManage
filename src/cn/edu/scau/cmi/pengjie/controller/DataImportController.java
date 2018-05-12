package cn.edu.scau.cmi.pengjie.controller;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.scau.cmi.dao.CacDAO;
import cn.edu.scau.cmi.dao.CacdeviceDAO;
import cn.edu.scau.cmi.dao.CacrecordtimeDAO;
import cn.edu.scau.cmi.dao.CacsensorDAO;
import cn.edu.scau.cmi.dao.LedbuildingDAO;
import cn.edu.scau.cmi.dao.LedmeterDAO;
import cn.edu.scau.cmi.dao.ProjectDAO;
import cn.edu.scau.cmi.dao.ProjectDAOImpl;
import cn.edu.scau.cmi.dao.WhbuildingDAO;
import cn.edu.scau.cmi.dao.WhdatatypeDAO;
import cn.edu.scau.cmi.dao.WhdeviceDAO;
import cn.edu.scau.cmi.dao.WhdevicedataDAO;
import cn.edu.scau.cmi.domain.Cac;
import cn.edu.scau.cmi.domain.Cacdevice;
import cn.edu.scau.cmi.domain.Cacdevicedata;
import cn.edu.scau.cmi.domain.Cacrecordtime;
import cn.edu.scau.cmi.domain.Cacsensor;
import cn.edu.scau.cmi.domain.Cacsensordata;
import cn.edu.scau.cmi.domain.Ledbuilding;
import cn.edu.scau.cmi.domain.Ledmeter;
import cn.edu.scau.cmi.domain.Ledmeterdata;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdatatype;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.domain.Whdevicedata;
import cn.edu.scau.cmi.pengjie.dataPersistence.CACPersistenceService;
import cn.edu.scau.cmi.pengjie.dataPersistence.LEDPersistenceService;
import cn.edu.scau.cmi.pengjie.dataPersistence.WHPersistenceService;
import cn.edu.scau.cmi.service.CacdeviceService;
import cn.edu.scau.cmi.service.CacdevicedataService;
import cn.edu.scau.cmi.service.CacrecordtimeService;
import cn.edu.scau.cmi.service.CacsensorService;
import cn.edu.scau.cmi.service.CacsensordataService;
import cn.edu.scau.cmi.service.WhbuildingServiceImpl;

@Controller("DataImportController")
public class DataImportController {

	@Autowired
	private ProjectDAO projectdao;
	@Autowired
	WhbuildingDAO buildingdao;
	@Autowired
	WhdeviceDAO whdevicedao;
	@Autowired
	WhdatatypeDAO datatypedao;
	@Autowired
	WhdevicedataDAO deivcedatadao;
	@Autowired
	CacDAO cacdao;
	@Autowired
	CacdeviceDAO cacdevicedao;
	@Autowired
	WhbuildingDAO whbuildingdao;

	@Autowired
	LedbuildingDAO ledbuildingdao;
	@Autowired
	LedmeterDAO ledmeterdao;
	@Autowired
	CacrecordtimeDAO cacrecordtimedao;
	@Autowired
	CacsensorDAO cacsensordao;
	@Autowired
	CacrecordtimeService cacrecordtimeservice;
	@Autowired
	CacdeviceService cacdeviceservice;
	@Autowired
	CacsensorService cacsensorservice;
	@Autowired
	CacdevicedataService cacdevicedataservice;
	@Autowired
	CacsensordataService cacsensordataservice;
	@Autowired
	CACPersistenceService cacPersistenceService;
	@Autowired
	WHPersistenceService whPersistService;
	@Autowired
	LEDPersistenceService ledPersistencesService;

	/*
	 * 写WH数据进数据库
	 */
	@RequestMapping("/readWHDataIntoDB")
	public String WHDataPersistence(HttpServletRequest req,
			HttpServletResponse res) throws IOException, InvalidFormatException {
		File file = new File("D:/devicemanage/temp/WH/testData.xlsx");

		XSSFWorkbook xwb = null;
		xwb = new XSSFWorkbook(file);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;

		// 获取项目的值
		Iterator<Project> projects = projectdao.findAllProjects().iterator();

		Project project = projects.next();

		project = projectdao.store(project);

		List<Whdevice> whdeviceList = new ArrayList<>();
		List<Whdatatype> whdatatypesList = new ArrayList<>();

		String buildingName = sheet.getRow(1).getCell(1).toString();
		Whbuilding whbuilding = new Whbuilding();
		whbuilding = whPersistService.saveWhbuilding(buildingName, project);
		whbuilding = whbuildingdao.store(whbuilding);

		for (int i = sheet.getFirstRowNum() + 1; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			XSSFRow buildingRow = sheet.getRow(i);
			if (buildingRow == null) {
				System.out.println("第" + i + "行的数据   " + "最后的空一行");
				break;
			} else {
				// 保存whdatatype的类型
				String whdatatypename = buildingRow.getCell(3).toString();
				Whdatatype whdatatype = new Whdatatype();
				whdatatype = whPersistService.saveWhdatatype(whdatatypename);
				whdatatypesList.add(whdatatype);
				Set<Whdatatype> whdatatypeSet = new HashSet<Whdatatype>();
				whdatatypeSet.add(whdatatype);
				// 保存了whdevice设备名称
				String deviceName = buildingRow.getCell(2).toString();
				Whdevice whdevice = new Whdevice();
				whdevice = whPersistService.saveWhdevice(deviceName,
						whbuilding, whdatatypeSet);
				whdevice = whdevicedao.store(whdevice);
				whdeviceList.add(whdevice);

			}
		}

		List<Whdevicedata> whdevicedataList = new ArrayList<>();
		for (int i = sheet.getFirstRowNum() + 1; i < sheet
				.getPhysicalNumberOfRows(); i++) {

			row = sheet.getRow(i);
			// 获取每一行的参数值。
			if (row == null) {
				System.out.println("end of file");
				break;
			} else {

				// 写一个批处理方法处理它的whdevicedata;
				Date date = row.getCell(0).getDateCellValue();

				String whdatatype = row.getCell(3).toString();

				String deviceNumber = row.getCell(2).toString();
				// String paraname = row.getCell(3).toString();
				double value = row.getCell(4).getNumericCellValue();
				Integer isio = (int) row.getCell(5).getNumericCellValue();
				Integer isupdate = (int) row.getCell(6).getNumericCellValue();

				Whdevicedata whdevicedata = new Whdevicedata();

				int whdeviceTemp = -1;
				for (int n = 0; n < whdeviceList.size(); n++) {
					if (whdeviceList.get(n).getNumber().equals(deviceNumber)) {
						whdeviceTemp = n;
					}
				}
				int whdatatypeTemp = -1;
				for (int p = 0; p < whdatatypesList.size(); p++) {
					if (whdatatypesList.get(p).getName().equals(whdatatype)) {
						whdatatypeTemp = p;
					}
				}

				whdevicedata.setIsio(isio);
				whdevicedata.setIsupdate(isupdate);
				whdevicedata.setTime(date);
				whdevicedata.setValue(new BigDecimal(value));
				whdevicedata.setWhdatatype(whdatatypesList.get(whdatatypeTemp)
						.getId());
				whdevicedata
						.setWhdevice(whdeviceList.get(whdeviceTemp).getId());
				whdevicedataList.add(whdevicedata);

			}
			if (i % 100 == 0) {
				whPersistService.batchSaveWhdevicedata(whdevicedataList);
				whdevicedataList.clear();
			}
		}
		whPersistService.batchSaveWhdevicedata(whdevicedataList);
		System.out.println("数据保存成功！");
		return "forward:/dataInsertSuccessful.jsp";
	}

	/* 保存LED数据到数据库中 */
	@RequestMapping("/readLEDDataIntoDB")
	public String LEDDataPersistence(HttpServletRequest req,
			HttpServletResponse res) throws InvalidFormatException, IOException {
		System.out.println("开始保存LED数据：");

		File file = new File("D:/devicemanage/temp/LED/LED最终版格式文件.xlsx");

		XSSFWorkbook xwb = null;
		xwb = new XSSFWorkbook(file);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;

		// 获取项目的值
		Iterator<Project> projects = projectdao.findAllProjects().iterator();
		Project project = projects.next();

		Ledbuilding ledbuilding = new Ledbuilding();

		int wellNumber = (int) sheet.getRow(2).getCell(1).getNumericCellValue(); // 电井的数量
		int storeyNumber = (int) sheet.getRow(2).getCell(0)
				.getNumericCellValue();// 楼层的数量
		String buildingName = sheet.getRow(2).getCell(5).toString();// 建筑名称
		int totaldays = (int) sheet.getRow(2).getCell(2).getNumericCellValue();// 合计天数

		Date recordDate = sheet.getRow(2).getCell(3).getDateCellValue();

		ledbuilding = ledPersistencesService.saveLedbuilding(buildingName,
				project, wellNumber, storeyNumber);
		ledbuilding = ledbuildingdao.store(ledbuilding);

		for (int i = sheet.getFirstRowNum() + 4; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			if (row == null) {
				// 读取的行为空，因此数据需要停止
				System.out.println("第" + i + "行的数据为空");
			} else {
				Ledmeter ledmeter = new Ledmeter();
				Ledmeterdata ledmeterdata = new Ledmeterdata();

				String ledmerterNumber = row.getCell(0).toString();
				String wellNum = row.getCell(1).toString();
				int storeyNum = (int) row.getCell(4).getNumericCellValue();
				double ledmeterValue = row.getCell(3).getNumericCellValue();

				double totalamount = row.getCell(2).getNumericCellValue();

				// save ledmeter
				ledmeter = ledPersistencesService.saveLedmeter(ledmerterNumber,
						ledbuilding, wellNum, storeyNum, new BigDecimal(
								totalamount), new BigDecimal(totaldays));
				// System.out.println("保存Ledmeter数据了！");
				ledmeter = ledmeterdao.store(ledmeter);

				// save ledmeterdata
				System.out.println("ledmeterValue是否有变" + ledmeterValue
						+ "使用new BigDecimal::" + new BigDecimal(ledmeterValue));

				ledmeterdata = ledPersistencesService.saveLedmeterdata(
						new BigDecimal(ledmeterValue), recordDate, ledmeter);
				// System.out.println("保存Ledmeterdata数据了！");
			}
		}
		System.out.println("已经完成 了LED数据的导入！");
		return "forward:/dataInsertSuccessful.jsp";
	}

	// 按照每一列
	@RequestMapping("/cacDataPersistenceBYColumn")
	public String cacDataPersistenceBYColumn(HttpServletRequest req,
			HttpServletResponse res) throws InvalidFormatException,
			IOException, ParseException {

		long startTime = System.currentTimeMillis();

		File file = new File("D:/devicemanage/temp/CAC/交易中心空调数据格式201708.xlsx");

		/*
		 * POI操作XLSX文件
		 */
		XSSFWorkbook xwb = new XSSFWorkbook(file);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;

		/*
		 * 获取它的设备数量和传感器数量
		 */
		int columnNum = sheet.getRow(2).getPhysicalNumberOfCells();
		int deviceNum = 0;// 设备数量
		int sensorNum = 0;// 传感器数
		for (int i = 2; i < columnNum; i++) {
			if (sheet.getRow(1).getCell(i) == null) {
				deviceNum = i;
				break;
			}
		}

		deviceNum = deviceNum;
		sensorNum = columnNum - deviceNum - 2 + 1;

		Cac cac = new Cac();
		String remark = "能源公司中央空调";

		Iterator<Project> projects = projectdao.findAllProjects().iterator();
		Project project = projects.next();

		cac = cacPersistenceService.saveCac(project, remark);
		cac = cacdao.store(cac);

		List<Cacrecordtime> cacRecordtimeList = new ArrayList<>();

		for (int i = sheet.getFirstRowNum() + 3; i < sheet
				.getPhysicalNumberOfRows(); i++) {
			XSSFRow recordTimeRow = sheet.getRow(i);
			if (recordTimeRow == null) {
				System.out.println("cacrecordtime数据保存完毕");
				break;
			} else {
				if (recordTimeRow.getCell(0).getDateCellValue() != null) {
					String date = recordTimeRow.getCell(0).getDateCellValue()
							.toLocaleString();
					String time = recordTimeRow.getCell(1).getDateCellValue()
							.toLocaleString();

					Calendar RecordTime = getCACRecordtime(date, time);
					String watchkeeper = recordTimeRow.getCell(columnNum - 1)
							.toString();
					cacRecordtimeList.add(cacPersistenceService
							.saveCacrecordtime(RecordTime, watchkeeper));
				}
			}
		}
		for (int m = 0; m < cacRecordtimeList.size(); m++) {
			System.out.println("保存的时间："
					+ cacRecordtimeList.get(m).getRecordTime());
		}

		// 设备列表
		List<Cacdevice> cacDeviceList = new ArrayList<>();
		// 传感器列表
		List<Cacsensor> cacSensorList = new ArrayList<>();

		/*
		 * 调用CACPersistenceService直接保存它的设备和传感器名称
		 */
		for (int i = 2; i < columnNum - 1; i++) {
			if (i < deviceNum) {
				// 获取device列表
				String deviceName = sheet.getRow(sheet.getFirstRowNum())
						.getCell(i).toString();
				String deviceUnit = sheet.getRow(sheet.getFirstRowNum() + 1)
						.getCell(i).toString();
				cacDeviceList.add(cacPersistenceService.saveCacdevice(cac,
						deviceName, deviceUnit));
			} else {
				String sensorName = sheet.getRow(sheet.getFirstRowNum())
						.getCell(i).toString();
				cacSensorList.add(cacPersistenceService.saveCacsensor(cac,
						sensorName));
			}
		}

		for (int i = 2; i < columnNum - 1; i++) {
			// 保存第i列的数据,包括设备的数据和传感器的数据�?
			if (i < deviceNum) {
				int cacSeviceTemp = -1;
				for (int l = 0; l < cacDeviceList.size(); l++) {
					if (cacDeviceList.get(l).getName()
							.equals(sheet.getRow(0).getCell(i).toString())) {
						cacSeviceTemp = l;
					}
				}

				List<Cacdevicedata> cacdevicedataSet = new ArrayList<>();

				// 每一个单元格处理
				for (int j = sheet.getFirstRowNum() + 3; j < sheet
						.getPhysicalNumberOfRows(); j++) {
					XSSFRow datarow = sheet.getRow(j);

					if (datarow == null) {
						System.out.println(datarow);
						break;
					} else {
						// 保存时间
						if (datarow.getCell(0).getDateCellValue() != null) {
							String date = datarow.getCell(0).getDateCellValue()
									.toLocaleString();
							String time = datarow.getCell(1).getDateCellValue()
									.toLocaleString();
							Calendar RecordTime = getCACRecordtime(date, time);

							System.out.println(RecordTime);

							int cacrecordtimetemp = -1;
							for (int k = 0; k < cacRecordtimeList.size(); k++) {
								if (cacRecordtimeList.get(k).getRecordTime()
										.equals(RecordTime.getTime())) {
									System.out.println("到这一步！");
									cacrecordtimetemp = k;
								}
							}
							double value = datarow.getCell(i)
									.getNumericCellValue();
							int isreport = 0;

							Cacdevicedata cacdevicedata = new Cacdevicedata();
							cacdevicedata.setCacrecordtime(cacRecordtimeList
									.get(cacrecordtimetemp).getId());
							cacdevicedata.setIsreport(isreport);
							cacdevicedata.setValue(new BigDecimal(value));
							cacdevicedataSet.add(cacdevicedata);
						}
					}
				}

				cacPersistenceService.batchSaveCacdeviceData(cacdevicedataSet,
						cacDeviceList.get(cacSeviceTemp));

			} else {

				int cacsensortemp = -1;
				for (int m = 0; m < cacSensorList.size(); m++) {
					if (cacSensorList.get(m).getName()
							.equals(sheet.getRow(0).getCell(i).toString())) {
						cacsensortemp = m;
					}
				}

				List<Cacsensordata> cacsensordataSet = new ArrayList<>();
				// 保存传感器的数据
				for (int j = sheet.getFirstRowNum() + 3; j < sheet
						.getPhysicalNumberOfRows(); j++) {

					XSSFRow datarow = sheet.getRow(j);

					if (datarow == null) {
						System.out.println("已经没有数据了！");
						break;
					} else {

						if (datarow.getCell(0).getDateCellValue() != null) {
							String date = datarow.getCell(0).getDateCellValue()
									.toLocaleString();
							String time = datarow.getCell(1).getDateCellValue()
									.toLocaleString();
							Calendar RecordTime = getCACRecordtime(date, time);

							int cacrecordtimeTemp = -1;
							for (int k = 0; k < cacRecordtimeList.size(); k++) {
								if (cacRecordtimeList.get(k).getRecordTime()
										.equals(RecordTime.getTime())) {
									cacrecordtimeTemp = k;
								}
							}
							int isreport = 0;
							String status = datarow.getCell(i).toString();

							Cacsensordata cacsensordata = new Cacsensordata();

							cacsensordata.setCacrecordtime(cacRecordtimeList
									.get(cacrecordtimeTemp).getId());
							cacsensordata.setIsreport(isreport);
							cacsensordata.setValue(status);

							cacsensordataSet.add(cacsensordata);
						}
					}
				}
				cacPersistenceService.batchSaveCacsensorData(cacsensordataSet,
						cacSensorList.get(cacsensortemp));
			}
			System.out.println("按列读取数据保存");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法三的运行时间" + String.valueOf(endTime - startTime)
				+ "ms");

		return "forward:/dataInsertSuccessful.jsp";
	}

	@RequestMapping("/cacMalfunctionPersistence")
	public String cacMalfunctionPersistence(HttpServletRequest req,
			HttpServletResponse res) throws InvalidFormatException,
			IOException, ParseException {
		// save cacmalfunction
		File file = new File("D:/devicemanage/temp/CAC/2017-2malfunction.xlsx");

		XSSFWorkbook xwb = null;
		xwb = new XSSFWorkbook(file);
		XSSFSheet sheet = xwb.getSheetAt(0);
		XSSFRow row;

		// 保存cac数据
		Cac cac = new Cac();
		String remark = "能源公司中央空调";
		Iterator<Project> projects = projectdao.findAllProjects().iterator();
		Project project = projects.next();
		cac = cacPersistenceService.saveCac(project, remark);
		cac = cacdao.store(cac);

		for (int i = sheet.getFirstRowNum(); i < sheet
				.getPhysicalNumberOfRows(); i++) {

			row = sheet.getRow(i);

			String deviceUnit = " ";
			String deviceName = row.getCell(0).toString();
			String deviceStatus = getStatus(row.getCell(1).toString());
			String watchkeeper = row.getCell(3).toString();

			Cacdevice cacdevice = new Cacdevice();
			cacdevice = cacPersistenceService.saveCacdevice(cac, deviceName,
					deviceUnit);
			cacdevice = cacdevicedao.store(cacdevice);
			System.out.println("保存好的cacdevice数据：" + cacdevice.getName());

			Calendar malfunctionRecordtime = Calendar.getInstance();
			int excelCellType = row.getCell(2).getCellType();
			String StringTypeOfDate = "";
			Date dateTypeOfDate = new Date();
			int getExcelDateType = 0;
			switch (excelCellType) {
			case Cell.CELL_TYPE_STRING:// 字符串类型
				StringTypeOfDate = row.getCell(2).toString();
				System.out.println(i + "字符串" + StringTypeOfDate);
				getExcelDateType = 1;
				break;
			case Cell.CELL_TYPE_NUMERIC:// 日期数值的类型
				dateTypeOfDate = row.getCell(2).getDateCellValue();
				// System.out.println(i+"日期类型"+dateDate);
				getExcelDateType = 2;
				break;
			default:
				break;
			}
			if (getExcelDateType == 2) {
				// 处理date类型的日期
				malfunctionRecordtime.setTime(dateTypeOfDate);
			}
			if (getExcelDateType == 1) {
				// 处理stringe类型的date
				String[] dates = StringTypeOfDate.split(" ");
				malfunctionRecordtime = handleMalfunctionDate(dates);
			}
			System.out.println(malfunctionRecordtime + " 间隔 " + watchkeeper);

			Cacrecordtime cacrecordtime = cacPersistenceService
					.saveCacrecordtime(malfunctionRecordtime, watchkeeper);
			cacrecordtime = cacrecordtimedao.store(cacrecordtime);
			System.out.println("保存好的cacrecordtime数据："
					+ cacrecordtime.getRecordTime());
			cacPersistenceService.saveCacmalfunction(cacrecordtime, cacdevice,
					deviceStatus);
		}
		return "forward:/dataInsertSuccessful.jsp";
	}

	private static Calendar handleMalfunctionDate(String[] dates)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();

		int datetime = 0;
		switch (dates[2]) {
		case "上午":
			datetime = 0;
			break;
		case "下午":
			datetime = 12;
			break;
		}
		String[] timeStr = dates[3].split(":");

		int time = Integer.parseInt(timeStr[0]);
		time = time + datetime;
		int newtime = 0;

		int needToAddOneDay = 0;

		if (time >= 24) {
			needToAddOneDay = 1;
			newtime = time - 24;
		}
		if (needToAddOneDay == 1) {
			String finalDateStr = dates[0] + " " + Integer.toString(newtime)
					+ ":" + timeStr[1] + ":00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date finalDate = sdf.parse(finalDateStr);
			calendar.setTime(finalDate);
			calendar.add(Calendar.DATE, 1);
		} else {
			String finalDateStr = dates[0] + " " + dates[3] + ":00";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date finalDate = sdf.parse(finalDateStr);
			calendar.setTime(finalDate);
		}
		return calendar;
	}

	private static String getStatus(String status) {
		// TODO Auto-generated method stub
		String Status = "正常";
		if (status.contains("正常")) {
			Status = "正常";
		}
		if (status.contains("故障")) {
			Status = "故障";
		}
		return Status;
	}

	private Calendar getCACRecordtime(String date, String time)
			throws ParseException {
		Calendar calendar = Calendar.getInstance();

		String[] dates = date.split(" ");
		String[] times = time.split(" ");
		String correctDay = dates[0] + " " + times[1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date mydate = sdf.parse(correctDay);

		calendar.setTime(mydate);
		return calendar;
	}
}
