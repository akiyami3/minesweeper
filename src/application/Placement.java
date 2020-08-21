package application;

import java.util.Random;

public class Placement {

	private int[] reChallenge = new int[5];//不正解だった食材問題を格納し、もう一度出題させる

/**************************************
 *
 * 爆弾を配置するメソッド
 *
 * ************************************/

	public int[][] place_bomb(int a, int b) {

		int[][] mine = new int[7][12];

		int k, i, j;
		boolean judge_num = false;//最初にクリックされたマスの周りのマスだった場合true

		Random rand = new Random();


//------------------------配列の初期化-------------------------------

		for(i=0; i<7; i++) {
			for(j=0; j<12; j++) {
				mine[i][j] = 0;
			}
		}

//------------------------爆弾を配置する-------------------------
		for(k=1; k<11; k++) {
			judge_num = false;//初期化
			i = rand.nextInt(7);
			j = rand.nextInt(12);

			if(b != 0 ) {
				if(i == a && j == (b-1))judge_num = true; //左のマスをチェック
				if(a != 0) {
					if(i == (a-1) && j == (b-1)) judge_num = true;//左斜め上のマスをチェック
				}
				if(a != 6) {
					if(i == (a+1) && j == (b-1)) judge_num = true; //左斜め下のマスをチェック
				}
			}
			if(b != 11) {
				if(i == a && j == (b+1)) judge_num = true; //右のマスをチェック
				if(a != 0) {
					if(i == (a-1) && j == (b+1)) judge_num = true;//右斜め上のマスをチェック
				}
				if(a != 6) {
					if(i == (a+1) && j == (b+1)) judge_num = true; //右斜め下のマスをチェック
				}
			}
			if(a != 0) {
				if(i == (a-1) && j == b) judge_num = true; //上のマスをチェック
			}
			if(a != 6) {
				if(i == (a+1) && j == b) judge_num = true; //下のマスをチェック
			}

			if(!(judge_num)) {
				if(!(a == i && b == j) && mine[i][j] != 9) {//まだ爆弾が置かれていない場合
					mine[i][j] = 9;//爆弾を配置
				}else {
					if(k != 0)k--;//もう1回乱数を生成
				}
			}else {//既に爆弾が置かれていた場合
				if(k != 0)k--;//もう1回乱数を生成
			}
		}

		 mine = place_number(mine);//爆弾と数値を配置済みの二次元解列を返す

		return mine;


	}


/**********************************
 *
 * 数字を配置するメソッド
 *
 * ********************************/

	public int[][] place_number(int[][] mine) {
		int count = 0;//周囲の爆弾の個数をカウント
		for(int i=0; i<7; i++) {
			for(int j=0; j<12; j++) {

				if(mine[i][j] != 9) {//そのマスに爆弾が配置されていない場合
//----------------------------周囲に爆弾があるかの判定---------------------------------
					if(j != 0 ) {
						if(mine[i][j-1] == 9) count++;//左のマスをチェック
						if(i != 0) {
							if(mine[i-1][j-1] == 9) count++;//左斜め上のマスをチェック
						}
						if(i != 6) {
							if(mine[i+1][j-1] == 9) count++;//左斜め下のマスをチェック
						}
					}
					if(j != 11) {
						if(mine[i][j+1] == 9) count++;//右のマスをチェック
						if(i != 0) {
							if(mine[i-1][j+1] == 9) count++;//右斜め上のマスをチェック
						}
						if(i != 6) {
							if(mine[i+1][j+1] == 9) count++;//右斜め下のマスをチェック
						}
					}
					if(i != 0) {
						if(mine[i-1][j] == 9) count++;//上のマスをチェック
					}
					if(i != 6) {
						if(mine[i+1][j] == 9) count++;//下のマスをチェック
					}
					mine[i][j] = count;//カウントした数字を格納
					count = 0;//countの初期化
				}
			}
		}
		return mine;
	}


/**********************************
 *
 * イベントを配置するメソッド
 *
 * ********************************/

	public int[][] place_event(int[][] mine, boolean judge_firstTime){

		int[][] event = new int[7][12];//イベントの配置情報を格納 0:イベント配置されていない　1～7:イベント配置されている
		int i, j;
		int k, l = 1;//reChallenge[]の添え字管理

//------------------------0で初期化-------------------------------
		for(i=0; i<7; i++) {
			for(j=0; j<12; j++) {
				event[i][j] = 0;
			}
		}

//------------------------イベントを配置する-------------------------
		Random rand = new Random();
		for(k=1; k<=5; k++) {//イベントを5個配置
			if(judge_firstTime || reChallenge[l-1] == 1) {//(2回目以降だった場合)食材問題は、不正解だった問題のみを出題
				i = rand.nextInt(7);//0～6
				j = rand.nextInt(12);//0～11

				if(mine[i][j] != 9) {//ランダムで選ばれたマスに爆弾が配置されていない場合
					event[i][j] = k;//イベントを配置
				}else {
					k--;//このターンをノーカウントにする
					l--;//このターンをノーカウントにする
				}
			}
			if(l<5)l++;
		}
		return event;
	}

/**************************************
 *
 * reChallenge[]のセッター
 *
 * ************************************/
	public void setReChallenge(int[] reChallenge, int i) {

		this.reChallenge[i] = reChallenge[i];//メンバ変数への代入

	}


/**************************************
 *
 * reChallenge[]の初期化メソッド
 *
 * ************************************/
		public void initReChallenge() {
			int i;
			//reChallenge[]を初期化
			for(i=0; i<5; i++) {
				reChallenge[i] = 0;
			}
		}
}