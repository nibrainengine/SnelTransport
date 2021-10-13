package CIMSOLUTIONS.SnelTransport.DAOTests;

import CIMSOLUTIONS.SnelTransport.DAO.CourierAvailabilityDAO;
import CIMSOLUTIONS.SnelTransport.Models.AvailablePeriod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@JdbcTest
@Sql({"classpath:schema.sql", "classpath:test-data.sql"})
public class CourierAvailabilityDAOTest {
    @Mock
    private CourierAvailabilityDAO availabilityDAO;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private CourierAvailabilityDAO availabilityDatabaseDAO;

    @BeforeEach
    void beforeEach(){
        availabilityDatabaseDAO = new CourierAvailabilityDAO();
        availabilityDatabaseDAO.setInjectedBean(this.jdbcTemplate);
    }

    @Test
    void get() throws Exception {
        List<AvailablePeriod> availablePeriods = Collections.singletonList(new AvailablePeriod());
        int courierId = 1;

        when(availabilityDAO.get(courierId)).thenReturn(availablePeriods);
        assertEquals(1, availabilityDAO.get(courierId).size());
    }

    @Test
    void insert() throws Exception {
        AvailablePeriod availablePeriod = new AvailablePeriod();
        availablePeriod.setId(1);
        availablePeriod.setCourierId(1);
        availablePeriod.setStartTime(new Date());
        availablePeriod.setEndTime(new Date());

        when(availabilityDAO.getId(availablePeriod.getCourierId(), availablePeriod.getStartTime(), availablePeriod.getEndTime())).thenReturn(availablePeriod.getId());
        when(availabilityDAO.insert(availablePeriod)).thenReturn(availablePeriod.getId());
        assertEquals(availablePeriod.getId(), availabilityDAO.insert(availablePeriod));
    }

    // This is the only test needed for the approval yet it is not possible
    // The table uses columns that are SQL keywords (start, end)
    // These are handled differently between the local db and azure
    // Should wait until hibernate
//    @Test
//    void approve() throws Exception{
//        assertEquals(true, availabilityDatabaseDAO.approve(1).getApproved());
//    }


}
