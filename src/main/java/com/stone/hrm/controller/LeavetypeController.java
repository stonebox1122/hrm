package com.stone.hrm.controller;
import java.util.List;
import java.util.Map;

import com.stone.hrm.common.entity.PageResult;
import com.stone.hrm.common.entity.Result;
import com.stone.hrm.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stone.hrm.pojo.Leavetype;
import com.stone.hrm.service.LeavetypeService;

/**
 * leavetype控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/leavetype")
public class LeavetypeController {

	@Autowired
	private LeavetypeService leavetypeService;
	
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true, StatusCode.OK,"查询成功",leavetypeService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",leavetypeService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Leavetype> pageList = leavetypeService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Leavetype>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",leavetypeService.findSearch(searchMap));
    }
	
	/**
	 * 增加
	 * @param leavetype
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Leavetype leavetype  ){
		leavetypeService.add(leavetype);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param leavetype
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Leavetype leavetype, @PathVariable String id ){
		leavetype.setId(id);
		leavetypeService.update(leavetype);
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id){
		leavetypeService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}
