package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface WhdevicedataService {

	public void saveWhdevicedata(Whdevicedata whdevicedata);

	public Whdevicedata saveWhdevicedataWhdatatype(Integer id,
			Whdatatype related_whdatatype);

	public Whdevicedata saveWhdevicedataWhdevice(Integer id,
			Whdevice related_whdevice);

	public Set<Whdevicedata> loadWhdevicedatas();

	public Set<Whdevicedata> loadWhdevicedatas(int index, int size);

	public void deleteWhdevicedata(Whdevicedata whdevicedata);

	public Whdevicedata deleteWhdevicedataWhdatatype(Integer id,
			Integer related_whdatatype_id);

	public Whdevicedata deleteWhdevicedataWhdevice(Integer id,
			Integer related_whdevice_id);

	public List<Whdevicedata> findAllWhdevicedatas(Integer startResult,
			Integer maxRows);

	public Whdevicedata findWhdevicedataByPrimaryKey(Integer id);

	public Integer countwhdevicedatas();

	public Integer countRelativeWhdatatypeWhdevicedatas(Integer whdatatype);

	public Integer countRelativeWhdeviceWhdevicedatas(Integer whdevice);

}