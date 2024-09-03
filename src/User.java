import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String id;
    private String name;
    private int age;
    private List<CarbonConsumption> consumptions;

    public User(String name, int age, String id) {
        this.name = name;
        this.age = age;
        this.id = id;
        this.consumptions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void addConsumption(CarbonConsumption consumption) {
        this.consumptions.add(consumption);
    }

    public double getTotalConsumption() {
        return consumptions.stream().mapToDouble(CarbonConsumption::getConsumption).sum();
    }

    public String displayTotalConsumption() {
        StringBuilder message = new StringBuilder();
        message.append("\n").append("Carbon Consumption for : ").append(name).append("\n");
        message.append("Total: ").append(getTotalConsumption()).append(" kg CO2\n");
        return message.toString();
    }

    public String displayConsumption() {
        StringBuilder message = new StringBuilder();
        message.append("\n").append("Carbon Consumption for : ").append(name).append(" (ID: ").append(id).append(")\n");

        if (consumptions.isEmpty()) {
            message.append("No consumption data available.\n");
        } else {
            for (CarbonConsumption consumption : consumptions) {
                message.append(" Start Date: ").append(consumption.getStartDateTime())
                        .append(" <---> End Date: ").append(consumption.getEndDateTime()).append("\n")
                        .append(" Consumption: ").append(consumption.getConsumption())
                        .append(" kg CO2\n").append("\n");
            }
            // message.append(" Total Consumption: ").append(getTotalConsumption()).append(" kg CO2\n");
        }

        return message.toString();
    }

    public String displayInfosUsers() {
        return " * \n  " + "Name: " + name + " \t Age: " + age + " \t ID: " + id;
    }

    public long getTotalDurationInDays() {
        long totalDurationInDays = 0;

        for (CarbonConsumption consumption : consumptions) {
            long durationInDays = Duration.between(consumption.getStartDateTime(), consumption.getEndDateTime()).toDays();
            totalDurationInDays += durationInDays;
        }

        return totalDurationInDays;
    }

    public double getAverageDailyConsumption() {
        long totalDurationInDays = getTotalDurationInDays();

        if (totalDurationInDays == 0) {
            return getTotalConsumption();
        }

        return getTotalConsumption() / totalDurationInDays;
    }

    public String displayAverageDailyConsumption() {
        double averageDailyConsumption = getAverageDailyConsumption();
        long totalDurationInDays = getTotalDurationInDays();
        double totalConsumption = getTotalConsumption();

        return "Total duration of all consumption periods: " + totalDurationInDays + " days\n" +
                "Total consumption: " + totalConsumption + " kg CO2\n" +
                "Average daily consumption: " + averageDailyConsumption + " kg CO2/day\n";
    }
}
