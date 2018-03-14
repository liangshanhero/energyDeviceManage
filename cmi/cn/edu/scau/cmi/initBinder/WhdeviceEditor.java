package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdeviceEditor")
public class WhdeviceEditor extends PropertyEditorSupport {

	@Autowired
	private WhdeviceDAO whdeviceDAO;

	public void setAsText(String whdeviceString) {
		Whdevice whdevice = new Whdevice();
		if (whdeviceString.length() > 0) {
			String[] whdeviceStringSet = whdeviceString.split("\\] ");
			String[] room2deviceId1 = whdeviceStringSet[0].split("\\=\\[");

			whdevice = whdeviceDAO.findWhdeviceByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(whdevice);
		} else
			setValue(null);
	}
}