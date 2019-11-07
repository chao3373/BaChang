//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.shenke.controller.admin;

import com.shenke.entity.Count;
import com.shenke.entity.Group;
import com.shenke.entity.JieSuan;
import com.shenke.entity.Log;
import com.shenke.entity.Month;
import com.shenke.entity.Storage;
import com.shenke.repository.SaleListProductRepository;
import com.shenke.service.ClerkService;
import com.shenke.service.GroupService;
import com.shenke.service.LogService;
import com.shenke.service.SaleListProductService;
import com.shenke.service.StorageService;
import com.shenke.util.StringUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/admin/storage/"})
public class StorageAdminController {
    @Resource
    private StorageService storageService;
    @Resource
    private SaleListProductService saleListProductService;
    @Resource
    private ClerkService clerkService;
    @Resource
    private GroupService groupService;
    @Resource
    private LogService logService;
    @Resource
    private SaleListProductRepository saleListProductRepository;

    public StorageAdminController() {
    }

    @RequestMapping({"/add"})
    public Map<String, Object> add(Storage storage, String clerkName, String groupName, Double changdu, String type) {
        System.out.println(type);
        System.out.println(storage);
        System.out.println("员工名称：" + clerkName);
        if (changdu != null) {
            this.storageService.add(storage, clerkName, groupName, changdu, type);
        } else {
            this.storageService.add(storage, clerkName, groupName);
        }

        Map<String, Object> map = new HashMap();
        Integer id = this.storageService.selectByMaxId().getId();
        map.put("success", true);
        map.put("id", id);
        return map;
    }

    @RequestMapping({"/feibiaoAdd"})
    public Map<String, Object> feibiaoAdd(Storage storage, String clerkName, String groupName) {
        this.storageService.feibiaoAdd(storage, clerkName, groupName);
        Map<String, Object> map = new HashMap();
        Integer id = this.storageService.selectByMaxId().getId();
        map.put("success", true);
        map.put("id", id);
        return map;
    }

    @RequestMapping({"/selectByMaxId"})
    public Map<String, Object> selectByMaxId() {
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        map.put("row", this.storageService.selectByMaxId());
        return map;
    }

    @RequestMapping({"/out"})
    public Map<String, Object> outStorage(String ids) {
        Map<String, Object> map = new HashMap();
        String[] idArr = ids.split(",");
        this.storageService.outStorage(idArr, new Date());
        map.put("success", true);
        return map;
    }

    @RequestMapping({"/outKu"})
    public Map<String, Object> out(String ids) throws Exception {
        String ck = this.storageService.genCode();
        Map<String, Object> map = new HashMap();
        String[] idArr = ids.split(",");
        this.storageService.updateStateById("装车", idArr, new Date(), ck);
        map.put("success", true);
        return map;
    }

    @RequestMapping({"/outSuccess"})
    public Map<String, Object> outSuccess() {
        Map<String, Object> map = new HashMap();
        map.put("rows", this.storageService.outSuccess());
        return map;
    }

    @RequestMapping({"/save"})
    public Map<String, Object> gai(String pandianji, String ids) {
        Map<String, Object> map = new HashMap();
        String[] idArr = ids.split(",");

        for (int i = 0; i < idArr.length; ++i) {
            int id = Integer.parseInt(idArr[i]);
            this.logService.save(new Log("审核操作", "准备出库"));
            this.storageService.gai(pandianji, id);
        }

        map.put("success", true);
        return map;
    }

    @RequestMapping({"/findByClientAndGroupByName"})
    public Map<String, Object> findByClientAndGroupByName(String client) {
        System.out.println(client);
        Map<String, Object> map = new HashMap();
        List<Object[]> byClientAndGroupByName = this.storageService.findByClientAndGroupByName(client);
        map.put("success", true);
        map.put("data", byClientAndGroupByName);
        return map;
    }

    @RequestMapping({"/findById"})
    public Map<String, Object> findById(Integer id) {
        Map<String, Object> map = new HashMap();
        map.put("data", this.storageService.findById(id));
        return map;
    }

