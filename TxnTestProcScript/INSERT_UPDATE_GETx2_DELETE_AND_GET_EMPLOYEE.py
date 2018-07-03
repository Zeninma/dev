from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:default:connection"
driver = "com.splicemachine.db.jdbc.ClientDriver"

def run(tableName, empId, fname, lname, fname2, lname2, empId2, rs):
    conn = zxJDBC.connect(jdbc_url, None, None, driver)
    c = conn.cursor()
    insertStmt = "insert into " + tableName + " values(?, ?, ?)"
    c.execute(insertStmt,[empId, fname, lname])

    updateStmt = "update " + tableName + " set FNAME = ?, LNAME = ? where ID = ?"
    c.execute(updateStmt,[fname2, lname2, empId])

    getFirstNamesStmt = "select FNAME from " + tableName
    c.execute(getFirstNamesStmt)

    getLastNamesStmt = "select LNAME from " + tableName
    c.execute(getLastNamesStmt)

    deleteStmt = "delete from " + tableName + " where ID = ?"
    c.execute(deleteStmt,[empId2])

    getEmpStmt = "select * from " + tableName + " where ID = ?"
    c.execute(getEmpStmt,[empId])

    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    c.close()
    conn.close()
