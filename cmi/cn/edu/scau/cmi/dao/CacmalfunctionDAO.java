package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacmalfunctionDAO extends JpaDao<Cacmalfunction> {

	public Cacmalfunction findCacmalfunctionByCacrecordtime(
			Integer cacrecordtime) throws DataAccessException;

	public Cacmalfunction findCacmalfunctionByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException;

	public Cacmalfunction findCacmalfunctionByCacdevice(Integer cacdevice)
			throws DataAccessException;

	public Cacmalfunction findCacmalfunctionByCacdevice(Integer cacdevice,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacmalfunction> findCacmalfunctionByStatus(String status)
			throws DataAccessException;

	public Set<Cacmalfunction> findCacmalfunctionByStatus(String status,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacmalfunction> findCacmalfunctionByStatusContaining(
			String status_1) throws DataAccessException;

	public Set<Cacmalfunction> findCacmalfunctionByStatusContaining(
			String status_1, int startResult, int maxRows)
			throws DataAccessException;

	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime,
			Integer cacdevice) throws DataAccessException;

	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime,
			Integer cacdevice, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacmalfunction> findAllCacmalfunctions()
			throws DataAccessException;

	public Set<Cacmalfunction> findAllCacmalfunctions(int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacmalfunction> findCacdeviceCacmalfunctions(Integer Cacdevice,
			int start, int max);

	public Set<Cacmalfunction> findCacrecordtimeCacmalfunctions(
			Integer Cacrecordtime, int start, int max);

}
