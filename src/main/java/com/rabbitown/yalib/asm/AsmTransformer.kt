package com.rabbitown.yalib.asm

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import java.lang.instrument.ClassFileTransformer
import java.security.ProtectionDomain

/**
 * @author Milkory
 */
class AsmTransformer : ClassFileTransformer {

    override fun transform(
        loader: ClassLoader?,
        className: String?,
        classBeingRedefined: Class<*>?,
        protectionDomain: ProtectionDomain?,
        classfileBuffer: ByteArray?
    ): ByteArray {
        val reader = ClassReader(classfileBuffer)
        val writer = ClassWriter(reader, ClassWriter.COMPUTE_MAXS)
        val visitor = AsmVisitor()
        reader.accept(visitor, 0)
        return writer.toByteArray()
    }

}