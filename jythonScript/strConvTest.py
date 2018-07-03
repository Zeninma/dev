from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:splice://localhost:1527/splicedb"
zxJDBC.autocommit = 1
username = "splice"
password = "admin"
driver = "com.splicemachine.db.jdbc.ClientDriver"

# obtain a connection using the with-statment
def run(col_name):
    conn = zxJDBC.connect(jdbc_url, username, password, driver)
    c = conn.cursor()
    c.execute("select "+col_name+" from sys.sysaliases {limit 3}")
    d = c.description
    result = c.fetchall()
    conn.commit()
    c.close()
    conn.close()
    return (c,d,result)
