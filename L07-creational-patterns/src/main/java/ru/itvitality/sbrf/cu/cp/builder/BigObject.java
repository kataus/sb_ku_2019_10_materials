package ru.itvitality.sbrf.cu.cp.builder;

public class BigObject {
    private final String param1;
    private final String param2;
    private final String param3;
    private final String param4;
    private final String param5;

    private BigObject(BigObjectBuilder builder) {
        this.param1 = builder.param1;
        this.param2 = builder.param2;
        this.param3 = builder.param3;
        this.param4 = builder.param4;
        this.param5 = builder.param5;
        Object o;
    }

    @Override
    public String toString() {
        return "BigObject{" +
                "param1='" + param1 + '\'' +
                ", param2='" + param2 + '\'' +
                ", param3='" + param3 + '\'' +
                ", param4='" + param4 + '\'' +
                ", param5='" + param5 + '\'' +
                '}';
    }

    public static class BigObjectBuilder {
        private String param1;
        private String param2;
        private String param3;
        private String param4;
        private String param5;

        public BigObjectBuilder(String param1) {
            this.param1 = param1; //mandatory
        }

        public BigObjectBuilder withParam2(String param2) {
            this.param2 = param2;
            return this; //fluent
        }

        public BigObjectBuilder withParam3(String param3) {
            this.param3 = param3;
            return this;
        }

        public BigObjectBuilder withParam4(String param4) {
            this.param4 = param4;
            return this;
        }

        public BigObjectBuilder withParam5(String param5) {
            this.param5 = param5;
            return this;
        }

        public BigObject build() {
            if (param1 == null || param2 == null) {
                // 50 lines of code
                throw new IllegalStateException();
            }
            return new BigObject(this);
        }
    }

}
