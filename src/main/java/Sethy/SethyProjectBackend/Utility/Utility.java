package Sethy.SethyProjectBackend.Utility;

import javax.servlet.http.HttpServletRequest;

public class Utility {
    public static String getSiteURL(HttpServletRequest request){
        String siteURL = request.getRequestURL().toString();
        return "https://sethy.netlify.app"; //siteURL.replace(request.getServletPath(),"");
    }
}
