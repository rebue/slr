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
import rebue.slr.mo.SlrShopAccountMo;
import rebue.slr.ro.SlrShopAccountDetailRo;
import rebue.slr.ro.SlrShopAccountRo;
import rebue.slr.svc.SlrShopAccountSvc;
import rebue.slr.to.SlrMoveShopAccountTo;

/**
 * 店铺账号
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@RestController
public class SlrShopAccountCtrl {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(SlrShopAccountCtrl.class);

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Resource
	private SlrShopAccountSvc svc;

	/**
	 * 有唯一约束的字段名称
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String _uniqueFilesName = "某字段内容";

	/**
	 * 修改店铺账号
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@PutMapping("/slr/shopaccount")
	Ro modify(@RequestBody SlrShopAccountMo mo) throws Exception {
		_log.info("modify SlrShopAccountMo: {}", mo);
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
	 * 查询店铺账号
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shopaccount")
	PageInfo<SlrShopAccountMo> list(SlrShopAccountMo mo,
			@RequestParam(value = "pageNum", required = false) Integer pageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		if (pageNum == null)
			pageNum = 1;
		if (pageSize == null)
			pageSize = 5;
		_log.info("list SlrShopAccountMo:" + mo + ", pageNum = " + pageNum + ", pageSize = " + pageSize);
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SlrShopAccountMo> result = svc.list(mo, pageNum, pageSize);
		_log.info("result: " + result);
		return result;
	}

	/**
	 * 获取单个店铺账号
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shopaccount/getbyid")
	SlrShopAccountMo getById(@RequestParam("id") java.lang.Long id) {
		_log.info("get SlrShopAccountMo by id: {}", id);
		return svc.getById(id);
	}

	/**
	 * 添加店铺账号
	 * 
	 * @mbg.overrideByMethodName
	 */
	@PostMapping("/slr/shopaccount")
	SlrShopAccountRo add(@RequestBody SlrMoveShopAccountTo to) throws Exception {
		_log.info("添加店铺信息的参数为：{}", to);
		svc.addEx(to.getId(), to.getSellerId(), to.getMoveIds());
		SlrShopAccountRo ro = svc.listAddedAndUnaddedUsers(to.getId(), 7, to.getAddedKeys(), to.getAddedPageNum(),
				to.getUnaddedKeys(), to.getUnaddedPageNum());
		_log.info("添加店铺查询指定店铺已添加和未添加的用户列表返回值为：{}", ro);
		return ro;
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
	@GetMapping("/slr/shopaccount/listaddedAndunaddedusers")
	SlrShopAccountRo listAddedAndUnaddedUsers(@RequestParam("id") Long id,
			@RequestParam(value = "pageSize", required = false) Integer pageSize,
			@RequestParam(value = "addedKeys", required = false) String addedKeys,
			@RequestParam(value = "addedPageNum", required = false) Integer addedPageNum,
			@RequestParam(value = "unaddedKeys", required = false) String unaddedKeys,
			@RequestParam(value = "unaddedPageNum", required = false) Integer unaddedPageNum) {
		_log.info(
				"查询指定店铺的已添加和未添加用户的列表的参数为：shopId-{}，pageSize-{}, addedKeys-{}, addedPageNum-{}, unaddedKeys-{}, unaddedPageNum-{}",
				id, pageSize, addedKeys, addedPageNum, unaddedKeys, unaddedPageNum);
		if (addedPageNum == null)
			addedPageNum = 1;
		if (unaddedPageNum == null)
			unaddedPageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		return svc.listAddedAndUnaddedUsers(id, pageSize, addedKeys, addedPageNum, unaddedKeys, unaddedPageNum);
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
	@GetMapping("/slr/shopaccount/listunaddedusers")
	SlrShopAccountRo listUnaddedUsers(@RequestParam("id") Long id,
			@RequestParam(value = "keys", required = false) String unaddedKeys,
			@RequestParam(value = "pageNum", required = false) Integer unaddedPageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		_log.info("获取店铺未添加的用户列表的参数为：shopId-{}, unaddedKeys-{}, unaddedPageNum-{}, pageSize-{}", id, unaddedKeys,
				unaddedPageNum, pageSize);
		if (unaddedPageNum == null)
			unaddedPageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SlrShopAccountDetailRo> listUnaddedUsers = svc.listUnaddedUsers(id, unaddedKeys, unaddedPageNum,
				pageSize);
		SlrShopAccountRo ro = new SlrShopAccountRo();
		ro.setUnaddedUsers(listUnaddedUsers);
		return ro;
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
	@GetMapping("/slr/shopaccount/listaddedusers")
	SlrShopAccountRo listAddedUsers(@RequestParam("id") Long id,
			@RequestParam(value = "keys", required = false) String addedKeys,
			@RequestParam(value = "pageNum", required = false) Integer addedPageNum,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		_log.info("获取店铺已添加的账号的参数为：shopId-{}, addedKeys-{}, addedPageNum-{}, pageSize-{}", id, addedKeys, addedPageNum,
				pageSize);
		if (addedPageNum == null)
			addedPageNum = 1;
		if (pageSize == null)
			pageSize = 7;
		if (pageSize > 50) {
			String msg = "pageSize不能大于50";
			_log.error(msg);
			throw new IllegalArgumentException(msg);
		}
		PageInfo<SlrShopAccountDetailRo> addedUsers = svc.listAddedUsers(id, addedKeys, addedPageNum, pageSize);
		SlrShopAccountRo ro = new SlrShopAccountRo();
		ro.setAddedUsers(addedUsers);
		return ro;
	}

	/**
	 * 删除店铺账号
	 *
	 * @param id
	 * @param accountId
	 * @return
	 */
	@DeleteMapping("/slr/shopaccount")
	SlrShopAccountRo delShopAccount(@RequestBody SlrMoveShopAccountTo to) {
		_log.info("删除店铺账号的参数为：{}", to);
		svc.delShopAccount(to.getId(), to.getMoveIds());
		return svc.listAddedAndUnaddedUsers(to.getId(), 7, to.getAddedKeys(), to.getAddedPageNum(), to.getUnaddedKeys(),
				to.getUnaddedPageNum());
	}
	
	/**
	 * 根据条件获取一条数据
	 * @param mo
	 * @return
	 */
	@GetMapping("/slr/shopaccount/getOneShopAccount")
	SlrShopAccountMo getOneShopAccount( SlrShopAccountMo mo) {
		_log.info("getOneShopAccount SlrShopAccountMo-{}", mo);
		return svc.getOneShopAccount(mo);
	}
}
