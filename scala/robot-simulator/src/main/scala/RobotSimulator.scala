object Bearing extends Enumeration {
  val North, South, East, West = Value

  def turnLeft(bearing: Bearing.Value): Bearing.Value = bearing match {
    case North => West
    case South => East
    case East => North
    case West => South
  }

  def turnRight(bearing: Bearing.Value): Bearing.Value = bearing match {
    case North => East
    case South => West
    case East => South
    case West => North
  }

  def advance(bearing: Bearing.Value, coordinates: (Int, Int)): (Int, Int) = bearing match {
    case North => (coordinates._1, coordinates._2 + 1)
    case South => (coordinates._1, coordinates._2 - 1)
    case East => (coordinates._1 + 1, coordinates._2)
    case West => (coordinates._1 - 1, coordinates._2)
  }
}

case class Robot(bearing: Bearing.Value, coordinates: (Int, Int)) {
  def turnRight: Robot = copy(bearing = Bearing.turnRight(bearing))

  def turnLeft: Robot = copy(bearing = Bearing.turnLeft(bearing))

  def advance: Robot = copy(coordinates = Bearing.advance(bearing, coordinates))

  def simulate(instructions: String): Robot = {
    instructions.foldLeft(this)((robot, instruction) => instruction match {
      case 'R' => robot.turnRight
      case 'L' => robot.turnLeft
      case 'A' => robot.advance
    })
  }
}