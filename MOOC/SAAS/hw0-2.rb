#!/usr/bin/env ruby
def hello(name)
	"Hello, "+name
end

def starts_with_consonant?(s)
	result = false
	if (s!=nil && s.length!=0)
		result = (s =~ /^[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z]/) == 0
	end
	return result
end

def binary_multiple_of_4?(s)
	result = false
	if (s!=nil && s.length > 2)
		result = (s =~ /^(?:[01]*0)?0$/) == 0
	end
	return result
end

#puts hello("") == "Hello, "
#puts hello("name") == "Hello, name"
#puts starts_with_consonant? "AA"
#puts starts_with_consonant? "BA"
#puts starts_with_consonant? "1A"
#puts starts_with_consonant? ''
#puts starts_with_consonant? nil
#puts starts_with_consonant? '#foo'
#puts binary_multiple_of_4? "100"
#puts binary_multiple_of_4? "1000"
#puts binary_multiple_of_4? "101"
#puts binary_multiple_of_4? "10"
#puts binary_multiple_of_4? "1000A"
#puts binary_multiple_of_4? "0101010101010100"
