package cn.txm.nsfw.complain.service.impl;

import cn.txm.core.service.impl.BaseServiceImpl;
import cn.txm.nsfw.complain.dao.ComplainDao;
import cn.txm.nsfw.complain.entity.ComplainEntity;
import cn.txm.nsfw.complain.service.ComplainService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("complainService")
public class ComplainServiceImpl extends BaseServiceImpl<ComplainEntity> implements ComplainService {

    private ComplainDao complainDao;

    @Resource
    public void setComplainDao(ComplainDao complainDao) {
        super.setBaseDao(complainDao);
        this.complainDao = complainDao;
    }

    public ComplainDao getComplainDao() {
        return complainDao;
    }

}