public class FinancialForecasting {
    static double calculateFutureValue(double presentValue, double growthRate, int years) {
        if (years == 0) {
            return presentValue;
        }
        return calculateFutureValue(presentValue * (1 + growthRate), growthRate, years - 1);
    }

    public static void main(String[] args) {
        double currentRevenue = 500000;
        double annualGrowthRate = 0.08;
        int forecastYears = 5;
        double projectedRevenue = calculateFutureValue(currentRevenue, annualGrowthRate, forecastYears);
        System.out.println("Projected revenue after " + forecastYears + " years: " + projectedRevenue);
    }
}
