package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.LedbuildingDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("LedbuildingService")
@Transactional
public class LedbuildingServiceImpl implements LedbuildingService {
	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private LedmeterDAO ledmeterDAO;

	@Autowired
	private LedmeterService ledmeterService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedbuildingServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveLedbuilding(Ledbuilding ledbuilding) {
		Ledbuilding existingLedbuilding = ledbuildingDAO
				.findLedbuildingByPrimaryKey(ledbuilding.getId());
		if (existingLedbuilding != null) {
			if (existingLedbuilding != ledbuilding) {
				existingLedbuilding.setName(ledbuilding.getName());
				existingLedbuilding.setWell(ledbuilding.getWell());
				existingLedbuilding.setStorey(ledbuilding.getStorey());
			}

			if (ledbuilding.getRelativeProject() != null) {
				entityManager.persist(ledbuilding.getRelativeProject());
				existingLedbuilding.setRelativeProject(ledbuilding
						.getRelativeProject());
			} else
				existingLedbuilding.setRelativeProject(null);

			if (ledbuilding.getRelativeLedmeters() != null) {
				for (Ledmeter relativeLedmeter : ledbuilding
						.getRelativeLedmeters()) {
					relativeLedmeter
							.setRelativeLedbuilding(existingLedbuilding);
					entityManager.persist(relativeLedmeter);
				}
				existingLedbuilding.setRelativeLedmeters(ledbuilding
						.getRelativeLedmeters());
			} else
				existingLedbuilding.setRelativeLedmeters(null);
			entityManager.persist(existingLedbuilding);
		} else {
			entityManager.persist(ledbuilding);
			ledbuilding = ledbuildingDAO.store(ledbuilding);
		}
		ledbuildingDAO.flush();
	}

	@Transactional
	public Ledbuilding saveLedbuildingProject(Integer id,
			Project related_project) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				id, -1, -1);
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
		ledbuilding.setRelativeProject(related_project);
		related_project.getRelativeLedbuildings().add(ledbuilding);
		ledbuilding = ledbuildingDAO.store(ledbuilding);
		ledbuildingDAO.flush();

		related_project = projectDAO.store(related_project);
		projectDAO.flush();

		return ledbuilding;
	}

	@Transactional
	public Ledbuilding saveLedbuildingLedmeters(Integer id,
			Ledmeter related_ledmeters) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				id, -1, -1);
		Ledmeter existingLedmeters = ledmeterDAO
				.findLedmeterByPrimaryKey(related_ledmeters.getId());
		if (existingLedmeters != null) {
			existingLedmeters.setId(related_ledmeters.getId());
			existingLedmeters.setNumber(related_ledmeters.getNumber());
			existingLedmeters.setWell(related_ledmeters.getWell());
			existingLedmeters.setStorey(related_ledmeters.getStorey());
			existingLedmeters.setTotalamout(related_ledmeters.getTotalamout());
			existingLedmeters.setTotaldays(related_ledmeters.getTotaldays());
			related_ledmeters = existingLedmeters;
			entityManager.persist(related_ledmeters);
		} else {
			entityManager.persist(related_ledmeters);
			ledmeterDAO.flush();
		}
		entityManager.persist(ledbuilding);
		related_ledmeters.setRelativeLedbuilding(ledbuilding);
		ledbuilding.getRelativeLedmeters().add(related_ledmeters);
		entityManager.persist(ledbuilding);
		entityManager.persist(related_ledmeters);
		ledbuildingDAO.flush();

		return ledbuilding;
	}

	@Transactional
	public void deleteLedbuilding(Ledbuilding ledbuilding) {

		ledbuilding.setRelativeProject(null);

		Set<Ledmeter> ledmeterSet = ledbuilding.getRelativeLedmeters();
		ledbuilding.setRelativeLedmeters(null);
		entityManager.persist(ledbuilding);
		ledbuildingDAO.remove(ledbuilding);
		ledbuildingDAO.flush();
	}

	@Transactional
	public Ledbuilding deleteLedbuildingProject(Integer ledbuilding_id,
			Integer related_project_id) {
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuilding_id, -1, -1);
		Project related_project = projectDAO.findProjectByPrimaryKey(
				related_project_id, -1, -1);
		ledbuilding.setRelativeProject(null);
		related_project.getRelativeLedbuildings().remove(ledbuilding);
		ledbuilding = ledbuildingDAO.store(ledbuilding);
		ledbuildingDAO.flush();
		related_project = projectDAO.store(related_project);

		projectDAO.flush();
		return ledbuilding;
	}

	@Transactional
	public Ledbuilding deleteLedbuildingLedmeters(Integer ledbuilding_id,
			Integer related_ledmeters_id) {
		Ledmeter related_ledmeters = ledmeterDAO.findLedmeterByPrimaryKey(
				related_ledmeters_id, -1, -1);
		Ledbuilding ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(
				ledbuilding_id, -1, -1);
		Set<Ledmeter> ledmeterSet = ledbuilding.getRelativeLedmeters();
		Ledmeter teache = new Ledmeter();
		if (ledmeterSet.size() > 0)
			for (Ledmeter th : ledmeterSet) {
				if (th == related_ledmeters) {
					teache = related_ledmeters;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeLedbuilding(null);
			ledbuilding.getRelativeLedmeters().remove(teache);
		}

		entityManager.persist(related_ledmeters);
		entityManager.persist(ledbuilding);
		ledbuildingDAO.flush();
		return ledbuilding;
	}

	@Transactional
	public Set<Ledbuilding> loadLedbuildings() {
		return ledbuildingDAO.findAllLedbuildings();

	}

	@Transactional
	public Set<Ledbuilding> loadLedbuildings(int index, int size) {
		return ledbuildingDAO.findAllLedbuildings(index, size);
	}

	@Transactional
	public List<Ledbuilding> findAllLedbuildings(Integer startResult,
			Integer maxRows) {
		return new java.util.ArrayList<Ledbuilding>(
				ledbuildingDAO.findAllLedbuildings(startResult, maxRows));
	}

	@Transactional
	public Ledbuilding findLedbuildingByPrimaryKey(Integer id

	) {
		return ledbuildingDAO.findLedbuildingByPrimaryKey(id);
	}

	@Transactional
	public Integer countledbuildings() {
		return ((Long) ledbuildingDAO.createQuerySingleResult(
				"select count(o) from Ledbuilding o").getSingleResult())
				.intValue();
	}

	@Transactional
	public Integer countRelativeProjectLedbuildings(Integer project) {
		String sql = "select count(*) from Ledbuilding where project="
				+ project;
		return ((Long) ledbuildingDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
