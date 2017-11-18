package com.guaishushu.dao;

import java.util.List;
import java.util.Map;

import com.guaishushu.bean.Receiver;
import com.guaishushu.db.SQLHelper;

public class ReceiverDaoImpl implements ReceiverDao {
	private SQLHelper helper;

	@Override
	/* 根据收件人信息查找id */
	public long findReceiverIdByInfo(Receiver receiver, long user_id) {
		long receiver_id = -1;
		helper = new SQLHelper();

		String sql = "select id from d_receiver where name = ? and address = ? and phone = ? and user_id = ?";
		Object[] params = { receiver.getName(), receiver.getAddress(),
				receiver.getPhone(), user_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				receiver_id = Long.parseLong(map.get("ID").toString());
			}
		}

		return receiver_id;
	}

	@Override
	/* 向收件人表中插入数据 */
	public void addReceiver(Receiver receiver, long user_id) {
		helper = new SQLHelper();

		String sql = "insert into d_receiver values (d_receiver_seq.nextVal, ?, ?, ?, ?)";
		Object[] params = { receiver.getName(), receiver.getAddress(),
				receiver.getPhone(), user_id };
		helper.insert(sql, params);
		System.out.println("添加收件人信息成功!");
	}

	@Override
	/* 根据receiver_id查找Receiver */
	public Receiver findReceiverById(long receiver_id) {
		helper = new SQLHelper();
		Receiver receiver = null;

		String sql = "select * from d_receiver where id = ?";
		Long[] params = { receiver_id };
		List<Map<String, Object>> list = helper.query(sql, params);
		Map<String, Object> map = null;

		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				map = list.get(i);

				String name = (String) map.get("NAME");
				String address = (String) map.get("ADDRESS");
				String phone = (String) map.get("PHONE");

				receiver = new Receiver(receiver_id, name, address, phone);
			}
		}

		return receiver;
	}

}
