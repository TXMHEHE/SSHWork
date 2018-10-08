package cn.txm.nsfw.user.action;

import cn.txm.core.action.BaseAction;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.entity.Info;
import cn.txm.nsfw.role.service.RoleService;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;
import cn.txm.nsfw.user.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;
import java.util.UUID;


public class UserAction extends BaseAction {

    private UserService userService;
    //private List<User> userList;
    private User user;
    private File headImg;
    private String headImgContentType;
    private String headImgFileName;
    private File userExcel;
    private String userExcelContentType;
    private String userExcelFileName;
    private RoleService roleService;
    private String[] userRoleIds;
    private String strName;


    public UserService getUserService() {
        return userService;
    }

    //@Resource
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }

    //@Resource
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    //列表页面
    public String listUI() {
        QueryHelper queryHelper = new QueryHelper(User.class, "u");
        try {
            if(user != null){
                if(StringUtils.isNotBlank(user.getName())){
                    user.setName(URLDecoder.decode(user.getName(), "utf-8"));
                    queryHelper.addCondition("u.name like ?", "%" + user.getName() + "%");
                }

            }
            pageResult = userService.getPageResult(queryHelper, getPageNo(), getPageSize());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "listUI";
    }

    //跳转到新增页面
    public String addUI() {
        //加载角色列表
        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
        return "addUI";
    }

    //保存新曾
    public String add() {
        if (user != null) {
            //处理头像
            if(headImg != null) {
                try {
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String fileName = UUID.randomUUID().toString() + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //赋值文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/"+fileName);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("---"+user+"---"+userRoleIds+"---");
            System.out.println("----- Action ----");
            userService.saveUserAndRole(user,userRoleIds);
        }
        return "list";
    }

    //跳转到编辑页面
    public String editUI() {
        System.out.println("hehe1");
        //加载角色列表
        ActionContext.getContext().getContextMap().put("roleList",roleService.findObjects());
        //ActionContext.getContext().getContextMap().put("sex",user.isGender());
        System.out.println("hehe2");
        if (user != null && user.getId() != null) {
            strName=user.getName();
            user = userService.findObjectById(user.getId());
            System.out.println("hehe3");
            System.out.println(user);
            //处理角色回显
            List<UserRole> list=userService.getUserRolesByUserId(user.getId());
            System.out.println(list);
            System.out.println("hehe4");
            if(list != null && list.size()>0) {
                userRoleIds=new String[list.size()];
                for(int i=0;i<list.size();i++) {
                    userRoleIds[i]=list.get(i).getId().getRole().getRoleId();
                    System.out.println("hehe5");
                }
            }
        }
        System.out.println("hehe6");
        return "editUI";
    }

    //保存编辑
     public String edit() {
        if (user != null) {
            //处理头像
            if(headImg != null) {
                try {
                    //1、保存头像到upload/user
                    //获取保存路径的绝对地址
                    String filePath = ServletActionContext.getServletContext().getRealPath("upload/user");
                    String fileName = UUID.randomUUID().toString() + headImgFileName.substring(headImgFileName.lastIndexOf("."));
                    //赋值文件
                    FileUtils.copyFile(headImg, new File(filePath, fileName));

                    //2、设置用户头像路径
                    user.setHeadImg("user/"+fileName);
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
            userService.updateUserAndRole(user,userRoleIds);
        }
        return "list";
    }

    //删除
    public String delete() {
        if (user != null && user.getId() != null) {
            userService.delete(user.getId());
        }
        return "list";
    }

    //批量删除
    public String deleteSelected() {
        if (selectedRow != null) {
            for (String id : selectedRow) {
                userService.delete(id);
            }
        }
        return "list";
    }

    //导出用户列表
    public void exportExcel() {
        try{
            System.out.println("action");
            //1、查找用户列表
            //userList=userService.findObjects();
            //2、导出
            HttpServletResponse response=ServletActionContext.getResponse();
            response.setContentType("application/x-excel");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String("userList.xls".getBytes(), "UTF-8"));
            ServletOutputStream outputStream=response.getOutputStream();
            userService.exportExcel(userService.findObjects(),outputStream);
            if(outputStream != null) {
                outputStream.close();
            }
            System.out.println("action");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导入用户列表
    public String importExcel() {
        //1、获取excelwenjian
        if(userExcel != null) {
            //是否是excel
            if(userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
                //2、导入
                System.out.println("");
                userService.importExcel(userExcel,userExcelFileName);
            }
        }
        return "list";
    }

    //校验用户账号唯一
    public void verifyAccount() {
        //1、获取账号Action
        //StringUtils.isNotBlank(user.getAccount())
        if(user != null && StringUtils.isNotBlank(user.getAccount())) {
            //2、根据账号到数据库中校验是否存在该账号对应的用户
            List<User> list=userService.findUserByAccountAndId(user.getId(),user.getAccount());
            String strResult="true";
            if(list != null && list.size()>0) {
                //说明该账号已经存在
                strResult="false";
            }
            //输出
            try {
                HttpServletResponse response=ServletActionContext.getResponse();
                response.setContentType("html/text");
                ServletOutputStream outputStream=response.getOutputStream();
                outputStream.write(strResult.getBytes());
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getHeadImg() {
        return headImg;
    }

    public void setHeadImg(File headImg) {
        this.headImg = headImg;
    }

    public String getHeadImgContentType() {
        return headImgContentType;
    }

    public void setHeadImgContentType(String headImgContentType) {
        this.headImgContentType = headImgContentType;
    }

    public String getHeadImgFileName() {
        return headImgFileName;
    }

    public void setHeadImgFileName(String headImgFileName) {
        this.headImgFileName = headImgFileName;
    }

    public File getUserExcel() {
        return userExcel;
    }

    public void setUserExcel(File userExcel) {
        this.userExcel = userExcel;
    }

    public String getUserExcelContentType() {
        return userExcelContentType;
    }

    public void setUserExcelContentType(String userExcelContentType) {
        this.userExcelContentType = userExcelContentType;
    }

    public String getUserExcelFileName() {
        return userExcelFileName;
    }

    public void setUserExcelFileName(String userExcelFileName) {
        this.userExcelFileName = userExcelFileName;
    }

    public String[] getUserRoleIds() {
        return userRoleIds;
    }

    public void setUserRoleIds(String[] userRoleIds) {
        this.userRoleIds = userRoleIds;
    }

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }
}
