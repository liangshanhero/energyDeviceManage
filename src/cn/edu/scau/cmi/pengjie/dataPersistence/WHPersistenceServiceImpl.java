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
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.dao.ProjectDAO;
import cn.edu.scau.cmi.dao.WhbuildingDAO;
import cn.edu.scau.cmi.dao.WhdatatypeDAO;
import cn.edu.scau.cmi.dao.WhdeviceDAO;
import cn.edu.scau.cmi.dao.WhdevicedataDAO;
import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdatatype;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.domain.Whdevicedata;
import cn.edu.scau.cmi.service.ProjectService;
import cn.edu.scau.cmi.service.WhbuildingService;
import cn.edu.scau.cmi.service.WhdatatypeService;
import cn.edu.scau.cmi.service.WhdeviceService;
import cn.edu.scau.cmi.service.WhdevicedataService;

@Service("WHPersistenceService")
@Transactional
public class WHPersistenceServiceImpl implements WHPersistenceService {

	@Autowired
	WhdatatypeDAO whdatatypeDAO;
	@Autowired
	WhdatatypeService Whdatatypeservice;

	@Autowired
	WhbuildingDAO buildingdao;

	@Autowired
	WhbuildingService buildingservice;
	@Autowired
	ProjectDAO projectdao;
	@Autowired
	ProjectService projectservice;
	@Autowired
	WhdeviceDAO whdevicedao;
	@Autowired
	WhdeviceService deviceService;
	@Autowired
	WhdevicedataDAO whdevicedatadao;

	@Autowired
	WhdevicedataService devicedataService;

	@PersistenceContext(unitName = "energydevice")
	EntityManager entityManager;

	@Transactional
	// 修改为返回值Whbuilding，然后将它的值当做目该项目下的建筑（唯一一个，要考虑到已经存在建筑的情况）
	public Whbuilding saveWhbuilding(String buildingName, Project project) {

		System.out.println("chazhao projetc" + projectdao.findAllProjects());

		Iterator<Whbuilding> whbuildings = buildingdao.findWhbuildingByName(
				buildingName).iterator();

		Whbuilding newbuilding = new Whbuilding();

		if (!whbuildings.hasNext()) {
			// 说明该数据库中已经存在了该信息

			newbuilding.setName(buildingName);
			newbuilding.setProject(project.getId());
			entityManager.persist(newbuilding);
			System.out.println("保存了该newbuilding!");

		} else {
			int needsaveIntoDb = 0;
			while (whbuildings.hasNext()) {
				//
				Whbuilding whbuilding = new Whbuilding();
				whbuilding = whbuildings.next();
				if (whbuilding.getProject().equals(project.getId())) {
					newbuilding = whbuilding;
					needsaveIntoDb = 1;
				}
			}

			if (needsaveIntoDb == 0) {
				// need insert into DB
				newbuilding.setName(buildingName);
				newbuilding.setProject(project.getId());
				entityManager.persist(newbuilding);
			}
		}
		newbuilding = buildingdao.store(newbuilding);
		return newbuilding;
	}

	@Transactional
	public Whdevice saveWhdevice(String deviceNumber, Whbuilding whbuilding) {
		// TODO Auto-generated method stub

		Whdevice whdevice = new Whdevice();

		Iterator<Whdevice> whdevices = whdevicedao.findWhdeviceByNumber(
				deviceNumber).iterator();
		// 如果whdevices判断为空值，则直接添加到数据库中；如果有该值了，则比较它的whbuilding是否相同，相同则不添加进去了。
		if (!whdevices.hasNext()) {
			whdevice.setNumber(deviceNumber);
			whdevice.setWhbuilding(whbuilding);
			entityManager.persist(whdevice);
			entityManager.flush();
			whdevice = whdevicedao.store(whdevice);
			System.out.println("这是新添加的 whdevice");
		} else {
			int isDeviceExist = 0;
			Whdevice device = new Whdevice();
			while (whdevices.hasNext()) {

				device = whdevices.next();
				// device.getWhbuilding()
				if (device.getWhbuilding().equals(whbuilding.getId())) {
					// 说明该设备已经存在了，不需要存进去了;
					isDeviceExist = 1;
					whdevice = device;
					System.out.println("已经存在了该 whdevice，不需要保存进数据库中");
				}
			}
			if (isDeviceExist == 0) {
				// 说明该设备的建筑没有存在，故需要存放进数据库中。
				whdevice.setNumber(deviceNumber);
				whdevice.setWhbuilding(whbuilding);
				entityManager.persist(whdevice);
				entityManager.flush();
				whdevice = whdevicedao.store(whdevice);
				System.out.println("保存了 whdevice");
			}
		}
		return whdevice;
	}

