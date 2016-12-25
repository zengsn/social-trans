package com.qingtian.apps.system.menu.action;

import com.qingtian.apps.system.menu.entity.Menu;
import com.qingtian.apps.system.menu.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by machao on 2016/12/25.
 */
@RestController
@RequestMapping("Menu")
public class MenuAction {

    private Logger logger = LoggerFactory.getLogger("MenuAction");

    @Resource(name="menuService")
    private MenuService menuService;

    @RequestMapping("list.do")
    public String getMenu(){
        List<Menu> list = menuService.getMenu();
        return JsonUtils.genUpdateDataReturnJsonStr(true,"获取菜单列表成功",list);
    }
}
