// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.routing.JavaScriptReverseRoute


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:9
package controllers.javascript {

  // @LINE:9
  class ReverseHomeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def index: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.HomeController.index",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + """"})
        }
      """
    )
  
  }

  // @LINE:14
  class ReverseRecipeController(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def retrieveAllByIngredient: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAllByIngredient",
      """
        function(ingredient0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/ingredient/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("ingredient", ingredient0))})
        }
      """
    )
  
    // @LINE:39
    def retrieveAllByTime: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAllByTime",
      """
        function(time0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/time/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("time", time0))})
        }
      """
    )
  
    // @LINE:59
    def delete: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.delete",
      """
        function(id0) {
          return _wA({method:"DELETE", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:44
    def retrieveByTitle: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveByTitle",
      """
        function(title0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/title/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("title", title0))})
        }
      """
    )
  
    // @LINE:54
    def update: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.update",
      """
        function(id0) {
          return _wA({method:"PUT", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:24
    def retrieveAllByType: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAllByType",
      """
        function(typeFood0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/type/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("typeFood", typeFood0))})
        }
      """
    )
  
    // @LINE:74
    def retrieveAll: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAll",
      """
        function() {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes"})
        }
      """
    )
  
    // @LINE:49
    def retrieveAllByValoration: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAllByValoration",
      """
        function(puntuation0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/puntuation/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("puntuation", puntuation0))})
        }
      """
    )
  
    // @LINE:29
    def retrieveAllByName: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveAllByName",
      """
        function(name0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes/name/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[String]].javascriptUnbind + """)("name", name0))})
        }
      """
    )
  
    // @LINE:64
    def valorateRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.valorateRecipe",
      """
        function(id0) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0)) + "/puntuation"})
        }
      """
    )
  
    // @LINE:69
    def inputIngredientsRecipe: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.inputIngredientsRecipe",
      """
        function(id0) {
          return _wA({method:"PATCH", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0)) + "/ingredient"})
        }
      """
    )
  
    // @LINE:19
    def retrieveById: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.retrieveById",
      """
        function(id0) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "recipe/" + encodeURIComponent((""" + implicitly[play.api.mvc.PathBindable[Integer]].javascriptUnbind + """)("id", id0))})
        }
      """
    )
  
    // @LINE:14
    def create: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.RecipeController.create",
      """
        function() {
          return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "recipes"})
        }
      """
    )
  
  }

  // @LINE:79
  class ReverseAssets(_prefix: => String) {

    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:79
    def versioned: JavaScriptReverseRoute = JavaScriptReverseRoute(
      "controllers.Assets.versioned",
      """
        function(file1) {
          return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[play.api.mvc.PathBindable[Asset]].javascriptUnbind + """)("file", file1)})
        }
      """
    )
  
  }


}
