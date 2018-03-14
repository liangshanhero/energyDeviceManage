package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacdeviceService {

	public void saveCacdevice(Cacdevice cacdevice);

	public Cacdevice saveCacdeviceCac(Integer id, Cac related_cac);

	public Cacdevice saveCacdeviceCacdevicedatas(Integer id,
			Cacdevicedata related_cacdevicedatas);

	public Cacdevice saveCacdeviceCacmalfunctions(Integer id,
			Cacmalfunction related_cacmalfunctions);

	public Set<Cacdevice> loadCacdevices();

	public Set<Cacdevice> loadCacdevices(int index, int size);

	public void deleteCacdevice(Cacdevice cacdevice);

	public Cacdevice deleteCacdeviceCac(Integer id, Integer related_cac_id);

	public Cacdevice deleteCacdeviceCacdevicedatas(Integer id,
			Integer related_cacdevicedatas_cacdevice,
			Integer related_cacdevicedatas_cacrecordtime);

	public Cacdevice deleteCacdeviceCacmalfunctions(Integer id,
			Integer related_cacmalfunctions_cacrecordtime,
			Integer related_cacmalfunctions_cacdevice);

	public List<Cacdevice> findAllCacdevices(Integer startResult,
			Integer maxRows);

	public Cacdevice findCacdeviceByPrimaryKey(Integer id);

	public Integer countcacdevices();

	public Integer countRelativeCacCacdevices(Integer cac);

}