    @RequestMapping({"/findbygroup"})
    public Map<String, Object> FindByGroup(String client) {
        Map<String, Object> map = new HashMap();
        List<JieSuan> findbygroup = this.storageService.FindByGroup(client);
        map.put("success", true);
        map.put("data", findbygroup);
        return map;
    }

    @RequestMapping({"/searchLiftMoney"})
    public Map<String, Object> searchLiftMoney(String saleNumber, String name, String client, String mode, String price, String realityweight, String productDate, String productDatee, String pleasant) {
        Map<String, Object> map = new HashMap();
        Map<String, Object> map1 = new HashMap();
        System.out.println(productDate);
        System.out.println(productDatee);
        map1.put("saleNumber", saleNumber);
        map1.put("pleasant", pleasant);
        map1.put("productDate", productDate);
        map1.put("productDatee", productDatee);
        map1.put("realityweight", realityweight);
        map1.put("name", name);
        map1.put("client", client);
        map1.put("mode", mode);
        map1.put("price", price);
        map.put("success", true);
        map.put("rows", this.storageService.searchLiftMoney(map1));
        return map;
    }

    @RequestMapping({"/setLocation"})
    public Map<String, Object> setLocation(String ids, Integer location) {
        Map<String, Object> map = new HashMap();
        String[] split = ids.split(",");

        for (int i = 0; i < split.length; ++i) {
            this.storageService.setLocation(Integer.parseInt(split[i]), location);
        }

        map.put("success", true);
        return map;
    }

    @RequestMapping({"/saveAdd"})
    public Map<String, Object> saveAdd(Storage storage) {
        System.out.println(storage);
        System.out.println(storage.getNum());
        this.storageService.save(storage, storage.getNum());
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        return map;
    }

    @RequestMapping({"/truck"})
    public Map<String, Object> truck(String state) {
        Map<String, Object> map = new HashMap();
        if (state == null) {
            state = "%%";
        } else {
            state = "%" + state + "%";
        }

        map.put("rows", this.storageService.findByState(state));
        return map;
    }

//    @RequestMapping({"/detail"})
//    public Map<String, Object> detail(String date, String client, String peasant, String product, String chukudanhao, String order) throws ParseException {
//        System.out.println("出库单号：" + chukudanhao);
//        System.out.println(date);
//        Map<String, Object> map = new HashMap();
//        Map<String, Object> map1 = new HashMap();
//        if (StringUtil.isNotEmpty(date)) {
//            map1.put("date", date);
//        } else {
//            map1.put("date", (Object)null);
//        }
//
//        map1.put("client", client);
//        map1.put("peasant", peasant);
//        map1.put("product", product);
//        map1.put("order", order);
//        map1.put("chukudanhao", chukudanhao);
//        System.out.println(map1.get("chukudanhao"));
//        List<Storage> storageList = this.storageService.detail(map1);
//        Iterator var10 = storageList.iterator();
//
//        while(var10.hasNext()) {
//            Storage storage = (Storage)var10.next();
//            Integer integer = this.storageService.countByDetail(storage, (String)map1.get("date"));
//            System.out.println(integer);
//            storage.setSum(integer);
//            Double danjian = (double)storage.getDabaonum() * storage.getRealityweight();
//            storage.setDanjianzhong(danjian);
//            Double zongzhong = (double)storage.getSum() * danjian;
//            System.out.println("总数量：" + integer);
//            System.out.println("打包数：" + storage.getDabaonum());
//            System.out.println("单件重量：" + danjian);
//            System.out.println("总重量：" + zongzhong);
//            storage.setZongzhong((new BigDecimal(zongzhong)).setScale(2, 0).doubleValue());
//        }
//
//        map.put("success", true);
//        map.put("rows", storageList);
//        return map;
//    }

    @RequestMapping({"/selectClientNameByOutDate"})
    public List<Storage> selectClientNameByOutDate(String outDate) throws ParseException {
        return this.storageService.selectClientNameByOutDate(new Date((new SimpleDateFormat("yyyy-MM-dd")).parse(outDate).getTime()));
    }

