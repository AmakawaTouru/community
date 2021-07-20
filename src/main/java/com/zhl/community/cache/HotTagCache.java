package com.zhl.community.cache;

import com.zhl.community.dto.HotTagDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.*;

@Data
@Component
public class HotTagCache {

    private List<Integer> hotTags = new ArrayList<>();

    public void sortTags(Map<Integer,Long> priorities){
        //设置一共有多少个热门标签
        int max = 8;

        //优先级队列
        PriorityQueue<HotTagDTO> priorityQueue = new PriorityQueue<>(max, new Comparator<HotTagDTO>() {
            @Override
            public int compare(HotTagDTO o1, HotTagDTO o2) {
                if(o1.getPriorities() - o2.getPriorities() > 0){
                    return 1;
                }else if(o1.getPriorities() - o2.getPriorities() == 0){
                    return 0;
                }else{
                    return -1;
                }
            }
        });

        //遍历
        for(Map.Entry<Integer,Long> entry:priorities.entrySet()){
            HotTagDTO hotTagDTO = new HotTagDTO();
            hotTagDTO.setTvid(entry.getKey());
            hotTagDTO.setPriorities(entry.getValue());
            if(priorityQueue.size() < max){
                priorityQueue.offer(hotTagDTO);
            }else{
                Long minHot = priorityQueue.peek().getPriorities();
                if(minHot < hotTagDTO.getPriorities()){
                    priorityQueue.poll();
                    priorityQueue.offer(hotTagDTO);
                }
            }
        }

        //将优先队列逐个出队
        List<Integer> sortedTags = new ArrayList<>();
        HotTagDTO poll = priorityQueue.poll();
        while (poll != null) {
            sortedTags.add(0, poll.getTvid());
            poll = priorityQueue.poll();
        }

        //得到的标签就是最大的几个标签ID了。
        hotTags = sortedTags;

    }


}
