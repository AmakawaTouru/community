package com.zhl.community.dto;

import com.zhl.community.model.TagProperties;
import com.zhl.community.model.TagValues;
import lombok.Data;

@Data
public class ConnTagpTagvDTO {
    private Integer id;
    private Integer tpid;
    private Integer tvid;
    private TagProperties tagProperties;
    private TagValues tagValues;
}
