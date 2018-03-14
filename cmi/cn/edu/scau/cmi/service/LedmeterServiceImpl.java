package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.LedmeterDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("LedmeterService")
@Transactional
public class LedmeterServiceImpl implements LedmeterService {
	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedbuildingService ledbuildingService;

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterdataService ledmeterdataService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedmeterServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveLedmeter(Ledmeter ledmeter) {
		Ledmeter existingLedmeter = ledmeterDAO
				.findLedmeterByPrimaryKey(ledmeter.getId());
		if (existingLedmeter != null) {
			if (existingLedmeter != ledmeter) {
				existingLedmeter.setNumber(ledmeter.getNumber());
				existingLedmeter.setWell(ledmeter.getWell());
				existingLedmeter.setStorey(ledmeter.getStorey());
				existingLedmeter.setTotalamout(ledmeter.getTotalamout());
				existingLedmeter.setTotaldays(ledmeter.getTotaldays());
			}

			if (ledmeter.getRelativeLedbuilding() != null) {
				entityManager.persist(ledmeter.getRelativeLedbuilding());
				existingLedmeter.setRelativeLedbuilding(ledmeter
						.getRelativeLedbuilding());
			} else
				existingLedmeter.setRelativeLedbuilding(null);

			if (ledmeter.getRelativeLedmeterdatas() != null) {
				for (Ledmeterdata relativeLedmeterdata : ledmeter
						.getRelativeLedmeterdatas()) {
					relativeLedmeterdata.setRelativeLedmeter(existingLedmeter);
					entityManager.persist(relativeLedmeterdata);
				}
				existingLedmeter.setRelativeLedmeterdatas(ledmeter
						.getRelativeLedmeterdatas());
			} else
				existingLedmeter.setRelativeLedmeterdatas(null);
			entityManager.persist(existingLedmeter);
		} else {
			entityManager.persist(ledmeter);
			ledmeter = ledmeterDAO.store(ledmeter);
		}
		ledmeterDAO.flush();
	}

	@Transactional
	public Ledmeter saveLedmeterLedbuilding(Integer id,
			Ledbuilding related_ledbuilding) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(id, -1, -1);
		Ledbuilding existingLedbuilding = ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuilding.getId());
		if (existingLedbuilding != null) {
			existingLedbuilding.setId(related_ledbuilding.getId());
			existingLedbuilding.setName(related_ledbuilding.getName());
			existingLedbuilding.setWell(related_ledbuilding.getWell());
			existingLedbuilding.setStorey(related_ledbuilding.getStorey());
			related_ledbuilding = existingLedbuilding;
		} else {
			related_ledbuilding = ledbuildingDAO.store(related_ledbuilding);
			ledbuildingDAO.flush();
		}
		ledmeter.setRelativeLedbuilding(related_ledbuilding);
		related_ledbuilding.getRelativeLedmeters().add(ledmeter);
		ledmeter = ledmeterDAO.store(ledmeter);
		ledmeterDAO.flush();

		related_ledbuilding = ledbuildingDAO.store(related_ledbuilding);
		ledbuildingDAO.flush();

		return ledmeter;
	}

	@Transactional
	public Ledmeter saveLedmeterLedmeterdatas(Integer id,
			Ledmeterdata related_ledmeterdatas) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(id, -1, -1);
		Ledmeterdata existingLedmeterdatas = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(related_ledmeterdatas.getId());
		if (existingLedmeterdatas != null) {
			existingLedmeterdatas.setId(related_ledmeterdatas.getId());
			existingLedmeterdatas.setValue(related_ledmeterdatas.getValue());
			existingLedmeterdatas.setTime(related_ledmeterdatas.getTime());
			related_ledmeterdatas = existingLedmeterdatas;
			entityManager.persist(related_ledmeterdatas);
		} else {
			entityManager.persist(related_ledmeterdatas);
			ledmeterdataDAO.flush();
		}
		entityManager.persist(ledmeter);
		related_ledmeterdatas.setRelativeLedmeter(ledmeter);
		ledmeter.getRelativeLedmeterdatas().add(related_ledmeterdatas);
		entityManager.persist(ledmeter);
		entityManager.persist(related_ledmeterdatas);
		ledmeterDAO.flush();

		return ledmeter;
	}

	@Transactional
	public void deleteLedmeter(Ledmeter ledmeter) {

		ledmeter.setRelativeLedbuilding(null);

		Set<Ledmeterdata> ledmeterdataSet = ledmeter.getRelativeLedmeterdatas();
		ledmeter.setRelativeLedmeterdatas(null);
		entityManager.persist(ledmeter);
		ledmeterDAO.remove(ledmeter);
		ledmeterDAO.flush();
	}

	@Transactional
	public Ledmeter deleteLedmeterLedbuilding(Integer ledmeter_id,
			Integer related_ledbuilding_id) {
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id,
				-1, -1);
		Ledbuilding related_ledbuilding = ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuilding_id, -1, -1);
		ledmeter.setRelativeLedbuilding(null);
		related_ledbuilding.getRelativeLedmeters().remove(ledmeter);
		ledmeter = ledmeterDAO.store(ledmeter);
		ledmeterDAO.flush();
		related_ledbuilding = ledbuildingDAO.store(related_ledbuilding);

		ledbuildingDAO.flush();
		return ledmeter;
	}

	@Transactional
	public Ledmeter deleteLedmeterLedmeterdatas(Integer ledmeter_id,
			Integer related_ledmeterdatas_id) {
		Ledmeterdata related_ledmeterdatas = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(related_ledmeterdatas_id, -1, -1);
		Ledmeter ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(ledmeter_id,
				-1, -1);
		Set<Ledmeterdata> ledmeterdataSet = ledmeter.getRelativeLedmeterdatas();
		Ledmeterdata teache = new Ledmeterdata();
		if (ledmeterdataSet.size() > 0)
			for (Ledmeterdata th : ledmeterdataSet) {
				if (th == related_ledmeterdatas) {
					teache = related_ledmeterdatas;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeLedmeter(null);
			ledmeter.getRelativeLedmeterdatas().remove(teache);
		}

		entityManager.persist(related_ledmeterdatas);
		entityManager.persist(ledmeter);
		ledmeterDAO.flush();
		return ledmeter;
	}

	@Transactional
	public Set<Ledmeter> loadLedmeters() {
		return ledmeterDAO.findAllLedmeters();

	}

	@Transactional
	public Set<Ledmeter> loadLedmeters(int index, int size) {
		return ledmeterDAO.findAllLedmeters(index, size);
	}

	@Transactional
	public List<Ledmeter> findAllLedmeters(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Ledmeter>(ledmeterDAO.findAllLedmeters(
				startResult, maxRows));
	}

	@Transactional
	public Ledmeter findLedmeterByPrimaryKey(Integer id

	) {
		return ledmeterDAO.findLedmeterByPrimaryKey(id);
	}

	@Transactional
	public Integer countledmeters() {
		return ((Long) ledmeterDAO.createQuerySingleResult(
				"select count(o) from Ledmeter o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeLedbuildingLedmeters(Integer ledbuilding) {
		String sql = "select count(*) from Ledmeter where ledbuilding="
				+ ledbuilding;
		return ((Long) ledmeterDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
