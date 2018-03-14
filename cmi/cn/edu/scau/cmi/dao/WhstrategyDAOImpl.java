package cn.edu.scau.cmi.dao;

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


@Repository("WhstrategyDAO")
@Transactional
public class WhstrategyDAOImpl extends AbstractJpaDao<Whstrategy> implements WhstrategyDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whstrategy.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhstrategyDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whstrategy findWhstrategyById(Integer id) throws DataAccessException {

		return findWhstrategyById(id, -1, -1);
	}

	@Transactional
	public Whstrategy findWhstrategyById(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategyById", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategy) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByEnable(Boolean enable) throws DataAccessException {

		return findWhstrategyByEnable(enable, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByEnable(Boolean enable, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByEnable", startResult, maxRows, enable);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByEnableContaining(Boolean enable) throws DataAccessException {

		return findWhstrategyByEnableContaining(enable, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByEnableContaining(Boolean enable, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByEnableContaining", startResult, maxRows, enable);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByCreateDate(Date createDate) throws DataAccessException {

		return findWhstrategyByCreateDate(createDate, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByCreateDate(Date createDate, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByCreateDate", startResult, maxRows, createDate);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByCreateDateContaining(Date createDate) throws DataAccessException {

		return findWhstrategyByCreateDateContaining(createDate, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByCreateDateContaining(Date createDate, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByCreateDateContaining", startResult, maxRows, createDate);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByRemark(byte[] remark) throws DataAccessException {

		return findWhstrategyByRemark(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByRemark(byte[] remark, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByRemark", startResult, maxRows, remark);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByRemarkContaining(byte[] remark) throws DataAccessException {

		return findWhstrategyByRemarkContaining(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByRemarkContaining(byte[] remark, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByRemarkContaining", startResult, maxRows, remark);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByName(String name) throws DataAccessException {

		return findWhstrategyByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByName(String name, int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByName", startResult, maxRows, name);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Set<Whstrategy> findWhstrategyByNameContaining(String name) throws DataAccessException {

		return findWhstrategyByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findWhstrategyByNameContaining(String name, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findWhstrategyByNameContaining", startResult, maxRows, name);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@Transactional
	public Whstrategy findWhstrategyByPrimaryKey(Integer id) throws DataAccessException {
		return findWhstrategyByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whstrategy findWhstrategyByPrimaryKey(Integer id, int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhstrategyByPrimaryKey", startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whstrategy) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whstrategy> findAllWhstrategys() throws DataAccessException {

		return findAllWhstrategys(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whstrategy> findAllWhstrategys(int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findAllWhstrategys", startResult, maxRows);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whstrategy> findWhdeviceWhstrategys(Integer whdevice, int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhdeviceWhstrategys", start, max, whdevice);
		return new LinkedHashSet<Whstrategy>(query.getResultList());
	}

	public boolean canBeMerged(Whstrategy entity) {
		return false;
	}
}
