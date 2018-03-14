package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface LedmeterdataService {

	public void saveLedmeterdata(Ledmeterdata ledmeterdata);

	public Ledmeterdata saveLedmeterdataLedmeter(Integer id,
			Ledmeter related_ledmeter);

	public Set<Ledmeterdata> loadLedmeterdatas();

	public Set<Ledmeterdata> loadLedmeterdatas(int index, int size);

	public void deleteLedmeterdata(Ledmeterdata ledmeterdata);

	public Ledmeterdata deleteLedmeterdataLedmeter(Integer id,
			Integer related_ledmeter_id);

	public List<Ledmeterdata> findAllLedmeterdatas(Integer startResult,
			Integer maxRows);

	public Ledmeterdata findLedmeterdataByPrimaryKey(Integer id);

	public Integer countledmeterdatas();

	public Integer countRelativeLedmeterLedmeterdatas(Integer ledmeter);

}