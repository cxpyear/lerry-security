package com.lerry.lerrysecurity.auth.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *  组件和角色关系
 * @author LErry.li
 * Date: 2018-07-10
 * Time: 13:09
 */
@Data
public class RoleMenuDTO implements Serializable {


    private static final long serialVersionUID = -549628716880460426L;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 角色编号
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 组件ID
     */
    private Long menuId;

    /**
     * 组件编号
     */
    private String menuCode;

    /**
     * 组件名称
     */
    private String menuName;

    /**
     * 组件类型 0：目录   1：菜单   2：按钮
     */
    private String type;

    /**
     * 父级组件编号
     */
    private String parentCode;

    /**
     * 组件路径
     */
    private String url;

    /**
     * 获取按钮方法访问方式
     */
    private String method;

    /**
     * 组件状态 -1：已删除  0隐藏，1正常
     */
    private String menuStatus;
}
