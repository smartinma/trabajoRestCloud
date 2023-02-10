
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

object recipe extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template2[String,Integer,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String, age: Integer):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.30*/("""
        """),format.raw/*2.9*/("""<?xml version="1.0" encoding="utf-8" ?>
<recipe>
    """),_display_(/*4.6*/_name_tag(name)),format.raw/*4.21*/("""
    """),format.raw/*5.5*/("""<age>"""),_display_(/*5.11*/(age)),format.raw/*5.16*/("""</age>
</recipe>"""))
      }
    }
  }

  def render(name:String,age:Integer): play.twirl.api.XmlFormat.Appendable = apply(name,age)

  def f:((String,Integer) => play.twirl.api.XmlFormat.Appendable) = (name,age) => apply(name,age)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/recipe.scala.xml
                  HASH: e0f7c1fc293919920e28243236a404e327e76d24
                  MATRIX: 910->1|1032->29|1068->39|1149->95|1184->110|1216->116|1248->122|1273->127
                  LINES: 27->1|32->1|33->2|35->4|35->4|36->5|36->5|36->5
                  -- GENERATED --
              */
          