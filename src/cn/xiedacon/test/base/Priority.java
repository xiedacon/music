package cn.xiedacon.test.base;

public @interface Priority {

	/**
	 * 测试方法优先级
	 * @return
	 */
	Level value() default Level.Level_1;
}
