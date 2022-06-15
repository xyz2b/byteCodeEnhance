package org.xyzjiao.test;

import javassist.*;

import java.io.IOException;

public class Test {
    public static void main(String[] args)  {
        ClassPool pool = ClassPool.getDefault();

        String packetName = "org.xyzjiao.bytecode.enhance";
        String className = "TestTest";

        CtClass clzz = pool.makeClass(packetName + "." + className);

        try {
            CtField field1 = CtField.make("private String s;", clzz);
            clzz.addField(field1);

            CtField field2 = CtField.make("private int n = 10;", clzz);
            clzz.addField(field2);

            CtConstructor defaultConstructor = CtNewConstructor.defaultConstructor(clzz);
            clzz.addConstructor(defaultConstructor);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("public " + className + "(int n, String s) {");
            stringBuilder.append("this.n = n;");
            stringBuilder.append("this.s = s;");
            stringBuilder.append("}");
            CtConstructor constructor1 = CtNewConstructor.make(stringBuilder.toString(), clzz);
            clzz.addConstructor(constructor1);

        } catch (CannotCompileException e) {
            e.printStackTrace();
        }

        try {
            clzz.writeFile();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }
}
