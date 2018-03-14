package cn.edu.scau.cmi.pengjie.dataPersistence;

import java.io.File;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import cn.edu.scau.cmi.domain.Cac;
import cn.edu.scau.cmi.domain.Cacdevice;
import cn.edu.scau.cmi.domain.Cacdevicedata;
import cn.edu.scau.cmi.domain.Cacmalfunction;
import cn.edu.scau.cmi.domain.Cacrecordtime;
import cn.edu.scau.cmi.domain.Cacsensor;
import cn.edu.scau.cmi.domain.Cacsensordata;
import cn.edu.scau.cmi.domain.Project;

public interface CACPersistenceService {

	public Cac saveCac(Project project, String remark);

	public Cacsensor saveCacsensor(Cac cac, String name);

	public Cacdevice saveCacdevice(Cac cac, String name, String unit);

	public Cacmalfunction saveCacmalfunction(Cacrecordtime recordtime,
			Cacdevice cacdevice, String status);

	public Cacrecordtime saveCacrecordtime(Calendar RecordTime,
			String watchkeeper);

	public Cacsensordata saveCacsensordata(Cacrecordtime cacrecordtime,
			Cacsensor cacsensor, String value, int isreport);

	public Cacdevicedata saveCacdevicedata(Cacdevice cacdevice,
			Cacrecordtime cacarecordtime, BigDecimal value, int isreport);

	public void batchSaveCacdeviceData(List<Cacdevicedata> cacdevicedataSet,
			Cacdevice cacdevice);

	public void batchSaveCacsensorData(List<Cacsensordata> cacsensordataSest,
			Cacsensor cacsensor);

	// public Cacsensor saveCacsensor(File file ,Cac cac);

}
