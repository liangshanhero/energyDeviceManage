package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("LedmeterdatasEditor")
public class LedmeterdatasEditor extends PropertyEditorSupport {

	@Autowired
	private LedmeterdataDAO ledmeterdataDAO;

	public void setAsText(String ledmeterdatas) {

		Set<Ledmeterdata> ledmeterdataSet = new HashSet<Ledmeterdata>();
		if (ledmeterdatas.length() > 0) {
			String[] sourceLedmeterdataStringSet = ledmeterdatas.split(",");
			if (sourceLedmeterdataStringSet != null) {
				for (String ledmeterdataStringSet : sourceLedmeterdataStringSet) {
					String[] ledmeterdataIdStringSet = ledmeterdataStringSet
							.split("\\] ");
					String[] ledmeterdataIdString1 = ledmeterdataIdStringSet[0]
							.split("\\=\\[");
					Ledmeterdata ledmeterdata = ledmeterdataDAO
							.findLedmeterdataByPrimaryKey(Integer
									.parseInt(ledmeterdataIdString1[1]));
					ledmeterdataSet.add(ledmeterdata);
				}
			}
			setValue(ledmeterdataSet);
		} else
			setValue(null);
	}
}