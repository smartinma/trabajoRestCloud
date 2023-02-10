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
  // @LINE:79
  Assets_2: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:9
    HomeController_0: controllers.HomeController,
    // @LINE:14
    RecipeController_1: controllers.RecipeController,
    // @LINE:79
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
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.retrieveById(req:Request, id:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/type/""" + "$" + """typeFood<[^/]+>""", """controllers.RecipeController.retrieveAllByType(req:Request, typeFood:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/name/""" + "$" + """name<[^/]+>""", """controllers.RecipeController.retrieveAllByName(req:Request, name:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/ingredient/""" + "$" + """ingredient<[^/]+>""", """controllers.RecipeController.retrieveAllByIngredient(req:Request, ingredient:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipes/time/""" + "$" + """time<[^/]+>""", """controllers.RecipeController.retrieveAllByTime(req:Request, time:Integer)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/title/""" + "$" + """title<[^/]+>""", """controllers.RecipeController.retrieveByTitle(req:Request, title:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/puntuation/""" + "$" + """point<[^/]+>""", """controllers.RecipeController.retrieveAllByValoration(req:Request, point:Integer)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.update(req:Request, id:Integer)"""),
    ("""DELETE""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>""", """controllers.RecipeController.delete(req:Request, id:Integer)"""),
    ("""PUT""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """recipe/""" + "$" + """id<[^/]+>/point/""" + "$" + """point<[^/]+>""", """controllers.RecipeController.valorateRecipe(req:Request, id:Integer, point:Integer)"""),
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
  private[this] lazy val controllers_RecipeController_retrieveById2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveById2_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveById(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveById",
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
  private[this] lazy val controllers_RecipeController_retrieveAllByType3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/type/"), DynamicPart("typeFood", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveAllByType3_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAllByType(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAllByType",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """recipes/type/""" + "$" + """typeFood<[^/]+>""",
      """#########################################################################################
3-Obtener una receta por su tipo de alimentación
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:29
  private[this] lazy val controllers_RecipeController_retrieveAllByName4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/name/"), DynamicPart("name", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveAllByName4_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAllByName(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAllByName",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """recipes/name/""" + "$" + """name<[^/]+>""",
      """#########################################################################################
4-Obtener una receta por su nombre genérico
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:34
  private[this] lazy val controllers_RecipeController_retrieveAllByIngredient5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/ingredient/"), DynamicPart("ingredient", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveAllByIngredient5_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAllByIngredient(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAllByIngredient",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """recipes/ingredient/""" + "$" + """ingredient<[^/]+>""",
      """#########################################################################################
5-Obtener una receta por alguno de sus ingredientes
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:39
  private[this] lazy val controllers_RecipeController_retrieveAllByTime6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes/time/"), DynamicPart("time", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveAllByTime6_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAllByTime(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAllByTime",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer]),
      "GET",
      this.prefix + """recipes/time/""" + "$" + """time<[^/]+>""",
      """#########################################################################################
6-Obtener una receta por su tiempo de preparación
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:44
  private[this] lazy val controllers_RecipeController_retrieveByTitle7_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/title/"), DynamicPart("title", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveByTitle7_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveByTitle(fakeValue[play.mvc.Http.Request], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveByTitle",
      Seq(classOf[play.mvc.Http.Request], classOf[String]),
      "GET",
      this.prefix + """recipe/title/""" + "$" + """title<[^/]+>""",
      """#########################################################################################
7-Obtener una receta por su titulo unico
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:49
  private[this] lazy val controllers_RecipeController_retrieveAllByValoration8_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/puntuation/"), DynamicPart("point", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_retrieveAllByValoration8_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.retrieveAllByValoration(fakeValue[play.mvc.Http.Request], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "retrieveAllByValoration",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer]),
      "GET",
      this.prefix + """recipe/puntuation/""" + "$" + """point<[^/]+>""",
      """#########################################################################################
8-Obtener una receta por su valoracion
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:54
  private[this] lazy val controllers_RecipeController_update9_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_update9_invoker = createInvoker(
    
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
9-Actualizar una receta por id
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:59
  private[this] lazy val controllers_RecipeController_delete10_route = Route("DELETE",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_delete10_invoker = createInvoker(
    
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
10-Eliminar una receta por id
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:64
  private[this] lazy val controllers_RecipeController_valorateRecipe11_route = Route("PUT",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipe/"), DynamicPart("id", """[^/]+""",true), StaticPart("/point/"), DynamicPart("point", """[^/]+""",true)))
  )
  private[this] lazy val controllers_RecipeController_valorateRecipe11_invoker = createInvoker(
    
    (req:play.mvc.Http.Request) =>
      RecipeController_1.valorateRecipe(fakeValue[play.mvc.Http.Request], fakeValue[Integer], fakeValue[Integer]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.RecipeController",
      "valorateRecipe",
      Seq(classOf[play.mvc.Http.Request], classOf[Integer], classOf[Integer]),
      "PUT",
      this.prefix + """recipe/""" + "$" + """id<[^/]+>/point/""" + "$" + """point<[^/]+>""",
      """#########################################################################################
11-Puntuar una receta por su id
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:74
  private[this] lazy val controllers_RecipeController_retrieveAll12_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("recipes")))
  )
  private[this] lazy val controllers_RecipeController_retrieveAll12_invoker = createInvoker(
    
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
13-Listar todas las recetas
#########################################################################################""",
      Seq()
    )
  )

  // @LINE:79
  private[this] lazy val controllers_Assets_versioned13_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned13_invoker = createInvoker(
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
    case controllers_RecipeController_retrieveById2_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_retrieveById2_invoker.call(
          req => RecipeController_1.retrieveById(req, id))
      }
  
    // @LINE:24
    case controllers_RecipeController_retrieveAllByType3_route(params@_) =>
      call(params.fromPath[String]("typeFood", None)) { (typeFood) =>
        controllers_RecipeController_retrieveAllByType3_invoker.call(
          req => RecipeController_1.retrieveAllByType(req, typeFood))
      }
  
    // @LINE:29
    case controllers_RecipeController_retrieveAllByName4_route(params@_) =>
      call(params.fromPath[String]("name", None)) { (name) =>
        controllers_RecipeController_retrieveAllByName4_invoker.call(
          req => RecipeController_1.retrieveAllByName(req, name))
      }
  
    // @LINE:34
    case controllers_RecipeController_retrieveAllByIngredient5_route(params@_) =>
      call(params.fromPath[String]("ingredient", None)) { (ingredient) =>
        controllers_RecipeController_retrieveAllByIngredient5_invoker.call(
          req => RecipeController_1.retrieveAllByIngredient(req, ingredient))
      }
  
    // @LINE:39
    case controllers_RecipeController_retrieveAllByTime6_route(params@_) =>
      call(params.fromPath[Integer]("time", None)) { (time) =>
        controllers_RecipeController_retrieveAllByTime6_invoker.call(
          req => RecipeController_1.retrieveAllByTime(req, time))
      }
  
    // @LINE:44
    case controllers_RecipeController_retrieveByTitle7_route(params@_) =>
      call(params.fromPath[String]("title", None)) { (title) =>
        controllers_RecipeController_retrieveByTitle7_invoker.call(
          req => RecipeController_1.retrieveByTitle(req, title))
      }
  
    // @LINE:49
    case controllers_RecipeController_retrieveAllByValoration8_route(params@_) =>
      call(params.fromPath[Integer]("point", None)) { (point) =>
        controllers_RecipeController_retrieveAllByValoration8_invoker.call(
          req => RecipeController_1.retrieveAllByValoration(req, point))
      }
  
    // @LINE:54
    case controllers_RecipeController_update9_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_update9_invoker.call(
          req => RecipeController_1.update(req, id))
      }
  
    // @LINE:59
    case controllers_RecipeController_delete10_route(params@_) =>
      call(params.fromPath[Integer]("id", None)) { (id) =>
        controllers_RecipeController_delete10_invoker.call(
          req => RecipeController_1.delete(req, id))
      }
  
    // @LINE:64
    case controllers_RecipeController_valorateRecipe11_route(params@_) =>
      call(params.fromPath[Integer]("id", None), params.fromPath[Integer]("point", None)) { (id, point) =>
        controllers_RecipeController_valorateRecipe11_invoker.call(
          req => RecipeController_1.valorateRecipe(req, id, point))
      }
  
    // @LINE:74
    case controllers_RecipeController_retrieveAll12_route(params@_) =>
      call { 
        controllers_RecipeController_retrieveAll12_invoker.call(
          req => RecipeController_1.retrieveAll(req))
      }
  
    // @LINE:79
    case controllers_Assets_versioned13_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned13_invoker.call(Assets_2.versioned(path, file))
      }
  }
}
