package com.heracles.framework.task.job;

import com.heracles.eat.helper.Cart;

public class ClearDishesJob extends AbstractJob {

	@Override
	public void run() {
		Cart.removeOvertime(1);
		setMessage("清理点菜单的缓存");
	}

		
}
