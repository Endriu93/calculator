package calculations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import kalkulator.Function;
import kalkulator.MathOp;

public class ExpressionCalculation {
	private final String rpnExpression;
	private final String rpnConvertableExpression;
	private double result;
	private String expressionToDisplay;
	private boolean expressionValidation;
	ExpressionCalculation(String inputExpression) {
		this.expressionValidation = true;
		this.rpnConvertableExpression = this.convertInputStringToRPNConvertable(inputExpression);
		RPNConverter converter = new RPNConverter(rpnConvertableExpression);
		rpnExpression = converter.convertToRPN();
	}
	private final static Map<String,Operations> operationMap;
	static {
		Map<String,Operations> tmpMap = new HashMap<>();
		tmpMap.put("+", Operators.ADD);
		tmpMap.put("-", Operators.SUBTRACT);
		tmpMap.put("*", Operators.MULTIPLY);
		tmpMap.put("/", Operators.DIVIDE);
		tmpMap.put("^", Operators.POV);
		tmpMap.put("log", Functions.LOG);
		tmpMap.put("abs", Functions.ABS);
		tmpMap.put("fact", Functions.FACT);
		operationMap = Collections.unmodifiableMap(tmpMap);
	}
	public static Map<String,Operations> getOpMap() {
		return operationMap;
	}
	private String convertInputStringToRPNConvertable(String input) {
		String tmp = input.replaceAll(" ", "");
		this.expressionToDisplay = tmp;

		tmp = tmp.replaceAll("(\\d+)([.])(\\d+)(log|abs|fact|[(])", "$1$2$3*$4");
		tmp = tmp.replaceAll("(\\d+)(log|abs|fact|[(])", "$1*$2");
		tmp = tmp.replaceAll("([/]|\\*|\\^)-(log|abs|fact|[(])", "*-1$1$2");
		tmp = tmp.replaceAll("([\\(])(-)", "(0-");
		tmp = tmp.replaceAll("\\+|-|\\*|/|\\^|log|abs|fact|[(]|[)]", " $0 ");
		tmp = tmp.replaceAll("  ", " ");
		tmp = tmp.replaceAll("(\\d+) ([.]) (\\d+)", "$1$2$3");
		tmp = tmp.replaceAll("([(]|\\*|\\^|[/]) (-||\\+) ([0-9])", "$1 $2$3");
		tmp = tmp.trim();
		try {
			if(tmp.charAt(0) == '-' ) {
				tmp = tmp.replaceFirst("-", "0 -");
			}
			else if( tmp.charAt(0) == '+') {
				tmp = tmp.replaceFirst(" ","");
			}
		} catch (IndexOutOfBoundsException e) {
			expressionValidation = false;
		}
		if(tmp.contains(". ")){
			expressionValidation = false;
		}
		return tmp;
		 
	}
	public void calculate() {
		if(expressionValidation == false) {
			return;
		}
		Stack<String> stack = new Stack<>();
		String tokens[] = rpnExpression.split(" ");
		for(String token : tokens) {
			if(operationMap.containsKey(token)) {
				Class<? extends Operations> opClass = operationMap.get(token).getClass();
					try {
						if(opClass.getSimpleName().equals("Operators")) {
							Method method = MathOp.class.getMethod(operationMap.get(token).getFuncName(), Double.class,Double.class);
							Double firstOperand = Double.parseDouble(stack.pop());
							Double secondOperand = Double.parseDouble(stack.pop());
							Double result = (Double)method.invoke(null, secondOperand,firstOperand);
							stack.push(result.toString());
						}
						else {
							Method method = Function.class.getMethod(operationMap.get(token).getFuncName(), Double.class);
							Double result = (Double)method.invoke(null, Double.parseDouble(stack.pop()));
							stack.push(result.toString());
						}
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						expressionValidation = false;
						System.out.print(e.getCause().getMessage());
						return;
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch(NumberFormatException e) {
						expressionValidation = false;
						return;
					} catch(EmptyStackException e) {
						expressionValidation = false;
						return;
					}
			}
			else {
				stack.push(token);
			}
		}
		if(stack.size() > 1) {
			expressionValidation = false;
			return;
		}
		try {
			result = roundTo2Places(Double.parseDouble(stack.pop()));
		} catch (NumberFormatException e) {
			expressionValidation = false;
		}

	}
	public static double roundTo2Places(double value) {
		assert value >= Long.MIN_VALUE / 100 && value <= Long.MAX_VALUE / 100;
		long digits = (long) (value < 0 ? value * 100 - 0.5 : value * 100 + 0.5);
		return (double) digits / 100;
	}
	public double getResult() {
		return result;
	}
	public String getExpression() {
		return this.expressionToDisplay;
	}
	public boolean isValid() {
		return this.expressionValidation;
	}
	
}
