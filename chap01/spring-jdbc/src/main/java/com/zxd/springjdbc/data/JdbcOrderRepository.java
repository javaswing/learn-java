package com.zxd.springjdbc.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zxd.springjdbc.model.Order;
import com.zxd.springjdbc.model.Taco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private SimpleJdbcInsert orderInserter;
    private SimpleJdbcInsert orderTacoInserter;
    private ObjectMapper objectMapper;


    @Autowired
    public JdbcOrderRepository(JdbcTemplate jdbcTemplate) {
        this.orderInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order")
                .usingGeneratedKeyColumns("id");
        this.orderTacoInserter = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("Taco_Order_Tacos");
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public Order save(Order o) {
        o.setPlacedAt(new Date());
        long orderId = saveOrderDetail(o);
        for (Taco t : o.getTacos()) {
            saveTacoToOrder(t, orderId);
        }
        return o;
    }

    private long saveOrderDetail(Order o) {
        Map<String, Object> values = objectMapper.convertValue(o, Map.class);
        values.put("placedAt", o.getPlacedAt());
        long orderId = orderInserter.executeAndReturnKey(values).longValue();
        return orderId;
    }

    private void saveTacoToOrder(Taco taco, long orderId) {
        Map<String, Object> values = new HashMap<>();
        values.put("tacoOrder", orderId);
        values.put("taco", taco);
        orderTacoInserter.execute(values);
    }
}
