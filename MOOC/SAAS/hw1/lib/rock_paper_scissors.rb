class RockPaperScissors

  # Exceptions this class can raise:
  class NoSuchStrategyError < StandardError ; end

  def self.winner(player1, player2)
  	s1 = player1[1].downcase
  	s2 = player2[1].downcase
  	win = player1
    if (s1 == 'r') 
    	if (s2 == 's') 
    		win = player1
    	elsif (s2 == 'p')
    		win = player2
    	elsif (s2 == 'r')
    		win = player1
    	else
    		raise NoSuchStrategyError, "Strategy must be one of R,P,S"
    	end
    elsif (s1 == 's')
    	if (s2 == 'p') 
    		win = player1
    	elsif (s2 == 'r')
    		win = player2
    	elsif (s2 == 's')
    		win = player1
    	else
    		raise NoSuchStrategyError, "Strategy must be one of R,P,S" 
    	end
    elsif (s1 == 'p')
    	if (s2 == 'r') 
    		win = player1
    	elsif (s2 == 's')
    		win = player2
    	elsif (s2 == 'p')
    		win = player1
    	else
    		raise NoSuchStrategyError, "Strategy must be one of R,P,S"
    	end
    else
    	raise NoSuchStrategyError, "Strategy must be one of R,P,S"
    end
    return win
  end

  def self.tournament_winner(tournament)
    player1 = tournament[0]
    player2 = tournament[1]
    win = nil
    if ((player1[0].instance_of? Array) && (player2[0].instance_of? Array)) 
    	win1 = self.tournament_winner(player1)
    	win2 = self.tournament_winner(player2)
    	win = self.winner(win1, win2)
    else
    	win = self.winner(player1, player2)
    end
    return win
    	
  end

end
