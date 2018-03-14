package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacsensordataEditor")
public class CacsensordataEditor extends PropertyEditorSupport {

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	public void setAsText(String cacsensordataString) {
		Cacsensordata cacsensordata = new Cacsensordata();
		if (cacsensordataString.length() > 0) {
			String[] cacsensordataStringSet = cacsensordataString.split("\\] ");
			String[] room2deviceId1 = cacsensordataStringSet[0].split("\\=\\[");
			String[] room2deviceId2 = cacsensordataStringSet[1].split("\\=\\[");

			cacsensordata = cacsensordataDAO.findCacsensordataByPrimaryKey(
					Integer.parseInt(room2deviceId1[1]),
					Integer.parseInt(room2deviceId2[1]));
			setValue(cacsensordata);
		} else
			setValue(null);
	}
}