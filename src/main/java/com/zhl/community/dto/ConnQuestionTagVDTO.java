package com.zhl.community.dto;

import com.zhl.community.model.Question;
import com.zhl.community.model.TagValues;
import lombok.Data;

@Data
public class ConnQuestionTagVDTO {
    private Integer id;
    private Integer qid;
    private Integer tvid;
    private Question question;
    private TagValues tagValues;
}
