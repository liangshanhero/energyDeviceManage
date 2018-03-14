package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacsensorEditor")
public class CacsensorEditor extends PropertyEditorSupport {

	@Autowired
	private CacsensorDAO cacsensorDAO;

	public void setAsText(String cacsensorString) {
		Cacsensor cacsensor = new Cacsensor();
		if (cacsensorString.length() > 0) {
			String[] cacsensorStringSet = cacsensorString.split("\\] ");
			String[] room2deviceId1 = cacsensorStringSet[0].split("\\=\\[");

			cacsensor = cacsensorDAO.findCacsensorByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(cacsensor);
		} else
			setValue(null);
	}
}