package com.zhl.community.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhl.community.dto.PaginationDTO;
import com.zhl.community.dto.QuestionDTO;
import com.zhl.community.mapper.ConnQuestionTagvMapper;
import com.zhl.community.mapper.QuestionMapper;
import com.zhl.community.mapper.UserMapper;
import com.zhl.community.model.Question;
import com.zhl.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ConnQuestionTagvMapper connQuestionTagvMapper;

    /**
     * 分页展示所有帖子
     * @return
     */
    public PaginationDTO<QuestionDTO> listAllQuestion(int page, int size,String search,String sort){
        List<QuestionDTO> qsdto = new ArrayList<>();
        PageHelper.startPage(page, size);
        //根据查找和内容进行排序
        List<Question> questions = questionMapper.findAll(search,sort);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        for(Question q:questions){
            User creator = userMapper.findById(q.getCreator());
            QuestionDTO temp = new QuestionDTO();
            BeanUtils.copyProperties(q, temp);
            temp.setUser(creator);
            qsdto.add(temp);
        }
        PaginationDTO<QuestionDTO> res = new PaginationDTO(pageInfo);
        res.setData(qsdto);
        return res;
    }

    /**
     * 分页展示某一个用户的帖子
     * @return
     */
    public PaginationDTO<QuestionDTO> listQuestionByUser(int page, int size,User user){
        List<QuestionDTO> qsdto = new ArrayList<>();
        PageHelper.startPage(page, size);
        List<Question> questions = questionMapper.findByCreatorId(user);
        PageInfo<Question> pageInfo = new PageInfo<>(questions);
        for(Question q:questions){
            QuestionDTO temp = new QuestionDTO();
            BeanUtils.copyProperties(q, temp);
            temp.setUser(user);
            qsdto.add(temp);
        }
        PaginationDTO<QuestionDTO> res = new PaginationDTO(pageInfo);
        res.setData(qsdto);
        return res;
    }


    /**
     * 根据问题ID返回问题信息
     * @param id
     * @return
     */
    public QuestionDTO listQuestionById(int id){
        Question question = questionMapper.findById(id);
        User user = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);
        questionDTO.setUser(user);
        return questionDTO;
    }


    /**
     * 根据问题来查找相关问题
     * 思路：
     *  先根据问题，找到标签
     *  再根据标签，找到其他用这个标签的问题
     *  再根据问题，倒序后10位为相关问题
     * @param questionDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        int qid = questionDTO.getId();
        //根据qid来获取相对应的标签值有哪些
        List<Integer> tvids = connQuestionTagvMapper.selectTvidsByQid(qid);
        if(tvids.isEmpty()){
            return null;
        }
        //根据tvid来获取的问题id（去重）
        List<Integer> qids = connQuestionTagvMapper.selectQidsByTvid(qid,tvids);
        if(qids.isEmpty()){
            return null;
        }
        //返回问题列表，并且按创造时间倒序
        List<Question> questions = questionMapper.findByIds(qids);
        List<QuestionDTO> res = new ArrayList<>();
        //返回一个DTO列表
        for(Question question:questions){
            User user = userMapper.findById(question.getCreator());
            QuestionDTO temp = new QuestionDTO();
            BeanUtils.copyProperties(question, temp);
            temp.setUser(user);
            res.add(temp);
        }
        return res;
    }

    public void updateViewCount(int id) {
        questionMapper.updateViewCount(id);
    }
}
