package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacService {

	public void saveCac(Cac cac);

	public Cac saveCacProject(Integer id, Project related_project);

	public Cac saveCacCacdevices(Integer id, Cacdevice related_cacdevices);

	public Cac saveCacCacsensors(Integer id, Cacsensor related_cacsensors);

	public Set<Cac> loadCacs();

	public Set<Cac> loadCacs(int index, int size);

	public void deleteCac(Cac cac);

	public Cac deleteCacProject(Integer id, Integer related_project_id);

	public Cac deleteCacCacdevices(Integer id, Integer related_cacdevices_id);

	public Cac deleteCacCacsensors(Integer id, Integer related_cacsensors_id);

	public List<Cac> findAllCacs(Integer startResult, Integer maxRows);

	public Cac findCacByPrimaryKey(Integer id);

	public Integer countcacs();

	public Integer countRelativeProjectCacs(Integer project);

}