import math._
import java.awt.event.{ ActionEvent, ActionListener }

package object bezier {
  implicit class RichDouble(d: Double) {
    def **(exp: Double) = pow(d, exp)
  }
  implicit def double2int(d: Double) = round(d.toFloat)
  implicit def code2runnable(fn: => Unit) = new Runnable { def run() = fn }
}
