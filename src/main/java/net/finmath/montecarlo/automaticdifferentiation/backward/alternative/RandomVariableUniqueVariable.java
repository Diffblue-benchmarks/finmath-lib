/**
 *
 */
package net.finmath.montecarlo.automaticdifferentiation.backward.alternative;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleUnaryOperator;
import java.util.function.IntToDoubleFunction;
import java.util.stream.DoubleStream;

import net.finmath.functions.DoubleTernaryOperator;
import net.finmath.montecarlo.RandomVariableFromDoubleArray;
import net.finmath.stochastic.RandomVariable;

/**
 * @author Stefan Sedlmair
 * @version 1.0
 */
public class RandomVariableUniqueVariable implements RandomVariable {

	private static final long serialVersionUID = -2631868286977854016L;

	public enum OperatorType {
		ADD, MULT, DIV, SUB, SQUARED, SQRT, LOG, SIN, COS, EXP
	}

	private final RandomVariableUniqueVariableFactory factory = new RandomVariableUniqueVariableFactory();
	private ArrayList<RandomVariableUniqueVariable> parentsVariables;
	private OperatorType parentOperatorType; /* important for the partial derivatives */

	private int variableID;
	private boolean isConstant;

	/*---------------------------------------------------------------------------------------------------------------------------------*/

	/**
	 * <b>Do not use this constructor on its own.</b> It is thought only to be use by the {@link RandomVariableUniqueVariableFactory}!
	 *
	 * @param variableID is the index of the corresponding {@link RandomVariable} in the ArrayList of the {@link RandomVariableUniqueVariableFactory}
	 * @param isConstant If true, this is a constant.
	 * @param parentVariables Indices of parents
	 * @param parentOperatorType Operator
	 */
	public RandomVariableUniqueVariable(int variableID, boolean isConstant, ArrayList<RandomVariableUniqueVariable> parentVariables, OperatorType parentOperatorType) {
		this.variableID = variableID;
		this.isConstant = isConstant;
		this.parentsVariables = parentVariables;
		this.parentOperatorType = parentOperatorType;
	}

	public RandomVariableUniqueVariable(double time, double[] values, boolean isConstant, ArrayList<RandomVariableUniqueVariable> parentVariables, OperatorType parentOperatorType){
		constructRandomVariableUniqueVariable(new RandomVariableFromDoubleArray(time, values), isConstant, parentVariables, parentOperatorType);
	}

	public RandomVariableUniqueVariable(RandomVariable randomVariable, boolean isConstant, ArrayList<RandomVariableUniqueVariable> parentVariables, OperatorType parentOperatorType){
		constructRandomVariableUniqueVariable(randomVariable, isConstant, parentVariables, parentOperatorType);
	}

	public RandomVariableUniqueVariable(double time, double[] values, boolean isConstant){
		constructRandomVariableUniqueVariable(new RandomVariableFromDoubleArray(time, values), isConstant, /*parentVariables*/ null, /*parentOperatorType*/ null);
	}

	public RandomVariableUniqueVariable(RandomVariable randomVariable, boolean isConstant){
		constructRandomVariableUniqueVariable(randomVariable, isConstant, /*parentVariables*/ null, /*parentOperatorType*/ null);
	}

	public RandomVariableUniqueVariable(double time, double[] values){
		constructRandomVariableUniqueVariable(new RandomVariableFromDoubleArray(time, values), /*isConstant*/ false, /*parentVariables*/ null, /*parentOperatorType*/ null);
	}

	public RandomVariableUniqueVariable(RandomVariable randomVariable){
		constructRandomVariableUniqueVariable(randomVariable, /*isConstant*/ false, /*parentVariables*/ null, /*parentOperatorType*/ null);
	}

	/**
	 * Function calls {@link RandomVariableUniqueVariableFactory} to use the given {@link RandomVariableFromDoubleArray}
	 * and save it to its internal ArrayList. The index of the object will be give to the new {@link RandomVariableUniqueVariable}
	 * object.
	 *
	 *  @param randomVariable
	 *  @param isConstant
	 * */
	private void constructRandomVariableUniqueVariable(RandomVariable randomVariable, boolean isConstant, ArrayList<RandomVariableUniqueVariable> parentVariables, OperatorType parentOperatorType){
		/*
		 * by calling the method in the factory it will produce a new object of RandomVariable and
		 * the new item will be stored in its factory internal array list
		 */
		RandomVariable normalrandomvariable = factory.createRandomVariable(randomVariable, isConstant, parentVariables, parentOperatorType);

		/* by construction this object can be up-casted to RandomVariableUniqueVariable */
		RandomVariableUniqueVariable newrandomvariableuniquevariable = (RandomVariableUniqueVariable)normalrandomvariable;

		/* now we have access to the internal variables of the new RandomVarialeUniqueVariable */
		this.variableID = newrandomvariableuniquevariable.getVariableID();
		this.isConstant = newrandomvariableuniquevariable.isConstant();
		this.parentsVariables = newrandomvariableuniquevariable.getParentVariables();
		this.parentOperatorType = newrandomvariableuniquevariable.getParentOperatorType();
	}

