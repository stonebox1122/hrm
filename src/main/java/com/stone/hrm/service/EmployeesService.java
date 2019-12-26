package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.EmployeesDao;
import com.stone.hrm.pojo.Employees;
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
 * employees服务层
 * 
 * @author Administrator
 *
 */
@Service
public class EmployeesService {

	@Autowired
	private EmployeesDao employeesDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Employees> findAll() {
		return employeesDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Employees> findSearch(Map whereMap, int page, int size) {
		Specification<Employees> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return employeesDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Employees> findSearch(Map whereMap) {
		Specification<Employees> specification = createSpecification(whereMap);
		return employeesDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Employees findById(String id) {
		return employeesDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param employees
	 */
	public void add(Employees employees) {
		employees.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		employeesDao.save(employees);
	}

	/**
	 * 修改
	 * @param employees
	 */
	public void update(Employees employees) {
		employeesDao.save(employees);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		employeesDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Employees> createSpecification(Map searchMap) {

		return new Specification<Employees>() {

			@Override
			public Predicate toPredicate(Root<Employees> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 员工姓名
                if (searchMap.get("name")!=null && !"".equals(searchMap.get("name"))) {
                	predicateList.add(cb.like(root.get("name").as(String.class), "%"+(String)searchMap.get("name")+"%"));
                }
                // 员工工号
                if (searchMap.get("usercode")!=null && !"".equals(searchMap.get("usercode"))) {
                	predicateList.add(cb.like(root.get("usercode").as(String.class), "%"+(String)searchMap.get("usercode")+"%"));
                }
                // 登录密码
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 在职状态
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
                // 性别
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 手机
                if (searchMap.get("mobile")!=null && !"".equals(searchMap.get("mobile"))) {
                	predicateList.add(cb.like(root.get("mobile").as(String.class), "%"+(String)searchMap.get("mobile")+"%"));
                }
                // 邮箱
                if (searchMap.get("mail")!=null && !"".equals(searchMap.get("mail"))) {
                	predicateList.add(cb.like(root.get("mail").as(String.class), "%"+(String)searchMap.get("mail")+"%"));
                }
                // 婚姻状态：单身，已婚，离异，丧偶
                if (searchMap.get("marriage")!=null && !"".equals(searchMap.get("marriage"))) {
                	predicateList.add(cb.like(root.get("marriage").as(String.class), "%"+(String)searchMap.get("marriage")+"%"));
                }
                // 国籍
                if (searchMap.get("national")!=null && !"".equals(searchMap.get("national"))) {
                	predicateList.add(cb.like(root.get("national").as(String.class), "%"+(String)searchMap.get("national")+"%"));
                }
                // 地址
                if (searchMap.get("address")!=null && !"".equals(searchMap.get("address"))) {
                	predicateList.add(cb.like(root.get("address").as(String.class), "%"+(String)searchMap.get("address")+"%"));
                }
                // 学历
                if (searchMap.get("education")!=null && !"".equals(searchMap.get("education"))) {
                	predicateList.add(cb.like(root.get("education").as(String.class), "%"+(String)searchMap.get("education")+"%"));
                }
                // 学位
                if (searchMap.get("degree")!=null && !"".equals(searchMap.get("degree"))) {
                	predicateList.add(cb.like(root.get("degree").as(String.class), "%"+(String)searchMap.get("degree")+"%"));
                }
                // 毕业院校
                if (searchMap.get("graduationSchool")!=null && !"".equals(searchMap.get("graduationSchool"))) {
                	predicateList.add(cb.like(root.get("graduationSchool").as(String.class), "%"+(String)searchMap.get("graduationSchool")+"%"));
                }
                // 职位编码
                if (searchMap.get("jobCode")!=null && !"".equals(searchMap.get("jobCode"))) {
                	predicateList.add(cb.like(root.get("jobCode").as(String.class), "%"+(String)searchMap.get("jobCode")+"%"));
                }
                // 领导工号
                if (searchMap.get("managerCode")!=null && !"".equals(searchMap.get("managerCode"))) {
                	predicateList.add(cb.like(root.get("managerCode").as(String.class), "%"+(String)searchMap.get("managerCode")+"%"));
                }
                // 银行名称
                if (searchMap.get("bankName")!=null && !"".equals(searchMap.get("bankName"))) {
                	predicateList.add(cb.like(root.get("bankName").as(String.class), "%"+(String)searchMap.get("bankName")+"%"));
                }
                // 银行卡号
                if (searchMap.get("bankNumber")!=null && !"".equals(searchMap.get("bankNumber"))) {
                	predicateList.add(cb.like(root.get("bankNumber").as(String.class), "%"+(String)searchMap.get("bankNumber")+"%"));
                }
                // 政治面貌
                if (searchMap.get("politicalVisage")!=null && !"".equals(searchMap.get("politicalVisage"))) {
                	predicateList.add(cb.like(root.get("politicalVisage").as(String.class), "%"+(String)searchMap.get("politicalVisage")+"%"));
                }
                // 身份证
                if (searchMap.get("idCard")!=null && !"".equals(searchMap.get("idCard"))) {
                	predicateList.add(cb.like(root.get("idCard").as(String.class), "%"+(String)searchMap.get("idCard")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
