package mvc.other.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by k on 2018/4/1.
 */
public class JedisUtil {
    private static String	hostName	= "119.28.86.177";
    private static int		port		= 6379;
    private static String	password	= "wpy@123";

    private static int	MaxTotal						= 300;
    private static int	MaxIdle							= 50;
    private static int	MinIdle							= 8;
    private static int	MaxWaitMillis					= 10000;
    private static int	TimeBetweenEvictionRunsMillis	= 30000;
    private static int	NumTestsPerEvictionRun			= 10;
    private static int	MinEvictableIdleTimeMillis		= 60000;
    private static int	TIMEOUT							= 10000;

    private static JedisPool jedisPool = null;

    static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(MaxTotal);
            config.setMaxIdle(MaxIdle);
            config.setMinIdle(MinIdle);// 设置最小空闲数
            config.setMaxWaitMillis(MaxWaitMillis);
            config.setTestOnBorrow(true);
            config.setTestOnReturn(true);
            // Idle时进行连接扫描
            config.setTestWhileIdle(true);
            // 表示idle object evitor两次扫描之间要sleep的毫秒数
            config.setTimeBetweenEvictionRunsMillis(TimeBetweenEvictionRunsMillis);
            // 表示idle object evitor每次扫描的最多的对象数
            config.setNumTestsPerEvictionRun(NumTestsPerEvictionRun);
            // 表示一个对象至少停留在idle状态的最短时间，然后才能被idle object
            // evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
            config.setMinEvictableIdleTimeMillis(MinEvictableIdleTimeMillis);

            jedisPool = new JedisPool(config, hostName, port, TIMEOUT, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis jedis = jedisPool.getResource();
                return jedis;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void returnResource( final Jedis jedis ) {
        if (jedis != null) {
            jedisPool.close();
        }
    }

    public static void main(String[] args) {
       getJedis();
    }
}
