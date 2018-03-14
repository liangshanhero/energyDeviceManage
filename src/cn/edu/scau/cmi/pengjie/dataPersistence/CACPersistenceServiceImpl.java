package cn.edu.scau.cmi.pengjie.dataPersistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.dao.CacDAO;
import cn.edu.scau.cmi.dao.CacdeviceDAO;
import cn.edu.scau.cmi.dao.CacdevicedataDAO;
import cn.edu.scau.cmi.dao.CacmalfunctionDAO;
import cn.edu.scau.cmi.dao.CacrecordtimeDAO;
import cn.edu.scau.cmi.dao.CacsensorDAO;
import cn.edu.scau.cmi.dao.CacsensordataDAO;
import cn.edu.scau.cmi.domain.Cac;
import cn.edu.scau.cmi.domain.Cacdevice;
import cn.edu.scau.cmi.domain.Cacdevicedata;
import cn.edu.scau.cmi.domain.Cacmalfunction;
import cn.edu.scau.cmi.domain.Cacrecordtime;
import cn.edu.scau.cmi.domain.Cacsensor;
import cn.edu.scau.cmi.domain.Cacsensordata;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.service.CacrecordtimeService;

@Service("CACPersistenceService")
@Transactional
public class CACPersistenceServiceImpl implements CACPersistenceService {

	@Autowired
	private CacDAO cacdao;
	@Autowired
	private CacdeviceDAO cacdevicedao;
	@Autowired
	private CacdevicedataDAO cacdevicedatadao;
	@Autowired
	private CacmalfunctionDAO cacmalfunctiondao;
	@Autowired
	private CacrecordtimeDAO cacrecordtimedao;
	@Autowired
	private CacsensorDAO cacsensordao;

	@Autowired
	CacrecordtimeService cacrecordtimeservice;

	@Autowired
	private CacsensordataDAO cacsensordatadao;

	@PersistenceContext(unitName = "energydevice")
	EntityManager entityManager;

	@Transactional
	public Cac saveCac(Project project, String remark) {

		Cac cac = new Cac();

		Iterator<Cac> cacs = cacdao.findAllCacs().iterator();

		if (!cacs.hasNext()) {

			System.out.println("save CAC");
			cac.setProject(project.getId());
			cac.setRemark(remark);
			entityManager.persist(cac);
			entityManager.flush();
			cac = cacdao.store(cac);

		} else {
			int needInsertInto = 0;

			while (cacs.hasNext()) {
				Cac newcac = new Cac();
				newcac = cacs.next();
				if (newcac.getProject().equals(project)) {
					needInsertInto = 1;
					cac = newcac;
				}
				if (needInsertInto == 0) {
					// 说明数据库中没有该项�?
					cac.setProject(project.getId());
					cac.setRemark(remark);
					entityManager.persist(cac);
					entityManager.flush();
				}
				cac = cacdao.store(cac);
				System.out.println("save CAC");
			}
		}
		return cac;
	}

	@Transactional
	public Cacsensor saveCacsensor(Cac cac, String name) {
		// TODO Auto-generated method stub
		Cacsensor cacsensor = new Cacsensor();
		Iterator<Cacsensor> cacsensors = cacsensordao.findCacsensorByName(name)
				.iterator();
		if (!cacsensors.hasNext()) {
			// 不存在这样的传感器，可以直接保存进数据库中�?
			cacsensor.setName(name);
			cacsensor.setCac(cac.getId());
			entityManager.persist(cacsensor);
			entityManager.flush();

		} else {
			int needInsertInto = 0;
			while (cacsensors.hasNext()) {
				Cacsensor Cacsensor = new Cacsensor();
				Cacsensor = cacsensors.next();
				if (Cacsensor.getCac().equals(cac)) {
					needInsertInto = 1;
					cacsensor = Cacsensor;
				}
			}
			if (needInsertInto == 0) {
				// 说明数据库中有该名称的传感器，但不是该项目的传感器�?
				cacsensor.setName(name);
				cacsensor.setCac(cac.getId());
				entityManager.persist(cacsensor);
				entityManager.flush();
			}
		}
		cacsensor = cacsensordao.store(cacsensor);
		return cacsensor;
	}

