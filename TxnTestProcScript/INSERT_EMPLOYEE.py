def run(tableName, empId, fname, lname):
    c = conn.cursor()
    stmt = "insert into " + tableName + " values(?, ?, ?)"
    c.execute(stmt, [empId, fname, lname])
    conn.commit()
    c.close()
    conn.close()
