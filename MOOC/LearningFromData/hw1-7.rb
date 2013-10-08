#!/Users/banarayanaswamy/.rvm/bin/ruby-2.0.0-p247
# This is support material for the course "Learning from Data" on edX.org
# https://www.edx.org/course/caltechx/cs1156x/learning-data/1120
#
# The software is intented for course usage, no guarantee whatsoever
# Date: Sep 30, 2013
#
# Template for a LIONsolver parametric table script.
#
# Generates a table based on input parameters taken from another table or from user input
#
# Syntax:
# When called without command line arguments:
#    number_of_inputs
#    name_of_input_1 default_value_of_input_1
#    ...
#    name_of_input_n default_value_of_input_n
# Otherwise, the program is invoked with the following syntax:
#    script_name.py input_1 ... input_n table_row_number output_file.csv
# where table_row_number is the row from which the input values are taken (assume it to be 0 if not needed)
#
# To customize, modify the output message with no arguments given and insert task-specific code
# to insert lines (using tmp_csv.writerow) in the output table.

#
# If there are not enough parameters, optionally write out the number of required parameters
# followed by the list of their names and default values. One parameter per line,
# name followed by tab followed by default value.
# LIONsolver will use this list to provide a user friendly interface for the component's evaluation.
#

if ARGV.length < 2
	puts "2\nNumber of tests\t1000\nNumber of training points\t10\n"
	exit	
end

#
# Retrieve the input parameters, the input row number, and the output filename.
#
in_params=[]
ARGV[0..-1].each { |x|
	in_params << x
}
in_row_number = ARGV[-2]
out_filename = ARGV[-1]
no_of_tests = in_params[0].to_i
training_size = in_params[1].to_i

#
# Retrieve the output filename from the command line; create a temporary filename
# and open it, passing it through the CSV writer
#
tmp_filename = out_filename + "_"
tmp_file = File.open(tmp_filename, "w")

#############################################################################
#
# Task-specific code goes here.
#

# The following function is a stub for the perceptron training function required in Exercise1-7 and following.
# It currently generates random results.
# You should replace it with your implementation of the
# perceptron algorithm (we cannot do it otherwise we solve the homework for you :)
# This functon takes the coordinates of the two points and the number of training samples to be considered.
# It returns the number of iterations needed to converge and the disagreement with the original function.
def perceptron_training (x1, y1, x2, y2, training_size)
	target_slope = (y2 -y1)/(x2-x1)
	target_c = y1 - (target_slope*x1)
	xt,yt,ot = generate_training_set(target_slope, target_c, training_size)
	# weights[0] is wx and weights[1] is wy
	weights = [my_rand(-1,1), my_rand(-1,1)]
	learning_rate = 0.1
	iteration = 1
	loop do
		global_error=0
		for i in 0..training_size-1 do
			#for the weight calculate the output
			actual_output = output(xt[i],yt[i],weights)
			local_error = ot[i] - actual_output
			if local_error != 0
				#update weights to adjust
				weights[0] = weights[0] + learning_rate * local_error * xt[i]
				weights[1] = weights[1] + learning_rate * local_error * yt[i]
			end
			global_error = global_error + local_error.abs
		end
		if global_error == 0
			break
		end
		iteration += 1
	end
	disagreement = calculate_disagreement(-target_slope,1,weights[0],weights[1])
	return iteration, disagreement
end

def calculate_disagreement(wtx, wty, wax, way)
	disagreement=[]
	threshold = 0.000999
	error_margin = 0.005
	sample_size = 10000
	sample_size.times do
		x = my_rand(-1.0,1.0)
		y = my_rand(-1.0,1.0)
		result = ((wtx * x + wty * y) - (wax * x + way * y)).abs
		new_result = (result - error_margin).abs <= threshold ? 0 : 1
		disagreement << new_result
	end 
	#agreement = disagreement.count(0)
	#probability = 1 - (agreement/sample_size)
	probability = disagreement.count(1)/sample_size
	return probability
end

def generate_training_set(m, c, training_size)
	xt=[]
	yt=[]
	ot=[]
	wt=[-m,1]
	training_size.times do 
		x1 = my_rand(-1.0,1.0)
		y1 = my_rand(-1.0,1.0)
		o1 = output(x1,y1,wt)
		xt << x1
		yt << y1
		ot << o1
	end
	return xt,yt,ot
end

def output(x, y, w)
	return (w[0] * x + w[1] * y) >= 0 ? 1 : -1
end

def generate_rand_points
	x1,x2,y1,y2=0
	loop do 
		x1 = my_rand(-1.0,1.0)
		y1 = my_rand(-1.0,1.0)
		x2 = my_rand(-1.0,1.0)
		y2 = my_rand(-1.0,1.0)
		if x2 - x1 != 0
			break
		end
	end
	return x1,y1,x2,y2
end

def my_rand(start, last)
	return rand(start..last)
	
end


# Write the header line in the output file, in this case the output is a 3-columns table containing the results
# of the experiments
# The syntax  name::type  is used to identify the columns and specify the type of data
header = "Test number::label,Number of iterations::number,Disagreement::number\n"
tmp_file.puts header


# Repeat the experiment n times (tests parameter) and store the result of each experiment in one line of the output table
no_of_iterations=[]
disagree=[]
for t in 1..no_of_tests.to_i do
	x1,y1,x2,y2 = generate_rand_points
	iterations, disagreement = perceptron_training(x1, y1, x2, y2, training_size)
	no_of_iterations << iterations
	disagree << disagreement
	line = t.to_s + ',' + iterations.to_s + ',' + disagreement.to_s
	tmp_file.puts line
end

puts "Average of iterations #{(no_of_iterations.inject(0.0) { |sum, el| sum + el })/no_of_tests}"
puts "Disagree count #{disagree.count(1)}"
puts "Probability of disagreement #{(disagree.count(1)/no_of_tests)}"
#
#############################################################################
#
# Close all input files and the temporary output file.
#
tmp_file.close

#
# Rename the temporary output file into the final one.
# It's important that the output file only appears when it is complete,
# otherwise LIONsolver might read an incomplete table.
#
File.rename(tmp_filename, out_filename)