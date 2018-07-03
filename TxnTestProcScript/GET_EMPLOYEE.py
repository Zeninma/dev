def run(tableName, empId, rs):
    c = conn.cursor()
    stmt = "select * from " + tableName + " where ID = ?"
    c.execute(stmt,[empId])
    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    c.close()
    conn.close()
