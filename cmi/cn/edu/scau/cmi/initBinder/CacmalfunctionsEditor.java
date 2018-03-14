package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacmalfunctionsEditor")
public class CacmalfunctionsEditor extends PropertyEditorSupport {

	@Autowired
	private CacmalfunctionDAO cacmalfunctionDAO;

	public void setAsText(String cacmalfunctions) {

		Set<Cacmalfunction> cacmalfunctionSet = new HashSet<Cacmalfunction>();
		if (cacmalfunctions.length() > 0) {
			String[] sourceCacmalfunctionStringSet = cacmalfunctions.split(",");
			if (sourceCacmalfunctionStringSet != null) {
				for (String cacmalfunctionStringSet : sourceCacmalfunctionStringSet) {
					String[] cacmalfunctionIdStringSet = cacmalfunctionStringSet
							.split("\\] ");
					String[] cacmalfunctionIdString1 = cacmalfunctionIdStringSet[0]
							.split("\\=\\[");
					String[] cacmalfunctionIdString2 = cacmalfunctionIdStringSet[1]
							.split("\\=\\[");
					Cacmalfunction cacmalfunction = cacmalfunctionDAO
							.findCacmalfunctionByPrimaryKey(
									Integer.parseInt(cacmalfunctionIdString1[1]),
									Integer.parseInt(cacmalfunctionIdString2[1]));
					cacmalfunctionSet.add(cacmalfunction);
				}
			}
			setValue(cacmalfunctionSet);
		} else
			setValue(null);
	}
}