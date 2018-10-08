package cn.txm.login.action;

import cn.txm.core.constant.Constant;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import java.util.List;

public class LoginAction extends ActionSupport {

    @Resource
    private UserService userService;

    private User user;
    private String loginResult;

    //跳转到登陆页面
    public String toLoginUI() {
        return "loginUI";
    }

    //登陆
    public String login() {
        if(user != null) {
            if(StringUtils.isNoneBlank(user.getAccount()) && StringUtils.isNoneBlank(user.getPassword())) {
                //根据用户的账号和密码查询用户列表
                List<User> list=userService.findUserByAccountAndPass(user.getAccount(),user.getPassword());
                if(list != null && list.size()>0) { //说明登陆成功
                    User user=list.get(0);
                    //2.1.1、根据用户id查询该用户的所有角色
                    user.setUserRole(userService.getUserRolesByUserId(user.getId()));
                    //2.1.2、将用户信息保存到Session中
                    ServletActionContext.getRequest().getSession().setAttribute(Constant.USER,user);
                    //2.1.3、将用户登陆记录到日志文件中
                    Log log= LogFactory.getLog(getClass());
                    log.info("用户名称为"+user.getName()+"的用户登陆了系统");
                    //2.1.4、重定向跳转到首页
                    return "home";
                } else {    //登陆失败
                    loginResult="账号或密码不正确";
                }
            } else {
                loginResult="账号或密码不能为空";
            }
        } else {
            loginResult="请输入账号和密码";
        }
        return toLoginUI();
    }

    //退出，注销
    public String logout() {
        //清除Session中保存的用户信息
        ServletActionContext.getRequest().getSession().removeAttribute(Constant.USER);
        return toLoginUI();
    }

    //跳转到没有权限提示页面
    public String toNoPermissionUI() {
        return "noPermissionUI";
    }


    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }
}