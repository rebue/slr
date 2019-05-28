package rebue.slr.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 添加卖家实体参数
 * 
 * @author lbl
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class AddSellerTo {

	/**
	 * 公司/组织名称
	 *
	 * 数据库字段: SUC_ORG.NAME
	 *
	 */
	private String name;

	/**
	 * 公司/组织简称
	 *
	 * 数据库字段: SUC_ORG.SHORT_NAME
	 *
	 */
	private String shortName;

	/**
	 * 公司/组织备注
	 *
	 * 数据库字段: SUC_ORG.REMARK
	 *
	 */
	private String remark;

	/**
	 * 联系方式
	 *
	 * 数据库字段: SUC_ORG.CONTACT
	 *
	 */
	private String contact;
	
    
    /**
     *    账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ACCOUNT_ID
     *
     */
    private Long accountId;
}
