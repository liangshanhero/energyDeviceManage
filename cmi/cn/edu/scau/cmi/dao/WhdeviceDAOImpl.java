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

@Repository("WhdeviceDAO")
@Transactional
public class WhdeviceDAOImpl extends AbstractJpaDao<Whdevice> implements
		WhdeviceDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Whdevice.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public WhdeviceDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Whdevice findWhdeviceById(Integer id) throws DataAccessException {

		return findWhdeviceById(id, -1, -1);
	}

	@Transactional
	public Whdevice findWhdeviceById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdeviceById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdevice> findWhdeviceByNumber(String number)
			throws DataAccessException {

		return findWhdeviceByNumber(number, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevice> findWhdeviceByNumber(String number, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdeviceByNumber", startResult,
				maxRows, number);
		return new LinkedHashSet<Whdevice>(query.getResultList());
	}

	@Transactional
	public Set<Whdevice> findWhdeviceByNumberContaining(String number)
			throws DataAccessException {

		return findWhdeviceByNumberContaining(number, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevice> findWhdeviceByNumberContaining(String number,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findWhdeviceByNumberContaining",
				startResult, maxRows, number);
		return new LinkedHashSet<Whdevice>(query.getResultList());
	}

	@Transactional
	public Whdevice findWhdeviceByPrimaryKey(Integer id)
			throws DataAccessException {
		return findWhdeviceByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Whdevice findWhdeviceByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findWhdeviceByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Whdevice) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Whdevice> findAllWhdevices() throws DataAccessException {

		return findAllWhdevices(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Whdevice> findAllWhdevices(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllWhdevices", startResult, maxRows);
		return new LinkedHashSet<Whdevice>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Whdevice> findWhbuildingWhdevices(Integer whbuilding, int start,
			int max) throws DataAccessException {
		Query query = createNamedQuery("findAllWhbuildingWhdevices", start,
				max, whbuilding);
		return new LinkedHashSet<Whdevice>(query.getResultList());
	}

	public boolean canBeMerged(Whdevice entity) {
		return false;
	}
}