    @RequestMapping({"/selectOutByOutNumber"})
    public List<Storage> selectOutByOutNumber(String outNumber) {
        System.out.println(outNumber);
        return this.storageService.selectOutByOutNumber(outNumber);
    }

    @RequestMapping({"/selectCountByNameAndOutNumber"})
    public String selectCountByNameAndOutNumber(String name, String outNumber) {
        return this.storageService.selectCountByNameAndOutNumber(name, outNumber);
    }

    @RequestMapping({"/findbySalelistId"})
    public Map<String, Object> FindBySaleListId() {
        Map<String, Object> map = new HashMap();
        List<Count> findBySaleListId = this.storageService.FindBySaleListId();
        map.put("success", true);
        map.put("rows", findBySaleListId);
        System.out.println(map);
        return map;
    }

    @RequestMapping({"/findAll"})
    public Map<String, Object> findAll() {
        Map<String, Object> map = new HashMap();
        List<Storage> list = this.storageService.findAll();
        map.put("rows", list);
        return map;
    }

    @RequestMapping({"/findSaleListNumber1"})
    public Map<String, Object> findSaleListNumber() {
        Map<String, Object> map = new HashMap();
        List<Storage> list = this.storageService.findSaleListNumber();
        map.put("rows", list);
        return map;
    }

    @RequestMapping({"/findSaleListId"})
    public Map<String, Object> findStorage(String saleNumber) {
        Map<String, Object> map = new HashMap();
        List<Storage> list1 = this.storageService.findStorage(saleNumber);
        map.put("rows", list1);
        return map;
    }

    /***
     * 机台产量查询
     * @param storage
     * @param dateInProducedd
     * @return
     */
    @RequestMapping({"/JitaiProduct"})
    public Map<String, Object> JitaiProduct(Storage storage, String dateInProducedd) {
        System.out.println(storage);
        System.out.println(dateInProducedd);
        System.out.println(storage.getGroupName());
        String end;
        Date stard;
        if (StringUtil.isNotEmpty(storage.getGroupName()) && storage.getGroupName().equals("夜班")) {
            String star = dateInProducedd + " 13:25:00";
            end = dateInProducedd.split("-")[0] + "-" + dateInProducedd.split("-")[1] + "-" + (Integer.parseInt(dateInProducedd.split("-")[2]) + 1) + " 13:35:00";

            try {
                stard = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(star);
                Date endd = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(end);
                System.out.println("夜班");
                System.out.println(stard);
                System.out.println(endd);
//                map.put("rows", this.storageService.JitaiProduct(storage, (Date)null, stard, endd));
                Map<String, Object> map = storageService.JitaiProductt(storage, (Date) null, stard, endd);
                List list = (List) map.get("rows");
                Map<String, Object> mp = (Map) list.get(0);
                for (Map.Entry<String, Object> entry : mp.entrySet()) {
                    System.out.println(entry.getKey() + "--->" + entry.getValue() + "===>" + entry.getValue().getClass().toString());
                }
                return map;
            } catch (ParseException var9) {
                var9.printStackTrace();
            }
        } else {
            try {
                Date date = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(dateInProducedd + " 00:00:00");
                end = dateInProducedd + " 23:59:59";
                stard = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(end);
//                map.put("rows", this.storageService.JitaiProduct(storage, date, date, stard));
                Map<String, Object> map = storageService.JitaiProductt(storage, date, date, stard);
                List<Map<String, Object>> list = (List) map.get("rows");
                Map<String, Object> mp = (Map) list.get(0);
                for (Map.Entry<String, Object> entry : mp.entrySet()) {
                    System.out.println(entry.getKey() + "--->" + entry.getValue() + "===>" + entry.getValue().getClass().toString());
                }
                return map;
            } catch (ParseException var8) {
                var8.printStackTrace();
            }
        }

        return null;
    }

