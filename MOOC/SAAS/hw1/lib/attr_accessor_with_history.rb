class Class

  def attr_accessor_with_history(attr_name)
    attr_name = attr_name.to_s # make sure it's a string
    history = nil
    

    class_eval %Q{
    	
    	define_method("#{attr_name}") do 
    		@#{attr_name}
    	end
    	define_method("#{attr_name}=") do |variable| 
    		
    		if (@history == nil)
    			@history=[nil]
    		else
    			@history << @#{attr_name}
    		end
    		@#{attr_name} = variable
  		end	
      	define_method("#{attr_name}_history") do 
      		return @history
      	end
    }
  end
  
end
