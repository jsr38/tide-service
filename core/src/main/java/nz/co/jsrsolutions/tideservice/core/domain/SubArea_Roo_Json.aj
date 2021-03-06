// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.SubArea;

privileged aspect SubArea_Roo_Json {
    
    public String SubArea.toJson() {
        return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
    
    public static SubArea SubArea.fromJsonToSubArea(String json) {
        return new JSONDeserializer<SubArea>().use(null, SubArea.class).deserialize(json);
    }
    
    public static String SubArea.toJsonArray(Collection<SubArea> collection) {
        return new JSONSerializer().exclude("*.class").deepSerialize(collection);
    }
    
    public static Collection<SubArea> SubArea.fromJsonArrayToSubAreas(String json) {
        return new JSONDeserializer<List<SubArea>>().use(null, ArrayList.class).use("values", SubArea.class).deserialize(json);
    }
    
}
