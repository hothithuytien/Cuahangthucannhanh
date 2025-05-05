package BUS;

import dao.StatisticsDAO;
import java.util.List;

public class StatisticsBUS {
    private StatisticsDAO thongKeDAO;

    public StatisticsBUS() {
        thongKeDAO = new StatisticsDAO();
    }

    public List<Object[]> getRevenueBySpecificDate(String date) {
        return thongKeDAO.getRevenueBySpecificDate(date);
    }
    
    // Lọc Top 5 theo Ngày
    public List<Object[]> getTop5ProductsByDay(String ngayLap) throws Exception {
        StatisticsDAO dao = new StatisticsDAO();
        return dao.getTop5ProductsByDay(ngayLap);
    }

    // Lọc Top 5 theo Tháng
    public List<Object[]> getTop5ProductsByMonth(String thangLap) throws Exception {
        StatisticsDAO dao = new StatisticsDAO();
        return dao.getTop5ProductsByMonth(thangLap);
    }
    
    // Lọc theo khoảng thời gian
    public List<Object[]> getRevenueByDateRange(String fromDate, String toDate) {
        return thongKeDAO.getRevenueByDateRange(fromDate, toDate);
    }
}
