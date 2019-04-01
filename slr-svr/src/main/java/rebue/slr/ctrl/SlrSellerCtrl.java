package rebue.slr.ctrl;

import com.github.pagehelper.PageInfo;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.slr.ro.SlrSellerRo;
import rebue.slr.svc.SlrSellerSvc;
import rebue.slr.to.AddSellerTo;
import rebue.slr.to.ModifySellerTo;

/**
 * 卖家
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class SlrSellerCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(SlrSellerCtrl.class);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private SlrSellerSvc svc;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 添加卖家
	 * 
	 * @mbg.overrideByMethodName
	 */
	@PostMapping("/slr/seller")
	Ro add(@RequestBody AddSellerTo mo) throws Exception {
		_log.info("add SlrSellerMo: {}", mo);
		Ro ro = new Ro();
		try {
			return svc.addEx(mo);
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
	 * 修改卖家
	 *
	 * @mbg.overrideByMethodName
	 */
	@PutMapping("/slr/seller")
	Ro modify(@RequestBody ModifySellerTo to) throws Exception {
		_log.info("modify ModifySellerTo: {}", to);
		Ro ro = new Ro();
		try {
			return svc.modifyEx(to);
		} catch (DuplicateKeyException e) {
			String msg = "修改失败，" + _uniqueFilesName + "已存在，不允许出现重复";
			_log.error(msg + ": to=" + to, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		} catch (RuntimeException e) {
			String msg = "修改失败，出现运行时异常";
			_log.error(msg + ": to-" + to, e);
			ro.setMsg(msg);
			ro.setResult(ResultDic.FAIL);
			return ro;
		}
	}

	/**
	 * 删除卖家
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DeleteMapping("/slr/seller")
	Ro del(@RequestParam("id") java.lang.Long id) {
		_log.info("del SlrSellerMo by id: {}", id);
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
	 * 获取单个卖家
	 * 
	 * @mbg.overrideByMethodName
	 */
	@GetMapping("/slr/seller/getbyid")
	SlrSellerRo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get SlrSellerMo by id: {}", id);
		return svc.getByIdEx(id);
	}

	/**
	 * 查询卖家
	 * 
	 * @mbg.overrideByMethodName
	 */
	@GetMapping("/slr/seller")
	PageInfo<SlrSellerRo> list(SlrSellerRo ro, @RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 5;
		_log.info("list SlrSellerRo:" + ro + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SlrSellerRo> result = svc.listEx(ro, pageNum, pageSize);
		_log.info("result: " + result);
		return result;
	}
}
