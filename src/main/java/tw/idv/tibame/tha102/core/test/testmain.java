package tw.idv.tibame.tha102.core.test;

import static tw.idv.tibame.tha102.core.util.CommonMysql.LOCALHOST;
import static tw.idv.tibame.tha102.core.util.CommonMysql.PORT;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import tw.idv.tibame.tha102.web.emp.vo.Emp;

public class testmain {
	public static void main(String[] args) {
		Jedis jedis =new Jedis(LOCALHOST,PORT);
		
		String a ="abc1";
		
		String strid =a.substring(a.length()-1);
		Integer  num =0;
		System.out.println(strid);
//		
//		List<Integer>  numIn =new ArrayList();
//			numIn.add(1);
//			numIn.add(2);
//			numIn.add(3);
//			numIn.add(4);
//			numIn.add(5);
//			
//		for(Integer ii : numIn) {
//			num +=ii;
//		}(Math.random()+100)*1000; 
		
		Integer TrkNum =Double.valueOf(Math.random()*1000000).intValue();
		
		
		System.out.println(TrkNum);
		
		
		

//		jedis.set("test","Hello testRedis");
//		System.out.println("mykey"+jedis.get("test"));
		
//		
//		Emp emp =new Emp();
//		
//			emp.setEmpId(1);
//			emp.setEmpName("abc");
//			emp.setEmpPassword("1234");
//			
//		Emp emp1 =new Emp();
//			
//		emp1.setEmpId(2);
//		emp1.setEmpName("bobo");
//		emp1.setEmpPassword("5678");
//	
//		
//		List<Emp> pens =new ArrayList();
//		
//		pens.add(emp);
//		pens.add(emp1);
//		
//		String jObject = new JSONObject(emp).toString();
//		String jArray  = new JSONArray(pens).toString();
//		
//		StringBuilder sb =new StringBuilder("testmypen :").append(emp.getEmpId());
//		
//		jedis.set(sb.toString(),jObject);
//		jedis.set("pens",jArray);
//		
//		System.out.println(jedis.get(sb.toString()));
//		System.out.println(jedis.get("pens"));
		
		
		
		
		
	
				
				
		
	}
	
}	

 class test{
	
	
	
	
	
	
}