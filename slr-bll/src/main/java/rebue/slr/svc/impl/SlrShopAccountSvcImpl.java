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
import com.github.pagehelper.PageInfo;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
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
public class SlrShopAccountSvcImpl
		extends BaseSvcImpl<java.lang.Long, SlrShopAccountJo, SlrShopAccountDao, SlrShopAccountMo, SlrShopAccountMapper>
		implements SlrShopAccountSvc {

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
	 * @param shopId
	 *            店铺ID
	 * @param pageSize
	 *            每页大小
	 * @param addedKeys
	 *            模糊查询已添加用户的关键字
	 * @param addedPageNum
	 *            已添加用户是第几页
	 * @param unaddedKeys
	 *            模糊查询未添加用户的关键字
	 * @param unaddedPageNum
	 *            未添加用户是第几页
	 */
	@Override
	public SlrShopAccountRo listAddedAndUnaddedUsers(Long shopId, Integer pageSize, String addedKeys,
			Integer addedPageNum, String unaddedKeys, Integer unaddedPageNum) {
		SlrShopAccountRo ro = new SlrShopAccountRo();
		// 获取已添加用户列表
		PageInfo<SlrShopAccountDetailRo> added = thisSvc.listAddedUsers(shopId, addedKeys, addedPageNum, pageSize);
		_log.info("查询已添加用户列表的结果为:{} ", added);
		ro.setAddedUsers(added);
		// 获取未添加用户列表
		PageInfo<SlrShopAccountDetailRo> unadded = thisSvc.listUnaddedUsers(shopId, unaddedKeys, unaddedPageNum,
				pageSize);
		_log.info("查询未添加用户列表的结果为:{} ", unadded);
		ro.setUnaddedUsers(unadded);
		return ro;
	}

	/**
	 * 获取店铺未添加的用户列表 1：先获取所有的已经添加的用户 2：再根据组织id和关键字和ids查询分页去suc查询用户账户信息
	 * 
	 * @param shopId
	 *            店铺id
	 * @param unaddedKeys
	 *            搜索关键字
	 * @param unaddedPageNum
	 *            第几页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PageInfo<SlrShopAccountDetailRo> listUnaddedUsers(Long shopId, String unaddedKeys, Integer unaddedPageNum,
			Integer pageSize) {
		_log.info("获取店铺未添加的用户列表的参数为：shopId-{}, unaddedKeys-{}, unaddedPageNum-{}, pageSize-{}", shopId, unaddedKeys,
				unaddedPageNum, pageSize);
		PageInfo<SlrShopAccountDetailRo> pageInfo = new PageInfo<>();

		SlrShopAccountMo shopAccountListMo = new SlrShopAccountMo();
		shopAccountListMo.setShopId(shopId);
		List<SlrShopAccountMo> shopAccountList = super.list(shopAccountListMo);
		_log.info("获取店铺已经添加的用户列表返回值为：{}", String.valueOf(shopAccountList));

		// 整理所有已经添加的用户帐号并去suc分页查询用户帐号数据
		String userIds = "";
		if (shopAccountList.size() == 0) {
			userIds = "0";
		} else {
			for (int i = 0; i < shopAccountList.size(); i++) {
				if (i != 0 && i < shopAccountList.size()) {
					userIds += ",'" + shopAccountList.get(i).getAccountId() + "'";
				} else {
					userIds += "'" + shopAccountList.get(i).getAccountId() + "'";
				}
			}
		}

		_log.info("获取店铺卖家(组织)id的参数为：shopId-{}", shopId);
		SlrShopMo slrShopMo = slrShopSvc.getById(shopId);
		_log.info("获取店铺卖家(组织)id的返回值为：{}", slrShopMo);

		_log.info("查询未添加用户信息的参数为：orgId-{}, unaddedKeys-{}, userIds-{}, unaddedPageNum-{}, pageSize-{}",
				slrShopMo.getSellerId(), unaddedKeys, userIds, unaddedPageNum, pageSize);
		PageInfo<SucUserDetailRo> listByKeysAndNotUserIds = sucUserSvc.listUnaddedUsersByOrgIdAndUsers(
				slrShopMo.getSellerId(), userIds, unaddedKeys, unaddedPageNum, pageSize);
		_log.info("查询未添加用户信息的返回值为：{}", listByKeysAndNotUserIds);
		pageInfo = dozerMapper.map(listByKeysAndNotUserIds, PageInfo.class);

		List<SlrShopAccountDetailRo> shopAccountDetailList = new ArrayList<>();
		for (SucUserDetailRo sucUserDetailRo : listByKeysAndNotUserIds.getList()) {
			SlrShopAccountDetailRo shpShopAccountDetailRo = dozerMapper.map(sucUserDetailRo,
					SlrShopAccountDetailRo.class);
			shpShopAccountDetailRo.setShopId(shopId);
			shopAccountDetailList.add(shpShopAccountDetailRo);
		}

		pageInfo.setList(shopAccountDetailList);
		_log.info("获取店铺未添加的用户列表返回值为：{}", pageInfo);
		return pageInfo;
	}

	/**
	 * 获取店铺已添加的账号 1：先查询店铺中所有的用户帐号 2：再根据用户帐号ids和关键字去suc用户分页查询用户信息
	 * 
	 * @param shopId
	 *            店铺id
	 * @param addedKeys
	 *            搜索关键字
	 * @param addedPageNum
	 *            第几页
	 * @param pageSize
	 *            每页大小
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PageInfo<SlrShopAccountDetailRo> listAddedUsers(Long shopId, String addedKeys, Integer addedPageNum,
			Integer pageSize) {
		_log.info("获取店铺已添加的账号的参数为：shopId-{}, addedKeys-{}, addedPageNum-{}, pageSize-{}", shopId, addedKeys,
				addedPageNum, pageSize);
		PageInfo<SlrShopAccountDetailRo> pageInfo = new PageInfo<>();

		// 获取所有已经添加的帐号
		SlrShopAccountMo shopAccountMo = new SlrShopAccountMo();
		shopAccountMo.setShopId(shopId);
		_log.info("获取店铺已经添加的用户帐号参数为：{}", shopAccountMo);
		List<SlrShopAccountMo> shopAccountList = super.list(shopAccountMo);
		_log.info("获取店铺已经添加的用户帐号结果为：shopAccountList-{}", shopAccountList);
		if (shopAccountList.size() == 0) {
			pageInfo.setList(new ArrayList<>());
			return pageInfo;
		}

		// 整理所有已经添加的用户帐号并去suc分页查询用户帐号数据
		String userIds = "";
		for (int i = 0; i < shopAccountList.size(); i++) {
			if (i != 0 && i < shopAccountList.size()) {
				userIds += ",'" + shopAccountList.get(i).getAccountId() + "'";
			} else {
				userIds += "'" + shopAccountList.get(i).getAccountId() + "'";
			}
		}
		_log.info("获取用户账户信息参数为：addedPageNum-{},pageSize-{},userIds-{},addedKeys-{}", addedPageNum, pageSize, userIds,
				addedKeys);
		PageInfo<SucUserMo> sucUserPage = sucUserSvc.listuserbyidsAndKeys(addedPageNum, pageSize, userIds, addedKeys);
		_log.info("获取用户账户信息结果为：sucUserPage-{}", sucUserPage);
		// 获取分页信息
		pageInfo = dozerMapper.map(sucUserPage, PageInfo.class);

		// 整理用户信息
		List<SlrShopAccountDetailRo> shopAccountLsit = new ArrayList<>();
		for (SucUserMo sucUserMo : sucUserPage.getList()) {
			SlrShopAccountDetailRo shopAccountDetailRo = dozerMapper.map(sucUserMo, SlrShopAccountDetailRo.class);
			shopAccountLsit.add(shopAccountDetailRo);
		}
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
	public Ro addEx(Long shopId, Long sellerId, List<Long> moveIds) {
		Ro ro = new Ro();
		_log.info("添加店铺账号的参数为：shopId-{}, accountIds-{}", shopId, String.valueOf(moveIds));
		if (shopId == null || moveIds.size() == 0) {
			_log.info("添加店铺账号出现参数错误，请求的参数为：shopId-{}, accountIds-{}", shopId, String.valueOf(moveIds));
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数错误");
			return ro;
		}
		for (Long accountId : moveIds) {
			SlrShopAccountMo mo = new SlrShopAccountMo();
			mo.setSellerId(sellerId);
			mo.setAccountId(accountId);
			
			// 先删除当前用户在当前卖家店铺下的所有帐号再设置一个默认的店铺以确保每个用户只有一个(默认)店铺
			_log.info("删除用户在当前卖家下所有店铺中的记录参数为：accountId-{}，sellerId-{}", accountId,sellerId);
			int i = _mapper.delShopAccountByAccountIdAndSellerId(accountId, sellerId);
			_log.info("删除用户在当前卖家下所有店铺中的记录返回值为：i-{}", i);

			mo.setIsDefault(true);
			mo.setShopId(shopId);
			thisSvc.add(mo);
		}
		_log.info("添加店铺账号成功");
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("添加成功");
		return ro;
	}

	@Override
	public Ro addShopAccount(SlrShopAccountMo mo) {
		mo.setIsDefault(true);
		_log.info("添加店铺账号参数为 mo-{}",mo);
		Ro ro = new Ro();
		// 先删除当前用户在当前卖家店铺下的所有帐号再设置一个默认的店铺以确保每个用户只有一个(默认)店铺
		_log.info("删除用户在当前卖家下所有店铺中的记录参数为：accountId-{}，sellerId-{}", mo.getAccountId(),mo.getSellerId());
		int i = _mapper.delShopAccountByAccountIdAndSellerId(mo.getAccountId(), mo.getSellerId());
		_log.info("删除用户在当前卖家下所有店铺中的记录返回值为：i-{}", i);
		
		if(thisSvc.add(mo)!=1) {
			throw new RuntimeException("添加失败");
		}
		_log.info("添加店铺账号成功为");
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("添加成功");
		return ro;
	}

	@Override
	public SlrShopAccountMo getOneShopAccount(SlrShopAccountMo mo) {
		
		return _mapper.getOneShopAccount(mo);
	}
}
