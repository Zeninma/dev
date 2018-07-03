def run(number, rs1, rs2, rs3, rs4):
    if (number > 0):
        c = conn.cursor()
        pyRs1 = c.execute("VALUES(1)")
        rs1[0] = factory.create([pyRs1.description, pyRs1.fetchall()])
    if (number > 1):
        c = conn.cursor()
        pyRs2 = c.execute("VALUES(1)")
        rs2[0] = factory.create([pyRs2.description, pyRs2.fetchall()])
    if (number > 2):
        c = conn.cursor()
        pyRs3 = c.execute("VALUES(1)")
        rs3[0] = factory.create([pyRs3.description, pyRs3.fetchall()])
    if (number > 3):
        c = conn.cursor()
        pyRs4 = c.execute("VALUES(1)")
        rs4[0] = factory.create([pyRs4.description, pyRs4.fetchall()])
    c.close()
    conn.close()
