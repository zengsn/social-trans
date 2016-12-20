package com.qingtian.apps.role.service;

import com.qingtian.apps.role.entity.Role;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.workSpace.utils.RandomGUID;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */
@Service("roleService")
public class RoleService {

    private Logger logger = LoggerFactory.getLogger("RoleService.class");

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 查询全部角色
     * @return
     */
    public List<Role> selectAll(Role role){
        role.setDisabled("1");
        List<Role> list = sqlSession.selectList("Role.selectAll",role);
        return list;
    }

    /**
     * 增加角色
     * @param role
     * @return
     */
    public Boolean addRole(Role role){
        role.setId(new RandomGUID().toString());
        int result = sqlSession.insert("Role.insert",role);
        return result>0? true:false;
    }

    /**
     * 校验角色代码唯一性
     * @param code
     * @return
     */
    public Boolean verifyAccount(String code){
        Role role = new Role();
        role.setCode(code);
        List<Role> list = sqlSession.selectList("Role.verifyAccount",role);
        if(list!= null && list.size()>0){
            //角色代码不唯一
            return true;
        }else {
            //角色代码唯一
            return false;
        }
    }

    /**
     * 根据id修改角色资料
     * @param role
     * @return
     */
    public Boolean updateRole(Role role){
        int result = sqlSession.update("Role.updateById",role);
        return result>0? true:false;
    }

    /**
     * 根据角色id删除角色（逻辑删除）
     * @param id
     * @return
     */
    public Boolean deleteRoleById(String id){
        Role role = new Role();
        role.setId(id);
        int result = sqlSession.update("Role.deleteRoleById",role);
        return result>0? true:false;
    }
}
