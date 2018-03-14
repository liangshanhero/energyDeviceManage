package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.CompanyDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("CompanyService")
@Transactional
public class CompanyServiceImpl implements CompanyService {
	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private StaffService staffService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CompanyServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveCompany(Company company) {
		Company existingCompany = companyDAO.findCompanyByPrimaryKey(company
				.getId());
		if (existingCompany != null) {
			if (existingCompany != company) {
				existingCompany.setName(company.getName());
				existingCompany.setPhone(company.getPhone());
				existingCompany.setFax(company.getFax());
				existingCompany.setPostcode(company.getPostcode());
				existingCompany.setAddress(company.getAddress());
				existingCompany.setWebsite(company.getWebsite());
				existingCompany.setPersonduty(company.getPersonduty());
				existingCompany.setDetail(company.getDetail());
				existingCompany.setRemark(company.getRemark());
			}

			if (company.getRelativeStaff() != null) {
				entityManager.persist(company.getRelativeStaff());
				existingCompany.setRelativeStaff(company.getRelativeStaff());
			} else
				existingCompany.setRelativeStaff(null);

			if (company.getRelativeProjects() != null) {
				for (Project relativeProject : company.getRelativeProjects()) {
					relativeProject.setRelativeCompany(existingCompany);
					entityManager.persist(relativeProject);
				}
				existingCompany.setRelativeProjects(company
						.getRelativeProjects());
			} else
				existingCompany.setRelativeProjects(null);
			if (company.getRelativeStaffs() != null) {
				for (Staff relativeStaff : company.getRelativeStaffs()) {
					relativeStaff.setRelativeCompany(existingCompany);
					entityManager.persist(relativeStaff);
				}
				existingCompany.setRelativeStaffs(company.getRelativeStaffs());
			} else
				existingCompany.setRelativeStaffs(null);
			entityManager.persist(existingCompany);
		} else {
			entityManager.persist(company);
			company = companyDAO.store(company);
		}
		companyDAO.flush();
	}

	@Transactional
	public Company saveCompanyStaff(Integer id, Staff related_staff) {
		Company company = companyDAO.findCompanyByPrimaryKey(id, -1, -1);
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
		company.setRelativeStaff(related_staff);
		related_staff.getRelativeCompanys().add(company);
		company = companyDAO.store(company);
		companyDAO.flush();

		related_staff = staffDAO.store(related_staff);
		staffDAO.flush();

		return company;
	}

	@Transactional
	public Company saveCompanyProjects(Integer id, Project related_projects) {
		Company company = companyDAO.findCompanyByPrimaryKey(id, -1, -1);
		Project existingProjects = projectDAO
				.findProjectByPrimaryKey(related_projects.getId());
		if (existingProjects != null) {
			existingProjects.setId(related_projects.getId());
			existingProjects.setName(related_projects.getName());
			existingProjects.setType(related_projects.getType());
			existingProjects.setStatus(related_projects.getStatus());
			existingProjects.setProvince(related_projects.getProvince());
			existingProjects.setCity(related_projects.getCity());
			existingProjects.setArea(related_projects.getArea());
			existingProjects.setAddress(related_projects.getAddress());
			existingProjects.setDetail(related_projects.getDetail());
			existingProjects.setRemark(related_projects.getRemark());
			related_projects = existingProjects;
			entityManager.persist(related_projects);
		} else {
			entityManager.persist(related_projects);
			projectDAO.flush();
		}
		entityManager.persist(company);
		related_projects.setRelativeCompany(company);
		company.getRelativeProjects().add(related_projects);
		entityManager.persist(company);
		entityManager.persist(related_projects);
		companyDAO.flush();

		return company;
	}

