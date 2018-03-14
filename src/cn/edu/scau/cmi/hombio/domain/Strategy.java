package cn.edu.scau.cmi.hombio.domain;

import java.util.List;

/**
 * 
 * 用于把前端POST的JSON格式数据封装为对象
 * @author _TESLA_
 *
 */
public class Strategy {
	
	private Integer id;
	
	private String name;

	private List<StrategyDetail> details;
	
	private Boolean enable;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public List<StrategyDetail> getDetails() {
		return details;
	}

	public void setDetails(List<StrategyDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Strategy [id=" + id + ", name=" + name + ", details=" + details + ", enable=" + enable + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
