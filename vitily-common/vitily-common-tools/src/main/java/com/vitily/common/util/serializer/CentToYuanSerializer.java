package com.vitily.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vitily.common.util.NumberUtil;

import java.io.IOException;
import java.text.DecimalFormat;

public class CentToYuanSerializer extends JsonSerializer<Long> {
    private DecimalFormat df = new DecimalFormat("##.00");
    @Override
    public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value != null) {
            //gen.writeString(NumberUtil.getScale2MDownYuanByCent((long)value).toString());
            //gen.writeNumber(NumberUtil.getScale2MDownYuanByCent(value));
            String val = NumberUtil.respScale2MDownYuanByCent(value);
            gen.writeNumber(val);
        }
    }
}
