# CS115 Programming Project 2
# Author: Jared Duncan
# 3/8/2020
# This program was written to satisfy the programming project 2 assignment for cs115

# Gather data for Chair 1 and calculate the total
chair1name = input("Input the name of the first chair style: ")
chair1price = float(input("What is the price of the chair? "))
chair1sales = int(input("How many were sold? "))
chair1total = (chair1price * chair1sales)

# New line
print("")

# Gather data for Chair 2 and calculate the total
chair2name = input("Input the name of the second chair style: ")
chair2price = float(input("What is the price of the chair? "))
chair2sales = int(input("How many were sold? "))
chair2total = (chair2price * chair2sales)

# New line
print("")

# Gather data for Chair 3 and calculate the total
chair3name = input("Input the name of the third chair style: ")
chair3price = float(input("What is the price of the chair? "))
chair3sales = int(input("How many were sold? "))
chair3total = (chair3price * chair3sales)

# New line
print("")

# Calculate the grand total
grandTotal = chair1total + chair2total + chair3total

# Print the table, starting with the header, columns, rows, and the grand total.
print(format("PRICE PER NUMBER", '^100s'))
print(format('STYLE', '<25s'), format('CHAIR', '>25s'), format('SOLD','^25s'), format('AMOUNT', '>25s'))
print(format(chair1name, '<25s'), format(chair1price, '>25,.2f'), format(chair1sales,'^25d'), format(chair1total, '>25,.2f'))
print(format(chair2name, '<25s'), format(chair2price, '>25,.2f'), format(chair2sales,'^25d'), format(chair2total, '>25,.2f'))
print(format(chair3name, '<25s'), format(chair3price, '>25,.2f'), format(chair3sales,'^25d'), format(chair3total, '>25,.2f'))
print("")
print(format("TOTAL", '<75s'), format(grandTotal, '>27,.2f'))