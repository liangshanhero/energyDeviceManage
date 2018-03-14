package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacmalfunctionDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacmalfunctionService")
@Transactional
public class CacmalfunctionServiceImpl implements CacmalfunctionService {
	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

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

	public CacmalfunctionServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacmalfunction(Cacmalfunction cacmalfunction) {
		Cacmalfunction existingCacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						cacmalfunction.getCacrecordtime(),
						cacmalfunction.getCacdevice());
		if (existingCacmalfunction != null) {
			if (existingCacmalfunction != cacmalfunction) {
				existingCacmalfunction.setStatus(cacmalfunction.getStatus());
			}

			if (cacmalfunction.getRelativeCacdevice() != null) {
				entityManager.persist(cacmalfunction.getRelativeCacdevice());
				existingCacmalfunction.setRelativeCacdevice(cacmalfunction
						.getRelativeCacdevice());
			} else
				existingCacmalfunction.setRelativeCacdevice(null);
			if (cacmalfunction.getRelativeCacrecordtime() != null) {
				entityManager
						.persist(cacmalfunction.getRelativeCacrecordtime());
				existingCacmalfunction.setRelativeCacrecordtime(cacmalfunction
						.getRelativeCacrecordtime());
			} else
				existingCacmalfunction.setRelativeCacrecordtime(null);

			entityManager.persist(existingCacmalfunction);
		} else {
			entityManager.persist(cacmalfunction);
			cacmalfunction = cacmalfunctionDAO.store(cacmalfunction);
		}
		cacmalfunctionDAO.flush();
	}

	@Transactional
	public Cacmalfunction saveCacmalfunctionCacdevice(Integer cacrecordtime,
			Integer cacdevice, Cacdevice related_cacdevice) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice, -1,
						-1);
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
		cacmalfunction.setRelativeCacdevice(related_cacdevice);
		related_cacdevice.getRelativeCacmalfunctions().add(cacmalfunction);
		cacmalfunction = cacmalfunctionDAO.store(cacmalfunction);
		cacmalfunctionDAO.flush();

		related_cacdevice = cacdeviceDAO.store(related_cacdevice);
		cacdeviceDAO.flush();

		return cacmalfunction;
	}

	@Transactional
	public Cacmalfunction saveCacmalfunctionCacrecordtime(
			Integer cacrecordtime, Integer cacdevice,
			Cacrecordtime related_cacrecordtime) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice, -1,
						-1);
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
		cacmalfunction.setRelativeCacrecordtime(related_cacrecordtime);
		related_cacrecordtime.getRelativeCacmalfunctions().add(cacmalfunction);
		cacmalfunction = cacmalfunctionDAO.store(cacmalfunction);
		cacmalfunctionDAO.flush();

		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);
		cacrecordtimeDAO.flush();

		return cacmalfunction;
	}

	@Transactional
	public void deleteCacmalfunction(Cacmalfunction cacmalfunction) {

		cacmalfunction.setRelativeCacdevice(null);
		cacmalfunction.setRelativeCacrecordtime(null);

		entityManager.persist(cacmalfunction);
		cacmalfunctionDAO.remove(cacmalfunction);
		cacmalfunctionDAO.flush();
	}

	@Transactional
	public Cacmalfunction deleteCacmalfunctionCacdevice(
			Integer cacmalfunction_cacrecordtime,
			Integer cacmalfunction_cacdevice, Integer related_cacdevice_id) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice, -1, -1);
		Cacdevice related_cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				related_cacdevice_id, -1, -1);
		cacmalfunction.setRelativeCacdevice(null);
		related_cacdevice.getRelativeCacmalfunctions().remove(cacmalfunction);
		cacmalfunction = cacmalfunctionDAO.store(cacmalfunction);
		cacmalfunctionDAO.flush();
		related_cacdevice = cacdeviceDAO.store(related_cacdevice);

		cacdeviceDAO.flush();
		return cacmalfunction;
	}

	@Transactional
	public Cacmalfunction deleteCacmalfunctionCacrecordtime(
			Integer cacmalfunction_cacrecordtime,
			Integer cacmalfunction_cacdevice, Integer related_cacrecordtime_id) {
		Cacmalfunction cacmalfunction = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(cacmalfunction_cacrecordtime,
						cacmalfunction_cacdevice, -1, -1);
		Cacrecordtime related_cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id, -1, -1);
		cacmalfunction.setRelativeCacrecordtime(null);
		related_cacrecordtime.getRelativeCacmalfunctions().remove(
				cacmalfunction);
		cacmalfunction = cacmalfunctionDAO.store(cacmalfunction);
		cacmalfunctionDAO.flush();
		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);

		cacrecordtimeDAO.flush();
		return cacmalfunction;
	}

	@Transactional
	public Set<Cacmalfunction> loadCacmalfunctions() {
		return cacmalfunctionDAO.findAllCacmalfunctions();

	}

	@Transactional
	public Set<Cacmalfunction> loadCacmalfunctions(int index, int size) {
		return cacmalfunctionDAO.findAllCacmalfunctions(index, size);
	}

	@Transactional
	public List<Cacmalfunction> findAllCacmalfunctions(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacmalfunction>(
				cacmalfunctionDAO.findAllCacmalfunctions(startResult, maxRows));
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime

	, Integer cacdevice

	) {
		return cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(cacrecordtime,
				cacdevice);
	}

	@Transactional
	public Integer countcacmalfunctions() {
		return ((Long) cacmalfunctionDAO.createQuerySingleResult(
				"select count(o) from Cacmalfunction o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeCacdeviceCacmalfunctions(Integer cacdevice) {
		String sql = "select count(*) from Cacmalfunction where cacdevice="
				+ cacdevice;
		return ((Long) cacmalfunctionDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeCacrecordtimeCacmalfunctions(
			Integer cacrecordtime) {
		String sql = "select count(*) from Cacmalfunction where cacrecordtime="
				+ cacrecordtime;
		return ((Long) cacmalfunctionDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
