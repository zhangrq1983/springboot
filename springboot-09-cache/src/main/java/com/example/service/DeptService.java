package com.example.service;

import com.example.bean.Department;
import com.example.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cache.Cache;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    RedisCacheManager deptCacheManager;


    /**
     *  缓存的数据能存入redis；
     *  第二次从缓存中查询就不能反序列化回来；
     *  存的是dept的json数据;CacheManager默认使用RedisTemplate<Object, Employee>操作Redis
     *
     *
     * @param id
     * @return
     */
//    @Cacheable(cacheNames = "dept",cacheManager = "deptCacheManager")
//    public Department getDeptById(Integer id){
//        System.out.println("查询部门"+id);
//        Department department = departmentMapper.getDeptById(id);
//        return department;
//    }

    // 使用缓存管理器得到缓存，进行api调用
    public Department getDeptById(Integer id){
        Department department = null;

        //获取某个缓存
        Cache dept = deptCacheManager.getCache("dept");
        department = dept.get("1",Department.class);
        if(department == null){
            System.out.println("查询部门"+id);
            department = departmentMapper.getDeptById(id);
            dept.put("1",department);
        }
        return department;
    }


}
