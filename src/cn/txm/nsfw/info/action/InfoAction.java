package cn.txm.nsfw.info.action;

import cn.txm.core.action.BaseAction;
import cn.txm.core.page.PageResult;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.entity.Info;
import cn.txm.nsfw.info.service.InfoService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.naming.directory.SearchControls;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class InfoAction extends BaseAction {

    private InfoService infoService;
    private Info info;
    private String[] privilegeIds;
    private String strTitle;


    public String[] getPrivilegeIds() {
        return privilegeIds;
    }

    public void setPrivilegeIds(String[] privilegeIds) {
        this.privilegeIds = privilegeIds;
    }

    public InfoService getInfoService() {
        return infoService;
    }

    public void setInfoService(InfoService infoService) {
        this.infoService = infoService;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    //列表页面
    public String listUI() throws Exception {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        QueryHelper queryHelper = new QueryHelper(Info.class, "i");
        try {
            if(info != null){
                if(StringUtils.isNotBlank(info.getTitle())){
                    info.setTitle(URLDecoder.decode(info.getTitle(), "utf-8"));
                    queryHelper.addCondition("i.title like ?", "%" + info.getTitle() + "%");
                }

            }
            //根据创建时间降序排序
            queryHelper.addOrderbyProperty("i.createTime", QueryHelper.ORDER_BY_DESC);
            pageResult = infoService.getPageResult(queryHelper, getPageNo(), getPageSize());

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        //System.out.println("++++++++++++++Action++++++++++++++"+infoList+"+++++++++++++++++++++++++++");
        //System.out.println("++++++++++++++Action++++++++++++++"+queryHelper.getQueryListHql()+"+++++++++++++++++++++++++++");
        //System.out.println("++++++++++++++Action++++++++++++++"+info.getTitle()+"+++++++++++++++++++++++++++");
        return "listUI";
    }

    //跳转到新增页面
    public String addUI() {
        //加载权限集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);
        info=new Info();
        info.setCreateTime(new Timestamp(new Date().getTime()));
        return "addUI";
        //System.out.println("add");
    }

    //保存新曾
    public String add() {
        if (info != null) {
            infoService.save(info);
        }
        info=null;
        return "list";
    }

    //跳转到编辑页面
    public String editUI() {
        //加载分类集合
        ActionContext.getContext().getContextMap().put("infoTypeMap", Info.INFO_TYPE_MAP);

        //处理权限回显
        if(info != null && info.getInfoId() != null) {
            //解决查询条件覆盖的问题
            strTitle=info.getTitle();
            //System.out.println("++++++++++++Action++++++++++++++"+strTitle+"+++++++++++++++++++++++++++++");
            info=infoService.findObjectById(info.getInfoId());
        }
        return "editUI";
    }

    //保存编辑
    public String edit() {
        if (info != null) {
            infoService.update(info);
        }
        return "list";
    }

    //删除
    public String delete() {
        if (info != null && info.getInfoId() != null) {
            strTitle=info.getTitle();
            infoService.delete(info.getInfoId());
        }
        return "list";
    }

    //批量删除
    public String deleteSelected() {
        if (selectedRow != null) {
            for (String id : selectedRow) {
                strTitle=info.getTitle();
                infoService.delete(id);
            }
        }
        return "list";
    }

    //异步发布信息
    public void publicInfo() {
        try {
            if (info != null) {
                //1、更新信息状态
                Info tem = infoService.findObjectById(info.getInfoId());
                System.out.println(tem);
                tem.setState(info.getState());
                infoService.update(tem);

                //2、输出更新结果
                HttpServletResponse response = ServletActionContext.getResponse();
                response.setContentType("text/html");
                ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write("更新状态成功".getBytes("UTF-8"));
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}