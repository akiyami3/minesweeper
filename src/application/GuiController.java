package application;





import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


	public class GuiController{


/*--------------- 各種コンポーネントの宣言---------------*/
//スタート画面
		private Text text_teacher;//先生のコメント
		private Button button_curry;//カレーボタン
		private Button button_Mabo;//サラダボタン
		private Button button_pudding;//プリンボタン
		private Button button_rule;

//マインスイーパー実行画面
		private Text text_get_picture;
		private Button[] button_get_picture = new Button[5];//ゲットした食材のイラストを表示
		private Button[][] button = new Button[7][12];

//食材確認画面
		private Text[] text_ingredients = new Text[5];//必要な食材を表示する
		private Text text_need;//"必要な食材"と表示する
		private Text text_need_msg;//"材料を確認したら料理をスタートしよう"と表示する

//クイズ画面
		private Text text_name;//食材の名前を表示
		private Text text_quiz;//問題を表示
		private Text[] text_choice = new Text[3];//選択肢を表示
		private Button button_answer1;//「１」ボタン
		private Button button_answer2;//「２」ボタン
		private Button button_answer3;//「３」ボタン

//結果画面
		private Button button_start;//スタートボタン
		private Text[] text_result = new Text[7];
		private Text text_titleGet;//ゲットした食材を表示するタイトル
		private Text text_get[] = new Text[5];//ゲットした食材の表示
		private Text text_next;//ボタンの上に表示するテキスト
		private Text text_stage_total;//ステージ数を表示するテキスト

//ゲームクリア画面
		private Button button_restart;//スタート画面へ戻るボタン
		private Button button_close;//終了ボタン
		private Text text_clear;
		private Text text_success;

//ゲームオーバー画面
		private Text text_gameOver;
		private Text text_failure;

			/*--------------- メンバ変数---------------*/
		private Stage primaryStage;
		private String path = "C:\\Users\\s1857202\\Downloads\\pleiades-4.8.0-java-win-64bit-jre_20180923\\pleiades\\workspace\\Minesweeper\\src\\application";
		private boolean judge_1click = false;//1回目のクリックがされたか判定
		private int[][] mine = new int[9][9]; //マスの爆弾と数値の情報を格納(9:爆弾)
		private int[][] event = new int[9][9];//イベントの配置(0:イベント無し 1～5:イベント有り)
		private int k;//event_open[]の添え字管理
		private int[] event_open;
		private int[] num_event_incorrect = new int[5];//不正解だったイベント番号を格納
		private boolean judge_incorrect = false;//食材問題のうち1つ以上不正解だった場合true
		private char[] result_icon = new char[7];//num_event_incorrect[]の正解不正解に対応して'〇'または'×'を格納し、結果画面で表示する
		private boolean judge_firstTime = true;//1回目のプレーの場合:true　2回目以降のプレーの場合:false
		private String file_menu;//メニューに応じた画像のファイル名を格納する
		private int stage_total = 0;//ステージ数を集計する

		//ルール説明画面
		private Text text_explanation1;//説明文
		private Text text_explanation2;//説明文
		private Text text_explanation3;//説明文
		private Text text_explanation4;//説明文
		private Text text_explanation5;//説明文
		private Text text_explanation6;//説明文


		Quiz quiz;
		Placement pMent = new Placement();
		Process process = new Process();


/*******************************
 *
 * コンストラクタ
 *
 * *****************************/
		public GuiController(Stage primaryStage) {

			this.primaryStage = primaryStage;//メンバ変数stageに、mainから受け取ったstageを代入する

		}


/*******************************
 *
 * コンストラクタ
 *
 * *****************************/
		public GuiController(){
			  super();
		}

/******************************************
 *
 * スタート画面のメソッド
 *
 * ****************************************/

		public void display_start() throws Exception {

			  primaryStage.setTitle("スタート画面");
			  primaryStage.setWidth(500);
			  primaryStage.setHeight(500);
			  primaryStage.setResizable(false);//フレームの拡大できないように指定
			  primaryStage.setOnCloseRequest(e -> {
				  Platform.exit();
				  System.exit(0);
			  });

			  //imageの読み込み
			  Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
			  //imageviewの作成
			  ImageView imgView_set = new ImageView( image_set );
			  imgView_set.setOpacity(0.5);//半透明にする


			  //imageの読み込み
			  Image image_comment = new Image( "file:" + path + "\\image\\吹き出し.png");
			  //imageviewの作成
			  ImageView imgView_comment = new ImageView( image_comment );
			  //画像の縮小
			  imgView_comment.setScaleY( 0.3 );
			  imgView_comment.setScaleX( -0.9 );
			  //画像の位置の調整
			  imgView_comment.setTranslateX( -30. );
			  imgView_comment.setTranslateY( -170. );

			  
			 Image image = new Image( "file:" + path + "\\image\\シェフ.png");
			 //imageviewの作成
			 ImageView imgView = new ImageView( image );
			 //画像の縮小
			 imgView.setScaleY( 0.8 );
			 imgView.setScaleX( 0.8 );
			 //画像の位置の調整
			 imgView.setTranslateX( 120. );
			 imgView.setTranslateY( 30. );


//----------------------カレーボタン--------------------------
			 button_curry = new Button("カレー");
			 button_curry.setPrefWidth(150);
			 button_curry.setPrefHeight(40);
			 button_curry.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 15 ) );
			 button_curry.setStyle("-fx-base: #D2B48C");//マスに色を付ける
			 button_curry.setOnMouseClicked(event -> {
				 file_menu = "\\カレー.png";
				 quiz = new Quiz(1);//1:カレーでQuizのインスタンス生成
				 stage_total = 1;//ステージ数を1にする
				 		try {
				 			display_ingredients();//材料確認画面へ飛ぶ
						} catch (Exception ex) {
						}
				});

