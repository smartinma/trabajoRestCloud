package templates;

import org.junit.Test;
import play.test.WithApplication;
import play.twirl.api.Content;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplatesRecipeTest extends WithApplication {

    //Test funcional de templates: template de una receta en xml
    @Test
    public void testRecipeViewXML() {

        String name = "fabada", title = "Fabada_Asturiana", typeFood = "Carnivora";
        Integer time = 25;

        Content res = views.xml.recipe.render(name, time, title, typeFood);

        assertEquals("application/xml", res.contentType());
        assertTrue(res.body().contains("<recipe>"));
    }

}
