package cn.txm.nsfw.user.dao.impl;

import cn.txm.core.dao.impl.BaseDaoImpl;
import cn.txm.nsfw.user.dao.UserDao;
import cn.txm.nsfw.user.entity.User;
import cn.txm.nsfw.user.entity.UserRole;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import java.io.Serializable;
import java.util.List;

public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao{
    @Override
    public List<User> findUserByAccountAndId(String id,String account) {
        System.out.println("HQL");
        String hql="FROM User WHERE account=?";
        if(StringUtils.isNoneBlank(id)) {
            hql+=" AND id!=?";
        }
        Query query=getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(0,account);
        if(StringUtils.isNoneBlank(id)) {
            query.setParameter(1,id);
        }
        System.out.println("HQL");
        return query.list();
    }

    @Override
    public void saveUserRole(UserRole userRole) {
        getHibernateTemplate().save(userRole);
    }

    @Override
    public void deleteUserRoleByUserId(Serializable id) {
        Query query=getSessionFactory().getCurrentSession().createQuery("delete from UserRole where id.userId=?");
        query.setParameter(0,id);
        query.executeUpdate();
    }

    @Override
    public List<UserRole> getUserRolesByUserId(String id) {
        Query query=getSessionFactory().getCurrentSession().createQuery("from UserRole where id.userId=?");
        query.setParameter(0,id);
        System.out.println("UsaerLIstr"+query);
        System.out.println("UsaerLIstr");

        return query.list();
    }

    @Override
    public List<User> findUserByAccountAndPass(String account, String password) {
        Query query=getSessionFactory().getCurrentSession().createQuery("FROM User WHERE account=? and password=?");
        query.setParameter(0,account);
        query.setParameter(1,password);
        return query.list();
    }
}