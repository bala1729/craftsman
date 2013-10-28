#!/usr/bin/env ruby
class BookInStock
	attr_accessor :isbn
	attr_accessor :price
	def initialize(isbn, price)
		if(isbn==nil || isbn.empty?) 
			raise ArgumentError.new("isbn must not be empty or nil")
		end 
		if (price<=0)
			raise ArgumentError.new("price must be greater than zero")
		end
		@isbn = isbn
		@price = price
	end

	def price_as_string
		sprintf "$%.2f",@price
	end
end

#a = BookInStock.new('',-1)
#a=BookInStock.new(nil,-1)
#puts BookInStock.new("123A",33.95).price_as_string == "$33.95"
