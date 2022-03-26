package com.example.myapplication


import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class TestMethodVisitor extends MethodVisitor {

    private String className
    private String methodName

    TestMethodVisitor(MethodVisitor methodVisitor,
                      String className,
                      String methodName) {
        super(Opcodes.ASM5, methodVisitor)
        this.className = className
        this.methodName = methodName
    }

    @Override
    void visitCode() {
        // 开始访问方法体
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
        // time1
        mv.visitVarInsn(Opcodes.LSTORE, 1)
        super.visitCode()
    }

    @Override
    void visitInsn(int opcode) {
        // 访问操作符
        if (opcode == Opcodes.ATHROW || (opcode >= Opcodes.IRETURN && opcode <= Opcodes.RETURN)) {
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/System", "currentTimeMillis", "()J", false)
            // time2
            mv.visitVarInsn(Opcodes.LLOAD, 1)
            // time1 var1
            mv.visitInsn(Opcodes.LSUB)
            // diffTime
            mv.visitVarInsn(Opcodes.LSTORE, 2)
            // nothing in the stack
            mv.visitLdcInsn("TestPlugin")
            // TestPlugin
            mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder")
            // TestPlugin sb
            mv.visitInsn(Opcodes.DUP)
            // TestPlugin sb sb
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
            // TestPlugin sb
            mv.visitLdcInsn("${className}#${methodName} function cost :".toString())
            // TestPlugin sb str
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
            // TestPlugin sb
            mv.visitVarInsn(Opcodes.LLOAD, 2)
            // TestPlugin sb diffTime
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false)
            // TestPlugin sb
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
            // TestPlugin sb
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "android/util/Log", "i", "(Ljava/lang/String;Ljava/lang/String;)I", false)
            // int值
            mv.visitInsn(Opcodes.POP)
        }
        super.visitInsn(opcode)
    }
}