package com.shenke.service;

import java.awt.*;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import com.shenke.entity.Clerk;
import com.shenke.entity.Count;
import com.shenke.entity.JieSuan;
import com.shenke.entity.Storage;

/**
 * 入库单Service
 *
 * @author Administrator
 */
public interface StorageService {

    /**
     * 添加入库单
     *
     */
    public void add(Storage storage, String clerkName, String groupName);

    public List<Storage> fandAll();

    /**
     * 根据机器的序列号查询
     *
     * @return
     */
    public List<Storage> fandAllBySerialNumber(String serialNumber);

    /**
     * 根据订单销售商品id查询入库单
     *
     * @param
     */
    public Storage selectByMaxId();

    /**
     * 改为已出库
     */
    public void outStorage(int id, Date date);

    /**
     * 查询未出库的信息
     *
     * @return
     */
    public List<Storage> outSuccess();


    /**
     *
     */
    public void gai(String storage_number, int id);

    /**
     * 根据id修改状态
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public void updateStateById(String state, Integer id, Date date);

    /**
     * 根据客户查询并按照产品名称排序
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public List<Object[]> findByClientAndGroupByName(String client);

    /**
     * 查询所有已经完成的商品
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public List<Storage> findAll();

    /**
     * 根据id模糊查询
    * @Description:
    * @Param:
    * @return:
    * @Author: Andy
    * @Date:
    */
    public Storage findById(Integer id);

    /**
     *
     * 获取当天最大的出库单号
     * @return
     */
    public String getTodayMaxOutNumber();

    /**
     * 分组查询
     * @param client
     * @return
     */
    public List<JieSuan> FindByGroup(String client);

    List<Storage> searchLiftMoney(Map<String, Object> map);

    public void setLocation(Integer parseInt, Integer location);

    public void save(Storage storage);

    public void save(Storage storage, Integer num);

    public List<Storage> findByState(String state);

    public List<Storage> detail(Map<String, Object> map1);

    public void updateOutNumberById(Integer parseInt) throws Exception;

    public List<Storage> selectClientNameByOutDate(Date s);

    public List<Storage> selectOutByOutNumber(String outNumber);

    public String selectCountByNameAndOutNumber(String name, String outNumber);

    public List<Count> FindBySaleListId();

    Integer countBySaleListProductId(Integer id);
}
