
package views.xml

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.xml._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object _name_tag extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[String,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.16*/("""
"""),format.raw/*2.1*/("""<name>"""),_display_(/*2.8*/(name)),format.raw/*2.14*/("""</name>"""))
      }
    }
  }

  def render(name:String): play.twirl.api.XmlFormat.Appendable = apply(name)

  def f:((String) => play.twirl.api.XmlFormat.Appendable) = (name) => apply(name)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/_name_tag.scala.xml
                  HASH: 3bdd61eb0ce9eacac80270dc63e84c78cefceeb0
                  MATRIX: 905->1|1013->15|1041->17|1073->24|1099->30
                  LINES: 27->1|32->1|33->2|33->2|33->2
                  -- GENERATED --
              */
          