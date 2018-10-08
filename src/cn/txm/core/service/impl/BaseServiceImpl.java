package cn.txm.core.service.impl;

import cn.txm.core.dao.BaseDao;
import cn.txm.core.page.PageResult;
import cn.txm.core.service.BaseService;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.entity.Info;
import java.io.Serializable;
import java.util.List;

public class BaseServiceImpl<T> implements BaseService<T> {

    private BaseDao<T> baseDao;

    public BaseDao<T> getBaseDao() {
        return baseDao;
    }

    public void setBaseDao(BaseDao<T> baseDao) {
        this.baseDao = baseDao;
    }

    @Override
    public void save(T entity) {
        baseDao.save(entity);
    }

    @Override
    public void update(T entity) {
        baseDao.update(entity);
    }

    @Override
    public void delete(Serializable id) {
        baseDao.delete(id);
    }

    @Override
    public T findObjectById(Serializable id) {
        return baseDao.findObjectById(id);
    }

    @Override
    public List<T> findObjects() {
        return baseDao.findObjects();
    }

    @Override
    public List<T> findObjects(String hql, List<Object> parameters) {
        List<T> hehe=baseDao.findObjects(hql,parameters);
        System.out.println("++++++++++++++Service++++++++++++++"+baseDao+"+++++++++++++++++++++++++++");
        System.out.println("++++++++++++++Service++++++++++++++"+hehe+"+++++++++++++++++++++++++++");
        return hehe;
    }

    @Override
    public List<T> findObjects(QueryHelper queryHelper) {
        return baseDao.findObjects(queryHelper);
    }

    @Override
    public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
        return baseDao.getPageResult(queryHelper,pageNo,pageSize);
    }
}