	@Transactional
	public Whdatatype saveWhdatatype(String whdatatype, Set<Whdevice> whdevices) {
		// TODO Auto-generated method stub
		Iterator<Whdatatype> whdatatypes = whdatatypeDAO.findWhdatatypeByName(
				whdatatype).iterator();
		Whdatatype datatype = new Whdatatype();

		if (!whdatatypes.hasNext()) {
			// 说明数据库中没有该数据的信息，则可以直接添加进数据库中

			datatype.setName(whdatatype);
			datatype.setRelativeWhdevices(whdevices);
			entityManager.persist(datatype);
			entityManager.flush();

			datatype = whdatatypeDAO.store(datatype);
			System.out.println("保存到数据库中！！！！");

		} else {
			datatype = whdatatypes.next();
			System.out.println("该whdatatype已经存在在数据库中了");
		}
		return datatype;
	}

	@Transactional
	public Whdevicedata saveWhdevicedata(Whdevice whdevice,
			Whdatatype whdatatype, Date time, String value, String isupdate,
			String isio) {
		// 获取所有的whdatatype

		Whdevicedata whdevicedata = new Whdevicedata();
		whdevicedata.setWhdevice(whdevice.getId());
		whdevicedata.setWhdatatype(whdatatype.getId());

		// 时间Date转换为Calendar
		Calendar WHRecordTime = Calendar.getInstance();
		WHRecordTime.setTime(time);
		whdevicedata.setTime(WHRecordTime.getTime());
		// setvalue()，设置它的
		whdevicedata.setValue(new BigDecimal(value));

		// 传递过来的isio字符串是float类型的字符串，需要转换
		String[] isIO = isio.split("\\.");
		int isIo = Integer.parseInt(isIO[0]);
		whdevicedata.setIsio(isIo);

		// 传递过来的isUpdate数据也是float类型的字符串，需要转换
		String[] isupdates = isupdate.split("\\.");
		int isUpdate = Integer.parseInt(isupdates[0]);
		whdevicedata.setIsupdate(isUpdate);

		entityManager.persist(whdevicedata);
		entityManager.flush();
		whdevicedata = whdevicedatadao.store(whdevicedata);

		System.out.println("保存了 whdevicedata!!!");
		return whdevicedata;
	}

	@Transactional
	public Whdatatype saveWhdatatype(String whdatatypeNme) {
		// TODO Auto-generated method stub
		Iterator<Whdatatype> whdatatypes = whdatatypeDAO.findWhdatatypeByName(
				whdatatypeNme).iterator();
		Whdatatype datatype = new Whdatatype();
		if (!whdatatypes.hasNext()) {

			datatype.setName(whdatatypeNme);
			entityManager.persist(datatype);
			entityManager.flush();
		} else {
			datatype = whdatatypes.next();
			System.out.println("该whdatatype已经存在数据库中了");
		}
		datatype = whdatatypeDAO.store(datatype);
		return datatype;
	}

	@Transactional
	public Whdevice saveWhdevice(String deviceNumber, Whbuilding whbuilding,
			Set<Whdatatype> whdatatypeSet) {
		Whdevice whdevice = new Whdevice();

		Iterator<Whdevice> whdevices = whdevicedao.findWhdeviceByNumber(
				deviceNumber).iterator();
		// 如果whdevices判断为空值，则直接添加到数据库中；如果有该值了，则比较它的whbuilding是否相同，相同则不添加进去了。
		if (!whdevices.hasNext()) {
			whdevice.setNumber(deviceNumber);
			whdevice.setWhbuilding(whbuilding);
			whdevice.setWhdatatypes(whdatatypeSet);
			entityManager.persist(whdevice);
			entityManager.flush();
			System.out.println("这是新添加的 whdevice");
		} else {
			int isDeviceExist = 0;
			Whdevice device = new Whdevice();
			while (whdevices.hasNext()) {

				device = whdevices.next();
				if (device.getWhbuilding().equals(whbuilding.getId())) {
					// 说明该设备已经存在了，不需要存进去了;
					isDeviceExist = 1;
					whdevice = device;
					System.out.println("已经存在了该 whdevice，不需要保存进数据库中");
				}
			}
			if (isDeviceExist == 0) {
				// 说明该设备的建筑没有存在，故需要存放进数据库中。
				whdevice.setNumber(deviceNumber);
				whdevice.setWhbuilding(whbuilding);
				whdevice.setWhdatatypes(whdatatypeSet);
				entityManager.persist(whdevice);
				entityManager.flush();
				System.out.println("保存了 whdevice");
			}
		}
		whdevice = whdevicedao.store(whdevice);
		return whdevice;
	}

	@Transactional
	public void batchSaveWhdevicedata(List<Whdevicedata> whdevicedataList) {
		System.out.println("开始保存时间：" + new Date());

		// TODO Auto-generated method stub
		for (int i = 0; i < whdevicedataList.size(); i++) {
			entityManager.persist(whdevicedataList.get(i));
			if (i % 50 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		entityManager.flush();
		entityManager.clear();
		System.out.println("save whdevicedataList");
		System.out.println("完成了保存的时间：" + new Date());
	}

}
