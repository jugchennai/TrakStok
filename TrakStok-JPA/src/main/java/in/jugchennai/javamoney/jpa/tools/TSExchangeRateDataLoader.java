/*
 * Copyright 2013 JUGChennai.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.jugchennai.javamoney.jpa.tools;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sathishkumarkk
 */
public class TSExchangeRateDataLoader {

    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String DATABASE = "jdbc:derby://localhost:1527/trakstok";
    public static final String SOURCE_CURRENCY_COCE = "EUR";
    private Connection conn = null;
    private PreparedStatement pstmt = null;
   /* private String dbUser;
    private String dbPassword;*/
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MalformedURLException, IOException {
        // TODO code application logic here

        TSExchangeRateDataLoader dbDataLoader = new TSExchangeRateDataLoader();


        StaXParser read = new StaXParser();
        List<Cube> stokList = read.readConfig("http://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml");

        if (!stokList.isEmpty()) {

            dbDataLoader.loadDriver();
            
            try {
                dbDataLoader.insertStockRate(stokList);
                System.out.println("-Done! TS_EXCHANGE_RATE is upto date-");
            } catch (ParseException ex) {
                Logger.getLogger(TSExchangeRateDataLoader.class.getName())
                        .log(Level.SEVERE, null, ex);
            }
   
        } else {

            System.out.println("No data in given xml");

        }

    }

    /**
     * load the jdbc driver
     */
    public void loadDriver() {
        try {

            Class.forName(DRIVER);


        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TSStockInflectionDataLoader.class.getName())
                    .log(Level.SEVERE, "Error loading jdbc driver", ex);
        }
    }

    public void insertStockRate(List<Cube> stokList) throws ParseException {
        try {
            /*if (dbUser != null && dbPassword != null) {
             * conn = DriverManager.getConnection(DATABASE, dbUser, dbPassword);
             * } else {}*/
            conn = DriverManager.getConnection(DATABASE);
            ResultSet rs = conn.createStatement().executeQuery("select distinct (exchange_date) from APP.TS_EXCHANGE_RATE order by exchange_date desc FETCH FIRST 1 ROWS ONLY");
            Date pastFinalStockDate = null, currentDate;
            if (rs.next()) {
                pastFinalStockDate = rs.getDate(1);
            }
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("INSERT INTO TS_EXCHANGE_RATE"
                    + " (CURRENCY_CODE, EXCHANGE_DATE, RATE,"
                    + " SOURCE_CURRENCY_CODE) values(?,?,?,?)");
            int j = 0;
<<<<<<< HEAD
            for (Cube cube : stokList) {

                pstmt.setString(1, cube.getCurrency());

                pstmt.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                        .parse(cube.getDate()).getTime()));

                pstmt.setDouble(3, Double.parseDouble(cube.getRate()));

                pstmt.setString(4, SOURCE_CURRENCY_COCE);

                pstmt.addBatch();

                if ((j + 1) % 100 == 0) {
                    pstmt.executeBatch();// will execute batch update process for every 100 element.
                }
                j++;
                //   System.out.println(j);
=======
            if(pastFinalStockDate != null){// check if DB is empty or not. if it is null then the DB is empty.
                for (Cube cube : stokList) {// imports only data based on comaparation with DB data.
                    currentDate = new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(cube.getDate()).getTime());
                    if (currentDate.compareTo(pastFinalStockDate) > 0) {
                      
                        pstmt.setString(1, cube.getCurrency());
                        pstmt.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                                .parse(cube.getDate()).getTime()));
                        pstmt.setDouble(3, Double.parseDouble(cube.getRate()));
                        pstmt.setString(4, SOURCE_CURRENCY_COCE);
                        pstmt.addBatch();
                        if ((j + 1) % 100 == 0) {
                            pstmt.executeBatch();// will execute batch update process for every 100 element.
                        }
                        j++;
                    }else{
                        break;
                    }
            }
            }else{
                for (Cube cube : stokList) {// this will import the full xml data since DB is empty.
                    pstmt.setString(1, cube.getCurrency());
                    pstmt.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd")
                            .parse(cube.getDate()).getTime()));
                    pstmt.setDouble(3, Double.parseDouble(cube.getRate()));
                    pstmt.setString(4, SOURCE_CURRENCY_COCE);
                    pstmt.addBatch();
                    if ((j + 1) % 100 == 0) {
                        pstmt.executeBatch();// will execute batch update process for every 100 element.
                    }
                    j++;
                }
>>>>>>> 67009584404c5e4273dbcb4295e99cb6a3657b29
            }
            pstmt.executeBatch();
            conn.commit();
     
        } catch (SQLException ex) {
            Logger.getLogger(TSExchangeRateDataLoader.class.getName())
                    .log(Level.SEVERE, null, ex);
            if (conn != null) {
                System.err.println("Transaction is being rolled back");
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(TSExchangeRateDataLoader.class.getName())
                            .log(Level.SEVERE, null, ex1);
                }
            }
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.setAutoCommit(true);
                    conn.close();
                }
            } catch (Exception e) {
                Logger.getLogger(TSExchangeRateDataLoader.class.getName())
                        .log(Level.SEVERE, null, e);
            }
        }



    }
}
