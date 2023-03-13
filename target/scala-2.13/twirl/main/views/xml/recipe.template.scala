
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

object recipe extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template4[String,Integer,String,String,play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(name: String, time: Integer, title: String, typeFood: String):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.64*/("""
"""),format.raw/*2.1*/("""<recipe>
    <name>"""),_display_(/*3.12*/(name)),format.raw/*3.18*/("""</name>
    <time>"""),_display_(/*4.12*/(time)),format.raw/*4.18*/("""</time>
    <typeFood>"""),_display_(/*5.16*/(typeFood)),format.raw/*5.26*/("""</typeFood>
    <title>"""),_display_(/*6.13*/(title)),format.raw/*6.20*/("""</title>
</recipe>"""))
      }
    }
  }

  def render(name:String,time:Integer,title:String,typeFood:String): play.twirl.api.XmlFormat.Appendable = apply(name,time,title,typeFood)

  def f:((String,Integer,String,String) => play.twirl.api.XmlFormat.Appendable) = (name,time,title,typeFood) => apply(name,time,title,typeFood)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/recipe.scala.xml
                  HASH: 00bdba0c35c50cac932001e8d3fb9d76d926f912
                  MATRIX: 924->1|1080->63|1108->65|1155->86|1181->92|1227->112|1253->118|1303->142|1333->152|1384->177|1411->184
                  LINES: 27->1|32->1|33->2|34->3|34->3|35->4|35->4|36->5|36->5|37->6|37->6
                  -- GENERATED --
              */
          