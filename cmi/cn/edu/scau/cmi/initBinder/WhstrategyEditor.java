package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategyEditor")
public class WhstrategyEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategyDAO whstrategyDAO;
	
	public void setAsText(String whstrategyString){
		Whstrategy whstrategy=new Whstrategy();
		if(whstrategyString.length()>0){
		String[] whstrategyStringSet=whstrategyString.split("\\] ");
		String[] room2deviceId1=whstrategyStringSet[0].split("\\=\\[");
				
		whstrategy=whstrategyDAO.findWhstrategyByPrimaryKey(Integer.parseInt(room2deviceId1[1]));
		setValue(whstrategy);
		}
		else
		setValue(null);
	}
}