package rebue.slr.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 修改卖家信息实体参数
 * @author lbl
 *
 */
@Data
@JsonInclude(Include.NON_NULL)
public class ModifySellerTo {

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
	 * 公司/组织简称
	 */
	private String shortName;

	/**
	 * 公司/组织备注
	 *
	 * 数据库字段: SUC_ORG.REMARK
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String remark;

	/**
	 * 联系方式
	 *
	 * 数据库字段: SUC_ORG.CONTACT
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String contact;
}
