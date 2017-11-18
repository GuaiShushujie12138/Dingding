package com.guaishushu.dao;

import com.guaishushu.bean.Receiver;

public interface ReceiverDao {
	/* 根据收件人姓名,地址和电话和用户id查找收件人id */
	long findReceiverIdByInfo(Receiver receiver, long user_id);

	/* 向Receiver表中插入数据 */
	void addReceiver(Receiver receiver, long user_id);

	/* 根据receiver_id 查找收件人 */
	Receiver findReceiverById(long receiver_id);
}
