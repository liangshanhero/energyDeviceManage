package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdatatypesEditor")
public class WhdatatypesEditor extends PropertyEditorSupport {

	@Autowired
	private WhdatatypeDAO whdatatypeDAO;

	public void setAsText(String whdatatypes) {

		Set<Whdatatype> whdatatypeSet = new HashSet<Whdatatype>();
		if (whdatatypes.length() > 0) {
			String[] sourceWhdatatypeStringSet = whdatatypes.split(",");
			if (sourceWhdatatypeStringSet != null) {
				for (String whdatatypeStringSet : sourceWhdatatypeStringSet) {
					String[] whdatatypeIdStringSet = whdatatypeStringSet
							.split("\\] ");
					String[] whdatatypeIdString1 = whdatatypeIdStringSet[0]
							.split("\\=\\[");
					Whdatatype whdatatype = whdatatypeDAO
							.findWhdatatypeByPrimaryKey(Integer
									.parseInt(whdatatypeIdString1[1]));
					whdatatypeSet.add(whdatatype);
				}
			}
			setValue(whdatatypeSet);
		} else
			setValue(null);
	}
}