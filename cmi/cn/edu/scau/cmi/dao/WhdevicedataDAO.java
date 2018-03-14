package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhdevicedataDAO extends JpaDao<Whdevicedata> {

	public Whdevicedata findWhdevicedataById(Integer id)
			throws DataAccessException;

	public Whdevicedata findWhdevicedataById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByTime(Date time)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByTime(Date time, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByTimeContaining(Date time_1)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByTimeContaining(Date time_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByValue(BigDecimal value)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByValueContaining(
			BigDecimal value_1) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByValueContaining(
			BigDecimal value_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsupdate(Integer isupdate)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsupdate(Integer isupdate,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsupdateContaining(
			Integer isupdate_1) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsupdateContaining(
			Integer isupdate_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsio(Integer isio)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsio(Integer isio,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsioContaining(Integer isio_1)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdevicedataByIsioContaining(Integer isio_1,
			int startResult, int maxRows) throws DataAccessException;

	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id)
			throws DataAccessException;

	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdevicedata> findAllWhdevicedatas() throws DataAccessException;

	public Set<Whdevicedata> findAllWhdevicedatas(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdevicedata> findWhdatatypeWhdevicedatas(Integer Whdatatype,
			int start, int max);

	public Set<Whdevicedata> findWhdeviceWhdevicedatas(Integer Whdevice,
			int start, int max);

}