	/*---------------------------------------------------------------------------------------------------------------------------------*/

	private int[] getParentIDs(){

		if(parentsVariables == null) return null;

		int[] parentIDs = new int[parentsVariables.size()];

		for(int i = 0; i < parentsVariables.size(); i++){
			parentIDs[i] = parentsVariables.get(i).getVariableID();
		}

		/*DO NOT sort this array! This deletes the information for divisions (de-/nominator)*/

		return parentIDs;
	}

	public int getVariableID(){
		return variableID;
	}

	private boolean isConstant(){
		return isConstant;
	}

	private ArrayList<RandomVariableUniqueVariable> getParentVariables(){
		return parentsVariables;
	}

	private OperatorType getParentOperatorType(){
		return parentOperatorType;
	}

	private ArrayList<RandomVariable> getListOfAllVariables(){
		return factory.getListOfAllVariables();
	}

	private ArrayList<RandomVariable> getParentRandomVariables(){

		ArrayList<RandomVariable> parentrandomvariables = new ArrayList<>();

		for(RandomVariableUniqueVariable parent:parentsVariables){
			parentrandomvariables.add(parent.getRandomVariable());
		}

		return parentrandomvariables;
	}

	private RandomVariable getRandomVariable(){
		return getListOfAllVariables().get(variableID);
	}

	public boolean isVariable(){
		return parentsVariables == null && isConstant() == false;

	}

