import ie.atu.week9.Passenger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PassengerTest {
    static Passenger passenger;

    @BeforeAll
    static void setup(){
        passenger = new Passenger("Mr", "Meike", "e Meike");
    }

    @Test
    void ConstructorSuccess(){
        assertEquals("Mr", passenger.getTitle());
        assertEquals("Meike", passenger.getFirstName());
        assertEquals("e Meike", passenger.getLastName());
    }

    @Test
    void testTitleSuccess(){
        assertEquals("Mr", passenger.getTitle());
        Passenger passenger1 = new Passenger("Ms", "Meike", "e Meike");
        assertEquals("Ms", passenger1.getTitle());
        Passenger passenger2 = new Passenger("Mrs", "Meike", "e Meike");
        assertEquals("Mrs", passenger2.getTitle());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Mr", "Mrs", "Ms"})
    void testTitleSuccessAlso(String title){
        Passenger passenger3 = new Passenger(title, "Meike", "e Meike");
    }
    @Test
    void testTitleFail(){
        assertThrows(IllegalArgumentException.class, ()-> new Passenger("Not Mr", "Meike", "e Meike"));
        assertThrows(IllegalArgumentException.class, ()-> new Passenger("", "Meike", "e Meike"));
    }

    @Test
    void testShortFirstName(){
        Exception e =assertThrows(IllegalArgumentException.class, ()-> {
            new Passenger("Mr" , "Me", "e Meike");
        });
        assertEquals("Invalid First Name Length", e.getMessage());
    }

    @Test
    void testShortLastName(){
        Exception e = assertThrows(IllegalArgumentException.class, ()-> {
            new Passenger("Mr", "Meike", "e");
        });
        assertEquals("Invalid Last Name Length", e.getMessage());
    }
}
