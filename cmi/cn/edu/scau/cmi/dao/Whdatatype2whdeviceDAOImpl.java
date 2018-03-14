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

@Repository("Whdatatype2whdeviceDAO")
@Transactional
public class Whdatatype2whdeviceDAOImpl extends
		AbstractJpaDao<Whdatatype2whdevice> implements Whdatatype2whdeviceDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whdatatype2whdevice.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public Whdatatype2whdeviceDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdatatype(
			Integer whdatatype) throws DataAccessException {

		return findWhdatatype2whdeviceByWhdatatype(whdatatype, -1, -1);
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdatatype(
			Integer whdatatype, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery(
					"findWhdatatype2whdeviceByWhdatatype", startResult,
					maxRows, whdatatype);
			return (cn.edu.scau.cmi.domain.Whdatatype2whdevice) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdevice(
			Integer whdevice) throws DataAccessException {

		return findWhdatatype2whdeviceByWhdevice(whdevice, -1, -1);
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdevice(
			Integer whdevice, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdatatype2whdeviceByWhdevice",
					startResult, maxRows, whdevice);
			return (cn.edu.scau.cmi.domain.Whdatatype2whdevice) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByPrimaryKey(
			Integer whdatatype, Integer whdevice) throws DataAccessException {
		return findWhdatatype2whdeviceByPrimaryKey(whdatatype, whdevice, -1, -1);
	}

	@Transactional
	public Whdatatype2whdevice findWhdatatype2whdeviceByPrimaryKey(
			Integer whdatatype, Integer whdevice, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery(
					"findWhdatatype2whdeviceByPrimaryKey", startResult,
					maxRows, whdatatype, whdevice);
			return (cn.edu.scau.cmi.domain.Whdatatype2whdevice) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdatatype2whdevice> findAllWhdatatype2whdevices()
			throws DataAccessException {

		return findAllWhdatatype2whdevices(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdatatype2whdevice> findAllWhdatatype2whdevices(
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdatatype2whdevices",
				startResult, maxRows);
		return new LinkedHashSet<Whdatatype2whdevice>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whdatatype2whdevice> findWhdatatypeWhdatatype2whdevices(
			Integer whdatatype, int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdatatypeWhdatatype2whdevices",
				start, max, whdatatype);
		return new LinkedHashSet<Whdatatype2whdevice>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whdatatype2whdevice> findWhdeviceWhdatatype2whdevices(
			Integer whdevice, int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdeviceWhdatatype2whdevices",
				start, max, whdevice);
		return new LinkedHashSet<Whdatatype2whdevice>(query.getResultList());
	}

	public boolean canBeMerged(Whdatatype2whdevice entity) {
		return true;
	}
}