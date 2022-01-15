package com.heima.item.canal;

import com.github.benmanes.caffeine.cache.Cache;
import com.heima.item.config.RedisHandler;
import com.heima.item.pojo.Item;
import com.heima.item.pojo.ItemStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @Time : 2022/1/15 20:26
 * @Author : YuanMing
 * @File : itemHandler.java
 * @Software: IntelliJ IDEA
 */
@CanalTable("tb_item")
@Component
public class itemHandler implements EntryHandler<Item> {

    @Autowired
    private RedisHandler redisHandler;

    @Autowired
    private Cache<Long, Item> itemCache;

    @Autowired
    private Cache<Long, ItemStock> stockCache;

    @Override
    public void insert(Item item) {
        // 写数据到JVM进程缓存
        itemCache.put(item.getId(), item);
        // 写数据到redis
        redisHandler.saveItem(item);
    }

    @Override
    public void update(Item before, Item after) {
        // 写数据到JVM进程缓存
        itemCache.put(after.getId(), after);
        // 写数据到redis
        redisHandler.saveItem(after);
    }

    @Override
    public void delete(Item item) {
        // 删除数据到JVM进程缓存
        itemCache.invalidate(item.getId());
        // 删除数据到redis
        redisHandler.deleteItemById(item.getId());

    }
}
