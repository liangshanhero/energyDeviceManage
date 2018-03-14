package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategytypeEditor")
public class WhstrategytypeEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategytypeDAO whstrategytypeDAO;
	
	public void setAsText(String whstrategytypeString){
		Whstrategytype whstrategytype=new Whstrategytype();
		if(whstrategytypeString.length()>0){
		String[] whstrategytypeStringSet=whstrategytypeString.split("\\] ");
		String[] room2deviceId1=whstrategytypeStringSet[0].split("\\=\\[");
				
		whstrategytype=whstrategytypeDAO.findWhstrategytypeByPrimaryKey(Integer.parseInt(room2deviceId1[1]));
		setValue(whstrategytype);
		}
		else
		setValue(null);
	}
}