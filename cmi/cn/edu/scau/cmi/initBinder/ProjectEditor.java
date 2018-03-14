package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("ProjectEditor")
public class ProjectEditor extends PropertyEditorSupport {

	@Autowired
	private ProjectDAO projectDAO;

	public void setAsText(String projectString) {
		Project project = new Project();
		if (projectString.length() > 0) {
			String[] projectStringSet = projectString.split("\\] ");
			String[] room2deviceId1 = projectStringSet[0].split("\\=\\[");

			project = projectDAO.findProjectByPrimaryKey(Integer
					.parseInt(room2deviceId1[1]));
			setValue(project);
		} else
			setValue(null);
	}
}