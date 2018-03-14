package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CompanyService {

	public void saveCompany(Company company);

	public Company saveCompanyStaff(Integer id, Staff related_staff);

	public Company saveCompanyProjects(Integer id, Project related_projects);

	public Company saveCompanyStaffs(Integer id, Staff related_staffs);

	public Set<Company> loadCompanys();

	public Set<Company> loadCompanys(int index, int size);

	public void deleteCompany(Company company);

	public Company deleteCompanyStaff(Integer id, Integer related_staff_id);

	public Company deleteCompanyProjects(Integer id, Integer related_projects_id);

	public Company deleteCompanyStaffs(Integer id, Integer related_staffs_id);

	public List<Company> findAllCompanys(Integer startResult, Integer maxRows);

	public Company findCompanyByPrimaryKey(Integer id);

	public Integer countcompanys();

	public Integer countRelativeStaffCompanys(Integer staff);

}