package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * jobs实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jobs implements Serializable{

	@Id
	private String id;//ID
	private String jobName;//职位名称
	private String jobCode;//职位编码
	private String departmentCode;//部门编码
	private java.util.Date createTime;//创建时间

}
