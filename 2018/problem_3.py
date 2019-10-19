import sys
import numpy as np
import math

def find_coordinate(pos, M):
	if pos % M == 0:
		y = M
	else:
		y = pos%M

	x = (pos - y)//M + 1

	return x, y

def find_path(N, M, a):
	lowest = np.argmin(a) + 1
	highest = np.argmax(a) + 1

	lowest_pos = find_coordinate(lowest, M)
	highest_pos = find_coordinate(highest, M)

	print('Lowest is: ',lowest, lowest_pos)
	print('Highest is: ',highest, highest_pos)

	return

def main():
	M = input()
	N = input()

	M = int(M)
	N = int(N)
	a = np.ndarray(shape=(N,M), dtype=int)

	for i in range(N):
		for j in range(M):
			num = input()
			a[i][j] = int(num)

	find_path(N, M, a)

main()