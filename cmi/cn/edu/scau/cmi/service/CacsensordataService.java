package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface CacsensordataService {

	public void saveCacsensordata(Cacsensordata cacsensordata);

	public Cacsensordata saveCacsensordataCacrecordtime(Integer cacrecordtime,
			Integer cacsensor, Cacrecordtime related_cacrecordtime);

	public Cacsensordata saveCacsensordataCacsensor(Integer cacrecordtime,
			Integer cacsensor, Cacsensor related_cacsensor);

	public Set<Cacsensordata> loadCacsensordatas();

	public Set<Cacsensordata> loadCacsensordatas(int index, int size);

	public void deleteCacsensordata(Cacsensordata cacsensordata);

	public Cacsensordata deleteCacsensordataCacrecordtime(
			Integer cacrecordtime, Integer cacsensor,
			Integer related_cacrecordtime_id);

	public Cacsensordata deleteCacsensordataCacsensor(Integer cacrecordtime,
			Integer cacsensor, Integer related_cacsensor_id);

	public List<Cacsensordata> findAllCacsensordatas(Integer startResult,
			Integer maxRows);

	public Cacsensordata findCacsensordataByPrimaryKey(Integer cacrecordtime,
			Integer cacsensor);

	public Integer countcacsensordatas();

	public Integer countRelativeCacrecordtimeCacsensordatas(
			Integer cacrecordtime);

	public Integer countRelativeCacsensorCacsensordatas(Integer cacsensor);

}