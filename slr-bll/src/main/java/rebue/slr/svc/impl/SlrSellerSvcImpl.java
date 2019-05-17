package rebue.slr.svc.impl;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import rebue.robotech.dic.ResultDic;
import rebue.robotech.ro.Ro;
import rebue.robotech.svc.impl.BaseSvcImpl;
import rebue.slr.dao.SlrSellerDao;
import rebue.slr.jo.SlrSellerJo;
import rebue.slr.mapper.SlrSellerMapper;
import rebue.slr.mo.SlrSellerMo;
import rebue.slr.mo.SlrShopMo;
import rebue.slr.ro.SlrSellerRo;
import rebue.slr.svc.SlrSellerSvc;
import rebue.slr.svc.SlrShopSvc;
import rebue.slr.to.AddSellerTo;
import rebue.slr.to.ModifySellerTo;
import rebue.suc.mo.SucOrgMo;
import rebue.suc.ro.SucOrgRo;
import rebue.suc.svr.feign.SucOrgSvc;

/**
 * 卖家
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
public class SlrSellerSvcImpl extends
		BaseSvcImpl<java.lang.Long, SlrSellerJo, SlrSellerDao, SlrSellerMo, SlrSellerMapper> implements SlrSellerSvc {

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private static final Logger _log = LoggerFactory.getLogger(SlrSellerSvcImpl.class);
	
	@Resource
	private Mapper dozerMapper;
	
	@Resource
	private SucOrgSvc sucOrgSvc;
	
	@Resource
	private SlrSellerSvc thisSvc;
	
	@Resource
	private SlrShopSvc slrShopSvc;

	/**
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public int add(SlrSellerMo mo) {
		_log.info("添加卖家");
		// 如果id为空那么自动生成分布式id
		if (mo.getId() == null || mo.getId() == 0) {
			mo.setId(_idWorker.getId());
		}
		return super.add(mo);
	}

	/**
	 * 重写查询卖家信息
	 * 
	 * @param ro
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public PageInfo<SlrSellerRo> listEx(SlrSellerRo ro, final Integer pageNum, final Integer pageSize) {
		_log.info("重写查询卖家信息的参数为：SlrSellerRo-{}, pageNum-{}, pageSize-{}", ro, pageNum, pageSize);
		PageInfo<SlrSellerRo> pageInfo = new PageInfo<>();
		ArrayList<SlrSellerRo> list = new ArrayList<>();
		PageInfo<SlrSellerMo> doSelectPageInfo = PageHelper.startPage(pageNum, pageSize)
				.doSelectPageInfo(() -> _mapper.selectAll());
		_log.info("重写查询卖家信息的返回值为：{}", doSelectPageInfo);
		for (SlrSellerMo slrSellerMo : doSelectPageInfo.getList()) {
			SucOrgRo sucOrgRo = sucOrgSvc.getById(slrSellerMo.getId());
			_log.info("重写查询卖家信息查询组织信息的返回值为：{}", sucOrgRo);
			if (sucOrgRo.getResult() == 1) {
				SlrSellerRo slrSellerRo = dozerMapper.map(sucOrgRo.getRecord(), SlrSellerRo.class);
				list.add(slrSellerRo);
			}
		}

		pageInfo = dozerMapper.map(doSelectPageInfo, PageInfo.class);
		pageInfo.setList(list);
		_log.info("重写查询卖家信息的返回值：{}", pageInfo);
		return pageInfo;
	}

	/**
	 * 重写根据id获取卖家信息
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public SlrSellerRo getByIdEx(Long id) {
		_log.info("根据id查询卖家信息的参数为：{}", id);
		SlrSellerRo ro = new SlrSellerRo();
		if (id == null) {
			return ro;
		}
		SucOrgRo sucOrgRo = sucOrgSvc.getById(id);
		_log.info("根据id查询卖家信息查询组织信息的返回值为：{}", sucOrgRo);
		if (sucOrgRo.getResult() != 1) {
			return ro;
		}
		ro = dozerMapper.map(sucOrgRo, SlrSellerRo.class);
		return ro;
	}

	/**
	 * 添加卖家
	 * 1：先根据卖家名字去组织哪里看看组织是否存在，存在的话直接拉过来
	 * @param to
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Ro addEx(AddSellerTo to) {
		_log.info("添加卖家的参数为：{}", to);
		Ro ro = new Ro();
		if (to.getName() == null || to.getShortName() == null) {
			_log.error("添加卖家时出现卖家名称为null，请求的参数为：{}", to);
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数错误");
			return ro;
		}
		
		
		// 组织id/卖家id
		Long id = _idWorker.getId();
		
		_log.info("查询组织是否存在的参数为：{}", to.getName());
		SucOrgMo sucOrgMo=sucOrgSvc.getOne(to.getName());
		_log.info("查询组织是否存在的结果为：{}", sucOrgMo);
		if(sucOrgMo !=null) {
			id=sucOrgMo.getId();
		}else {
			SucOrgMo orgMo = dozerMapper.map(to, SucOrgMo.class);
			orgMo.setId(id);
			_log.info("sucOrg不存在当前买家组织，所以添加在sucOrg中添加一个组织，参数: {}", orgMo);
			SucOrgRo sucOrgRo = sucOrgSvc.add(orgMo);
			_log.info("sucOrg不存在当前买家组织，所以添加在sucOrg中添加一个组织，结果: {}", sucOrgRo);
			if (sucOrgRo.getResult() != 1) {
				_log.error("添加sucOrg组织出现错误，请求的参数为：{}", sucOrgRo);
				throw new RuntimeException("添加组织失败");
			}
		}
		
		SlrShopMo shopMo = new SlrShopMo();
		shopMo.setShopName(to.getName());
		shopMo.setShortName(to.getShortName());
		shopMo.setContact(to.getContact());
		shopMo.setSellerId(id);
		_log.info("添加卖家添加店铺的参数为：{}", shopMo);
		Ro addShopRo = slrShopSvc.addShop(shopMo);
		_log.info("添加卖家添加店铺的返回值为：{}", addShopRo);
		if (addShopRo.getResult() != ResultDic.SUCCESS) {
			_log.info("添加卖家添加店铺出现错误，请求的参数为：{}", shopMo);
			throw new RuntimeException("添加店铺失败");
		}



		_log.info("添加卖家成功，请求的参数为：{}", to);
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("添加成功");
		return ro;
	}

	/**
	 * 修改卖家信息
	 * 
	 * @param to
	 * @return
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Ro modifyEx(ModifySellerTo to) {
		_log.info("修改卖家信息的参数为：{}", to);
		Ro ro = new Ro();
		if (to.getId() == null || to.getName() == null || to.getShortName() == null) {
			_log.error("修改卖家信息时出现参数有误，请求的参数为：{}", to);
			ro.setResult(ResultDic.PARAM_ERROR);
			ro.setMsg("参数有误");
			return ro;
		}

		SucOrgMo orgMo = dozerMapper.map(to, SucOrgMo.class);
		_log.info("修改卖家信息修改组织信息的参数为：{}", orgMo);
		SucOrgRo modifyResult = sucOrgSvc.modify(orgMo);
		_log.info("修改卖家信息修改组织信息的返回值为：{}", modifyResult);
		if (modifyResult.getResult() != 1) {
			_log.error("修改卖家信息修改组织时出现异常，请求的参数为：{}", orgMo);
			ro.setResult(ResultDic.FAIL);
			ro.setMsg("修改出错");
			return ro;
		}

		_log.info("修改卖家信息成功，请求的参数为：{}", to);
		ro.setResult(ResultDic.SUCCESS);
		ro.setMsg("修改成功");
		return ro;
	}
}
