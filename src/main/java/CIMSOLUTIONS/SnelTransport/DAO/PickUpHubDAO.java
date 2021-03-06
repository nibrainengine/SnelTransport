package CIMSOLUTIONS.SnelTransport.DAO;

import CIMSOLUTIONS.SnelTransport.DTO.PickUpAPIDTO;
import CIMSOLUTIONS.SnelTransport.DTO.PickupDataDTO;
import CIMSOLUTIONS.SnelTransport.Models.Address;
import CIMSOLUTIONS.SnelTransport.Models.PickUpHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PickUpHubDAO {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleJdbcInsert;


    @Autowired
    public void setInjectedBean(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * This call returns a list of PickUpHubs with their addresses. This is based on an inner join of the addressId inside the pickuphub table and the primary key inside the address table.
     * To read this list of values as a nested object (Address object inside PickUpHub object), a custom row mapper has been created.
     *
     * @return List<PickUpHub>  A list of PickupHubs outfitted with their matching address.
     */
    public List<PickUpHub> getURLsandAddresses() {
        String query = "SELECT pickUpHub.url, address.street, address.houseNumber,address.zipCode, address.city, address.country, address.latitude, address.longitude, address.id FROM pickUpHub INNER JOIN address ON pickUpHub.addressId=address.id WHERE url IS NOT NULL AND isDisabled = 'False'";
        return jdbcTemplate.query(query, (rs, rowNum) -> {
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
            address.setId(rs.getInt(9));
            pickUpHub.setAddress(address);

            return pickUpHub;
        });

    }

    /**
     * This method returns a list of all the Pickup hubs including their address as PickUpAPI Data Transfer Objects.
     * There are no arguments for this method. It returns all, regardless of disabled variable or the lackter of a URL.
     * @return List<PickUpAPIDTO> A list containing PickUpAPIDTO objects filled with an id, url, address and wether they are disabled.
     */
    public List<PickUpAPIDTO> getAPIs(){
        String query ="SELECT pickUpHub.id, pickUpHub.url,  address.street, address.houseNumber,address.zipCode, address.city, address.country, address.latitude, address.longitude, address.id, pickUpHub.isDisabled FROM pickUpHub INNER JOIN address ON pickUpHub.addressId=address.id";
        return jdbcTemplate.query(query, (resultSet, i) -> {
            PickUpAPIDTO dto = new PickUpAPIDTO();
            dto.setId(resultSet.getInt(1));
            dto.setUrl(resultSet.getString(2));
            dto.setAddress( new Address(
                    resultSet.getString(3), //Street
                    resultSet.getString(4), //houseNumber
                    resultSet.getString(5), //zipCode
                    resultSet.getString(6), //city
                    resultSet.getString(7), //country
                    resultSet.getDouble(8), //latitude
                    resultSet.getDouble(9), //longitude
                    resultSet.getInt(10))); //id
            dto.setDisabled(resultSet.getBoolean(11));
            return dto;
        });
    }

    /**
     * This method inserts a new PickupHub and its address into our database.
     *
     * @param pickupHub a PickUpHub object outfitted with address and URL
     */
    public void postPickupHub(PickUpHub pickupHub) throws Exception {
        try {
            simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate.getDataSource()).withTableName("address").usingGeneratedKeyColumns("id");
            Map<String, Object> parameters = new HashMap<>(7);
            parameters.put("street", pickupHub.getAddress().getStreet());
            parameters.put("houseNumber", pickupHub.getAddress().getHouseNumber());
            parameters.put("zipCode", pickupHub.getAddress().getZipCode());
            parameters.put("city", pickupHub.getAddress().getCity());
            parameters.put("country", pickupHub.getAddress().getCountry());
            parameters.put("latitude", pickupHub.getAddress().getLatitude());
            parameters.put("longitude", pickupHub.getAddress().getLongitude());
            Number addressId = simpleJdbcInsert.executeAndReturnKey(parameters);
            String queryPickup = "INSERT INTO pickUpHub (addressId, isDisabled, url) VALUES (?,?,?)";
            jdbcTemplate.update(queryPickup, addressId, false, pickupHub.getUrl());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Swaps the value of isDisabled from true to false, or the other way around.
     * Works both ways with the same query.
     * @param id    The primary key of the PickUpHub
     * @return PickUpHub    PickUpHub object with the newly set value
     */
    public PickUpHub enableDisablePickup(int id){
        String query = "UPDATE pickUpHub SET isDisabled = 1 - isDisabled WHERE id=?";
        jdbcTemplate.update(query, id);
        return getPickup(id);
    }

    /**
     * Query to obtain a single PickUpHub by its ID
     * @param id    The primary key of the PickUpHub
     * @return PickUpHub   PickUpHub object
     */
    public PickUpHub getPickup(int id){
        String query ="SELECT pickUpHub.id, pickUpHub.url,  address.street, address.houseNumber,address.zipCode, address.city, address.country, address.latitude, address.longitude, address.id, pickUpHub.isDisabled FROM pickUpHub INNER JOIN address ON pickUpHub.addressId=address.id WHERE pickUpHub.id = " + id;
        List<PickUpHub> tempHolder = jdbcTemplate.query(query, (resultSet, i) -> {
            PickUpHub pickUpHub = new PickUpHub();
            pickUpHub.setId(resultSet.getInt(1));
            pickUpHub.setUrl(resultSet.getString(2));
            pickUpHub.setAddress(new Address(
                    resultSet.getString(3), //Street
                    resultSet.getString(4), //houseNumber
                    resultSet.getString(5), //zipCode
                    resultSet.getString(6), //city
                    resultSet.getString(7), //country
                    resultSet.getDouble(8), //latitude
                    resultSet.getDouble(9), //longitude
                    resultSet.getInt(10))); //id
            pickUpHub.setDisabled(resultSet.getBoolean(11));
            return pickUpHub;
        });
        //The query can only return one item but our row mapper does not know that.
        return tempHolder.get(0);
    }
}
