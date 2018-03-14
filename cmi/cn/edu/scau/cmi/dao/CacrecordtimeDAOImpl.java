package cn.edu.scau.cmi.dao;

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

@Repository("CacrecordtimeDAO")
@Transactional
public class CacrecordtimeDAOImpl extends AbstractJpaDao<Cacrecordtime>
		implements CacrecordtimeDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacrecordtime.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacrecordtimeDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacrecordtime findCacrecordtimeById(Integer id)
			throws DataAccessException {

		return findCacrecordtimeById(id, -1, -1);
	}

	@Transactional
	public Cacrecordtime findCacrecordtimeById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacrecordtimeById",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacrecordtime) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByRecordTime(Date RecordTime)
			throws DataAccessException {

		return findCacrecordtimeByRecordTime(RecordTime, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByRecordTime(Date RecordTime,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacrecordtimeByRecordTime",
				startResult, maxRows, RecordTime);
		return new LinkedHashSet<Cacrecordtime>(query.getResultList());
	}

	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByRecordTimeContaining(
			Date RecordTime) throws DataAccessException {

		return findCacrecordtimeByRecordTimeContaining(RecordTime, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByRecordTimeContaining(
			Date RecordTime, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery(
				"findCacrecordtimeByRecordTimeContaining", startResult,
				maxRows, RecordTime);
		return new LinkedHashSet<Cacrecordtime>(query.getResultList());
	}

	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeper(String Watchkeeper)
			throws DataAccessException {

		return findCacrecordtimeByWatchkeeper(Watchkeeper, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeper(
			String Watchkeeper, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacrecordtimeByWatchkeeper",
				startResult, maxRows, Watchkeeper);
		return new LinkedHashSet<Cacrecordtime>(query.getResultList());
	}

	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeperContaining(
			String Watchkeeper) throws DataAccessException {

		return findCacrecordtimeByWatchkeeperContaining(Watchkeeper, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeperContaining(
			String Watchkeeper, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery(
				"findCacrecordtimeByWatchkeeperContaining", startResult,
				maxRows, Watchkeeper);
		return new LinkedHashSet<Cacrecordtime>(query.getResultList());
	}

	@Transactional
	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id)
			throws DataAccessException {
		return findCacrecordtimeByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacrecordtimeByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacrecordtime) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacrecordtime> findAllCacrecordtimes()
			throws DataAccessException {

		return findAllCacrecordtimes(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacrecordtime> findAllCacrecordtimes(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacrecordtimes", startResult,
				maxRows);
		return new LinkedHashSet<Cacrecordtime>(query.getResultList());
	}

	public boolean canBeMerged(Cacrecordtime entity) {
		return true;
	}
}