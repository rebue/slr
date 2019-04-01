package rebue.slr.svc;

import com.github.pagehelper.PageInfo;

import rebue.robotech.ro.Ro;
import rebue.robotech.svc.BaseSvc;
import rebue.slr.jo.SlrSellerJo;
import rebue.slr.mo.SlrSellerMo;
import rebue.slr.ro.SlrSellerRo;
import rebue.slr.to.AddSellerTo;
import rebue.slr.to.ModifySellerTo;

/**
 * 卖家
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
public interface SlrSellerSvc extends BaseSvc<java.lang.Long, SlrSellerMo, SlrSellerJo> {

	/**
	 * 重写查询卖家信息
	 * 
	 * @param ro
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<SlrSellerRo> listEx(SlrSellerRo ro, Integer pageNum, Integer pageSize);

	/**
	 * 重写根据id获取卖家信息
	 * 
	 * @param id
	 * @return
	 */
	SlrSellerRo getByIdEx(Long id);

	/**
	 * 添加卖家
	 * 
	 * @param to
	 * @return
	 */
	Ro addEx(AddSellerTo to);

	/**
	 * 修改卖家信息
	 * 
	 * @param to
	 * @return
	 */
	Ro modifyEx(ModifySellerTo to);
}
