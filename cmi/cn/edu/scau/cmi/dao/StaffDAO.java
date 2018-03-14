package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface StaffDAO extends JpaDao<Staff> {

	public Staff findStaffById(Integer id) throws DataAccessException;

	public Staff findStaffById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findStaffByName(String name) throws DataAccessException;

	public Set<Staff> findStaffByName(String name, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findStaffByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Staff> findStaffByNameContaining(String name_1, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByDuty(String duty) throws DataAccessException;

	public Set<Staff> findStaffByDuty(String duty, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findStaffByDutyContaining(String duty_1)
			throws DataAccessException;

	public Set<Staff> findStaffByDutyContaining(String duty_1, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByToken(String token) throws DataAccessException;

	public Set<Staff> findStaffByToken(String token, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByTokenContaining(String token_1)
			throws DataAccessException;

	public Set<Staff> findStaffByTokenContaining(String token_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByType(String type) throws DataAccessException;

	public Set<Staff> findStaffByType(String type, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findStaffByTypeContaining(String type_1)
			throws DataAccessException;

	public Set<Staff> findStaffByTypeContaining(String type_1, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByStatus(String status)
			throws DataAccessException;

	public Set<Staff> findStaffByStatus(String status, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByStatusContaining(String status_1)
			throws DataAccessException;

	public Set<Staff> findStaffByStatusContaining(String status_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByLevel(String level) throws DataAccessException;

	public Set<Staff> findStaffByLevel(String level, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByLevelContaining(String level_1)
			throws DataAccessException;

	public Set<Staff> findStaffByLevelContaining(String level_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByLoginname(String loginname)
			throws DataAccessException;

	public Set<Staff> findStaffByLoginname(String loginname, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByLoginnameContaining(String loginname_1)
			throws DataAccessException;

	public Set<Staff> findStaffByLoginnameContaining(String loginname_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByPassword(String password)
			throws DataAccessException;

	public Set<Staff> findStaffByPassword(String password, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByPasswordContaining(String password_1)
			throws DataAccessException;

	public Set<Staff> findStaffByPasswordContaining(String password_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByRemark(String remark)
			throws DataAccessException;

	public Set<Staff> findStaffByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Staff> findStaffByRemarkContaining(String remark_1)
			throws DataAccessException;

	public Set<Staff> findStaffByRemarkContaining(String remark_1,
			int startResult, int maxRows) throws DataAccessException;

	public Staff findStaffByPrimaryKey(Integer id) throws DataAccessException;

	public Staff findStaffByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findAllStaffs() throws DataAccessException;

	public Set<Staff> findAllStaffs(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Staff> findCompanyStaffs(Integer Company, int start, int max);

}
