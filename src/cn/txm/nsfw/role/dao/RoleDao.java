package cn.txm.nsfw.role.dao;

import cn.txm.core.dao.BaseDao;
import cn.txm.nsfw.role.entity.Role;

public interface RoleDao extends BaseDao<Role> {

    //删除该角色对应的所有权限
    public void deleteRoleRrivilegeByRoleId(String roleId);
}
