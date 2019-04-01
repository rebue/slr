package rebue.slr.mo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 店铺信息
 *
 * 数据库表: SLR_SHOP
 *
 * @mbg.generated 自动生成的注释，如需修改本注释，请删除本行
 */
@JsonInclude(Include.NON_NULL)
public class SlrShopMo implements Serializable {

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long id;

    /**
     *    卖家ID(=卖家组织ID)
     *
     *    数据库字段: SLR_SHOP.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Long sellerId;

    /**
     *    店铺名称
     *
     *    数据库字段: SLR_SHOP.SHOP_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String shopName;

    /**
     *    店铺简称
     *
     *    数据库字段: SLR_SHOP.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String shortName;

    /**
     *    详细地址
     *
     *    数据库字段: SLR_SHOP.ADDERSS
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String adderss;

    /**
     *    经度
     *
     *    数据库字段: SLR_SHOP.LONGITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String longitude;

    /**
     *    纬度
     *
     *    数据库字段: SLR_SHOP.LATITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String latitude;

    /**
     *    联系方式
     *
     *    数据库字段: SLR_SHOP.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private String contact;

    /**
     *    是否启用
     *
     *    数据库字段: SLR_SHOP.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private Boolean isEnabled;

    /**
     *    创建时间
     *
     *    数据库字段: SLR_SHOP.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    /**
     *    修改时间
     *
     *    数据库字段: SLR_SHOP.MODIFY_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date modifyTime;

    /**
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    private static final long serialVersionUID = 1L;

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getId() {
        return id;
    }

    /**
     *    店铺ID
     *
     *    数据库字段: SLR_SHOP.ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *    卖家ID(=卖家组织ID)
     *
     *    数据库字段: SLR_SHOP.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Long getSellerId() {
        return sellerId;
    }

    /**
     *    卖家ID(=卖家组织ID)
     *
     *    数据库字段: SLR_SHOP.SELLER_ID
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    /**
     *    店铺名称
     *
     *    数据库字段: SLR_SHOP.SHOP_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getShopName() {
        return shopName;
    }

    /**
     *    店铺名称
     *
     *    数据库字段: SLR_SHOP.SHOP_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    /**
     *    店铺简称
     *
     *    数据库字段: SLR_SHOP.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getShortName() {
        return shortName;
    }

    /**
     *    店铺简称
     *
     *    数据库字段: SLR_SHOP.SHORT_NAME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     *    详细地址
     *
     *    数据库字段: SLR_SHOP.ADDERSS
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getAdderss() {
        return adderss;
    }

    /**
     *    详细地址
     *
     *    数据库字段: SLR_SHOP.ADDERSS
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setAdderss(String adderss) {
        this.adderss = adderss;
    }

    /**
     *    经度
     *
     *    数据库字段: SLR_SHOP.LONGITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     *    经度
     *
     *    数据库字段: SLR_SHOP.LONGITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     *    纬度
     *
     *    数据库字段: SLR_SHOP.LATITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     *    纬度
     *
     *    数据库字段: SLR_SHOP.LATITUDE
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     *    联系方式
     *
     *    数据库字段: SLR_SHOP.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public String getContact() {
        return contact;
    }

    /**
     *    联系方式
     *
     *    数据库字段: SLR_SHOP.CONTACT
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     *    是否启用
     *
     *    数据库字段: SLR_SHOP.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Boolean getIsEnabled() {
        return isEnabled;
    }

    /**
     *    是否启用
     *
     *    数据库字段: SLR_SHOP.IS_ENABLED
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setIsEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     *    创建时间
     *
     *    数据库字段: SLR_SHOP.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     *    创建时间
     *
     *    数据库字段: SLR_SHOP.CREATE_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     *    修改时间
     *
     *    数据库字段: SLR_SHOP.MODIFY_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public Date getModifyTime() {
        return modifyTime;
    }

    /**
     *    修改时间
     *
     *    数据库字段: SLR_SHOP.MODIFY_TIME
     *
     *    @mbg.generated 自动生成，如需修改，请删除本行
     */
    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
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
        sb.append(", sellerId=").append(sellerId);
        sb.append(", shopName=").append(shopName);
        sb.append(", shortName=").append(shortName);
        sb.append(", adderss=").append(adderss);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", contact=").append(contact);
        sb.append(", isEnabled=").append(isEnabled);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
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
        SlrShopMo other = (SlrShopMo) that;
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
