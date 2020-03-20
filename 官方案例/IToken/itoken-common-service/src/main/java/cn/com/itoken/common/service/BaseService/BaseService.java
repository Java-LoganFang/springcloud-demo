package cn.com.itoken.common.service.BaseService;

import cn.com.itoken.common.service.domain.BaseDomain;
import com.github.pagehelper.PageInfo;

/**
 * 通用业务逻辑层
 * 包括增删改查、计数
 * @param <T>
 */
public interface BaseService <T extends BaseDomain> {
    public int insert(T t, String createBy);

    public int delete(T t);

    public int update(T t, String updateBy);

    public int count(T t);

    public T select(T t);

    public PageInfo<T> page(int pageNum, int pageSize, T t);
}
