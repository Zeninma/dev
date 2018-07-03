def run(tableName, empId, customizedMessage1, customizedMessage2, rs):
    c = conn.cursor()
    customizedMessage1[0] = "The first message"
    customizedMessage2[0] = "The second message"
    stmt = "select * from " + tableName + " where ID = ?"
    c.execute(stmt,[empId])
    d = c.description
    result = c.fetchall()
    rs[0] = factory.create([d,result])
    conn.commit()
    return
