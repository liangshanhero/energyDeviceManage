package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface WhdatatypeService {

	public void saveWhdatatype(Whdatatype whdatatype);

	public Whdatatype saveWhdatatypeWhdevices(Integer id,
			Whdevice related_whdevices);

	public Whdatatype saveWhdatatypeWhdevicedatas(Integer id,
			Whdevicedata related_whdevicedatas);

	public Set<Whdatatype> loadWhdatatypes();

	public Set<Whdatatype> loadWhdatatypes(int index, int size);

	public void deleteWhdatatype(Whdatatype whdatatype);

	public Whdatatype deleteWhdatatypeWhdevices(Integer id,
			Integer related_whdevices_id);

	public Whdatatype deleteWhdatatypeWhdevicedatas(Integer id,
			Integer related_whdevicedatas_id);

	public List<Whdatatype> findAllWhdatatypes(Integer startResult,
			Integer maxRows);

	public Whdatatype findWhdatatypeByPrimaryKey(Integer id);

	public Integer countwhdatatypes();

	public Integer countWhdatatypeWhdevices(Integer whdatatype);

}