package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface LedmeterdataDAO extends JpaDao<Ledmeterdata> {

	public Ledmeterdata findLedmeterdataById(Integer id)
			throws DataAccessException;

	public Ledmeterdata findLedmeterdataById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByValue(BigDecimal value)
			throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByValueContaining(
			BigDecimal value_1) throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByValueContaining(
			BigDecimal value_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByTime(Date time)
			throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByTime(Date time, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByTimeContaining(Date time_1)
			throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterdataByTimeContaining(Date time_1,
			int startResult, int maxRows) throws DataAccessException;

	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id)
			throws DataAccessException;

	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Ledmeterdata> findAllLedmeterdatas() throws DataAccessException;

	public Set<Ledmeterdata> findAllLedmeterdatas(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Ledmeterdata> findLedmeterLedmeterdatas(Integer Ledmeter,
			int start, int max);

}
