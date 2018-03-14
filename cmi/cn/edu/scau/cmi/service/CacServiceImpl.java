package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CacDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CacService")
@Transactional
public class CacServiceImpl implements CacService {
	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	@Autowired
	private CacdeviceService cacdeviceService;

	@Autowired
	private CacsensorDAO cacsensorDAO;

	@Autowired
	private CacsensorService cacsensorService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCac(Cac cac) {
		Cac existingCac = cacDAO.findCacByPrimaryKey(cac.getId());
		if (existingCac != null) {
			if (existingCac != cac) {
				existingCac.setRemark(cac.getRemark());
			}

			if (cac.getRelativeProject() != null) {
				entityManager.persist(cac.getRelativeProject());
				existingCac.setRelativeProject(cac.getRelativeProject());
			} else
				existingCac.setRelativeProject(null);

			if (cac.getRelativeCacdevices() != null) {
				for (Cacdevice relativeCacdevice : cac.getRelativeCacdevices()) {
					relativeCacdevice.setRelativeCac(existingCac);
					entityManager.persist(relativeCacdevice);
				}
				existingCac.setRelativeCacdevices(cac.getRelativeCacdevices());
			} else
				existingCac.setRelativeCacdevices(null);
			if (cac.getRelativeCacsensors() != null) {
				for (Cacsensor relativeCacsensor : cac.getRelativeCacsensors()) {
					relativeCacsensor.setRelativeCac(existingCac);
					entityManager.persist(relativeCacsensor);
				}
				existingCac.setRelativeCacsensors(cac.getRelativeCacsensors());
			} else
				existingCac.setRelativeCacsensors(null);
			entityManager.persist(existingCac);
		} else {
			entityManager.persist(cac);
			cac = cacDAO.store(cac);
		}
		cacDAO.flush();
	}

	@Transactional
	public Cac saveCacProject(Integer id, Project related_project) {
		Cac cac = cacDAO.findCacByPrimaryKey(id, -1, -1);
		Project existingProject = projectDAO
				.findProjectByPrimaryKey(related_project.getId());
		if (existingProject != null) {
			existingProject.setId(related_project.getId());
			existingProject.setName(related_project.getName());
			existingProject.setType(related_project.getType());
			existingProject.setStatus(related_project.getStatus());
			existingProject.setProvince(related_project.getProvince());
			existingProject.setCity(related_project.getCity());
			existingProject.setArea(related_project.getArea());
			existingProject.setAddress(related_project.getAddress());
			existingProject.setDetail(related_project.getDetail());
			existingProject.setRemark(related_project.getRemark());
			related_project = existingProject;
		} else {
			related_project = projectDAO.store(related_project);
			projectDAO.flush();
		}
		cac.setRelativeProject(related_project);
		related_project.getRelativeCacs().add(cac);
		cac = cacDAO.store(cac);
		cacDAO.flush();

		related_project = projectDAO.store(related_project);
		projectDAO.flush();

		return cac;
	}

	@Transactional
	public Cac saveCacCacdevices(Integer id, Cacdevice related_cacdevices) {
		Cac cac = cacDAO.findCacByPrimaryKey(id, -1, -1);
		Cacdevice existingCacdevices = cacdeviceDAO
				.findCacdeviceByPrimaryKey(related_cacdevices.getId());
		if (existingCacdevices != null) {
			existingCacdevices.setId(related_cacdevices.getId());
			existingCacdevices.setName(related_cacdevices.getName());
			existingCacdevices.setUnit(related_cacdevices.getUnit());
			related_cacdevices = existingCacdevices;
			entityManager.persist(related_cacdevices);
		} else {
			entityManager.persist(related_cacdevices);
			cacdeviceDAO.flush();
		}
		entityManager.persist(cac);
		related_cacdevices.setRelativeCac(cac);
		cac.getRelativeCacdevices().add(related_cacdevices);
		entityManager.persist(cac);
		entityManager.persist(related_cacdevices);
		cacDAO.flush();

		return cac;
	}

