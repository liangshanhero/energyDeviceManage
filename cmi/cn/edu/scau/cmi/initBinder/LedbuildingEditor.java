package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedbuildingEditor")
public class LedbuildingEditor extends PropertyEditorSupport {

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	public void setAsText(String ledbuildingString) {
		Ledbuilding ledbuilding = new Ledbuilding();
		if (ledbuildingString.length() > 0) {
			String[] ledbuildingStringSet = ledbuildingString.split("\\] ");
			String[] room2deviceId1 = ledbuildingStringSet[0].split("\\=\\[");

			ledbuilding = ledbuildingDAO.findLedbuildingByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(ledbuilding);
		} else
			setValue(null);
	}
}