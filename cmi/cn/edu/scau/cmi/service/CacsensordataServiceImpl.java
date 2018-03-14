package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacsensordataDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacsensordataService")
@Transactional
public class CacsensordataServiceImpl implements CacsensordataService {
	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	@Autowired
	private CacrecordtimeService cacrecordtimeService;
	@Autowired
	private CacsensorDAO cacsensorDAO;

	@Autowired
	private CacsensorService cacsensorService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacsensordataServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacsensordata(Cacsensordata cacsensordata) {
		Cacsensordata existingCacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						cacsensordata.getCacrecordtime(),
						cacsensordata.getCacsensor());
		if (existingCacsensordata != null) {
			if (existingCacsensordata != cacsensordata) {
				existingCacsensordata.setValue(cacsensordata.getValue());
				existingCacsensordata.setIsreport(cacsensordata.getIsreport());
			}

			if (cacsensordata.getRelativeCacrecordtime() != null) {
				entityManager.persist(cacsensordata.getRelativeCacrecordtime());
				existingCacsensordata.setRelativeCacrecordtime(cacsensordata
						.getRelativeCacrecordtime());
			} else
				existingCacsensordata.setRelativeCacrecordtime(null);
			if (cacsensordata.getRelativeCacsensor() != null) {
				entityManager.persist(cacsensordata.getRelativeCacsensor());
				existingCacsensordata.setRelativeCacsensor(cacsensordata
						.getRelativeCacsensor());
			} else
				existingCacsensordata.setRelativeCacsensor(null);

			entityManager.persist(existingCacsensordata);
		} else {
			entityManager.persist(cacsensordata);
			cacsensordata = cacsensordataDAO.store(cacsensordata);
		}
		cacsensordataDAO.flush();
	}

	@Transactional
	public Cacsensordata saveCacsensordataCacrecordtime(Integer cacrecordtime,
			Integer cacsensor, Cacrecordtime related_cacrecordtime) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor, -1, -1);
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
		cacsensordata.setRelativeCacrecordtime(related_cacrecordtime);
		related_cacrecordtime.getRelativeCacsensordatas().add(cacsensordata);
		cacsensordata = cacsensordataDAO.store(cacsensordata);
		cacsensordataDAO.flush();

		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);
		cacrecordtimeDAO.flush();

		return cacsensordata;
	}

	@Transactional
	public Cacsensordata saveCacsensordataCacsensor(Integer cacrecordtime,
			Integer cacsensor, Cacsensor related_cacsensor) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacrecordtime, cacsensor, -1, -1);
		Cacsensor existingCacsensor = cacsensorDAO
				.findCacsensorByPrimaryKey(related_cacsensor.getId());
		if (existingCacsensor != null) {
			existingCacsensor.setId(related_cacsensor.getId());
			existingCacsensor.setName(related_cacsensor.getName());
			related_cacsensor = existingCacsensor;
		} else {
			related_cacsensor = cacsensorDAO.store(related_cacsensor);
			cacsensorDAO.flush();
		}
		cacsensordata.setRelativeCacsensor(related_cacsensor);
		related_cacsensor.getRelativeCacsensordatas().add(cacsensordata);
		cacsensordata = cacsensordataDAO.store(cacsensordata);
		cacsensordataDAO.flush();

		related_cacsensor = cacsensorDAO.store(related_cacsensor);
		cacsensorDAO.flush();

		return cacsensordata;
	}

	@Transactional
	public void deleteCacsensordata(Cacsensordata cacsensordata) {

		cacsensordata.setRelativeCacrecordtime(null);
		cacsensordata.setRelativeCacsensor(null);

		entityManager.persist(cacsensordata);
		cacsensordataDAO.remove(cacsensordata);
		cacsensordataDAO.flush();
	}

	@Transactional
	public Cacsensordata deleteCacsensordataCacrecordtime(
			Integer cacsensordata_cacrecordtime,
			Integer cacsensordata_cacsensor, Integer related_cacrecordtime_id) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, -1, -1);
		Cacrecordtime related_cacrecordtime = cacrecordtimeDAO
				.findCacrecordtimeByPrimaryKey(related_cacrecordtime_id, -1, -1);
		cacsensordata.setRelativeCacrecordtime(null);
		related_cacrecordtime.getRelativeCacsensordatas().remove(cacsensordata);
		cacsensordata = cacsensordataDAO.store(cacsensordata);
		cacsensordataDAO.flush();
		related_cacrecordtime = cacrecordtimeDAO.store(related_cacrecordtime);

		cacrecordtimeDAO.flush();
		return cacsensordata;
	}

	@Transactional
	public Cacsensordata deleteCacsensordataCacsensor(
			Integer cacsensordata_cacrecordtime,
			Integer cacsensordata_cacsensor, Integer related_cacsensor_id) {
		Cacsensordata cacsensordata = cacsensordataDAO
				.findCacsensordataByPrimaryKey(cacsensordata_cacrecordtime,
						cacsensordata_cacsensor, -1, -1);
		Cacsensor related_cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				related_cacsensor_id, -1, -1);
		cacsensordata.setRelativeCacsensor(null);
		related_cacsensor.getRelativeCacsensordatas().remove(cacsensordata);
		cacsensordata = cacsensordataDAO.store(cacsensordata);
		cacsensordataDAO.flush();
		related_cacsensor = cacsensorDAO.store(related_cacsensor);

		cacsensorDAO.flush();
		return cacsensordata;
	}

	@Transactional
	public Set<Cacsensordata> loadCacsensordatas() {
		return cacsensordataDAO.findAllCacsensordatas();

	}

	@Transactional
	public Set<Cacsensordata> loadCacsensordatas(int index, int size) {
		return cacsensordataDAO.findAllCacsensordatas(index, size);
	}

	@Transactional
	public List<Cacsensordata> findAllCacsensordatas(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacsensordata>(
				cacsensordataDAO.findAllCacsensordatas(startResult, maxRows));
	}

	@Transactional
	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime

	, Integer cacsensor

	) {
		return cacsensordataDAO.findCacsensordataByPrimaryKey(cacrecordtime,
				cacsensor);
	}

	@Transactional
	public Integer countcacsensordatas() {
		return ((Long) cacsensordataDAO.createQuerySingleResult(
				"select count(*) from Cacsensordata o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeCacrecordtimeCacsensordatas(
			Integer cacrecordtime) {
		String sql = "select count(*) from Cacsensordata where cacrecordtime="
				+ cacrecordtime;
		return ((Long) cacsensordataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeCacsensorCacsensordatas(Integer cacsensor) {
		String sql = "select count(*) from Cacsensordata where cacsensor="
				+ cacsensor;
		return ((Long) cacsensordataDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
