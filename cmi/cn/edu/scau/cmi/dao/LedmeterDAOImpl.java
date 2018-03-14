package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

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

@Repository("LedmeterDAO")
@Transactional
public class LedmeterDAOImpl extends AbstractJpaDao<Ledmeter> implements
		LedmeterDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Ledmeter.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public LedmeterDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Ledmeter findLedmeterById(Integer id) throws DataAccessException {

		return findLedmeterById(id, -1, -1);
	}

	@Transactional
	public Ledmeter findLedmeterById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedmeterById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledmeter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByNumber(String number)
			throws DataAccessException {

		return findLedmeterByNumber(number, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByNumber(String number, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByNumber", startResult,
				maxRows, number);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByNumberContaining(String number)
			throws DataAccessException {

		return findLedmeterByNumberContaining(number, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByNumberContaining(String number,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByNumberContaining",
				startResult, maxRows, number);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByWell(String well)
			throws DataAccessException {

		return findLedmeterByWell(well, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByWell(String well, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByWell", startResult,
				maxRows, well);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByWellContaining(String well)
			throws DataAccessException {

		return findLedmeterByWellContaining(well, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByWellContaining(String well,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByWellContaining",
				startResult, maxRows, well);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByStorey(Integer storey)
			throws DataAccessException {

		return findLedmeterByStorey(storey, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByStorey(Integer storey, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByStorey", startResult,
				maxRows, storey);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByStoreyContaining(Integer storey)
			throws DataAccessException {

		return findLedmeterByStoreyContaining(storey, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByStoreyContaining(Integer storey,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByStoreyContaining",
				startResult, maxRows, storey);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByTotalamout(BigDecimal totalamout)
			throws DataAccessException {

		return findLedmeterByTotalamout(totalamout, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByTotalamout(BigDecimal totalamout,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByTotalamout", startResult,
				maxRows, totalamout);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByTotalamoutContaining(
			BigDecimal totalamout) throws DataAccessException {

		return findLedmeterByTotalamoutContaining(totalamout, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByTotalamoutContaining(
			BigDecimal totalamout, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByTotalamoutContaining",
				startResult, maxRows, totalamout);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByTotaldays(BigDecimal totaldays)
			throws DataAccessException {

		return findLedmeterByTotaldays(totaldays, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByTotaldays(BigDecimal totaldays,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByTotaldays", startResult,
				maxRows, totaldays);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Set<Ledmeter> findLedmeterByTotaldaysContaining(BigDecimal totaldays)
			throws DataAccessException {

		return findLedmeterByTotaldaysContaining(totaldays, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findLedmeterByTotaldaysContaining(
			BigDecimal totaldays, int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findLedmeterByTotaldaysContaining",
				startResult, maxRows, totaldays);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@Transactional
	public Ledmeter findLedmeterByPrimaryKey(Integer id)
			throws DataAccessException {
		return findLedmeterByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Ledmeter findLedmeterByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findLedmeterByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Ledmeter) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Ledmeter> findAllLedmeters() throws DataAccessException {

		return findAllLedmeters(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Ledmeter> findAllLedmeters(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllLedmeters", startResult, maxRows);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Ledmeter> findLedbuildingLedmeters(Integer ledbuilding,
			int start, int max) throws DataAccessException {
		Query query = createNamedQuery("findAllLedbuildingLedmeters", start,
				max, ledbuilding);
		return new LinkedHashSet<Ledmeter>(query.getResultList());
	}

	public boolean canBeMerged(Ledmeter entity) {
		return true;
	}
}