    @RequestMapping({"/kucunzonglan"})
    public Map<String, Object> KuCunZongLan(String clientname, String saleNumber, String saleDate) {
        Map<String, Object> map = new HashMap();
        Map<String, Object> map1 = new HashMap();
        map1.put("clientname", clientname);
        map1.put("saleNumber", saleNumber);
        map1.put("saleDate", saleDate);
        map.put("success", true);
        map.put("rows", this.storageService.KucunSearch(map1));
        return map;
    }

    @RequestMapping({"/select"})
    public Map<String, Object> select(Storage storage, String dateInProducedd) {
        if (storage.getGroup() != null) {
            storage.setGroupName(this.groupService.findById(storage.getGroup().getId()).getName());
        }

        Map<String, Object> map = new HashMap();
        List<Storage> list = this.storageService.select(storage, dateInProducedd);
        map.put("success", true);
        map.put("rows", list);
        return map;
    }

    @RequestMapping({"/selectEdit"})
    public Map<String, Object> selectEdit(Storage storage, String dateInProducedd, Integer page, Integer rows) {
        if (storage.getGroup() != null) {
            storage.setGroupName(this.groupService.findById(storage.getGroup().getId()).getName());
        }

//        Map<String, Object> map = new HashMap();
//        List<Storage> list = this.storageService.selectEdit(storage, dateInProducedd, page, rows);
        Map<String, Object> map = storageService.selectEditt(storage, dateInProducedd, page, rows);
//        Long total = this.storageService.getCount(storage, dateInProducedd);
//        map.put("success", true);
//        map.put("rows", list);
//        map.put("total", total);
        return map;
    }

//    @RequestMapping({"/findKuCun"})
//    public Map<String, Object> findKuCun(Storage storage, String dateInProducedd, String dateInProduceddd) {
//        System.out.println(dateInProducedd);
//        System.out.println(dateInProduceddd);
//        if (storage.getGroup() != null) {
//            storage.setGroupName(this.groupService.findById(storage.getGroup().getId()).getName());
//        }
//
//        Map<String, Object> map = new HashMap();
//        List<Storage> list = this.storageService.selectt(storage, dateInProducedd, dateInProduceddd);
//        Iterator var6 = list.iterator();
//
//        while(var6.hasNext()) {
//            Storage st = (Storage)var6.next();
//            Integer integer = this.storageService.kucunCount(st, dateInProducedd, dateInProduceddd);
//            st.setSum(integer);
//            Double danjianzhong = (double)st.getDabaonum() * st.getRealityweight();
//            st.setDanjianzhong(danjianzhong);
//            st.setZongzhong((double)st.getSum() * danjianzhong);
//        }
//
//        map.put("success", true);
//        map.put("rows", list);
//        return map;
//    }

    @RequestMapping({"/updateshijian"})
    public String updateshijian(Integer[] ids, String date) {
        try {
            Date parse = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(date);

            for (int i = 0; i < ids.length; ++i) {
                this.storageService.updateshijian(ids[i], parse);
            }
        } catch (ParseException var5) {
            var5.printStackTrace();
        }

        return "修改成功";
    }

    @RequestMapping({"/updateByIdsAndState"})
    public Map<String, Object> updateByIdAndState(String idArr, String state) {
        Map<String, Object> map = new HashMap();
        String[] split = idArr.split(",");
        this.storageService.updateByIdAndState(split, state);
        map.put("success", true);
        return map;
    }

    @RequestMapping({"/selectByState"})
    public Map<String, Object> selectByState(String state) {
        System.out.println(state);
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        map.put("rows", this.storageService.selectByState(state));
        return map;
    }

    @RequestMapping({"/selectState"})
    public Map<String, Object> selectState(String state) {
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        map.put("rows", this.storageService.selectByState(state));
        return map;
    }

    @RequestMapping({"/goBackku"})
    public Map<String, Object> goBackku(String ids) {
        Map<String, Object> map = new HashMap();
        String[] split = ids.split(",");
        this.storageService.updateByIdAndState(split, "生产完成");
        map.put("success", true);
        return map;
    }

