package cn.txm.nsfw.role.service.impl;

import cn.txm.core.service.BaseService;
import cn.txm.core.service.impl.BaseServiceImpl;
import cn.txm.nsfw.role.dao.RoleDao;
import cn.txm.nsfw.role.entity.Role;
import cn.txm.nsfw.role.service.RoleService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service(value="roleService")
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private RoleDao roleDao;

    @Resource
    public void setRoleDao(RoleDao roleDao) {
        super.setBaseDao(roleDao);
        this.roleDao = roleDao;
    }

    public RoleDao getRoleDao() {
        return roleDao;
    }

    /*
    @Override
    public void save(Role role) {
        roleDao.save(role);
    }
    @Override
    public void update(Role role) {
        //1、删除角色对于的所有权限
        roleDao.deleteRoleRrivilegeByRoleId(role.getRoleId());
        //2、更新角色及其权限
        roleDao.update(role);
    }
    @Override
    public void delete(Serializable id) {
        roleDao.delete(id);
    }
    @Override
    public Role findObjectById(Serializable id) {
        return roleDao.findObjectById(id);
    }
    @Override
    public List<Role> findObjects() {
        return roleDao.findObjects();
    }
    */
}