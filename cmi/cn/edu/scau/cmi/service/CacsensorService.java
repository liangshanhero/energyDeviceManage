package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacsensorService {

	public void saveCacsensor(Cacsensor cacsensor);

	public Cacsensor saveCacsensorCac(Integer id, Cac related_cac);

	public Cacsensor saveCacsensorCacsensordatas(Integer id,
			Cacsensordata related_cacsensordatas);

	public Set<Cacsensor> loadCacsensors();

	public Set<Cacsensor> loadCacsensors(int index, int size);

	public void deleteCacsensor(Cacsensor cacsensor);

	public Cacsensor deleteCacsensorCac(Integer id, Integer related_cac_id);

	public Cacsensor deleteCacsensorCacsensordatas(Integer id,
			Integer related_cacsensordatas_cacrecordtime,
			Integer related_cacsensordatas_cacsensor);

	public List<Cacsensor> findAllCacsensors(Integer startResult,
			Integer maxRows);

	public Cacsensor findCacsensorByPrimaryKey(Integer id);

	public Integer countcacsensors();

	public Integer countRelativeCacCacsensors(Integer cac);

}