package com.plat.acoal.utils;

import java.util.UUID;

public class UUIDUtil {
	
	public static String getUUID(){
		
		return UUID.randomUUID().toString().replace("-", "").substring(0, 9);
	}

		public static Integer getUUIDInOrderId(){
			Integer orderId=UUID.randomUUID().toString().hashCode();
			orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
			return orderId;
		}

	}





