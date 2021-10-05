package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.OrderItemDAO;
import CIMSOLUTIONS.SnelTransport.Models.OrderItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderItemDAOTest {

    OrderItemDAO orderItemDAO = Mockito.mock(OrderItemDAO.class);

    @Test
    void getOrderItems() throws Exception {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());
        when(orderItemDAO.getOrderItems(0,0,0)).thenReturn(orderItems);
        assertSame(orderItemDAO.getOrderItems(0, 0, 0).get(0).getClass(), OrderItem.class);
    }

    @Test
    void getOrderItemsDate() throws Exception {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());
        when(orderItemDAO.getOrderItemsWithDate(0,0)).thenReturn(orderItems);
        assertSame(orderItemDAO.getOrderItemsWithDate(0, 0).get(0).getClass(), OrderItem.class);
    }
}
