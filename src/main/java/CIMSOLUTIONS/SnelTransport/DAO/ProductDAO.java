package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.DTO.ProductDTO;
import CIMSOLUTIONS.SnelTransport.Models.*;
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

    /**
     * This function fetches a product of a specific orderItem from the database by inserting courierId, scheduleId
     * and an index in the query.
     * @param index the index of a courier schedule order
     * @param courierId identification of a courier
     * @param scheduleId identification of a schedule
     * @return Product object that contains data about a product
     */
    public Product getProduct(int index, int courierId, int scheduleId) throws Exception {
        try {
            String queryProduct =   "SELECT DISTINCT product.id as id, " +
                                        "product.supplierProductId as supplierNumberIdentification, " +
                                        "product.name as name, product.price as price, productSize.name as size " +
                                    "FROM courier, scheduleRoute, address, orderItem, orderStatus, " +
                                        "courierSchedule, product, productSize, category, productCategory " +
                                    "WHERE courier.userId = " + courierId + " " +
                                    "AND courierSchedule.id = " + scheduleId + " " +
                                    "AND courierSchedule.courierId = courier.userId " +
                                    "AND courierSchedule.id = scheduleRoute.courierScheduleId " +
                                    "AND courierSchedule.id = scheduleRoute.courierScheduleId " +
                                    "AND scheduleRoute.indexOrder = " + index + " " +
                                    "AND scheduleRoute.orderItemsId = orderItem.id " +
                                    "AND orderItem.orderStatusId = orderStatus.id " +
                                    "AND orderItem.productId = product.id " +
                                    "AND product.sizeId = productSize.id";
            Product product = jdbcTemplate.query(queryProduct, BeanPropertyRowMapper.newInstance(Product.class)).get(0);
            product.setCategories(getProductCategories(product.getId()));
            return product;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function fetches a product of a specific orderItem from the database by inserting date and an index in the query.
     * @param index the index of a courier schedule order
     * @param scheduleId identification of a schedule
     * @return Product object that contains data about a product
     */
    public Product getProductWithDate(int index, int scheduleId) throws Exception {
        try {
            String queryProduct =   "SELECT DISTINCT product.id as id, " +
                                        "product.supplierProductId as supplierNumberIdentification, " +
                                        "product.name as name, product.price as price, productSize.name as size " +
                                    "FROM courier, scheduleRoute, address, orderItem, orderStatus, " +
                                        "courierSchedule, product, productSize, category, productCategory " +
                                    "WHERE courierSchedule.id = " + scheduleId + " " +
                                    "AND courierSchedule.courierId = courier.userId " +
                                    "AND courierSchedule.id = scheduleRoute.courierScheduleId " +
                                    "AND courierSchedule.id = scheduleRoute.courierScheduleId " +
                                    "AND orderItem.id = " + index + " " +
                                    "AND scheduleRoute.orderItemsId = orderItem.id " +
                                    "AND orderItem.orderStatusId = orderStatus.id " +
                                    "AND orderItem.productId = product.id " +
                                    "AND product.sizeId = productSize.id";
            Product product = jdbcTemplate.query(queryProduct, BeanPropertyRowMapper.newInstance(Product.class)).get(0);
            product.setCategories(getProductCategories(product.getId()));
            return product;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function gets all products that are available for a customer from the database.
     * @return List<ProductDTO> The productDTO object is a simple version of the Product Object with
     * only information the customer has to see. Quantity is added to the ProductDTO.
     */
    public List<ProductDTO> getAllAvailable() throws Exception {
        String query =  "SELECT " +
                            "id, name, price, " +
                            "(SELECT ISNULL(SUM(ISNULL(quantity, 0)), 0) " +
                            "FROM supplierProduct " +
                        "WHERE productId = p.id) as quantity " +
                        "FROM product p";
        List<ProductDTO> products;

        try {
            products = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(ProductDTO.class));
            products.removeIf(p -> p.getQuantity() == 0);
            for (ProductDTO product : products)
            {
                product.setCategories(getProductCategories(product.getId()));
            }
            return products;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * This function gets all the categories of one product
     * @param productId identification of a product
     * @return List<String> categories saved as strings in a list
     */
    private List<String> getProductCategories(int productId) throws Exception {
        String query =  "SELECT c.name " +
                        "FROM productCategory pc " +
                        "INNER JOIN product p on pc.productId = p.id " +
                        "INNER JOIN category c on pc.categoryId = c.id " +
                        "WHERE p.id = " + productId;
        List<String> productCategories;

        try {
            productCategories = jdbcTemplate.queryForList(query, String.class);
            return productCategories;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
