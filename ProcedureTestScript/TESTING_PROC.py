def run(rs1, rs2, rs3, rs4):
    c1 = conn.cursor()
    c1.execute("VALUES(1)")
    rs1[0] = factory.create([c1.description, c1.fetchall()])

    c2 = conn.cursor()
    c2.execute("VALUES(1)")
    rs2[0] = factory.create([c2.description, c2.fetchall()])

    c3 = conn.cursor()
    c3.execute("VALUES(1)")
    rs3[0] = factory.create([c3.description, c3.fetchall()])

    c4 = conn.cursor()
    c4.execute("VALUES(1)")
    rs4[0] = factory.create([c4.description, c4.fetchall()])

