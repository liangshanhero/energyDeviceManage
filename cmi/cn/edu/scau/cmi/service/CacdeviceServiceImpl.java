package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacdeviceDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacdeviceService")
@Transactional
public class CacdeviceServiceImpl implements CacdeviceService {
	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacService cacService;

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	@Autowired
	private CacdevicedataService cacdevicedataService;

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	@Autowired
	private CacmalfunctionService cacmalfunctionService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacdeviceServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCacdevice(Cacdevice cacdevice) {
		Cacdevice existingCacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(cacdevice.getId());
		if (existingCacdevice != null) {
			if (existingCacdevice != cacdevice) {
				existingCacdevice.setName(cacdevice.getName());
				existingCacdevice.setUnit(cacdevice.getUnit());
			}

			if (cacdevice.getRelativeCac() != null) {
				entityManager.persist(cacdevice.getRelativeCac());
				existingCacdevice.setRelativeCac(cacdevice.getRelativeCac());
			} else
				existingCacdevice.setRelativeCac(null);

			if (cacdevice.getRelativeCacdevicedatas() != null) {
				for (Cacdevicedata relativeCacdevicedata : cacdevice
						.getRelativeCacdevicedatas()) {
					relativeCacdevicedata
							.setRelativeCacdevice(existingCacdevice);
					entityManager.persist(relativeCacdevicedata);
				}
				existingCacdevice.setRelativeCacdevicedatas(cacdevice
						.getRelativeCacdevicedatas());
			} else
				existingCacdevice.setRelativeCacdevicedatas(null);
			if (cacdevice.getRelativeCacmalfunctions() != null) {
				for (Cacmalfunction relativeCacmalfunction : cacdevice
						.getRelativeCacmalfunctions()) {
					relativeCacmalfunction
							.setRelativeCacdevice(existingCacdevice);
					entityManager.persist(relativeCacmalfunction);
				}
				existingCacdevice.setRelativeCacmalfunctions(cacdevice
						.getRelativeCacmalfunctions());
			} else
				existingCacdevice.setRelativeCacmalfunctions(null);
			entityManager.persist(existingCacdevice);
		} else {
			entityManager.persist(cacdevice);
			cacdevice = cacdeviceDAO.store(cacdevice);
		}
		cacdeviceDAO.flush();
	}

	@Transactional
	public Cacdevice saveCacdeviceCac(Integer id, Cac related_cac) {
		Cacdevice cacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(id, -1, -1);
		Cac existingCac = cacDAO.findCacByPrimaryKey(related_cac.getId());
		if (existingCac != null) {
			existingCac.setId(related_cac.getId());
			existingCac.setRemark(related_cac.getRemark());
			related_cac = existingCac;
		} else {
			related_cac = cacDAO.store(related_cac);
			cacDAO.flush();
		}
		cacdevice.setRelativeCac(related_cac);
		related_cac.getRelativeCacdevices().add(cacdevice);
		cacdevice = cacdeviceDAO.store(cacdevice);
		cacdeviceDAO.flush();

		related_cac = cacDAO.store(related_cac);
		cacDAO.flush();

		return cacdevice;
	}

	@Transactional
	public Cacdevice saveCacdeviceCacdevicedatas(Integer id,
			Cacdevicedata related_cacdevicedatas) {
		Cacdevice cacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(id, -1, -1);
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
		entityManager.persist(cacdevice);
		related_cacdevicedatas.setRelativeCacdevice(cacdevice);
		cacdevice.getRelativeCacdevicedatas().add(related_cacdevicedatas);
		entityManager.persist(cacdevice);
		entityManager.persist(related_cacdevicedatas);
		cacdeviceDAO.flush();

		return cacdevice;
	}

	@Transactional
	public Cacdevice saveCacdeviceCacmalfunctions(Integer id,
			Cacmalfunction related_cacmalfunctions) {
		Cacdevice cacdevice = cacdeviceDAO
				.findCacdeviceByPrimaryKey(id, -1, -1);
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
		entityManager.persist(cacdevice);
		related_cacmalfunctions.setRelativeCacdevice(cacdevice);
		cacdevice.getRelativeCacmalfunctions().add(related_cacmalfunctions);
		entityManager.persist(cacdevice);
		entityManager.persist(related_cacmalfunctions);
		cacdeviceDAO.flush();

		return cacdevice;
	}

