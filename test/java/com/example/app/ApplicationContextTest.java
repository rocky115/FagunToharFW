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

package com.example.app;

import com.example.framework.context.ApplicationContext;
import java.util.HashMap;
import java.util.Map;

public class ApplicationContextTest {

    public static void main(String[] args) {
        // Define bean definitions
        Map<String, Class<?>> beanDefinitions = new HashMap<>();
        beanDefinitions.put("MyController", MyController.class);
        beanDefinitions.put("MyService", MyService.class);

        // Initialize application context
        ApplicationContext context = new ApplicationContext(beanDefinitions);

        // Run tests
        testGetBean(context);
        testBeanSingleton(context);
    }

    private static void testGetBean(ApplicationContext context) {
        // Retrieve beans from context
        MyController controller = (MyController) context.getBean("MyController");
        MyService service = (MyService) context.getBean("MyService");

        // Ensure beans are not null
        if (controller == null) {
            System.out.println("testGetBean FAILED: Controller is null");
            return;
        }
        if (service == null) {
            System.out.println("testGetBean FAILED: Service is null");
            return;
        }

        // Ensure dependency is injected
        if (controller.getMyService() == null) {
            System.out.println("testGetBean FAILED: MyService is not injected into MyController");
            return;
        }

        if (controller.getMyService() != service) {
            System.out.println("testGetBean FAILED: Injected MyService is not the same instance");
            return;
        }

        System.out.println("testGetBean PASSED");
    }

    private static void testBeanSingleton(ApplicationContext context) {
        // Retrieve beans from context
        MyService service1 = (MyService) context.getBean("MyService");
        MyService service2 = (MyService) context.getBean("MyService");

        // Ensure that the same instance is returned each time
        if (service1 != service2) {
            System.out.println("testBeanSingleton FAILED: Different instances of MyService");
            return;
        }

        System.out.println("testBeanSingleton PASSED");
    }
}