	/*---------------------------------------------------------------------------------------------------------------------------------*/


	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#equals(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public boolean equals(RandomVariable randomVariable) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public double getFiltrationTime() {
		return getRandomVariable().getFiltrationTime();
	}

	@Override
	public int getTypePriority() {
		return 3;
	}

	@Override
	public double get(int pathOrState) {
		return getRandomVariable().get(pathOrState);
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#size()
	 */
	@Override
	public int size() {
		return getRandomVariable().size();
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#isDeterministic()
	 */
	@Override
	public boolean isDeterministic() {
		return getRandomVariable().isDeterministic();
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getRealizations()
	 */
	@Override
	public double[] getRealizations() {
		return getRandomVariable().getRealizations();
	}

	@Override
	public Double doubleValue() {
		return getRandomVariable().doubleValue();
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getMin()
	 */
	@Override
	public double getMin() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getMax()
	 */
	@Override
	public double getMax() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getAverage()
	 */
	@Override
	public double getAverage() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getAverage(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public double getAverage(RandomVariable probabilities) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getVariance()
	 */
	@Override
	public double getVariance() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getVariance(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public double getVariance(RandomVariable probabilities) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getSampleVariance()
	 */
	@Override
	public double getSampleVariance() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getStandardDeviation()
	 */
	@Override
	public double getStandardDeviation() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getStandardDeviation(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public double getStandardDeviation(RandomVariable probabilities) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getStandardError()
	 */
	@Override
	public double getStandardError() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getStandardError(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public double getStandardError(RandomVariable probabilities) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getQuantile(double)
	 */
	@Override
	public double getQuantile(double quantile) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getQuantile(double, net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public double getQuantile(double quantile, RandomVariable probabilities) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getQuantileExpectation(double, double)
	 */
	@Override
	public double getQuantileExpectation(double quantileStart, double quantileEnd) {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getHistogram(double[])
	 */
	@Override
	public double[] getHistogram(double[] intervalPoints) {
		return getRandomVariable().getHistogram(intervalPoints);
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#getHistogram(int, double)
	 */
	@Override
	public double[][] getHistogram(int numberOfPoints, double standardDeviations) {
		return getRandomVariable().getHistogram(numberOfPoints, standardDeviations);
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#cache()
	 */
	@Override
	public RandomVariable cache() {
		return getRandomVariable().cache();
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#apply(java.util.function.DoubleUnaryOperator)
	 */

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#floor(double)
	 */
	@Override
	public RandomVariable floor(double floor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#add(double)
	 */
	@Override
	public RandomVariable add(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#sub(double)
	 */
	@Override
	public RandomVariable sub(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#mult(double)
	 */
	@Override
	public RandomVariable mult(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#div(double)
	 */
	@Override
	public RandomVariable div(double value) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#pow(double)
	 */
	@Override
	public RandomVariable pow(double exponent) {
		return null;
	}

	@Override
	public RandomVariable average() {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#squared()
	 */
	@Override
	public RandomVariable squared() {
		return apply(OperatorType.SQUARED, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#sqrt()
	 */
	@Override
	public RandomVariable sqrt() {
		return apply(OperatorType.SQRT, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#exp()
	 */
	@Override
	public RandomVariable exp() {
		return apply(OperatorType.EXP, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#log()
	 */
	@Override
	public RandomVariable log() {
		return apply(OperatorType.LOG, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#sin()
	 */
	@Override
	public RandomVariable sin() {
		return apply(OperatorType.SIN, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#cos()
	 */
	@Override
	public RandomVariable cos() {
		return apply(OperatorType.COS, new RandomVariable[] {this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#add(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable add(RandomVariable randomVariable) {
		return apply(OperatorType.ADD, new RandomVariable[] {this, randomVariable});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#sub(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable sub(RandomVariable randomVariable) {
		return apply(OperatorType.SUB, new RandomVariable[] {this, randomVariable});
	}

	@Override
	public RandomVariable bus(RandomVariable randomVariable) {
		return apply(OperatorType.SUB, new RandomVariable[] {randomVariable, this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#mult(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable mult(RandomVariable randomVariable) {
		return apply(OperatorType.MULT, new RandomVariable[] {this, randomVariable});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#div(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable div(RandomVariable randomVariable) {
		return apply(OperatorType.DIV, new RandomVariable[] {this, randomVariable});
	}

	@Override
	public RandomVariable vid(RandomVariable randomVariable) {
		return apply(OperatorType.DIV, new RandomVariable[] {randomVariable, this});
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#cap(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable cap(RandomVariable cap) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#floor(net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable floor(RandomVariable floor) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#accrue(net.finmath.stochastic.RandomVariable, double)
	 */
	@Override
	public RandomVariable accrue(RandomVariable rate, double periodLength) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#discount(net.finmath.stochastic.RandomVariable, double)
	 */
	@Override
	public RandomVariable discount(RandomVariable rate, double periodLength) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RandomVariable choose(RandomVariable valueIfTriggerNonNegative, RandomVariable valueIfTriggerNegative) {
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#invert()
	 */
	@Override
	public RandomVariable invert() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#abs()
	 */
	@Override
	public RandomVariable abs() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#addProduct(net.finmath.stochastic.RandomVariable, double)
	 */
	@Override
	public RandomVariable addProduct(RandomVariable factor1, double factor2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#addProduct(net.finmath.stochastic.RandomVariable, net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable addProduct(RandomVariable factor1, RandomVariable factor2) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#addRatio(net.finmath.stochastic.RandomVariable, net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable addRatio(RandomVariable numerator, RandomVariable denominator) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#subRatio(net.finmath.stochastic.RandomVariable, net.finmath.stochastic.RandomVariable)
	 */
	@Override
	public RandomVariable subRatio(RandomVariable numerator, RandomVariable denominator) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see net.finmath.stochastic.RandomVariable#isNaN()
	 */
	@Override
	public RandomVariable isNaN() {
		return getRandomVariable().isNaN();
	}

	/**
	 * Check if an object can be up-casted to {@link RandomVariableUniqueVariable}.
	 * <b>If not treat the object as an constant</b> with respect to the AAD components.
	 *
	 * @param obj any object that should be tested.
	 * @return <i>true</i> if object can be casted to {@link RandomVariableUniqueVariable}, else <i>false</i>
	 * */
	private boolean isUpcastableToRandomVariableUniqueVariable(Object obj){
		return (obj instanceof RandomVariableUniqueVariable);
	}

	/** Apply one of the possible {@link OperatorType} to an array of {@link RandomVariable}s.
	 *  If the entries in the array are not an instance of {@link RandomVariableUniqueVariable}
	 *  generate a new {@link RandomVariableUniqueVariable} and consider them as constants.
	 * */
	private RandomVariableUniqueVariable apply(OperatorType operatortype, RandomVariable[] operatorVariables){

		ArrayList<RandomVariableUniqueVariable> parentVariables = new ArrayList<>();

		for(int i = 0; i < operatorVariables.length; i++){
			/*
			 * is variable upcastable to {@link RandomVariableUniqueVariable} ?
			 */
			if(!isUpcastableToRandomVariableUniqueVariable(operatorVariables[i])){
				/*
				 * if no then construct a new one and consider it constant
				 */
				operatorVariables[i] = new RandomVariableUniqueVariable(operatorVariables[i], /*isConstant*/ true);
			}
			/* add current function variable to parentVariables of new RandomVariableUniqueVariable*/
			parentVariables.add(i, (RandomVariableUniqueVariable) operatorVariables[i]);

			/* get the underlying RandomVariableFromDoubleArray from the factory */
			operatorVariables[i] = parentVariables.get(i).getRandomVariable();
		}

		RandomVariable resultrandomvariable;

		switch(operatortype){
		/* functions with one argument  */
		case SQUARED:
			resultrandomvariable = operatorVariables[0].squared();
			break;
		case SQRT:
			resultrandomvariable = operatorVariables[0].sqrt();
			break;
		case EXP:
			resultrandomvariable = operatorVariables[0].exp();
			break;
		case LOG:
			resultrandomvariable = operatorVariables[0].log();
			break;
		case SIN:
			resultrandomvariable = operatorVariables[0].sin();
			break;
		case COS:
			resultrandomvariable = operatorVariables[0].cos();
			break;

			/* functions with two arguments */
		case ADD:
			resultrandomvariable = operatorVariables[0].add(operatorVariables[1]);
			break;
		case SUB:
			resultrandomvariable = operatorVariables[0].sub(operatorVariables[1]);
			break;
		case MULT:
			resultrandomvariable = operatorVariables[0].mult(operatorVariables[1]);
			break;
		case DIV:
			resultrandomvariable = operatorVariables[0].div(operatorVariables[1]);
			break;

			/* if non of the above throw exception */
		default:
			throw new IllegalArgumentException("Operation not supported!\n");
		}

		/* create new RandomVariableUniqueVariable which is definitely NOT Constant */
		return new RandomVariableUniqueVariable(resultrandomvariable, /*isConstant*/ false, parentVariables, operatortype);
	}

	/**
	 * Apply the AAD algorithm to this very variable
	 *
	 * NOTE: in this case it is indeed correct to assume that the output dimension is "one"
	 * meaning that there is only one {@link RandomVariableUniqueVariable} as an output.
	 *
	 * @return gradient for the built up function
	 * */
	public RandomVariable[] getGradient(){

		// for now let us take the case for output-dimension equal to one!
		int numberOfVariables = getNumberOfVariablesInList();
		int numberOfCalculationSteps = factory.getNumberOfEntriesInList();

		RandomVariable[] omega_hat = new RandomVariable[numberOfCalculationSteps];

		// first entry gets initialized
		omega_hat[numberOfCalculationSteps-1] = new RandomVariableFromDoubleArray(1.0);

		/*
		 * TODO: Find way that calculations form here on are not 'recorded' by the factory
		 * IDEA: Let the calculation below run on {@link RandomVariableFromDoubleArray}, ie cast everything down!
		 * */

		for(int functionIndex = numberOfCalculationSteps - 2; functionIndex > 0; functionIndex--){
			// apply chain rule
			omega_hat[functionIndex] = new RandomVariableFromDoubleArray(0.0);

			/*TODO: save all D_{i,j}*\omega_j in vector and sum up later */
			for(RandomVariableUniqueVariable parent:parentsVariables){

				int variableIndex = parent.getVariableID();

				omega_hat[functionIndex] = omega_hat[functionIndex].add(getPartialDerivative(functionIndex, variableIndex).mult(omega_hat[variableIndex]));
			}
		}

		/* Due to the fact that we can still introduce 'new' true variables on the fly they are NOT the last couple of indices!
		 * Thus save the indices of the true variables and recover them after finalizing all the calculations
		 * IDEA: quit calculation after minimal true variable index is reached */
		RandomVariable[] gradient = new RandomVariable[numberOfVariables];

		/* TODO: sort array in correct manner! */
		int[] indicesOfVariables = getIDsOfVariablesInList();

		for(int i = 0; i < numberOfVariables; i++){
			gradient[i] = omega_hat[numberOfCalculationSteps - numberOfVariables + indicesOfVariables[i]];
		}

		return gradient;
	}

	private ArrayList<RandomVariableUniqueVariable> getListOfDependingTrueVariables(){

		ArrayList<RandomVariableUniqueVariable> listOfDependingTrueVariables = new ArrayList<>();

		for(RandomVariableUniqueVariable parent:parentsVariables){
			if(parent.isVariable() && !listOfDependingTrueVariables.contains(parent)){
				listOfDependingTrueVariables.add(parent);
			} else if (parent.getParentIDs() != null){
				listOfDependingTrueVariables.addAll(parent.getListOfDependingTrueVariables());
			}
		}

		return listOfDependingTrueVariables;
	}

	private int[] getIDsOfVariablesInList() {
		int[] IDsOfVariablesInList = new int[getNumberOfVariablesInList()];

		ArrayList<RandomVariableUniqueVariable> listOfDependingTrueVariables = getListOfDependingTrueVariables();

		for(RandomVariableUniqueVariable variable:listOfDependingTrueVariables){
			IDsOfVariablesInList[listOfDependingTrueVariables.indexOf(variable)] = variable.getVariableID();
		}

		return IDsOfVariablesInList;
	}

	private int getNumberOfVariablesInList() {
		return getListOfDependingTrueVariables().size();
	}

	/**
	 * @param functionIndex
	 * @param variableIndex
	 * @return
	 */
	private RandomVariable getPartialDerivative(int functionIndex, int variableIndex) {

		if(!Arrays.asList(getParentIDs()).contains(variableIndex)) return new RandomVariableFromDoubleArray(0.0);

		RandomVariableUniqueVariable currentRandomVariable = (RandomVariableUniqueVariable) getListOfAllVariables().get(functionIndex);
		ArrayList<RandomVariable> currentParentRandomVaribles = currentRandomVariable.getParentRandomVariables();

		RandomVariable resultrandomvariable;

		switch(currentRandomVariable.getParentOperatorType()){
		/* functions with one argument  */
		case SQUARED:
			resultrandomvariable = currentParentRandomVaribles.get(0).mult(2.0);
			break;
		case SQRT:
			resultrandomvariable = currentParentRandomVaribles.get(0).sqrt().invert().mult(0.5);
			break;
		case EXP:
			resultrandomvariable = currentParentRandomVaribles.get(0).exp();
			break;
		case LOG:
			resultrandomvariable = currentParentRandomVaribles.get(0).invert();
			break;
		case SIN:
			resultrandomvariable = currentParentRandomVaribles.get(0).cos();
			break;
		case COS:
			resultrandomvariable = currentParentRandomVaribles.get(0).sin().mult(-1.0);
			break;

			/* functions with two arguments */
		case ADD:
			resultrandomvariable = new RandomVariableFromDoubleArray(1.0);
			break;
		case SUB:
			resultrandomvariable = new RandomVariableFromDoubleArray(1.0);
			if(variableIndex == currentRandomVariable.getParentIDs()[1]){
				resultrandomvariable = resultrandomvariable.mult(-1.0);
			}
			break;
		case MULT:
			if(variableIndex == currentRandomVariable.getParentIDs()[0]){
				resultrandomvariable = currentParentRandomVaribles.get(1);
			} else {
				resultrandomvariable = currentParentRandomVaribles.get(0);
			}
			break;
		case DIV:
			if(variableIndex == currentRandomVariable.getParentIDs()[0]){
				resultrandomvariable = currentParentRandomVaribles.get(1).invert();
			} else {
				resultrandomvariable = currentParentRandomVaribles.get(0).div(currentParentRandomVaribles.get(1).squared()).mult(-1);
			}
			break;

			/* if non of the above throw exception */
		default:
			throw new IllegalArgumentException("Operation not supported!\n");
		}

		return resultrandomvariable;
	}

	@Override
	public String toString(){
		return super.toString() + "\n" +
				"time: " + getFiltrationTime() + "\n" +
				"realizations: " + Arrays.toString(getRealizations()) + "\n" +
				"variableID: " + variableID + "\n" +
				"parentIDs: " + Arrays.toString(getParentIDs()) + ((getParentIDs() == null) ? "" : (" type: " + parentOperatorType.name())) + "\n" +
				"isTrueVariable: " + isVariable() + "";
	}

	@Override
	public RandomVariable cap(double cap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IntToDoubleFunction getOperator() {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public DoubleStream getRealizationsStream() {
		throw new UnsupportedOperationException("Not supported.");
	}

	@Override
	public RandomVariable apply(DoubleUnaryOperator operator) {
		throw new UnsupportedOperationException("Applying functions is not supported.");
	}

	@Override
	public RandomVariable apply(DoubleBinaryOperator operator, RandomVariable argument) {
		throw new UnsupportedOperationException("Applying functions is not supported.");
	}

	@Override
	public RandomVariable apply(DoubleTernaryOperator operator, RandomVariable argument1, RandomVariable argument2) {
		throw new UnsupportedOperationException("Applying functions is not supported.");
	}
}
