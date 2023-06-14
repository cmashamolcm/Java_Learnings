package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableScheduling
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        ApplicationContext cxt = SpringApplication.run(App.class, args);
        cxt.getBean(MyBean.class).show();
        cxt.getBean("my-bean-1", MyBean.class)
                .show();
        //cxt.getBean(App.class).show();

        getDeveloper(cxt);

    }

    private static void getDeveloper( ApplicationContext cxt) {
        Developer dev = cxt.getBean(Developer.class);
        dev.laptop.printType();
        dev.desktop.printType();

    }

    void show(){
        System.out.println("Hello from App bean");
    }
}
