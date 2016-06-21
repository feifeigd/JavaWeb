package com.internet.cms.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 用来确定哪些方法由哪些角色访问
 * 属性有一个role，如果role的值为base表示这个方法可以被所有的登陆用户访问
 * 如果为ROLE_PUBLISH表示只能为文章发布人员访问
 * 如果某个方法中没有加入AuthMethod就表示该方法只能由管理员访问
 * @author Administrator
 *
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthMethod {

	public String role() default "base";
}
