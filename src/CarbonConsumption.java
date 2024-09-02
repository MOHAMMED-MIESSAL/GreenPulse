import java.time.LocalDateTime;

public class CarbonConsumption {
    private LocalDateTime dateTime;
    private double consumption;

    public CarbonConsumption(LocalDateTime dateTime, double consumption) {
        this.dateTime = dateTime;
        this.consumption = consumption;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getConsumption() {
        return consumption;
    }

}
