package ink.reactor.dataparser.common;

import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.method.JavaGetterAndSetters;
import ink.reactor.fission.method.JavaMethod;

public class DataParserTemplateCommon {

    public static JavaClass createTemplate(final Class<?> clazz, final String className, final String packageName) {
        return createTemplate(clazz, className, packageName, ParserClass.combineCommentaries(ParserClass.JSON_COMBINED, ParserClass.DATA_GENERATOR));
    }

    public static JavaClass createTemplate(final Class<?> clazz, final String className, final String packageName, final String commentary) {
        final JavaClass javaClass = ParserClass.immutableClass(packageName, className, clazz, commentary);
        JavaGetterAndSetters.DEFAULT.addGetters(javaClass);
        return javaClass;
    }

    public static void addVanillaData(final String className, final JavaClass javaClass) {
        final VanillaCommons vanillaCommons = VanillaCommons.builder().copyParameters(true).build();
        vanillaCommons.addAllField(javaClass);

        final JavaMethod vanillaMethod = vanillaCommons.createVanillaMethod(javaClass);
        vanillaCommons.fillVanillaMethodCode(vanillaMethod, className);

        javaClass.addMethods(vanillaMethod);
    }
}
