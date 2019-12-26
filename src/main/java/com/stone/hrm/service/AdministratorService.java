package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.AdministratorDao;
import com.stone.hrm.pojo.Administrator;
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
 * administrator服务层
 * 
 * @author Administrator
 *
 */
@Service
public class AdministratorService {

	@Autowired
	private AdministratorDao administratorDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Administrator> findAll() {
		return administratorDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Administrator> findSearch(Map whereMap, int page, int size) {
		Specification<Administrator> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return administratorDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Administrator> findSearch(Map whereMap) {
		Specification<Administrator> specification = createSpecification(whereMap);
		return administratorDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Administrator findById(String id) {
		return administratorDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param administrator
	 */
	public void add(Administrator administrator) {
		administrator.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		administratorDao.save(administrator);
	}

	/**
	 * 修改
	 * @param administrator
	 */
	public void update(Administrator administrator) {
		administratorDao.save(administrator);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		administratorDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Administrator> createSpecification(Map searchMap) {

		return new Specification<Administrator>() {

			@Override
			public Predicate toPredicate(Root<Administrator> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 员工工号
                if (searchMap.get("usercode")!=null && !"".equals(searchMap.get("usercode"))) {
                	predicateList.add(cb.like(root.get("usercode").as(String.class), "%"+(String)searchMap.get("usercode")+"%"));
                }
                // 登录密码
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 状态：启动/禁用
                if (searchMap.get("state")!=null && !"".equals(searchMap.get("state"))) {
                	predicateList.add(cb.like(root.get("state").as(String.class), "%"+(String)searchMap.get("state")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
