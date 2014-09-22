package com.heracles.eat.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.heracles.eat.entity.account.Food;
import com.heracles.framework.tools.Datetime;
import com.heracles.framework.tools.Unit;

public class Cart {

	private static Map<Long, List<Order>> cartMap = new HashMap<Long, List<Order>>();
	private static Map<Long, Long> datetimeMap = new HashMap<Long, Long>();
	private static Map<Long, Double> totalMap = new HashMap<Long, Double>();
	
	public static void addCart(Long tableId, Order order){
		
		List<Order> orderList = cartMap.get(tableId);
		if (orderList != null && orderList.size() > 0){
			orderList = addList(orderList, order);
		}else{
			orderList = new ArrayList<Order>();
			orderList.add(order);
		}
		Double money = totalMap.get(tableId);
		if (money != null && money > 0){
			money = money + (order.getPrice() * order.getNumber());
		}else{
			money = order.getPrice() * order.getNumber();
		}
		
		cartMap.put(tableId, orderList);
		datetimeMap.put(tableId, order.getDatetime());
		totalMap.put(tableId, money);
	}
	
	public static List<Order> getOrderListFormCart(Long tableId){
		return cartMap.get(tableId);
	}
	
	public static Long getDatetimeFormCart(Long tableId){
		return datetimeMap.get(tableId);
	}
	
	public static Double getTotalFormCart(Long tableId){
		return totalMap.get(tableId);
	}
	
	public static String removeOrder(Long tableId, Long foodId, int type, Double number){
		List<Order> orderList = getOrderListFormCart(tableId);
		String result = "";
		if (orderList != null && orderList.size() > 0){
			Double money = totalMap.get(tableId);
			Iterator<Order> iter = orderList.iterator();
			while (iter.hasNext()){
				Order order = null;
				try {
					 order = iter.next();
				} catch(Exception e){
					break;
				}
				if (order != null && order.getFoodId().equals(foodId)){
					if (type == Food.PORTION_TYPE){
						if (order.getNumber() >= number){
							if (order.getNumber() > 1){
								order.setNumber(order.getNumber() - 1);
							}else
								iter.remove();
							money = money - (order.getPrice() * number);
							result = "成功减少'"+order.getName()+"'"+number+"个";
						}else
							result = "您减少的菜的数量大于已经点菜的数量，请重新输入！";
					}else{
						iter.remove();
						money = money - (order.getPrice() * order.getNumber());
						result = "成功减少'"+order.getName()+"'"+order.getNumber()+"个";
					}
					totalMap.put(tableId, money); 
				}
			}
			cartMap.put(tableId, orderList);
		}
		return result;
	}
	
	public static void removeCart(Long tableId){
		cartMap.remove(tableId);
		datetimeMap.remove(tableId);
		totalMap.remove(tableId);
	}
	
	public static void removeOvertime(int hour){
		Set<Long> key = datetimeMap.keySet();
		Long date = Datetime.getLastTime(hour, 0, 0);
		Long orderDate = 0L;
		Iterator<Long> it = key.iterator();
        while (it.hasNext()) {
        	Long tableId = it.next();
        	orderDate = datetimeMap.get(tableId);
        	if (orderDate <= date){
        		removeCart(tableId);
        	}
        }
	}

	public static int getSize(){
		return cartMap.size();
	}
	
	public static int getFoodCount(Long tableId){
		List<Order> orderList = cartMap.get(tableId);
		if (Unit.isNotNull(orderList)){
			return orderList.size();
		}else
			return 0;
	}
	
	public static List<Order> addList(List<Order> list, Order order){
		boolean repeat = false;
		for(Order o : list){
			if (o.getFoodId().equals(order.getFoodId())){
				o.setNumber(o.getNumber()+ order.getNumber());
				o.setDatetime(System.currentTimeMillis());
				repeat = true;
			}
		}
		order.setDatetime(System.currentTimeMillis());
		if (repeat == false) list.add(order); 
		return list;
	}
	
}
