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

@Repository("CompanyDAO")
@Transactional
public class CompanyDAOImpl extends AbstractJpaDao<Company> implements
		CompanyDAO {
	private final static Set<Class<?>> dataTypes = new HashSet<Class<?>>(
			Arrays.asList(new Class<?>[] { Company.class }));

	@PersistenceContext(unitName = "energydevice")
	private EntityManager entityManager;

	public CompanyDAOImpl() {
		super();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public Set<Class<?>> getTypes() {
		return dataTypes;
	}

	@Transactional
	public Company findCompanyById(Integer id) throws DataAccessException {

		return findCompanyById(id, -1, -1);
	}

	@Transactional
	public Company findCompanyById(Integer id, int startResult, int maxRows)
			throws DataAccessException {
		try {
			Query query = createNamedQuery("findCompanyById", startResult,
					maxRows, id);
			return (cn.edu.scau.cmi.domain.Company) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Company> findCompanyByName(String name)
			throws DataAccessException {

		return findCompanyByName(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByName(String name, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByName", startResult,
				maxRows, name);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByNameContaining(String name)
			throws DataAccessException {

		return findCompanyByNameContaining(name, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByNameContaining(String name,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByNameContaining",
				startResult, maxRows, name);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPhone(String phone)
			throws DataAccessException {

		return findCompanyByPhone(phone, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPhone(String phone, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPhone", startResult,
				maxRows, phone);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPhoneContaining(String phone)
			throws DataAccessException {

		return findCompanyByPhoneContaining(phone, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPhoneContaining(String phone,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPhoneContaining",
				startResult, maxRows, phone);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByFax(String fax) throws DataAccessException {

		return findCompanyByFax(fax, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByFax(String fax, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByFax", startResult,
				maxRows, fax);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByFaxContaining(String fax)
			throws DataAccessException {

		return findCompanyByFaxContaining(fax, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByFaxContaining(String fax, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByFaxContaining",
				startResult, maxRows, fax);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPostcode(String postcode)
			throws DataAccessException {

		return findCompanyByPostcode(postcode, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPostcode(String postcode, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPostcode", startResult,
				maxRows, postcode);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPostcodeContaining(String postcode)
			throws DataAccessException {

		return findCompanyByPostcodeContaining(postcode, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPostcodeContaining(String postcode,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPostcodeContaining",
				startResult, maxRows, postcode);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByAddress(String address)
			throws DataAccessException {

		return findCompanyByAddress(address, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByAddress(String address, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByAddress", startResult,
				maxRows, address);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByAddressContaining(String address)
			throws DataAccessException {

		return findCompanyByAddressContaining(address, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByAddressContaining(String address,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByAddressContaining",
				startResult, maxRows, address);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByWebsite(String website)
			throws DataAccessException {

		return findCompanyByWebsite(website, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByWebsite(String website, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByWebsite", startResult,
				maxRows, website);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByWebsiteContaining(String website)
			throws DataAccessException {

		return findCompanyByWebsiteContaining(website, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByWebsiteContaining(String website,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByWebsiteContaining",
				startResult, maxRows, website);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPersonduty(String personduty)
			throws DataAccessException {

		return findCompanyByPersonduty(personduty, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPersonduty(String personduty,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPersonduty", startResult,
				maxRows, personduty);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByPersondutyContaining(String personduty)
			throws DataAccessException {

		return findCompanyByPersondutyContaining(personduty, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByPersondutyContaining(String personduty,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByPersondutyContaining",
				startResult, maxRows, personduty);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByDetail(String detail)
			throws DataAccessException {

		return findCompanyByDetail(detail, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByDetail(String detail, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByDetail", startResult,
				maxRows, detail);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByDetailContaining(String detail)
			throws DataAccessException {

		return findCompanyByDetailContaining(detail, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByDetailContaining(String detail,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByDetailContaining",
				startResult, maxRows, detail);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByRemark(String remark)
			throws DataAccessException {

		return findCompanyByRemark(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByRemark", startResult,
				maxRows, remark);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Set<Company> findCompanyByRemarkContaining(String remark)
			throws DataAccessException {

		return findCompanyByRemarkContaining(remark, -1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findCompanyByRemarkContaining(String remark,
			int startResult, int maxRows) throws DataAccessException {
		Query query = createNamedQuery("findCompanyByRemarkContaining",
				startResult, maxRows, remark);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@Transactional
	public Company findCompanyByPrimaryKey(Integer id)
			throws DataAccessException {
		return findCompanyByPrimaryKey(id, -1, -1);
	}

	@Transactional
	public Company findCompanyByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException {
		try {
			Query query = createNamedQuery("findCompanyByPrimaryKey",
					startResult, maxRows, id);
			return (cn.edu.scau.cmi.domain.Company) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	@Transactional
	public Set<Company> findAllCompanys() throws DataAccessException {

		return findAllCompanys(-1, -1);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public Set<Company> findAllCompanys(int startResult, int maxRows)
			throws DataAccessException {
		Query query = createNamedQuery("findAllCompanys", startResult, maxRows);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	@SuppressWarnings("unchecked")
	public Set<Company> findStaffCompanys(Integer staff, int start, int max)
			throws DataAccessException {
		Query query = createNamedQuery("findAllStaffCompanys", start, max,
				staff);
		return new LinkedHashSet<Company>(query.getResultList());
	}

	public boolean canBeMerged(Company entity) {
		return true;
	}
}