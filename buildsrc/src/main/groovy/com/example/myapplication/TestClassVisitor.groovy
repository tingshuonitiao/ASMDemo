package com.example.myapplication

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class TestClassVisitor extends ClassVisitor {

    private String className

    TestClassVisitor(ClassVisitor classVisitor) {
        super(Opcodes.ASM5, classVisitor)
    }

    @Override
    void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        this.className = name
        super.visit(version, access, name, signature, superName, interfaces)
    }

    @Override
    MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions)
        return new TestMethodVisitor(mv, className, name)
    }
}