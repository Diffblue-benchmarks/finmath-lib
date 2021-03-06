package net.finmath.montecarlo.automaticdifferentiation;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import net.finmath.montecarlo.AbstractRandomVariableFactory;
import net.finmath.montecarlo.RandomVariableFactory;
import net.finmath.montecarlo.automaticdifferentiation.backward.RandomVariableDifferentiableAADFactory;
import net.finmath.montecarlo.automaticdifferentiation.forward.RandomVariableDifferentiableADFactory;
import net.finmath.stochastic.RandomVariable;

/**
 * Basic test for RandomVariableDifferentiableAAD.
 *
 * @author Christian Fries
 */
@RunWith(Parameterized.class)
public class RandomVariableDifferentiableTypePriorityTest {

	/* parameters specify the factories one wants to test against each other */
	@Parameters
	public static Collection<Object[]> data(){
		return Arrays.asList(new Object[][] {
			{ new RandomVariableFactory(true  /* isUseDoublePrecisionFloatingPointImplementation */), new RandomVariableDifferentiableAADFactory(new RandomVariableFactory(true  /* isUseDoublePrecisionFloatingPointImplementation */)) },
			{ new RandomVariableFactory(false /* isUseDoublePrecisionFloatingPointImplementation */), new RandomVariableDifferentiableAADFactory(new RandomVariableFactory(false /* isUseDoublePrecisionFloatingPointImplementation */)) },
			{ new RandomVariableFactory(true  /* isUseDoublePrecisionFloatingPointImplementation */), new RandomVariableDifferentiableADFactory(new RandomVariableFactory(true  /* isUseDoublePrecisionFloatingPointImplementation */)) },
			{ new RandomVariableFactory(false /* isUseDoublePrecisionFloatingPointImplementation */), new RandomVariableDifferentiableADFactory(new RandomVariableFactory(false /* isUseDoublePrecisionFloatingPointImplementation */)) },
		});
	}

	private final AbstractRandomVariableFactory randomVariableFactoryValue;
	private final AbstractRandomVariableFactory randomVariableFactoryDifferentiable;

	public RandomVariableDifferentiableTypePriorityTest(AbstractRandomVariableFactory randomVariableFactoryValue, AbstractRandomVariableDifferentiableFactory randomVariableFactoryDifferentiable) {
		this.randomVariableFactoryValue = randomVariableFactoryValue;
		this.randomVariableFactoryDifferentiable = randomVariableFactoryDifferentiable;
	}

	@Test
	public void testTypePriorityAdd() {

		RandomVariable x = randomVariableFactoryDifferentiable.createRandomVariable(2.0);
		RandomVariable y = randomVariableFactoryValue.createRandomVariable(3.0);

		System.out.println("Checking the return type of operators upon commutation:");

		/*
		 * add
		 */

		RandomVariable z1 = x.add(y);
		System.out.println("Value:" + z1.getAverage() + "\t Class" + z1.getClass());
		Assert.assertSame("Return type class", x.getClass(), z1.getClass());

		RandomVariable z2 = y.add(x);
		System.out.println("Value:" + z2.getAverage() + "\t Class" + z2.getClass());
		Assert.assertSame("Return type class", x.getClass(), z2.getClass());	// Applying to y we expect the class of x

		System.out.println();

		Assert.assertEquals("Value upon commutation", z1.getAverage(), z2.getAverage(), 0.0);
	}

	@Test
	public void testTypePriorityMult() {

		RandomVariable x = randomVariableFactoryDifferentiable.createRandomVariable(2.0);
		RandomVariable y = randomVariableFactoryValue.createRandomVariable(3.0);

		System.out.println("Checking the return type of operators upon commutation:");

		/*
		 * mult
		 */

		RandomVariable z1 = x.mult(y);
		System.out.println("Value:" + z1.getAverage() + "\t Class" + z1.getClass());
		Assert.assertSame("Return type class", x.getClass(), z1.getClass());

		RandomVariable z2 = y.mult(x);
		System.out.println("Value:" + z2.getAverage() + "\t Class" + z2.getClass());
		Assert.assertSame("Return type class", x.getClass(), z2.getClass());	// Applying to y we expect the class of x

		Assert.assertEquals("Value upon commutation", z1.getAverage(), z2.getAverage(), 0.0);
	}

	@Test
	public void testTypePriorityCap() {

		RandomVariable x = randomVariableFactoryDifferentiable.createRandomVariable(2.0);
		RandomVariable y = randomVariableFactoryValue.createRandomVariable(3.0);

		System.out.println("Checking the return type of operators upon commutation:");

		/*
		 * cap
		 */

		RandomVariable z1 = x.cap(y);
		System.out.println("Value:" + z1.getAverage() + "\t Class" + z1.getClass());
		Assert.assertSame("Return type class", x.getClass(), z1.getClass());

		RandomVariable z2 = y.cap(x);
		System.out.println("Value:" + z2.getAverage() + "\t Class" + z2.getClass());
		Assert.assertSame("Return type class", x.getClass(), z2.getClass());	// Applying to y we expect the class of x

		Assert.assertEquals("Value upon commutation", z1.getAverage(), z2.getAverage(), 0.0);
	}

	@Test
	public void testTypePriorityFloor() {

		RandomVariable x = randomVariableFactoryDifferentiable.createRandomVariable(2.0);
		RandomVariable y = randomVariableFactoryValue.createRandomVariable(3.0);

		System.out.println("Checking the return type of operators upon commutation:");

		/*
		 * floor
		 */

		RandomVariable z1 = x.floor(y);
		System.out.println("Value:" + z1.getAverage() + "\t Class" + z1.getClass());
		Assert.assertSame("Return type class", x.getClass(), z1.getClass());

		RandomVariable z2 = y.floor(x);
		System.out.println("Value:" + z2.getAverage() + "\t Class" + z2.getClass());
		Assert.assertSame("Return type class", x.getClass(), z2.getClass());	// Applying to y we expect the class of x

		Assert.assertEquals("Value upon commutation", z1.getAverage(), z2.getAverage(), 0.0);
	}
}
