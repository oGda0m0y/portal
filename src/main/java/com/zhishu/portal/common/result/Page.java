
package com.zhishu.portal.common.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Page implements Serializable {

    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 3395165835078064973L;

    // 当前页码
    private Integer page;
    // 最大页码数
    private Integer maxPage;
    // 总记录数
    private Integer totalCount;
    // 查询开始条数,翻页的开始条数位置
    private Integer beginIndex;
    // 每页的条数
    private Integer pageSize;
    // 传给前台的数据列表数据
    private List<?> data;
    // 传给前台的数据单个数据
    private Object info;
    // 从前台传入后台的查询参数
    private Map<String, Object> parm = new HashMap<String, Object>();
    // 返回码
    private int code;
    // 返回错误或成功的消息
    private String msg;

    public Page() {

    }

    public Page(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        if (this.page < 1)
            this.page = 1;
        if (this.pageSize < 0)
            this.pageSize = 10;
        if (totalCount % this.pageSize == 0) {
            maxPage = totalCount / this.pageSize;
        } else {

            if (totalCount <= this.pageSize) {
                maxPage = 1;
            } else {
                maxPage = (totalCount / this.pageSize) + 1;
            }

        }

        beginIndex = (this.page - 1) * this.pageSize;

    }

    public Page(int page, int pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public void calMaxPage() {
        if (this.page < 1)
            this.page = 1;
        if (this.pageSize < 0)
            this.pageSize = 10;
        if (totalCount % this.pageSize == 0) {
            maxPage = totalCount / this.pageSize;
        } else {

            if (totalCount <= this.pageSize) {
                maxPage = 1;
            } else {
                maxPage = (totalCount / this.pageSize) + 1;
            }

        }
        beginIndex = (this.page - 1) * this.pageSize;
    }

    public void setResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getMaxPage() {
        if (maxPage == null || maxPage < 1)
            return 1;
        return maxPage;
    }

    public void setMaxPage(Integer maxPage) {
        this.maxPage = maxPage;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public Map<String, Object> getParm() {
        return parm;
    }

    public void setParm(Map<String, Object> parm) {
        this.parm = parm;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getInfo() {
        return info;
    }

    public void setInfo(Object info) {
        this.info = info;
    }

   
}
