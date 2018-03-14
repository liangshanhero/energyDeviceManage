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

@Repository("WhdatatypeDAO")
@Transactional
public class WhdatatypeDAOImpl extends AbstractJpaDao<Whdatatype> implements
		WhdatatypeDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whdatatype.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdatatypeDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whdatatype findWhdatatypeById(Integer id) throws DataAccessException {

		return findWhdatatypeById(id, -1, -1);
	}

	@Transactional
	public Whdatatype findWhdatatypeById(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdatatypeById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdatatype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdatatype> findWhdatatypeByName(String name)
			throws DataAccessException {

		return findWhdatatypeByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdatatype> findWhdatatypeByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdatatypeByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Whdatatype>(query.getResultList());
	}

	@Transactional
	public Set<Whdatatype> findWhdatatypeByNameContaining(String name)
			throws DataAccessException {

		return findWhdatatypeByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdatatype> findWhdatatypeByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdatatypeByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Whdatatype>(query.getResultList());
	}

	@Transactional
	public Whdatatype findWhdatatypeByPrimaryKey(Integer id)
			throws DataAccessException {
		return findWhdatatypeByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whdatatype findWhdatatypeByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdatatypeByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdatatype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdatatype> findAllWhdatatypes() throws DataAccessException {

		return findAllWhdatatypes(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdatatype> findAllWhdatatypes(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhdatatypes", startResult,
				maxRows);
		return new LinkedHashSet<Whdatatype>(query.getResultList());
	}

	public boolean canBeMerged(Whdatatype entity) {
		return true;
	}
}