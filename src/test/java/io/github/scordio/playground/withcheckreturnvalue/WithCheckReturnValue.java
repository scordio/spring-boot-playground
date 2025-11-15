package io.github.scordio.playground.withcheckreturnvalue;

import org.assertj.core.api.AbstractAssert;
import org.springframework.lang.CheckReturnValue;

public class WithCheckReturnValue extends AbstractAssert<WithCheckReturnValue, Object> {

	WithCheckReturnValue() {
		super(null, WithCheckReturnValue.class);
	}

	@CheckReturnValue
	public Object notReturningSelf() {
		return new Object();
	}

	@Override
	public WithCheckReturnValue isEqualTo(Object expected) {
		return super.isEqualTo(expected);
	}

}
