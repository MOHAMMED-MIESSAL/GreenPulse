import java.time.Duration;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Map;

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

    public String displayDetailedWeeklyReport() {
        StringBuilder report = new StringBuilder();
        report.append("Weekly Carbon Consumption Report for ").append(name).append(":\n");
        report.append("====================================================\n");

        if (consumptions.isEmpty()) {
            report.append("No consumption data available.\n");
            return report.toString();
        }

        // Liste pour stocker chaque période de consommation avec sa date de début et fin réelles
        List<String> consumptionPeriods = new ArrayList<>();

        for (CarbonConsumption consumption : consumptions) {
            LocalDate startDate = consumption.getStartDateTime().toLocalDate();
            LocalDate endDate = consumption.getEndDateTime().toLocalDate();

            // Diviser la période de consommation en semaines, tout en gardant les dates réelles de début et fin
            while (!startDate.isAfter(endDate)) {
                // Calculer la fin de la semaine courante
                LocalDate weekEnd = startDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

                // Limiter la période au endDate si elle se termine avant la fin de la semaine
                LocalDate periodEndDate = weekEnd.isBefore(endDate) ? weekEnd : endDate;

                // Calculer la consommation pour cette période
                double weeklyConsumption = calculateConsumptionForPeriod(consumption, startDate, periodEndDate);

                // Stocker la consommation avec sa période réelle
                String periodInfo = String.format("%s to %s: %.2f kg CO2", startDate, periodEndDate, weeklyConsumption);
                consumptionPeriods.add(periodInfo);

                // Avancer à la semaine suivante
                startDate = periodEndDate.plusDays(1);
            }
        }

        // Afficher chaque période distincte avec ses dates réelles
        for (String period : consumptionPeriods) {
            report.append("Period: ").append(period).append("\n");
            report.append("====================================================\n");
        }

        return report.toString();
    }

    // Méthode pour calculer la consommation pour une période spécifique
    private double calculateConsumptionForPeriod(CarbonConsumption consumption, LocalDate startDate, LocalDate endDate) {
        long totalDays = Duration.between(consumption.getStartDateTime().toLocalDate().atStartOfDay(), consumption.getEndDateTime().toLocalDate().plusDays(1).atStartOfDay()).toDays();
        long periodDays = Duration.between(startDate.atStartOfDay(), endDate.plusDays(1).atStartOfDay()).toDays();

        return (consumption.getConsumption() * periodDays) / totalDays;
    }
}
