package ink.reactor.dataparser.common;

import ink.reactor.fission.JavaVisibility;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.field.JavaField;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.format.JavaFormatter;
import ink.reactor.fission.method.JavaMethod;
import ink.reactor.fission.method.JavaMethodParameter;
import ink.reactor.fission.util.ArrayAppender;
import lombok.Builder;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public @Builder class VanillaCommons {

    public static final VanillaCommons DEFAULT = VanillaCommons.builder().build();

    private boolean copyParameters;

    public void addAllField(final @NonNull JavaClass javaClass) {
        addAllField(javaClass, javaClass.getClassName());
    }

    public void addAllField(final @NonNull JavaClass javaClass, final @NonNull String type) {
        final JavaField field = new JavaField("List<" + type + ">", "ALL");
        field.setStatic(true);
        field.setFinal(true);
        field.setVisibility(JavaVisibility.PUBLIC);
        field.setValue("new ArrayList<>(" + ParserPlaceholders.ALL_VALUES + ")");

        javaClass.addFields(field);
        javaClass.addImports("java.util.List", "java.util.ArrayList");
    }

    public JavaField createVanillaField(final boolean format, final String className, final String fieldName, final Object... parameters) {
        final String parametersString;
        if (format) {
            parametersString = JavaFormatter.format(Arrays.asList(parameters));
        } else {
            final StringBuilder builder = new StringBuilder();
            ArrayAppender.append(parameters, builder);
            parametersString = builder.toString();
        }
        final String code = "vanilla(" + parametersString + ')';
        final JavaField javaField = new JavaField(className, fieldName.toUpperCase(), code);
        return JavaFields.STATIC_CONSTANTS.applyOptions(javaField);
    }

    public JavaField createVanillaField(final String className, final String fieldName, final Object... parameters) {
        return createVanillaField(false, className, fieldName, parameters);
    }

    public JavaMethod createVanillaMethod(@NonNull JavaClass javaClass) {
        final int instanceFields = javaClass.getInstanceFields();
        final List<JavaMethodParameter> methodParameters = new ArrayList<>(instanceFields);
        final Collection<JavaField> fields = javaClass.getFields();
        for (final JavaField field : fields) {
            if (!field.isStatic()) {
                methodParameters.add(new JavaMethodParameter(field.getType(), field.getName()));
            }
        }
        return createVanillaMethod(methodParameters);
    }

    public JavaMethod createVanillaMethod(@NonNull Collection<JavaMethodParameter> parameters) {
        final JavaMethod javaMethod = new JavaMethod("vanilla");
        javaMethod.setStatic(true);
        javaMethod.setVisibility(JavaVisibility.PRIVATE);

        if (copyParameters) {
            final Collection<JavaMethodParameter> copyParameters = new ArrayList<>(parameters.size());
            for (final JavaMethodParameter parameter : parameters) {
                copyParameters.add(parameter.copy());
            }
            parameters = copyParameters;
        }

        javaMethod.setParameters(parameters);
        return javaMethod;
    }

    public void fillVanillaMethodCode(final JavaMethod vanillaMethod, final String className) {
        final StringBuilder builder = new StringBuilder();
        final Collection<JavaMethodParameter> javaMethodParameters = vanillaMethod.getParameters();
        int i = 0;
        for (final JavaMethodParameter parameter : javaMethodParameters) {
            builder.append(parameter.getName());
            if (++i != javaMethodParameters.size()) {
                builder.append(", ");
            }
        }
        fillVanillaMethodCode(vanillaMethod, className, builder.toString());
    }

    public void fillVanillaMethodCode(final @NonNull JavaMethod vanillaMethod, final @NonNull String className, final @NonNull String parameters) {
        vanillaMethod.setCodeBlock(
                "final " + className + " instance = new " + className + "(" + parameters + ");" +
                "\nALL.add(instance);" +
                "\nreturn instance;");

        vanillaMethod.setReturnObjectType(className);
    }
}
