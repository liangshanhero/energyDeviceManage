package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacdeviceEditor")
public class CacdeviceEditor extends PropertyEditorSupport {

	@Autowired
	private CacdeviceDAO cacdeviceDAO;

	public void setAsText(String cacdeviceString) {
		Cacdevice cacdevice = new Cacdevice();
		if (cacdeviceString.length() > 0) {
			String[] cacdeviceStringSet = cacdeviceString.split("\\] ");
			String[] room2deviceId1 = cacdeviceStringSet[0].split("\\=\\[");

			cacdevice = cacdeviceDAO.findCacdeviceByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(cacdevice);
		} else
			setValue(null);
	}
}