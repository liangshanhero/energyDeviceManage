package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("StaffEditor")
public class StaffEditor extends PropertyEditorSupport {

	@Autowired
	private StaffDAO staffDAO;

	public void setAsText(String staffString) {
		Staff staff = new Staff();
		if (staffString.length() > 0) {
			String[] staffStringSet = staffString.split("\\] ");
			String[] room2deviceId1 = staffStringSet[0].split("\\=\\[");

			staff = staffDAO.findStaffByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(staff);
		} else
			setValue(null);
	}
}