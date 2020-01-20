package ru.itvitality.sbrf.cu.app.common;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SerializersTest {


  @Test
  public void serializeDeSerialize() throws Exception {
    TestData testData = new TestData(1, "str", 2);

    byte[] data = Serializers.serialize(testData);

    TestData object = Serializers.deserialize(data, TestData.class);
    assertThat(object).isEqualTo(testData);
  }


}
