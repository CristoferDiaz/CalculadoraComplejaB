package dad.javafx.calculadoracompleja;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class CalculadoraCompleja extends Application {

	private TextField operando1text;
	private TextField operando2text;
	private TextField operando1textB;
	private TextField operando2textB;
	private ComboBox<String> operdaorCombo;
	private TextField resultadoText;
	private TextField resultadoTextB;

	// property
	private DoubleProperty operando1 = new SimpleDoubleProperty();
	private DoubleProperty operando1B = new SimpleDoubleProperty();
	private DoubleProperty operando2 = new SimpleDoubleProperty();
	private DoubleProperty operando2B = new SimpleDoubleProperty();
	private DoubleProperty resultado = new SimpleDoubleProperty();
	private DoubleProperty resultadoB = new SimpleDoubleProperty();
	private StringProperty operacion = new SimpleStringProperty();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//inicializo todo 
		operando1text = new TextField();
		operando1text.setPrefColumnCount(4);
		operando1textB = new TextField();
		operando1textB.setPrefColumnCount(4);

		operando2text = new TextField();
		operando2text.setPrefColumnCount(4);
		operando2textB = new TextField();
		operando2textB.setPrefColumnCount(4);

		resultadoText = new TextField();
		resultadoText.setPrefColumnCount(4);
		resultadoTextB = new TextField();
		resultadoTextB.setPrefColumnCount(4);
		
		//valores del combo 
		operdaorCombo = new ComboBox<String>();
		operdaorCombo.getItems().addAll("+", "-", "*", "/");

		//parte visual
		HBox root = new HBox(5, operando1text, new Label("+"), operando1textB, new Label(" i"));
		root.setAlignment(Pos.CENTER);
		HBox root2 = new HBox(5, operando2text, new Label("+"), operando2textB, new Label(" i"));
		root2.setAlignment(Pos.CENTER);
		HBox root3 = new HBox(5, resultadoText, new Label("+"), resultadoTextB, new Label(" i"));
		root3.setAlignment(Pos.CENTER);
		VBox rootOrd = new VBox(root, root2, new Separator(), root3);
		rootOrd.setAlignment(Pos.CENTER);
		HBox rootFin = new HBox(operdaorCombo, rootOrd, new Label("  ="));
		rootFin.setAlignment(Pos.CENTER);

		Scene scene = new Scene(rootFin, 320, 200);

		primaryStage.setTitle("calculadora");
		primaryStage.setScene(scene);
		primaryStage.show();
		
		
		// bindeos 
		Bindings.bindBidirectional(operando1text.textProperty(), operando1, new NumberStringConverter());
		Bindings.bindBidirectional(operando2text.textProperty(), operando2, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoText.textProperty(), resultado, new NumberStringConverter());
		Bindings.bindBidirectional(operando1textB.textProperty(), operando1B, new NumberStringConverter());
		Bindings.bindBidirectional(operando2textB.textProperty(), operando2B, new NumberStringConverter());
		Bindings.bindBidirectional(resultadoTextB.textProperty(), resultadoB, new NumberStringConverter());
		operacion.bind(operdaorCombo.getSelectionModel().selectedItemProperty());
		
		//listener en una funcion:
		operacion.addListener((o, ov, nv) -> onOperacionChanged(nv));

		
		//seleciona el primer elemto del combo 
		operdaorCombo.getSelectionModel().selectFirst();
		
	}
	//funcion del listener para las operaciones 
	private void onOperacionChanged(String nv) {
		switch (nv) {
		case "+":
			resultado.bind(operando1.add(operando2));
			resultadoB.bind(operando1B.add(operando2B));
			break;
		case "*":
			resultado.bind(operando1.multiply(operando2).subtract(operando1B.multiply(operando2B)));
			resultadoB.bind(operando1.multiply(operando2B).add(operando1B.multiply(operando2)));
			break;
		case "-":
			resultado.bind(operando1.subtract(operando2));
			resultadoB.bind(operando1B.subtract(operando2B));
			break;
		case "/":

			resultado.bind((operando1.multiply(operando2).add(operando1B.multiply(operando2B))
					.divide(operando2.multiply(operando2).add((operando2B.multiply(operando2B))))));
			resultado.bind((operando1B.multiply(operando2).subtract(operando1.multiply(operando2B))
					.divide(operando2.multiply(operando2).add((operando2B.multiply(operando2B))))));
			break;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
