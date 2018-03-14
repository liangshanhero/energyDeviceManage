package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.WhbuildingDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("WhbuildingService")
@Transactional
public class WhbuildingServiceImpl implements WhbuildingService {
	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	@Autowired
	private WhdeviceService whdeviceService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhbuildingServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveWhbuilding(Whbuilding whbuilding) {
		Whbuilding existingWhbuilding = whbuildingDAO
				.findWhbuildingByPrimaryKey(whbuilding.getId());
		if (existingWhbuilding != null) {
			if (existingWhbuilding != whbuilding) {
				existingWhbuilding.setName(whbuilding.getName());
			}

			if (whbuilding.getRelativeProject() != null) {
				entityManager.persist(whbuilding.getRelativeProject());
				existingWhbuilding.setRelativeProject(whbuilding
						.getRelativeProject());
			} else
				existingWhbuilding.setRelativeProject(null);

			if (whbuilding.getRelativeWhdevices() != null) {
				for (Whdevice relativeWhdevice : whbuilding
						.getRelativeWhdevices()) {
					relativeWhdevice.setWhbuilding(existingWhbuilding);
					entityManager.persist(relativeWhdevice);
				}
				existingWhbuilding.setRelativeWhdevices(whbuilding
						.getRelativeWhdevices());
			} else
				existingWhbuilding.setRelativeWhdevices(null);
			entityManager.persist(existingWhbuilding);
		} else {
			entityManager.persist(whbuilding);
			whbuilding = whbuildingDAO.store(whbuilding);
		}
		whbuildingDAO.flush();
	}

	@Transactional
	public Whbuilding saveWhbuildingProject(Integer id, Project related_project) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(id,
				-1, -1);
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
		whbuilding.setRelativeProject(related_project);
		related_project.getRelativeWhbuildings().add(whbuilding);
		whbuilding = whbuildingDAO.store(whbuilding);
		whbuildingDAO.flush();

		related_project = projectDAO.store(related_project);
		projectDAO.flush();

		return whbuilding;
	}

	@Transactional
	public Whbuilding saveWhbuildingWhdevices(Integer id,
			Whdevice related_whdevices) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(id,
				-1, -1);
		Whdevice existingWhdevices = whdeviceDAO
				.findWhdeviceByPrimaryKey(related_whdevices.getId());
		if (existingWhdevices != null) {
			existingWhdevices.setId(related_whdevices.getId());
			existingWhdevices.setNumber(related_whdevices.getNumber());
			related_whdevices = existingWhdevices;
			entityManager.persist(related_whdevices);
		} else {
			entityManager.persist(related_whdevices);
			whdeviceDAO.flush();
		}
		entityManager.persist(whbuilding);
		related_whdevices.setWhbuilding(whbuilding);
		whbuilding.getRelativeWhdevices().add(related_whdevices);
		entityManager.persist(whbuilding);
		entityManager.persist(related_whdevices);
		whbuildingDAO.flush();

		return whbuilding;
	}

	@Transactional
	public void deleteWhbuilding(Whbuilding whbuilding) {

		whbuilding.setRelativeProject(null);

		Set<Whdevice> whdeviceSet = whbuilding.getRelativeWhdevices();
		whbuilding.setRelativeWhdevices(null);
		entityManager.persist(whbuilding);
		whbuildingDAO.remove(whbuilding);
		whbuildingDAO.flush();
	}

	@Transactional
	public Whbuilding deleteWhbuildingProject(Integer whbuilding_id,
			Integer related_project_id) {
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuilding_id, -1, -1);
		Project related_project = projectDAO.findProjectByPrimaryKey(
				related_project_id, -1, -1);
		whbuilding.setRelativeProject(null);
		related_project.getRelativeWhbuildings().remove(whbuilding);
		whbuilding = whbuildingDAO.store(whbuilding);
		whbuildingDAO.flush();
		related_project = projectDAO.store(related_project);

		projectDAO.flush();
		return whbuilding;
	}

	@Transactional
	public Whbuilding deleteWhbuildingWhdevices(Integer whbuilding_id,
			Integer related_whdevices_id) {
		Whdevice related_whdevices = whdeviceDAO.findWhdeviceByPrimaryKey(
				related_whdevices_id, -1, -1);
		Whbuilding whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(
				whbuilding_id, -1, -1);
		Set<Whdevice> whdeviceSet = whbuilding.getRelativeWhdevices();
		Whdevice teache = new Whdevice();
		if (whdeviceSet.size() > 0)
			for (Whdevice th : whdeviceSet) {
				if (th == related_whdevices) {
					teache = related_whdevices;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setWhbuilding(null);
			whbuilding.getRelativeWhdevices().remove(teache);
		}

		entityManager.persist(related_whdevices);
		entityManager.persist(whbuilding);
		whbuildingDAO.flush();
		return whbuilding;
	}

	@Transactional
	public Set<Whbuilding> loadWhbuildings() {
		return whbuildingDAO.findAllWhbuildings();

	}

	@Transactional
	public Set<Whbuilding> loadWhbuildings(int index, int size) {
		return whbuildingDAO.findAllWhbuildings(index, size);
	}

	@Transactional
	public List<Whbuilding> findAllWhbuildings(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Whbuilding>(
				whbuildingDAO.findAllWhbuildings(startResult, maxRows));
	}

	@Transactional
	public Whbuilding findWhbuildingByPrimaryKey(Integer id

	) {
		return whbuildingDAO.findWhbuildingByPrimaryKey(id);
	}

	@Transactional
	public Integer countwhbuildings() {
		return ((Long) whbuildingDAO.createQuerySingleResult(
				"select count(o) from Whbuilding o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeProjectWhbuildings(Integer project) {
		String sql = "select count(*) from Whbuilding where project=" + project;
		return ((Long) whbuildingDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
