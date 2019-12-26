package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Administrator;
/**
 * administrator数据访问接口
 * @author Administrator
 *
 */
public interface AdministratorDao extends JpaRepository<Administrator,String>,JpaSpecificationExecutor<Administrator>{
	
}
