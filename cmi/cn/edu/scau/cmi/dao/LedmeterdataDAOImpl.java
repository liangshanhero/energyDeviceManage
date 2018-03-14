package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Arrays;
import java.util.Date;
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

@Repository("LedmeterdataDAO")
@Transactional
public class LedmeterdataDAOImpl extends AbstractJpaDao<Ledmeterdata> implements
		LedmeterdataDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Ledmeterdata.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedmeterdataDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Ledmeterdata findLedmeterdataById(Integer id)
			throws DataAccessException {

		return findLedmeterdataById(id, -1, -1);
	}

	@Transactional
	public Ledmeterdata findLedmeterdataById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedmeterdataById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledmeterdata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByValue(BigDecimal value)
			throws DataAccessException {

		return findLedmeterdataByValue(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterdataByValue", startResult,
				maxRows, value);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByValueContaining(BigDecimal value)
			throws DataAccessException {

		return findLedmeterdataByValueContaining(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByValueContaining(
			BigDecimal value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findLedmeterdataByValueContaining",
				startResult, maxRows, value);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByTime(Date time)
			throws DataAccessException {

		return findLedmeterdataByTime(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByTime(Date time, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterdataByTime", startResult,
				maxRows, time);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByTimeContaining(Date time)
			throws DataAccessException {

		return findLedmeterdataByTimeContaining(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeterdata> findLedmeterdataByTimeContaining(Date time,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterdataByTimeContaining",
				startResult, maxRows, time);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	@Transactional
	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id)
			throws DataAccessException {
		return findLedmeterdataByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedmeterdataByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledmeterdata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledmeterdata> findAllLedmeterdatas() throws DataAccessException {

		return findAllLedmeterdatas(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeterdata> findAllLedmeterdatas(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllLedmeterdatas", startResult,
				maxRows);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Ledmeterdata> findLedmeterLedmeterdatas(Integer ledmeter,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllLedmeterLedmeterdatas", start,
				max, ledmeter);
		return new LinkedHashSet<Ledmeterdata>(query.getResultList());
	}

	public boolean canBeMerged(Ledmeterdata entity) {
		return true;
	}
}