package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedmeterdataEditor")
public class LedmeterdataEditor extends PropertyEditorSupport {

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	public void setAsText(String ledmeterdataString) {
		Ledmeterdata ledmeterdata = new Ledmeterdata();
		if (ledmeterdataString.length() > 0) {
			String[] ledmeterdataStringSet = ledmeterdataString.split("\\] ");
			String[] room2deviceId1 = ledmeterdataStringSet[0].split("\\=\\[");

			ledmeterdata = ledmeterdataDAO.findLedmeterdataByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(ledmeterdata);
		} else
			setValue(null);
	}
}