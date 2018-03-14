package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.ProjectDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("ProjectService")
@Transactional
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private CompanyService companyService;
	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private StaffService staffService;

	@Autowired
	private CacDAO cacDAO;

	@Autowired
	private CacService cacService;

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	@Autowired
	private LedbuildingService ledbuildingService;

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	@Autowired
	private WhbuildingService whbuildingService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public ProjectServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveProject(Project project) {
		Project existingProject = projectDAO.findProjectByPrimaryKey(project
				.getId());
		if (existingProject != null) {
			if (existingProject != project) {
				existingProject.setName(project.getName());
				existingProject.setType(project.getType());
				existingProject.setStatus(project.getStatus());
				existingProject.setProvince(project.getProvince());
				existingProject.setCity(project.getCity());
				existingProject.setArea(project.getArea());
				existingProject.setAddress(project.getAddress());
				existingProject.setDetail(project.getDetail());
				existingProject.setRemark(project.getRemark());
			}

			if (project.getRelativeCompany() != null) {
				entityManager.persist(project.getRelativeCompany());
				existingProject
						.setRelativeCompany(project.getRelativeCompany());
			} else
				existingProject.setRelativeCompany(null);
			if (project.getRelativeStaff() != null) {
				entityManager.persist(project.getRelativeStaff());
				existingProject.setRelativeStaff(project.getRelativeStaff());
			} else
				existingProject.setRelativeStaff(null);

			if (project.getRelativeCacs() != null) {
				for (Cac relativeCac : project.getRelativeCacs()) {
					relativeCac.setRelativeProject(existingProject);
					entityManager.persist(relativeCac);
				}
				existingProject.setRelativeCacs(project.getRelativeCacs());
			} else
				existingProject.setRelativeCacs(null);
			if (project.getRelativeLedbuildings() != null) {
				for (Ledbuilding relativeLedbuilding : project
						.getRelativeLedbuildings()) {
					relativeLedbuilding.setRelativeProject(existingProject);
					entityManager.persist(relativeLedbuilding);
				}
				existingProject.setRelativeLedbuildings(project
						.getRelativeLedbuildings());
			} else
				existingProject.setRelativeLedbuildings(null);
			if (project.getRelativeWhbuildings() != null) {
				for (Whbuilding relativeWhbuilding : project
						.getRelativeWhbuildings()) {
					relativeWhbuilding.setRelativeProject(existingProject);
					entityManager.persist(relativeWhbuilding);
				}
				existingProject.setRelativeWhbuildings(project
						.getRelativeWhbuildings());
			} else
				existingProject.setRelativeWhbuildings(null);
			entityManager.persist(existingProject);
		} else {
			entityManager.persist(project);
			project = projectDAO.store(project);
		}
		projectDAO.flush();
	}

	@Transactional
	public Project saveProjectCompany(Integer id, Company related_company) {
		Project project = projectDAO.findProjectByPrimaryKey(id, -1, -1);
		Company existingCompany = companyDAO
				.findCompanyByPrimaryKey(related_company.getId());
		if (existingCompany != null) {
			existingCompany.setId(related_company.getId());
			existingCompany.setName(related_company.getName());
			existingCompany.setPhone(related_company.getPhone());
			existingCompany.setFax(related_company.getFax());
			existingCompany.setPostcode(related_company.getPostcode());
			existingCompany.setAddress(related_company.getAddress());
			existingCompany.setWebsite(related_company.getWebsite());
			existingCompany.setPersonduty(related_company.getPersonduty());
			existingCompany.setDetail(related_company.getDetail());
			existingCompany.setRemark(related_company.getRemark());
			related_company = existingCompany;
		} else {
			related_company = companyDAO.store(related_company);
			companyDAO.flush();
		}
		project.setRelativeCompany(related_company);
		related_company.getRelativeProjects().add(project);
		project = projectDAO.store(project);
		projectDAO.flush();

		related_company = companyDAO.store(related_company);
		companyDAO.flush();

		return project;
	}

	@Transactional
	public Project saveProjectStaff(Integer id, Staff related_staff) {
		Project project = projectDAO.findProjectByPrimaryKey(id, -1, -1);
		Staff existingStaff = staffDAO.findStaffByPrimaryKey(related_staff
				.getId());
		if (existingStaff != null) {
			existingStaff.setId(related_staff.getId());
			existingStaff.setName(related_staff.getName());
			existingStaff.setDuty(related_staff.getDuty());
			existingStaff.setToken(related_staff.getToken());
			existingStaff.setType(related_staff.getType());
			existingStaff.setStatus(related_staff.getStatus());
			existingStaff.setLevel(related_staff.getLevel());
			existingStaff.setLoginname(related_staff.getLoginname());
			existingStaff.setPassword(related_staff.getPassword());
			existingStaff.setRemark(related_staff.getRemark());
			related_staff = existingStaff;
		} else {
			related_staff = staffDAO.store(related_staff);
			staffDAO.flush();
		}
		project.setRelativeStaff(related_staff);
		related_staff.getRelativeProjects().add(project);
		project = projectDAO.store(project);
		projectDAO.flush();

		related_staff = staffDAO.store(related_staff);
		staffDAO.flush();

		return project;
	}

	@Transactional
	public Project saveProjectCacs(Integer id, Cac related_cacs) {
		Project project = projectDAO.findProjectByPrimaryKey(id, -1, -1);
		Cac existingCacs = cacDAO.findCacByPrimaryKey(related_cacs.getId());
		if (existingCacs != null) {
			existingCacs.setId(related_cacs.getId());
			existingCacs.setRemark(related_cacs.getRemark());
			related_cacs = existingCacs;
			entityManager.persist(related_cacs);
		} else {
			entityManager.persist(related_cacs);
			cacDAO.flush();
		}
		entityManager.persist(project);
		related_cacs.setRelativeProject(project);
		project.getRelativeCacs().add(related_cacs);
		entityManager.persist(project);
		entityManager.persist(related_cacs);
		projectDAO.flush();

		return project;
	}

	@Transactional
	public Project saveProjectLedbuildings(Integer id,
			Ledbuilding related_ledbuildings) {
		Project project = projectDAO.findProjectByPrimaryKey(id, -1, -1);
		Ledbuilding existingLedbuildings = ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuildings.getId());
		if (existingLedbuildings != null) {
			existingLedbuildings.setId(related_ledbuildings.getId());
			existingLedbuildings.setName(related_ledbuildings.getName());
			existingLedbuildings.setWell(related_ledbuildings.getWell());
			existingLedbuildings.setStorey(related_ledbuildings.getStorey());
			related_ledbuildings = existingLedbuildings;
			entityManager.persist(related_ledbuildings);
		} else {
			entityManager.persist(related_ledbuildings);
			ledbuildingDAO.flush();
		}
		entityManager.persist(project);
		related_ledbuildings.setRelativeProject(project);
		project.getRelativeLedbuildings().add(related_ledbuildings);
		entityManager.persist(project);
		entityManager.persist(related_ledbuildings);
		projectDAO.flush();

		return project;
	}

	@Transactional
	public Project saveProjectWhbuildings(Integer id,
			Whbuilding related_whbuildings) {
		Project project = projectDAO.findProjectByPrimaryKey(id, -1, -1);
		Whbuilding existingWhbuildings = whbuildingDAO
				.findWhbuildingByPrimaryKey(related_whbuildings.getId());
		if (existingWhbuildings != null) {
			existingWhbuildings.setId(related_whbuildings.getId());
			existingWhbuildings.setName(related_whbuildings.getName());
			related_whbuildings = existingWhbuildings;
			entityManager.persist(related_whbuildings);
		} else {
			entityManager.persist(related_whbuildings);
			whbuildingDAO.flush();
		}
		entityManager.persist(project);
		related_whbuildings.setRelativeProject(project);
		project.getRelativeWhbuildings().add(related_whbuildings);
		entityManager.persist(project);
		entityManager.persist(related_whbuildings);
		projectDAO.flush();

		return project;
	}

	@Transactional
	public void deleteProject(Project project) {

		project.setRelativeCompany(null);
		project.setRelativeStaff(null);

		Set<Cac> cacSet = project.getRelativeCacs();
		project.setRelativeCacs(null);
		Set<Ledbuilding> ledbuildingSet = project.getRelativeLedbuildings();
		project.setRelativeLedbuildings(null);
		Set<Whbuilding> whbuildingSet = project.getRelativeWhbuildings();
		project.setRelativeWhbuildings(null);
		entityManager.persist(project);
		projectDAO.remove(project);
		projectDAO.flush();
	}

	@Transactional
	public Project deleteProjectCompany(Integer project_id,
			Integer related_company_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		Company related_company = companyDAO.findCompanyByPrimaryKey(
				related_company_id, -1, -1);
		project.setRelativeCompany(null);
		related_company.getRelativeProjects().remove(project);
		project = projectDAO.store(project);
		projectDAO.flush();
		related_company = companyDAO.store(related_company);

		companyDAO.flush();
		return project;
	}

	@Transactional
	public Project deleteProjectStaff(Integer project_id,
			Integer related_staff_id) {
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		Staff related_staff = staffDAO.findStaffByPrimaryKey(related_staff_id,
				-1, -1);
		project.setRelativeStaff(null);
		related_staff.getRelativeProjects().remove(project);
		project = projectDAO.store(project);
		projectDAO.flush();
		related_staff = staffDAO.store(related_staff);

		staffDAO.flush();
		return project;
	}

	@Transactional
	public Project deleteProjectCacs(Integer project_id, Integer related_cacs_id) {
		Cac related_cacs = cacDAO.findCacByPrimaryKey(related_cacs_id, -1, -1);
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		Set<Cac> cacSet = project.getRelativeCacs();
		Cac teache = new Cac();
		if (cacSet.size() > 0)
			for (Cac th : cacSet) {
				if (th == related_cacs) {
					teache = related_cacs;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeProject(null);
			project.getRelativeCacs().remove(teache);
		}

		entityManager.persist(related_cacs);
		entityManager.persist(project);
		projectDAO.flush();
		return project;
	}

	@Transactional
	public Project deleteProjectLedbuildings(Integer project_id,
			Integer related_ledbuildings_id) {
		Ledbuilding related_ledbuildings = ledbuildingDAO
				.findLedbuildingByPrimaryKey(related_ledbuildings_id, -1, -1);
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		Set<Ledbuilding> ledbuildingSet = project.getRelativeLedbuildings();
		Ledbuilding teache = new Ledbuilding();
		if (ledbuildingSet.size() > 0)
			for (Ledbuilding th : ledbuildingSet) {
				if (th == related_ledbuildings) {
					teache = related_ledbuildings;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeProject(null);
			project.getRelativeLedbuildings().remove(teache);
		}

		entityManager.persist(related_ledbuildings);
		entityManager.persist(project);
		projectDAO.flush();
		return project;
	}

	@Transactional
	public Project deleteProjectWhbuildings(Integer project_id,
			Integer related_whbuildings_id) {
		Whbuilding related_whbuildings = whbuildingDAO
				.findWhbuildingByPrimaryKey(related_whbuildings_id, -1, -1);
		Project project = projectDAO
				.findProjectByPrimaryKey(project_id, -1, -1);
		Set<Whbuilding> whbuildingSet = project.getRelativeWhbuildings();
		Whbuilding teache = new Whbuilding();
		if (whbuildingSet.size() > 0)
			for (Whbuilding th : whbuildingSet) {
				if (th == related_whbuildings) {
					teache = related_whbuildings;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeProject(null);
			project.getRelativeWhbuildings().remove(teache);
		}

		entityManager.persist(related_whbuildings);
		entityManager.persist(project);
		projectDAO.flush();
		return project;
	}

	@Transactional
	public Set<Project> loadProjects() {
		return projectDAO.findAllProjects();

	}

	@Transactional
	public Set<Project> loadProjects(int index, int size) {
		return projectDAO.findAllProjects(index, size);
	}

	@Transactional
	public List<Project> findAllProjects(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Project>(projectDAO.findAllProjects(
				startResult, maxRows));
	}

	@Transactional
	public Project findProjectByPrimaryKey(Integer id

	) {
		return projectDAO.findProjectByPrimaryKey(id);
	}

	@Transactional
	public Integer countprojects() {
		return ((Long) projectDAO.createQuerySingleResult(
				"select count(o) from Project o").getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeCompanyProjects(Integer company) {
		String sql = "select count(*) from Project where company=" + company;
		return ((Long) projectDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeStaffProjects(Integer staff) {
		String sql = "select count(*) from Project where staff=" + staff;
		return ((Long) projectDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
