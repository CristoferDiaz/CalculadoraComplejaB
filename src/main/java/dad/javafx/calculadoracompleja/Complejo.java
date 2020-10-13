package dad.javafx.calculadoracompleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
	
	private DoubleProperty real = new SimpleDoubleProperty();
	private DoubleProperty Imaginario = new SimpleDoubleProperty();
	public Complejo() {}
	
	public Complejo(double real, double imaginario) {
		super();
		setReal(real);
		setImaginario(imaginario);
	}
	
	public final DoubleProperty realProperty() {
		return this.real;
	}
	
	public final double getReal() {
		return this.realProperty().get();
	}
	
	public final void setReal(final double real) {
		this.realProperty().set(real);
	}
	
	public final DoubleProperty ImaginarioProperty() {
		return this.Imaginario;
	}
	
	public final double getImaginario() {
		return this.ImaginarioProperty().get();
	}
	
	public final void setImaginario(final double Imaginario) {
		this.ImaginarioProperty().set(Imaginario);
	}
	
	@Override
	public String toString() {
		return getReal() + "+" + getImaginario() + "i";
	}
	
	public Complejo add (Complejo c) {
		Complejo r = new Complejo();
		r.realProperty().bind(real.add(c.realProperty()));
		r.ImaginarioProperty().bind(Imaginario.add(c.ImaginarioProperty()));
		return r;
		
	}
	
	public static void main(String[] args) {
		Complejo a = new Complejo(1, 2);
		Complejo b = new Complejo(3, 4);
		Complejo c = a.add(b);
		System.out.println(c);
		a.setReal(10);
		System.out.println(c);		
	}
		
	
}
