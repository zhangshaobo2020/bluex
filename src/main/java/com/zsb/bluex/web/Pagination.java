package com.zsb.bluex.web;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Pagination<T> implements Serializable {
    private long current;
    private long size;
    private long total;
    private List<T> records;

    public Pagination(HttpServletRequest request) {
        if (request.getParameterMap().containsKey("size") && request.getParameterMap().containsKey("current")) {
            this.setSize(Long.parseLong(request.getParameter("size")));
            this.setCurrent(Long.parseLong(request.getParameter("current")));
        } else {
            throw new RuntimeException("缺少分页参数 size & current");
        }
    }

    public Page<T> generate() {
        return new Page<>(current, size);
    }

    public Pagination<T> transfer(Page<T> page) {
        this.current = page.getCurrent();
        this.size = page.getSize();
        this.total = page.getTotal();
        this.records = page.getRecords();
        return this;
    }
}
