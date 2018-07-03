import sys
import java
from java.lang import Class
import java.lang.Object

import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

class ExampleStoredProcedure(java.lang.Object):
	def getSkuInventory(self, skuCode, resultSet):
		"@sig public static void getSkuInventory(String skuCode, ResultSet[] resultSet) throws SQLException"
      		try:
           		con = DriverManager.getConnection("jdbc:default:connection")
           		sql = "select * from INVENTORY " + "where SKU_CODE = ?"
           		ps = con.prepareStatement(sql)
           		ps.setString(1, skuCode)
           		resultSet[0] = ps.executeQuery();
		except SQLException, msg:
			print msg
			sys.exit(-1)