    @RequestMapping({"/allWanCheng"})
    public Map<String, Object> allWanCheng() {
        Map<String, Object> map = new HashMap();
        List<Storage> byState = this.storageService.findByState("%生产完成%");
        map.put("success", true);
        map.put("rows", byState);
        return map;
    }

    @RequestMapping({"/updateClerk"})
    public Map<String, Object> updateClerk(Integer[] ids, String clerkName) {
        Integer clerkId = this.clerkService.finName(clerkName).getId();
        Map<String, Object> map = new HashMap();

        for (int i = 0; i < ids.length; ++i) {
            this.storageService.updateClerk(ids[i], clerkName, clerkId);
        }

        map.put("success", true);
        return map;
    }

    @RequestMapping({"/selectMonth"})
    public Map<String, Object> selectMonth(String month, String year) {
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        List<Month> list = new ArrayList();
        list.add(this.storageService.selectMonth(month, year));
        map.put("rows", list);
        return map;
    }

    @RequestMapping({"/selectYear"})
    public Map<String, Object> selectYear(String year) {
        Map<String, Object> map = new HashMap();
        map.put("success", true);
        List<Month> list = new ArrayList();
        list.add(this.storageService.selectYear(year));
        map.put("rows", list);
        return map;
    }

    @RequestMapping({"/updatebanzu"})
    public Map<String, Object> updatebanzu(Integer[] ids, String banzu) {
        Map<String, Object> map = new HashMap();
        Group group = this.groupService.findByGroupName(banzu);

        for (int i = 0; i < ids.length; ++i) {
            this.storageService.updatebanzu(ids[i], banzu, group.getId());
        }

        map.put("success", true);
        return map;
    }

    @RequestMapping({"/updatezhongliang"})
    public String updatezhongliang(Integer id, Double zhongliang) {
        this.storageService.updatezhongliang(id, zhongliang);
        return "修改成功";
    }

    @RequestMapping({"/deletekucun"})
    public String deletekucun(Integer id) {
        this.storageService.findById(id);
        return this.storageService.deletekucun(id);
    }

    @RequestMapping({"/updatechangdu"})
    public String updatechangdu(Integer changdu, Integer id) {
        this.storageService.updatechangdu(changdu, id);
        return "修改成功";
    }

    @RequestMapping({"/updatehoudu"})
    public String updatehoudu(String houdu, Integer[] idArr) {
        for (int i = 0; i < idArr.length; ++i) {
            this.storageService.updatehoudu(houdu, idArr[i]);
        }

        return "修改成功";
    }

    @RequestMapping({"/selectTihuo"})
    public List<Storage> selectTihuo(String pandianji) {
        System.out.println(pandianji);
        return this.storageService.selectTihuo(pandianji);
    }

    /***
     * 当前库存查询页面查询
     * @return
     */
    @RequestMapping("/findKuCun")
    public Map<String, Object> findKuCun(Storage storage, String dateInProducedd, String dateInProduceddd, Integer page, Integer rows) {
        System.out.println(page);
        System.out.println(rows);
        if (storage.getGroup() != null) {
            storage.setGroupName(groupService.findById(storage.getGroup().getId()).getName());
        }
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> results = storageService.selecttt(storage, dateInProducedd, dateInProduceddd, page, rows);
        return results;
    }

    /**
     * 按条件查询出库明细表
     *
     * @Description:
     * @Param:
     * @return:
     * @Author: Andy
     * @Date:
     */
    @RequestMapping("/detail")
    public Map<String, Object> detail(String date, String client, String peasant, String product, String chukudanhao, String order) throws ParseException {
        System.out.println("出库单号：" + chukudanhao);
        System.out.println(date);
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> map1 = new HashMap<>();
        if (StringUtil.isNotEmpty(date)) {
            map1.put("date", date);
        } else {
            map1.put("date", null);
        }
        map1.put("client", client);
        map1.put("peasant", peasant);
        map1.put("product", product);
        map1.put("order", order);
        map1.put("chukudanhao", chukudanhao);
        System.out.println(map1.get("chukudanhao"));
        Map<String, Object> storageList = storageService.detaill(map1);
        return storageList;
    }
}
