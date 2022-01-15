package com.heima.item.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heima.item.pojo.Item;
import com.heima.item.pojo.ItemStock;
import com.heima.item.service.IItemService;
import com.heima.item.service.IItemStockService;
import com.heima.item.service.impl.ItemService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Time : 2022/1/15 17:49
 * @Author : YuanMing
 * @File : RedisHandler.java
 * @Software: IntelliJ IDEA
 */
@Component
public class RedisHandler implements InitializingBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IItemStockService stockService;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void afterPropertiesSet() throws Exception {
        // 初始化缓存，缓存预热
        // 1. 查询商品信息
        List<Item> itemList = itemService.list();
        // 2. 放入缓存
        for (Item item : itemList) {
            // 2.1 item序列化为json
            String itemJson = MAPPER.writeValueAsString(item);
            // 2.2 存入redis
            redisTemplate.opsForValue().set("item:id:" + item.getId(), itemJson);
        }
        // 3. 查询商品库存信息
        List<ItemStock> stockList = stockService.list();
        // 4. 放入缓存
        for (ItemStock stock : stockList) {
            // 4.1 stock序列化为json
            String stockJson = MAPPER.writeValueAsString(stock);
            // 4.2 存入redis
            redisTemplate.opsForValue().set("stock:id:" + stock.getId(), stockJson);
        }
    }

    public void saveItem(Item item){
        try {
            String itemJson = MAPPER.writeValueAsString(item);
            redisTemplate.opsForValue().set("item:id:" + item.getId(), itemJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteItemById(Long id){
        redisTemplate.delete("item:id:" + id);
    }
}
