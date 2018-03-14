package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategydetailsEditor")
public class WhstrategydetailsEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;
	
	public void setAsText(String whstrategydetails){
		
		Set<Whstrategydetail> whstrategydetailSet = new HashSet<Whstrategydetail>();
		if(whstrategydetails.length()>0){
		String[] sourceWhstrategydetailStringSet = whstrategydetails.split(",");
		if (sourceWhstrategydetailStringSet != null) {
			for (String whstrategydetailStringSet : sourceWhstrategydetailStringSet) {
				String[] whstrategydetailIdStringSet = whstrategydetailStringSet.split("\\] ");
				String[] whstrategydetailIdString1 = whstrategydetailIdStringSet[0].split("\\=\\[");
				Whstrategydetail whstrategydetail = whstrategydetailDAO.findWhstrategydetailByPrimaryKey(Integer.parseInt(whstrategydetailIdString1[1]));
				whstrategydetailSet.add(whstrategydetail);
				}
			}
			setValue(whstrategydetailSet);
		}
		else
			setValue(null);
	}
}