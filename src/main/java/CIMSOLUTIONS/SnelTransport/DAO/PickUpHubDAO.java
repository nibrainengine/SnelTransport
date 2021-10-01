package CIMSOLUTIONS.SnelTransport.DAO;

import class_objects.Address;
import class_objects.PickUpHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class PickUpHubDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This call returns a list of PickUpHubs with their addresses. This is based on an inner join of the addressId inside the pickuphub table and the primary key inside the address table.
     * To read this list of values as a nested object (Address object inside PickUpHub object), a custom row mapper has been created.
     * @return List<PickUpHub>  A list of PickupHubs outfitted with their matching address.
     */
    public List<PickUpHub> getURLsandAddresses() {
        String query = "SELECT pickUpHub.url, address.street, address.houseNumber,address.zipCode, address.city, address.country, address.latitude, address.longitude FROM pickUpHub INNER JOIN address ON pickUpHub.addressId=address.id WHERE url IS NOT NULL AND isDisabled = 'False'";
        return jdbcTemplate.query(query, new RowMapper<>() {
            @Override
            public PickUpHub mapRow(ResultSet rs, int rowNum) throws SQLException {
                //Query is index based, do not reorganize.
                PickUpHub pickUpHub = new PickUpHub();
                pickUpHub.setUrl(rs.getString(1));
                Address address = new Address();
                address.setStreet((rs.getString(2)));
                address.setHouseNumber(rs.getString(3));
                address.setZipCode(rs.getString(4));
                address.setCity(rs.getString(5));
                address.setCountry(rs.getString(6));
                address.setLatitude(rs.getDouble(7));
                address.setLongitude(rs.getDouble(8));
                pickUpHub.setAddress(address);

                return pickUpHub;
            }
        });

    }

    /**
     * This method inserts a new PickupHub and its address into our database.
     * @param pickupHub a PickUpHub object outfitted with address and URL
     */
    public void postPickupHub(PickUpHub pickupHub) {
        String queryAddress = "INSERT INTO address (street, houseNumber, zipCode, city, country, latitude, longitude) VALUES (?,?,?,?,?,?,?)";
        String queryPickup = "INSERT INTO pickUpHub (addressId, isDisabled, url) VALUES ( (SELECT id FROM address WHERE street=? AND houseNumber=?) ,?,?)";
            jdbcTemplate.update(queryAddress,
                    pickupHub.getAddress().getStreet(),
                    pickupHub.getAddress().getHouseNumber(),
                    pickupHub.getAddress().getZipCode(),
                    pickupHub.getAddress().getCity(),
                    pickupHub.getAddress().getCountry(),
                    pickupHub.getAddress().getLatitude(),
                    pickupHub.getAddress().getLongitude());
            jdbcTemplate.update(queryPickup, pickupHub.getAddress().getStreet(), pickupHub.getAddress().getHouseNumber(), false, pickupHub.getUrl());
    }
}
