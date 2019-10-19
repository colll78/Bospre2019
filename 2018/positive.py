import sys 
import numpy as np

def find_xy(A,B,C):
	for x in range(1, C//A + 1):
		if ((C-A*x) % B) == 0:
			return x, (C-A*x)//B

def main():
	A = input()
	B = input()
	C = input()

	x,y = find_xy(int(A),int(B),int(C))

	print('{} * {} + {} * {} = {}'.format(A,x,B,y,C))

main()