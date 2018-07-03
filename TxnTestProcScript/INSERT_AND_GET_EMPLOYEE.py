def run(tableName, empId, fname, lname, rs):
    c = conn.cursor()
    insertStmt = "insert into " + tableName + " values(?, ?, ?)"
    c.execute(insertStmt,[empId, fname, lname])
    getStmt = "select * from " + tableName + " where ID = ?"
    c.execute(getStmt,[empId])
    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    c.close()
    conn.close()
