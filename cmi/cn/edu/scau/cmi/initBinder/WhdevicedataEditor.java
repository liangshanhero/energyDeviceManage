package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdevicedataEditor")
public class WhdevicedataEditor extends PropertyEditorSupport {

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	public void setAsText(String whdevicedataString) {
		Whdevicedata whdevicedata = new Whdevicedata();
		if (whdevicedataString.length() > 0) {
			String[] whdevicedataStringSet = whdevicedataString.split("\\] ");
			String[] room2deviceId1 = whdevicedataStringSet[0].split("\\=\\[");

			whdevicedata = whdevicedataDAO.findWhdevicedataByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(whdevicedata);
		} else
			setValue(null);
	}
}