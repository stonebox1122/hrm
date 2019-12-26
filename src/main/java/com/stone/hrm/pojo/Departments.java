package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * departments实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Departments implements Serializable{

	@Id
	private String id;//ID
	private String departmentName;//部门名称
	private String departmentCode;//部门编码
	private String companyCode;//公司编码
	private String departmentPcode;//父级部门编码
	private String departmentManagerCode;//部门领导工号
	private java.util.Date createTime;//创建时间

}
