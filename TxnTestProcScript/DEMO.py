from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:default:connection"
zxJDBC.autocommit = 1
driver = "com.splicemachine.db.jdbc.ClientDriver"

def run(lim, res):
    conn = zxJDBC.connect(jdbc_url, None, None, driver)
    c = conn.cursor()
    stmt = "select alias, javaclassname from sys.sysaliases {limit ?}"
    c.executemany(stmt,[lim])
    d = c.description
    result = c.fetchall()
    res[0] = factory([d,result])
    conn.commit()
    c.close()
    conn.close()
