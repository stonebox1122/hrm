package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Leavetype;
/**
 * leavetype数据访问接口
 * @author Administrator
 *
 */
public interface LeavetypeDao extends JpaRepository<Leavetype,String>,JpaSpecificationExecutor<Leavetype>{
	
}
