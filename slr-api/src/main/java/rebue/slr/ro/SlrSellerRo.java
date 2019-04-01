package rebue.slr.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 卖家信息
 * @author lbl
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class SlrSellerRo {

	/**
	 * 公司/组织id
	 *
	 * 数据库字段: SUC_ORG.ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Long id;

	/**
	 * 公司/组织名称
	 *
	 * 数据库字段: SUC_ORG.NAME
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String name;

	/**
	 * 公司/组织备注
	 *
	 * 数据库字段: SUC_ORG.REMARK
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String remark;
	
	/**
	 * 公司/组织简称
	 */
	private String shortName;

	/**
	 * 创建时间戳
	 *
	 * 数据库字段: SUC_ORG.CREATE_TIMESTAMP
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Long createTimestamp;

	/**
	 * 是否启用
	 *
	 * 数据库字段: SUC_ORG.IS_ENABLED
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Boolean isEnabled;

	/**
	 * 联系方式
	 *
	 * 数据库字段: SUC_ORG.CONTACT
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String contact;
}
