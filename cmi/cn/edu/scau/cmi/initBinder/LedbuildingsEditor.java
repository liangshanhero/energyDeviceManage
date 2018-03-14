package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedbuildingsEditor")
public class LedbuildingsEditor extends PropertyEditorSupport {

	@Autowired
	private LedbuildingDAO ledbuildingDAO;

	public void setAsText(String ledbuildings) {

		Set<Ledbuilding> ledbuildingSet = new HashSet<Ledbuilding>();
		if (ledbuildings.length() > 0) {
			String[] sourceLedbuildingStringSet = ledbuildings.split(",");
			if (sourceLedbuildingStringSet != null) {
				for (String ledbuildingStringSet : sourceLedbuildingStringSet) {
					String[] ledbuildingIdStringSet = ledbuildingStringSet
							.split("\\] ");
					String[] ledbuildingIdString1 = ledbuildingIdStringSet[0]
							.split("\\=\\[");
					Ledbuilding ledbuilding = ledbuildingDAO
							.findLedbuildingByPrimaryKey(Integer
									.parseInt(ledbuildingIdString1[1]));
					ledbuildingSet.add(ledbuilding);
				}
			}
			setValue(ledbuildingSet);
		} else
			setValue(null);
	}
}