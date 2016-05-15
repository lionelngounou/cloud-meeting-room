package sample.cloudmeetingroom.booking;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import sample.cloudmeetingroom.booking.repository.RoomBookingRepository;

/**
 * @author Lionel Stephane
 */
@SpringBootApplication
public class BookingServiceStarter {
	
    public static void main(String[] args) {
        SpringApplication.run(BookingServiceStarter.class, args);  
    }    
    
    @Bean
    CommandLineRunner bootstrap(final RoomBookingRepository roomBookingRepository){
        return (String[] args) -> {
			roomBookingRepository.init();
		};
    }
}