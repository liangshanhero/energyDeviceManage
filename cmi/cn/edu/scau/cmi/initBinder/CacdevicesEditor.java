package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacdevicesEditor")
public class CacdevicesEditor extends PropertyEditorSupport {

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	public void setAsText(String cacdevices) {

		Set<Cacdevice> cacdeviceSet = new HashSet<Cacdevice>();
		if (cacdevices.length() > 0) {
			String[] sourceCacdeviceStringSet = cacdevices.split(",");
			if (sourceCacdeviceStringSet != null) {
				for (String cacdeviceStringSet : sourceCacdeviceStringSet) {
					String[] cacdeviceIdStringSet = cacdeviceStringSet
							.split("\\] ");
					String[] cacdeviceIdString1 = cacdeviceIdStringSet[0]
							.split("\\=\\[");
					Cacdevice cacdevice = cacdeviceDAO
							.findCacdeviceByPrimaryKey(Integer
									.parseInt(cacdeviceIdString1[1]));
					cacdeviceSet.add(cacdevice);
				}
			}
			setValue(cacdeviceSet);
		} else
			setValue(null);
	}
}