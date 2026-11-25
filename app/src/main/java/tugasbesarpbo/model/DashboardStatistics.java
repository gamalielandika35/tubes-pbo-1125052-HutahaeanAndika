package tugasbesarpbo.model;

public class DashboardStatistics {

    private int totalServiceOrders;
    private int completedOrders;
    private int inProgress;
    private int totalCustomers;

    public DashboardStatistics(int totalServiceOrders, int completedOrders, int inProgress, int totalCustomers) {

        this.totalServiceOrders = totalServiceOrders;
        this.completedOrders = completedOrders;
        this.inProgress = inProgress;
        this.totalCustomers = totalCustomers;
    }

    public int getTotalServiceOrders() {
        return totalServiceOrders;
    }

    public int getCompletedOrders() {
        return completedOrders;
    }

    public int getInProgress() {
        return inProgress;
    }

    public int getTotalCustomers() {
        return totalCustomers;
    }
}