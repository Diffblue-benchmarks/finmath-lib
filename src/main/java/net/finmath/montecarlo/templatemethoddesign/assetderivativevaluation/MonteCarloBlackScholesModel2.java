/*
 * Created on 20.01.2004
 */
package net.finmath.montecarlo.templatemethoddesign.assetderivativevaluation;

import java.time.LocalDateTime;
import java.util.Map;

import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel;
import net.finmath.montecarlo.templatemethoddesign.LogNormalProcess;
import net.finmath.stochastic.RandomVariable;
import net.finmath.time.TimeDiscretization;

/**
 * Monte Carlo simulation of a simple Black-Scholes model for a stock generated discrete process
 *
 * @author Christian Fries
 * @version 1.2
 */
public class MonteCarloBlackScholesModel2 extends LogNormalProcess implements AssetModelMonteCarloSimulationModel {

	private double initialValue;
	private double riskFreeRate;		// Actually the same as the drift (which is not stochastic)
	private double volatility;

	private RandomVariable[]	initialValueVector	= new RandomVariable[1];
	private RandomVariable	drift;
	private RandomVariable	volatilityOnPaths;

	/**
	 * Create a Monte-Carlo simulation using given time discretization.
	 *
	 * @param timeDiscretization The time discretization
	 * @param numberOfPaths The number of Monte-Carlo path to be used
	 * @param initialValue Spot value
	 * @param riskFreeRate The risk free rate
	 * @param volatility The log volatility
	 */
	public MonteCarloBlackScholesModel2(
			TimeDiscretization timeDiscretization,
			int numberOfPaths,
			double initialValue,
			double riskFreeRate,
			double volatility) {
		super(timeDiscretization, 1 /* numberOfComponents */ , 1 /* numberOfFactors */, numberOfPaths, 3141 /* seed */);

		this.initialValue	= initialValue;
		this.riskFreeRate	= riskFreeRate;
		this.volatility		= volatility;

		/*
		 * The interface definition requires that we provide the drift and the volatility in terms of random variables.
		 * We construct the corresponding random variables here and will return (immutable) references to them.
		 */
		this.initialValueVector[0]	= new RandomVariableFromDoubleArray(0.0, initialValue);
		this.drift					= new RandomVariableFromDoubleArray(0.0, riskFreeRate);
		this.volatilityOnPaths		= new RandomVariableFromDoubleArray(0.0, volatility);
	}

	/**
	 * Create a Monte-Carlo simulation using given time discretization.
	 *
	 * @param timeDiscretization The time discretization.
	 * @param numberOfPaths The number of Monte-Carlo path to be used.
	 * @param initialValue Spot value.
	 * @param riskFreeRate The risk free rate.
	 * @param volatility The log volatility.
	 * @param seed The seed for the random number generator.
	 */
	public MonteCarloBlackScholesModel2(
			TimeDiscretization timeDiscretization,
			int numberOfPaths,
			double initialValue,
			double riskFreeRate,
			double volatility,
			int seed) {
		super(timeDiscretization, 1 /* numberOfComponents */ , 1 /* numberOfFactors */, numberOfPaths, seed);

		this.initialValue	= initialValue;
		this.riskFreeRate	= riskFreeRate;
		this.volatility		= volatility;

		/*
		 * The interface definition requires that we provide the drift and the volatility in terms of random variables.
		 * We construct the corresponding random variables here and will return (immutable) references to them.
		 */
		this.initialValueVector[0]	= new RandomVariableFromDoubleArray(0.0, initialValue);
		this.drift					= new RandomVariableFromDoubleArray(0.0, riskFreeRate);
		this.volatilityOnPaths		= new RandomVariableFromDoubleArray(0.0, volatility);
	}

	@Override
	public LocalDateTime getReferenceDate() {
		throw new UnsupportedOperationException("This model does not provide a reference date. Reference dates will be mandatory in a future version.");
	}

	@Override
	public int getNumberOfAssets() {
		return 1;
	}

	/**
	 * @return Returns the initialValue.
	 */
	@Override
	public RandomVariable[] getInitialValue() {
		return initialValueVector;
	}

	@Override
	public RandomVariable getDrift(int timeIndex, int componentIndex, RandomVariable[] realizationAtTimeIndex, RandomVariable[] realizationPredictor) {
		return drift;
	}

	@Override
	public RandomVariable getFactorLoading(int timeIndex, int factor, int component, RandomVariable[] realizationAtTimeIndex) {
		return volatilityOnPaths;
	}


	/* (non-Javadoc)
	 * @see net.finmath.montecarlo.assetderivativevaluation.AssetModelMonteCarloSimulationModel#getAssetValue(int, int)
	 */
	@Override
	public RandomVariable getAssetValue(int timeIndex, int assetIndex) {
		return getProcessValue(timeIndex, assetIndex);
	}

	@Override
	public RandomVariable getAssetValue(double time, int assetIndex) {
		return getAssetValue(getTimeIndex(time), assetIndex);
	}

	@Override
	public RandomVariable getMonteCarloWeights(double time) {
		return getMonteCarloWeights(getTimeIndex(time));
	}

	@Override
	public RandomVariable getNumeraire(int timeIndex)
	{
		double time = getTime(timeIndex);

		return getNumeraire(time);
	}

	@Override
	public RandomVariable getNumeraire(double time)
	{
		double numeraireValue = Math.exp(riskFreeRate * time);

		return new RandomVariableFromDoubleArray(time, numeraireValue);
	}

	@Override
	public RandomVariable getRandomVariableForConstant(double value) {
		return getBrownianMotion().getRandomVariableForConstant(value);
	}

	@Override
	public String toString() {
		return super.toString() + "\n" +
				"MonteCarloBlackScholesModelByInheritance:\n" +
				"  initial value...:" + initialValue + "\n" +
				"  risk free rate..:" + riskFreeRate + "\n" +
				"  volatiliy.......:" + volatility;
	}

	/**
	 * Returns the riskFreeRate.
	 *
	 * @return The riskFreeRate.
	 */
	public double getRiskFreeRate() {
		return riskFreeRate;
	}

	/**
	 * Returns the volatility.
	 *
	 * @return The volatility.
	 */
	public double getVolatility() {
		return volatility;
	}

	@Override
	public AssetModelMonteCarloSimulationModel getCloneWithModifiedSeed(int seed) {
		return new MonteCarloBlackScholesModel2(this.getTimeDiscretization(), this.getNumberOfPaths(), this.getInitialValue()[0].get(0), this.getRiskFreeRate(), this.getVolatility(), seed);
	}

	@Override
	public AssetModelMonteCarloSimulationModel getCloneWithModifiedData(Map<String, Object> dataModified) {
		throw new UnsupportedOperationException();
	}
}
