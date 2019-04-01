package rebue.slr.ro;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class SlrShopRo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 店铺ID
	 *
	 * 数据库字段: SHP_SHOP.ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Long id;

	/**
	 * 店铺名称
	 *
	 * 数据库字段: SHP_SHOP.SHOP_NAME
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String shopName;

	/**
	 * 店铺简称
	 *
	 * 数据库字段: SHP_SHOP.SHOP_ABBRE
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String shortName;

	/**
	 * 详细地址
	 *
	 * 数据库字段: SHP_SHOP.ADDERSS
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String adderss;

	/**
	 * 纬度
	 *
	 * 数据库字段: SHP_SHOP.LATITUDE
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String latitude;

	/**
	 * 经度
	 *
	 * 数据库字段: SHP_SHOP.LONGITUDE
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String longitude;

	/**
	 * 联系方式
	 *
	 * 数据库字段: SHP_SHOP.CONTACT
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private String contact;

	/**
	 * 组织ID
	 *
	 * 数据库字段: SHP_SHOP.ORG_ID
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Long sellerId;
	
	/**
	 * 组织名称
	 */
	private String orgName;

	/**
	 * 是否启用
	 *
	 * 数据库字段: SHP_SHOP.IS_ENABLED
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	private Boolean isEnabled;

	/**
	 * 创建时间
	 *
	 * 数据库字段: SHP_SHOP.CREATE_TIME
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date createTime;

	/**
	 * 修改时间
	 *
	 * 数据库字段: SHP_SHOP.MODIFY_TIME
	 *
	 * @mbg.generated 自动生成，如需修改，请删除本行
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date modifyTime;
}
