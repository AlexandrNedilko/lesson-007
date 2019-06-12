package Serializable;

import java.lang.annotation.*;

@Inherited
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@interface SaveTo {
    String path() default "c:\\temp\\file.txt";

    //<method> method PATH(method);
}