	@Transactional
	public Cac saveCacCacsensors(Integer id, Cacsensor related_cacsensors) {
		Cac cac = cacDAO.findCacByPrimaryKey(id, -1, -1);
		Cacsensor existingCacsensors = cacsensorDAO
				.findCacsensorByPrimaryKey(related_cacsensors.getId());
		if (existingCacsensors != null) {
			existingCacsensors.setId(related_cacsensors.getId());
			existingCacsensors.setName(related_cacsensors.getName());
			related_cacsensors = existingCacsensors;
			entityManager.persist(related_cacsensors);
		} else {
			entityManager.persist(related_cacsensors);
			cacsensorDAO.flush();
		}
		entityManager.persist(cac);
		related_cacsensors.setRelativeCac(cac);
		cac.getRelativeCacsensors().add(related_cacsensors);
		entityManager.persist(cac);
		entityManager.persist(related_cacsensors);
		cacDAO.flush();

		return cac;
	}

	@Transactional
	public void deleteCac(Cac cac) {

		cac.setRelativeProject(null);

		Set<Cacdevice> cacdeviceSet = cac.getRelativeCacdevices();
		cac.setRelativeCacdevices(null);
		Set<Cacsensor> cacsensorSet = cac.getRelativeCacsensors();
		cac.setRelativeCacsensors(null);
		entityManager.persist(cac);
		cacDAO.remove(cac);
		cacDAO.flush();
	}

	@Transactional
	public Cac deleteCacProject(Integer cac_id, Integer related_project_id) {
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		Project related_project = projectDAO.findProjectByPrimaryKey(
				related_project_id, -1, -1);
		cac.setRelativeProject(null);
		related_project.getRelativeCacs().remove(cac);
		cac = cacDAO.store(cac);
		cacDAO.flush();
		related_project = projectDAO.store(related_project);

		projectDAO.flush();
		return cac;
	}

	@Transactional
	public Cac deleteCacCacdevices(Integer cac_id, Integer related_cacdevices_id) {
		Cacdevice related_cacdevices = cacdeviceDAO.findCacdeviceByPrimaryKey(
				related_cacdevices_id, -1, -1);
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		Set<Cacdevice> cacdeviceSet = cac.getRelativeCacdevices();
		Cacdevice teache = new Cacdevice();
		if (cacdeviceSet.size() > 0)
			for (Cacdevice th : cacdeviceSet) {
				if (th == related_cacdevices) {
					teache = related_cacdevices;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCac(null);
			cac.getRelativeCacdevices().remove(teache);
		}

		entityManager.persist(related_cacdevices);
		entityManager.persist(cac);
		cacDAO.flush();
		return cac;
	}

	@Transactional
	public Cac deleteCacCacsensors(Integer cac_id, Integer related_cacsensors_id) {
		Cacsensor related_cacsensors = cacsensorDAO.findCacsensorByPrimaryKey(
				related_cacsensors_id, -1, -1);
		Cac cac = cacDAO.findCacByPrimaryKey(cac_id, -1, -1);
		Set<Cacsensor> cacsensorSet = cac.getRelativeCacsensors();
		Cacsensor teache = new Cacsensor();
		if (cacsensorSet.size() > 0)
			for (Cacsensor th : cacsensorSet) {
				if (th == related_cacsensors) {
					teache = related_cacsensors;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCac(null);
			cac.getRelativeCacsensors().remove(teache);
		}

		entityManager.persist(related_cacsensors);
		entityManager.persist(cac);
		cacDAO.flush();
		return cac;
	}

	@Transactional
	public Set<Cac> loadCacs() {
		return cacDAO.findAllCacs();

	}

	@Transactional
	public Set<Cac> loadCacs(int index, int size) {
		return cacDAO.findAllCacs(index, size);
	}

	@Transactional
	public List<Cac> findAllCacs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Cac>(cacDAO.findAllCacs(startResult,
				maxRows));
	}

	@Transactional
	public Cac findCacByPrimaryKey(Integer id

	) {
		return cacDAO.findCacByPrimaryKey(id);
	}

	@Transactional
	public Integer countcacs() {
		return ((Long) cacDAO.createQuerySingleResult(
				"select count(o) from Cac o").getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeProjectCacs(Integer project) {
		String sql = "select count(*) from Cac where project=" + project;
		return ((Long) cacDAO.createQuerySingleResult(sql).getSingleResult())
				.intValue();
	}

}
