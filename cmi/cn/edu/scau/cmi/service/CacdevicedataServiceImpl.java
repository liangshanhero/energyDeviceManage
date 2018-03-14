package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacdevicedataDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacdevicedataService")
@Transactional
public class CacdevicedataServiceImpl implements CacdevicedataService {
	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacdeviceService cacdeviceService;
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@Autowired
	private CacrecordtimeService cacrecordtimeService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacdevicedataServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacdevicedata(Cacdevicedata cacdevicedata) {
		Cacdevicedata existingCacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedata.getCacdevice(),
						cacdevicedata.getCacrecordtime());
		if (existingCacdevicedata != null) {
			if (existingCacdevicedata != cacdevicedata) {
				existingCacdevicedata.setValue(cacdevicedata.getValue());
				existingCacdevicedata.setIsreport(cacdevicedata.getIsreport());
			}

			if (cacdevicedata.getRelativeCacdevice() != null) {
				entityManager.persist(cacdevicedata.getRelativeCacdevice());
				existingCacdevicedata.setRelativeCacdevice(cacdevicedata
						.getRelativeCacdevice());
			} else
				existingCacdevicedata.setRelativeCacdevice(null);
			if (cacdevicedata.getRelativeCacrecordtime() != null) {
				entityManager.persist(cacdevicedata.getRelativeCacrecordtime());
				existingCacdevicedata.setRelativeCacrecordtime(cacdevicedata
						.getRelativeCacrecordtime());
			} else
				existingCacdevicedata.setRelativeCacrecordtime(null);

			entityManager.persist(existingCacdevicedata);
		} else {
			entityManager.persist(cacdevicedata);
			cacdevicedata = cacdevicedataDAO.store(cacdevicedata);
		}
		cacdevicedataDAO.flush();
	}

	@Transactional
	public Cacdevicedata saveCacdevicedataCacdevice(Integer cacdevice,
			Integer cacrecordtime, Cacdevice related_cacdevice) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime, -1, -1);
		Cacdevice existingCacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(related_cacdevice.getId());
		if (existingCacdevice != null) {
			existingCacdevice.setId(related_cacdevice.getId());
			existingCacdevice.setName(related_cacdevice.getName());
			existingCacdevice.setUnit(related_cacdevice.getUnit());
			related_cacdevice = existingCacdevice;
		} else {
			related_cacdevice = cacdeviceDAO.store(related_cacdevice);
			cacdeviceDAO.flush();
		}
		cacdevicedata.setRelativeCacdevice(related_cacdevice);
		related_cacdevice.getRelativeCacdevicedatas().add(cacdevicedata);
		cacdevicedata = cacdevicedataDAO.store(cacdevicedata);
		cacdevicedataDAO.flush();

		related_cacdevice = cacdeviceDAO.store(related_cacdevice);
		cacdeviceDAO.flush();

		return cacdevicedata;
	}

	@Transactional
	public Cacdevicedata saveCacdevicedataCacrecordtime(Integer cacdevice,
			Integer cacrecordtime, Cacrecordtime related_cacrecordtime) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime, -1, -1);
		Cacrecordtime existingCacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime.getId());
		if (existingCacrecordtime != null) {
			existingCacrecordtime.setId(related_cacrecordtime.getId());
			existingCacrecordtime.setRecordTime(related_cacrecordtime
					.getRecordTime());
			existingCacrecordtime.setWatchkeeper(related_cacrecordtime
					.getWatchkeeper());
			related_cacrecordtime = existingCacrecordtime;
		} else {
			related_cacrecordtime = cacrecordtimeDAO
					.store(related_cacrecordtime);
			cacrecordtimeDAO.flush();
		}
		cacdevicedata.setRelativeCacrecordtime(related_cacrecordtime);
		related_cacrecordtime.getRelativeCacdevicedatas().add(cacdevicedata);
		cacdevicedata = cacdevicedataDAO.store(cacdevicedata);
		cacdevicedataDAO.flush();

		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);
		cacrecordtimeDAO.flush();

		return cacdevicedata;
	}

	@Transactional
	public void deleteCacdevicedata(Cacdevicedata cacdevicedata) {

		cacdevicedata.setRelativeCacdevice(null);
		cacdevicedata.setRelativeCacrecordtime(null);

		entityManager.persist(cacdevicedata);
		cacdevicedataDAO.remove(cacdevicedata);
		cacdevicedataDAO.flush();
	}

	@Transactional
	public Cacdevicedata deleteCacdevicedataCacdevice(
			Integer cacdevicedata_cacdevice,
			Integer cacdevicedata_cacrecordtime, Integer related_cacdevice_id) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, -1, -1);
		Cacdevice related_cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				related_cacdevice_id, -1, -1);
		cacdevicedata.setRelativeCacdevice(null);
		related_cacdevice.getRelativeCacdevicedatas().remove(cacdevicedata);
		cacdevicedata = cacdevicedataDAO.store(cacdevicedata);
		cacdevicedataDAO.flush();
		related_cacdevice = cacdeviceDAO.store(related_cacdevice);

		cacdeviceDAO.flush();
		return cacdevicedata;
	}

	@Transactional
	public Cacdevicedata deleteCacdevicedataCacrecordtime(
			Integer cacdevicedata_cacdevice,
			Integer cacdevicedata_cacrecordtime,
			Integer related_cacrecordtime_id) {
		Cacdevicedata cacdevicedata = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(cacdevicedata_cacdevice,
						cacdevicedata_cacrecordtime, -1, -1);
		Cacrecordtime related_cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id, -1, -1);
		cacdevicedata.setRelativeCacrecordtime(null);
		related_cacrecordtime.getRelativeCacdevicedatas().remove(cacdevicedata);
		cacdevicedata = cacdevicedataDAO.store(cacdevicedata);
		cacdevicedataDAO.flush();
		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);

		cacrecordtimeDAO.flush();
		return cacdevicedata;
	}

	@Transactional
	public Set<Cacdevicedata> loadCacdevicedatas() {
		return cacdevicedataDAO.findAllCacdevicedatas();

	}

	@Transactional
	public Set<Cacdevicedata> loadCacdevicedatas(int index, int size) {
		return cacdevicedataDAO.findAllCacdevicedatas(index, size);
	}

	@Transactional
	public List<Cacdevicedata> findAllCacdevicedatas(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacdevicedata>(
				cacdevicedataDAO.findAllCacdevicedatas(startResult, maxRows));
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice

	, Integer cacrecordtime

	) {
		return cacdevicedataDAO.findCacdevicedataByPrimaryKey(cacdevice,
				cacrecordtime);
	}

	@Transactional
	public Integer countcacdevicedatas() {
		return ((Long) cacdevicedataDAO.createQuerySingleResult(
				"select count(o) from Cacdevicedata o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeCacdeviceCacdevicedatas(Integer cacdevice) {
		String sql = "select count(*) from Cacdevicedata where cacdevice="
				+ cacdevice;
		return ((Long) cacdevicedataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeCacrecordtimeCacdevicedatas(
			Integer cacrecordtime) {
		String sql = "select count(*) from Cacdevicedata where cacrecordtime="
				+ cacrecordtime;
		return ((Long) cacdevicedataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
