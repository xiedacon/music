package cn.xiedacon.test.base;

public @interface Priority {

	/**
	 * 测试方法优先级
	 * 
	 * @return
	 */
	Level value() default Level.L1;

	enum Level {
		L1(1), L2(2), L3(3), L4(4);

		Level(int i) {
			this.level = i;
		}

		public int level;
	}
}
