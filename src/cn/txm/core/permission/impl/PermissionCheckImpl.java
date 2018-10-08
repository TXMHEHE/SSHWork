package cn.txm.core.permission.impl;

import cn.txm.core.permission.PermissionCheck;
import cn.txm.nsfw.role.entity.Role;
import cn.txm.nsfw.role.entity.RolePrivilege;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;
import cn.txm.nsfw.user.service.UserService;
import javax.annotation.Resource;
import java.util.List;


public class PermissionCheckImpl implements PermissionCheck {

    @Resource
    UserService userService;

    @Override
    public boolean isAccessible(User user, String code) {
        //1、获取用户的所有权限
        List<UserRole> list=user.getUserRole();
        if(list == null) {
            list=userService.getUserRolesByUserId(user.getId());
        }

        //2、根据每个角色对应的所有权限进行对比
        if(list != null && list.size()>0) {
            for(UserRole ur : list) {
                Role role=ur.getId().getRole();
                for(RolePrivilege rp : role.getRolePrivileges()) {
                    //对比是否有凑得对应的权限
                    if(code.equals(rp.getId().getCode())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}