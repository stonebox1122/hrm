package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Departments;
/**
 * departments数据访问接口
 * @author Administrator
 *
 */
public interface DepartmentsDao extends JpaRepository<Departments,String>,JpaSpecificationExecutor<Departments>{
	
}
