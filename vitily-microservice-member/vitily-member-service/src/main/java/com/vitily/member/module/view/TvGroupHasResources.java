package com.vitily.member.module.view;

import com.vitily.member.module.entity.TbGroupHasResources;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TvGroupHasResources extends TbGroupHasResources {
	String urlPattern;
}
