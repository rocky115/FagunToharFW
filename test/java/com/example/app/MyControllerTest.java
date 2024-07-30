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

public class MyControllerTest {

    public static void main(String[] args) {
        testHandleRequest();
    }

    private static void testHandleRequest() {
        // Setup: Create instances of MyService and MyController
        MyService myService = new MyService();
        MyController myController = new MyController();
        
        // Inject MyService into MyController
        myController.setMyService(myService);

        // Redirect System.out to a ByteArrayOutputStream to capture the output
        java.io.ByteArrayOutputStream outputStream = new java.io.ByteArrayOutputStream();
        java.io.PrintStream printStream = new java.io.PrintStream(outputStream);
        java.io.PrintStream originalOut = System.out;
        System.setOut(printStream);

        // Invoke the method to test
        myController.handleRequest();

        // Restore System.out
        System.setOut(originalOut);

        // Verify the output
        String output = outputStream.toString().trim();
        if ("Hello, world!".equals(output)) {
            System.out.println("testHandleRequest PASSED");
        } else {
            System.out.println("testHandleRequest FAILED: expected 'Hello, world!', but got '" + output + "'");
        }
    }
}

