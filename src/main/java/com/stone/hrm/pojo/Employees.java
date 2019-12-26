package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * employees实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employees implements Serializable{

	@Id
	private String id;//ID
	private String name;//员工姓名
	private String usercode;//员工工号
	private String password;//登录密码
	private String state;//在职状态
	private String sex;//性别
	private String mobile;//手机
	private String mail;//邮箱
	private java.util.Date hiredate;//入职时间
	private java.util.Date birthday;//出生日期
	private String marriage;//婚姻状态：单身，已婚，离异，丧偶
	private String national;//国籍
	private String address;//地址
	private String education;//学历
	private String degree;//学位
	private String graduationSchool;//毕业院校
	private java.util.Date graduationDate;//毕业时间
	private String jobCode;//职位编码
	private String managerCode;//领导工号
	private String bankName;//银行名称
	private String bankNumber;//银行卡号
	private String politicalVisage;//政治面貌
	private String idCard;//身份证
	private java.util.Date createTime;//创建时间

}
