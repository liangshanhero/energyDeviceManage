package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacsensordatasEditor")
public class CacsensordatasEditor extends PropertyEditorSupport {

	@Autowired
	private CacsensordataDAO cacsensordataDAO;

	public void setAsText(String cacsensordatas) {

		Set<Cacsensordata> cacsensordataSet = new HashSet<Cacsensordata>();
		if (cacsensordatas.length() > 0) {
			String[] sourceCacsensordataStringSet = cacsensordatas.split(",");
			if (sourceCacsensordataStringSet != null) {
				for (String cacsensordataStringSet : sourceCacsensordataStringSet) {
					String[] cacsensordataIdStringSet = cacsensordataStringSet
							.split("\\] ");
					String[] cacsensordataIdString1 = cacsensordataIdStringSet[0]
							.split("\\=\\[");
					String[] cacsensordataIdString2 = cacsensordataIdStringSet[1]
							.split("\\=\\[");
					Cacsensordata cacsensordata = cacsensordataDAO
							.findCacsensordataByPrimaryKey(
									Integer.parseInt(cacsensordataIdString1[1]),
									Integer.parseInt(cacsensordataIdString2[1]));
					cacsensordataSet.add(cacsensordata);
				}
			}
			setValue(cacsensordataSet);
		} else
			setValue(null);
	}
}