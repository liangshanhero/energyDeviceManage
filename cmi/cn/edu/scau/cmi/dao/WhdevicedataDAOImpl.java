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

@Repository("WhdevicedataDAO")
@Transactional
public class WhdevicedataDAOImpl extends AbstractJpaDao<Whdevicedata> implements
		WhdevicedataDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whdevicedata.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdevicedataDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whdevicedata findWhdevicedataById(Integer id)
			throws DataAccessException {

		return findWhdevicedataById(id, -1, -1);
	}

	@Transactional
	public Whdevicedata findWhdevicedataById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdevicedataById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdevicedata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByTime(Date time)
			throws DataAccessException {

		return findWhdevicedataByTime(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByTime(Date time, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByTime", startResult,
				maxRows, time);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByTimeContaining(Date time)
			throws DataAccessException {

		return findWhdevicedataByTimeContaining(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByTimeContaining(Date time,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByTimeContaining",
				startResult, maxRows, time);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByValue(BigDecimal value)
			throws DataAccessException {

		return findWhdevicedataByValue(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByValue", startResult,
				maxRows, value);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByValueContaining(BigDecimal value)
			throws DataAccessException {

		return findWhdevicedataByValueContaining(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByValueContaining(
			BigDecimal value, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByValueContaining",
				startResult, maxRows, value);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsupdate(Integer isupdate)
			throws DataAccessException {

		return findWhdevicedataByIsupdate(isupdate, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsupdate(Integer isupdate,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByIsupdate",
				startResult, maxRows, isupdate);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsupdateContaining(
			Integer isupdate) throws DataAccessException {

		return findWhdevicedataByIsupdateContaining(isupdate, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsupdateContaining(
			Integer isupdate, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByIsupdateContaining",
				startResult, maxRows, isupdate);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsio(Integer isio)
			throws DataAccessException {

		return findWhdevicedataByIsio(isio, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsio(Integer isio,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByIsio", startResult,
				maxRows, isio);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsioContaining(Integer isio)
			throws DataAccessException {

		return findWhdevicedataByIsioContaining(isio, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findWhdevicedataByIsioContaining(Integer isio,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdevicedataByIsioContaining",
				startResult, maxRows, isio);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@Transactional
	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id)
			throws DataAccessException {
		return findWhdevicedataByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdevicedataByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdevicedata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdevicedata> findAllWhdevicedatas() throws DataAccessException {

		return findAllWhdevicedatas(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevicedata> findAllWhdevicedatas(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhdevicedatas", startResult,
				maxRows);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whdevicedata> findWhdatatypeWhdevicedatas(Integer whdatatype,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdatatypeWhdevicedatas", start,
				max, whdatatype);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whdevicedata> findWhdeviceWhdevicedatas(Integer whdevice,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdeviceWhdevicedatas", start,
				max, whdevice);
		return new LinkedHashSet<Whdevicedata>(query.getResultList());
	}

	public boolean canBeMerged(Whdevicedata entity) {
		return true;
	}
}