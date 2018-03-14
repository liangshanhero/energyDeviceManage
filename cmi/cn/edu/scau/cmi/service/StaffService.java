package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface StaffService {

	public void saveStaff(Staff staff);

	public Staff saveStaffCompany(Integer id, Company related_company);

	public Staff saveStaffCompanys(Integer id, Company related_companys);

	public Staff saveStaffProjects(Integer id, Project related_projects);

	public Set<Staff> loadStaffs();

	public Set<Staff> loadStaffs(int index, int size);

	public void deleteStaff(Staff staff);

	public Staff deleteStaffCompany(Integer id, Integer related_company_id);

	public Staff deleteStaffCompanys(Integer id, Integer related_companys_id);

	public Staff deleteStaffProjects(Integer id, Integer related_projects_id);

	public List<Staff> findAllStaffs(Integer startResult, Integer maxRows);

	public Staff findStaffByPrimaryKey(Integer id);

	public Integer countstaffs();

	public Integer countRelativeCompanyStaffs(Integer company);

}