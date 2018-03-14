package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedmetersEditor")
public class LedmetersEditor extends PropertyEditorSupport {

	@Autowired
	private LedmeterDAO ledmeterDAO;

	public void setAsText(String ledmeters) {

		Set<Ledmeter> ledmeterSet = new HashSet<Ledmeter>();
		if (ledmeters.length() > 0) {
			String[] sourceLedmeterStringSet = ledmeters.split(",");
			if (sourceLedmeterStringSet != null) {
				for (String ledmeterStringSet : sourceLedmeterStringSet) {
					String[] ledmeterIdStringSet = ledmeterStringSet
							.split("\\] ");
					String[] ledmeterIdString1 = ledmeterIdStringSet[0]
							.split("\\=\\[");
					Ledmeter ledmeter = ledmeterDAO
							.findLedmeterByPrimaryKey(Integer
									.parseInt(ledmeterIdString1[1]));
					ledmeterSet.add(ledmeter);
				}
			}
			setValue(ledmeterSet);
		} else
			setValue(null);
	}
}