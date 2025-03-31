package ink.reactor.dataparser.type.block.method;

import com.alibaba.fastjson2.JSONArray;
import ink.reactor.fission.method.JavaMethod;


public interface BlockMethodCodeGenerator {
    String generateMethodCode(final JavaMethod javaMethod, final JSONArray blockStates, final int defaultId);
}
