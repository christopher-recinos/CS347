/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.ParameterNameAware;
import java.util.Map;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 * This parent class makes sure that the action classes have access to the Session object and Parameter Names
 * @author greatwmc
 */
public class FetchSessionAware extends ActionSupport implements SessionAware, ParameterNameAware {
    
    SessionMap<String, Object> session;
    
    /**
     * This allows access to the session so that the userId can be accessed
     *
     * @param session
     */
    @Override
    public void setSession(Map<String, Object> session) {

        this.session = (SessionMap)session;
    }

    /**
     * This stops the user from hacking the URL request parameters
     *
     * @param parameterName
     * @return
     */
    @Override
    public boolean acceptableParameterName(String parameterName) {

        boolean allowedParameterName = true;

        if (parameterName.contains("session") || parameterName.contains("request")) {
            allowedParameterName = false;
        }
        return allowedParameterName;
    }
}
