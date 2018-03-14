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

@Repository("CacsensorDAO")
@Transactional
public class CacsensorDAOImpl extends AbstractJpaDao<Cacsensor> implements
		CacsensorDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacsensor.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacsensorDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacsensor findCacsensorById(Integer id) throws DataAccessException {

		return findCacsensorById(id, -1, -1);
	}

	@Transactional
	public Cacsensor findCacsensorById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensorById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacsensor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacsensor> findCacsensorByName(String name)
			throws DataAccessException {

		return findCacsensorByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensor> findCacsensorByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacsensorByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Cacsensor>(query.getResultList());
	}

	@Transactional
	public Set<Cacsensor> findCacsensorByNameContaining(String name)
			throws DataAccessException {

		return findCacsensorByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensor> findCacsensorByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacsensorByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Cacsensor>(query.getResultList());
	}

	@Transactional
	public Cacsensor findCacsensorByPrimaryKey(Integer id)
			throws DataAccessException {
		return findCacsensorByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Cacsensor findCacsensorByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensorByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Cacsensor) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacsensor> findAllCacsensors() throws DataAccessException {

		return findAllCacsensors(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensor> findAllCacsensors(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacsensors", startResult,
				maxRows);
		return new LinkedHashSet<Cacsensor>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacsensor> findCacCacsensors(Integer cac, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacCacsensors", start, max, cac);
		return new LinkedHashSet<Cacsensor>(query.getResultList());
	}

	public boolean canBeMerged(Cacsensor entity) {
		return true;
	}
}