package org.koushik.javabrains.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamUsingAnnotations(@MatrixParam("param") String matrixParam,
			@HeaderParam("authSessionID") String authSessionID, @CookieParam("name") String cookie) {
		return "Test Param :" + matrixParam + " Header Param : " + authSessionID + " Cookie : " + cookie;
	}

	@GET
	@Path("context")
	public String getParamUsingContext(@Context UriInfo uriInfo, @Context HttpHeaders headers) {
		String path = uriInfo.getAbsolutePath().toString();
		String cookies = headers.getCookies().toString();
		return "Path : " + path + " Cookies : " + cookies;
	}

}
