package rebue.slr.svc;

import com.github.pagehelper.PageInfo;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.slr.jo.SlrShopJo;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.ro.SlrShopRo;

/**
 * 店铺信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface SlrShopSvc extends BaseSvc<java.lang.Long, SlrShopMo, SlrShopJo> {

	/**
	 * 添加店铺 流程： 1、根据组织id查询组织是否存在 2、添加店铺
	 */
	Ro addShop(SlrShopMo mo);

	/**
	 * 设置禁用或者启用店铺
	 *
	 * @param id
	 * @param isEnabled
	 * @return
	 */
	Ro enable(Long id, Boolean isEnabled);

	/**
	 * 查询门店信息
	 *
	 * @param mo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<SlrShopRo> listShop(SlrShopMo mo, int pageNum, int pageSize);
}
