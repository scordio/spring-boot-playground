package io.github.scordio.playground.nocheckreturnvalue;

import org.assertj.core.api.AbstractAssert;

public class NoCheckReturnValue extends AbstractAssert<NoCheckReturnValue, Object> {

	NoCheckReturnValue() {
		super(null, NoCheckReturnValue.class);
	}

	public Object notReturningSelf() {
		return new Object();
	}

}
