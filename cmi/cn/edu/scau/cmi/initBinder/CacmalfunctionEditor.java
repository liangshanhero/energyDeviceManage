package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacmalfunctionEditor")
public class CacmalfunctionEditor extends PropertyEditorSupport {

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	public void setAsText(String cacmalfunctionString) {
		Cacmalfunction cacmalfunction = new Cacmalfunction();
		if (cacmalfunctionString.length() > 0) {
			String[] cacmalfunctionStringSet = cacmalfunctionString
					.split("\\] ");
			String[] room2deviceId1 = cacmalfunctionStringSet[0]
					.split("\\=\\[");
			String[] room2deviceId2 = cacmalfunctionStringSet[1]
					.split("\\=\\[");

			cacmalfunction = cacmalfunctionDAO.findCacmalfunctionByPrimaryKey(
					Integer.parseInt(room2deviceId1[1]),
					Integer.parseInt(room2deviceId2[1]));
			setValue(cacmalfunction);
		} else
			setValue(null);
	}
}