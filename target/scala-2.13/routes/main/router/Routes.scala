// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:9
  HomeController_0: controllers.HomeController,
  // @LINE:14
  RecipeController_1: controllers.RecipeController,
  // @LINE:39
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:9
    HomeController_0: controllers.HomeController,
    // @LINE:14
    RecipeController_1: controllers.RecipeController,
    // @LINE:39
    Assets_2: controllers.Assets
  ) = this(errorHandler, HomeController_0, RecipeController_1, Assets_2, "/")

  def withPrefix(addPrefix: String): Routes = {
    val prefix = play.api.routing.Router.concatPrefix(addPrefix, this.prefix)
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, RecipeController_1, Assets_2, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index(req:Request)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.create(req:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.retrieve(req:Request, id:Integer)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.update(req:Request, id:Integer)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.delete(req:Request, id:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes""", """controllers.RecipeController.retrieveAll(req:Request)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:9
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      HomeController_0.index(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """""",
      """#########################################################################################
0-Inicio:
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_RecipeController_create1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_create1_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.create(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "create",
      Seq(classOf[play.mvc.Http.Request]),
      "POST",
      this.prefix + """recipes""",
      """#########################################################################################
1-Crear receta:
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:19
  private[this] lazy val controllers_RecipeController_retrieve2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieve2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieve(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieve",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer]),
      "GET",
      this.prefix + """recipe/""" + "$" + """id<[^/]+>""",
      """#########################################################################################
2-Obtener una receta por su id
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_RecipeController_update3_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_update3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.update(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "update",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer]),
      "PUT",
      this.prefix + """recipe/""" + "$" + """id<[^/]+>""",
      """#########################################################################################
3-Actualizar una receta
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_RecipeController_delete4_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_delete4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.delete(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "delete",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer]),
      "DELETE",
      this.prefix + """recipe/""" + "$" + """id<[^/]+>""",
      """#########################################################################################
4-Eliminar una receta
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:34
  private[this] lazy val controllers_RecipeController_retrieveAll5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_retrieveAll5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAll(fakeValue[play.mvc.Http.Request]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAll",
      Seq(classOf[play.mvc.Http.Request]),
      "GET",
      this.prefix + """recipes""",
      """#########################################################################################
5-Listar todas las recetas
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:39
  private[this] lazy val controllers_Assets_versioned6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned6_invoker = createInvoker(
    Assets_2.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:9
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(
          req => HomeController_0.index(req))
      }
  
    // @LINE:14
    case controllers_RecipeController_create1_route(params@_) =>
      call { 
        controllers_RecipeController_create1_invoker.call(
          req => RecipeController_1.create(req))
      }
  
    // @LINE:19
    case controllers_RecipeController_retrieve2_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_retrieve2_invoker.call(
          req => RecipeController_1.retrieve(req, id))
      }
  
    // @LINE:24
    case controllers_RecipeController_update3_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_update3_invoker.call(
          req => RecipeController_1.update(req, id))
      }
  
    // @LINE:29
    case controllers_RecipeController_delete4_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_delete4_invoker.call(
          req => RecipeController_1.delete(req, id))
      }
  
    // @LINE:34
    case controllers_RecipeController_retrieveAll5_route(params@_) =>
      call { 
        controllers_RecipeController_retrieveAll5_invoker.call(
          req => RecipeController_1.retrieveAll(req))
      }
  
    // @LINE:39
    case controllers_Assets_versioned6_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned6_invoker.call(Assets_2.versioned(path, file))
      }
  }
}
