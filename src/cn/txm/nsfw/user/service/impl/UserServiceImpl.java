package cn.txm.nsfw.user.service.impl;


import cn.txm.core.service.impl.BaseServiceImpl;
import cn.txm.core.util.ExcelUtil;
import cn.txm.core.util.ImportExcelUtil;
import cn.txm.nsfw.role.entity.Role;
import cn.txm.nsfw.user.dao.UserDao;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;
import cn.txm.nsfw.user.entity.UserRoleId;
import cn.txm.nsfw.user.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Service(value="userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

    private UserDao userDao;

    @Resource
    public void setUserDao(UserDao userDao) {
        super.setBaseDao(userDao);
        this.userDao = userDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    /*
    @Override
    public void save(User user) {
        userDao.save(user);

    }
    @Override
    public void update(User user) {
        userDao.update(user);
    }
    @Override
    public void delete(Serializable id) {
        userDao.delete(id);
        //删除用户对应的权限
        userDao.deleteUserRoleByUserId(id);
    }
    @Override
    public User findObjectById(Serializable id) {
        return userDao.findObjectById(id);
    }
    @Override
    public List<User> findObjects() {
        return userDao.findObjects();
    }
    */

    public void exportExcel(List<User> userList, ServletOutputStream outputStream) {
        ExcelUtil.exportUserExcel(userList, outputStream);
    }


    @Override
    public void importExcel(File userExcel, String userExcelFileName) {
        for(User user : ImportExcelUtil.importExcel(userExcel,userExcelFileName)) {
            save(user);
        }
    }

    public List<User> findUserByAccountAndId(String id,String account) {
        return userDao.findUserByAccountAndId(id,account);
    }

    @Override
    public void updateUserAndRole(User user, String... roleIds) {
        //1、根据用户删除该用户的所有角色
        userDao.deleteUserRoleByUserId(user.getId());
        //2、更新用户
        update(user);
        //3、保存用户对应的角色
        if(roleIds != null){
            for(String roleId: roleIds){
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
            }
        }
    }

    @Override
    public void saveUserAndRole(User user, String... roleIds) {
        //1、保存用户
        save(user);
        //2、保存用户对应的角色
        if(roleIds != null){
            for(String roleId: roleIds){
                userDao.saveUserRole(new UserRole(new UserRoleId(new Role(roleId), user.getId())));
            }
        }
    }

    @Override
    public List<UserRole> getUserRolesByUserId(String id) {
        System.out.println("Service");
        List<UserRole> hehe=userDao.getUserRolesByUserId(id);
        System.out.println(hehe);
        System.out.println("ServiceHOU");
        return hehe;
    }

    @Override
    public List<User> findUserByAccountAndPass(String account, String password) {
        return userDao.findUserByAccountAndPass(account,password);
    }
}