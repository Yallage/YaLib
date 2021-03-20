package com.rabbitown.yalib.asm

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ModuleVisitor
import org.objectweb.asm.Opcodes

/**
 * @author Milkory
 */
class AsmVisitor : ClassVisitor(Opcodes.ASM7), Opcodes {

    override fun visitModule(name: String?, access: Int, version: String?): ModuleVisitor {
        return super.visitModule(name, access, version)
    }

    private fun replace(source: String?): String {
        if (source == null) return ""
        return source
    }

}