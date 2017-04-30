package com.qingtian.apps.system.menu.action;

import com.qingtian.apps.system.menu.entity.Menu;
import com.qingtian.apps.system.menu.service.MenuService;
import com.qingtian.utils.StringUtils;
import com.qingtian.utils.ToolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.workSpace.utils.JsonUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by machao on 2016/12/25.
 */
@RestController
@RequestMapping("Menu")
public class MenuAction {

    private Logger logger = LoggerFactory.getLogger("MenuAction");

    @Resource(name = "menuService")
    private MenuService menuService;

    @RequestMapping("list.do")
    public String getMenu() {
        List<Menu> list = menuService.getMenu();
        return JsonUtils.genUpdateDataReturnJsonStr(true, "获取菜单列表成功", list);
    }

    /**
     * 返回经过处理的菜单
     */
    @RequestMapping("getMenuList.do")
    public String getMenuList() {
        List<Menu> list = menuService.getMenu();
        List<Map<String, String>> reList = ToolUtils.tranJsonData(list);
        return JsonUtils.genUpdateDataReturnJsonStr(true, "获取菜单列表成功", reList);
    }


    @RequestMapping("getMenuListByUserId.do")
    public String getMenuListByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return JsonUtils.genUpdateDataReturnJsonStr(true, "userId为空");
        }
        List<Menu> list = menuService.getMenuListByUserId(userId);
        try {
            return JsonUtils.genUpdateDataReturnJsonStr(true, "获取菜单成功",list);
        } catch (Exception e) {
            e.printStackTrace();
            return JsonUtils.genUpdateDataReturnJsonStr(false, "操作由于以异常而失败" + e.getMessage());
        }
    }
}
