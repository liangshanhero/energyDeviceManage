package cn.edu.scau.cmi.dao;

import java.util.List;
import java.util.Set;

import org.springframework.dao.DataAccessException;
import cn.edu.scau.cmi.domain.*;

public interface CacsensordataDAO extends JpaDao<Cacsensordata> {

	public Cacsensordata findCacsensordataByCacrecordtime(Integer cacrecordtime)
			throws DataAccessException;

	public Cacsensordata findCacsensordataByCacrecordtime(
			Integer cacrecordtime, int startResult, int maxRows)
			throws DataAccessException;

	public Cacsensordata findCacsensordataByCacsensor(Integer cacsensor)
			throws DataAccessException;

	public Cacsensordata findCacsensordataByCacsensor(Integer cacsensor,
			int startResult, int maxRows) throws DataAccessException;
	public List<Cacsensordata> findCacsensordataByCacsensor1(Integer cacsensor,
			int startResult, int maxRows) throws DataAccessException ;

	public Set<Cacsensordata> findCacsensordataByValue(String value)
			throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByValue(String value,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByValueContaining(String value_1)
			throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByValueContaining(
			String value_1, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByIsreport(Integer isreport)
			throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByIsreport(Integer isreport,
			int startResult, int maxRows) throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByIsreportContaining(
			Integer isreport_1) throws DataAccessException;

	public Set<Cacsensordata> findCacsensordataByIsreportContaining(
			Integer isreport_1, int startResult, int maxRows)
			throws DataAccessException;

	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime,
			Integer cacsensor) throws DataAccessException;

	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime,
			Integer cacsensor, int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacsensordata> findAllCacsensordatas()
			throws DataAccessException;

	public Set<Cacsensordata> findAllCacsensordatas(int startResult, int maxRows)
			throws DataAccessException;

	public Set<Cacsensordata> findCacrecordtimeCacsensordatas(
			Integer Cacrecordtime, int start, int max);

	public Set<Cacsensordata> findCacsensorCacsensordatas(Integer Cacsensor,
			int start, int max);

}
