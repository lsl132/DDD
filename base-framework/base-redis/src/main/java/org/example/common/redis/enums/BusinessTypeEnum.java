package org.example.common.redis.enums;


public enum BusinessTypeEnum {


    CLOUD_ACCOUNT_INSERT("1010101","云管-账号管理-管理员账号新增"),
    CLOUD_ACCOUNT_UPDATE("1010102","云管-账号管理-管理员账号信息更新"),
    CLOUD_ACCOUNT_DEACTIVATE("1010103","云管-账号管理-管理员账号停用"),
    CLOUD_ACCOUNT_ACTIVATE("1010104","云管-账号管理-管理员账号启用"),
    CLOUD_ACCOUNT_DELETE("1010105","云管-账号管理-管理员账号删除"),

    CLOUD_ROLE_INSERT("1010201","云管-账号管理-管理员角色新增"),
    CLOUD_ROLE_UPDATE("1010202","云管-账号管理-管理员角色信息更新"),
    CLOUD_ROLE_DEACTIVATE("1010203","云管-账号管理-管理员角色停用"),
    CLOUD_ROLE_ACTIVATE("1010204","云管-账号管理-管理员角色启用"),
    CLOUD_ROLE_DELETE("1010205","云管-账号管理-管理员角色删除"),
    //------------------------------------------------------------
    CLOUD_PRODUCT_INSERT("1020101","云管-研发管理-产品新增"),

    CLOUD_AUTHORITY_PACKAGE_INSERT("1020201","云管-研发管理-权限包新增"),

    CLOUD_FUNCTION_POINT_INSERT("1020301","云管-研发管理-功能点新增"),
    //------------------------------------------------------------
    CLOUD_APPLICTION_INSERT("1030101","云管-运维管理-应用服务新增"),

    CLOUD_API_INTERFACE_INSERT("1030201","云管-运维管理-API接口新增"),

    CLOUD_INFORMATION_INTERFACE_INSERT("1030301","云管-运维管理-资讯接口新增"),

    CLOUD_SITE_INSERT("1030401","云管-运维管理-站点新增"),
    //------------------------------------------------------------
    CLOUD_TENANT_INSERT_FIRST("1040101","云管-租户管理-租户新增(第一步)"),
    CLOUD_TENANT_INSERT_SECOND("1040102","云管-租户管理-租户新增(第二步)"),
    CLOUD_TENANT_INSERT_THIRD("1040103","云管-租户管理-租户新增(第三步)"),
    CLOUD_TENANT_INSERT_FOURTH("1040104","云管-租户管理-租户新增(第四步)"),

    //------------------------------------------------------------
    CLOUD_COMBO_INSERT("1050101","云管-产品商城-套餐新增"),

    //------------------------------------------------------------
    CLOUD_ORDER_INSERT("1050101","云管-订单管理-订单新增"),
    //-----------------------------------------------------------
    CLOUD_ANNOUNCEMENT_INSERT("1060101","云管-公告-公告新增（草稿箱）"),
    CLOUD_ANNOUNCEMENT_UPDATE("1060102","云管-公告-公告信息编辑"),
    CLOUD_ANNOUNCEMENT_PUBLISH("1060103","云管-公告-公告发布"),
    CLOUD_ANNOUNCEMENT_UNPUBLISH("1060104","云管-公告-公告取消发布"),

    ;

    /** 错误代码 */
    private String code;
    /** 错误代码归类描述 */
    private String describe;


    BusinessTypeEnum(String code, String describe) {
        this.code = code;
        this.describe = describe;
    }


    public static BusinessTypeEnum getBusinessTypeEnum(String code) {
        for (BusinessTypeEnum e : values()) {
            if (e.getCode().equals(code) ) {
                return e;
            }
        }
        throw new IllegalArgumentException("获取Redis业务类型失败");
    }

    public String getCode() {
        return code;
    }

    public String getDescribe() {
        return describe;
    }
}
