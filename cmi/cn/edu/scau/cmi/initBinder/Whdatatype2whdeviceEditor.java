package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("Whdatatype2whdeviceEditor")
public class Whdatatype2whdeviceEditor extends PropertyEditorSupport {

	@Autowired
	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;

	public void setAsText(String whdatatype2whdeviceString) {
		Whdatatype2whdevice whdatatype2whdevice = new Whdatatype2whdevice();
		if (whdatatype2whdeviceString.length() > 0) {
			String[] whdatatype2whdeviceStringSet = whdatatype2whdeviceString
					.split("\\] ");
			String[] room2deviceId1 = whdatatype2whdeviceStringSet[0]
					.split("\\=\\[");
			String[] room2deviceId2 = whdatatype2whdeviceStringSet[1]
					.split("\\=\\[");

			whdatatype2whdevice = whdatatype2whdeviceDAO
					.findWhdatatype2whdeviceByPrimaryKey(
							Integer.parseInt(room2deviceId1[1]),
							Integer.parseInt(room2deviceId2[1]));
			setValue(whdatatype2whdevice);
		} else
			setValue(null);
	}
}