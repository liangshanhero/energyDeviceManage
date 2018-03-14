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

@Repository("LedbuildingDAO")
@Transactional
public class LedbuildingDAOImpl extends AbstractJpaDao<Ledbuilding> implements
		LedbuildingDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Ledbuilding.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedbuildingDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Ledbuilding findLedbuildingById(Integer id)
			throws DataAccessException {

		return findLedbuildingById(id, -1, -1);
	}

	@Transactional
	public Ledbuilding findLedbuildingById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedbuildingById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledbuilding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByName(String name)
			throws DataAccessException {

		return findLedbuildingByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByNameContaining(String name)
			throws DataAccessException {

		return findLedbuildingByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByWell(Integer well)
			throws DataAccessException {

		return findLedbuildingByWell(well, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByWell(Integer well,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByWell", startResult,
				maxRows, well);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByWellContaining(Integer well)
			throws DataAccessException {

		return findLedbuildingByWellContaining(well, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByWellContaining(Integer well,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByWellContaining",
				startResult, maxRows, well);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByStorey(Integer storey)
			throws DataAccessException {

		return findLedbuildingByStorey(storey, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByStorey(Integer storey,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByStorey", startResult,
				maxRows, storey);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Ledbuilding> findLedbuildingByStoreyContaining(Integer storey)
			throws DataAccessException {

		return findLedbuildingByStoreyContaining(storey, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findLedbuildingByStoreyContaining(Integer storey,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedbuildingByStoreyContaining",
				startResult, maxRows, storey);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@Transactional
	public Ledbuilding findLedbuildingByPrimaryKey(Integer id)
			throws DataAccessException {
		return findLedbuildingByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Ledbuilding findLedbuildingByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedbuildingByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledbuilding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledbuilding> findAllLedbuildings() throws DataAccessException {

		return findAllLedbuildings(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledbuilding> findAllLedbuildings(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllLedbuildings", startResult,
				maxRows);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Ledbuilding> findProjectLedbuildings(Integer project, int start,
			int max) throws DataAccessException {
		Query query = createNamedQuery("findAllProjectLedbuildings", start,
				max, project);
		return new LinkedHashSet<Ledbuilding>(query.getResultList());
	}

	public boolean canBeMerged(Ledbuilding entity) {
		return true;
	}
}