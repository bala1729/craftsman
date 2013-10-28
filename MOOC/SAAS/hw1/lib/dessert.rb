class Dessert
  attr_accessor :name
  attr_accessor :calories
  def initialize(name, calories)
    @name = name
    @calories = calories
  end
  def healthy?
    iam = false
    if calories < 200
      iam = true
    end
    return iam
  end
  def delicious?
    return true
  end
end

class JellyBean < Dessert
  attr_accessor :flavor
  def initialize(flavor)
    @flavor = flavor
    @calories = 5
    @name = flavor + ' jelly bean'
  end
  def delicious?
    iam = true
    if flavor == 'licorice'
      iam = false
    end
    return iam
  end
end
