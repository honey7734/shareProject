package com.jsp.controller;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import com.jsp.action.Action;
import com.jsp.context.ApplicationContext;

public class HandlerMapper {
	
	private Map<String, Action> commandMap 
						= new HashMap<String, Action>();
	
	static final String path = "com/jsp/properties/url";
	
	public HandlerMapper() throws Exception {
		this(path);
	}
	
	public HandlerMapper(String path) throws Exception {		
		ResourceBundle rbHome = ResourceBundle.getBundle(path);
		Set<String> actionSetHome = rbHome.keySet(); // uri set
		Iterator<String> it = actionSetHome.iterator();
		
		while(it.hasNext()){
			
			String command = it.next(); //key -> url
			
			String actionClassName = rbHome.getString(command);
			
			try {
				Class<?> actionClass = Class.forName(actionClassName);
				Action commandAction = (Action)actionClass.newInstance();
				
				//?��존주?��(service, dao.......)
				//?��존성 ?��?�� �? 조립
				Method[] methods = actionClass.getMethods();
				for (Method method : methods) {
					if (method.getName().indexOf("set")==0) {
						/* set메소?�� ?��?��메터 ???�� ?��?��
						 * 
						 * String paramType=method.getParameterTypes()[0].getName();
						 * paramType=paramType.substring(paramType.lastIndexOf(".")+1);
						 * 
						 * paramType=(paramType.charAt(0) + "").toLowerCase() + paramType.substring(1);
						 */
						String paramName = method.getName().substring(3);
						paramName=paramName.substring(0,1).toLowerCase()
								 +paramName.substring(1);
								
						method.invoke(commandAction,
								ApplicationContext.getApplicationContext().get(paramName));
						
						System.out.println("[HandlerMapper:invoke]"
								+actionClassName+":"
								+ApplicationContext.getApplicationContext().get(paramName));
					}
					
				}
				
				commandMap.put(command, commandAction);
				System.out.println("[HandlerMapper]"+command+":"+commandAction +" �? �?비되?��?��?��?��.");
				
			}catch (ClassNotFoundException e){
				System.out.println("[HandlerMapper]"+actionClassName 
												+ "?�� 존재?���? ?��?��?��?��.");
				throw e;
			} catch (InstantiationException e) {
				System.out.println("[HandlerMapper]"+actionClassName 
												+ "?��?��?��?���? ?��?��?�� ?�� ?��?��?��?��.");
				throw e;
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
		}
		
	}
	
	public Action getAction(String url) {
		Action action = commandMap.get(url);
		return action;
	}
}