//----------------------麻婆豆腐ボタン--------------------------
			 button_Mabo = new Button("麻婆豆腐");
			 button_Mabo.setPrefWidth(150);
			 button_Mabo.setPrefHeight(40);
			 button_Mabo.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC ,15 ) );
			 button_Mabo.setStyle("-fx-base: #D2B48C");//マスに色を付ける
			 button_Mabo.setOnMouseClicked(event -> {
				 file_menu = "\\麻婆豆腐.png";
				 quiz = new Quiz(2);//2:麻婆豆腐でQuizのインスタンス生成
				 stage_total = 1;//ステージ数を1にする
				 try {
					 		display_ingredients();//材料確認画面へ飛ぶ
				 		} catch (Exception ex) {
				 		}
				});

//----------------------プリンボタン--------------------------
			 button_pudding = new Button("プリン");
			 button_pudding.setPrefWidth(150);
			 button_pudding.setPrefHeight(40);
			 button_pudding.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 15 ) );
			 button_pudding.setStyle("-fx-base: #D2B48C");//マスに色を付ける
			 button_pudding.setOnMouseClicked(event -> {
				 file_menu = "\\プリン.png";
				 quiz = new Quiz(3);//3:プリンでQuizのインスタンス生成
				 stage_total = 1;//ステージ数を1にする
						try {
							display_ingredients();//材料確認画面へ飛ぶ
						} catch (Exception ex) {
						}
				});

//----------------------ルール説明ボタン--------------------------
			 button_rule = new Button("ルール説明");
			 button_rule.setPrefWidth(150);
			 button_rule.setPrefHeight(40);
			 button_rule.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 15 ) );
			 button_rule.setStyle("-fx-base: #CD5C5C");//マスに色を付ける
			 button_rule.setTranslateX( -5. );
			 button_rule.setOnMouseClicked(event -> {
					try {
						display_explanation();
					} catch (Exception ex) {
					}
			});

			 //先生のコメント
			 text_teacher = new Text("作りたいメニューを選んで調理を開始しよう！");
			 text_teacher.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		     text_teacher.setTextAlignment( TextAlignment.CENTER );
		     text_teacher.setWrappingWidth( 600.0 );
		     //配置
		     text_teacher.setTranslateX( -30 );
		     text_teacher.setTranslateY( -83. );


//--------------------------配置------------------------------

			 VBox teacher = new VBox();
			 teacher.setAlignment(Pos.CENTER);

			 VBox text = new VBox();
			 text.setAlignment(Pos.BOTTOM_CENTER);
			 text.getChildren().addAll(text_teacher);


			 VBox menu1 = new VBox();
			 menu1.setAlignment(Pos.CENTER_RIGHT);
			 menu1.setPadding( new Insets(5, 5, 5, 5));
			 menu1.setSpacing(6);
			 menu1.getChildren().addAll(button_curry, button_Mabo, button_pudding);


			 VBox menu2 = new VBox();
			 menu2.setAlignment(Pos.CENTER_RIGHT);
			 menu2.setPadding( new Insets(5, 5, 5, 5));
			 menu2.setSpacing(10);
			 menu2.getChildren().addAll(menu1, button_rule);
			 //位置の調整
			 menu2.setTranslateX( -120. );
			 menu2.setTranslateY( 30. );

			 HBox box_start = new HBox();
			 box_start.setAlignment(Pos.CENTER);
			 box_start.getChildren().addAll(menu2, teacher);

			 VBox start = new VBox();
			 start.setAlignment(Pos.CENTER);
			 start.setPadding( new Insets(5, 5, 5, 5));
			 start.setSpacing(0.0000000001);
			 start.getChildren().addAll(text, box_start);


			 StackPane stackPane = new StackPane(imgView_set, imgView_comment, imgView, start);
			 primaryStage.setScene(new Scene(stackPane));
			 primaryStage.show();

			 }


