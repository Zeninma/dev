def run(tableName, empId, errorCode, errorMessage, rs):
    c = conn.cursor()
    try:
        stmt = "select * from " + tableName + " where ID = ?"
        c.execute(stmt,[empId])
        d = c.description
        result = c.fetchall()
        rs[0] = factory.create([d,result])
        conn.commit()
    except:
        errorCode[0] = "1"
        responseErrorMessage[0] = "Failure - exception fetching record"
