package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface ProjectDAO extends JpaDao<Project> {

	public Project findProjectById(Integer id) throws DataAccessException;

	public Project findProjectById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Project> findProjectByName(String name)
			throws DataAccessException;

	public Set<Project> findProjectByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Project> findProjectByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByType(String type)
			throws DataAccessException;

	public Set<Project> findProjectByType(String type, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByTypeContaining(String type_1)
			throws DataAccessException;

	public Set<Project> findProjectByTypeContaining(String type_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByStatus(String status)
			throws DataAccessException;

	public Set<Project> findProjectByStatus(String status, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByStatusContaining(String status_1)
			throws DataAccessException;

	public Set<Project> findProjectByStatusContaining(String status_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByProvince(String province)
			throws DataAccessException;

	public Set<Project> findProjectByProvince(String province, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByProvinceContaining(String province_1)
			throws DataAccessException;

	public Set<Project> findProjectByProvinceContaining(String province_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByCity(String city)
			throws DataAccessException;

	public Set<Project> findProjectByCity(String city, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByCityContaining(String city_1)
			throws DataAccessException;

	public Set<Project> findProjectByCityContaining(String city_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByArea(String area)
			throws DataAccessException;

	public Set<Project> findProjectByArea(String area, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByAreaContaining(String area_1)
			throws DataAccessException;

	public Set<Project> findProjectByAreaContaining(String area_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByAddress(String address)
			throws DataAccessException;

	public Set<Project> findProjectByAddress(String address, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByAddressContaining(String address_1)
			throws DataAccessException;

	public Set<Project> findProjectByAddressContaining(String address_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByDetail(String detail)
			throws DataAccessException;

	public Set<Project> findProjectByDetail(String detail, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByDetailContaining(String detail_1)
			throws DataAccessException;

	public Set<Project> findProjectByDetailContaining(String detail_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Project> findProjectByRemark(String remark)
			throws DataAccessException;

	public Set<Project> findProjectByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findProjectByRemarkContaining(String remark_1)
			throws DataAccessException;

	public Set<Project> findProjectByRemarkContaining(String remark_1,
			int startResult, int maxRows) throws DataAccessException;

	public Project findProjectByPrimaryKey(Integer id)
			throws DataAccessException;

	public Project findProjectByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Project> findAllProjects() throws DataAccessException;

	public Set<Project> findAllProjects(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Project> findCompanyProjects(Integer Company, int start, int max);

	public Set<Project> findStaffProjects(Integer Staff, int start, int max);

}
