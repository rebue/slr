package rebue.slr.to;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

/**
 * 在店铺账号中移动的账号对象
 * @author lbl
 *
 */
@JsonInclude(Include.NON_NULL)
@Data
public class SlrMoveShopAccountTo {

	/**
	 * 店铺ID
	 */
	private Long id;
	
	/**
	 * 卖家id/组织id
	 */
	private Long sellerId;

	/**
	 * 已添加用户列表的模糊查询的关键字
	 */
	private String addedKeys;

	/**
	 * 未添加用户列表的模糊查询的关键字
	 */
	private String unaddedKeys;

	/**
	 * 已添加用户列表的页码
	 */
	private Integer addedPageNum;

	/**
	 * 未添加用户列表的页码
	 */
	private Integer unaddedPageNum;

	/**
	 * 移动用户的ID列表
	 */
	private List<Long> moveIds;
}
