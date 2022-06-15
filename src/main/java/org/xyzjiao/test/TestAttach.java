package org.xyzjiao.test;

import javassist.*;

import java.io.IOException;
import java.lang.reflect.Method;

public class TestAttach {
    public static void main(String[] args) {
        ClassPool pool = ClassPool.getDefault();

        CtClass clz = pool.getOrNull("org.xyzjiao.test.TestClass");

        try {
            CtMethod method = clz.getDeclaredMethod("setAge", new CtClass[] { CtClass.intType });

            method.setBody("{ System.out.println(\"new body\"); }");

            method.addLocalVariable("time", CtClass.longType);

            method.insertBefore("{ long time = System.nanoTime(); }");
            method.insertAfter("{ System.out.println(System.nanoTime() - time); }");


            CtMethod method2 = clz.getDeclaredMethod("test", new CtClass[] { CtClass.intType });
            method2.setBody("{ System.out.println(\"new body\"); }");

            CtMethod method3 = clz.getDeclaredMethod("test", new CtClass[] {});
            method3.setBody("{ System.out.println(\"new body3\"); }");


            clz.writeFile();
        } catch (NotFoundException | CannotCompileException | IOException e) {
            e.printStackTrace();
        }

    }
}
