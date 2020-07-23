package com.simulationFramework.SystemState.SITMFactory;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;

import com.simulationFramework.SystemState.FactoryInerfaces.ITask;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="TASKS")
@BatchSize(size=25)
@Getter @Setter @ToString
public class SITMTask implements ITask,Serializable  {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="TASKID")
	private long taskID;
	
	@Column(name="SCHEDULETYPEID")
	private long scheduleTypeID;
	
	@Column(name="LINES_LINEID")
	private long lineID;
	
	@Column(name="PLANVERSIONID")
	private long planVersionID;

	public SITMTask(long taskID, long scheduleTypeID, long lineID, long planVersionID) {
		super();
		this.taskID = taskID;
		this.scheduleTypeID = scheduleTypeID;
		this.lineID = lineID;
		this.planVersionID = planVersionID;
	}
	
	

}