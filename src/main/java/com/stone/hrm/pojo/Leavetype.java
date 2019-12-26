package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * leavetype实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="leavetype")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Leavetype implements Serializable{

	@Id
	private String id;//ID
	private String type;//请假类型

}
