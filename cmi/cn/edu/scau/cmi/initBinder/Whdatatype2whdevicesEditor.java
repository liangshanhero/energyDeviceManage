package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("Whdatatype2whdevicesEditor")
public class Whdatatype2whdevicesEditor extends PropertyEditorSupport {

	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	public void setAsText(String whdatatype2whdevices) {

		Set<Whdatatype2whdevice> whdatatype2whdeviceSet = new HashSet<Whdatatype2whdevice>();
		if (whdatatype2whdevices.length() > 0) {
			String[] sourceWhdatatype2whdeviceStringSet = whdatatype2whdevices
					.split(",");
			if (sourceWhdatatype2whdeviceStringSet != null) {
				for (String whdatatype2whdeviceStringSet : sourceWhdatatype2whdeviceStringSet) {
					String[] whdatatype2whdeviceIdStringSet = whdatatype2whdeviceStringSet
							.split("\\] ");
					String[] whdatatype2whdeviceIdString1 = whdatatype2whdeviceIdStringSet[0]
							.split("\\=\\[");
					String[] whdatatype2whdeviceIdString2 = whdatatype2whdeviceIdStringSet[1]
							.split("\\=\\[");
					Whdatatype2whdevice whdatatype2whdevice = whdatatype2whdeviceDAO
							.findWhdatatype2whdeviceByPrimaryKey(
									Integer.parseInt(whdatatype2whdeviceIdString1[1]),
									Integer.parseInt(whdatatype2whdeviceIdString2[1]));
					whdatatype2whdeviceSet.add(whdatatype2whdevice);
				}
			}
			setValue(whdatatype2whdeviceSet);
		} else
			setValue(null);
	}
}