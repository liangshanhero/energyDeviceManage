package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacrecordtimeEditor")
public class CacrecordtimeEditor extends PropertyEditorSupport {

	@Autowired
	private CacrecordtimeDAO cacrecordtimeDAO;

	public void setAsText(String cacrecordtimeString) {
		Cacrecordtime cacrecordtime = new Cacrecordtime();
		if (cacrecordtimeString.length() > 0) {
			String[] cacrecordtimeStringSet = cacrecordtimeString.split("\\] ");
			String[] room2deviceId1 = cacrecordtimeStringSet[0].split("\\=\\[");

			cacrecordtime = cacrecordtimeDAO
					.findCacrecordtimeByPrimaryKey(Integer
							.parseInt(room2deviceId1[1]));
			setValue(cacrecordtime);
		} else
			setValue(null);
	}
}