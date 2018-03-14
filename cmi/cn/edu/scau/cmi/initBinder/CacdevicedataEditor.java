package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacdevicedataEditor")
public class CacdevicedataEditor extends PropertyEditorSupport {

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	public void setAsText(String cacdevicedataString) {
		Cacdevicedata cacdevicedata = new Cacdevicedata();
		if (cacdevicedataString.length() > 0) {
			String[] cacdevicedataStringSet = cacdevicedataString.split("\\] ");
			String[] room2deviceId1 = cacdevicedataStringSet[0].split("\\=\\[");
			String[] room2deviceId2 = cacdevicedataStringSet[1].split("\\=\\[");

			cacdevicedata = cacdevicedataDAO.findCacdevicedataByPrimaryKey(
					Integer.parseInt(room2deviceId1[1]),
					Integer.parseInt(room2deviceId2[1]));
			setValue(cacdevicedata);
		} else
			setValue(null);
	}
}