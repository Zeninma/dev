package org.splicetest.customprocs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * This class contains custom stored procedures that will be dynamically loaded into the Splice Machine
 * database with the SQLJ jar file loading system procedures.
 *
 * @author Splice Machine
 */


public class CustomSpliceProcs {
    /**
     * Return the names for all tables in the database.
     *
     * @param rs    result set containing names of all the tables in teh database
     */

    public static void GET_TABLE_NAMES(ResultSet[] rs)
            throws SQLException
    {
        Connection conn = DriverManager.getConnection("jdbc:default:connection");
        PreparedStatement pstmt = conn.prepareStatement("select * from sys.systables");
        rs[0] = pstmt.executeQuery();
        conn.close();
    }
}