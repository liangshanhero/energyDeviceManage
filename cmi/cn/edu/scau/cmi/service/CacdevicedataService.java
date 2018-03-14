package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacdevicedataService {

	public void saveCacdevicedata(Cacdevicedata cacdevicedata);

	public Cacdevicedata saveCacdevicedataCacdevice(Integer cacdevice,
			Integer cacrecordtime, Cacdevice related_cacdevice);

	public Cacdevicedata saveCacdevicedataCacrecordtime(Integer cacdevice,
			Integer cacrecordtime, Cacrecordtime related_cacrecordtime);

	public Set<Cacdevicedata> loadCacdevicedatas();

	public Set<Cacdevicedata> loadCacdevicedatas(int index, int size);

	public void deleteCacdevicedata(Cacdevicedata cacdevicedata);

	public Cacdevicedata deleteCacdevicedataCacdevice(Integer cacdevice,
			Integer cacrecordtime, Integer related_cacdevice_id);

	public Cacdevicedata deleteCacdevicedataCacrecordtime(Integer cacdevice,
			Integer cacrecordtime, Integer related_cacrecordtime_id);

	public List<Cacdevicedata> findAllCacdevicedatas(Integer startResult,
			Integer maxRows);

	public Cacdevicedata findCacdevicedataByPrimaryKey(Integer cacdevice,
			Integer cacrecordtime);

	public Integer countcacdevicedatas();

	public Integer countRelativeCacdeviceCacdevicedatas(Integer cacdevice);

	public Integer countRelativeCacrecordtimeCacdevicedatas(
			Integer cacrecordtime);

}