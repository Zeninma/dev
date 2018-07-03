from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:default:connection"
driver = "com.splicemachine.db.jdbc.ClientDriver"
def run():
    conn = zxJDBC.connect(jdbc_url, None, None, driver)
    c = conn.cursor()
    stmt = "select * from TEST_TABLE {limit 1}"
    c.execute(stmt)
    d = c.description
    result = c.fetchall()
    conn.commit()
    c.close()
    conn.close()
    return result
