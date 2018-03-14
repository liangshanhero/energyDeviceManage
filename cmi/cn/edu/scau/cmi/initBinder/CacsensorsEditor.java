package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacsensorsEditor")
public class CacsensorsEditor extends PropertyEditorSupport {

	@Autowired
	private CacsensorDAO cacsensorDAO;

	public void setAsText(String cacsensors) {

		Set<Cacsensor> cacsensorSet = new HashSet<Cacsensor>();
		if (cacsensors.length() > 0) {
			String[] sourceCacsensorStringSet = cacsensors.split(",");
			if (sourceCacsensorStringSet != null) {
				for (String cacsensorStringSet : sourceCacsensorStringSet) {
					String[] cacsensorIdStringSet = cacsensorStringSet
							.split("\\] ");
					String[] cacsensorIdString1 = cacsensorIdStringSet[0]
							.split("\\=\\[");
					Cacsensor cacsensor = cacsensorDAO
							.findCacsensorByPrimaryKey(Integer
									.parseInt(cacsensorIdString1[1]));
					cacsensorSet.add(cacsensor);
				}
			}
			setValue(cacsensorSet);
		} else
			setValue(null);
	}
}