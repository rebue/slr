package rebue.slr.msg;

import lombok.Data;

@Data
public class SlrModifyShopNameMsg {
	
	/**
	 * 店铺id
	 */
	private Long id;
	
	/**
	 * 店铺名字
	 */
	private String  name;

}
