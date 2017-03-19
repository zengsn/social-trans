package com.qingtian.apps.system.menu.service;

import com.qingtian.apps.system.menu.entity.Menu;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by machao on 2016/12/25.
 */
@Service
public class MenuService {

    private Logger logger = LoggerFactory.getLogger("MenuServcie");

    @Autowired
    private SqlSessionTemplate sqlSession;

    /**
     * 获取菜单列表
     * @return
     */
    public List<Menu> getMenu(){
        List<Menu> list = sqlSession.selectList("Menu.select");
        return list;
    }

    public List<Menu> getMenuListByUserId(String userId){
        List<Menu> list = sqlSession.selectList("Menu.getMenuListByUserId",userId);
        return list;
    }
}
