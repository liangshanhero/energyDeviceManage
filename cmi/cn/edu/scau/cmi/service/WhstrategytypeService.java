package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;


public interface WhstrategytypeService {

	public void saveWhstrategytype(Whstrategytype whstrategytype);

	public Whstrategytype saveWhstrategytypeWhstrategydetails(Integer id, Whstrategydetail related_whstrategydetails);

	public Set<Whstrategytype> loadWhstrategytypes();

	public Set<Whstrategytype> loadWhstrategytypes(int index, int size);

	public void deleteWhstrategytype(Whstrategytype whstrategytype);

	public Whstrategytype deleteWhstrategytypeWhstrategydetails(Integer id, Integer related_whstrategydetails_id);

	public List<Whstrategytype> findAllWhstrategytypes(Integer startResult, Integer maxRows);

	public Whstrategytype findWhstrategytypeByPrimaryKey(Integer id);

	public Integer countwhstrategytypes();

}
