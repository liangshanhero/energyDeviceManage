package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedmeterEditor")
public class LedmeterEditor extends PropertyEditorSupport {

	@Autowired
	private LedmeterDAO ledmeterDAO;

	public void setAsText(String ledmeterString) {
		Ledmeter ledmeter = new Ledmeter();
		if (ledmeterString.length() > 0) {
			String[] ledmeterStringSet = ledmeterString.split("\\] ");
			String[] room2deviceId1 = ledmeterStringSet[0].split("\\=\\[");

			ledmeter = ledmeterDAO.findLedmeterByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(ledmeter);
		} else
			setValue(null);
	}
}