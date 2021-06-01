package com.bds.api;

import java.util.Date;

import com.bds.model.Result;

/**
 * Created by guoyu on 2015/8/24.
 */
public interface IServiceApi {
	// 添加任务

	public Result addTask(String requestId, String url, String uuid, String userId);

	public Result doJob(String url, Long id, String jobId,Long user_id);

	public Result addBalance(Long user_id, Double amount, Integer balance_type, Date validity_time, Long transaction_id,
			Long order_id, String order_no, String remark);

	public Result subtractBalance(Long user_id, Double amount);

}
