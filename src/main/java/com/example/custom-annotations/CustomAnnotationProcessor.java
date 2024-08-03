
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
package com.example.custom-annotations;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CustomAnnotationProcessor {
    public static void main(String[] args) {
        String className = "com.example.custom-annotations.MyClass";
        String filePath = "src/main/java/com/example/custom-annotations/MyClass.java";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check for custom annotation syntax
                if (line.trim().startsWith("// #CustomAnnotation(")) {
                    String annotationValue = extractAnnotationValue(line);
                    System.out.println("Found custom annotation with value: " + annotationValue);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String extractAnnotationValue(String line) {
        int startIndex = line.indexOf("\"") + 1;
        int endIndex = line.lastIndexOf("\"");
        return line.substring(startIndex, endIndex);
    }
}

