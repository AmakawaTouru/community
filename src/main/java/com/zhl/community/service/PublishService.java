package com.zhl.community.service;

import com.zhl.community.mapper.ConnQuestionTagvMapper;
import com.zhl.community.mapper.QuestionMapper;
import com.zhl.community.mapper.TagValuesMapper;
import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import com.zhl.community.utils.TagSplitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublishService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ConnQuestionTagvMapper questionTagvMapper;
    @Autowired
    private TagValuesMapper tagValuesMapper;

    /**
     * 更新或者插入问题
     * @param id
     * @param title
     * @param description
     * @param tag
     * @param user
     */
    @Transactional
    public void publishOrUpdateQuestion(Integer id, String title, String description, String tag, User user) {
        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        //切分标签
        String[] tags = TagSplitUtils.tagSplit(tag);
        List<Integer> vals = new ArrayList<>();
        for(String s:tags){
            Integer vid = tagValuesMapper.selectByValue(s);
            vals.add(vid);
        }
        if(id == -1){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setCreator(user.getId());
            question.setCommentCount(0);
            question.setViewCount(0);
            question.setLikeCount(0);
            //返回自增ID，用于插入联系表
            questionMapper.inserQuestion(question);
            id = question.getId();
        }else{
            question.setGmtModified(System.currentTimeMillis());
            question.setId(id);
            questionMapper.updateQuestion(question);
        }
        //先把id对应的标签全删了，再插入一遍。
        //这样可以让插入可修改都用同一个逻辑
        questionTagvMapper.deleteByQuestionId(id);
        for(int v:vals) {
            questionTagvMapper.insert(id, v);
        }
    }
}
