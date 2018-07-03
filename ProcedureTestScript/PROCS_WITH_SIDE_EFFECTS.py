def run(returnResults, rs1, rs2):
    c = conn.cursor()
    c.execute("INSERT INTO SIMPLE_TABLE VALUES(42)")
    if(returnResults > 0):
        c1 = conn.cursor()
        c1.execute("VALUES(1)")
        rs1[0] = factory.create([c1.description, c1.fetchall()])
    if(returnResults > 1):
        c2 = conn.cursor()
        c2.execute("VALUES(1)")
        rs2[0] = factory.create([c2.description, c2.fetchall()])
    conn.close()

