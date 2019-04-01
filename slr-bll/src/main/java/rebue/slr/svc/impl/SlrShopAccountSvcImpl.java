package rebue.slr.svc.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.slr.dao.SlrShopAccountDao;
import rebue.slr.jo.SlrShopAccountJo;
import rebue.slr.mapper.SlrShopAccountMapper;
import rebue.slr.mo.SlrShopAccountMo;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.ro.SlrShopAccountDetailRo;
import rebue.slr.ro.SlrShopAccountRo;
import rebue.slr.svc.SlrShopAccountSvc;
import rebue.slr.svc.SlrShopSvc;
import rebue.suc.mo.SucUserMo;
import rebue.suc.ro.SucUserDetailRo;
import rebue.suc.svr.feign.SucUserSvc;
import rebue.wheel.ListUtils;

/**
 * 店铺账号
 *
 * 在单独使用不带任何参数的 @Transactional 注释时，
 * propagation(传播模式)=REQUIRED，readOnly=false，
 * isolation(事务隔离级别)=READ_COMMITTED，
 * 而且事务不会针对受控异常（checked exception）回滚。
 *
 * 注意：
 * 一般是查询的数据库操作，默认设置readOnly=true, propagation=Propagation.SUPPORTS
 * 而涉及到增删改的数据库操作的方法，要设置 readOnly=false, propagation=Propagation.REQUIRED
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
@Service
public class SlrShopAccountSvcImpl extends BaseSvcImpl<java.lang.Long, SlrShopAccountJo, SlrShopAccountDao, SlrShopAccountMo, SlrShopAccountMapper> implements SlrShopAccountSvc {

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final Logger _log = LoggerFactory.getLogger(SlrShopAccountSvcImpl.class);
    
    @Resource
	private Mapper dozerMapper;

	@Resource
	private SlrShopAccountSvc thisSvc;

	@Resource
	private SlrShopSvc slrShopSvc;

	@Resource
	private SucUserSvc sucUserSvc;

    /**
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public int add(SlrShopAccountMo mo) {
        _log.info("添加店铺账号");
        // 如果id为空那么自动生成分布式id
        if (mo.getId() == null || mo.getId() == 0) {
            mo.setId(_idWorker.getId());
        }
        return super.add(mo);
    }
    
    /**
	 * 删除店铺账号
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delShopAccount(Long shopId, List<Long> moveIds) {
		_log.info("删除店铺账号id的参数为：shopId-{}, moveIds-{}", shopId, String.valueOf(moveIds));
		if (shopId == null || moveIds.size() == 0) {
			_log.error("删除店铺账号出现参数错误，请求的参数为：shopId-{}, moveIds-{}", shopId, moveIds);
			return;
		}
		_mapper.delShopAccount(shopId, ListUtils.toString(moveIds));
	}

	/**
	 * 查询指定店铺的已添加和未添加用户的列表
	 *
	 * @param shopId         店铺ID
	 * @param pageSize       每页大小
	 * @param addedKeys      模糊查询已添加用户的关键字
	 * @param addedPageNum   已添加用户是第几页
	 * @param unaddedKeys    模糊查询未添加用户的关键字
	 * @param unaddedPageNum 未添加用户是第几页
	 */
	@Override
	public SlrShopAccountRo listAddedAndUnaddedUsers(Long shopId, Integer pageSize, String addedKeys,
			Integer addedPageNum, String unaddedKeys, Integer unaddedPageNum) {
		SlrShopAccountRo ro = new SlrShopAccountRo();
		// 获取已添加用户列表
		PageInfo<SlrShopAccountDetailRo> added = thisSvc.listAddedUsers(shopId, addedKeys, addedPageNum, pageSize);
		_log.info("added: " + added);
		ro.setAddedUsers(added);
		_log.info("查询指定店铺的已添加和未添加用户的列表查询未添加用户列表的参数为：shopId-{}, unaddedKeys-{}, unaddedPageNum-{}, pageSize-{}", shopId,
				unaddedKeys, unaddedPageNum, pageSize);
		// 获取未添加用户列表
		PageInfo<SlrShopAccountDetailRo> unadded = thisSvc.listUnaddedUsers(shopId, unaddedKeys, unaddedPageNum,
				pageSize);
		_log.info("unadded: " + unadded);
		ro.setUnaddedUsers(unadded);
		return ro;
	}

	/**
	 * 获取店铺未添加的用户列表
	 *
	 * @param shopId         店铺id
	 * @param unaddedKeys    搜索关键字
	 * @param unaddedPageNum 第几页
	 * @param pageSize       每页大小
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PageInfo<SlrShopAccountDetailRo> listUnaddedUsers(Long shopId, String unaddedKeys, Integer unaddedPageNum,
			Integer pageSize) {
		_log.info("获取店铺未添加的用户列表的参数为：shopId-{}, unaddedKeys-{}, unaddedPageNum-{}, pageSize-{}", shopId, unaddedKeys,
				unaddedPageNum, pageSize);
		SlrShopAccountMo accountMo = new SlrShopAccountMo();
		accountMo.setShopId(shopId);
		_log.info("获取店铺未添加的用户列表根据店铺id查询店铺账号的参数为：{}", accountMo);
		List<SlrShopAccountMo> shopAccountList = thisSvc.list(accountMo);
		_log.info("获取店铺未添加的用户列表根据店铺id查询店铺账号的返回值为：{}", String.valueOf(shopAccountList));
		StringBuilder users = new StringBuilder();
		for (SlrShopAccountMo shpShopAccountMo : shopAccountList) {
			users.append(shpShopAccountMo.getAccountId() + ",");
		}
		String userIds = null;
		if (users.length() != 0) {
			userIds = users.substring(0, users.length() - 1);
		}
		
		SlrShopMo slrShopMo = slrShopSvc.getById(shopId);
		_log.info("获取店铺未添加的用户列表根据店铺id查询店铺信息的返回值为：{}", slrShopMo);
		
		_log.info("获取店铺未添加的用户列表查询用户信息的参数为：orgId-{}, unaddedKeys-{}, userIds-{}, unaddedPageNum-{}, pageSize-{}",
				slrShopMo.getSellerId(), unaddedKeys, userIds, unaddedPageNum, pageSize);
		PageInfo<SucUserDetailRo> listByKeysAndNotUserIds = sucUserSvc.listUnaddedUsersByOrgIdAndUsers(
				slrShopMo.getSellerId(), userIds, unaddedKeys, unaddedPageNum, pageSize);
		_log.info("获取店铺未添加的用户列表查询用户信息的返回值为：{}", listByKeysAndNotUserIds);
		PageInfo<SlrShopAccountDetailRo> pageInfo = new PageInfo<>();
		List<SlrShopAccountDetailRo> shopAccountDetailList = new ArrayList<>();
		for (SucUserDetailRo sucUserDetailRo : listByKeysAndNotUserIds.getList()) {
			SlrShopAccountDetailRo shpShopAccountDetailRo = dozerMapper.map(sucUserDetailRo,
					SlrShopAccountDetailRo.class);
			shpShopAccountDetailRo.setShopId(shopId);
			shopAccountDetailList.add(shpShopAccountDetailRo);
		}
		pageInfo = dozerMapper.map(listByKeysAndNotUserIds, PageInfo.class);
		pageInfo.setList(shopAccountDetailList);
		_log.info("获取店铺未添加的用户列表返回值为：{}", pageInfo);
		return pageInfo;
	}

	/**
	 * 获取店铺已添加的账号
	 *
	 * @param shopId       店铺id
	 * @param addedKeys    搜索关键字
	 * @param addedPageNum 第几页
	 * @param pageSize     每页大小
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PageInfo<SlrShopAccountDetailRo> listAddedUsers(Long shopId, String addedKeys, Integer addedPageNum,
			Integer pageSize) {
		_log.info("获取店铺已添加的账号的参数为：shopId-{}, addedKeys-{}, addedPageNum-{}, pageSize-{}", shopId, addedKeys,
				addedPageNum, pageSize);
		SlrShopAccountMo shopAccountMo = new SlrShopAccountMo();
		shopAccountMo.setShopId(shopId);
		_log.info("获取店铺已添加的账号根据店铺id查询店铺账号信息的参数为：{}", shopAccountMo);
		PageInfo<SlrShopAccountMo> doSelectPageInfo = PageHelper.startPage(addedPageNum, pageSize)
				.doSelectPageInfo(() -> _mapper.selectSelective(shopAccountMo));
		_log.info("获取店铺已添加的账号根据店铺id查询店铺账号信息的返回值为：{}", doSelectPageInfo);
		PageInfo<SlrShopAccountDetailRo> pageInfo = new PageInfo<>();
		List<SlrShopAccountDetailRo> shopAccountLsit = new ArrayList<>();
		for (SlrShopAccountMo shpShopAccountMo : doSelectPageInfo.getList()) {
			_log.info("获取店铺已添加的账号查询账号信息的参数为：{}", shpShopAccountMo.getAccountId());
			SucUserMo sucUserMo = sucUserSvc.getById(shpShopAccountMo.getAccountId());
			_log.info("获取店铺已添加的账号查询账号信息的返回值为：{}", sucUserMo);
			if (sucUserMo != null) {
				SlrShopAccountDetailRo shpShopAccountDetailRo = dozerMapper.map(sucUserMo,
						SlrShopAccountDetailRo.class);
				shpShopAccountDetailRo.setShopId(shopId);
				shopAccountLsit.add(shpShopAccountDetailRo);
			}
		}
		pageInfo = dozerMapper.map(doSelectPageInfo, PageInfo.class);
		pageInfo.setList(shopAccountLsit);
		_log.info("获取店铺已添加的账号返回值为：{}", pageInfo);
		return pageInfo;
	}

	/**
	 * 添加店铺账号
	 *
	 * @param shopId
	 * @param moveIds
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void addEx(Long shopId, Long sellerId, List<Long> moveIds) {
		_log.info("添加店铺账号的参数为：shopId-{}, accountIds-{}", shopId, String.valueOf(moveIds));
		if (shopId == null || moveIds.size() == 0) {
			_log.info("添加店铺账号出现参数错误，请求的参数为：shopId-{}, accountIds-{}", shopId, String.valueOf(moveIds));
			return;
		}
		for (Long accountId : moveIds) {
			SlrShopAccountMo mo = new SlrShopAccountMo();
			mo.setSellerId(sellerId);
			mo.setAccountId(accountId);
			_log.info("添加店铺账号根据卖家id/组织id和账号id查询用户店铺的参数为：{}", mo);
			List<SlrShopAccountMo> list = thisSvc.list(mo);
			_log.info("添加店铺账号根据卖家id/组织id和账号id查询用户店铺的返回值为：{}", String.valueOf(list));
			Boolean isDefault = false;
			if (list.size() == 0) {
				isDefault = true;
			} else {
				mo.setIsDefault(true);
				_log.info("添加店铺账号判断账号是否有默认店铺的参数为：{}", mo);
				SlrShopAccountMo slrShopAccountMo = thisSvc.getOne(mo);
				_log.info("添加店铺账号判断账号是否有默认店铺的返回值为：{}", slrShopAccountMo);
				if (slrShopAccountMo == null) {
					isDefault = true;
				}
			}
			mo.setIsDefault(isDefault);
			mo.setShopId(shopId);
			thisSvc.add(mo);
		}
	}
}
