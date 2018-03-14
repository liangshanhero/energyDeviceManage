package cn.edu.scau.cmi.service;

import java.util.List;
import java.util.Set;
import cn.edu.scau.cmi.domain.*;


public interface WhstrategydetailService {

	public void saveWhstrategydetail(Whstrategydetail whstrategydetail);

	public Whstrategydetail saveWhstrategydetailWhstrategy(Integer id, Whstrategy related_whstrategy);

	public Whstrategydetail saveWhstrategydetailWhstrategytype(Integer id, Whstrategytype related_whstrategytype);

	public Set<Whstrategydetail> loadWhstrategydetails();

	public Set<Whstrategydetail> loadWhstrategydetails(int index, int size);

	public void deleteWhstrategydetail(Whstrategydetail whstrategydetail);

	public Whstrategydetail deleteWhstrategydetailWhstrategy(Integer id, Integer related_whstrategy_id);

	public Whstrategydetail deleteWhstrategydetailWhstrategytype(Integer id, Integer related_whstrategytype_id);

	public List<Whstrategydetail> findAllWhstrategydetails(Integer startResult, Integer maxRows);

	public Whstrategydetail findWhstrategydetailByPrimaryKey(Integer id);

	public Integer countwhstrategydetails();

	public Integer countRelativeWhstrategyWhstrategydetails(Integer whstrategy);

	public Integer countRelativeWhstrategytypeWhstrategydetails(Integer whstrategytype);

}
