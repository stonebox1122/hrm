package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.LeavetypeDao;
import com.stone.hrm.pojo.Leavetype;
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
 * leavetype服务层
 * 
 * @author Administrator
 *
 */
@Service
public class LeavetypeService {

	@Autowired
	private LeavetypeDao leavetypeDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Leavetype> findAll() {
		return leavetypeDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Leavetype> findSearch(Map whereMap, int page, int size) {
		Specification<Leavetype> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return leavetypeDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Leavetype> findSearch(Map whereMap) {
		Specification<Leavetype> specification = createSpecification(whereMap);
		return leavetypeDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Leavetype findById(String id) {
		return leavetypeDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param leavetype
	 */
	public void add(Leavetype leavetype) {
		leavetype.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		leavetypeDao.save(leavetype);
	}

	/**
	 * 修改
	 * @param leavetype
	 */
	public void update(Leavetype leavetype) {
		leavetypeDao.save(leavetype);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		leavetypeDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Leavetype> createSpecification(Map searchMap) {

		return new Specification<Leavetype>() {

			@Override
			public Predicate toPredicate(Root<Leavetype> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 请假类型
                if (searchMap.get("type")!=null && !"".equals(searchMap.get("type"))) {
                	predicateList.add(cb.like(root.get("type").as(String.class), "%"+(String)searchMap.get("type")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
