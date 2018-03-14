package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhdevicedatasEditor")
public class WhdevicedatasEditor extends PropertyEditorSupport {

	@Autowired
	private WhdevicedataDAO whdevicedataDAO;

	public void setAsText(String whdevicedatas) {

		Set<Whdevicedata> whdevicedataSet = new HashSet<Whdevicedata>();
		if (whdevicedatas.length() > 0) {
			String[] sourceWhdevicedataStringSet = whdevicedatas.split(",");
			if (sourceWhdevicedataStringSet != null) {
				for (String whdevicedataStringSet : sourceWhdevicedataStringSet) {
					String[] whdevicedataIdStringSet = whdevicedataStringSet
							.split("\\] ");
					String[] whdevicedataIdString1 = whdevicedataIdStringSet[0]
							.split("\\=\\[");
					Whdevicedata whdevicedata = whdevicedataDAO
							.findWhdevicedataByPrimaryKey(Integer
									.parseInt(whdevicedataIdString1[1]));
					whdevicedataSet.add(whdevicedata);
				}
			}
			setValue(whdevicedataSet);
		} else
			setValue(null);
	}
}