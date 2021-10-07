package CIMSOLUTIONS.SnelTransport.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackageSizeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Fetches all the package size a single courier can carry from the database.
     * @param courierId unique identifier of a courier
     * @return List of strings containing the package size name (groot, middel, klein)
     */
    public List<String> getPackageSizeCourier(int courierId){
        String query = "select productSize.name from productSize, courier, courierProductSize where courierId = "+courierId+" and courierProductSize.courierId = courier.userId and courierProductSize.productSizeId = productSize.id";
        return jdbcTemplate.queryForList(query, String.class);
    }
}