/***********************************************
 *
 * マインスイーパーのプレイ画面のメソッド
 *
 * *********************************************/
	public void display_mine() throws Exception{


			 primaryStage.setTitle("マインスイーパー");
			 primaryStage.setWidth(620);
			 primaryStage.setHeight(510);
			 primaryStage.setResizable(false);//フレームの拡大できないように指定
			 primaryStage.setOnCloseRequest(e -> {
				  Platform.exit();
				  System.exit(0);
			 });

			 //imageの読み込み
			 Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
			 //imageviewの作成
			 ImageView imgView_set = new ImageView( image_set );
			 imgView_set.setOpacity(0.5);//半透明にする

			 //imageの読み込み
			 Image image_white = new Image( "file:" + path + "\\image\\白.jpg");
			 //imageviewの作成
			 ImageView imgView_white = new ImageView( image_white );
			 imgView_white.setOpacity(0.8);//半透明にする
			 //画像の縮小
			 imgView_white.setScaleY( 0.2 );
			 imgView_white.setScaleX( 0.62);
			 //画像の位置の調整
			 imgView_white.setTranslateY( 81.368689 );


			 //imageの読み込み
			 Image image_menu = new Image( "file:" + path + file_menu);
			 //imageviewの作成
			 ImageView imgView_menu = new ImageView( image_menu );
			 imgView_menu.setOpacity(2.);
			 //位置の調整
			 imgView_menu.setTranslateY( 60. );

			 text_get_picture = new Text("GETした食材");
			 text_get_picture.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 28 ) );
			 text_get_picture.setFill(Color.web("#8B0000"));

			 
			 for(int i=0; i<5; i++) {
				 if(result_icon[i] != '〇') {//前のステージで不正解だった場合
					 button_get_picture[i] = new Button(quiz.getQuiz_name_i(i));//食材を表示
					 button_get_picture[i].setPrefWidth(120);
					 button_get_picture[i].setPrefHeight(120);
					 button_get_picture[i].setDisable(true);//ボタンの無効化

				 }
			 }


			for(int i=0; i<7; i++) {
				for(int j=0; j<12; j++) {
					button[i][j] = new Button();
					button[i][j].setPrefWidth(50);
					button[i][j].setPrefHeight(50);
					button[i][j].setStyle("-fx-base: #F0E68C");//マスに色を付ける
					int a = i;
					int b = j;
					button[i][j].setOnMouseClicked(event -> {

						switch(event.getButton()) {

//************************左クリック****************************
							case PRIMARY:

////////1回目のクリック && フラグが立てられていない場合////////
								if(!(judge_1click) && (process.getOpen(a, b) != 3)) {
									judge_1click = true;//1回目のクリックがされたためtrueにする.
									mine = pMent.place_bomb(a, b);
									this.event = pMent.place_event(mine,judge_firstTime);//爆弾・数字・イベントの配置
									open_button(process.open_square(mine, this.event, a, b));//ボタンの表示の処理
									event_open = process.getEvent_open();//Processクラスのevent_open[]を取得

									if(event_open[0] != 0) {//イベントマスが1つ以上開かれていたら
										for(k=0; k<5; k++) {
											if(event_open[k] != 0) {//開かれたイベントマスの番号が格納されている場合
												try {
													display_quiz(quiz.getQuiz_i(event_open[k]-1), quiz.getQuiz_choice_ij(event_open[k]-1, 0), quiz.getQuiz_choice_ij(event_open[k]-1, 1), quiz.getQuiz_choice_ij(event_open[k]-1, 2), quiz.getQuiz_answer_i(event_open[k]-1), event_open[k]);//問題,選択肢①,選択肢②,選択肢③,答え,イベント番号
												} catch (Exception ex) {
												}
											}
										}

									}
									process.initEvent_open();//Processクラスのevent_open[]を初期化
/////////////

////////////2回目以降のクリックの場合///////////
								}else {

									if(mine[a][b] == 9 && (process.getOpen(a, b) != 3)) {//クリックしたマスが爆弾 && フラグが立てられていない場合
										try {
											display_gameOver();
										} catch (Exception ex) {
										}
									}else if(process.getOpen(a, b) != 3) {//2回目以降のクリックで爆弾以外だった場合 && フラグが立てられていない場合
										int open_count = open_button(process.open_square(mine, this.event, a, b));//ボタンの表示の処理
										event_open = process.getEvent_open();//Processクラスのevent_open[]を取得

										if(event_open[0] != 0) {//イベントマスが1つ以上開かれていたら
											for(k=0; k<5; k++) {
												if(event_open[k] != 0) {//開かれたイベントマスの番号が格納されている場合
													try {
														display_quiz(quiz.getQuiz_i(event_open[k]-1), quiz.getQuiz_choice_ij(event_open[k]-1, 0), quiz.getQuiz_choice_ij(event_open[k]-1, 1), quiz.getQuiz_choice_ij(event_open[k]-1, 2), quiz.getQuiz_answer_i(event_open[k]-1), event_open[k]);//問題,選択肢①,選択肢②,選択肢③,答え,イベント番号
														} catch (Exception ex) {
													}
												}
											}

										}
										process.initEvent_open();//Processクラスのevent_open[]を初期化

//-----------------------------ゲームクリアの判定-------------------------------------
										if(73 < open_count) {//爆弾以外のマスを全て開けた場合

											for(k=0; k<5; k++) {
												pMent.setReChallenge(num_event_incorrect, k);//PlacementクラスのreChallenge[]に代入して(食材の不正解があれば)次回以降のイベントの配置の処理に使う
											}
											for(k=0;k<5;k++) {
												if(num_event_incorrect[k] == 1) {//不正解だった場合
													result_icon[k] = '　';
													judge_incorrect = true;//不正解があった場合	judge_incorrectをtrueにする
												}else {//正解だった場合
													result_icon[k] = '〇';
												}
											}

											if(judge_incorrect) {//食材問題のうち1つ以上不正解だった場合
												judge_incorrect = false;//初期化
												judge_firstTime = false;//2回目以降となるためfalseにする
												stage_total++;//ステージ数をインクリメント
												try {
													display_result();//結果画面の表示
												} catch (Exception ex) {
												}
											}else {//食材問題を全問正解した場合

												//num_event_incorrect[]の初期化
												for(k=0;k<5;k++) {
													num_event_incorrect[k] = 0;
												}

												judge_firstTime = true;//1回目のtrueに戻す
												pMent.initReChallenge();//PlacementクラスのreChallenge[]を初期化
												try {
													display_gameClear();//ゲームクリア画面の表示
												} catch (Exception ex) {
												}
											}
										}
//---------------------------------
									}
								}
///////////////////////
								break;

//************************右クリック****************************
							case SECONDARY:

								if(process.getOpen(a,  b) == 3){//既にフラグがたてられている場合

									process.setOpen(a,  b,  0);//open[a][b]に0を代入してまだ開かれていない状態にする
									button[a][b].setText("");//フラグをボタンに表示
									button[a][b].setGraphic(null);

								}else {//まだフラグが立てられていない場合

									process.setOpen(a,  b,  3);//open[a][b]に3を代入
									//imageの読み込み
									Image image_fryingPan = new Image( "file:" + path + "\\image\\フライパン.png");
									//imageviewの作成
									ImageView imgView_flyingPan = new ImageView(image_fryingPan);
									button[a][b].setGraphic(imgView_flyingPan);

								}
								break;

							default:

								break;

						}
					});

				}

			}

