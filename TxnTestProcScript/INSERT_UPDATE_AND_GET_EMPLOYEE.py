from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:default:connection"
driver = "com.splicemachine.db.jdbc.ClientDriver"

def run(tableName, empId, fname, lname, fname2, lname2, rs):
    conn = zxJDBC.connect(jdbc_url, None, None, driver)
    c = conn.cursor()
    insertStmt = "insert into " + tableName + " values(?, ?, ?)"
    c.execute(insertStmt,[empId, fname, lname])
    updateStmt = "update " + tableName + " set FNAME = ?, LNAME = ? where ID = ?"
    c.execute(updateStmt,[fname2, lname2, empId])
    getStmt = "select * from " + tableName + " where ID = ?"
    c.execute(getStmt,[empId])
    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    c.close()
    conn.close()
