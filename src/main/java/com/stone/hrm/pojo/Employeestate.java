package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * employeestate实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="employeestate")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employeestate implements Serializable{

	@Id
	private String id;//ID
	private String state;//员工状态

}
