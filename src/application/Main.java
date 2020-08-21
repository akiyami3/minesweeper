package application;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) throws Exception {

		GuiController gcon = new GuiController(stage);//GuiControllerインスタンス化
		gcon.display_start();

	}

	public static void main(String[] args) {
		launch(args);//startメソッドへ飛ばす



	}
}

