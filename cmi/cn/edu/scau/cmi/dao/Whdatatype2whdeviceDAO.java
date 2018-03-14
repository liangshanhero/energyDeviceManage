package cn.edu.scau.cmi.dao;

import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface Whdatatype2whdeviceDAO extends JpaDao<Whdatatype2whdevice> {

	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdatatype(
			Integer whdatatype) throws DataAccessException;

	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdatatype(
			Integer whdatatype, int startResult, int maxRows)
			throws DataAccessException;

	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdevice(
			Integer whdevice) throws DataAccessException;

	public Whdatatype2whdevice findWhdatatype2whdeviceByWhdevice(
			Integer whdevice, int startResult, int maxRows)
			throws DataAccessException;

	public Whdatatype2whdevice findWhdatatype2whdeviceByPrimaryKey(
			Integer whdatatype, Integer whdevice) throws DataAccessException;

	public Whdatatype2whdevice findWhdatatype2whdeviceByPrimaryKey(
			Integer whdatatype, Integer whdevice, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Whdatatype2whdevice> findAllWhdatatype2whdevices()
			throws DataAccessException;

	public Set<Whdatatype2whdevice> findAllWhdatatype2whdevices(
			int startResult, int maxRows) throws DataAccessException;

	public Set<Whdatatype2whdevice> findWhdatatypeWhdatatype2whdevices(
			Integer Whdatatype, int start, int max);

	public Set<Whdatatype2whdevice> findWhdeviceWhdatatype2whdevices(
			Integer Whdevice, int start, int max);

}
