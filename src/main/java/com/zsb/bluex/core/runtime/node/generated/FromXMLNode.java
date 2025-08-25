package com.zsb.bluex.core.runtime.node.generated;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.zsb.bluex.core.runtime.ExecutionContext;
import com.zsb.bluex.core.runtime.node.PureNode;

public class FromXMLNode extends PureNode {
    public Class<?> clazz;

    public FromXMLNode(String id, Class<?> clazz) {
        super(id, "FromXMLNode" + "->" + clazz.toString());
        this.clazz = clazz;
    }

    @Override
    public Object evaluate(String outputName, ExecutionContext ctx) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        return xmlMapper.readValue((String) inputParams.get("Xml").getValue(ctx), clazz);
    }
}
