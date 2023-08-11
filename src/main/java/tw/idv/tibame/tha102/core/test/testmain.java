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
		
//		jedis.set("test","Hello testRedis");
//		System.out.println("mykey"+jedis.get("test"));
		
		
		Emp emp =new Emp();
		
			emp.setEmp_id(1);
			emp.setEmp_name("abc");
			emp.setEmp_password("1234");
			
		Emp emp1 =new Emp();
			
		emp1.setEmp_id(2);
		emp1.setEmp_name("bobo");
		emp1.setEmp_password("5678");
	
		
		List<Emp> pens =new ArrayList();
		
		pens.add(emp);
		pens.add(emp1);
		
		String jObject = new JSONObject(emp).toString();
		String jArray  = new JSONArray(pens).toString();
		
		StringBuilder sb =new StringBuilder("testmypen :").append(emp.getEmp_id());
		
		jedis.set(sb.toString(),jObject);
		jedis.set("pens",jArray);
		
		System.out.println(jedis.get(sb.toString()));
		System.out.println(jedis.get("pens"));
		
		
	}
	
}	
