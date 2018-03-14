package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdevicesEditor")
public class WhdevicesEditor extends PropertyEditorSupport {

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	public void setAsText(String whdevices) {

		Set<Whdevice> whdeviceSet = new HashSet<Whdevice>();
		if (whdevices.length() > 0) {
			String[] sourceWhdeviceStringSet = whdevices.split(",");
			if (sourceWhdeviceStringSet != null) {
				for (String whdeviceStringSet : sourceWhdeviceStringSet) {
					String[] whdeviceIdStringSet = whdeviceStringSet
							.split("\\] ");
					String[] whdeviceIdString1 = whdeviceIdStringSet[0]
							.split("\\=\\[");
					Whdevice whdevice = whdeviceDAO
							.findWhdeviceByPrimaryKey(Integer
									.parseInt(whdeviceIdString1[1]));
					whdeviceSet.add(whdevice);
				}
			}
			setValue(whdeviceSet);
		} else
			setValue(null);
	}
}