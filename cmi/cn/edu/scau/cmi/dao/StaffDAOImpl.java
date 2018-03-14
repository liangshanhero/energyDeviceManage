package cn.edu.scau.cmi.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.domain.*;

@Repository("StaffDAO")
@Transactional
public class StaffDAOImpl extends AbstractJpaDao<Staff> implements StaffDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Staff.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public StaffDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Staff findStaffById(Integer id) throws DataAccessException {

		return findStaffById(id, -1, -1);
	}

	@Transactional
	public Staff findStaffById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findStaffById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Staff) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Staff> findStaffByName(String name) throws DataAccessException {

		return findStaffByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findStaffByName", startResult, maxRows,
				name);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByNameContaining(String name)
			throws DataAccessException {

		return findStaffByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByNameContaining(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByDuty(String duty) throws DataAccessException {

		return findStaffByDuty(duty, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByDuty(String duty, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findStaffByDuty", startResult, maxRows,
				duty);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByDutyContaining(String duty)
			throws DataAccessException {

		return findStaffByDutyContaining(duty, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByDutyContaining(String duty, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByDutyContaining",
				startResult, maxRows, duty);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByToken(String token) throws DataAccessException {

		return findStaffByToken(token, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByToken(String token, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByToken", startResult,
				maxRows, token);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByTokenContaining(String token)
			throws DataAccessException {

		return findStaffByTokenContaining(token, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByTokenContaining(String token, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByTokenContaining",
				startResult, maxRows, token);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByType(String type) throws DataAccessException {

		return findStaffByType(type, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByType(String type, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findStaffByType", startResult, maxRows,
				type);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByTypeContaining(String type)
			throws DataAccessException {

		return findStaffByTypeContaining(type, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByTypeContaining(String type, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByTypeContaining",
				startResult, maxRows, type);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByStatus(String status)
			throws DataAccessException {

		return findStaffByStatus(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByStatus(String status, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByStatus", startResult,
				maxRows, status);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByStatusContaining(String status)
			throws DataAccessException {

		return findStaffByStatusContaining(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByStatusContaining(String status,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByStatusContaining",
				startResult, maxRows, status);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByLevel(String level) throws DataAccessException {

		return findStaffByLevel(level, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByLevel(String level, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByLevel", startResult,
				maxRows, level);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByLevelContaining(String level)
			throws DataAccessException {

		return findStaffByLevelContaining(level, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByLevelContaining(String level, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByLevelContaining",
				startResult, maxRows, level);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByLoginname(String loginname)
			throws DataAccessException {

		return findStaffByLoginname(loginname, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByLoginname(String loginname, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByLoginname", startResult,
				maxRows, loginname);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByLoginnameContaining(String loginname)
			throws DataAccessException {

		return findStaffByLoginnameContaining(loginname, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByLoginnameContaining(String loginname,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByLoginnameContaining",
				startResult, maxRows, loginname);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByPassword(String password)
			throws DataAccessException {

		return findStaffByPassword(password, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByPassword(String password, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByPassword", startResult,
				maxRows, password);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByPasswordContaining(String password)
			throws DataAccessException {

		return findStaffByPasswordContaining(password, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByPasswordContaining(String password,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByPasswordContaining",
				startResult, maxRows, password);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByRemark(String remark)
			throws DataAccessException {

		return findStaffByRemark(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByRemark", startResult,
				maxRows, remark);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Set<Staff> findStaffByRemarkContaining(String remark)
			throws DataAccessException {

		return findStaffByRemarkContaining(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findStaffByRemarkContaining(String remark,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findStaffByRemarkContaining",
				startResult, maxRows, remark);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@Transactional
	public Staff findStaffByPrimaryKey(Integer id) throws DataAccessException {
		return findStaffByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Staff findStaffByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findStaffByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Staff) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Staff> findAllStaffs() throws DataAccessException {

		return findAllStaffs(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Staff> findAllStaffs(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllStaffs", startResult, maxRows);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Staff> findCompanyStaffs(Integer company, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCompanyStaffs", start, max,
				company);
		return new LinkedHashSet<Staff>(query.getResultList());
	}

	public boolean canBeMerged(Staff entity) {
		return true;
	}
}