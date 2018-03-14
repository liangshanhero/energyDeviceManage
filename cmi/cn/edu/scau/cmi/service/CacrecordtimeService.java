package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacrecordtimeService {

	public void saveCacrecordtime(Cacrecordtime cacrecordtime);

	public Cacrecordtime saveCacrecordtimeCacdevicedatas(Integer id,
			Cacdevicedata related_cacdevicedatas);

	public Cacrecordtime saveCacrecordtimeCacmalfunctions(Integer id,
			Cacmalfunction related_cacmalfunctions);

	public Cacrecordtime saveCacrecordtimeCacsensordatas(Integer id,
			Cacsensordata related_cacsensordatas);

	public Set<Cacrecordtime> loadCacrecordtimes();

	public Set<Cacrecordtime> loadCacrecordtimes(int index, int size);

	public void deleteCacrecordtime(Cacrecordtime cacrecordtime);

	public Cacrecordtime deleteCacrecordtimeCacdevicedatas(Integer id,
			Integer related_cacdevicedatas_cacdevice,
			Integer related_cacdevicedatas_cacrecordtime);

	public Cacrecordtime deleteCacrecordtimeCacmalfunctions(Integer id,
			Integer related_cacmalfunctions_cacrecordtime,
			Integer related_cacmalfunctions_cacdevice);

	public Cacrecordtime deleteCacrecordtimeCacsensordatas(Integer id,
			Integer related_cacsensordatas_cacrecordtime,
			Integer related_cacsensordatas_cacsensor);

	public List<Cacrecordtime> findAllCacrecordtimes(Integer startResult,
			Integer maxRows);

	public Cacrecordtime findCacrecordtimeByPrimaryKey(Integer id);

	public Integer countcacrecordtimes();

}