package cn.txm.nsfw.user.dao;

import cn.txm.core.dao.BaseDao;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;
import java.io.Serializable;
import java.util.List;

public interface UserDao extends BaseDao<User> {
    //根据账号和用户id 查询用户
    public List<User> findUserByAccountAndId(String id, String account);

    //保存用户角色
    public void saveUserRole(UserRole userRole);

    //根据用户id删除该用户的所有用户角色
    public void deleteUserRoleByUserId(Serializable id);

    //根据用户id获取该用户对应的所有角色
    public List<UserRole> getUserRolesByUserId(String id);

    public List<User> findUserByAccountAndPass(String account, String password);
}
