package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.EmployeestateDao;
import com.stone.hrm.pojo.Employeestate;
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
 * employeestate服务层
 * 
 * @author Administrator
 *
 */
@Service
public class EmployeestateService {

	@Autowired
	private EmployeestateDao employeestateDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Employeestate> findAll() {
		return employeestateDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Employeestate> findSearch(Map whereMap, int page, int size) {
		Specification<Employeestate> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return employeestateDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Employeestate> findSearch(Map whereMap) {
		Specification<Employeestate> specification = createSpecification(whereMap);
		return employeestateDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Employeestate findById(String id) {
		return employeestateDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param employeestate
	 */
	public void add(Employeestate employeestate) {
		employeestate.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		employeestateDao.save(employeestate);
	}

	/**
	 * 修改
	 * @param employeestate
	 */
	public void update(Employeestate employeestate) {
		employeestateDao.save(employeestate);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		employeestateDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Employeestate> createSpecification(Map searchMap) {

		return new Specification<Employeestate>() {

			@Override
			public Predicate toPredicate(Root<Employeestate> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 员工状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