//-------------------------------配置-------------------------------------
			HBox box_picture = new HBox();
			box_picture.setAlignment(Pos.CENTER);
			box_picture.getChildren().addAll(button_get_picture[0], button_get_picture[1], button_get_picture[2], button_get_picture[3], button_get_picture[4]);

			HBox boxA = new HBox();
			boxA.setAlignment(Pos.CENTER);
			//boxA.setSpacing(5);
			boxA.getChildren().addAll(button[0][0], button[0][1],button[0][2], button[0][3], button[0][4], button[0][5], button[0][6], button[0][7], button[0][8], button[0][9], button[0][10], button[0][11]);


			HBox boxB = new HBox();
			boxB.setAlignment(Pos.CENTER);
			boxB.getChildren().addAll(button[1][0], button[1][1],button[1][2], button[1][3], button[1][4], button[1][5], button[1][6], button[1][7], button[1][8], button[1][9], button[1][10], button[1][11]);


			HBox boxC = new HBox();
			boxC.setAlignment(Pos.CENTER);
			boxC.getChildren().addAll(button[2][0], button[2][1],button[2][2], button[2][3], button[2][4], button[2][5], button[2][6], button[2][7], button[2][8], button[2][9], button[2][10], button[2][11]);


			HBox boxD = new HBox();
			boxD.setAlignment(Pos.CENTER);
			boxD.getChildren().addAll(button[3][0],button[3][1],button[3][2], button[3][3], button[3][4], button[3][5], button[3][6], button[3][7], button[3][8], button[3][9], button[3][10], button[3][11]);


			HBox boxE = new HBox();
			boxE.setAlignment(Pos.CENTER);
			boxE.getChildren().addAll(button[4][0],button[4][1],button[4][2], button[4][3], button[4][4], button[4][5], button[4][6], button[4][7], button[4][8], button[4][9], button[4][10], button[4][11]);


			HBox boxF = new HBox();
			boxF.setAlignment(Pos.CENTER);
			boxF.getChildren().addAll(button[5][0],button[5][1],button[5][2], button[5][3], button[5][4], button[5][5], button[5][6], button[5][7], button[5][8], button[5][9], button[5][10], button[5][11]);


			HBox boxG = new HBox();
			boxG.setAlignment(Pos.CENTER);
			boxG.getChildren().addAll(button[6][0],button[6][1],button[6][2], button[6][3], button[6][4], button[6][5], button[6][6], button[6][7], button[6][8], button[6][9], button[6][10], button[6][11]);


			VBox game = new VBox();
			game.setAlignment(Pos.CENTER);
			game.getChildren().addAll(boxA, boxB, boxC, boxD, boxE, boxF, boxG);


			VBox root = new VBox();
			root.setAlignment(Pos.CENTER);
			root.setSpacing(5);
			root.getChildren().addAll(text_get_picture, box_picture, game);



			StackPane stackPane = new StackPane(imgView_set, imgView_white, imgView_menu, root);

			primaryStage.setScene(new Scene(stackPane));
			primaryStage.showAndWait();

			}


/********************************
 *
 * マスを開くメソッド
 *
 * ******************************/
	public int open_button(int[][] open) {
		String str_num;//数値をString型に変換したものを格納
		int open_count = 0;
		for(int i=0; i<7; i++) {
			for(int j=0; j<12; j++) {
				if(open[i][j] == 1) {//open[i][j]が１(開かれていたら)だった場合
					open_count++;
					if(event[i][j] == 1 || event[i][j] == 2 || event[i][j] == 3 || event[i][j] == 4 || event[i][j] == 5 || event[i][j] == 6 || event[i][j] == 7) {//イベントありの場合
						button[i][j].setStyle("-fx-base: #f4f162");//マスを黄色にする
						button[i][j].setOpacity(0.7);//半透明にする
					}
					if(mine[i][j] != 0) {//数字が0でない場合
						str_num = String.valueOf(mine[i][j]);//int型をString型に変換
						button[i][j].setText(str_num);//数字をボタンに表示
						button[i][j].setDisable(true);//ボタンの無効化
						button[i][j].setStyle(null);//マスをデフォルトの色に戻す
						button[i][j].setOpacity(0.6);//半透明にする
						button[i][j].setFont( new Font(20));
					}else {
						button[i][j].setDisable(true);//ボタンの無効化
						button[i][j].setStyle(null);//マスをデフォルトの色に戻す
						button[i][j].setOpacity(0.6);//半透明にする
					}
				}
			}
		}
		return open_count;
	}

/********************************
 *
 * 食材確認画面のメソッド
 *
 * ******************************/
	public void display_ingredients() throws Exception{

		primaryStage.setTitle("食材確認画面");
		primaryStage.setWidth(350);
		primaryStage.setHeight(455);
		primaryStage.setResizable(false);//フレームの拡大できないように指定

//-------------------------画像--------------------------
		//imageの読み込み
		 Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
		 //imageviewの作成
		 ImageView imgView_set = new ImageView( image_set );
		 imgView_set.setOpacity(0.5);//半透明にする

		//imageの読み込み
		Image image_white = new Image( "file:" + path + "\\image\\白.jpg");
		//imageviewの作成
		ImageView imgView_white = new ImageView( image_white );
		imgView_white.setOpacity(0.5);//半透明にする
		//画像の縮小
		imgView_white.setScaleY( 0.22 );
		//位置の調整
		imgView_white.setTranslateY( 10. );

//----------------------------テキスト------------------------------

		text_need = new Text("<必要な食材>");
		text_need.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 28 ) );
		text_need.setFill(Color.web("#8B0000"));
		//位置の調整
		text_need.setTranslateY( -125. );

		for(int i=0; i<5; i++) {
			text_ingredients[i] = new Text("・" + quiz.getQuiz_name_i(i));//食材を表示
			text_ingredients[i].setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 25 ) );

		}

		text_need_msg = new Text("必要な食材を確認したら料理スタート！");
		text_need_msg.setFill(Color.web("#8B0000"));
		//位置の調整
		text_need_msg.setTranslateY( 130. );
//-----------------------------スタートボタン-------------------------------
		button_start = new Button("スタート");
		button_start.setPrefWidth(100);
		button_start.setPrefHeight(40);
		button_start.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		button_start.setStyle("-fx-base: #F0E68C");//ボタンに色を付ける
		button_start.setOnMouseClicked(event -> {
				try {
					display_mine();//マインスイーパー画面へ
				} catch (Exception ex) {
				}
		});

//-----------------------------戻るボタン-------------------------------
		button_restart = new Button("戻る");
		button_restart.setPrefWidth(100);
		button_restart.setPrefHeight(40);
		button_restart.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		button_restart.setStyle("-fx-base: #D3D3D3");//ボタンに色を付ける
		button_restart.setOnMouseClicked(event -> {
				try {
					display_start();//スタート画面へ
				} catch (Exception ex) {
				}
		});

