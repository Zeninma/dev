def run(closedRs):
    c = conn.cursor()
    c.execute("VALUES(1)");
    closedRs = factory.create([c.description,c.fetchall()])
    closedRs.close()
    conn.close()

