package org.preznicek.publiclibrary.database.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public class BookCondition {

	private Integer spilled;
	
	@Column(name="bent_edges")
	private Integer bentEdges;
	
	@Column(name="torn_leaves")
	private Integer tornLeaves;
	
	@Column(name="missing_leaves")
	private Integer missingLeaves;
	
	@Transient
	private Float averageCondition;
	
	
	
	
	
	public Integer getSpilled() {
		return spilled;
	}
	public void setSpilled(Integer spilled) {
		this.spilled = spilled;
	}
	public Integer getBentEdges() {
		return bentEdges;
	}
	public void setBentEdges(Integer bentEdges) {
		this.bentEdges = bentEdges;
	}
	public Integer getTornLeaves() {
		return tornLeaves;
	}
	public void setTornLeaves(Integer tornLeaves) {
		this.tornLeaves = tornLeaves;
	}
	public Integer getMissingLeaves() {
		return missingLeaves;
	}
	public void setMissingLeaves(Integer missingLeaves) {
		this.missingLeaves = missingLeaves;
	}
	public Float getAverageCondition() {
		float sum = 0;
		float count = 0;
		
		if (spilled != null) {
			sum += spilled.intValue();
			count++;
		}
		if (bentEdges != null) {
			sum += bentEdges.intValue();
			count++;
		}
		if (tornLeaves != null) {
			sum += tornLeaves.intValue();
			count++;
		}
		if (missingLeaves != null) {
			sum += missingLeaves.intValue();
			count++;
		}
		averageCondition = Float.valueOf(sum / count);
		
		return averageCondition;
	}
	public void setAverageCondition(Float averageCondition) {
		this.averageCondition = averageCondition;
	}
	
	@Override
	public String toString() {
		return "BookCondition [spilled=" + spilled + ", bentEdges=" + bentEdges
				+ ", tornLeaves=" + tornLeaves + ", missingLeaves="
				+ missingLeaves + "]";
	}
}
