factory = None
def setFactory(*args):
    factory = arg[0]

from com.ziclix.python.sql import zxJDBC
jdbc_url = "jdbc:default:connection"
username = "splice"
password = "admin"
driver = "com.splicemachine.db.jdbc.ClientDriver"

def run(*args):
    rs = args[0]
    factory = args[1]
    conn = zxJDBC.connect(jdbc_url, None, None, driver)
    c = conn.cursor()
    c.execute("select alias from sys.sysaliases {limit 3}")
    d = c.description
    rows = c.fetchall()
    rs[0] = factory.create([d,rows])
    conn.commit()
    c.close()
    conn.close()
