package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacdeviceDAO extends JpaDao<Cacdevice> {

	public Cacdevice findCacdeviceById(Integer id) throws DataAccessException;

	public Cacdevice findCacdeviceById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByName(String name)
			throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByName(String name, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByNameContaining(String name_1)
			throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByNameContaining(String name_1,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByUnit(String unit)
			throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByUnit(String unit, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByUnitContaining(String unit_1)
			throws DataAccessException;

	public Set<Cacdevice> findCacdeviceByUnitContaining(String unit_1,
			int startResult, int maxRows) throws DataAccessException;

	public Cacdevice findCacdeviceByPrimaryKey(Integer id)
			throws DataAccessException;

	public Cacdevice findCacdeviceByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Cacdevice> findAllCacdevices() throws DataAccessException;

	public Set<Cacdevice> findAllCacdevices(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacdevice> findCacCacdevices(Integer Cac, int start, int max);

}
