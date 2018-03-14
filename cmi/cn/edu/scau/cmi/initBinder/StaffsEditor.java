package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("StaffsEditor")
public class StaffsEditor extends PropertyEditorSupport {

	@Autowired
	private StaffDAO staffDAO;

	public void setAsText(String staffs) {

		Set<Staff> staffSet = new HashSet<Staff>();
		if (staffs.length() > 0) {
			String[] sourceStaffStringSet = staffs.split(",");
			if (sourceStaffStringSet != null) {
				for (String staffStringSet : sourceStaffStringSet) {
					String[] staffIdStringSet = staffStringSet.split("\\] ");
					String[] staffIdString1 = staffIdStringSet[0]
							.split("\\=\\[");
					Staff staff = staffDAO.findStaffByPrimaryKey(Integer
							.parseInt(staffIdString1[1]));
					staffSet.add(staff);
				}
			}
			setValue(staffSet);
		} else
			setValue(null);
	}
}