##########################################################################################
# Author: Jared Duncan
# Email: [redacted]
# Date: 5/3/2020
# Description: This program satisfies the CS115 Programming Project #4
##########################################################################################

# This function gathers information from the user and makes an income array
def inputNamesAndIncomes():
	# Initialize our incomes array
	incomes = []

	# Find out if we have incomes or not
	hasIncome = None
	while True:
		try:
			hasIncome = input('Do you have income amounts?')
			if hasIncome == 'y':
				hasIncome = True
			elif hasIncome == 'n':
				hasIncome = False
			if hasIncome != True and hasIncome != False:
				raise Exception
			break
		except:
			print('Error: invalid response, please enter \'y\' or \'n\'.')

	# If we have incomes, get the information
	if hasIncome:

		# Get the name
		while True:
			name = None
			try:
				while True:
					name = input('Enter a tax payer name:')
					if name == '':
						raise Exception
					break
			except:
				print('Error: invalid response, please enter a name.')

			# Get the income
			income = None
			while True:
				try:
					income = float(input('Enter income amount:'))
					if income <= 0:
						raise Exception
					break
				except:
					print('Error: invalid response, please enter a float above zero.')

			# Add the name and income to the incomes array
			incomes.append([name, income])

			# See if we have more incomes to add
			hasMore = None
			while True:
				try:
					hasMore = input('\nDo you have more income amounts?')
					if hasMore == 'y':
						hasMore = True
					elif hasMore == 'n':
						hasMore = False
					if hasMore != True and hasMore != False:
						raise Exception
					break
				except:
					print('Error: invalid response, please enter \'y\' or \'n\'.')

			# If we don't have more to add, exit the loop
			# Otherwise, loop again
			if not hasMore:
				break

	# If we get here, someone started the program
	# and was already apparently finished before it started.
	else:
		quit()

	# Return the income array to the calling statement
	return incomes

# This little piggy -- this function calculates the taxes
def calculateTaxes(incomes):
	# Make a copy of the income array
	# Honestly don't need to do this, but I wanted to give
	# myself a reason to have a return at the end of the function
	# otherwise it just looks weird to me
	incomes = list(incomes)

	# Loop thru a copy of the copy of the passed income array
	# I'm doing this so we can edit the income array while looping.
	# If I didnt do this to a copy we'd get an error
	for index, record in enumerate(list(incomes)):
		name, income = record
		tax = 0
		if income >= 0 and income < 50000:
			tax = income * .05
		elif income >= 50000 and income < 100000:
			tax = 2500 + ((income - 50000) * .07)
		elif income >= 100000:
			tax = 6000 + ((income - 100000) * .09)
		incomes[index].append(tax)

	# return the modified incomes variable with taxes included
	return incomes

# Display a report, using format to make it look nicer.
def displayTaxReport(incomes):
	print('\n2020 TAX DUES FOR XANADU STATE\n')
	print(format('NAME','<30'),format('ANNUAL INCOME','>25'),format('TAX DUE','>20'))
	for record in incomes:
		name, income, tax = record
		income = '$' + str(round(income,2))
		tax = '$' + str(round(tax,2))
		print(format(name,'<30'), format(income,'>25'), format(tax,'>20'))
	print('')

def main():
	print('Welcome to Xanadu Tax Computation Program\n')
	displayTaxReport(calculateTaxes(inputNamesAndIncomes()))

main()