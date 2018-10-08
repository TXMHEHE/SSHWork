package cn.txm.nsfw.user.service;

import cn.txm.core.service.BaseService;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;

import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

public interface UserService extends BaseService<User> {
   /*
    //新增
    public void save(User user);
    //更新
    public void update(User user);
    //根据id删除
    public void delete(Serializable id);
    //根据id查找
    public User findObjectById(Serializable id);
    //查找列表
    public List<User> findObjects();
    */

    //导出用户列表
    public void exportExcel(List<User> userlist,ServletOutputStream outputStream);

    //导入用户列表
    public void importExcel(File userExcel, String userExcelFileName);

    //根据账号和用户名查询用户
    public List<User> findUserByAccountAndId(String id,String account);

    //更新用户及其对应的角色
    public void updateUserAndRole(User user, String... roleIds);

    //保存用户及其对应的角色
    public void saveUserAndRole(User user, String... roleIds);

    //根据用户id获取该用户对应的所有角色
    public List<UserRole> getUserRolesByUserId(String id);

    //根据账号和密码查询用户
    public List<User> findUserByAccountAndPass(String account, String password);
}
