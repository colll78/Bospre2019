import sys

def find_char(string_to_find, char_to_find):
	return [i for i, ch in enumerate(string_to_find) if ch == char_to_find]

def mapping(input_str_1, input_str_2):
	positions = {}

	for i, ch in enumerate(input_str_1):
		# Get list of index of character i in string 2
		indexes = find_char(input_str_2, ch)

		# If cannot find any occurences of the character, return
		if indexes == []:
			print("impossible")
			return
		
		# Loop over the indexes to check if that position is already marked
		for index in indexes:
			found = False

			# If the index is available, add the index to positions dict
			if index not in positions:
				found = True
				positions[index] = i
				break		
		
		# If there is no character available, return 
		if found == False:
			print("impossible")
			return


	result = sorted(positions.items(), key = lambda x: x[1])
	for pair in result:
		print('{}: {} -> {}'.format(input_str_1[pair[1]], pair[1]+1, pair[0]+1))

def main():
	# Input strings
	input_str_1 = input()
	input_str_2 = input()

	mapping(input_str_1, input_str_2)

main()