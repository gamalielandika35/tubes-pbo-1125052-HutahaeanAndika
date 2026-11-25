package tugasbesarpbo.service;

import tugasbesarpbo.model.DashboardStatistics;
import tugasbesarpbo.repository.DashboardRepository;

import java.sql.SQLException;

public class DashboardService {

    private DashboardRepository dashboardRepository;

    public DashboardService() {
        dashboardRepository = new DashboardRepository();
    }

    public DashboardStatistics getStatistics() throws SQLException {
        return dashboardRepository.getStatistics();
    }
}