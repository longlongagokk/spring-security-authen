package com.vitily.member.module.entity;

import com.vitily.common.module.BaseEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 描述:会员分组表的实体类
 * @version
 * @author:  pc
 * @创建时间: 2019-10-30
 */
@Data
@Getter
@Setter
public class TbGroupHasResources extends BaseEntity {
    private Integer groupId;
    private Integer resourcesId;
}