//----------------------------配置------------------------------
		HBox button = new HBox();
		button.setAlignment(Pos.CENTER);
		button.setPadding( new Insets(5, 5, 5, 5));
		button.setSpacing(5);
		button.getChildren().addAll(button_start, button_restart);
		//位置の調整
		button.setTranslateY( 165. );

		VBox root = new VBox();
		root.setAlignment(Pos.CENTER_LEFT);
		root.setPadding( new Insets(5, 5, 5, 5));
		root.setSpacing(10);
		root.getChildren().addAll(text_ingredients[0], text_ingredients[1], text_ingredients[2], text_ingredients[3], text_ingredients[4]);
		//位置の調整
		root.setTranslateX( 100. );
		root.setTranslateY( 10. );

		StackPane stackPane = new StackPane(imgView_set, imgView_white, text_need, root, text_need_msg, button);
		primaryStage.setScene(new Scene(stackPane));
		primaryStage.showAndWait();


	}



/********************************
 *
 * クイズ画面のメソッド
 *
 * ******************************/
	public void display_quiz(String quiz, String choice1, String choice2, String choice3, int num_answer, int num_event) throws Exception{//問題,選択肢①,選択肢②,選択肢③,答え,イベント番号

		Stage primaryStage2 = new Stage();
	    primaryStage2.initOwner(primaryStage);
	    primaryStage2.initStyle(StageStyle.UNDECORATED);
	    primaryStage2.setWidth(500);
		primaryStage2.setHeight(350);

//-------------------------画像--------------------------
	    //imageの読み込み
		Image image_question  = new Image( "file:" + path + "\\image\\はてな.jpg");
		//imageviewの作成
		ImageView imgView_question = new ImageView( image_question );
		imgView_question.setOpacity(0.4);//半透明にする
		//画像の縮小
		imgView_question.setScaleY( 0.7 );
		imgView_question.setScaleX( 0.7 );


//-------------------------テキスト--------------------------
		//食材名を表示
		text_name = new Text("正解で" + this.quiz.getQuiz_name_i(num_event-1) + "ゲット！");

		//問題を表示
		text_quiz = new Text(quiz);
		text_quiz.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
	    text_quiz.setTextAlignment( TextAlignment.CENTER );
	    text_quiz.setWrappingWidth( 500.0 );

		//選択肢を表示
		text_choice[0] = new Text();
		text_choice[0].setText("１．" + choice1);
		text_choice[0].setWrappingWidth( 500.0 );
		text_choice[1] = new Text();
		text_choice[1].setText("２．" + choice2);
		text_choice[1].setWrappingWidth( 500.0 );
		text_choice[2] = new Text();
		text_choice[2].setText("３．" + choice3);
		text_choice[2].setWrappingWidth( 500.0 );

//------------------------1ボタン	------------------------

		int num = num_answer;
		 button_answer1 = new Button("１");
		 button_answer1.setPrefWidth(70);
		 button_answer1.setPrefHeight(40);
		 button_answer1.setOnMouseClicked (event -> {
			 if(num == 1) {//正解だった場合
				 num_event_incorrect[num_event-1] = 0;//正解だったイベント番号に対応する要素に0を格納する
				 picture(num_event);//ゲットした食材のイラストを表示
			 }else {//不正解だった場合
					 num_event_incorrect[num_event-1] = 1;//不正解だったイベント番号に対応する要素に1を格納する
			 }
			 primaryStage2.close();
		 });

//------------------------2ボタン	------------------------
		 button_answer2 = new Button("２");
		 button_answer2.setPrefWidth(70);
		 button_answer2.setPrefHeight(40);
		 button_answer2.setOnMouseClicked (event -> {
			 if(num == 2) {//正解だった場合
				 num_event_incorrect[num_event-1] = 0;//正解だったイベント番号に対応する要素に0を格納する
				 picture(num_event);//ゲットした食材のイラストを表示
			 }else {//不正解だった場合
				num_event_incorrect[num_event-1] = 1;//不正解だったイベント番号に対応する要素に1を格納する
			 }
			 primaryStage2.close();
		 });


//------------------------3ボタン	------------------------
		 button_answer3 = new Button("３");
		 button_answer3.setPrefWidth(70);
		 button_answer3.setPrefHeight(40);
		 button_answer3.setOnMouseClicked (event -> {
			 if(num == 3) {//正解だった場合
				 num_event_incorrect[num_event-1] = 0;//正解だったイベント番号に対応する要素に0を格納する
				 picture(num_event);//ゲットした食材のイラストを表示
			 }else {//不正解だった場合
				 num_event_incorrect[num_event-1] = 1;//不正解だったイベント番号に対応する要素に1を格納する
			 }
			 primaryStage2.close();
		 });


//--------------------配置--------------------------
		VBox choice = new VBox();
		choice.setAlignment(Pos.TOP_LEFT);
		choice.setPadding( new Insets(5, 5, 5, 5));
		choice.setSpacing(3);
		choice.getChildren().addAll(text_choice[0], text_choice[1], text_choice[2]);
		//配置
		choice.setTranslateX( 150. );
		choice.setTranslateY( 0. );

		HBox answer = new HBox();
		answer.setAlignment(Pos.CENTER);
		answer.setSpacing(5);
		answer.getChildren().addAll(button_answer1, button_answer2, button_answer3);

		VBox box_quiz = new VBox();
		box_quiz.setAlignment(Pos.CENTER);
		box_quiz.setPadding( new Insets(5, 5, 5, 5));
		box_quiz.setSpacing(10);
		box_quiz.getChildren().addAll(text_name,text_quiz, choice, answer);


		StackPane stackPane = new StackPane(imgView_question, box_quiz);

		primaryStage2.setScene(new Scene(stackPane));
		primaryStage2.showAndWait();

	}


