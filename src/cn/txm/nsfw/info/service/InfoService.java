package cn.txm.nsfw.info.service;

import cn.txm.core.page.PageResult;
import cn.txm.core.service.BaseService;
import cn.txm.core.util.QueryHelper;
import cn.txm.nsfw.info.entity.Info;
import java.io.Serializable;
import java.util.List;

public interface InfoService extends BaseService<Info> {

    /*
        //新增
        public void save(Info info);
        //更新
        public void update(Info info);
        //根据id删除
        public void delete(Serializable id);
        //根据id查找
        public Info findObjectById(Serializable id);
        //查找列表
        public List<Info> findObjects();
        //条件查询实体列表
        public List<Info> findObjects(String hql,List<Object> parameters);
        //条件查询实体列表--查询助手queryHelper
        public List<Info> findObjects(QueryHelper queryHelper);
        //分页条件查询实体列表--查询助手queryHelper
        public PageResult getPageResult(QueryHelper queryHelper, int pageNo, int pageSize);
    */
}