	@Transactional
	public Cacdevice saveCacdevice(Cac cac, String name, String unit) {
		// TODO Auto-generated method stub
		Cacdevice cacdevice = new Cacdevice();
		Iterator<Cacdevice> cacdevices = cacdevicedao.findCacdeviceByName(name)
				.iterator();

		System.out.println("到达这里 ！");

		if (!cacdevices.hasNext()) {
			// 不存在这样的传感器，可以直接保存进数据库中�?
			cacdevice.setName(name);
			cacdevice.setCac(cac.getId());
			cacdevice.setUnit(unit);
			entityManager.persist(cacdevice);
			System.out.println("新添加进去的数据！！");
		} else {
			int needInsertInto = 0;
			while (cacdevices.hasNext()) {
				Cacdevice Cacdevice = new Cacdevice();
				Cacdevice = cacdevices.next();
				if (Cacdevice.getCac().equals(cac)) {
					needInsertInto = 1;
					cacdevice = Cacdevice;
				}
			}
			if (needInsertInto == 0) {
				// 说明数据库中有该名称的传感器，但不是该项目的传感器�?
				cacdevice.setName(name);
				cacdevice.setCac(cac.getId());
				cacdevice.setUnit(unit);
				entityManager.persist(cacdevice);
			}
		}
		cacdevice = cacdevicedao.store(cacdevice);
		cacdevicedao.flush();
		return cacdevice;
	}

	@Transactional
	public Cacmalfunction saveCacmalfunction(Cacrecordtime recordtime,
			Cacdevice cacdevice, String status) {
		System.out.println("recordtime:" + recordtime);
		System.out.println("cacdevice:" + cacdevice);

		Cacmalfunction MyMalfunction = new Cacmalfunction();
		Cacmalfunction malfunction = cacmalfunctiondao
				.findCacmalfunctionByPrimaryKey(recordtime.getId(),
						cacdevice.getId());

		if (malfunction == null) {
			// 说明没有该数�?
			System.out.println("baocun :malfunction");
			System.out.println("cacname:" + cacdevice.getName());

			MyMalfunction.setCacdevice(cacdevice.getId());
			MyMalfunction.setCacrecordtime(recordtime.getId());
			MyMalfunction.setStatus(status);
			System.out.println("11111");
			entityManager.persist(MyMalfunction);
			entityManager.flush();
			System.out.println("22222");
		} else {// 说明数据库中已经存在了该数据，可以直接拿去用
			MyMalfunction = malfunction;
		}
		return MyMalfunction;
	}

	@Transactional
	public Cacrecordtime saveCacrecordtime(Calendar RecordTime,
			String watchkeeper) {

		Cacrecordtime cacrecordtime = new Cacrecordtime();

		cacrecordtime.setRecordTime(RecordTime.getTime());
		cacrecordtime.setWatchkeeper(watchkeeper);

		boolean canbemerged = cacrecordtimedao.canBeMerged(cacrecordtime);

		Iterator<Cacrecordtime> cacrecordtimeSet = cacrecordtimedao
				.findCacrecordtimeByWatchkeeper(watchkeeper).iterator();

		if (!cacrecordtimeSet.hasNext()) {
			// 说明不存在该时间段的记录，需要存进去
			cacrecordtime.setRecordTime(RecordTime.getTime());
			cacrecordtime.setWatchkeeper(watchkeeper);
			entityManager.persist(cacrecordtime);
			entityManager.flush();

		} else {
			int needInsertInto = 0;
			while (cacrecordtimeSet.hasNext()) {
				Cacrecordtime Cacrecordtime = new Cacrecordtime();
				Cacrecordtime = cacrecordtimeSet.next();
				if (Cacrecordtime.getRecordTime().equals(RecordTime)) {
					needInsertInto = 1;
					cacrecordtime = Cacrecordtime;
					System.out.println("数据库中已经存在了该数据信息");
				}
			}
			if (needInsertInto == 0) {
				cacrecordtime.setRecordTime(RecordTime.getTime());
				cacrecordtime.setWatchkeeper(watchkeeper);
				entityManager.persist(cacrecordtime);
				entityManager.flush();
			}
		}
		cacrecordtime = cacrecordtimedao.store(cacrecordtime);
		return cacrecordtime;
	}

