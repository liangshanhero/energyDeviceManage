package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacrecordtimesEditor")
public class CacrecordtimesEditor extends PropertyEditorSupport {

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	public void setAsText(String cacrecordtimes) {

		Set<Cacrecordtime> cacrecordtimeSet = new HashSet<Cacrecordtime>();
		if (cacrecordtimes.length() > 0) {
			String[] sourceCacrecordtimeStringSet = cacrecordtimes.split(",");
			if (sourceCacrecordtimeStringSet != null) {
				for (String cacrecordtimeStringSet : sourceCacrecordtimeStringSet) {
					String[] cacrecordtimeIdStringSet = cacrecordtimeStringSet
							.split("\\] ");
					String[] cacrecordtimeIdString1 = cacrecordtimeIdStringSet[0]
							.split("\\=\\[");
					Cacrecordtime cacrecordtime = cacrecordtimeDAO
							.findCacrecordtimeByPrimaryKey(Integer
									.parseInt(cacrecordtimeIdString1[1]));
					cacrecordtimeSet.add(cacrecordtime);
				}
			}
			setValue(cacrecordtimeSet);
		} else
			setValue(null);
	}
}