package edu.ssic;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "Avisos", description = "Avisos da UTFPR")
        },
        info = @Info(
                title = "UTFPR Avisos API",
                version = "1.0.0",
                description = "API para consulta de avisos da UTFPR",
                contact = @Contact(
                        name = "Daniel Fonseca",
                        email = "danielfonseca@alunos.utfpr.edu.br",
                        url = "https://www.linkedin.com/in/daniel-fonseca-da-silva-3b2a23233/"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0.html"
                )
        )
)
public class UtfprAvisosApi extends Application {
}
