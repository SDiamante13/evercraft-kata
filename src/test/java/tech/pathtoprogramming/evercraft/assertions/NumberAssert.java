package tech.pathtoprogramming.evercraft.assertions;

import org.assertj.core.api.AbstractAssert;

public class NumberAssert extends AbstractAssert<NumberAssert, Integer> {

    public NumberAssert(Integer actual) {
        super(actual, NumberAssert.class);
    }

    public static NumberAssert assertThat(Integer actual) {
        return new NumberAssert(actual);
    }

    public NumberAssert isTen() {
        isNotNull();
        if (!actual.equals(10)) {
            failWithMessage("Expected number to be ten");
        }

        return this;
    }
}
