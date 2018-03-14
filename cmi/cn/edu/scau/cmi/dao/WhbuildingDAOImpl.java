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

@Repository("WhbuildingDAO")
@Transactional
public class WhbuildingDAOImpl extends AbstractJpaDao<Whbuilding> implements
		WhbuildingDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whbuilding.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhbuildingDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whbuilding findWhbuildingById(Integer id) throws DataAccessException {

		return findWhbuildingById(id, -1, -1);
	}

	@Transactional
	public Whbuilding findWhbuildingById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhbuildingById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Whbuilding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whbuilding> findWhbuildingByName(String name)
			throws DataAccessException {

		return findWhbuildingByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whbuilding> findWhbuildingByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhbuildingByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Whbuilding>(query.getResultList());
	}

	@Transactional
	public Set<Whbuilding> findWhbuildingByNameContaining(String name)
			throws DataAccessException {

		return findWhbuildingByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whbuilding> findWhbuildingByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhbuildingByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Whbuilding>(query.getResultList());
	}

	@Transactional
	public Whbuilding findWhbuildingByPrimaryKey(Integer id)
			throws DataAccessException {
		return findWhbuildingByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whbuilding findWhbuildingByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhbuildingByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whbuilding) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whbuilding> findAllWhbuildings() throws DataAccessException {

		return findAllWhbuildings(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whbuilding> findAllWhbuildings(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhbuildings", startResult,
				maxRows);
		return new LinkedHashSet<Whbuilding>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whbuilding> findProjectWhbuildings(Integer project, int start,
			int max) throws DataAccessException {
		Query query = createNamedQuery("findAllProjectWhbuildings", start, max,
				project);
		return new LinkedHashSet<Whbuilding>(query.getResultList());
	}

	public boolean canBeMerged(Whbuilding entity) {
		return true;
	}
}