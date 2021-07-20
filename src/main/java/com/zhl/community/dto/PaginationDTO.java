package com.zhl.community.dto;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO<T> {

    private Integer curPage;
    private Integer totalPageNum;
    private boolean showFirstPage;
    private boolean showPrevious;
    private boolean showNext;
    private boolean showEndPage;
    private List<Integer> pages;
    private List<T> data;

    public PaginationDTO(){}
    public PaginationDTO(PageInfo pageInfo){
        this.showFirstPage = !pageInfo.isIsFirstPage();
        this.showPrevious = pageInfo.isHasPreviousPage();
        this.showNext = pageInfo.isHasNextPage();
        this.showEndPage = !pageInfo.isIsLastPage();
        this.curPage = pageInfo.getPageNum();
        this.totalPageNum = pageInfo.getPages();

        pages = new ArrayList<>();
        pages.add(curPage);
        for(int i = 1; i<=3;i++){
            if(curPage - i >= 1){
                pages.add(0,curPage-i);
            }
            if(curPage + i <= totalPageNum){
                pages.add(curPage+i);
            }
        }
    }

}
