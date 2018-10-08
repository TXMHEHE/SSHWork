package cn.txm.nsfw.role.action;

import cn.txm.core.action.BaseAction;
import cn.txm.core.constant.Constant;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.entity.Info;
import cn.txm.nsfw.role.entity.Role;
import cn.txm.nsfw.role.entity.RolePrivilege;
import cn.txm.nsfw.role.entity.RolePrivilegeId;
import cn.txm.nsfw.role.service.RoleService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;

import java.net.URLDecoder;
import java.util.HashSet;
import java.util.List;

public class RoleAction extends BaseAction {

    private RoleService roleService;
    private List<Role> roleList;
    private Role role;
    private String[] privilegeIds;
    private String strName;

    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    //列表页面
    public String listUI() {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        QueryHelper queryHelper = new QueryHelper(Role.class, "r");
        try {
            if(role != null){
                if(StringUtils.isNotBlank(role.getName())){
                    role.setName(URLDecoder.decode(role.getName(), "utf-8"));
                    queryHelper.addCondition("r.name like ?", "%" + role.getName() + "%");
                }

            }
            pageResult = roleService.getPageResult(queryHelper, getPageNo(), getPageSize());

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "listUI";
    }

    //跳转到新增页面
    public String addUI() {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);
        return "addUI";
        //System.out.println("add");
    }

    //保存新曾
    public String add() {
        if (role != null) {
            //处理权限
            if(privilegeIds != null) {
                HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
                for(int i=0;i<privilegeIds.length;i++) {
                    set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
                }
                role.setRolePrivileges(set);
            }
            roleService.save(role);
        }
        return "list";
    }

    //跳转到编辑页面
    public String editUI() {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("privilegeMap", Constant.PRIVILEGE_MAP);

        //处理权限回显
        if(role != null && role.getRoleId() != null) {
            strName=role.getName();
            role=roleService.findObjectById(role.getRoleId());
            //处理权限回显
            if(role.getRolePrivileges() != null) {
                privilegeIds=new String[role.getRolePrivileges().size()];
                int i=0;
                for(RolePrivilege rp : role.getRolePrivileges()) {
                    privilegeIds[i]=rp.getId().getCode();
                    i++;
                }
            }
        }

        return "editUI";
    }

    //保存编辑
    public String edit() {
        if (role != null) {
            //处理权限
            if(privilegeIds != null) {
                HashSet<RolePrivilege> set=new HashSet<RolePrivilege>();
                for(int i=0;i<privilegeIds.length;i++) {
                    set.add(new RolePrivilege(new RolePrivilegeId(role,privilegeIds[i])));
                }
                role.setRolePrivileges(set);
            }
            roleService.update(role);
        }
        return "list";
    }

    //删除
    public String delete() {
        if (role != null && role.getRoleId() != null) {
            roleService.delete(role.getRoleId());
        }
        return "list";
    }

    //批量删除
    public String deleteSelected() {
        if (selectedRow != null) {
            for (String id : selectedRow) {
                roleService.delete(id);
            }
        }
        return "list";
    }


}