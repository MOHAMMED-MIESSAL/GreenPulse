import java.time.LocalDateTime;

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

}
