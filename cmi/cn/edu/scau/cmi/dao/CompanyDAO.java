package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CompanyDAO extends JpaDao<Company> {

	public Company findCompanyById(Integer id) throws DataAccessException;

	public Company findCompanyById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Company> findCompanyByName(String name)
			throws DataAccessException;

	public Set<Company> findCompanyByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Company> findCompanyByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPhone(String phone)
			throws DataAccessException;

	public Set<Company> findCompanyByPhone(String phone, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPhoneContaining(String phone_1)
			throws DataAccessException;

	public Set<Company> findCompanyByPhoneContaining(String phone_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByFax(String fax) throws DataAccessException;

	public Set<Company> findCompanyByFax(String fax, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByFaxContaining(String fax_1)
			throws DataAccessException;

	public Set<Company> findCompanyByFaxContaining(String fax_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPostcode(String postcode)
			throws DataAccessException;

	public Set<Company> findCompanyByPostcode(String postcode, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPostcodeContaining(String postcode_1)
			throws DataAccessException;

	public Set<Company> findCompanyByPostcodeContaining(String postcode_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByAddress(String address)
			throws DataAccessException;

	public Set<Company> findCompanyByAddress(String address, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByAddressContaining(String address_1)
			throws DataAccessException;

	public Set<Company> findCompanyByAddressContaining(String address_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByWebsite(String website)
			throws DataAccessException;

	public Set<Company> findCompanyByWebsite(String website, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByWebsiteContaining(String website_1)
			throws DataAccessException;

	public Set<Company> findCompanyByWebsiteContaining(String website_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPersonduty(String personduty)
			throws DataAccessException;

	public Set<Company> findCompanyByPersonduty(String personduty,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByPersondutyContaining(String personduty_1)
			throws DataAccessException;

	public Set<Company> findCompanyByPersondutyContaining(String personduty_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByDetail(String detail)
			throws DataAccessException;

	public Set<Company> findCompanyByDetail(String detail, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByDetailContaining(String detail_1)
			throws DataAccessException;

	public Set<Company> findCompanyByDetailContaining(String detail_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByRemark(String remark)
			throws DataAccessException;

	public Set<Company> findCompanyByRemark(String remark, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findCompanyByRemarkContaining(String remark_1)
			throws DataAccessException;

	public Set<Company> findCompanyByRemarkContaining(String remark_1,
			int startResult, int maxRows) throws DataAccessException;

	public Company findCompanyByPrimaryKey(Integer id)
			throws DataAccessException;

	public Company findCompanyByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Company> findAllCompanys() throws DataAccessException;

	public Set<Company> findAllCompanys(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Company> findStaffCompanys(Integer Staff, int start, int max);

}
