/*

*Copyright (c) 2009 Radhamadhab Dalai
*Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation
the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and
to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above
copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO
THE WARRANTIES OF MERCHANTABILITY,FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.

 */
package com.example.framework.mvc;

import com.example.framework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class DispatcherServlet extends HttpServlet {
    private ApplicationContext applicationContext;
    private RequestMapping requestMapping;

    @Override
    public void init(ServletConfig config) {
        applicationContext = new ApplicationContext(getBeanDefinitions());
        requestMapping = new RequestMapping();
        initHandlerMappings();
    }

    private Map<String, Class<?>> getBeanDefinitions() {
        Map<String, Class<?>> beanDefinitions = new HashMap<>();
        beanDefinitions.put("MyController", com.example.app.MyController.class);
        beanDefinitions.put("MyService", com.example.app.MyService.class);
        return beanDefinitions;
    }

    private void initHandlerMappings() {
        requestMapping.registerMapping("/hello", "handleRequest");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doDispatch(request, response);
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String methodName = requestMapping.getMethodName(uri);

        if (methodName != null) {
            try {
                Object controller = applicationContext.getBean("MyController");
                Method method = controller.getClass().getMethod(methodName);
                method.invoke(controller);
            } catch (Exception e) {
                throw new ServletException("Failed to handle request", e);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}

