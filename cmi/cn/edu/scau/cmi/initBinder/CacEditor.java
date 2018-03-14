package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacEditor")
public class CacEditor extends PropertyEditorSupport {

	@Autowired
	private CacDAO cacDAO;

	public void setAsText(String cacString) {
		Cac cac = new Cac();
		if (cacString.length() > 0) {
			String[] cacStringSet = cacString.split("\\] ");
			String[] room2deviceId1 = cacStringSet[0].split("\\=\\[");

			cac = cacDAO.findCacByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(cac);
		} else
			setValue(null);
	}
}