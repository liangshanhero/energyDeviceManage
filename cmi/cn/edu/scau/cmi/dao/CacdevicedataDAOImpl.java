package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.domain.*;

@Repository("CacdevicedataDAO")
@Transactional
public class CacdevicedataDAOImpl extends AbstractJpaDao<Cacdevicedata>
		implements CacdevicedataDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacdevicedata.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacdevicedataDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByCacdevice(Integer cacdevice)
			throws DataAccessException {

		return findCacdevicedataByCacdevice(cacdevice, -1, -1);
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByCacdevice(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdevicedataByCacdevice",
					startResult, maxRows, cacdevice);
			return (cn.edu.scau.cmi.domain.Cacdevicedata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cacdevicedata> findCacdevicedataByCacdevice1(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdevicedataByCacdevice",
					startResult, maxRows, cacdevice);
			return  query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByCacrecordtime(Integer cacrecordtime)
			throws DataAccessException {

		return findCacdevicedataByCacrecordtime(cacrecordtime, -1, -1);
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdevicedataByCacrecordtime",
					startResult, maxRows, cacrecordtime);
			return (cn.edu.scau.cmi.domain.Cacdevicedata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByValue(BigDecimal value)
			throws DataAccessException {

		return findCacdevicedataByValue(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdevicedataByValue", startResult,
				maxRows, value);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByValueContaining(
			BigDecimal value) throws DataAccessException {

		return findCacdevicedataByValueContaining(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByValueContaining(
			BigDecimal value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacdevicedataByValueContaining",
				startResult, maxRows, value);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByIsreport(Integer isreport)
			throws DataAccessException {

		return findCacdevicedataByIsreport(isreport, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByIsreport(Integer isreport,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdevicedataByIsreport",
				startResult, maxRows, isreport);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByIsreportContaining(
			Integer isreport) throws DataAccessException {

		return findCacdevicedataByIsreportContaining(isreport, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevicedata> findCacdevicedataByIsreportContaining(
			Integer isreport, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacdevicedataByIsreportContaining",
				startResult, maxRows, isreport);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice,
			Integer cacrecordtime) throws DataAccessException {
		return findCacdevicedataByPrimaryKey(cacdevice, cacrecordtime, -1, -1);
	}

	@Transactional
	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice,
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdevicedataByPrimaryKey",
					startResult, maxRows, cacdevice, cacrecordtime);
			return (cn.edu.scau.cmi.domain.Cacdevicedata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacdevicedata> findAllCacdevicedatas()
			throws DataAccessException {

		return findAllCacdevicedatas(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevicedata> findAllCacdevicedatas(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacdevicedatas", startResult,
				maxRows);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacdevicedata> findCacdeviceCacdevicedatas(Integer cacdevice,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllCacdeviceCacdevicedatas", start,
				max, cacdevice);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacdevicedata> findCacrecordtimeCacdevicedatas(
			Integer cacrecordtime, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacrecordtimeCacdevicedatas",
				start, max, cacrecordtime);
		return new LinkedHashSet<Cacdevicedata>(query.getResultList());
	}

	public boolean canBeMerged(Cacdevicedata entity) {
		return true;
	}
}