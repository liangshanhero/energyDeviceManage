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

@Repository("CacmalfunctionDAO")
@Transactional
public class CacmalfunctionDAOImpl extends AbstractJpaDao<Cacmalfunction>
		implements CacmalfunctionDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacmalfunction.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacmalfunctionDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByCacrecordtime(
			Integer cacrecordtime) throws DataAccessException {

		return findCacmalfunctionByCacrecordtime(cacrecordtime, -1, -1);
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacmalfunctionByCacrecordtime",
					startResult, maxRows, cacrecordtime);
			return (cn.edu.scau.cmi.domain.Cacmalfunction) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByCacdevice(Integer cacdevice)
			throws DataAccessException {

		return findCacmalfunctionByCacdevice(cacdevice, -1, -1);
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByCacdevice(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacmalfunctionByCacdevice",
					startResult, maxRows, cacdevice);
			return (cn.edu.scau.cmi.domain.Cacmalfunction) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacmalfunction> findCacmalfunctionByStatus(String status)
			throws DataAccessException {

		return findCacmalfunctionByStatus(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacmalfunction> findCacmalfunctionByStatus(String status,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacmalfunctionByStatus",
				startResult, maxRows, status);
		return new LinkedHashSet<Cacmalfunction>(query.getResultList());
	}

	@Transactional
	public Set<Cacmalfunction> findCacmalfunctionByStatusContaining(
			String status) throws DataAccessException {

		return findCacmalfunctionByStatusContaining(status, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacmalfunction> findCacmalfunctionByStatusContaining(
			String status, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacmalfunctionByStatusContaining",
				startResult, maxRows, status);
		return new LinkedHashSet<Cacmalfunction>(query.getResultList());
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime,
			Integer cacdevice) throws DataAccessException {
		return findCacmalfunctionByPrimaryKey(cacrecordtime, cacdevice, -1, -1);
	}

	@Transactional
	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime,
			Integer cacdevice, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacmalfunctionByPrimaryKey",
					startResult, maxRows, cacrecordtime, cacdevice);
			return (cn.edu.scau.cmi.domain.Cacmalfunction) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacmalfunction> findAllCacmalfunctions()
			throws DataAccessException {

		return findAllCacmalfunctions(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacmalfunction> findAllCacmalfunctions(int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllCacmalfunctions", startResult,
				maxRows);
		return new LinkedHashSet<Cacmalfunction>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacmalfunction> findCacdeviceCacmalfunctions(Integer cacdevice,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllCacdeviceCacmalfunctions",
				start, max, cacdevice);
		return new LinkedHashSet<Cacmalfunction>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacmalfunction> findCacrecordtimeCacmalfunctions(
			Integer cacrecordtime, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacrecordtimeCacmalfunctions",
				start, max, cacrecordtime);
		return new LinkedHashSet<Cacmalfunction>(query.getResultList());
	}

	public boolean canBeMerged(Cacmalfunction entity) {
		return true;
	}
}