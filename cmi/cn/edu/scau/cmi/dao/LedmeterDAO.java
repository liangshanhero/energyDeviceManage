package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface LedmeterDAO extends JpaDao<Ledmeter> {

	public Ledmeter findLedmeterById(Integer id) throws DataAccessException;

	public Ledmeter findLedmeterById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByNumber(String number)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByNumber(String number, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByNumberContaining(String number_1)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByNumberContaining(String number_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByWell(String well)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByWell(String well, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByWellContaining(String well_1)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByWellContaining(String well_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByStorey(Integer storey)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByStorey(Integer storey, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByStoreyContaining(Integer storey_1)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByStoreyContaining(Integer storey_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotalamout(BigDecimal totalamout)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotalamout(BigDecimal totalamout,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotalamoutContaining(
			BigDecimal totalamout_1) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotalamoutContaining(
			BigDecimal totalamout_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotaldays(BigDecimal totaldays)
			throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotaldays(BigDecimal totaldays,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotaldaysContaining(
			BigDecimal totaldays_1) throws DataAccessException;

	public Set<Ledmeter> findLedmeterByTotaldaysContaining(
			BigDecimal totaldays_1, int startResult, int maxRows)
			throws DataAccessException;

	public Ledmeter findLedmeterByPrimaryKey(Integer id)
			throws DataAccessException;

	public Ledmeter findLedmeterByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeter> findAllLedmeters() throws DataAccessException;

	public Set<Ledmeter> findAllLedmeters(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledmeter> findLedbuildingLedmeters(Integer Ledbuilding,
			int start, int max);

}
