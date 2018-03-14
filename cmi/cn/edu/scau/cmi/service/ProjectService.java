package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface ProjectService {

	public void saveProject(Project project);

	public Project saveProjectCompany(Integer id, Company related_company);

	public Project saveProjectStaff(Integer id, Staff related_staff);

	public Project saveProjectCacs(Integer id, Cac related_cacs);

	public Project saveProjectLedbuildings(Integer id,
			Ledbuilding related_ledbuildings);

	public Project saveProjectWhbuildings(Integer id,
			Whbuilding related_whbuildings);

	public Set<Project> loadProjects();

	public Set<Project> loadProjects(int index, int size);

	public void deleteProject(Project project);

	public Project deleteProjectCompany(Integer id, Integer related_company_id);

	public Project deleteProjectStaff(Integer id, Integer related_staff_id);

	public Project deleteProjectCacs(Integer id, Integer related_cacs_id);

	public Project deleteProjectLedbuildings(Integer id,
			Integer related_ledbuildings_id);

	public Project deleteProjectWhbuildings(Integer id,
			Integer related_whbuildings_id);

	public List<Project> findAllProjects(Integer startResult, Integer maxRows);

	public Project findProjectByPrimaryKey(Integer id);

	public Integer countprojects();

	public Integer countRelativeCompanyProjects(Integer company);

	public Integer countRelativeStaffProjects(Integer staff);

}