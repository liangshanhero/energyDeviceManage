package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacdevicedatasEditor")
public class CacdevicedatasEditor extends PropertyEditorSupport {

	@Autowired
	private CacdevicedataDAO cacdevicedataDAO;

	public void setAsText(String cacdevicedatas) {

		Set<Cacdevicedata> cacdevicedataSet = new HashSet<Cacdevicedata>();
		if (cacdevicedatas.length() > 0) {
			String[] sourceCacdevicedataStringSet = cacdevicedatas.split(",");
			if (sourceCacdevicedataStringSet != null) {
				for (String cacdevicedataStringSet : sourceCacdevicedataStringSet) {
					String[] cacdevicedataIdStringSet = cacdevicedataStringSet
							.split("\\] ");
					String[] cacdevicedataIdString1 = cacdevicedataIdStringSet[0]
							.split("\\=\\[");
					String[] cacdevicedataIdString2 = cacdevicedataIdStringSet[1]
							.split("\\=\\[");
					Cacdevicedata cacdevicedata = cacdevicedataDAO
							.findCacdevicedataByPrimaryKey(
									Integer.parseInt(cacdevicedataIdString1[1]),
									Integer.parseInt(cacdevicedataIdString2[1]));
					cacdevicedataSet.add(cacdevicedata);
				}
			}
			setValue(cacdevicedataSet);
		} else
			setValue(null);
	}
}