package com.kingdee.web.results;

import org.springframework.data.domain.Page;
import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Json result view: { total: <i>X</i>, rows: [...] }
 * @author coder
 */
public class PageListJsonResult<E> {

    private long total = 0;

    private List<E> rows = new ArrayList<>();

    public PageListJsonResult() {
    }

    public PageListJsonResult(List<E> rows) {
        if (rows != null) {
            this.total = rows.size();
            this.rows = rows;
        }
    }

    public PageListJsonResult(List<E> rows, long total) {
        if (rows != null) {
            this.rows = rows;
        }
        this.total = total;
    }

    public PageListJsonResult(@NonNull Page<E> page) {
        this.total = page.getTotalElements();
        this.rows = page.getContent();
    }

    public static <E> PageListJsonResult<E> of(@NonNull Page<E> page){
        PageListJsonResult<E> result = new PageListJsonResult<>();
        result.total = page.getTotalElements();
        result.rows  = page.getContent();

        return result;
    }

    public long getTotal() {
        return total;
    }

    public List<E> getRows() {
        return rows;
    }
}
