package rebue.slr.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import rebue.robotech.mapper.MybatisBaseMapper;
import rebue.slr.mo.SlrShopMo;

@Mapper
public interface SlrShopMapper extends MybatisBaseMapper<SlrShopMo, Long> {

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int deleteByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insert(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int insertSelective(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    SlrShopMo selectByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKeySelective(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int updateByPrimaryKey(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SlrShopMo> selectAll();

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    List<SlrShopMo> selectSelective(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existByPrimaryKey(Long id);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    boolean existSelective(SlrShopMo record);

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    int countSelective(SlrShopMo record);
    
    /**
     * 禁用或者启用店铺
     *
     * @param id
     * @param isEnabled
     * @return
     */
    @Update("UPDATE SLR_SHOP SET IS_ENABLED=#{isEnabled,jdbcType=TINYINT} WHERE ID = #{id,jdbcType=BIGINT}")
    int enable(@Param("id") Long id, @Param("isEnabled") boolean isEnabled);
}
