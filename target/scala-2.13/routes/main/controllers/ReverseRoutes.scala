// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset
import _root_.play.libs.F

// @LINE:9
package controllers {

  // @LINE:9
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:9
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }

  // @LINE:14
  class ReverseRecipeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:34
    def retrieveAllByIngredient(ingredient:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/ingredient/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("ingredient", ingredient)))
    }
  
    // @LINE:39
    def retrieveAllByTime(time:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/time/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("time", time)))
    }
  
    // @LINE:59
    def delete(id:Integer): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:44
    def retrieveByTitle(title:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/title/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("title", title)))
    }
  
    // @LINE:54
    def update(id:Integer): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:24
    def retrieveAllByType(typeFood:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/type/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("typeFood", typeFood)))
    }
  
    // @LINE:74
    def retrieveAll(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes")
    }
  
    // @LINE:49
    def retrieveAllByValoration(puntuation:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/puntuation/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("puntuation", puntuation)))
    }
  
    // @LINE:29
    def retrieveAllByName(name:String): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipes/name/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[String]].unbind("name", name)))
    }
  
    // @LINE:64
    def valorateRecipe(id:Integer): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)) + "/puntuation")
    }
  
    // @LINE:69
    def inputIngredientsRecipe(id:Integer): Call = {
      
      Call("PATCH", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)) + "/ingredient")
    }
  
    // @LINE:19
    def retrieveById(id:Integer): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "recipe/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Integer]].unbind("id", id)))
    }
  
    // @LINE:14
    def create(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "recipes")
    }
  
  }

  // @LINE:79
  class ReverseAssets(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:79
    def versioned(file:Asset): Call = {
      implicit lazy val _rrc = new play.core.routing.ReverseRouteContext(Map(("path", "/public"))); _rrc
      Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[play.api.mvc.PathBindable[Asset]].unbind("file", file))
    }
  
  }


}
