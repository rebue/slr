package rebue.slr.svr.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import rebue.sbs.feign.FeignConfig;
import rebue.slr.mo.SlrShopMo;

@FeignClient(name = "slr-svr", configuration = FeignConfig.class, contextId = "slr-svr-shop")
public interface SlrShopSvc {

    /**
     * 获取单个店铺信息
     *
     * @mbg.generated 自动生成，如需修改，请删除本行
     */
    @GetMapping("/slr/shop/getbyid")
    SlrShopMo getById(@RequestParam("id") java.lang.Long id);

    /**
     * 根据参数获取店铺集合
     * 
     * @param orgId
     * @return
     */
    @GetMapping("/slr/shop")
    List<SlrShopMo> list(@RequestBody SlrShopMo mo);

    /**
     * 查询所有店铺信息
     */
    @GetMapping("/slr/shop/all")
    List<SlrShopMo> listAll();
}
