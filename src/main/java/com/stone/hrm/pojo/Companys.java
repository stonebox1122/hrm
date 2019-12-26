package com.stone.hrm.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * companys实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="companys")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Companys implements Serializable{

	@Id
	private String id;//ID
	private String companyName;//公司名称
	private String companyCode;//公司编码
	private String companyAddress;//公司地址
	private String businessLicenseId;//营业执照ID
	private String legalRepresentative;//法人代表
	private String companyPhone;//公司电话
	private String companyMail;//邮箱
	private java.util.Date createTime;//创建时间

}
