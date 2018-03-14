package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhbuildingsEditor")
public class WhbuildingsEditor extends PropertyEditorSupport {

	@Autowired
	private WhbuildingDAO whbuildingDAO;

	public void setAsText(String whbuildings) {

		Set<Whbuilding> whbuildingSet = new HashSet<Whbuilding>();
		if (whbuildings.length() > 0) {
			String[] sourceWhbuildingStringSet = whbuildings.split(",");
			if (sourceWhbuildingStringSet != null) {
				for (String whbuildingStringSet : sourceWhbuildingStringSet) {
					String[] whbuildingIdStringSet = whbuildingStringSet
							.split("\\] ");
					String[] whbuildingIdString1 = whbuildingIdStringSet[0]
							.split("\\=\\[");
					Whbuilding whbuilding = whbuildingDAO
							.findWhbuildingByPrimaryKey(Integer
									.parseInt(whbuildingIdString1[1]));
					whbuildingSet.add(whbuilding);
				}
			}
			setValue(whbuildingSet);
		} else
			setValue(null);
	}
}