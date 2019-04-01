package rebue.slr.svr.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.sbs.feign.FeignConfig;
import rebue.slr.mo.SlrShopMo;

@FeignClient(name = "slr-svr", configuration = FeignConfig.class)
public interface SlrShopSvc {

	/**
	 * 获取单个店铺信息
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@GetMapping("/slr/shop/getbyid")
	SlrShopMo getById(@RequestParam("id") java.lang.Long id);
}