package com.miviekart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.miviekart.dao.IOrdersRepository;
import com.miviekart.dto.OrderItemData;
import com.miviekart.dto.OrdersData;
import com.miviekart.model.Orders;

@Service
public class OrdersService implements IOrderService {
	@Autowired
	private IOrdersRepository ordersRepository;

	private Orders getOrdersEntity(OrdersData ordersData) {
		Orders orders = new Orders();
		orders.setOrderId(ordersData.getOrderId());
		orders.setCustomer(ordersData.getCustomer());
		orders.setOrderItems(ordersData.getOrderItems());
		orders.setTimestamp(ordersData.getTimestamp());

		return orders;

	}

	private OrdersData getOrdersData(Orders orders) {
		OrdersData ordersData = new OrdersData();
		ordersData.setOrderId(orders.getOrderId());
		ordersData.setCustomer(orders.getCustomer());
		ordersData.setTimestamp(orders.getTimestamp());
		ordersData.setOrderItems(orders.getOrderItems());
		return ordersData;

	}

	@Override
	public List<OrdersData> findAll() {

		List<OrdersData> ordersDataList = new ArrayList<>();
		List<Orders> orders = ordersRepository.findAll();
		orders.forEach(orders1 -> {
			ordersDataList.add(getOrdersData(orders1));
		});

		return ordersDataList;
	}

	@Override
	public OrdersData findById(int id) {
		Optional<Orders> orderOptional = ordersRepository.findById(id);
		if (orderOptional == null) {
			new EntityNotFoundException("orders not Found");

		}
		return getOrdersData(orderOptional.get());
	}

	@Override
	public OrdersData create(OrdersData ordersData) {
		Orders orders = getOrdersEntity(ordersData);
		return getOrdersData(ordersRepository.save(orders));

	}

	@Override
	public boolean delete(int id) {
		Orders orders = ordersRepository.findById(id).get();
		if (orders != null) {
			ordersRepository.deleteById(id);
			return true;

		} else {
			return false;
		}

	}

	public OrdersData update(OrdersData ordersData, int orderId) {
		Orders orders = ordersRepository.findById(orderId).get();
		if (orders != null) {

			orders.setCustomer(ordersData.getCustomer());
			orders.setOrderItems(ordersData.getOrderItems());
			orders.setTimestamp(ordersData.getTimestamp());
			ordersRepository.save(orders);
			return getOrdersData(orders);
		} else {
			return null;
		}

	}

}