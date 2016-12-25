package com.qingtian.apps.system.menu.entity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("Menu")
public class Menu implements Serializable{
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    private String id;

    /**
     *菜单代码
     */
    private String menuCode;

    /**
     *菜单显示的名称
     */
    private String menuName;

    /**
     *对应的url 
     */
    private String url;

    /**
     *图标
     */
    private String iconCls;

    /**
     *父节点id
     */
    private String parentId;

    /**
     *
     */
    private String level;

    /**
     *
     */
    private String sort;

    /**
     * 获取属性 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 设置属性 
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 获取属性 菜单代码
     * @param menuCode
     */
    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }
    /**
     * 设置属性 菜单代码
     * @return menuCode
     */
    public String getMenuCode() {
        return menuCode;
    }

    /**
     * 获取属性 菜单显示的名称
     * @param menuName
     */
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    /**
     * 设置属性 菜单显示的名称
     * @return menuName
     */
    public String getMenuName() {
        return menuName;
    }

    /**
     * 获取属性 对应的url 
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * 设置属性 对应的url 
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * 获取属性 图标
     * @param iconCls
     */
    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }
    /**
     * 设置属性 图标
     * @return iconCls
     */
    public String getIconCls() {
        return iconCls;
    }

    /**
     * 获取属性 父节点id
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
    /**
     * 设置属性 父节点id
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 获取属性 
     * @param level
     */
    public void setLevel(String level) {
        this.level = level;
    }
    /**
     * 设置属性 
     * @return level
     */
    public String getLevel() {
        return level;
    }

    /**
     * 获取属性 
     * @param sort
     */
    public void setSort(String sort) {
        this.sort = sort;
    }
    /**
     * 设置属性 
     * @return sort
     */
    public String getSort() {
        return sort;
    }

}
