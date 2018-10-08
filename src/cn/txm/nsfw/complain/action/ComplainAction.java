package cn.txm.nsfw.complain.action;

import cn.txm.core.action.BaseAction;
import cn.txm.core.page.PageResult;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.complain.entity.ComplainEntity;
import cn.txm.nsfw.complain.service.ComplainService;

import javax.annotation.Resource;

public class ComplainAction extends BaseAction {

    @Resource
    private ComplainService complainService;
    private ComplainEntity complainEntity;

    //列表
    public String listUI() {
        QueryHelper queryHelper=new QueryHelper(ComplainEntity.class,"c");
        PageResult pageResult=complainService.getPageResult(queryHelper,getPageNo(),getPageSize());
        return "listUI";
    }

    public ComplainService getComplainService() {
        return complainService;
    }

    public void setComplainService(ComplainService complainService) {
        this.complainService = complainService;
    }

    public ComplainEntity getComplainEntity() {
        return complainEntity;
    }

    public void setComplainEntity(ComplainEntity complainEntity) {
        this.complainEntity = complainEntity;
    }
}