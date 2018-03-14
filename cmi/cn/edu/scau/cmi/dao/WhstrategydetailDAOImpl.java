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


@Repository("WhstrategydetailDAO")
@Transactional
public class WhstrategydetailDAOImpl extends AbstractJpaDao<Whstrategydetail> implements WhstrategydetailDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whstrategydetail.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategydetailDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whstrategydetail findWhstrategydetailById(Integer id) throws DataAccessException {

		return findWhstrategydetailById(id, -1, -1);
	}

	@Transactional
	public Whstrategydetail findWhstrategydetailById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategydetailById", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategydetail) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMax(BigDecimal max) throws DataAccessException {

		return findWhstrategydetailByMax(max, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMax(BigDecimal max, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByMax", startResult, maxRows, max);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMaxContaining(BigDecimal max) throws DataAccessException {

		return findWhstrategydetailByMaxContaining(max, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMaxContaining(BigDecimal max, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByMaxContaining", startResult, maxRows, max);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMin(BigDecimal min) throws DataAccessException {

		return findWhstrategydetailByMin(min, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMin(BigDecimal min, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByMin", startResult, maxRows, min);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMinContaining(BigDecimal min) throws DataAccessException {

		return findWhstrategydetailByMinContaining(min, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByMinContaining(BigDecimal min, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByMinContaining", startResult, maxRows, min);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByTime(Date time) throws DataAccessException {

		return findWhstrategydetailByTime(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByTime(Date time, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByTime", startResult, maxRows, time);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByTimeContaining(Date time) throws DataAccessException {

		return findWhstrategydetailByTimeContaining(time, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findWhstrategydetailByTimeContaining(Date time, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategydetailByTimeContaining", startResult, maxRows, time);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@Transactional
	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id) throws DataAccessException {
		return findWhstrategydetailByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategydetailByPrimaryKey", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategydetail) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategydetail> findAllWhstrategydetails() throws DataAccessException {

		return findAllWhstrategydetails(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategydetail> findAllWhstrategydetails(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWhstrategydetails", startResult, maxRows);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whstrategydetail> findWhstrategyWhstrategydetails(Integer whstrategy, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhstrategyWhstrategydetails", start, max, whstrategy);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whstrategydetail> findWhstrategytypeWhstrategydetails(Integer whstrategytype, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhstrategytypeWhstrategydetails", start, max, whstrategytype);
		return new LinkedHashSet<Whstrategydetail>(query.getResultList());
	}

	public boolean canBeMerged(Whstrategydetail entity) {
		return true;
	}
}