	@Transactional
	public void deleteCacdevice(Cacdevice cacdevice) {

		cacdevice.setRelativeCac(null);

		Set<Cacdevicedata> cacdevicedataSet = cacdevice
				.getRelativeCacdevicedatas();
		cacdevice.setRelativeCacdevicedatas(null);
		Set<Cacmalfunction> cacmalfunctionSet = cacdevice
				.getRelativeCacmalfunctions();
		cacdevice.setRelativeCacmalfunctions(null);
		entityManager.persist(cacdevice);
		cacdeviceDAO.remove(cacdevice);
		cacdeviceDAO.flush();
	}

	@Transactional
	public Cacdevice deleteCacdeviceCac(Integer cacdevice_id,
			Integer related_cac_id) {
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		Cac related_cac = cacDAO.findCacByPrimaryKey(related_cac_id, -1, -1);
		cacdevice.setRelativeCac(null);
		related_cac.getRelativeCacdevices().remove(cacdevice);
		cacdevice = cacdeviceDAO.store(cacdevice);
		cacdeviceDAO.flush();
		related_cac = cacDAO.store(related_cac);

		cacDAO.flush();
		return cacdevice;
	}

	@Transactional
	public Cacdevice deleteCacdeviceCacdevicedatas(Integer cacdevice_id,
			Integer related_cacdevicedatas_cacdevice,
			Integer related_cacdevicedatas_cacrecordtime) {
		Cacdevicedata related_cacdevicedatas = cacdevicedataDAO
				.findCacdevicedataByPrimaryKey(
						related_cacdevicedatas_cacdevice,
						related_cacdevicedatas_cacrecordtime, -1, -1);
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		Set<Cacdevicedata> cacdevicedataSet = cacdevice
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
			teache.setRelativeCacdevice(null);
			cacdevice.getRelativeCacdevicedatas().remove(teache);
		}

		entityManager.persist(related_cacdevicedatas);
		entityManager.persist(cacdevice);
		cacdeviceDAO.flush();
		return cacdevice;
	}

	@Transactional
	public Cacdevice deleteCacdeviceCacmalfunctions(Integer cacdevice_id,
			Integer related_cacmalfunctions_cacrecordtime,
			Integer related_cacmalfunctions_cacdevice) {
		Cacmalfunction related_cacmalfunctions = cacmalfunctionDAO
				.findCacmalfunctionByPrimaryKey(
						related_cacmalfunctions_cacrecordtime,
						related_cacmalfunctions_cacdevice, -1, -1);
		Cacdevice cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(
				cacdevice_id, -1, -1);
		Set<Cacmalfunction> cacmalfunctionSet = cacdevice
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
			teache.setRelativeCacdevice(null);
			cacdevice.getRelativeCacmalfunctions().remove(teache);
		}

		entityManager.persist(related_cacmalfunctions);
		entityManager.persist(cacdevice);
		cacdeviceDAO.flush();
		return cacdevice;
	}

	@Transactional
	public Set<Cacdevice> loadCacdevices() {
		return cacdeviceDAO.findAllCacdevices();

	}

	@Transactional
	public Set<Cacdevice> loadCacdevices(int index, int size) {
		return cacdeviceDAO.findAllCacdevices(index, size);
	}

	@Transactional
	public List<Cacdevice> findAllCacdevices(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Cacdevice>(
				cacdeviceDAO.findAllCacdevices(startResult, maxRows));
	}

	@Transactional
	public Cacdevice findCacdeviceByPrimaryKey(Integer id

	) {
		return cacdeviceDAO.findCacdeviceByPrimaryKey(id);
	}

	@Transactional
	public Integer countcacdevices() {
		return ((Long) cacdeviceDAO.createQuerySingleResult(
				"select count(o) from Cacdevice o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeCacCacdevices(Integer cac) {
		String sql = "select count(*) from Cacdevice where cac=" + cac;
		return ((Long) cacdeviceDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
