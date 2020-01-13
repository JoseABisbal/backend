package es.thehillogy.thefarmerkitbackend.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;



@Entity
@Table(name = "Tasks")
public class Tasks implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6742820738877859151L;

	@Id
	@GeneratedValue
	@Column
	private Long id;
	
	@Column(name = "Name",nullable = false,length = 250)
	private String name;
	
	@Column(name="Description",nullable = false,length = 1000)
	@Type(type="text")
	private String description;
	
	@Column(name="UsersTasks",length = 255)
	private UsersTasks users;
		
	@Column(name="createddate")
	private Date createddate;
	
	@Column(name="StartDate")
	private Date startDate;
	
	@Column(name="EndDate")
	private Date endDate;
	
	@Column(name="TaskTypeId")
	private TaskTypeId taskTypeId;
	
	@JoinColumn(name = "TaskStatusId ")
	private TaskStatusId taskStatusId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UsersTasks getUsers() {
		return users;
	}

	public void setUsers(UsersTasks users) {
		this.users = users;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public TaskTypeId getTaskTypeId() {
		return taskTypeId;
	}

	public void setTaskTypeId(TaskTypeId taskTypeId) {
		this.taskTypeId = taskTypeId;
	}

	public TaskStatusId getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(TaskStatusId taskStatusId) {
		this.taskStatusId = taskStatusId;
	}

	

}





	