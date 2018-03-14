package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacmalfunctionService {

	public void saveCacmalfunction(Cacmalfunction cacmalfunction);

	public Cacmalfunction saveCacmalfunctionCacdevice(Integer cacrecordtime,
			Integer cacdevice, Cacdevice related_cacdevice);

	public Cacmalfunction saveCacmalfunctionCacrecordtime(
			Integer cacrecordtime, Integer cacdevice,
			Cacrecordtime related_cacrecordtime);

	public Set<Cacmalfunction> loadCacmalfunctions();

	public Set<Cacmalfunction> loadCacmalfunctions(int index, int size);

	public void deleteCacmalfunction(Cacmalfunction cacmalfunction);

	public Cacmalfunction deleteCacmalfunctionCacdevice(Integer cacrecordtime,
			Integer cacdevice, Integer related_cacdevice_id);

	public Cacmalfunction deleteCacmalfunctionCacrecordtime(
			Integer cacrecordtime, Integer cacdevice,
			Integer related_cacrecordtime_id);

	public List<Cacmalfunction> findAllCacmalfunctions(Integer startResult,
			Integer maxRows);

	public Cacmalfunction findCacmalfunctionByPrimaryKey(Integer cacrecordtime,
			Integer cacdevice);

	public Integer countcacmalfunctions();

	public Integer countRelativeCacdeviceCacmalfunctions(Integer cacdevice);

	public Integer countRelativeCacrecordtimeCacmalfunctions(
			Integer cacrecordtime);

}