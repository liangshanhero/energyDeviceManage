package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacrecordtimeDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacrecordtimeService")
@Transactional
public class CacrecordtimeServiceImpl implements CacrecordtimeService {
	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacrecordtimeServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacrecordtime(Cacrecordtime cacrecordtime) {
		Cacrecordtime existingCacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime.getId());
		if (existingCacrecordtime != null) {
			if (existingCacrecordtime != cacrecordtime) {
				existingCacrecordtime.setRecordTime(cacrecordtime
						.getRecordTime());
				existingCacrecordtime.setWatchkeeper(cacrecordtime
						.getWatchkeeper());
			}

			if (cacrecordtime.getRelativeCacdevicedatas() != null) {
				for (Cacdevicedata relativeCacdevicedata : cacrecordtime
						.getRelativeCacdevicedatas()) {
					relativeCacdevicedata
							.setRelativeCacrecordtime(existingCacrecordtime);
					entityManager.persist(relativeCacdevicedata);
				}
				existingCacrecordtime.setRelativeCacdevicedatas(cacrecordtime
						.getRelativeCacdevicedatas());
			} else
				existingCacrecordtime.setRelativeCacdevicedatas(null);
			if (cacrecordtime.getRelativeCacmalfunctions() != null) {
				for (Cacmalfunction relativeCacmalfunction : cacrecordtime
						.getRelativeCacmalfunctions()) {
					relativeCacmalfunction
							.setRelativeCacrecordtime(existingCacrecordtime);
					entityManager.persist(relativeCacmalfunction);
				}
				existingCacrecordtime.setRelativeCacmalfunctions(cacrecordtime
						.getRelativeCacmalfunctions());
			} else
				existingCacrecordtime.setRelativeCacmalfunctions(null);
			if (cacrecordtime.getRelativeCacsensordatas() != null) {
				for (Cacsensordata relativeCacsensordata : cacrecordtime
						.getRelativeCacsensordatas()) {
					relativeCacsensordata
							.setRelativeCacrecordtime(existingCacrecordtime);
					entityManager.persist(relativeCacsensordata);
				}
				existingCacrecordtime.setRelativeCacsensordatas(cacrecordtime
						.getRelativeCacsensordatas());
			} else
				existingCacrecordtime.setRelativeCacsensordatas(null);
			entityManager.persist(existingCacrecordtime);
		} else {
			entityManager.persist(cacrecordtime);
			cacrecordtime = cacrecordtimeDAO.store(cacrecordtime);
		}
		cacrecordtimeDAO.flush();
	}

	@Transactional
	public Cacrecordtime saveCacrecordtimeCacdevicedatas(Integer id,
			Cacdevicedata related_cacdevicedatas) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id, -1, -1);
		Cacdevicedata existingCacdevicedatas = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						related_cacdevicedatas.getCacdevice(),
						related_cacdevicedatas.getCacrecordtime());
		if (existingCacdevicedatas != null) {
			existingCacdevicedatas.setCacdevice(related_cacdevicedatas
					.getCacdevice());
			existingCacdevicedatas.setCacrecordtime(related_cacdevicedatas
					.getCacrecordtime());
			existingCacdevicedatas.setValue(related_cacdevicedatas.getValue());
			existingCacdevicedatas.setIsreport(related_cacdevicedatas
					.getIsreport());
			related_cacdevicedatas = existingCacdevicedatas;
			entityManager.persist(related_cacdevicedatas);
		} else {
			entityManager.persist(related_cacdevicedatas);
			cacdevicedataDAO.flush();
		}
		entityManager.persist(cacrecordtime);
		related_cacdevicedatas.setRelativeCacrecordtime(cacrecordtime);
		cacrecordtime.getRelativeCacdevicedatas().add(related_cacdevicedatas);
		entityManager.persist(cacrecordtime);
		entityManager.persist(related_cacdevicedatas);
		cacrecordtimeDAO.flush();

		return cacrecordtime;
	}

	@Transactional
	public Cacrecordtime saveCacrecordtimeCacmalfunctions(Integer id,
			Cacmalfunction related_cacmalfunctions) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id, -1, -1);
		Cacmalfunction existingCacmalfunctions = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						related_cacmalfunctions.getCacrecordtime(),
						related_cacmalfunctions.getCacdevice());
		if (existingCacmalfunctions != null) {
			existingCacmalfunctions.setCacrecordtime(related_cacmalfunctions
					.getCacrecordtime());
			existingCacmalfunctions.setCacdevice(related_cacmalfunctions
					.getCacdevice());
			existingCacmalfunctions.setStatus(related_cacmalfunctions
					.getStatus());
			related_cacmalfunctions = existingCacmalfunctions;
			entityManager.persist(related_cacmalfunctions);
		} else {
			entityManager.persist(related_cacmalfunctions);
			cacmalfunctionDAO.flush();
		}
		entityManager.persist(cacrecordtime);
		related_cacmalfunctions.setRelativeCacrecordtime(cacrecordtime);
		cacrecordtime.getRelativeCacmalfunctions().add(related_cacmalfunctions);
		entityManager.persist(cacrecordtime);
		entityManager.persist(related_cacmalfunctions);
		cacrecordtimeDAO.flush();

		return cacrecordtime;
	}

	@Transactional
	public Cacrecordtime saveCacrecordtimeCacsensordatas(Integer id,
			Cacsensordata related_cacsensordatas) {
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(id, -1, -1);
		Cacsensordata existingCacsensordatas = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						related_cacsensordatas.getCacrecordtime(),
						related_cacsensordatas.getCacsensor());
		if (existingCacsensordatas != null) {
			existingCacsensordatas.setCacrecordtime(related_cacsensordatas
					.getCacrecordtime());
			existingCacsensordatas.setCacsensor(related_cacsensordatas
					.getCacsensor());
			existingCacsensordatas.setValue(related_cacsensordatas.getValue());
			existingCacsensordatas.setIsreport(related_cacsensordatas
					.getIsreport());
			related_cacsensordatas = existingCacsensordatas;
			entityManager.persist(related_cacsensordatas);
		} else {
			entityManager.persist(related_cacsensordatas);
			cacsensordataDAO.flush();
		}
		entityManager.persist(cacrecordtime);
		related_cacsensordatas.setRelativeCacrecordtime(cacrecordtime);
		cacrecordtime.getRelativeCacsensordatas().add(related_cacsensordatas);
		entityManager.persist(cacrecordtime);
		entityManager.persist(related_cacsensordatas);
		cacrecordtimeDAO.flush();

		return cacrecordtime;
	}

	@Transactional
	public void deleteCacrecordtime(Cacrecordtime cacrecordtime) {

		Set<Cacdevicedata> cacdevicedataSet = cacrecordtime
				.getRelativeCacdevicedatas();
		cacrecordtime.setRelativeCacdevicedatas(null);
		Set<Cacmalfunction> cacmalfunctionSet = cacrecordtime
				.getRelativeCacmalfunctions();
		cacrecordtime.setRelativeCacmalfunctions(null);
		Set<Cacsensordata> cacsensordataSet = cacrecordtime
				.getRelativeCacsensordatas();
		cacrecordtime.setRelativeCacsensordatas(null);
		entityManager.persist(cacrecordtime);
		cacrecordtimeDAO.remove(cacrecordtime);
		cacrecordtimeDAO.flush();
	}

	@Transactional
	public Cacrecordtime deleteCacrecordtimeCacdevicedatas(
			Integer cacrecordtime_id, Integer related_cacdevicedatas_cacdevice,
			Integer related_cacdevicedatas_cacrecordtime) {
		Cacdevicedata related_cacdevicedatas = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						related_cacdevicedatas_cacdevice,
						related_cacdevicedatas_cacrecordtime, -1, -1);
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		Set<Cacdevicedata> cacdevicedataSet = cacrecordtime
				.getRelativeCacdevicedatas();
		Cacdevicedata teache = new Cacdevicedata();
		if (cacdevicedataSet.size() > 0)
			for (Cacdevicedata th : cacdevicedataSet) {
				if (th == related_cacdevicedatas) {
					teache = related_cacdevicedatas;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCacrecordtime(null);
			cacrecordtime.getRelativeCacdevicedatas().remove(teache);
		}

		entityManager.persist(related_cacdevicedatas);
		entityManager.persist(cacrecordtime);
		cacrecordtimeDAO.flush();
		return cacrecordtime;
	}

	@Transactional
	public Cacrecordtime deleteCacrecordtimeCacmalfunctions(
			Integer cacrecordtime_id,
			Integer related_cacmalfunctions_cacrecordtime,
			Integer related_cacmalfunctions_cacdevice) {
		Cacmalfunction related_cacmalfunctions = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						related_cacmalfunctions_cacrecordtime,
						related_cacmalfunctions_cacdevice, -1, -1);
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		Set<Cacmalfunction> cacmalfunctionSet = cacrecordtime
				.getRelativeCacmalfunctions();
		Cacmalfunction teache = new Cacmalfunction();
		if (cacmalfunctionSet.size() > 0)
			for (Cacmalfunction th : cacmalfunctionSet) {
				if (th == related_cacmalfunctions) {
					teache = related_cacmalfunctions;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCacrecordtime(null);
			cacrecordtime.getRelativeCacmalfunctions().remove(teache);
		}

		entityManager.persist(related_cacmalfunctions);
		entityManager.persist(cacrecordtime);
		cacrecordtimeDAO.flush();
		return cacrecordtime;
	}

	@Transactional
	public Cacrecordtime deleteCacrecordtimeCacsensordatas(
			Integer cacrecordtime_id,
			Integer related_cacsensordatas_cacrecordtime,
			Integer related_cacsensordatas_cacsensor) {
		Cacsensordata related_cacsensordatas = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						related_cacsensordatas_cacrecordtime,
						related_cacsensordatas_cacsensor, -1, -1);
		Cacrecordtime cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(cacrecordtime_id, -1, -1);
		Set<Cacsensordata> cacsensordataSet = cacrecordtime
				.getRelativeCacsensordatas();
		Cacsensordata teache = new Cacsensordata();
		if (cacsensordataSet.size() > 0)
			for (Cacsensordata th : cacsensordataSet) {
				if (th == related_cacsensordatas) {
					teache = related_cacsensordatas;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCacrecordtime(null);
			cacrecordtime.getRelativeCacsensordatas().remove(teache);
		}

		entityManager.persist(related_cacsensordatas);
		entityManager.persist(cacrecordtime);
		cacrecordtimeDAO.flush();
		return cacrecordtime;
	}

	@Transactional
	public Set<Cacrecordtime> loadCacrecordtimes() {
		return cacrecordtimeDAO.findAllCacrecordtimes();

	}

	@Transactional
	public Set<Cacrecordtime> loadCacrecordtimes(int index, int size) {
		return cacrecordtimeDAO.findAllCacrecordtimes(index, size);
	}

	@Transactional
	public List<Cacrecordtime> findAllCacrecordtimes(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacrecordtime>(
				cacrecordtimeDAO.findAllCacrecordtimes(startResult, maxRows));
	}

	@Transactional
	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id

	) {
		return cacrecordtimeDAO.findCacrecordtimeByPrimaryKey(id);
	}

	@Transactional
	public Integer countcacrecordtimes() {
		return ((Long) cacrecordtimeDAO.createQuerySingleResult(
				"select count(o) from Cacrecordtime o").getSingleResult())
				.intValue();
	}

}
