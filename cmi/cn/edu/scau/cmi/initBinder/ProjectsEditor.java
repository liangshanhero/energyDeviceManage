package cn.edu.scau.cmi.initBinder;

import java.beans.PropertyEditorSupport;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import cn.edu.scau.cmi.dao.*;
import cn.edu.scau.cmi.domain.*;

@Component("ProjectsEditor")
public class ProjectsEditor extends PropertyEditorSupport {

	@Autowired
	private ProjectDAO projectDAO;

	public void setAsText(String projects) {

		Set<Project> projectSet = new HashSet<Project>();
		if (projects.length() > 0) {
			String[] sourceProjectStringSet = projects.split(",");
			if (sourceProjectStringSet != null) {
				for (String projectStringSet : sourceProjectStringSet) {
					String[] projectIdStringSet = projectStringSet
							.split("\\] ");
					String[] projectIdString1 = projectIdStringSet[0]
							.split("\\=\\[");
					Project project = projectDAO
							.findProjectByPrimaryKey(Integer
									.parseInt(projectIdString1[1]));
					projectSet.add(project);
				}
			}
			setValue(projectSet);
		} else
			setValue(null);
	}
}