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

@Repository("CacDAO")
@Transactional
public class CacDAOImpl extends AbstractJpaDao<Cac> implements CacDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cac.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cac findCacById(Integer id) throws DataAccessException {

		return findCacById(id, -1, -1);
	}

	@Transactional
	public Cac findCacById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacById", startResult, maxRows,
					id);
			return (cn.edu.scau.cmi.domain.Cac) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cac> findCacByRemark(String remark) throws DataAccessException {

		return findCacByRemark(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cac> findCacByRemark(String remark, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacByRemark", startResult, maxRows,
				remark);
		return new LinkedHashSet<Cac>(query.getResultList());
	}

	@Transactional
	public Set<Cac> findCacByRemarkContaining(String remark)
			throws DataAccessException {

		return findCacByRemarkContaining(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cac> findCacByRemarkContaining(String remark, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacByRemarkContaining",
				startResult, maxRows, remark);
		return new LinkedHashSet<Cac>(query.getResultList());
	}

	@Transactional
	public Cac findCacByPrimaryKey(Integer id) throws DataAccessException {
		return findCacByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Cac findCacByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacByPrimaryKey", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Cac) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cac> findAllCacs() throws DataAccessException {

		return findAllCacs(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cac> findAllCacs(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacs", startResult, maxRows);
		return new LinkedHashSet<Cac>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cac> findProjectCacs(Integer project, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllProjectCacs", start, max,
				project);
		return new LinkedHashSet<Cac>(query.getResultList());
	}

	public boolean canBeMerged(Cac entity) {
		return true;
	}
}