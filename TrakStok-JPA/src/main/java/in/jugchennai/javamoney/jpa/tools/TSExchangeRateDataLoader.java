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

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String dbUser;
    private String dbPassword;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        TSExchangeRateDataLoader dbDataLoader = new TSExchangeRateDataLoader();


        StaXParser read = new StaXParser();
        List<Cube> stokList = read.readConfig("eurofxref.xml");

        if (!stokList.isEmpty()) {

            dbDataLoader.loadDriver();
            try {
                dbDataLoader.insertStockRate(stokList);
            } catch (ParseException ex) {
                Logger.getLogger(TSExchangeRateDataLoader.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("-Done-");


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
            Logger.getLogger(TSStockInflectionDataLoader.class.getName()).log(Level.SEVERE, "Error loading jdbc driver", ex);
        }
    }

    public void insertStockRate(List<Cube> stokList) throws ParseException {
        try {
            /*if (dbUser != null && dbPassword != null) {
             * conn = DriverManager.getConnection(DATABASE, dbUser, dbPassword);
             * } else {}*/
            conn = DriverManager.getConnection(DATABASE);
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement("INSERT INTO TS_EXCHANGE_RATE (CURRENCY_CODE, EXCHANGE_DATE, RATE, SOURCE_CURRENCY_CODE) values(?,?,?,?)");

            int j = 0;
            for (Cube cube : stokList) {
                pstmt.setString(1, cube.getCurrency());
                System.out.println(cube.getCurrency());
                pstmt.setDate(2, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(cube.getDate()).getTime()));
                System.out.println(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(cube.getDate()).getTime()));
                pstmt.setDouble(3, Double.parseDouble(cube.getRate()));
                System.out.println(Double.parseDouble(cube.getRate()));
                pstmt.setString(4, SOURCE_CURRENCY_COCE);
                System.out.println(SOURCE_CURRENCY_COCE);
                if ((j + 1) % 100 == 0) {
                    pstmt.executeBatch();// will execute batch update process for every 100 element.
                }
                j++;
            }
            pstmt.executeBatch();
            conn.commit();
        } catch (SQLException ex) {
            Logger.getLogger(TSExchangeRateDataLoader.class.getName()).log(Level.SEVERE, null, ex);
            if (conn != null) {
                System.err.println("Transaction is being rolled back");
                try {
                    conn.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(TSExchangeRateDataLoader.class.getName()).log(Level.SEVERE, null, ex1);
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
                Logger.getLogger(TSExchangeRateDataLoader.class.getName()).log(Level.SEVERE, null, e);
            }
        }



    }
}
