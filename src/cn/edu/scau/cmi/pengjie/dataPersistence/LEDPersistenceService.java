package cn.edu.scau.cmi.pengjie.dataPersistence;

import java.math.BigDecimal;
import java.util.Date;

import cn.edu.scau.cmi.domain.Ledbuilding;
import cn.edu.scau.cmi.domain.Ledmeter;
import cn.edu.scau.cmi.domain.Ledmeterdata;
import cn.edu.scau.cmi.domain.Project;

public interface LEDPersistenceService {

	public Ledmeterdata saveLedmeterdata(BigDecimal value, Date date,
			Ledmeter ledmeter);

	public Ledmeter saveLedmeter(String number, Ledbuilding ledbuilding,
			String wellNum, int storey, BigDecimal totalamount,
			BigDecimal taotaldays);

	public Ledbuilding saveLedbuilding(String name, Project project, int well,
			int storey);
}
