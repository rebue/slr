package rebue.slr.mo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;

/**
 * 店铺账号
 *
 * 数据库表: SLR_SHOP_ACCOUNT
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SlrShopAccountMo implements Serializable {

    /**
     *    店铺账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long shopId;

    /**
     *    卖家ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long sellerId;

    /**
     *    账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ACCOUNT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long accountId;

    /**
     *    是否为默认店铺
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.IS_DEFAULT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isDefault;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    店铺账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    店铺账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getShopId() {
        return shopId;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SHOP_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    /**
     *    卖家ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     *    卖家ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     *    账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ACCOUNT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     *    账号ID
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.ACCOUNT_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    /**
     *    是否为默认店铺
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.IS_DEFAULT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsDefault() {
        return isDefault;
    }

    /**
     *    是否为默认店铺
     *
     *    数据库字段: SLR_SHOP_ACCOUNT.IS_DEFAULT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", shopId=").append(shopId);
        sb.append(", sellerId=").append(sellerId);
        sb.append(", accountId=").append(accountId);
        sb.append(", isDefault=").append(isDefault);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SlrShopAccountMo other = (SlrShopAccountMo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()));
    }

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }
}
