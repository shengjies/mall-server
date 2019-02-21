package com.code.mallservice.mall.utils;



import java.util.List;

public class Page<T> {


    private List<T> content;

    public List<T> getContent() {
        return content;
    }

    public Object total;

    public void setContent(List<T> content) {
        this.content = content;
    }

    private long count;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public Page() {
    }

    public Page(List<T> content, long count) {
        this.content = content;
        this.count = count;
    }

    public Object getTotal() {
        return total;
    }

    public void setTotal(Object total) {
        this.total = total;
    }
}
