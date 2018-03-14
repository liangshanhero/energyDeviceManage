package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhbuildingEditor")
public class WhbuildingEditor extends PropertyEditorSupport {

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	public void setAsText(String whbuildingString) {
		Whbuilding whbuilding = new Whbuilding();
		if (whbuildingString.length() > 0) {
			String[] whbuildingStringSet = whbuildingString.split("\\] ");
			String[] room2deviceId1 = whbuildingStringSet[0].split("\\=\\[");

			whbuilding = whbuildingDAO.findWhbuildingByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(whbuilding);
		} else
			setValue(null);
	}
}