/********************************
 *
 * 結果画面のメソッド
 *
 * ******************************/
	public void display_result() throws Exception{

		primaryStage.setTitle("結果画面");
		primaryStage.setWidth(620);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);//フレームの拡大できないように指定

//-------------------------画像--------------------------
		//imageの読み込み
		 Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
		 //imageviewの作成
		 ImageView imgView_set = new ImageView( image_set );
		 imgView_set.setOpacity(0.5);//半透明にする


		//imageの読み込み
		Image image_white = new Image( "file:" + path + "\\image\\白.jpg");
		//imageviewの作成
		ImageView imgView_white = new ImageView( image_white );
		imgView_white.setOpacity(0.5);//半透明にする
		//画像の縮小
		imgView_white.setScaleY( 0.3 );
		imgView_white.setScaleX( 0.37);
		//画像の位置の調整
		imgView_white.setTranslateX( 110. );


		//imageの読み込み
		Image image_blackboard = new Image( "file:" + path + "\\image\\結果発表.png");
		//imageviewの作成
		ImageView imgView_blackboard = new ImageView( image_blackboard );
		//配置
		imgView_blackboard.setTranslateX( -170. );
		imgView_blackboard.setTranslateY( 70. );


//-------------------------テキスト--------------------------
		int i;
		int k = 0;
		int l = 4;
		text_titleGet = new Text("ＧＥＴした食材");
		text_titleGet.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 25 ) );
		text_titleGet.setFill(Color.web("#8B0000"));

		for(i=0; i<5; i++) {
			if(result_icon[i] == '〇') {
				text_get[k] = new Text("・" + quiz.getQuiz_name_i(i));//正解だった場合、その食材名を表示
				text_get[k].setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 17 ) );
				k++;
			}else {
				text_get[l] = new Text("");
				l--;
			}
		}

		for(i=0; i<5; i++) {
			text_result[i] = new Text(quiz.getQuiz_i(i) + ":" + quiz.getQuiz_choice_ij(i, quiz.getQuiz_answer_i(i)-1) + result_icon[i]);//問題と正解を表示
			text_result[i].setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		}

		text_next = new Text("足りない食材を次のステージでGETして料理を完成させよう！");
		text_next.setFill(Color.web("#8B0000"));

//------------------------スタートボタン------------------------
		button_start = new Button("スタート");
		button_start.setPrefWidth(100);
		button_start.setPrefHeight(40);
		button_start.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		button_start.setStyle("-fx-base: #F0E68C");//ボタンに色を付ける
		button_start.setOnMouseClicked(event -> {
			judge_1click = false;//初期化
			 process.initOpen();//open[][]の初期化
			 if(file_menu == "\\カレー.png") quiz.Quiz_reConf(1, num_event_incorrect);//カレー
			 else if(file_menu == "\\麻婆豆腐.png") quiz.Quiz_reConf(2, num_event_incorrect);//麻婆豆腐
			 else quiz.Quiz_reConf(3, num_event_incorrect);//プリン
				try {
					display_mine();//マインスイーパー画面へ
				} catch (Exception ex) {
				}
		});


//------------------------配置------------------------
		VBox box_text = new VBox();
		box_text.setAlignment(Pos.TOP_LEFT);
		box_text.setPadding( new Insets(5, 5, 5, 5));
		box_text.setSpacing(3);
		box_text.getChildren().addAll(text_get[0], text_get[1], text_get[2], text_get[3], text_get[4]);


		VBox box_get = new VBox();
		box_get.setAlignment(Pos.TOP_LEFT);
		box_get.setPadding( new Insets(5, 5, 5, 5));
		box_get.setSpacing(3);
		box_get.getChildren().addAll(text_titleGet, box_text);
		//配置
		box_get.setTranslateX( 325. );
		box_get.setTranslateY( -35. );

		VBox box_result = new VBox();
		box_result.setAlignment(Pos.BOTTOM_LEFT);
		box_result.setPadding( new Insets(5, 5, 5, 5));
		box_result.setSpacing(3);
		box_result.getChildren().addAll(text_result[0], text_result[1], text_result[2], text_result[3],text_result[4]);
		//位置
		box_result.setTranslateX( 90. );
		box_result.setTranslateY( -50. );

		VBox next = new VBox();
		next.setAlignment(Pos.CENTER);
		next.setPadding( new Insets(5, 5, 5, 5));
		next.setSpacing(3);
		next.getChildren().addAll(text_next, button_start);
		//配置
		next.setTranslateX( -40. );

		VBox root_result = new VBox();
		root_result.setAlignment(Pos.CENTER_RIGHT);
		root_result.setPadding( new Insets(5, 5, 5, 5));
		root_result.setSpacing(7);
		root_result.getChildren().addAll(box_result, next);
		//配置
		root_result.setTranslateX( 150. );
		root_result.setTranslateY( -10. );

		VBox root = new VBox();
		root.setAlignment(Pos.BOTTOM_LEFT);
		root.setPadding( new Insets(5, 5, 5, 5));
		root.setSpacing(5);
		root.getChildren().addAll(box_get, root_result);



		StackPane stackPane = new StackPane(imgView_set, imgView_white, imgView_blackboard, root);
		primaryStage.setScene(new Scene(stackPane));
		primaryStage.showAndWait();

	}




/***************************************
 *
 * ゲームクリアの画面のメソッド
 *
 * *************************************/
	public void display_gameClear() throws Exception{

		primaryStage.setTitle("ゲームクリア画面");
		primaryStage.setWidth(400);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);//フレームの拡大できないように指定

//-------------------------画像---------------------------
		//imageの読み込み
		Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
		//imageviewの作成
		ImageView imgView_set = new ImageView( image_set );
		imgView_set.setOpacity(0.5);//半透明にする

		//imageの読み込み
		Image image_white = new Image( "file:" + path + "\\image\\白.jpg");
		//imageviewの作成
		ImageView imgView_white = new ImageView( image_white );
		imgView_white.setOpacity(0.7);//半透明にする
		//画像の縮小
		imgView_white.setScaleY( 0.08 );
		//画像の位置の調整
		imgView_white.setTranslateY( 59. );


		//imageの読み込み
		 Image image_cooking = new Image( "file:" + path + "\\image\\料理.png");
		 //imageviewの作成
		 ImageView imgView_cooking = new ImageView( image_cooking );

