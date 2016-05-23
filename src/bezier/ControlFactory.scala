package bezier

import javax.swing._
import javax.swing.event._

object ControlFactory {
  def slider(ort: Int, min: Int, max: Int)(action: => Unit): JSlider =
    slider(ort, min, max, (min+max)/2)(action)
  def slider(ort: Int, min: Int, max: Int, value: Int)
             (action: => Unit) = {
    val slider = new JSlider(ort, min, max, value)
    slider.addChangeListener(new ChangeListener {
      def stateChanged(e: ChangeEvent) = action
    })
    slider
  }
}
