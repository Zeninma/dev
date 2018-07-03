def run(tableName):
    c = conn.cursor()
    c.execute("create table " + tableName + " (ID int, FNAME varchar(20), LNAME varchar(30))")
    conn.commit()
    c.close()
    conn.close()
