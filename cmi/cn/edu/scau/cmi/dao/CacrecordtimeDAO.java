package cn.edu.scau.cmi.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacrecordtimeDAO extends JpaDao<Cacrecordtime> {

	public Cacrecordtime findCacrecordtimeById(Integer id)
			throws DataAccessException;

	public Cacrecordtime findCacrecordtimeById(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByRecordTime(Date RecordTime)
			throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByRecordTime(Date RecordTime,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByRecordTimeContaining(
			Date RecordTime_1) throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByRecordTimeContaining(
			Date RecordTime_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeper(String Watchkeeper)
			throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeper(
			String Watchkeeper, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeperContaining(
			String Watchkeeper_1) throws DataAccessException;

	public Set<Cacrecordtime> findCacrecordtimeByWatchkeeperContaining(
			String Watchkeeper_1, int startResult, int maxRows)
			throws DataAccessException;

	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id)
			throws DataAccessException;

	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacrecordtime> findAllCacrecordtimes()
			throws DataAccessException;

	public Set<Cacrecordtime> findAllCacrecordtimes(int startResult, int maxRows)
			throws DataAccessException;

}
