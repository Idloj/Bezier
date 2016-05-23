package bezier

import java.awt.{ Point => AWTPoint, _ }
import java.awt.event._
import javax.swing._
import javax.swing.event._
import math._

import util._
import ControlFactory._

object MainWindow extends App {
  EventQueue.invokeLater {
    val window = new MainWindow
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    window.setSize(new Dimension(800, 800))
    window.setResizable(false)
    window.setVisible(true)
  }
}

class MainWindow extends JFrame("Bezier Curve") {
  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName)
  setLayout(new BorderLayout)
  val panel = new MainPanel
  val precisionField = slider(SwingConstants.HORIZONTAL, 1, 5)(repaint())
  precisionField.setEnabled(false)
  add(panel, BorderLayout.CENTER)
  add(precisionField, BorderLayout.SOUTH)

  class MainPanel extends JPanel {
    val points = Array[Point](null, null, null)
    var index = 0
    val r=10
    override def paintComponent(g: Graphics) = {
      g.clearRect(0,0,getWidth,getHeight)
      0 to 2 foreach { i => val point = points(i)
        if (point!=null) {
          g.fillOval(point.x-r/2, point.y-r/2, r, r)
          g.drawString(('A'+i).toChar.toString, point.x+r, point.y+r)
        }
      }
      if (precisionField.isEnabled) {
        (0.0 to 1 by 10 ** -precisionField.getValue
          map { t => t } // should balance the denser middle
          foreach { t =>
            val p = points(0)*((1-t)**2) + points(1)*(2*(1-t)*t) + points(2)*(t**2)
            //println(s"t=$t , a=${points(0)} , b=${points(1)} , c=${points(2)} , p=$p")
            g.fillOval(p.x, p.y, 5, 5)
          })
      }
    }

    addMouseListener(new MouseAdapter {
      override def mouseClicked(e: MouseEvent) =
        if (index<3) {
          points(index) = new Point(e.getPoint.x, e.getPoint.y)
          repaint()
          index+=1
          if (index==3) precisionField.setEnabled(true)
        } else {
          index=0
          precisionField.setEnabled(false)
          points(0)=null;points(1)=null;points(2)=null
          repaint()
        }
      })
  }

}
