package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;

public interface LedmeterService {

	public void saveLedmeter(Ledmeter ledmeter);

	public Ledmeter saveLedmeterLedbuilding(Integer id,
			Ledbuilding related_ledbuilding);

	public Ledmeter saveLedmeterLedmeterdatas(Integer id,
			Ledmeterdata related_ledmeterdatas);

	public Set<Ledmeter> loadLedmeters();

	public Set<Ledmeter> loadLedmeters(int index, int size);

	public void deleteLedmeter(Ledmeter ledmeter);

	public Ledmeter deleteLedmeterLedbuilding(Integer id,
			Integer related_ledbuilding_id);

	public Ledmeter deleteLedmeterLedmeterdatas(Integer id,
			Integer related_ledmeterdatas_id);

	public List<Ledmeter> findAllLedmeters(Integer startResult, Integer maxRows);

	public Ledmeter findLedmeterByPrimaryKey(Integer id);

	public Integer countledmeters();

	public Integer countRelativeLedbuildingLedmeters(Integer ledbuilding);

}