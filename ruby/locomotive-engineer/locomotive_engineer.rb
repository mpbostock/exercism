class LocomotiveEngineer
  def self.generate_list_of_wagons(*wagons)
    wagons
  end

  def self.fix_list_of_wagons(each_wagons_id, missing_wagons)
    first, second, locomotive, *rest_of_wagons = each_wagons_id
    [locomotive, *missing_wagons, *rest_of_wagons, first, second]
  end

  def self.add_missing_stops(route, **missing_stops)
    { **route, stops: missing_stops.values }
  end

  def self.extend_route_information(route, more_route_information)
    { **route, **more_route_information }
  end
end
