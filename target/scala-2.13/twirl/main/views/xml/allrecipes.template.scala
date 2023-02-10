
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

object allrecipes extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.XmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.XmlFormat.Appendable]](play.twirl.api.XmlFormat) with _root_.play.twirl.api.Template1[List[RecipeModel],play.twirl.api.XmlFormat.Appendable] {

  /**/
  def apply/*1.2*/(receta: List[RecipeModel]):play.twirl.api.XmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*1.29*/("""
"""),format.raw/*2.1*/("""<?xml version="1.0" encoding="UTF-8" ?>
<allrecipes>
    """),_display_(/*4.6*/for(r <- receta) yield /*4.22*/{_display_(Seq[Any](format.raw/*4.23*/("""
        """),_display_(/*5.10*/recipe(r.name, r.time, r.title.title, r.typeFood)),format.raw/*5.59*/("""
    """)))}),format.raw/*6.6*/("""
"""),format.raw/*7.1*/("""</allrecipes>
"""))
      }
    }
  }

  def render(receta:List[RecipeModel]): play.twirl.api.XmlFormat.Appendable = apply(receta)

  def f:((List[RecipeModel]) => play.twirl.api.XmlFormat.Appendable) = (receta) => apply(receta)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/allrecipes.scala.xml
                  HASH: 72b2d59b2819ab5e995b15b9ab3818b0ba902e2e
                  MATRIX: 917->1|1038->28|1066->30|1151->90|1182->106|1220->107|1257->118|1326->167|1362->174|1390->176
                  LINES: 27->1|32->1|33->2|35->4|35->4|35->4|36->5|36->5|37->6|38->7
                  -- GENERATED --
              */
          