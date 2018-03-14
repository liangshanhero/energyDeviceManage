package cn.edu.scau.cmi.hombio.domain;

/**
 * 
 * @author _TESLA_
 *
 */
public class StrategyDetail {
	
	private Integer id;
	
	private Long min;
	
	private Long max;
	
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMin() {
		return min;
	}

	public void setMin(Long min) {
		this.min = min;
	}

	public Long getMax() {
		return max;
	}

	public void setMax(Long max) {
		this.max = max;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "StrategyDetail [id=" + id + ", min=" + min + ", max=" + max + ", type=" + type + "]";
	}
	
	

}
