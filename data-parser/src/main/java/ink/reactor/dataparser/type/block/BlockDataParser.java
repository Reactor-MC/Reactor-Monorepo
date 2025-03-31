package ink.reactor.dataparser.type.block;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import ink.reactor.dataparser.DataParser;
import ink.reactor.dataparser.common.ParserClass;
import ink.reactor.dataparser.common.ParserFiles;
import ink.reactor.dataparser.type.block.method.BlockMethodCodeGenerator;
import ink.reactor.dataparser.type.block.method.StringCodeGenerator;
import ink.reactor.dataparser.type.block.properties.BlockProperties;
import ink.reactor.fission.classes.JavaClass;
import ink.reactor.fission.classes.enums.JavaEnum;
import ink.reactor.fission.commentary.MultiLineCommentary;
import ink.reactor.fission.field.JavaField;
import ink.reactor.fission.field.JavaFields;
import ink.reactor.fission.method.JavaMethod;
import ink.reactor.fission.method.JavaMethodParameter;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
    Parse blocks searching common patterns in definition types.
    Example:
        All buttons share the same properties and states (only id is different).
        So, we can create a class called "Button" and calculate the id with the input parameters:
        Button.of(Blocks.ACACIA_BUTTON, BlockFace.FLOOR, BlockFacing.NORTH, true); -> Return the block id

    This increase HEAVILY the performance, reduce object creations and is more developer friendly.
    Because we don't need a registry and store all blocks individually
*/
public class BlockDataParser implements DataParser {
    private String packageName;
    private JavaClass blocksClass;
    private final Map<String, JavaClass> classByType = new HashMap<>();
    private BlockProperties blockProperties;

    @Override
    public void parse(final String packageName) throws IOException {
        this.packageName = packageName;
        this.blockProperties = new BlockProperties(packageName);

        blocksClass = new JavaClass(packageName, "Blocks");
        blocksClass.setFinal(true);
        blocksClass.setCommentary(MultiLineCommentary.of(ParserClass.DATA_GENERATOR));

        final JSONObject jsonObject = ParserFiles.loadJsonObject("blocks.json");

        // TODO: Optimize this (adding more code generators). Example: if all method parameters are booleans, use a lut table
        final BlockMethodCodeGenerator codeGenerator = new StringCodeGenerator();

        parseBlocks(jsonObject.entrySet(), codeGenerator);

        final Collection<JavaClass> classByTypesCollection = classByType.values();
        for (final JavaClass javaClass : classByTypesCollection) {
            ParserFiles.writeJava(javaClass.toString(), javaClass.getClassName());
        }

        final Collection<JavaEnum> propertiesClass = blockProperties.getProperties().values();
        for (final JavaEnum javaEnum : propertiesClass) {
            ParserFiles.writeJava(javaEnum.toString(), javaEnum.getClassName());
        }

        ParserFiles.writeJava(blocksClass.toString(), "Blocks");
    }

    private void parseBlocks(final Set<Map.Entry<String, Object>> blocks, final BlockMethodCodeGenerator codeGenerator) {
        for (final Map.Entry<String, Object> entry : blocks) {
            if (!(entry.getValue() instanceof JSONObject block)) {
                continue;
            }
            final String blockName = entry.getKey().split(":")[1].toUpperCase(); // Remove "minecraft:" prefix
            final JSONObject definition = block.getJSONObject("definition");
            final JSONArray blockStates = block.getJSONArray("states");
            final JSONObject defaultState = getDefaultState(blockStates);

            if (defaultState == null) {
                System.out.println("Can't found the default state of the block " + blockName + ". Invalid json?");
                continue;
            }

            final String type = definition.getString("type");

            final JSONObject properties = block.getJSONObject("properties");
            if (properties == null || properties.isEmpty()) { // Blocks without any properties, example: air, acacia planks, bamboo mosaic, etc
                addStaticFieldToBlockClass(defaultState, blockName);
                continue;
            }

            blockProperties.createPropertyClasses(properties);

            JavaClass javaClass = classByType.get(type);
            if (javaClass != null) {
                addStaticFieldToBlockClass(defaultState, blockName);
                continue;
            }

            javaClass = new JavaClass(packageName, BlockParserUtils.toClassName(type));
            javaClass.setFinal(true);
            classByType.put(type, javaClass);

            parseBlock(defaultState, blockStates, javaClass, codeGenerator);
        }
    }

    private void parseBlock(
        final JSONObject defaultState,
        final JSONArray blockStates,
        final JavaClass javaClass,
        final BlockMethodCodeGenerator codeGenerator
    ) {
        int defaultId = defaultState.getIntValue("id");
        JSONObject properties = defaultState.getJSONObject("properties");

        if (defaultId == -1) {
            System.out.println("Can't found the default id for the javaclass " + javaClass.getClassName() + ". Invalid json");
            return;
        }

        final JavaMethod javaMethod = new JavaMethod("of");
        javaMethod.setReturnObjectType("int");
        javaMethod.addParameters(new JavaMethodParameter("char", "id", true));
        javaMethod.setStatic(true);

        blockProperties.addMethodParameters(javaMethod, properties);
        javaMethod.setCodeBlock(codeGenerator.generateMethodCode(javaMethod, blockStates, defaultId));

        javaClass.addMethods(javaMethod);
    }

    private void addStaticFieldToBlockClass(final JSONObject defaultState, final String blockName) {
        final JavaField defaultBlockField = JavaFields.STATIC_CONSTANTS.ofInt(blockName, defaultState.getIntValue("id"));
        defaultBlockField.setType("char");
        blocksClass.addFields(defaultBlockField);
    }

    private JSONObject getDefaultState(final JSONArray blockStates) {
        for (final Object object : blockStates) {
            if (object instanceof JSONObject state && state.getBoolean("default") != null) {
                return state;
            }
        }
        return null;
    }
}