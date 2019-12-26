package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Employees;
/**
 * employees数据访问接口
 * @author Administrator
 *
 */
public interface EmployeesDao extends JpaRepository<Employees,String>,JpaSpecificationExecutor<Employees>{
	
}
