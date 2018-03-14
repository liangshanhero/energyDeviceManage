package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdatatypeEditor")
public class WhdatatypeEditor extends PropertyEditorSupport {

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	public void setAsText(String whdatatypeString) {
		Whdatatype whdatatype = new Whdatatype();
		if (whdatatypeString.length() > 0) {
			String[] whdatatypeStringSet = whdatatypeString.split("\\] ");
			String[] room2deviceId1 = whdatatypeStringSet[0].split("\\=\\[");

			whdatatype = whdatatypeDAO.findWhdatatypeByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(whdatatype);
		} else
			setValue(null);
	}
}