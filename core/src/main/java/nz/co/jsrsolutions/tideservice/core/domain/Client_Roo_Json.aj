// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package nz.co.jsrsolutions.tideservice.core.domain;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import nz.co.jsrsolutions.tideservice.core.domain.Client;

privileged aspect Client_Roo_Json {
    
    public String Client.toJson() {
        return new JSONSerializer().exclude("*.class").deepSerialize(this);
    }
    
    public static Client Client.fromJsonToClient(String json) {
        return new JSONDeserializer<Client>().use(null, Client.class).deserialize(json);
    }
    
    public static String Client.toJsonArray(Collection<Client> collection) {
        return new JSONSerializer().exclude("*.class").deepSerialize(collection);
    }
    
    public static Collection<Client> Client.fromJsonArrayToClients(String json) {
        return new JSONDeserializer<List<Client>>().use(null, ArrayList.class).use("values", Client.class).deserialize(json);
    }
    
}
