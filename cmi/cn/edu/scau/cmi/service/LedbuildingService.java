package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface LedbuildingService {

	public void saveLedbuilding(Ledbuilding ledbuilding);

	public Ledbuilding saveLedbuildingProject(Integer id,
			Project related_project);

	public Ledbuilding saveLedbuildingLedmeters(Integer id,
			Ledmeter related_ledmeters);

	public Set<Ledbuilding> loadLedbuildings();

	public Set<Ledbuilding> loadLedbuildings(int index, int size);

	public void deleteLedbuilding(Ledbuilding ledbuilding);

	public Ledbuilding deleteLedbuildingProject(Integer id,
			Integer related_project_id);

	public Ledbuilding deleteLedbuildingLedmeters(Integer id,
			Integer related_ledmeters_id);

	public List<Ledbuilding> findAllLedbuildings(Integer startResult,
			Integer maxRows);

	public Ledbuilding findLedbuildingByPrimaryKey(Integer id);

	public Integer countledbuildings();

	public Integer countRelativeProjectLedbuildings(Integer project);

}