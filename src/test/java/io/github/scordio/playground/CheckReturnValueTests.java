/*
 * Copyright 2024-2025 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.scordio.playground;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.JavaMethod;
import com.tngtech.archunit.core.domain.JavaModifier;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import io.github.scordio.playground.withcheckreturnvalue.WithCheckReturnValue;
import org.springframework.lang.CheckReturnValue;

import static com.tngtech.archunit.base.DescribedPredicate.not;

@AnalyzeClasses(packagesOf = { //
// 		NoCheckReturnValue.class, //
		WithCheckReturnValue.class //
})
class CheckReturnValueTests {

	@ArchTest
	void test(JavaClasses classes) {
		ArchRule rule = ArchRuleDefinition.methods()
			.that()
			.areDeclaredInClassesThat()
			.implement("org.assertj.core.api.Assert")
			.and()
			.arePublic()
			.and()
			.doNotHaveModifier(JavaModifier.BRIDGE)
			.and(not(returnSelfType()))
			.should()
			.beAnnotatedWith(CheckReturnValue.class)
			.allowEmptyShould(true);

		rule.check(classes);
	}

	private static DescribedPredicate<JavaMethod> returnSelfType() {
		return DescribedPredicate.describe("return self type",
				(method) -> method.getRawReturnType().equals(method.getOwner()));
	}

}
