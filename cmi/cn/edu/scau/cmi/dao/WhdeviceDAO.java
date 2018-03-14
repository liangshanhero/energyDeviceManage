package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface WhdeviceDAO extends JpaDao<Whdevice> {

	public Whdevice findWhdeviceById(Integer id) throws DataAccessException;

	public Whdevice findWhdeviceById(Integer id, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdevice> findWhdeviceByNumber(String number)
			throws DataAccessException;

	public Set<Whdevice> findWhdeviceByNumber(String number, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdevice> findWhdeviceByNumberContaining(String number_1)
			throws DataAccessException;

	public Set<Whdevice> findWhdeviceByNumberContaining(String number_1,
			int startResult, int maxRows) throws DataAccessException;

	public Whdevice findWhdeviceByPrimaryKey(Integer id)
			throws DataAccessException;

	public Whdevice findWhdeviceByPrimaryKey(Integer id, int startResult,
			int maxRows) throws DataAccessException;

	public Set<Whdevice> findAllWhdevices() throws DataAccessException;

	public Set<Whdevice> findAllWhdevices(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdevice> findWhbuildingWhdevices(Integer Whbuilding, int start,
			int max);

}
