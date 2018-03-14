package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacsensorDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacsensorService")
@Transactional
public class CacsensorServiceImpl implements CacsensorService {
	@Autowired
	private CacsensorDAO cacsensorDAO;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacService cacService;

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	@Autowired
	private CacsensordataService cacsensordataService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacsensorServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacsensor(Cacsensor cacsensor) {
		Cacsensor existingCacsensor = cacsensorDAO
				.findCacsensorByPrimaryKey(cacsensor.getId());
		if (existingCacsensor != null) {
			if (existingCacsensor != cacsensor) {
				existingCacsensor.setName(cacsensor.getName());
			}

			if (cacsensor.getRelativeCac() != null) {
				entityManager.persist(cacsensor.getRelativeCac());
				existingCacsensor.setRelativeCac(cacsensor.getRelativeCac());
			} else
				existingCacsensor.setRelativeCac(null);

			if (cacsensor.getRelativeCacsensordatas() != null) {
				for (Cacsensordata relativeCacsensordata : cacsensor
						.getRelativeCacsensordatas()) {
					relativeCacsensordata
							.setRelativeCacsensor(existingCacsensor);
					entityManager.persist(relativeCacsensordata);
				}
				existingCacsensor.setRelativeCacsensordatas(cacsensor
						.getRelativeCacsensordatas());
			} else
				existingCacsensor.setRelativeCacsensordatas(null);
			entityManager.persist(existingCacsensor);
		} else {
			entityManager.persist(cacsensor);
			cacsensor = cacsensorDAO.store(cacsensor);
		}
		cacsensorDAO.flush();
	}

	@Transactional
	public Cacsensor saveCacsensorCac(Integer id, Cac related_cac) {
		Cacsensor cacsensor = cacsensorDAO
				.findCacsensorByPrimaryKey(id, -1, -1);
		Cac existingCac = cacDAO.findCacByPrimaryKey(related_cac.getId());
		if (existingCac != null) {
			existingCac.setId(related_cac.getId());
			existingCac.setRemark(related_cac.getRemark());
			related_cac = existingCac;
		} else {
			related_cac = cacDAO.store(related_cac);
			cacDAO.flush();
		}
		cacsensor.setRelativeCac(related_cac);
		related_cac.getRelativeCacsensors().add(cacsensor);
		cacsensor = cacsensorDAO.store(cacsensor);
		cacsensorDAO.flush();

		related_cac = cacDAO.store(related_cac);
		cacDAO.flush();

		return cacsensor;
	}

	@Transactional
	public Cacsensor saveCacsensorCacsensordatas(Integer id,
			Cacsensordata related_cacsensordatas) {
		Cacsensor cacsensor = cacsensorDAO
				.findCacsensorByPrimaryKey(id, -1, -1);
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
		entityManager.persist(cacsensor);
		related_cacsensordatas.setRelativeCacsensor(cacsensor);
		cacsensor.getRelativeCacsensordatas().add(related_cacsensordatas);
		entityManager.persist(cacsensor);
		entityManager.persist(related_cacsensordatas);
		cacsensorDAO.flush();

		return cacsensor;
	}

	@Transactional
	public void deleteCacsensor(Cacsensor cacsensor) {

		cacsensor.setRelativeCac(null);

		Set<Cacsensordata> cacsensordataSet = cacsensor
				.getRelativeCacsensordatas();
		cacsensor.setRelativeCacsensordatas(null);
		entityManager.persist(cacsensor);
		cacsensorDAO.remove(cacsensor);
		cacsensorDAO.flush();
	}

	@Transactional
	public Cacsensor deleteCacsensorCac(Integer cacsensor_id,
			Integer related_cac_id) {
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensor_id, -1, -1);
		Cac related_cac = cacDAO.findCacByPrimaryKey(related_cac_id, -1, -1);
		cacsensor.setRelativeCac(null);
		related_cac.getRelativeCacsensors().remove(cacsensor);
		cacsensor = cacsensorDAO.store(cacsensor);
		cacsensorDAO.flush();
		related_cac = cacDAO.store(related_cac);

		cacDAO.flush();
		return cacsensor;
	}

	@Transactional
	public Cacsensor deleteCacsensorCacsensordatas(Integer cacsensor_id,
			Integer related_cacsensordatas_cacrecordtime,
			Integer related_cacsensordatas_cacsensor) {
		Cacsensordata related_cacsensordatas = cacsensordataDAO
				.findCacsensordataByPrimaryKey(
						related_cacsensordatas_cacrecordtime,
						related_cacsensordatas_cacsensor, -1, -1);
		Cacsensor cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(
				cacsensor_id, -1, -1);
		Set<Cacsensordata> cacsensordataSet = cacsensor
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
			teache.setRelativeCacsensor(null);
			cacsensor.getRelativeCacsensordatas().remove(teache);
		}

		entityManager.persist(related_cacsensordatas);
		entityManager.persist(cacsensor);
		cacsensorDAO.flush();
		return cacsensor;
	}

	@Transactional
	public Set<Cacsensor> loadCacsensors() {
		return cacsensorDAO.findAllCacsensors();

	}

	@Transactional
	public Set<Cacsensor> loadCacsensors(int index, int size) {
		return cacsensorDAO.findAllCacsensors(index, size);
	}

	@Transactional
	public List<Cacsensor> findAllCacsensors(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacsensor>(
				cacsensorDAO.findAllCacsensors(startResult, maxRows));
	}

	@Transactional
	public Cacsensor findCacsensorByPrimaryKey(Integer id

	) {
		return cacsensorDAO.findCacsensorByPrimaryKey(id);
	}

	@Transactional
	public Integer countcacsensors() {
		return ((Long) cacsensorDAO.createQuerySingleResult(
				"select count(o) from Cacsensor o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeCacCacsensors(Integer cac) {
		String sql = "select count(*) from Cacsensor where cac=" + cac;
		return ((Long) cacsensorDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
