package com.jiang.common.utils;

import com.jiang.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author 蒋雨岳
 * @Date 2020/3/20 0020
 */
@Data
@AllArgsConstructor
public class PageResult<T> {

    private int pageNum;
    private  int pageSize;
    private Long total;
    private List<T> list;
}
