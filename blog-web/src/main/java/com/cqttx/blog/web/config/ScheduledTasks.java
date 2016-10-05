package com.cqttx.blog.web.config;

import org.springframework.scheduling.annotation.Scheduled;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author stc
 * @see 定时任务
 */
//@Component
//@EnableScheduling
public class ScheduledTasks {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	// 每2秒执行一次
	@Scheduled(cron = "0/2 * * * * ?")
	public void scheduler() {
		System.out.println("现在时间：" + dateFormat.format(new Date()));
	}
}
