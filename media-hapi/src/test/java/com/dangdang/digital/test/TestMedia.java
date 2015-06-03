//package com.dangdang.digital.test;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.dangdang.base.comment.client.api.IBarrageCommentApi;
//import com.dangdang.bookreview.api.IBookReviewApi;
//import com.dangdang.digital.api.ICacheApi;
//import com.dangdang.digital.api.ISystemApi;
//import com.dangdang.digital.service.IStoreUpService;
//
//public class TestMedia {
//	private static ICacheApi cacheApi;
//
//	private static ISystemApi systemApi;
//
//	private static IBookReviewApi bookReviewApi;
//
//	private static RabbitTemplate rabbitTemplate;
//
//	private static IBarrageCommentApi barrageCommentApi;
//	
//
//	private static IStoreUpService  storeUpService;
//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		ApplicationContext cxt = new ClassPathXmlApplicationContext(new String[] { "config/spring_common.xml" });
//		cacheApi = (ICacheApi) cxt.getBean("cacheApi");
//		systemApi = (ISystemApi) cxt.getBean("systemApi");
//		bookReviewApi = (IBookReviewApi) cxt.getBean("bookReviewApi");
//		rabbitTemplate = (RabbitTemplate) cxt.getBean("rabbitTemplate");
//		barrageCommentApi = (IBarrageCommentApi) cxt.getBean("barrageCommentApi");
//		storeUpService = (IStoreUpService) cxt.getBean("storeUpService");
//	}
//
//	@Test
//	public void testCache() throws Exception {
//		List<Long> t = new ArrayList<Long>();
//		t.add(1960000001l);
//		t.add(1960000001l);
//		t.add(1960000002l);
//		System.out.println(storeUpService.getStoreUpObjectIdListByCustIdAndTargetIds(22227971l, "media", "DDXS-P", t));
//
//	}
//
//}
