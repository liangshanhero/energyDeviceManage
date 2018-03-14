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

@Repository("ProjectDAO")
@Transactional
public class ProjectDAOImpl extends AbstractJpaDao<Project> implements
		ProjectDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Project.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public ProjectDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Project findProjectById(Integer id) throws DataAccessException {

		return findProjectById(id, -1, -1);
	}

	@Transactional
	public Project findProjectById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findProjectById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Project) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Project> findProjectByName(String name)
			throws DataAccessException {

		return findProjectByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByNameContaining(String name)
			throws DataAccessException {

		return findProjectByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByType(String type)
			throws DataAccessException {

		return findProjectByType(type, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByType(String type, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByType", startResult,
				maxRows, type);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByTypeContaining(String type)
			throws DataAccessException {

		return findProjectByTypeContaining(type, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByTypeContaining(String type,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByTypeContaining",
				startResult, maxRows, type);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByStatus(String status)
			throws DataAccessException {

		return findProjectByStatus(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByStatus(String status, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByStatus", startResult,
				maxRows, status);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByStatusContaining(String status)
			throws DataAccessException {

		return findProjectByStatusContaining(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByStatusContaining(String status,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByStatusContaining",
				startResult, maxRows, status);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByProvince(String province)
			throws DataAccessException {

		return findProjectByProvince(province, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByProvince(String province, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByProvince", startResult,
				maxRows, province);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByProvinceContaining(String province)
			throws DataAccessException {

		return findProjectByProvinceContaining(province, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByProvinceContaining(String province,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByProvinceContaining",
				startResult, maxRows, province);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByCity(String city)
			throws DataAccessException {

		return findProjectByCity(city, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByCity(String city, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByCity", startResult,
				maxRows, city);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByCityContaining(String city)
			throws DataAccessException {

		return findProjectByCityContaining(city, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByCityContaining(String city,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByCityContaining",
				startResult, maxRows, city);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByArea(String area)
			throws DataAccessException {

		return findProjectByArea(area, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByArea(String area, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByArea", startResult,
				maxRows, area);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByAreaContaining(String area)
			throws DataAccessException {

		return findProjectByAreaContaining(area, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByAreaContaining(String area,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByAreaContaining",
				startResult, maxRows, area);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByAddress(String address)
			throws DataAccessException {

		return findProjectByAddress(address, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByAddress(String address, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByAddress", startResult,
				maxRows, address);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByAddressContaining(String address)
			throws DataAccessException {

		return findProjectByAddressContaining(address, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByAddressContaining(String address,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByAddressContaining",
				startResult, maxRows, address);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByDetail(String detail)
			throws DataAccessException {

		return findProjectByDetail(detail, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByDetail(String detail, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByDetail", startResult,
				maxRows, detail);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByDetailContaining(String detail)
			throws DataAccessException {

		return findProjectByDetailContaining(detail, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByDetailContaining(String detail,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByDetailContaining",
				startResult, maxRows, detail);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByRemark(String remark)
			throws DataAccessException {

		return findProjectByRemark(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByRemark", startResult,
				maxRows, remark);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Set<Project> findProjectByRemarkContaining(String remark)
			throws DataAccessException {

		return findProjectByRemarkContaining(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findProjectByRemarkContaining(String remark,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findProjectByRemarkContaining",
				startResult, maxRows, remark);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@Transactional
	public Project findProjectByPrimaryKey(Integer id)
			throws DataAccessException {
		return findProjectByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Project findProjectByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findProjectByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Project) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Project> findAllProjects() throws DataAccessException {

		return findAllProjects(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Project> findAllProjects(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllProjects", startResult, maxRows);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Project> findCompanyProjects(Integer company, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCompanyProjects", start, max,
				company);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Project> findStaffProjects(Integer staff, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllStaffProjects", start, max,
				staff);
		return new LinkedHashSet<Project>(query.getResultList());
	}

	public boolean canBeMerged(Project entity) {
		return true;
	}
}