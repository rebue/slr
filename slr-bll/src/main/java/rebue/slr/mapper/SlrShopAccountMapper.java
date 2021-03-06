package rebue.slr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.slr.mo.SlrShopAccountMo;

@Mapper
public interface SlrShopAccountMapper extends MybatisBaseMapper<SlrShopAccountMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    SlrShopAccountMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SlrShopAccountMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SlrShopAccountMo> selectSelective(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(SlrShopAccountMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(SlrShopAccountMo record);
    
    /**
     *  删除门店账号
     *  @param shopId
     *  @param accountId
     *  @return
     */
    @Delete("delete from SLR_SHOP_ACCOUNT where SHOP_ID = #{shopId,jdbcType=BIGINT} and ACCOUNT_ID IN (${userIds})")
    int delShopAccount(@Param("shopId") Long shopId, @Param("userIds") String userIds);
    
    /**
     *  删除卖家下的所有门店中的某个帐号
     *  @param shopId
     *  @param accountId
     *  @return
     */
    @Delete("delete from SLR_SHOP_ACCOUNT where ACCOUNT_ID = #{accountId,jdbcType=BIGINT} and SELLER_ID = #{sellerId,jdbcType=BIGINT}")
    int delShopAccountByAccountIdAndSellerId(@Param("accountId") Long accountId, @Param("sellerId") Long sellerId);
    
    /**
     * 根据条件获取单个账户信息
     * @param record
     * @return
     */
    SlrShopAccountMo  getOneShopAccount(SlrShopAccountMo record);
}
