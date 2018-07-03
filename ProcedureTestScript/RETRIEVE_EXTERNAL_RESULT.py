def run(dbName, user, password, externalRs):
    url = "jdbc:splice:" + dbName;
    conn = zxJDBC.connect(url, user, password, driver)
    c = conn.cursor()
    c.execute("VALUES(1)")
    externalRs = factory.create([c.description,c.fetchall()])
