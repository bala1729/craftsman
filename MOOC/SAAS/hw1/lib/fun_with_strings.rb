#!/usr/bin/env ruby
module FunWithStrings
  def palindrome?
    if (self != nil && !self.empty?) 
      str = self.delete("^a-zA-Z").upcase
      revstr = str.reverse
      revstr == str
    end
  end
  def count_words
    result = {}
    result.default = 0
    str = self.delete("^ a-zA-Z")
    words = str.downcase.split(" ")
    words.each do |word|
      result[word] = result[word] + 1
    end
    return result
  end
  def anagram_groups
    result = []
    if (self != nil && !self.empty?) 
      map = {}
      words = self.split(" ")
      words.each do |word|
        anakey = word.downcase.split("").sort.join
        group = map.fetch(anakey,[])
        group.push(word)
        map[anakey] = group
      end
      map.each do |key,value|
        result.push value
      end
    end
    return result
  end
end

# make all the above functions available as instance methods on Strings:

class String
  include FunWithStrings
end

class NilClass
  include FunWithStrings
end

