package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * administrator实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="administrator")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Administrator implements Serializable{

	@Id
	private String id;//ID
	private String usercode;//员工工号
	private String password;//登录密码
	private String state;//状态：启动/禁用

}
