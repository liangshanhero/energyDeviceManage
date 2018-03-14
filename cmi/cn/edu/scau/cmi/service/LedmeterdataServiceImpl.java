package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.LedmeterdataDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("LedmeterdataService")
@Transactional
public class LedmeterdataServiceImpl implements LedmeterdataService {
	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedmeterService ledmeterService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedmeterdataServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveLedmeterdata(Ledmeterdata ledmeterdata) {
		Ledmeterdata existingLedmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(ledmeterdata.getId());
		if (existingLedmeterdata != null) {
			if (existingLedmeterdata != ledmeterdata) {
				existingLedmeterdata.setValue(ledmeterdata.getValue());
				existingLedmeterdata.setTime(ledmeterdata.getTime());
			}

			if (ledmeterdata.getRelativeLedmeter() != null) {
				entityManager.persist(ledmeterdata.getRelativeLedmeter());
				existingLedmeterdata.setRelativeLedmeter(ledmeterdata
						.getRelativeLedmeter());
			} else
				existingLedmeterdata.setRelativeLedmeter(null);

			entityManager.persist(existingLedmeterdata);
		} else {
			entityManager.persist(ledmeterdata);
			ledmeterdata = ledmeterdataDAO.store(ledmeterdata);
		}
		ledmeterdataDAO.flush();
	}

	@Transactional
	public Ledmeterdata saveLedmeterdataLedmeter(Integer id,
			Ledmeter related_ledmeter) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(id, -1, -1);
		Ledmeter existingLedmeter = ledmeterDAO
				.findLedmeterByPrimaryKey(related_ledmeter.getId());
		if (existingLedmeter != null) {
			existingLedmeter.setId(related_ledmeter.getId());
			existingLedmeter.setNumber(related_ledmeter.getNumber());
			existingLedmeter.setWell(related_ledmeter.getWell());
			existingLedmeter.setStorey(related_ledmeter.getStorey());
			existingLedmeter.setTotalamout(related_ledmeter.getTotalamout());
			existingLedmeter.setTotaldays(related_ledmeter.getTotaldays());
			related_ledmeter = existingLedmeter;
		} else {
			related_ledmeter = ledmeterDAO.store(related_ledmeter);
			ledmeterDAO.flush();
		}
		ledmeterdata.setRelativeLedmeter(related_ledmeter);
		related_ledmeter.getRelativeLedmeterdatas().add(ledmeterdata);
		ledmeterdata = ledmeterdataDAO.store(ledmeterdata);
		ledmeterdataDAO.flush();

		related_ledmeter = ledmeterDAO.store(related_ledmeter);
		ledmeterDAO.flush();

		return ledmeterdata;
	}

	@Transactional
	public void deleteLedmeterdata(Ledmeterdata ledmeterdata) {

		ledmeterdata.setRelativeLedmeter(null);

		entityManager.persist(ledmeterdata);
		ledmeterdataDAO.remove(ledmeterdata);
		ledmeterdataDAO.flush();
	}

	@Transactional
	public Ledmeterdata deleteLedmeterdataLedmeter(Integer ledmeterdata_id,
			Integer related_ledmeter_id) {
		Ledmeterdata ledmeterdata = ledmeterdataDAO
				.findLedmeterdataByPrimaryKey(ledmeterdata_id, -1, -1);
		Ledmeter related_ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(
				related_ledmeter_id, -1, -1);
		ledmeterdata.setRelativeLedmeter(null);
		related_ledmeter.getRelativeLedmeterdatas().remove(ledmeterdata);
		ledmeterdata = ledmeterdataDAO.store(ledmeterdata);
		ledmeterdataDAO.flush();
		related_ledmeter = ledmeterDAO.store(related_ledmeter);

		ledmeterDAO.flush();
		return ledmeterdata;
	}

	@Transactional
	public Set<Ledmeterdata> loadLedmeterdatas() {
		return ledmeterdataDAO.findAllLedmeterdatas();

	}

	@Transactional
	public Set<Ledmeterdata> loadLedmeterdatas(int index, int size) {
		return ledmeterdataDAO.findAllLedmeterdatas(index, size);
	}

	@Transactional
	public List<Ledmeterdata> findAllLedmeterdatas(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Ledmeterdata>(
				ledmeterdataDAO.findAllLedmeterdatas(startResult, maxRows));
	}

	@Transactional
	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id

	) {
		return ledmeterdataDAO.findLedmeterdataByPrimaryKey(id);
	}

	@Transactional
	public Integer countledmeterdatas() {
		return ((Long) ledmeterdataDAO.createQuerySingleResult(
				"select count(o) from Ledmeterdata o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeLedmeterLedmeterdatas(Integer ledmeter) {
		String sql = "select count(*) from Ledmeterdata where ledmeter="
				+ ledmeter;
		return ((Long) ledmeterdataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
