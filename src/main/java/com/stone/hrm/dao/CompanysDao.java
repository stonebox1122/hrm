package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Companys;
/**
 * companys数据访问接口
 * @author Administrator
 *
 */
public interface CompanysDao extends JpaRepository<Companys,String>,JpaSpecificationExecutor<Companys>{
	
}
