package com.vitily.common.util.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * creator : whh-lether
 * date    : 2018/12/4 16:07
 * desc    :
 **/
public class Scale2DoubleSerializer extends JsonSerializer<Double>{
    private DecimalFormat df = new DecimalFormat("##.00");
    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        if(value != null) {
            //gen.writeString(df.format(value));
            gen.writeNumber(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP));
        }
    }
}
