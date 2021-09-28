package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.class_objects.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product getProduct(int index, int courierId, int scheduleId) {
        try {
            String queryProduct = "select distinct product.id as id, product.supplierProductId as suplierNumberIdentification, product.name as name, product.price as price, productSize.name as size, category.name as categories from courier, scheduleRoute, address, orderItem, orderStatus, courierSchedule, product, productSize, category, productCategory where courier.userId = " + courierId + " and courierSchedule.id = " + scheduleId + " and courierSchedule.courierId = courier.userId and courierSchedule.id = scheduleRoute.courierScheduleId and scheduleRoute.indexOrder = " + index + " and scheduleRoute.orderItemsId = orderItem.id and orderItem.orderStatusId = orderStatus.id and orderItem.productId = product.id and product.sizeId = productSize.id and product.id = productCategory.productId and category.id = productCategory.categoryId";

            Product product;
            product = jdbcTemplate.query(queryProduct, BeanPropertyRowMapper.newInstance(Product.class)).get(0);
            return product;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
}
