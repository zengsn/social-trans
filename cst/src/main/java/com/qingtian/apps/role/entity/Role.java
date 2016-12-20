package com.qingtian.apps.role.entity;
import java.io.Serializable;
import org.apache.ibatis.type.Alias;

@Alias("Role")
public class Role implements Serializable{
    private static final long serialVersionUID = 1L;


    /**
     *业务无关主键
     */
    private String id;

    /**
     *角色代码
     */
    private String code;

    /**
     *角色名称
     */
    private String rolename;

    /**
     *说明
     */
    private String description;

    /**
     *数据的有效性，&#39;1&#39;有效，&#39;0&#39;无效 用于逻辑删除
     */
    private String disabled;

    /**
     * 获取属性 业务无关主键
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * 设置属性 业务无关主键
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * 获取属性 角色代码
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }
    /**
     * 设置属性 角色代码
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 获取属性 角色名称
     * @param rolename
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }
    /**
     * 设置属性 角色名称
     * @return rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * 获取属性 说明
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * 设置属性 说明
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 获取属性 数据的有效性，&#39;1&#39;有效，&#39;0&#39;无效 用于逻辑删除
     * @param disabled
     */
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }
    /**
     * 设置属性 数据的有效性，&#39;1&#39;有效，&#39;0&#39;无效 用于逻辑删除
     * @return disabled
     */
    public String getDisabled() {
        return disabled;
    }

}
