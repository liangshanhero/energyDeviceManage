package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategysEditor")
public class WhstrategysEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategyDAO whstrategyDAO;
	
	public void setAsText(String whstrategys){
		
		Set<Whstrategy> whstrategySet = new HashSet<Whstrategy>();
		if(whstrategys.length()>0){
		String[] sourceWhstrategyStringSet = whstrategys.split(",");
		if (sourceWhstrategyStringSet != null) {
			for (String whstrategyStringSet : sourceWhstrategyStringSet) {
				String[] whstrategyIdStringSet = whstrategyStringSet.split("\\] ");
				String[] whstrategyIdString1 = whstrategyIdStringSet[0].split("\\=\\[");
				Whstrategy whstrategy = whstrategyDAO.findWhstrategyByPrimaryKey(Integer.parseInt(whstrategyIdString1[1]));
				whstrategySet.add(whstrategy);
				}
			}
			setValue(whstrategySet);
		}
		else
			setValue(null);
	}
}