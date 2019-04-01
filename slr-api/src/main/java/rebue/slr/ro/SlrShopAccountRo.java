package rebue.slr.ro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.github.pagehelper.PageInfo;

import lombok.Data;

/**
 * 返回已添加的和未添加的用户列表
 */
@JsonInclude(Include.NON_NULL)
@Data
public class SlrShopAccountRo {

	/**
     * 已添加的用户列表
     */
    private PageInfo<SlrShopAccountDetailRo> addedUsers;
    /**
     * 未添加的用户列表
     */
    private PageInfo<SlrShopAccountDetailRo> unaddedUsers;
}