//-------------------------テキスト---------------------------
		text_clear = new Text("ゲームクリア");
		text_clear.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 45 ) );
	    text_clear.setTextAlignment( TextAlignment.CENTER );
	    text_clear.setWrappingWidth( 550.0 );
	    //位置
	    text_clear.setTranslateY( -130 );

	    text_success = new Text("料理成功");
		text_success.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 30 ) );
	    text_success.setTextAlignment( TextAlignment.CENTER );
	    text_success.setFill(Color.web("#8B0000"));
	    //位置
	    text_success.setTranslateY( -150 );

	    for(int i=0; i<5; i++) {//結果の振り返り
			text_result[i] = new Text(quiz.getQuiz_i(i) + ":" + quiz.getQuiz_choice_ij(i, quiz.getQuiz_answer_i(i)-1) + result_icon[i]);//問題と正解を表示
			text_result[i].setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		}

	    //result_icon[]の初期化
	    for(int i=0; i<5; i++) {
	    	result_icon[i] = '　';
	    }

	    text_stage_total = new Text(stage_total + "ステージ目で料理成功しました！");
		text_stage_total.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		text_stage_total.setFill(Color.web("#8B0000"));

//--------------------スタート画面へボタン--------------------------
		 button_restart = new Button("スタート画面へ");
		 button_restart.setPrefWidth(120);
		 button_restart.setPrefHeight(50);
		 button_restart.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		 button_restart.setStyle("-fx-base: #F0E68C");//ボタンに色を付ける
		 button_restart.setOnMouseClicked(event -> {
			 judge_1click = false;//初期化
			 process.initOpen();//open[][]の初期化
			try {
				display_start();
			} catch (Exception ex) {
			}
		 });

//--------------------終了ボタン--------------------------
		 button_close = new Button("終了");
		 button_close.setPrefWidth(120);
		 button_close.setPrefHeight(50);
		 button_close.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		 button_close.setStyle("-fx-base: #D3D3D3");//ボタンに色を付ける
		 button_close.setOnMouseClicked(event -> {
					try {
						Platform.exit(); // 終了
					} catch (Exception ex) {
					}
			});

//--------------------配置--------------------------
		 HBox boxButton = new HBox();
		 boxButton.setAlignment(Pos.CENTER);
		 boxButton.setSpacing(5);
		 boxButton.getChildren().addAll(button_restart, button_close);
		 //位置
		 boxButton.setTranslateY( 15 );

		 VBox result = new VBox();
		 result.setAlignment(Pos.CENTER_LEFT);
		 result.setPadding( new Insets(5, 5, 5, 5));
		 result.setSpacing(3);
		 result.getChildren().addAll(text_result[0], text_result[1], text_result[2], text_result[3],text_result[4], text_stage_total);
		 //配置
		 result.setTranslateX( 100 );
		 result.setTranslateY( -8 );

		 VBox clear = new VBox();
		 clear.setAlignment(Pos.BOTTOM_CENTER);
		 clear.setPadding( new Insets(5, 5, 5, 5));
		 clear.setSpacing(10);
		 clear.getChildren().addAll(text_clear, text_success, result,  boxButton);
		 //配置
		 clear.setTranslateX( 0. );
		 clear.setTranslateY( -45. );

		 StackPane stackPane = new StackPane(imgView_set, imgView_cooking, imgView_white, clear);
		 primaryStage.setScene(new Scene(stackPane));
		 primaryStage.showAndWait();

	}


/********************************
 *
 * ゲームオーバー画面のメソッド
 *
 * ******************************/
	public void display_gameOver() throws Exception{

		primaryStage.setTitle("ゲームオーバー画面");
		primaryStage.setWidth(400);
		primaryStage.setHeight(500);
		primaryStage.setResizable(false);//フレームの拡大できないように指定

		//result_icon[]の初期化
	    for(int i=0; i<5; i++) {
	    	result_icon[i] = '　';
	    }


//-------------------------画像--------------------------
		//imageの読み込み
		Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
		//imageviewの作成
		ImageView imgView_set = new ImageView( image_set );
		imgView_set.setOpacity(0.5);//半透明にする


		//imageの読み込み
		Image image_gameover = new Image( "file:" + path + "\\image\\料理失敗.png");
		//imageviewの作成
		ImageView imgView_gameover = new ImageView( image_gameover );

//-------------------------テキスト--------------------------
		text_gameOver = new Text("ゲームオーバー");
		text_gameOver.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 45 ) );
	    text_gameOver.setTextAlignment( TextAlignment.CENTER );
	    text_gameOver.setWrappingWidth( 800.0 );

	    text_failure = new Text("料理失敗");
		text_failure.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 30 ) );
	    text_failure.setTextAlignment( TextAlignment.CENTER );

//--------------------------スタート画面へボタン---------------------------
		button_restart = new Button("スタート画面へ");
		button_restart.setPrefWidth(120);
		button_restart.setPrefHeight(50);
		button_restart.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		button_restart.setStyle("-fx-base: #F0E68C");//ボタンに色を付ける
		button_restart.setOnMouseClicked(event -> {
				judge_1click = false;//初期化
				process.initOpen();//open[][]の初期化
				try {
					display_start();
				} catch (Exception ex) {
				}
		});

//----------------------------終了ボタン----------------------------------
		button_close = new Button("終了");
		button_close.setPrefWidth(120);
		button_close.setPrefHeight(50);
		button_close.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
		button_close.setStyle("-fx-base: #D3D3D3");//ボタンに色を付ける
		button_close.setOnMouseClicked(event -> {
					try {
						Platform.exit(); // 終了
					} catch (Exception ex) {
					}
			});
