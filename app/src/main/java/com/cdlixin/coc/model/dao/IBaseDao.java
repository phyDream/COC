package com.cdlixin.coc.model.dao;
import java.util.List;

/**
 * Created by moon on 2016/11/28.
 */

public interface IBaseDao<M> {
    /**
     * 插入单个数据
     * @param entity
     * @return
     */
    boolean insert(M entity);

    /**
     * 插入集合
     * @param mList
     * @return
     */
    boolean insert(List<M> mList);

    /**
     * 删除单个数据
     * @param entity
     * @return
     */
    boolean delete(M entity);

    /**
     * 删除集合
     * @param mList
     * @return
     */
    boolean delete(List<M> mList);

    /**
     * 插入或者替换单个数据
     * @param entity
     * @return
     */
    boolean insertOrReplace(M entity);

    /**
     * 插入或替换集合
     * @param mList
     * @return
     */
    boolean insertOrReplace(List<M> mList);

    /**
     * 更新单个数据
     * @param entity
     * @return
     */
    void update(M entity);

    /**
     * 根据Id查找
     * @param id
     * @return
     */
    M selectById(String id);

    /**
     * 加载所有
     * @return
     */
    List<M> loadAll();

    /**
     * 删除表内所有
     * @return
     */
    boolean clear();


    /**自定义条件查找 返回列表
     * @param where
     * @param selectionArg
     * @return
     */
    List<M> queryRaw(String where, String... selectionArg);

}
