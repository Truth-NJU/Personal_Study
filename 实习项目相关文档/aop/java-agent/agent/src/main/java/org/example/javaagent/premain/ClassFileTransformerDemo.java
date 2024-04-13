package org.example.javaagent.premain;

import javassist.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

public class ClassFileTransformerDemo implements ClassFileTransformer {

    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) {
//        System.out.println("className: " + className);
        if (!className.equalsIgnoreCase("com/zuozewei/Dog")) {
            return null;
        }
//        return getBytesFromFile("C:\\Users\\Klein.Tao\\Desktop\\task\\javaagent\\example01\\src\\main\\java\\com\\zuozewei\\Dog.java");
        try {
            ClassPool pool = ClassPool.getDefault();
            String fullClassName = className.replace("/", ".");
            CtClass ctClass = pool.get(fullClassName);
            String methodName = "bark";
            CtMethod ctMethod = ctClass.getDeclaredMethod(methodName);
            String newMethodName = methodName + "$impl";
            ctMethod.setName(newMethodName);
            CtMethod newMethod = CtNewMethod.copy(ctMethod, methodName, ctClass, null);
            StringBuilder bodyStr = new StringBuilder();
            bodyStr.append("{");
            bodyStr.append("long start=System.nanoTime();\n");
            bodyStr.append(newMethodName + "($$);\n");
            bodyStr.append("long end= System.nanoTime();");
            bodyStr.append("\nSystem.out.println(\"the dog barks \"+(end-start)+\"s\");");
            bodyStr.append("}");
            newMethod.setBody(bodyStr.toString());
            ctClass.addMethod(newMethod);
            return ctClass.toBytecode();
        } catch (Exception e) {
            return null;
        }

    }

}
