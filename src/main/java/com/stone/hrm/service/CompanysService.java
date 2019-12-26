package com.stone.hrm.service;

import com.stone.hrm.common.util.IdWorker;
import com.stone.hrm.dao.CompanysDao;
import com.stone.hrm.pojo.Companys;
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
 * companys服务层
 * 
 * @author Administrator
 *
 */
@Service
public class CompanysService {

	@Autowired
	private CompanysDao companysDao;
	
	@Autowired
	private IdWorker idWorker;

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Companys> findAll() {
		return companysDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Companys> findSearch(Map whereMap, int page, int size) {
		Specification<Companys> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return companysDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Companys> findSearch(Map whereMap) {
		Specification<Companys> specification = createSpecification(whereMap);
		return companysDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Companys findById(String id) {
		return companysDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param companys
	 */
	public void add(Companys companys) {
		companys.setId( idWorker.nextId()+"" ); //雪花分布式ID生成器
		companysDao.save(companys);
	}

	/**
	 * 修改
	 * @param companys
	 */
	public void update(Companys companys) {
		companysDao.save(companys);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		companysDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Companys> createSpecification(Map searchMap) {

		return new Specification<Companys>() {

			@Override
			public Predicate toPredicate(Root<Companys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 公司名称
                if (searchMap.get("companyName")!=null && !"".equals(searchMap.get("companyName"))) {
                	predicateList.add(cb.like(root.get("companyName").as(String.class), "%"+(String)searchMap.get("companyName")+"%"));
                }
                // 公司编码
                if (searchMap.get("companyCode")!=null && !"".equals(searchMap.get("companyCode"))) {
                	predicateList.add(cb.like(root.get("companyCode").as(String.class), "%"+(String)searchMap.get("companyCode")+"%"));
                }
                // 公司地址
                if (searchMap.get("companyAddress")!=null && !"".equals(searchMap.get("companyAddress"))) {
                	predicateList.add(cb.like(root.get("companyAddress").as(String.class), "%"+(String)searchMap.get("companyAddress")+"%"));
                }
                // 营业执照ID
                if (searchMap.get("businessLicenseId")!=null && !"".equals(searchMap.get("businessLicenseId"))) {
                	predicateList.add(cb.like(root.get("businessLicenseId").as(String.class), "%"+(String)searchMap.get("businessLicenseId")+"%"));
                }
                // 法人代表
                if (searchMap.get("legalRepresentative")!=null && !"".equals(searchMap.get("legalRepresentative"))) {
                	predicateList.add(cb.like(root.get("legalRepresentative").as(String.class), "%"+(String)searchMap.get("legalRepresentative")+"%"));
                }
                // 公司电话
                if (searchMap.get("companyPhone")!=null && !"".equals(searchMap.get("companyPhone"))) {
                	predicateList.add(cb.like(root.get("companyPhone").as(String.class), "%"+(String)searchMap.get("companyPhone")+"%"));
                }
                // 邮箱
                if (searchMap.get("companyMail")!=null && !"".equals(searchMap.get("companyMail"))) {
                	predicateList.add(cb.like(root.get("companyMail").as(String.class), "%"+(String)searchMap.get("companyMail")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}
