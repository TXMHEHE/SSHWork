package cn.txm.core.dao.impl;

import cn.txm.core.dao.BaseDao;
import cn.txm.core.page.PageResult;
import cn.txm.core.util.QueryHelper;
import org.hibernate.Query;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;


public abstract class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T>{

    Class<T> clazz;

    public BaseDaoImpl() {
        //通过getGenericSuperclass()方法获取到父类泛型的类型
        ParameterizedType pt=(ParameterizedType)this.getClass().getGenericSuperclass();
        clazz=(Class<T>)pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T entity) {
        getHibernateTemplate().save(entity);
    }

    @Override
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    @Override
    public void delete(Serializable id) {
        getHibernateTemplate().delete(findObjectById(id));
    }

    @Override
    public T findObjectById(Serializable id) {
        return getHibernateTemplate().get(clazz,id);
    }

    @Override
    public List<T> findObjects() {
        Query query=getSessionFactory().getCurrentSession().createQuery("FROM"+" "+clazz.getSimpleName());
        //Query query=getSessionFactory().getCurrentSession().createQuery("FROM User");
        //From后不能缺少空格！！！
        return query.list();
        //return null;
    }

    @Override
    @Deprecated
    public List<T> findObjects(String hql,List<Object> parameters) {
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        if(parameters != null) {
            for(int i=0;i<parameters.size();i++) {
                query.setParameter(i,parameters.get(i));
            }
        }
        List<T> hehe=query.list();
        //System.out.println("++++++++++++++Dao++++++++++++++"+query+"+++++++++++++++++++++++++++");
        //System.out.println("++++++++++++++Dao++++++++++++++"+hehe+"+++++++++++++++++++++++++++");
        return hehe;
    }

    @Override
    //条件查询实体列表--查询助手queryHelper
    public List<T> findObjects(QueryHelper queryHelper) {
        Query query=getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryListHql());
        List<Object> parameters=queryHelper.getParameters();
        if(parameters != null) {
            for(int i=0;i<parameters.size();i++) {
                query.setParameter(i,parameters.get(i));
            }
        }
        System.out.println("+++"+queryHelper.getQueryListHql()+"+++");
        return query.list();
    }

    @Override
    public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize) {
        Query query=getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryListHql());
        List<Object> parameters=queryHelper.getParameters();
        if(parameters != null) {
            for(int i=0;i<parameters.size();i++) {
                query.setParameter(i,parameters.get(i));
            }
        }
        if(pageNo < 1) pageNo=1;
        query.setFirstResult((pageNo-1)*pageSize);  //设置数据起始索引号
        query.setMaxResults(pageSize);
        List items=query.list();

        //获取总记录数
        Query queryCount=getSessionFactory().getCurrentSession().createQuery(queryHelper.getQueryCountHql());
        long totalCount=(Long)queryCount.uniqueResult();

        PageResult pageResult=new PageResult(totalCount,pageNo,pageSize,items);
        return pageResult;
    }
}