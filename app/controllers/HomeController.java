package controllers;

import play.mvc.*;

import java.util.Optional;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */
    public Result index(Http.Request req) {

            return Results.ok("TRABAJO FINAL SERVICIOS REST - CLOUD 2022/2023 \n\n " +
                            "Miguel Hernandez Corral - 70901709T - mhernandezco.inf@upsa.es \n\n " +
                            "Silvia Martin Maria - 70909468P - smartinma02@upsa.es \n\n " +
                            "Master MIMO Upsa \n\n\n" +
                            "*** Aplicacion en funcionamiento... ***")
                    .as("text/plain")
                    .withHeader("mi-cabecera", "mi-valor");

        }

    }

