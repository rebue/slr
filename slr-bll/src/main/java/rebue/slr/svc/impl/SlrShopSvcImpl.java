package rebue.slr.svc.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.rep.svr.feign.RepRevenuAnnualSvr;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.slr.dao.SlrShopDao;
import rebue.slr.jo.SlrShopJo;
import rebue.slr.mapper.SlrShopMapper;
import rebue.slr.mo.SlrSellerMo;
import rebue.slr.mo.SlrShopAccountMo;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.pub.AddShopDonePub;
import rebue.slr.pub.SlrModifyShopNameDonePub;
import rebue.slr.ro.SlrShopRo;
import rebue.slr.svc.SlrSellerSvc;
import rebue.slr.svc.SlrShopAccountSvc;
import rebue.slr.svc.SlrShopSvc;
import rebue.slr.to.AddShopTo;
import rebue.suc.ro.SucOrgRo;
import rebue.suc.svr.feign.SucOrgSvc;

/**
 * 店铺信息
 *
 * 在单独使用不带任何参数的 @Transactional 注释时， propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED， 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意： 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class SlrShopSvcImpl extends BaseSvcImpl<java.lang.Long, SlrShopJo, SlrShopDao, SlrShopMo, SlrShopMapper>
        implements SlrShopSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(SlrShopSvcImpl.class);

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(SlrShopMo mo) {
        _log.info("添加店铺信息");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }

    @Resource
    private SlrShopSvc thisSvc;

    @Resource
    private SucOrgSvc sucOrgSvc;

    @Resource
    private Mapper dozerMapper;

    @Resource
    private SlrSellerSvc slrSellerSvc;

    @Resource
    private SlrShopAccountSvc shopAccountSvc;

    @Resource
    private RepRevenuAnnualSvr repRevenuAnnualSvr;

    @Resource
    private AddShopDonePub addShopDonePub;
    
    /**
     * 添加店铺 流程： 1、根据组织id查询组织是否存在 2、添加店铺
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro addShop(AddShopTo to) {
        SlrShopMo mo = dozerMapper.map(to, SlrShopMo.class);
        // 如果id为空那么自动生成分布式id,为了下面的店铺可以获取到这个id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        _log.info("添加店铺的参数为：{}", mo);
        Ro ro = new Ro();
        if (StringUtils.isAnyBlank(mo.getShopName(), mo.getShortName()) || mo.getSellerId() == null) {
            _log.error("添加店铺时出现参数错误，请求的参数为：{}", mo);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }

        SlrSellerMo slrSellerMo = slrSellerSvc.getById(mo.getSellerId());
        _log.info("添加店铺查询卖家的返回值为：{}", slrSellerMo);
        if (slrSellerMo == null) {
            SlrSellerMo sellerMo = new SlrSellerMo();
            sellerMo.setId(mo.getSellerId());
            _log.info("添加店铺时发现卖家不存在，是由组织添加，所以添加卖家，参数为：{}", sellerMo);
            int addSellerResult = slrSellerSvc.add(sellerMo);
            _log.info("添加店铺时发现卖家不存在，是由组织添加，所以添加卖家，返回值为：{}", addSellerResult);
            if (addSellerResult != 1) {
                _log.error("添加店铺添加卖家出现错误，请求的参数为：{}", sellerMo);
                throw new RuntimeException("添加卖家失败");
            }
        }

        mo.setCreateTime(new Date());
        _log.info("添加店铺的参数为：{}", mo);
        int addResult = thisSvc.add(mo);
        _log.info("添加店铺的返回值为：{}", addResult);
        if (addResult != 1) {
            _log.error("添加店铺失败，请求的参数为：{}", mo);
            throw new RuntimeException("添加失败");
        }

        // 将当前用户添加进去当前店铺中
        SlrShopAccountMo addShopAccountMo = new SlrShopAccountMo();
        addShopAccountMo.setAccountId(to.getAccountId());
        addShopAccountMo.setSellerId(mo.getSellerId());
        addShopAccountMo.setShopId(mo.getId());
        _log.info("将当前用户添加进去当前店铺中的参数为：{}", addShopAccountMo);
        Ro addShopAccountResult = shopAccountSvc.addShopAccount(addShopAccountMo);
        if (addShopAccountResult.getResult() != ResultDic.SUCCESS) {
            _log.error("将当前用户添加进去当前店铺中失败，请求的参数为：{}", addShopAccountMo);
            throw new RuntimeException("添加失败");
        }

        _log.info("添加店铺成功，请求的参数为：{}", mo);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("添加成功");

        // 发布定时任务创建营收报表任务消息通知
        addShopDonePub.send("添加店铺完成，发布店铺完成通知消息！！！！！！！！！！！！");
        return ro;
    }

    /**
     * 设置禁用或者启用店铺
     *
     * @param id
     * @param isEnabled
     * @return
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Ro enable(Long id, Boolean isEnabled) {
        _log.info("设置禁用或者启用店铺的参数为：id-{}, isEnabled-{}", id, isEnabled);
        Ro ro = new Ro();
        if (id == null || isEnabled == null) {
            _log.error("设置禁用或者启用店铺时出现参数错误，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
            ro.setResult(ResultDic.PARAM_ERROR);
            ro.setMsg("参数错误");
            return ro;
        }
        int enableResult = _mapper.enable(id, isEnabled);
        if (enableResult != 1) {
            _log.error("设置禁用或者启用店铺时出现错误，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
            ro.setResult(ResultDic.FAIL);
            ro.setMsg("设置出错");
            return ro;
        }
        _log.info("设置禁用或者启用店铺成功，请求的参数为：id-{}, isEnabled-{}", id, isEnabled);
        ro.setResult(ResultDic.SUCCESS);
        ro.setMsg("设置成功");
        return ro;
    }

    /**
     * 查询门店信息
     *
     * @param mo
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageInfo<SlrShopRo> listShop(SlrShopMo mo, final int pageNum, final int pageSize) {
        _log.info("listShop: mo-{}; pageNum-{}; pageSize-{}", mo, pageNum, pageSize);
        PageInfo<SlrShopRo> pageInfo         = new PageInfo<>();
        List<SlrShopRo>     listEx           = new ArrayList<>();
        PageInfo<SlrShopMo> doSelectPageInfo = PageHelper.startPage(pageNum, pageSize)
                .doSelectPageInfo(() -> _mapper.selectSelective(mo));
        _log.info("doSelectPageInfo={}", doSelectPageInfo);
        for (SlrShopMo slrShopMo : doSelectPageInfo.getList()) {
            SlrShopRo slrShopRo = dozerMapper.map(slrShopMo, SlrShopRo.class);
            _log.info("查询门店信息根据组织id查询组织信息的参数为：{}", slrShopMo.getSellerId());
            SucOrgRo sucOrgRo = sucOrgSvc.getById(slrShopMo.getSellerId());
            _log.info("查询门店信息根据组织id查询组织信息的返回值为：{}", sucOrgRo);
            if (sucOrgRo.getRecord() != null) {
                slrShopRo.setOrgName(sucOrgRo.getRecord().getName());
            }
            listEx.add(slrShopRo);
        }
        pageInfo = dozerMapper.map(doSelectPageInfo, PageInfo.class);
        pageInfo.setList(listEx);
        _log.info(" 查询门店信息的返回值为：{}", pageInfo);
        return pageInfo;
    }
}
