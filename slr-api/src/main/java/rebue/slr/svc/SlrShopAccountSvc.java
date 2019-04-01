package rebue.slr.svc;

import java.util.List;

import com.github.pagehelper.PageInfo;

import rebue.robotech.svc.BaseSvc;
import rebue.slr.jo.SlrShopAccountJo;
import rebue.slr.mo.SlrShopAccountMo;
import rebue.slr.ro.SlrShopAccountDetailRo;
import rebue.slr.ro.SlrShopAccountRo;

/**
 * 店铺账号
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface SlrShopAccountSvc extends BaseSvc<java.lang.Long, SlrShopAccountMo, SlrShopAccountJo> {

	/**
	 * 删除店铺账号
	 */
	void delShopAccount(Long shopId, List<Long> moveIds);

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
	SlrShopAccountRo listAddedAndUnaddedUsers(Long shopId, Integer pageSize, String addedKeys, Integer addedPageNum,
			String unaddedKeys, Integer unaddedPageNum);

	/**
	 * 获取店铺未添加的用户列表
	 *
	 * @param shopId         店铺id
	 * @param unaddedKeys    搜索关键字
	 * @param unaddedPageNum 第几页
	 * @param pageSize       每页大小
	 * @return
	 */
	PageInfo<SlrShopAccountDetailRo> listUnaddedUsers(Long shopId, String unaddedKeys, Integer unaddedPageNum,
			Integer pageSize);

	/**
	 * 获取店铺已添加的账号
	 *
	 * @param shopId       店铺id
	 * @param addedKeys    搜索关键字
	 * @param addedPageNum 第几页
	 * @param pageSize     每页大小
	 * @return
	 */
	PageInfo<SlrShopAccountDetailRo> listAddedUsers(Long shopId, String addedKeys, Integer addedPageNum,
			Integer pageSize);

	/**
	 * 添加店铺账号
	 *
	 * @param shopId
	 * @param moveIds
	 */
	void addEx(Long shopId, Long sellerId, List<Long> moveIds);
}
