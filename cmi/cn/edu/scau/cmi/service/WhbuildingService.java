package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface WhbuildingService {

	public void saveWhbuilding(Whbuilding whbuilding);

	public Whbuilding saveWhbuildingProject(Integer id, Project related_project);

	public Whbuilding saveWhbuildingWhdevices(Integer id,
			Whdevice related_whdevices);

	public Set<Whbuilding> loadWhbuildings();

	public Set<Whbuilding> loadWhbuildings(int index, int size);

	public void deleteWhbuilding(Whbuilding whbuilding);

	public Whbuilding deleteWhbuildingProject(Integer id,
			Integer related_project_id);

	public Whbuilding deleteWhbuildingWhdevices(Integer id,
			Integer related_whdevices_id);

	public List<Whbuilding> findAllWhbuildings(Integer startResult,
			Integer maxRows);

	public Whbuilding findWhbuildingByPrimaryKey(Integer id);

	public Integer countwhbuildings();

	public Integer countRelativeProjectWhbuildings(Integer project);

}