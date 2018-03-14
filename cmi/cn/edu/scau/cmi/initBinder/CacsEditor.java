package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("CacsEditor")
public class CacsEditor extends PropertyEditorSupport {

	@Autowired
	private CacDAO cacDAO;

	public void setAsText(String cacs) {

		Set<Cac> cacSet = new HashSet<Cac>();
		if (cacs.length() > 0) {
			String[] sourceCacStringSet = cacs.split(",");
			if (sourceCacStringSet != null) {
				for (String cacStringSet : sourceCacStringSet) {
					String[] cacIdStringSet = cacStringSet.split("\\] ");
					String[] cacIdString1 = cacIdStringSet[0].split("\\=\\[");
					Cac cac = cacDAO.findCacByPrimaryKey(Integer
							.parseInt(cacIdString1[1]));
					cacSet.add(cac);
				}
			}
			setValue(cacSet);
		} else
			setValue(null);
	}
}