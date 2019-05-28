package rebue.slr.ctrl;

import com.github.pagehelper.PageInfo;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.IdRo;
import rebue.robotech.ro.Ro;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.ro.SlrShopRo;
import rebue.slr.svc.SlrShopSvc;
import rebue.slr.to.AddShopTo;
import rebue.wheel.turing.JwtUtils;

/**
 * 店铺信息
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class SlrShopCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(SlrShopCtrl.class);

	@Value("${debug:false}")
	private Boolean isDebug;

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private SlrShopSvc svc;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 添加店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PostMapping("/slr/shop")
	IdRo add(@RequestBody SlrShopMo mo) throws Exception {
		_log.info("add SlrShopMo: {}", mo);
		IdRo ro = new IdRo();
		try {
			int result = svc.add(mo);
			if (result == 1) {
				String msg = "添加成功";
				_log.info("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.SUCCESS);
				ro.setId(mo.getId().toString());
				return ro;
			} else {
				String msg = "添加失败";
				_log.error("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.FAIL);
				return ro;
			}
		} catch (DuplicateKeyException e) {
			String msg = "添加失败，" + _uniqueFilesName + "已存在，不允许出现重复";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		} catch (RuntimeException e) {
			String msg = "添加失败，出现运行时异常";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 修改店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/slr/shop")
	Ro modify(@RequestBody SlrShopMo mo) throws Exception {
		_log.info("modify SlrShopMo: {}", mo);
		Ro ro = new Ro();
		try {
			if (svc.modify(mo) == 1) {
				String msg = "修改成功";
				_log.info("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.SUCCESS);
				return ro;
			} else {
				String msg = "修改失败";
				_log.error("{}: mo-{}", msg, mo);
				ro.setMsg(msg);
				ro.setResult(ResultDic.FAIL);
				return ro;
			}
		} catch (DuplicateKeyException e) {
			String msg = "修改失败，" + _uniqueFilesName + "已存在，不允许出现重复";
			_log.error(msg + ": mo=" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		} catch (RuntimeException e) {
			String msg = "修改失败，出现运行时异常";
			_log.error(msg + ": mo-" + mo, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 删除店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/slr/shop")
	Ro del(@RequestParam("id") java.lang.Long id) {
		_log.info("del SlrShopMo by id: {}", id);
		int result = svc.del(id);
		Ro ro = new Ro();
		if (result == 1) {
			String msg = "删除成功";
			_log.info("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.SUCCESS);
			return ro;
		} else {
			String msg = "删除失败，找不到该记录";
			_log.error("{}: id-{}", msg, id);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 查询店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shop")
	List<SlrShopMo> list(@RequestBody SlrShopMo mo) {
		_log.info("list查询店铺信息 :{} ",mo);
		List<SlrShopMo> result = svc.list(mo);
		_log.info("result: " + result);
		return result;
	}

	/**
	 * 获取单个店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shop/getbyid")
	SlrShopMo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get SlrShopMo by id: {}", id);
		return svc.getById(id);
	}

	/**
	 * 查询门店信息
	 *
	 * @param mo
	 * @param pageNum
	 * @param pageSize
	 * @return
	 * @throws ParseException 
	 */
	@GetMapping("/slr/shop/listshop")
	PageInfo<SlrShopRo> listShop(SlrShopMo mo, Integer pageNum, Integer pageSize,HttpServletRequest request) throws ParseException {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 5;
		_log.info("listShop ShpShopMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		if (!isDebug) {
			Long orgId = (Long) JwtUtils.getJwtAdditionItemInCookie(request, "orgId");
			mo.setSellerId(orgId);
		} else {
			mo.setSellerId(581703841586741249L);
		}
		PageInfo<SlrShopRo> listShop = svc.listShop(mo, pageNum, pageSize);
		return listShop;
	}

	/**
	 * 添加店铺
	 *
	 * @param mo
	 * @return
	 */
	@PostMapping("/slr/shop/addshop")
	Ro addShop(@RequestBody AddShopTo to) {
		_log.info("添加店铺的请求参数为：{}", to);
		try {
			return svc.addShop(to);
		} catch (Exception e) {
			_log.info("添加店铺出现异常：{}", e);
			Ro ro = new Ro();
			ro.setResult(ResultDic.FAIL);
			ro.setMsg(e.getMessage());
			return ro;
		}
	}

	/**
	 * 设置禁用或者启用店铺
	 *
	 * @param id
	 * @param isEnabled
	 * @return
	 */
	@PutMapping("/slr/shop/enable")
	Ro enable(@RequestParam("id") Long id, @RequestParam("isEnabled") Boolean isEnabled) {
		_log.info("设置禁用或者启用店铺的请求参数为：id-{}, isEnabled-{}", id, isEnabled);
		return svc.enable(id, isEnabled);
	}

	/**
	 * 根据卖家获取店铺信息
	 * 
	 * @param request
	 * @return
	 * @throws ParseException
	 */
	@GetMapping("/slr/shop/byseller")
	public List<SlrShopMo> shopList(HttpServletRequest request) throws ParseException {
		_log.info("开始根据卖家id查询店铺信息");
		SlrShopMo mo = new SlrShopMo();
		if (!isDebug) {
			Long orgId = (Long) JwtUtils.getJwtAdditionItemInCookie(request, "orgId");
			mo.setSellerId(orgId);
		} else {
			mo.setSellerId(581703841586741249L);
		}
		return svc.list(mo);
	}
}
