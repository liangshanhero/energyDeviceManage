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

@Repository("CacdeviceDAO")
@Transactional
public class CacdeviceDAOImpl extends AbstractJpaDao<Cacdevice> implements
		CacdeviceDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacdevice.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacdeviceDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacdevice findCacdeviceById(Integer id) throws DataAccessException {

		return findCacdeviceById(id, -1, -1);
	}

	@Transactional
	public Cacdevice findCacdeviceById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdeviceById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacdevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacdevice> findCacdeviceByName(String name)
			throws DataAccessException {

		return findCacdeviceByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevice> findCacdeviceByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdeviceByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevice> findCacdeviceByNameContaining(String name)
			throws DataAccessException {

		return findCacdeviceByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevice> findCacdeviceByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdeviceByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevice> findCacdeviceByUnit(String unit)
			throws DataAccessException {

		return findCacdeviceByUnit(unit, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevice> findCacdeviceByUnit(String unit, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdeviceByUnit", startResult,
				maxRows, unit);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	@Transactional
	public Set<Cacdevice> findCacdeviceByUnitContaining(String unit)
			throws DataAccessException {

		return findCacdeviceByUnitContaining(unit, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevice> findCacdeviceByUnitContaining(String unit,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacdeviceByUnitContaining",
				startResult, maxRows, unit);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	@Transactional
	public Cacdevice findCacdeviceByPrimaryKey(Integer id)
			throws DataAccessException {
		return findCacdeviceByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Cacdevice findCacdeviceByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacdeviceByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacdevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacdevice> findAllCacdevices() throws DataAccessException {

		return findAllCacdevices(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacdevice> findAllCacdevices(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacdevices", startResult,
				maxRows);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacdevice> findCacCacdevices(Integer cac, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacCacdevices", start, max, cac);
		return new LinkedHashSet<Cacdevice>(query.getResultList());
	}

	public boolean canBeMerged(Cacdevice entity) {
		return true;
	}
}