package facades;

import java.sql.SQLException;
import java.util.ArrayList;

import DAO.PostgresReportDAO;
import factories.AbstractDAOFactory;
import factories.PostgresDAOFactory;
import models.Report;

public class ReportFacade {
	
	private static ReportFacade reportFacade;
	private PostgresReportDAO reportDao;
	
	private ReportFacade(){
		AbstractDAOFactory daoFactory = AbstractDAOFactory.getFactory(AbstractDAOFactory.POSTGRES_DAO_FACTORY);
		reportDao = (PostgresReportDAO) daoFactory.getDao(PostgresDAOFactory.REPORT_DAO);
	}
	
	public static ReportFacade getInstance(){
		if(reportFacade == null){
			reportFacade = new ReportFacade();
		}
		return reportFacade;
	}
	
	public int insertReport(Report report) throws SQLException{
		 return reportDao.create(report);
	}
	
	public ArrayList<Report> getAllReports() throws SQLException{
		return reportDao.getAll();
	}
	
	
}
