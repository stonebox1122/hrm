package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.DepartmentsDao;
import com.stone.hrm.pojo.Departments;
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
 * departments服务层
 * 
 * @author Administrator
 *
 */
@Service
public class DepartmentsService {

	@Autowired
	private DepartmentsDao departmentsDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Departments> findAll() {
		return departmentsDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Departments> findSearch(Map whereMap, int page, int size) {
		Specification<Departments> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return departmentsDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Departments> findSearch(Map whereMap) {
		Specification<Departments> specification = createSpecification(whereMap);
		return departmentsDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Departments findById(String id) {
		return departmentsDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param departments
	 */
	public void add(Departments departments) {
		departments.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		departmentsDao.save(departments);
	}

	/**
	 * 修改
	 * @param departments
	 */
	public void update(Departments departments) {
		departmentsDao.save(departments);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		departmentsDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Departments> createSpecification(Map searchMap) {

		return new Specification<Departments>() {

			@Override
			public Predicate toPredicate(Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 部门名称
                if (searchMap.get("departmentName")!=null && !"".equals(searchMap.get("departmentName"))) {
                	predicateList.add(cb.like(root.get("departmentName").as(String.class), "%"+(String)searchMap.get("departmentName")+"%"));
                }
                // 部门编码
                if (searchMap.get("departmentCode")!=null && !"".equals(searchMap.get("departmentCode"))) {
                	predicateList.add(cb.like(root.get("departmentCode").as(String.class), "%"+(String)searchMap.get("departmentCode")+"%"));
                }
                // 公司编码
                if (searchMap.get("companyCode")!=null && !"".equals(searchMap.get("companyCode"))) {
                	predicateList.add(cb.like(root.get("companyCode").as(String.class), "%"+(String)searchMap.get("companyCode")+"%"));
                }
                // 父级部门编码
                if (searchMap.get("departmentPcode")!=null && !"".equals(searchMap.get("departmentPcode"))) {
                	predicateList.add(cb.like(root.get("departmentPcode").as(String.class), "%"+(String)searchMap.get("departmentPcode")+"%"));
                }
                // 部门领导工号
                if (searchMap.get("departmentManagerCode")!=null && !"".equals(searchMap.get("departmentManagerCode"))) {
                	predicateList.add(cb.like(root.get("departmentManagerCode").as(String.class), "%"+(String)searchMap.get("departmentManagerCode")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
