import sys
import math

def triangle_area(a,b,c):
	p = (a+b+c)/2
	return math.sqrt(p*(p-a)*(p-b)*(p-c))

def is_touched(r, l, d1, d2):
	is_touched = True

	area = triangle_area(d1,d2,l)

	if 2*area/l > r:
		is_touched = False 

	return is_touched

def main():
	r = input()
	l = input()
	d1 = input()
	d2 = input()

	r, l, d1, d2 = float(r), float(l), float(d1), float(d2)

	if is_touched(r, l, d1, d2):
		print('yes')
	else:
		print('no')

main()