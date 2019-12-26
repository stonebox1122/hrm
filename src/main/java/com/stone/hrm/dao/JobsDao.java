package com.stone.hrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.stone.hrm.pojo.Jobs;
/**
 * jobs数据访问接口
 * @author Administrator
 *
 */
public interface JobsDao extends JpaRepository<Jobs,String>,JpaSpecificationExecutor<Jobs>{
	
}
