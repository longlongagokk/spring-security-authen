package com.vitily.cache.foreign;

import com.vitily.common.module.BaseEntity;

public class TbDictionary extends BaseEntity {
    /**
     * 类型id
     */
    private Integer typeId;

    /**
     * 键
     */
    private String key;

    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String description;

    /**
     * 值类型
     */
    private String valueType;

    /**
     * 类型id
     * [whh]@return type_id 类型id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 类型id
     * @param typeId 类型id
     */
    public TbDictionary setTypeId(Integer typeId) {
        this.typeId = typeId;
        return this;
    }

    /**
     * 键
     * [whh]@return key 键
     */
    public String getKey() {
        return key;
    }

    /**
     * 键
     * @param key 键
     */
    public TbDictionary setKey(String key) {
        this.key = key;
        return this;
    }

    /**
     * 值
     * [whh]@return value 值
     */
    public String getValue() {
        return value;
    }

    /**
     * 值
     * @param value 值
     */
    public TbDictionary setValue(String value) {
        this.value = value;
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
    public TbDictionary setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * 值类型
     * [whh]@return value_type 值类型
     */
    public String getValueType() {
        return valueType;
    }

    /**
     * 值类型
     * @param valueType 值类型
     */
    public TbDictionary setValueType(String valueType) {
        this.valueType = valueType;
        return this;
    }
}