	@Transactional
	public Company saveCompanyStaffs(Integer id, Staff related_staffs) {
		Company company = companyDAO.findCompanyByPrimaryKey(id, -1, -1);
		Staff existingStaffs = staffDAO.findStaffByPrimaryKey(related_staffs
				.getId());
		if (existingStaffs != null) {
			existingStaffs.setId(related_staffs.getId());
			existingStaffs.setName(related_staffs.getName());
			existingStaffs.setDuty(related_staffs.getDuty());
			existingStaffs.setToken(related_staffs.getToken());
			existingStaffs.setType(related_staffs.getType());
			existingStaffs.setStatus(related_staffs.getStatus());
			existingStaffs.setLevel(related_staffs.getLevel());
			existingStaffs.setLoginname(related_staffs.getLoginname());
			existingStaffs.setPassword(related_staffs.getPassword());
			existingStaffs.setRemark(related_staffs.getRemark());
			related_staffs = existingStaffs;
			entityManager.persist(related_staffs);
		} else {
			entityManager.persist(related_staffs);
			staffDAO.flush();
		}
		entityManager.persist(company);
		related_staffs.setRelativeCompany(company);
		company.getRelativeStaffs().add(related_staffs);
		entityManager.persist(company);
		entityManager.persist(related_staffs);
		companyDAO.flush();

		return company;
	}

	@Transactional
	public void deleteCompany(Company company) {

		company.setRelativeStaff(null);

		Set<Project> projectSet = company.getRelativeProjects();
		company.setRelativeProjects(null);
		Set<Staff> staffSet = company.getRelativeStaffs();
		company.setRelativeStaffs(null);
		entityManager.persist(company);
		companyDAO.remove(company);
		companyDAO.flush();
	}

	@Transactional
	public Company deleteCompanyStaff(Integer company_id,
			Integer related_staff_id) {
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		Staff related_staff = staffDAO.findStaffByPrimaryKey(related_staff_id,
				-1, -1);
		company.setRelativeStaff(null);
		related_staff.getRelativeCompanys().remove(company);
		company = companyDAO.store(company);
		companyDAO.flush();
		related_staff = staffDAO.store(related_staff);

		staffDAO.flush();
		return company;
	}

	@Transactional
	public Company deleteCompanyProjects(Integer company_id,
			Integer related_projects_id) {
		Project related_projects = projectDAO.findProjectByPrimaryKey(
				related_projects_id, -1, -1);
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		Set<Project> projectSet = company.getRelativeProjects();
		Project teache = new Project();
		if (projectSet.size() > 0)
			for (Project th : projectSet) {
				if (th == related_projects) {
					teache = related_projects;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCompany(null);
			company.getRelativeProjects().remove(teache);
		}

		entityManager.persist(related_projects);
		entityManager.persist(company);
		companyDAO.flush();
		return company;
	}

	@Transactional
	public Company deleteCompanyStaffs(Integer company_id,
			Integer related_staffs_id) {
		Staff related_staffs = staffDAO.findStaffByPrimaryKey(
				related_staffs_id, -1, -1);
		Company company = companyDAO
				.findCompanyByPrimaryKey(company_id, -1, -1);
		Set<Staff> staffSet = company.getRelativeStaffs();
		Staff teache = new Staff();
		if (staffSet.size() > 0)
			for (Staff th : staffSet) {
				if (th == related_staffs) {
					teache = related_staffs;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeCompany(null);
			company.getRelativeStaffs().remove(teache);
		}

		entityManager.persist(related_staffs);
		entityManager.persist(company);
		companyDAO.flush();
		return company;
	}

	@Transactional
	public Set<Company> loadCompanys() {
		return companyDAO.findAllCompanys();

	}

	@Transactional
	public Set<Company> loadCompanys(int index, int size) {
		return companyDAO.findAllCompanys(index, size);
	}

	@Transactional
	public List<Company> findAllCompanys(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Company>(companyDAO.findAllCompanys(
				startResult, maxRows));
	}

	@Transactional
	public Company findCompanyByPrimaryKey(Integer id

	) {
		return companyDAO.findCompanyByPrimaryKey(id);
	}

	@Transactional
	public Integer countcompanys() {
		return ((Long) companyDAO.createQuerySingleResult(
				"select count(o) from Company o").getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeStaffCompanys(Integer staff) {
		String sql = "select count(*) from Company where staff=" + staff;
		return ((Long) companyDAO.createQuerySingleResult(sql)
				.getSingleResult()).intValue();
	}

}