//-----------------------------配置-------------------------------

		HBox boxButton = new HBox();
		boxButton.setAlignment(Pos.CENTER);
		boxButton.setSpacing(5);
		boxButton.getChildren().addAll(button_restart, button_close);


		VBox button_gameover = new VBox();
		button_gameover.setAlignment(Pos.BOTTOM_CENTER);
		button_gameover.setPadding( new Insets(5, 5, 5, 5));
		button_gameover.setSpacing(10);
		button_gameover.getChildren().addAll(text_gameOver, text_failure, boxButton);
		//配置
		button_gameover.setTranslateX( 0. );
		button_gameover.setTranslateY( -35. );

		StackPane stackPane = new StackPane(imgView_set, imgView_gameover, button_gameover);
		primaryStage.setScene(new Scene(stackPane));
		primaryStage.showAndWait();
	}

	/********************************
	 *
	 * ルール説明画面のメソッド
	 *
	 * ******************************/
		public void display_explanation() throws Exception{

			primaryStage.setTitle("ルール説明画面");
			primaryStage.setWidth(600);
			primaryStage.setHeight(400);
			primaryStage.setResizable(false);//フレームの拡大できないように指定


//-------------------------画像--------------------------
			//imageの読み込み
			Image image_set = new Image( "file:" + path + "\\image\\背景.jpg");
			//imageviewの作成
			ImageView imgView_set = new ImageView( image_set );
			imgView_set.setOpacity(0.5);//半透明にする

			//imageの読み込み
			Image image_fryingPan = new Image( "file:" + path + "\\image\\フライパン.png");
			//imageviewの作成
			ImageView imgView_flyingPan = new ImageView(image_fryingPan);
			//画像の縮小
			imgView_flyingPan.setScaleY( 1.5 );
			imgView_flyingPan.setScaleX( 1.5 );
			//配置
			imgView_flyingPan.setTranslateY( 70. );

//-------------------------テキスト--------------------------
			text_explanation1 = new Text("・マインスイーパーをプレーしながら食材のクイズに答えて\n　食材をGETしよう");
			text_explanation1.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
			text_explanation1.setStyle("-fx-base: #F0E68C");
		    text_explanation1.setTextAlignment( TextAlignment.LEFT );
		    text_explanation1.setWrappingWidth( 800.0 );


		    text_explanation2 = new Text("・５つの食材全てをGETしたら料理成功でゲームクリア");
			text_explanation2.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		    text_explanation2.setTextAlignment( TextAlignment.LEFT );
		    text_explanation2.setWrappingWidth( 800.0 );
		    text_explanation2.setFill(Color.web("#8B0000"));

		    text_explanation3 = new Text("・足りない食材があった場合は料理失敗。次のステージで再挑戦しよう");
			text_explanation3.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		    text_explanation3.setTextAlignment( TextAlignment.LEFT );
		    text_explanation3.setWrappingWidth( 800.0 );

		    text_explanation4 = new Text("　その問題が次のステージで出題されるので料理成功まで繰り返そう");
		    text_explanation4.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		    text_explanation4.setTextAlignment( TextAlignment.LEFT );
		    text_explanation4.setWrappingWidth( 800.0 );
		    text_explanation4.setFill(Color.web("#8B0000"));

		    text_explanation5 = new Text("・爆弾を踏むと料理失敗でゲームオーバー");
			text_explanation5.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		    text_explanation5.setTextAlignment( TextAlignment.LEFT );
		    text_explanation5.setWrappingWidth( 800.0 );
		    text_explanation5.setFill(Color.web("#8B0000"));

		    text_explanation6 = new Text("・爆弾だと思うマスに右クリックで　　　を置いて目印にしよう");
			text_explanation6.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 20 ) );
		    text_explanation6.setTextAlignment( TextAlignment.LEFT );
		    text_explanation6.setWrappingWidth( 800.0 );



//--------------------------戻るボタン---------------------------
			button_restart = new Button("戻る");
			button_restart.setPrefWidth(100);
			button_restart.setPrefHeight(40);
			button_restart.setFont( Font.font( "Meiryo UI" , FontWeight.BOLD  , FontPosture.ITALIC , 12 ) );
			button_restart.setStyle("-fx-base: #D3D3D3");//ボタンに色を付ける
			button_restart.setOnMouseClicked(event -> {
					judge_1click = false;//初期化
					process.initOpen();//open[][]の初期化
					try {
						display_start();
					} catch (Exception ex) {
					}
			});

//-----------------------------配置-------------------------------

			VBox text_explanation = new VBox();
			text_explanation.setAlignment(Pos.CENTER);
			text_explanation.setPadding( new Insets(5, 5, 5, 5));
			text_explanation.setSpacing(10);
			text_explanation.getChildren().addAll(text_explanation1, text_explanation2, text_explanation3, text_explanation4, text_explanation5, text_explanation6);
			//配置
			text_explanation.setTranslateX( 120.);

			VBox explanation = new VBox();
			explanation.setAlignment(Pos.CENTER);
			explanation.setPadding( new Insets(5, 5, 5, 5));
			explanation.setSpacing(10);
			explanation.getChildren().addAll(text_explanation, button_restart);

			StackPane stackPane = new StackPane(imgView_set, imgView_flyingPan, explanation);
			primaryStage.setScene(new Scene(stackPane));
			primaryStage.showAndWait();
		}

/********************************
 *
 * ゲットした食品のイラストを表示するメソッド
 *
 * ******************************/
		public void picture(int num_event) {

			//imageの読み込み
			 Image image_get = new Image( "file:" + path + "\\image\\" + this.quiz.getQuiz_name_i(num_event-1) + ".png");
			 //imageviewの作成
			 ImageView imgView_get = new ImageView(image_get);
			 button_get_picture[num_event-1].setText("");//フラグをボタンに表示
			 button_get_picture[num_event-1].setGraphic(imgView_get);
			 button_get_picture[num_event-1].setOpacity(2.);//半透明にする

		}

}
