def run(tableName):
    c = conn.cursor()
    c.execute("drop table " + tableName)
    conn.commit()
    c.close()
    conn.close()
