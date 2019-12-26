package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Employeestate;
/**
 * employeestate数据访问接口
 * @author Administrator
 *
 */
public interface EmployeestateDao extends JpaRepository<Employeestate,String>,JpaSpecificationExecutor<Employeestate>{
	
}
