package cn.edu.scau.cmi.pengjie.dataPersistence;

import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.edu.scau.cmi.domain.Project;
import cn.edu.scau.cmi.domain.Whbuilding;
import cn.edu.scau.cmi.domain.Whdatatype;
import cn.edu.scau.cmi.domain.Whdevice;
import cn.edu.scau.cmi.domain.Whdevicedata;

public interface WHPersistenceService {

	public Whbuilding saveWhbuilding(String buildingName, Project project);

	public Whdevice saveWhdevice(String device, Whbuilding whbuilding);

	public Whdevice saveWhdevice(String device, Whbuilding whbuilding,
			Set<Whdatatype> whdatatypeSet);

	public Whdatatype saveWhdatatype(String whdatatype, Set<Whdevice> whdevices);

	public Whdevicedata saveWhdevicedata(Whdevice whdevice,
			Whdatatype whdatatype, Date time, String value, String isupdate,
			String isio);

	public Whdatatype saveWhdatatype(String whdatatypeNme);

	public void batchSaveWhdevicedata(List<Whdevicedata> whdevicedataList);

}
