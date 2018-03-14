package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("WhstrategydetailEditor")
public class WhstrategydetailEditor extends PropertyEditorSupport{

	@Autowired
	private WhstrategydetailDAO whstrategydetailDAO;
	
	public void setAsText(String whstrategydetailString){
		Whstrategydetail whstrategydetail=new Whstrategydetail();
		if(whstrategydetailString.length()>0){
		String[] whstrategydetailStringSet=whstrategydetailString.split("\\] ");
		String[] room2deviceId1=whstrategydetailStringSet[0].split("\\=\\[");
				
		whstrategydetail=whstrategydetailDAO.findWhstrategydetailByPrimaryKey(Integer.parseInt(room2deviceId1[1]));
		setValue(whstrategydetail);
		}
		else
		setValue(null);
	}
}