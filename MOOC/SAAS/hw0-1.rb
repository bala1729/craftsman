#!/usr/bin/env ruby
def sum(a)
	result = a.size > 0 ? (a.inject(0) {|sum, a1| sum = sum + a1}) : 0
end

def max_2_sum(a)
	result=0
	if (a.size == 1)
		result = a[0]
	elsif (a.size > 1)
		a.sort! {|x,y| y <=> x} 
		result = a[0] + a[1]
	end
	return result
end

def sum_to_n?(a,n)
	exists = false
	if (a.size >0)
		a.sort!
		i=0
		j=a.size-1
		while (a[i] + a[j] != n and i!=j) 
			if (a[i] + a[j] > n)
				j -= 1
			elsif (a[i] + a[j] < n)
				i += 1
			end
		end
		if (i!=j && a[i] + a[j] == n)
			exists = true
		end
	elsif (a.size==0)
		if (n==0)
			exists = true
		end	
	end
	return exists
end
=begin
puts sum []
puts sum [1,2,3]
puts sum [1]
puts sum [1, -1.1]
puts sum_2_max []
puts sum_2_max [8]
puts sum_2_max [6,3]
puts sum_2_max [6,4,8]
puts sum_to_n? [],0
puts sum_to_n? [],1
puts sum_to_n? [1],1
puts sum_to_n? [0,1],1
puts sum_to_n? [1,4,2,9,4],8
puts sum_to_n? [1,4,2,9,4],10
puts sum_to_n? [1,4,2,9,4],4
puts sum_to_n? [1,4,2,9,4],15
puts sum_to_n? [1,4,2,9,4],13
=end