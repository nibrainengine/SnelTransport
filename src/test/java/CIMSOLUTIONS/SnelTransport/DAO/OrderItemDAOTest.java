package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.OrderItem;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class OrderItemDAOTest {

    OrderItemDAO orderItemDAO = Mockito.mock(OrderItemDAO.class);

    @Test
    void getOrderItems() {
        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(new OrderItem());
        when(orderItemDAO.getOrderItems(0,0,0)).thenReturn(orderItems);
        assertTrue(orderItemDAO.getOrderItems(0,0,0).get(0).getClass() == OrderItem.class);
    }
}
