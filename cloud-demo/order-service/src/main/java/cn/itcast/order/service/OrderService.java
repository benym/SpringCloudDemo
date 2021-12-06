package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserClient userClient;

    public Order queryOrderById(Long orderId) {

        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        // 2.用Feign远程调用
        User user = userClient.findById(order.getUserId());
        // 3.封装User到order
        order.setUser(user);
        // 4.返回
        return order;
    }


    public String query(Order order){
//        List<Reason> listReason = XXXMapper.selectDataById(order.getId);
//
//        Map<String,Integer> mapReason = listReason.stream().collect(Collectors.toMap(Reason::getReasonDescribe,Reason::getReasonId));
//
//
//        for (int i = 0; i < listReason.size(); i++) {
//            String reasonDescribe = listReason.get(i).getReasonDescribe();
//            Integer reasonId = listReason.get(i).getReasonId();
//            mapReason.put(reasonDescribe,reasonId);
//        }
//        if(mapReason.containsKey(order.getReasonDescribe())){
//            XXXMapper.insertDataByReason(Reason.getResonId);
//        }else{
//            XXXMapper.saveDataByReason(order);
//        }
//
        return "true";
    }
//    @Autowired
//    private RestTemplate restTemplate;

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        // 2.利用RestTemplate发起Http请求
//        // 2.1.url路径
//        String url = "http://userservice/user/"+order.getUserId();
//        User user = restTemplate.getForObject(url, User.class);
//        // 3.封装User到order
//        order.setUser(user);
//        // 4.返回
//        return order;
//    }

    @SentinelResource("goods")
    public void queryGoods(){
        System.err.println("查询商品");
    }
}
