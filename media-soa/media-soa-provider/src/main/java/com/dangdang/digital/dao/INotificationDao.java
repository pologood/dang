package com.dangdang.digital.dao;

import java.util.List;

import com.dangdang.digital.model.Notification;
/**
 * 
 * Description: 
 * All Rights Reserved.
 * @version 1.0  2015年1月10日 上午10:08:42  by 魏嵩（weisong@dangdang.com）创建
 */
public interface INotificationDao extends IBaseDao<Notification>{

	List<Notification> getNotificationListByCustIdsAndDeviceType(Integer appId, List<Long> usingUserIds, List<Integer> deviceTypes);

}
