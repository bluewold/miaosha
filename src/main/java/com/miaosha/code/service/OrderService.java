package com.miaosha.code.service;

import java.util.Date;

import com.miaosha.code.dao.OrderDao;
import com.miaosha.code.domain.MiaoshaOrder;
import com.miaosha.code.domain.MiaoshaUser;
import com.miaosha.code.domain.OrderInfo;
import com.miaosha.code.redis.OrderKey;
import com.miaosha.code.redis.RedisService;
import com.miaosha.code.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class OrderService {
	
	@Autowired
	OrderDao orderDao;
	
	@Autowired
	RedisService redisService;
	
	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
		//return orderDao.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
		return redisService.get(OrderKey.getMiaoshaOrderByUidGid, ""+userId+"_"+goodsId, MiaoshaOrder.class);
	}
	
	public OrderInfo getOrderById(long orderId) {
		return orderDao.getOrderById(orderId);
	}
	

	@Transactional
	public OrderInfo createOrder(MiaoshaUser user, GoodsVo goods) {
		try {
			OrderInfo orderInfo = new OrderInfo();
			orderInfo.setCreateDate(new Date());
			orderInfo.setDeliveryAddrId(0L);
			orderInfo.setGoodsCount(1);
			orderInfo.setGoodsId(goods.getId());
			orderInfo.setGoodsName(goods.getGoodsName());
			orderInfo.setGoodsPrice(goods.getMiaoshaPrice());
			orderInfo.setOrderChannel(1);
			orderInfo.setStatus(0);
			orderInfo.setUserId(user.getId());
			orderDao.insert(orderInfo);
			MiaoshaOrder miaoshaOrder = new MiaoshaOrder();
			miaoshaOrder.setGoodsId(goods.getId());
			miaoshaOrder.setOrderId(orderInfo.getId());
			miaoshaOrder.setUserId(user.getId());
			orderDao.insertMiaoshaOrder(miaoshaOrder);

			redisService.set(OrderKey.getMiaoshaOrderByUidGid, "" + user.getId() + "_" + goods.getId(), miaoshaOrder);

			return orderInfo;
		}
		catch(Exception e){
			return null;
		}
	}

	public void deleteOrders() {
		orderDao.deleteOrders();
		orderDao.deleteMiaoshaOrders();
	}

}
