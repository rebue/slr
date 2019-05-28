package rebue.slr.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 相比Mo增加了一个accountId字段，是为了在添加店铺的时候将当前用户添加进去作为默认用户
 * @author jjl
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class AddShopTo {
	
    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP.ID
     *
     */
    private Long id;

    /**
     *    卖家ID(=卖家组织ID)
     *
     *    数据库字段: SLR_SHOP.SELLER_ID
     *
     */
    private Long sellerId;

    /**
     *    店铺名称
     *
     *    数据库字段: SLR_SHOP.SHOP_NAME
     *
     */
    private String shopName;

    /**
     *    店铺简称
     *
     *    数据库字段: SLR_SHOP.SHORT_NAME
     *
     */
    private String shortName;

    /**
     *    详细地址
     *
     *    数据库字段: SLR_SHOP.ADDERSS
     *
     */
    private String adderss;

    /**
     *    经度
     *
     *    数据库字段: SLR_SHOP.LONGITUDE
     *
     */
    private String longitude;

    /**
     *    纬度
     *
     *    数据库字段: SLR_SHOP.LATITUDE
     *
     */
    private String latitude;

    /**
     *    联系方式
     *
     *    数据库字段: SLR_SHOP.CONTACT
     *
     */
    private String contact;

    /**
     *    是否启用
     *
     *    数据库字段: SLR_SHOP.IS_ENABLED
     *
     */
    private Boolean isEnabled;
    
    /**
     *    账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ACCOUNT_ID
     *
     */
    private Long accountId;

}
