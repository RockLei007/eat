package com.heracles.framework.task.job;

import com.heracles.framework.general.GeneralToken;
import com.heracles.framework.service.account.OperationLogManager;

public class ClearLogJob extends AbstractJob {

	private OperationLogManager operationLog;
	
	@Override
	public void run() {
		operationLog = (OperationLogManager) getAppObject("operationLogManager");
		//默认清理三个月前的数据
		operationLog.deleteLosteEffectiveness(GeneralToken.getClearSpace());
		setMessage("清楚过期日志");
	}

		
}
