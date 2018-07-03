#class Test():
#    def __init__(self):
#        self.val = 10
#
#        x = args[0]
#        print(self.val * x)

#value  = 5
#
#def test():
#    global value
#    value = 2
#
#test()
#print value

def func(a,b,c):
    print a+b+c

def execute(*args):
    func(*args)

execute(1,2,3)
