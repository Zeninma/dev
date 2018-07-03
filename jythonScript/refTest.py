from java.lang import String
# obtain a connection using the with-statment
def run(*args):
    strList = args[0]
    print type(strList)
    print type(args[0])
    strList[0] = String("abcd")
    return
