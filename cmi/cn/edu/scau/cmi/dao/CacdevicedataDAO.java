package cn.edu.scau.cmi.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacdevicedataDAO extends JpaDao<Cacdevicedata> {

	public Cacdevicedata findCacdevicedataByCacdevice(Integer cacdevice)
			throws DataAccessException;

	public Cacdevicedata findCacdevicedataByCacdevice(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException;
	public List<Cacdevicedata> findCacdevicedataByCacdevice1(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException;

	public Cacdevicedata findCacdevicedataByCacrecordtime(Integer cacrecordtime)
			throws DataAccessException;

	public Cacdevicedata findCacdevicedataByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByValue(BigDecimal value)
			throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByValue(BigDecimal value,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByValueContaining(
			BigDecimal value_1) throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByValueContaining(
			BigDecimal value_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByIsreport(Integer isreport)
			throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByIsreport(Integer isreport,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByIsreportContaining(
			Integer isreport_1) throws DataAccessException;

	public Set<Cacdevicedata> findCacdevicedataByIsreportContaining(
			Integer isreport_1, int startResult, int maxRows)
			throws DataAccessException;

	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice,
			Integer cacrecordtime) throws DataAccessException;

	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice,
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevicedata> findAllCacdevicedatas()
			throws DataAccessException;

	public Set<Cacdevicedata> findAllCacdevicedatas(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevicedata> findCacdeviceCacdevicedatas(Integer Cacdevice,
			int start, int max);

	public Set<Cacdevicedata> findCacrecordtimeCacdevicedatas(
			Integer Cacrecordtime, int start, int max);

}
