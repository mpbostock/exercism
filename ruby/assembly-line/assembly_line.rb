class AssemblyLine
  CARS_PER_HOUR = 221
  MINUTES_PER_HOUR = 60

  def initialize(speed)
    @speed = speed
  end

  private def success_rate
    if @speed in 5..8
      0.9
    elsif @speed == 9
      0.8
    elsif @speed == 10
      0.77
    else
      1.0
    end
  end

  def production_rate_per_hour
    @speed * CARS_PER_HOUR * success_rate
  end

  def working_items_per_minute
    (production_rate_per_hour / MINUTES_PER_HOUR).to_i
  end
end
