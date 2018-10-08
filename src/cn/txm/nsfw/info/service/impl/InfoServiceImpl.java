package cn.txm.nsfw.info.service.impl;

import cn.txm.core.page.PageResult;
import cn.txm.core.service.BaseService;
import cn.txm.core.service.impl.BaseServiceImpl;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.dao.InfoDao;
import cn.txm.nsfw.info.entity.Info;
import cn.txm.nsfw.info.service.InfoService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

@Service("infoService")
public class InfoServiceImpl extends BaseServiceImpl<Info> implements InfoService {

    private InfoDao infoDao;

    @Resource
    public void setInfoDao(InfoDao infoDao) {
        super.setBaseDao(infoDao);
        this.infoDao = infoDao;
    }

    public InfoDao getInfoDao() {
        return infoDao;
    }

    /*
    @Override
    public void save(Info info) {
        infoDao.save(info);
    }
    @Override
    public void update(Info info) {
        infoDao.update(info);
    }
    @Override
    public void delete(Serializable id) {
        infoDao.delete(id);
    }
    @Override
    public Info findObjectById(Serializable id) {
        return infoDao.findObjectById(id);
    }
    @Override
    public List<Info> findObjects() {
        return infoDao.findObjects();
    }
    @Override
    public List<Info> findObjects(String hql,List<Object> parameters) {
        return infoDao.findObjects(hql,parameters);
    }
    @Override
    public List<Info> findObjects(QueryHelper queryHelper) {
        System.out.println("+++Service+++");
        return infoDao.findObjects(queryHelper);
    }
    @Override
    public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
        System.out.println("++++++++++++++Service++++++++++++++"+queryHelper+"+++++++++++++++++++++++++++"+pageNo+pageSize);
        return infoDao.getPageResult(queryHelper,pageNo,pageSize);
    }
    */
}