/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.jugchennai.javamoney.jpa.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Data loader program for TS_STOCK_INFLECTION table
 *
 * @author gshenoy
 */
public class TSStockInflectionDataLoader {

    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DATABASE = "jdbc:derby://localhost:1527/trakstok";
    private Connection conn = null;
    private Statement stmt = null;
    private String dbUser;
    private String dbPassword;
    private String fromDate;
    private String toDate;
    private Calendar toD = null;
    private Calendar fromD = null;
    private Random stockRandom = new Random();
    private Random stockVariationRandom = new Random();
    private Random addOrSubRandom = new Random();
    private Random maxStockVariationRandom = new Random();

    /**
     * Expects to-date and from-date in dd/mm/yyyy format as inputs
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        if (args == null || args.length < 2) {

            System.out.println("Requied toDate (dd/mm/yyyy)");
            System.out.println("Requied fromDate (dd/mm/yyyy)");
            System.out.println("Optional dbUser");
            System.out.println("Optional dbPassword");

            System.exit(0);
        }



        TSStockInflectionDataLoader control = new TSStockInflectionDataLoader();

        control.fromDate = args[0];
        control.toDate = args[1];

        if (args != null && args.length == 4) {
            control.dbUser = args[2];
            control.dbPassword = args[3];
        }

        control.loadDriver();
        control.valAndSetDates();
        control.insertDummyStockInflection();

        System.out.println("-Done-");

    }

    /**
     * load the jdbc driver
     */
    public void loadDriver() {
        try {

            Class.forName(DRIVER);


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TSStockInflectionDataLoader.class.getName()).log(Level.SEVERE, "Error loading jdbc driver", ex);
        }
    }

    /**
     * Find the list of companies and add dummy stock inflection for each hour
     * of the day (between 10am to 5pm) excluding weekends for each day between
     * the to and from date provided as inputs
     *
     */
    public void insertDummyStockInflection() {
        try {
            if (dbUser != null && dbPassword != null) {
                conn = DriverManager.getConnection(DATABASE, dbUser, dbPassword);
            } else {
                conn = DriverManager.getConnection(DATABASE);
            }
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select TS_COMPANY.COMPANYID from TS_COMPANY");

            List<Integer> companies = new ArrayList<>();
            while (rs.next()) {
                companies.add(rs.getInt("COMPANYID"));
            }

            stmt.close();

            insertDummyStockInflection(companies);

        } catch (SQLException ex) {
            Logger.getLogger(TSStockInflectionDataLoader.class.getName()).log(Level.SEVERE, "Error connecting to database", ex);
            System.exit(0);
        }

    }

    private void insertDummyStockInflection(List<Integer> companies) {
        try {
            stmt = conn.createStatement();

            Iterator<Integer> listItr = companies.iterator();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Integer companyID;
            Integer stockValue;
            Integer maxStockVariation = getMaxStockVariation();

            toD.set(Calendar.HOUR_OF_DAY, 0);
            toD.set(Calendar.MINUTE, 0);
            toD.set(Calendar.SECOND, 0);

            while (listItr.hasNext()) {

                companyID = listItr.next();
                stockValue = getStockValue();

                Calendar firstDate = Calendar.getInstance();
                firstDate.setTime(fromD.getTime());

                while (hasNextDate(firstDate)) {

                    int startHour = 10;
                    while (true) {

                        firstDate.set(Calendar.HOUR_OF_DAY, startHour);



                        if (firstDate.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || firstDate.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {


                            firstDate.add(Calendar.DAY_OF_MONTH, 1);
                            // Skipping over weekends
                            continue;
                        }



                        stmt.execute("insert into TS_STOCK_INFLECTION (COMPANYID,DATETIME,AMOUNT) values (" + companyID
                                + ",'" + dateFormat.format(firstDate.getTime()) + "'," + stockValue + ")");



                        startHour++;
                        stockValue = getStockVariation(stockValue, maxStockVariation);

                        if (startHour > 17) {

                            firstDate.add(Calendar.DAY_OF_MONTH, 1);
                            maxStockVariation = getMaxStockVariation();
                            break;
                        }

                    }


                }

            }
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(TSStockInflectionDataLoader.class.getName()).log(Level.SEVERE, "", ex);
            System.exit(0);
        }

    }

    /**
     * Validate dates
     */
    private void valAndSetDates() {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date from = dateFormat.parse(fromDate);
            fromD = Calendar.getInstance();
            fromD.setTime(from);

        } catch (ParseException ex) {
            System.out.println("Invalid from-date " + fromDate + ". Must be in dd/MM/yyyy format");
            System.exit(0);
        }

        try {
            Date to = dateFormat.parse(toDate);
            toD = Calendar.getInstance();
            toD.setTime(to);
            toD.add(Calendar.DAY_OF_MONTH, 1);
        } catch (ParseException ex) {
            System.out.println("Invalid to-date " + toDate + ". Must be in dd/MM/yyyy format");
            System.exit(0);
        }

        if (fromD.after(toD)) {
            System.out.println("To-Date must be after from-Date");
            System.exit(0);
        }

    }

    /**
     * Checks if the current date is before the to-date
     *
     * @param currentDate
     * @return true if the current date is before the to-date
     */
    private boolean hasNextDate(Calendar currentDate) {
        return (currentDate.before(toD));
    }

    /**
     * Generate a random stock value
     *
     * @return random stock value between 1 and 501
     */
    private int getStockValue() {
        return stockRandom.nextInt(500) + 1;
    }

    /**
     * Generate random stock variation for the given stock value
     *
     * @param stockValue value of the stock to which needs to be varied
     * @param maxVariation maximum allowed variation
     * @return random stock variation for the given stock value
     */
    private int getStockVariation(int stockValue, int maxVariation) {
        int variation = stockVariationRandom.nextInt(maxVariation);
        if (stockValue == 0 || isAdd()) {
            return stockValue + variation;
        } else {
            return stockValue - variation;
        }
    }

    /**
     * Generate random choice to add / subtract
     *
     * @return true or false randomly
     */
    private boolean isAdd() {
        return addOrSubRandom.nextBoolean();
    }

    /**
     * Generate a random value for the maximum variation allowed for a stock
     * value between 1 and 11
     *
     * @return random value for the maximum variation allowed for a stock value
     * between 1 and 11
     */
    private int getMaxStockVariation() {
        return maxStockVariationRandom.nextInt(10) + 1;
    }
}
