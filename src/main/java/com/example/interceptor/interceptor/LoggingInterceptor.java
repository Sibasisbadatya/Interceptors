package com.example.interceptor.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

//        in the image of img.png u can see that HandlerMapping does all the mappings .
//        like for which endpoints which controller should be called .
//        and HandlerAdapter calls that controller method

//        and above all are controller by Dispatcher Servlet.

        if(handler instanceof HandlerMethod handlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            String beanName = method.getBeanType().getName();
            String methodName = method.getMethod().getName();
            System.out.println("Bean Name" + beanName);
            System.out.println("Method Name" + methodName);
            System.out.println("Pre Handle");
        }

        System.out.println("HTTP Method"+request.getMethod());
        System.out.println("Request URI"+request.getRequestURI());
        System.out.println("Request Parameteres"+request.getQueryString());
//        similarly we can get related info like ip adress,headers etc.
        return true;//true means we want proceeed ahead )if any intercepror is lined up it goes to that interceptor otherwise
//        proceed to controller
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
//        ModelAndView is used if we want to send any views or HTML JSP Pages instead of Rest APIS.
        System.out.println("After Completion");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("After Completion");
    }
}

//1. preHandle()
//Before the controller executes
//
//2. Controller executes
//Processes the request and returns a result
//
//3. postHandle()
//After controller execution, before view rendering
//
//4. View rendering / response processing
//Produces the response
//
//5. afterCompletion()
//Final cleanup and request logging

//For a traditional MVC controller returning a view name, the output is typically:

//The view rendering happens between postHandle() and afterCompletion(),



//5. When should you use each one?
//Use postHandle() when:
//        - You need to add attributes to a ModelAndView.
//        - You want to perform work after the controller returns but before a traditional MVC view is rendered.
//        Use afterCompletion() when:
//        - You want to log request completion.
//        - You want to record request-processing duration.
//        - You need to clean up resources associated with the request.
//        - You want to inspect an exception after request processing.