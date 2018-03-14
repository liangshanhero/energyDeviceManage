package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;


public interface WhstrategyService {

	public void saveWhstrategy(Whstrategy whstrategy);

	public Whstrategy saveWhstrategyWhdevice(Integer id, Whdevice related_whdevice);

	public Whstrategy saveWhstrategyWhstrategydetails(Integer id, Whstrategydetail related_whstrategydetails);

	public Set<Whstrategy> loadWhstrategys();

	public Set<Whstrategy> loadWhstrategys(int index, int size);

	public void deleteWhstrategy(Whstrategy whstrategy);

	public Whstrategy deleteWhstrategyWhdevice(Integer id, Integer related_whdevice_id);

	public Whstrategy deleteWhstrategyWhstrategydetails(Integer id, Integer related_whstrategydetails_id);

	public List<Whstrategy> findAllWhstrategys(Integer startResult, Integer maxRows);

	public Whstrategy findWhstrategyByPrimaryKey(Integer id);

	public Integer countwhstrategys();

	public Integer countRelativeWhdeviceWhstrategys(Integer whdevice);

}
