package com.vitily.member.module.entity;

import com.vitily.common.module.BaseEntity;

/**
 * 描述:会员分组表的实体类
 * @version
 * @author:  pc
 * @创建时间: 2019-10-30
 */
public class TbMemGroup extends BaseEntity {
    /**
     * 分组名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 默认状态
     */
    private Integer isDefault;

    /**
     * 享受价格百分比
     */
    private Double percent;

    /**
     * 分组名称
     * [whh]@return name 分组名称
     */
    public String getName() {
        return name;
    }

    /**
     * 分组名称
     * @param name 分组名称
     */
    public TbMemGroup setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 描述
     * [whh]@return description 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 描述
     * @param description 描述
     */
    public TbMemGroup setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 默认状态
     * [whh]@return is_default 默认状态
     */
    public Integer getIsDefault() {
        return isDefault;
    }

    /**
     * 默认状态
     * @param isDefault 默认状态
     */
    public TbMemGroup setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
        return this;
    }

    /**
     * 享受价格百分比
     * [whh]@return percent 享受价格百分比
     */
    public Double getPercent() {
        return percent;
    }

    /**
     * 享受价格百分比
     * @param percent 享受价格百分比
     */
    public TbMemGroup setPercent(Double percent) {
        this.percent = percent;
        return this;
    }
}