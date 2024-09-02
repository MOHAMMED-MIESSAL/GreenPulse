import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.time.temporal.ChronoUnit;


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


    // Show User Carbon consumption records
    public String displayConsumption () {
        StringBuilder message = new StringBuilder();
        message.append("\n").append("Carbon Consumption for : ").append(name).append(" (ID: ").append(id).append(")\n");

        if (consumptions.isEmpty()) {
            message.append("No consumption data available.\n");
        } else {
            for (CarbonConsumption consumption : consumptions) {
                message.append("Date: ").append(consumption.getDateTime())
                        .append(", Consumption: ").append(consumption.getConsumption())
                        .append(" kg CO2\n");
            }
            message.append("Total Consumption: ").append(getTotalConsumption()).append(" kg CO2\n");
        }

        return message.toString();
    }



    public String displayInfosUsers() {
        return " * \n  " + "Name: " + name + " \t Age: " + age + " \t ID: " + id;
    }

}
