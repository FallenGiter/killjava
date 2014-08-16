package cache.redis;

import redis.clients.jedis.Jedis;

public class Main {
	Jedis jedis, jedis2;
	
	public Main(){
		jedis = new Jedis("192.168.83.213", 6379);
		jedis2 = new Jedis("192.168.83.213", 6380);
		jedis.set("callme", "maybe1");
		System.out.println(jedis2.get("callme"));
	}
}
