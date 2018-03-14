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


@Repository("WhstrategytypeDAO")
@Transactional
public class WhstrategytypeDAOImpl extends AbstractJpaDao<Whstrategytype> implements WhstrategytypeDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whstrategytype.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategytypeDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whstrategytype findWhstrategytypeById(Integer id) throws DataAccessException {

		return findWhstrategytypeById(id, -1, -1);
	}

	@Transactional
	public Whstrategytype findWhstrategytypeById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategytypeById", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategytype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategytype> findWhstrategytypeByName(String name) throws DataAccessException {

		return findWhstrategytypeByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategytype> findWhstrategytypeByName(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategytypeByName", startResult, maxRows, name);
		return new LinkedHashSet<Whstrategytype>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategytype> findWhstrategytypeByNameContaining(String name) throws DataAccessException {

		return findWhstrategytypeByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategytype> findWhstrategytypeByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategytypeByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Whstrategytype>(query.getResultList());
	}

	@Transactional
	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id) throws DataAccessException {
		return findWhstrategytypeByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategytypeByPrimaryKey", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategytype) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategytype> findAllWhstrategytypes() throws DataAccessException {

		return findAllWhstrategytypes(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategytype> findAllWhstrategytypes(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWhstrategytypes", startResult, maxRows);
		return new LinkedHashSet<Whstrategytype>(query.getResultList());
	}

	public boolean canBeMerged(Whstrategytype entity) {
		return false;
	}
}
