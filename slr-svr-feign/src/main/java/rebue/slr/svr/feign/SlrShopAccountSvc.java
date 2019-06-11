package rebue.slr.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.sbs.feign.FeignConfig;
import rebue.slr.mo.SlrShopAccountMo;

@FeignClient(name = "slr-svr", configuration = FeignConfig.class)
public interface SlrShopAccountSvc {

	/**
	 * 获取单个店铺账号
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shopaccount/getbyid")
	SlrShopAccountMo getById(@RequestParam("id") java.lang.Long id);
	
	/**
	 * 根据条件获取一条数据
	 * @param mo
	 * @return
	 */
	@GetMapping("/slr/shopaccount/getOneShopAccount")
	SlrShopAccountMo getOne( SlrShopAccountMo mo);
}
