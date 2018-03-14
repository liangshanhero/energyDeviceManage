package cn.edu.scau.cmi.service;

import cn.edu.scau.cmi.dao.StaffDAO;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Service("StaffService")
@Transactional
public class StaffServiceImpl implements StaffService {
	@Autowired
	private StaffDAO staffDAO;

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private ProjectDAO projectDAO;

	@Autowired
	private ProjectService projectService;

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public StaffServiceImpl() {
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Transactional
	public void saveStaff(Staff staff) {
		Staff existingStaff = staffDAO.findStaffByPrimaryKey(staff.getId());
		if (existingStaff != null) {
			if (existingStaff != staff) {
				existingStaff.setName(staff.getName());
				existingStaff.setDuty(staff.getDuty());
				existingStaff.setToken(staff.getToken());
				existingStaff.setType(staff.getType());
				existingStaff.setStatus(staff.getStatus());
				existingStaff.setLevel(staff.getLevel());
				existingStaff.setLoginname(staff.getLoginname());
				existingStaff.setPassword(staff.getPassword());
				existingStaff.setRemark(staff.getRemark());
			}

			if (staff.getRelativeCompany() != null) {
				entityManager.persist(staff.getRelativeCompany());
				existingStaff.setRelativeCompany(staff.getRelativeCompany());
			} else
				existingStaff.setRelativeCompany(null);

			if (staff.getRelativeCompanys() != null) {
				for (Company relativeCompany : staff.getRelativeCompanys()) {
					relativeCompany.setRelativeStaff(existingStaff);
					entityManager.persist(relativeCompany);
				}
				existingStaff.setRelativeCompanys(staff.getRelativeCompanys());
			} else
				existingStaff.setRelativeCompanys(null);
			if (staff.getRelativeProjects() != null) {
				for (Project relativeProject : staff.getRelativeProjects()) {
					relativeProject.setRelativeStaff(existingStaff);
					entityManager.persist(relativeProject);
				}
				existingStaff.setRelativeProjects(staff.getRelativeProjects());
			} else
				existingStaff.setRelativeProjects(null);
			entityManager.persist(existingStaff);
		} else {
			entityManager.persist(staff);
			staff = staffDAO.store(staff);
		}
		staffDAO.flush();
	}

	@Transactional
	public Staff saveStaffCompany(Integer id, Company related_company) {
		Staff staff = staffDAO.findStaffByPrimaryKey(id, -1, -1);
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
		staff.setRelativeCompany(related_company);
		related_company.getRelativeStaffs().add(staff);
		staff = staffDAO.store(staff);
		staffDAO.flush();

		related_company = companyDAO.store(related_company);
		companyDAO.flush();

		return staff;
	}

	@Transactional
	public Staff saveStaffCompanys(Integer id, Company related_companys) {
		Staff staff = staffDAO.findStaffByPrimaryKey(id, -1, -1);
		Company existingCompanys = companyDAO
				.findCompanyByPrimaryKey(related_companys.getId());
		if (existingCompanys != null) {
			existingCompanys.setId(related_companys.getId());
			existingCompanys.setName(related_companys.getName());
			existingCompanys.setPhone(related_companys.getPhone());
			existingCompanys.setFax(related_companys.getFax());
			existingCompanys.setPostcode(related_companys.getPostcode());
			existingCompanys.setAddress(related_companys.getAddress());
			existingCompanys.setWebsite(related_companys.getWebsite());
			existingCompanys.setPersonduty(related_companys.getPersonduty());
			existingCompanys.setDetail(related_companys.getDetail());
			existingCompanys.setRemark(related_companys.getRemark());
			related_companys = existingCompanys;
			entityManager.persist(related_companys);
		} else {
			entityManager.persist(related_companys);
			companyDAO.flush();
		}
		entityManager.persist(staff);
		related_companys.setRelativeStaff(staff);
		staff.getRelativeCompanys().add(related_companys);
		entityManager.persist(staff);
		entityManager.persist(related_companys);
		staffDAO.flush();

		return staff;
	}

	@Transactional
	public Staff saveStaffProjects(Integer id, Project related_projects) {
		Staff staff = staffDAO.findStaffByPrimaryKey(id, -1, -1);
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
		entityManager.persist(staff);
		related_projects.setRelativeStaff(staff);
		staff.getRelativeProjects().add(related_projects);
		entityManager.persist(staff);
		entityManager.persist(related_projects);
		staffDAO.flush();

		return staff;
	}
	
//=================该代码已经修改了！============
	@Transactional
	public void deleteStaff(Staff staff) {
		
		System.out.println("staff.getId():"+staff.getId());
		if(staffDAO.findStaffByPrimaryKey(staff.getId()) == null){
			//如果已经删除了，则不再执行删除操作，然后跳转到其他页面
			System.out.println("数据库没有改用户");
		}
		else{
		//staff.setRelativeCompany(null);
		staff.setRelativeCompanys(null);
		staff.setRelativeProjects(null);
		entityManager.persist(staff);
		staffDAO.remove(staff);
		staffDAO.flush();
		}
	}
//================end该代码修改=============
	@Transactional
	public Staff deleteStaffCompany(Integer staff_id, Integer related_company_id) {
		Staff staff = staffDAO.findStaffByPrimaryKey(staff_id, -1, -1);
		Company related_company = companyDAO.findCompanyByPrimaryKey(
				related_company_id, -1, -1);
		staff.setRelativeCompany(null);
		related_company.getRelativeStaffs().remove(staff);
		staff = staffDAO.store(staff);
		staffDAO.flush();
		related_company = companyDAO.store(related_company);

		companyDAO.flush();
		return staff;
	}

	@Transactional
	public Staff deleteStaffCompanys(Integer staff_id,
			Integer related_companys_id) {
		Company related_companys = companyDAO.findCompanyByPrimaryKey(
				related_companys_id, -1, -1);
		Staff staff = staffDAO.findStaffByPrimaryKey(staff_id, -1, -1);
		Set<Company> companySet = staff.getRelativeCompanys();
		Company teache = new Company();
		if (companySet.size() > 0)
			for (Company th : companySet) {
				if (th == related_companys) {
					teache = related_companys;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeStaff(null);
			staff.getRelativeCompanys().remove(teache);
		}

		entityManager.persist(related_companys);
		entityManager.persist(staff);
		staffDAO.flush();
		return staff;
	}

	@Transactional
	public Staff deleteStaffProjects(Integer staff_id,
			Integer related_projects_id) {
		Project related_projects = projectDAO.findProjectByPrimaryKey(
				related_projects_id, -1, -1);
		Staff staff = staffDAO.findStaffByPrimaryKey(staff_id, -1, -1);
		Set<Project> projectSet = staff.getRelativeProjects();
		Project teache = new Project();
		if (projectSet.size() > 0)
			for (Project th : projectSet) {
				if (th == related_projects) {
					teache = related_projects;
					entityManager.persist(teache);
				}
			}
		if (teache != null) {
			teache.setRelativeStaff(null);
			staff.getRelativeProjects().remove(teache);
		}

		entityManager.persist(related_projects);
		entityManager.persist(staff);
		staffDAO.flush();
		return staff;
	}

	@Transactional
	public Set<Staff> loadStaffs() {
		return staffDAO.findAllStaffs();

	}

	@Transactional
	public Set<Staff> loadStaffs(int index, int size) {
		return staffDAO.findAllStaffs(index, size);
	}

	@Transactional
	public List<Staff> findAllStaffs(Integer startResult, Integer maxRows) {
		return new java.util.ArrayList<Staff>(staffDAO.findAllStaffs(
				startResult, maxRows));
	}

	@Transactional
	public Staff findStaffByPrimaryKey(Integer id

	) {
		return staffDAO.findStaffByPrimaryKey(id);
	}

	@Transactional
	public Integer countstaffs() {
		return ((Long) staffDAO.createQuerySingleResult(
				"select count(o) from Staff o").getSingleResult()).intValue();
	}

	@Transactional
	public Integer countRelativeCompanyStaffs(Integer company) {
		String sql = "select count(*) from Staff where company=" + company;
		return ((Long) staffDAO.createQuerySingleResult(sql).getSingleResult())
				.intValue();
	}

}
