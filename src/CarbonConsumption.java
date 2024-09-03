import java.time.LocalDateTime;
import java.time.Duration;

public class CarbonConsumption {


    private LocalDateTime StartDateTime;
    private LocalDateTime EndDateTime;
    private double consumption;

    public CarbonConsumption(LocalDateTime StartDateTime, LocalDateTime EndDateTime, double consumption) {
        this.StartDateTime = StartDateTime;
        this.EndDateTime = EndDateTime;
        this.consumption = consumption;
    }

    public LocalDateTime getStartDateTime() {
        return StartDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return EndDateTime;
    }

    public double getConsumption() {
        return consumption;
    }

    // Method to calculate daily consumption
//    public double getDailyConsumption() {
//        // Calculate the duration between start and end dates in days
//        long days = Duration.between(StartDateTime, EndDateTime).toDays();
//        if (days == 0) {
//            days = 1; // avoid division by 0
//        }
//
//        return consumption / days;
//    }
}
