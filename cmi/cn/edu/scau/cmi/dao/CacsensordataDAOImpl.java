package cn.edu.scau.cmi.dao;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.DataAccessException;

import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

import cn.edu.scau.cmi.domain.*;

@Repository("CacsensordataDAO")
@Transactional
public class CacsensordataDAOImpl extends AbstractJpaDao<Cacsensordata>
		implements CacsensordataDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Cacsensordata.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CacsensordataDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Cacsensordata findCacsensordataByCacrecordtime(Integer cacrecordtime)
			throws DataAccessException {

		return findCacsensordataByCacrecordtime(cacrecordtime, -1, -1);
	}

	@Transactional
	public Cacsensordata findCacsensordataByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensordataByCacrecordtime",
					startResult, maxRows, cacrecordtime);
			return (cn.edu.scau.cmi.domain.Cacsensordata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Cacsensordata findCacsensordataByCacsensor(Integer cacsensor)
			throws DataAccessException {

		return findCacsensordataByCacsensor(cacsensor, -1, -1);
	}

	@Transactional
	public Cacsensordata findCacsensordataByCacsensor(Integer cacsensor,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensordataByCacsensor",
					startResult, maxRows, cacsensor);
			return (cn.edu.scau.cmi.domain.Cacsensordata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Cacsensordata> findCacsensordataByCacsensor1(Integer cacsensor,
			int startResult, int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensordataByCacsensor",
					startResult, maxRows, cacsensor);
			return query.getResultList();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacsensordata> findCacsensordataByValue(String value)
			throws DataAccessException {

		return findCacsensordataByValue(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensordata> findCacsensordataByValue(String value,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacsensordataByValue", startResult,
				maxRows, value);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@Transactional
	public Set<Cacsensordata> findCacsensordataByValueContaining(String value)
			throws DataAccessException {

		return findCacsensordataByValueContaining(value, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensordata> findCacsensordataByValueContaining(String value,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacsensordataByValueContaining",
				startResult, maxRows, value);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@Transactional
	public Set<Cacsensordata> findCacsensordataByIsreport(Integer isreport)
			throws DataAccessException {

		return findCacsensordataByIsreport(isreport, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensordata> findCacsensordataByIsreport(Integer isreport,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCacsensordataByIsreport",
				startResult, maxRows, isreport);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@Transactional
	public Set<Cacsensordata> findCacsensordataByIsreportContaining(
			Integer isreport) throws DataAccessException {

		return findCacsensordataByIsreportContaining(isreport, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensordata> findCacsensordataByIsreportContaining(
			Integer isreport, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findCacsensordataByIsreportContaining",
				startResult, maxRows, isreport);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@Transactional
	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime,
			Integer cacsensor) throws DataAccessException {
		return findCacsensordataByPrimaryKey(cacrecordtime, cacsensor, -1, -1);
	}

	@Transactional
	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime,
			Integer cacsensor, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCacsensordataByPrimaryKey",
					startResult, maxRows, cacrecordtime, cacsensor);
			return (cn.edu.scau.cmi.domain.Cacsensordata) query
					.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Cacsensordata> findAllCacsensordatas()
			throws DataAccessException {

		return findAllCacsensordatas(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Cacsensordata> findAllCacsensordatas(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacsensordatas", startResult,
				maxRows);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacsensordata> findCacrecordtimeCacsensordatas(
			Integer cacrecordtime, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCacrecordtimeCacsensordatas",
				start, max, cacrecordtime);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Cacsensordata> findCacsensorCacsensordatas(Integer cacsensor,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllCacsensorCacsensordatas", start,
				max, cacsensor);
		return new LinkedHashSet<Cacsensordata>(query.getResultList());
	}

	public boolean canBeMerged(Cacsensordata entity) {
		return true;
	}
}