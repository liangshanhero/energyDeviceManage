package cn.edu.scau.cmi.pengjie.dataPersistence;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.dao.LedbuildingDAO;
import cn.edu.scau.cmi.dao.LedmeterDAO;
import cn.edu.scau.cmi.dao.LedmeterdataDAO;
import cn.edu.scau.cmi.domain.Ledbuilding;
import cn.edu.scau.cmi.domain.Ledmeter;
import cn.edu.scau.cmi.domain.Ledmeterdata;
import cn.edu.scau.cmi.domain.Project;

@Service("LEDPersistenceService")
@Transactional
public class LEDPersistenceServiceImpl implements LEDPersistenceService {

	@Autowired
	LedmeterdataDAO ledmeterdatadao;
	@Autowired
	LedmeterDAO ledmeterdao;
	@Autowired
	LedbuildingDAO ledbuildingdao;

	@PersistenceContext(unitName = "energydevice")
	EntityManager entityManager;

	@Transactional
	public Ledmeterdata saveLedmeterdata(BigDecimal value, Date date,
			Ledmeter ledmeter) {

		Ledmeterdata ledmeterdata = new Ledmeterdata();

		Calendar searchTime = Calendar.getInstance();
		searchTime.setTime(date);
		Iterator<Ledmeterdata> ledmeterdatas = ledmeterdatadao
				.findLedmeterdataByTime(searchTime.getTime()).iterator();

		if (!ledmeterdatas.hasNext()) {
			// 说明该日期的数据没有存放进去，故要保存该数据
			ledmeterdata.setValue(value);
			// transfrom time
			Calendar time = Calendar.getInstance();
			time.setTime(date);
			ledmeterdata.setTime(time.getTime());
			ledmeterdata.setLedmeter(ledmeter.getId());
			entityManager.persist(ledmeterdata);
			entityManager.flush();
		} else {
			int needInsertInto = 0;
			while (ledmeterdatas.hasNext()) {
				Ledmeterdata Ledmeterdata = new Ledmeterdata();
				Ledmeterdata = ledmeterdatas.next();
				if (Ledmeterdata.getLedmeter().equals(ledmeter)) {
					System.out.println("相同的值：" + ledmeter);
					needInsertInto = 1;
					ledmeterdata = Ledmeterdata;
				}
			}
			if (needInsertInto == 0) {
				ledmeterdata.setValue(value);
				// transfrom time
				Calendar time = Calendar.getInstance();
				time.setTime(date);
				ledmeterdata.setTime(time.getTime());
				ledmeterdata.setLedmeter(ledmeter.getId());
				entityManager.persist(ledmeterdata);
				entityManager.flush();
				System.out.println("保存里的数据：" + ledmeterdata);
			}
		}
		ledmeterdata = ledmeterdatadao.store(ledmeterdata);
		return ledmeterdata;
	}

	@Transactional
	public Ledmeter saveLedmeter(String number, Ledbuilding ledbuilding,
			String well, int storey, BigDecimal totalamount,
			BigDecimal totaldays) {
		// TODO Auto-generated method stub

		Ledmeter ledmeter = new Ledmeter();

		Iterator<Ledmeter> ledmeters = ledmeterdao.findLedmeterByNumber(number)
				.iterator();

		if (!ledmeters.hasNext()) {
			// 表示这是个新的数据，需要保存到数据库中
			ledmeter.setNumber(number);
			ledmeter.setStorey(storey);
			ledmeter.setWell(well);
			ledmeter.setLedbuilding(ledbuilding.getId());
			ledmeter.setTotalamout(totalamount);
			ledmeter.setTotaldays(totaldays);
			entityManager.persist(ledmeter);
			entityManager.flush();
			ledmeter = ledmeterdao.store(ledmeter);
		} else {
			ledmeter = ledmeters.next();
		}

		return ledmeter;
	}

	@Transactional
	public Ledbuilding saveLedbuilding(String name, Project project, int well,
			int storey) {
		// 该过程是储存过程。

		Ledbuilding ledbuilding = new Ledbuilding();

		Iterator<Ledbuilding> ledbuildings = ledbuildingdao
				.findLedbuildingByName(name).iterator();

		if (!ledbuildings.hasNext()) {
			// 通过名称查找到的建筑一般是唯一的，所以可以判断是否为空；没有下一个，表示是新添加的建筑
			ledbuilding.setName(name);
			ledbuilding.setProject(project.getId());
			ledbuilding.setWell(well);
			ledbuilding.setStorey(storey);
			entityManager.persist(ledbuilding);
			entityManager.flush();
			ledbuilding = ledbuildingdao.store(ledbuilding);
		} else {
			// 表示该建筑已经存在了，可以直接返回结果给用户
			ledbuilding = ledbuildings.next();
		}
		return ledbuilding;
	}

}
