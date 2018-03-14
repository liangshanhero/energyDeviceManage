package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface WhdeviceService {

	public void saveWhdevice(Whdevice whdevice);

	public Whdevice saveWhdeviceWhbuilding(Integer id,
			Whbuilding related_whbuilding);

	public Whdevice saveWhdeviceWhdatatypes(Integer id,
			Whdatatype related_whdatatypes);

	public Whdevice saveWhdeviceWhdevicedatas(Integer id,
			Whdevicedata related_whdevicedatas);

	public Set<Whdevice> loadWhdevices();

	public Set<Whdevice> loadWhdevices(int index, int size);

	public void deleteWhdevice(Whdevice whdevice);

	public Whdevice deleteWhdeviceWhbuilding(Integer id,
			Integer related_whbuilding_id);

	public Whdevice deleteWhdeviceWhdatatypes(Integer id,
			Integer related_whdatatypes_id);

	public Whdevice deleteWhdeviceWhdevicedatas(Integer id,
			Integer related_whdevicedatas_id);

	public List<Whdevice> findAllWhdevices(Integer startResult, Integer maxRows);

	public Whdevice findWhdeviceByPrimaryKey(Integer id);

	public Integer countwhdevices();

	public Integer countRelativeWhbuildingWhdevices(Integer whbuilding);

	public Integer countWhdeviceWhdatatypes(Integer whdevice);

}