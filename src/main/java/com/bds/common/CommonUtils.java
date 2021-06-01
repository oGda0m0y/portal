package com.bds.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class CommonUtils {

    public static String getOrderNo(Long userId){
	
	String uid = userId+"";
	for(int i=uid.length();i<9;i++){
	    uid = "0"+uid;
	}
	DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
	String d = df.format(new Date());
	uid = "D"+d+"U"+uid;
	
	UUID id = UUID.randomUUID();
	uid = uid+"R"+id.toString().replaceAll("-","").substring(0, 16);
	return uid;
    }
    
    public static void main(String args[]){
	for(long i=0;i<101;i++){
        	String order_no = getOrderNo(i);
        	System.out.println(order_no);
	}
    }
    
}
