package com.zhl.community.schedule;

import com.zhl.community.cache.HotTagCache;
import com.zhl.community.mapper.ConnQuestionTagvMapper;
import com.zhl.community.mapper.QuestionMapper;
import com.zhl.community.model.Question;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Component
@EnableScheduling
@Slf4j
public class ScheduleTask {

    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private ConnQuestionTagvMapper connQuestionTagvMapper;
    @Autowired
    private HotTagCache hotTagCache;
    /**
     * 三小时执行一次热门标签更新
     */
    @Scheduled(fixedRate = 1000 * 60 * 60 * 3)
    public void hotTagSchedule(){

        log.info("现在的时间是：{}",new Date());

        //起始位置
        int offset = 0;
        //偏移量
        int limit = 20;
        //问题列表
        List<Question> questions = new ArrayList<>();
        //优先级映射表(标签id，标签优先级计算值)
        HashMap<Integer,Long> priorities =new HashMap<>();

        //0就是刚开始计算，等于limit就是还没到最后
        while(offset == 0 || questions.size() == limit){
            //找出一部分问题
            questions = questionMapper.selectByOffset(offset,limit);
            for(Question question:questions){
                //找出标签值id
                List<Integer> tvids = connQuestionTagvMapper.selectTvidsByQid(question.getId());
                for(int tvid:tvids){
                    Long priority = priorities.get(tvid);
                    //如果之前已经计算过
                    if(priority != null){
                        priority = 5 + priority + 2*question.getCommentCount() +question.getViewCount();
                    }else{
                        //如果之前没有计算过
                        priority = new Long(5 + 2 * question.getCommentCount() + question.getViewCount());
                    }
                    priorities.put(tvid, priority);
                }
            }
            offset += limit;
        }
        //计算完之后将priorities交给HotTagCache来计算。
        hotTagCache.sortTags(priorities);
        System.out.println(priorities);

    }
}
