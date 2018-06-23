package com.github.cumtfc.srs.component.specification;

/**
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
public interface Specification<T> {

    /**
     * 实否满足规约
     * @param t 需要判断的对象
     * @return 判断结果
     */
    boolean isSatisfiedBy(T t);

}
