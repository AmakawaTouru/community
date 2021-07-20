package com.zhl.community.service;

import com.zhl.community.cache.HotTagCache;
import com.zhl.community.mapper.TagValuesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagValuesService {

    @Autowired
    private TagValuesMapper tagValuesMapper;
    @Autowired
    private HotTagCache hotTagCache;

    /**
     * 返回热门标签
     */
    public List<String> hogTags(){
        List<Integer> hotTags = hotTagCache.getHotTags();
        System.out.println(hotTags);
        List<String> hotTagsVal = tagValuesMapper.selectByIds(hotTags);
        return  hotTagsVal;
    }


}
