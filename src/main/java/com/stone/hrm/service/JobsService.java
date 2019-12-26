package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.JobsDao;
import com.stone.hrm.pojo.Jobs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * jobs服务层
 * 
 * @author Administrator
 *
 */
@Service
public class JobsService {

	@Autowired
	private JobsDao jobsDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Jobs> findAll() {
		return jobsDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Jobs> findSearch(Map whereMap, int page, int size) {
		Specification<Jobs> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return jobsDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Jobs> findSearch(Map whereMap) {
		Specification<Jobs> specification = createSpecification(whereMap);
		return jobsDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Jobs findById(String id) {
		return jobsDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param jobs
	 */
	public void add(Jobs jobs) {
		jobs.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		jobsDao.save(jobs);
	}

	/**
	 * 修改
	 * @param jobs
	 */
	public void update(Jobs jobs) {
		jobsDao.save(jobs);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		jobsDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Jobs> createSpecification(Map searchMap) {

		return new Specification<Jobs>() {

			@Override
			public Predicate toPredicate(Root<Jobs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 职位名称
                if (searchMap.get("jobName")!=null && !"".equals(searchMap.get("jobName"))) {
                	predicateList.add(cb.like(root.get("jobName").as(String.class), "%"+(String)searchMap.get("jobName")+"%"));
                }
                // 职位编码
                if (searchMap.get("jobCode")!=null && !"".equals(searchMap.get("jobCode"))) {
                	predicateList.add(cb.like(root.get("jobCode").as(String.class), "%"+(String)searchMap.get("jobCode")+"%"));
                }
                // 部门编码
                if (searchMap.get("departmentCode")!=null && !"".equals(searchMap.get("departmentCode"))) {
                	predicateList.add(cb.like(root.get("departmentCode").as(String.class), "%"+(String)searchMap.get("departmentCode")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
