//package cn.muye.cache;
//
//import org.redisson.api.RAtomicLong;
//import org.redisson.api.RFuture;
//import org.redisson.api.RLiveObjectService;
//import org.redisson.api.RedissonClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ExecutionException;
//
///**
// * Created by Martin on 2016/9/5.
// */
//public final class RedissonUtil {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(RedissonUtil.class);
//
//    @Value("${devCenter.redisRedissonCache}")
//    private String REDISSON_CACHE;
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    public CommonCache commonCache() {
//        RLiveObjectService liveObjectService = redissonClient.getLiveObjectService();
//        return liveObjectService.getOrCreate(CommonCache.class, REDISSON_CACHE);
//    }
//
//    /**
//     *  生成自增长的主键，支持批量
//     * @param client
//     * @param tableName 自增id管理RAtomicLong对象名称
//     * @param idCount 要申请多少个id
//     * @return
//     */
//    public static Long incrementKey(RedissonClient client, String tableName, long idCount) {
//        RAtomicLong at = client.getAtomicLong(tableName);
//        RFuture<Long> finalKey = at.addAndGetAsync(idCount);
//        Long finalKeyLong = 0L;
//        try {
//            finalKeyLong = finalKey.get();
//        } catch (InterruptedException e) {
//            LOGGER.error(e.getMessage(), e);
//        } catch (ExecutionException e) {
//            LOGGER.error(e.getMessage(),e);
//        }
//
//        return finalKeyLong;
//    }
//
//}
