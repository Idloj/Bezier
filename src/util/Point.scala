package util

import math._

case class Point(x: Double, y: Double) {
  def +(that: Point) = Point(x+that.x, y+that.y)
  def -(that: Point) = this + -that
  def unary_- = Point(-x, -y)
  def *(d: Double) = Point(d*x, d*y)
  def /(d: Double) = this * (1/d)
  def rotate(d: Double) =
    // this is slightly different than the normal equation since y points downwards, not upwards
    Point(x * cos(d) + y * sin(d), y * cos(d) - x * sin(d))
}
