package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategytypesEditor")
public class WhstrategytypesEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;
	
	public void setAsText(String whstrategytypes){
		
		Set<Whstrategytype> whstrategytypeSet = new HashSet<Whstrategytype>();
		if(whstrategytypes.length()>0){
		String[] sourceWhstrategytypeStringSet = whstrategytypes.split(",");
		if (sourceWhstrategytypeStringSet != null) {
			for (String whstrategytypeStringSet : sourceWhstrategytypeStringSet) {
				String[] whstrategytypeIdStringSet = whstrategytypeStringSet.split("\\] ");
				String[] whstrategytypeIdString1 = whstrategytypeIdStringSet[0].split("\\=\\[");
				Whstrategytype whstrategytype = whstrategytypeDAO.findWhstrategytypeByPrimaryKey(Integer.parseInt(whstrategytypeIdString1[1]));
				whstrategytypeSet.add(whstrategytype);
				}
			}
			setValue(whstrategytypeSet);
		}
		else
			setValue(null);
	}
}