	@Transactional
	public Cacsensordata saveCacsensordata(Cacrecordtime cacrecordtime,
			Cacsensor cacsensor, String value, int isreport) {
		Cacsensordata cacsensordata = new Cacsensordata();
		Cacsensordata cacsensordatas = cacsensordatadao
				.findCacsensordataByPrimaryKey(cacrecordtime.getId(),
						cacsensor.getId());
		if (cacsensordatas == null) {
			cacsensordata.setCacrecordtime(cacrecordtime.getId());
			cacsensordata.setCacsensor(cacsensor.getId());
			cacsensordata.setValue(value);
			cacsensordata.setIsreport(isreport);
			entityManager.persist(cacsensordata);
			entityManager.flush();
		} else {
			cacsensordata = cacsensordatas;
		}
		cacsensordata = cacsensordatadao.store(cacsensordata);
		return cacsensordata;
	}

	@Transactional
	public Cacdevicedata saveCacdevicedata(Cacdevice cacdevice,
			Cacrecordtime cacrecordtime, BigDecimal value, int isreport) {
		// TODO Auto-generated method stub
		Cacdevicedata cacdevicedata = new Cacdevicedata();
		Cacdevicedata cacdevicedataSet = cacdevicedatadao
				.findCacdevicedataByPrimaryKey(cacdevice.getId(),
						cacrecordtime.getId());

		if (cacdevicedataSet == null) {
			cacdevicedata.setCacdevice(cacdevice.getId());
			cacdevicedata.setCacrecordtime(cacrecordtime.getId());
			cacdevicedata.setValue(value);
			cacdevicedata.setIsreport(isreport);
			entityManager.persist(cacdevicedata);
			entityManager.flush();
			cacdevicedata = cacdevicedatadao.store(cacdevicedata);
		} else
			cacdevicedata = cacdevicedataSet;
		return cacdevicedata;
	}

	@Transactional
	public void saveCacdeviceData(File file) {
		// TODO

	}

	@Transactional
	public void batchSaveCacdeviceData(List<Cacdevicedata> cacdevicedataSet,
			Cacdevice cacdevice) {

		System.out.println("��ʼ:" + new Date());
		for (int i = 0; i < cacdevicedataSet.size(); i++) {
			Cacdevicedata cacdevicedata = cacdevicedataSet.get(i);
			cacdevicedata.setCacdevice(cacdevice.getId());
			entityManager.persist(cacdevicedata);
			if (i % 100 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		entityManager.flush();
		entityManager.clear();
		System.out.println("保存了device" + cacdevice.getName() + new Date());
	}

	@Transactional
	public void batchSaveCacsensorData(List<Cacsensordata> cacsensordataSest,
			Cacsensor cacsensor) {

		for (int i = 0; i < cacsensordataSest.size(); i++) {

			Cacsensordata cacsensordata = cacsensordataSest.get(i);
			cacsensordata.setCacsensor(cacsensor.getId());

			entityManager.persist(cacsensordata);
		}
		entityManager.flush();
		System.out.println("保存sensor" + cacsensor.getName());
	}

	@Transactional
	public Cacsensor saveCacsensor(File file, Cac cac) {
		// TODO Auto-generated method stub
		return null;
	}
}
