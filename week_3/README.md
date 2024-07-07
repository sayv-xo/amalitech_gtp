Here's a comprehensive README file for your sorting application using Spring Framework without Spring Boot:

---

# Sorting Algorithms Application

This is a demo application showcasing the use of various sorting algorithms implemented within a Spring MVC web application. The application demonstrates dependency injection, autowiring, inversion of control, and the use of Java-based Spring configurations.

## Table of Contents

1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Installation](#installation)
4. [Usage](#usage)
5. [Project Structure](#project-structure)
6. [Classes and Configuration](#classes-and-configuration)
7. [Sorting Algorithms](#sorting-algorithms)
8. [Error Handling](#error-handling)
9. [Styling](#styling)
10. [Contributing](#contributing)
11. [License](#license)

## Features

- Implemented sorting algorithms: Quick Sort, Heap Sort, Merge Sort, Radix Sort, and Bucket Sort.
- User-friendly web interface to input arrays and select sorting algorithms.
- Java-based Spring configuration without using Spring Boot.
- Error handling for invalid input formats.
- Clear and styled user interface using JSP and CSS.

## Technologies Used

- Java 21
- Spring Framework 5.3.34
- JSP
- Maven
- Tomcat
- Jakarta Servlet 5.0.0
- JSTL

## Installation

### Prerequisites

- Java Development Kit (JDK) 21 or higher
- Apache Maven 3.6 or higher
- Apache Tomcat 9.0 or higher

### Steps

1. Clone the repository:

2. Navigate to the project directory: 

3. Build the project using Maven:

```bash
mvn clean install
```

4. Deploy the WAR file to your Tomcat server:

   - Copy the generated `ssort.war` file from the `target` directory to the `webapps` directory of your Tomcat server.
   - Start the Tomcat server if it's not already running.

5. Access the application at `http://localhost:8080/`.

## Usage

1. Open the application in your web browser.
2. Input an array of numbers separated by spaces, commas, or other whitespace characters.
3. Select a sorting algorithm from the dropdown menu.
4. Click the "Sort" button to sort the array.
5. View the sorted array on the results page.

## Project Structure

```
sorting-app/
│
├── src/main/java/com/example/sorting/
│   ├── config/
│   │   ├── WebAppInitializer.java
│   │   └── WebMvcConfig.java
│   ├── controller/
│   │   └── SortController.java
│   ├── service/
│   │   ├── SortingService.java
│   │   └── algorithms/
│   │       ├── HeapSort.java
│   │       ├── QuickSort.java
│   │       ├── MergeSort.java
│   │       ├── RadixSort.java
│   │       └── BucketSort.java
│   └── model/
│       └── SortedArrayResource.java
│
├── src/main/webapp/WEB-INF/views/
│   ├── index.jsp
│   └── sort.jsp
│
├── src/main/resources/
│   └── log4j.properties
│
├── pom.xml
└── README.md
```

## Classes and Configuration

### WebAppInitializer

```java
package com.example.sorting.Config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebMvcConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
```

### WebMvcConfig

```java
package com.example.sorting.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.example.sorting"})
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
```

### Error Handling

Error handling is done in the `SortController` class. If the input format is invalid, an error message is added to the model and the user is redirected back to the index page with the error message displayed.

### Styling

#### CSS

Inline styling was used

### Contributing

Feel free to fork the repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

