package com.github.cumtfc.srs.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**注意！！要使用利用此注解获取到的当前用户的关联属性时
 * 建议从数据库重新查一遍，否则hibernate会因为session关闭而抛出异常
 * @author 冯楚
 * @date 2018/6/8-19:12
 */
@Target(ElementType.PARAMETER)          // 可用在方法的参数上
@Retention(RetentionPolicy.RUNTIME)     // 运行时有效
public @interface CurrentUser {
}