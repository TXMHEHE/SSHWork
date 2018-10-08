package cn.txm.nsfw.role.dao.impl;

import cn.txm.core.dao.impl.BaseDaoImpl;
import cn.txm.nsfw.role.dao.RoleDao;
import cn.txm.nsfw.role.entity.Role;
import org.hibernate.Query;

public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

    @Override
    public void deleteRoleRrivilegeByRoleId(String roleId) {
        Query query=getSessionFactory().getCurrentSession().createQuery("delete from RolePrivilege where id.role.roleId=?");
        query.setParameter(0,roleId);
        query.executeUpdate();
    }
}