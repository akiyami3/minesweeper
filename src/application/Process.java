package application;

public class Process {

	//メンバ変数
	private int[][] open = new int[7][12];//0:開かれていない状態　1:開かれた状態  3:フラグが立てられた状態
	private int[] event_open = new int[7];//オープンされたイベント番号を格納
	private int l = 0;//event[]の添え字管理


/************************************
 *
 * マスを開くメソッド
 *
 * **********************************/
	public int[][] open_square(int[][] mine, int[][] event, int i, int j) {

		open[i][j] = 1;//オープン
		if(event[i][j] != 0) {//イベントが配置されていた場合
			event_open[l] = event[i][j];
			l++;
		}

		if(mine[i][j] == 0){//現在のマスが0の場合
			if(j != 0 ) {
				//左のマスをチェック
				if(open[i][j-1] == 0 && mine[i][j-1] == 0 && open[i][j-1] != 3) {//	左のマスがまだ開かれていない && 0 && フラグが立っていない場合
					open_square(mine, event, i,j-1);
				}else if(open[i][j-1] != 3){
					if(open[i][j-1] == 0 && event[i][j-1] != 0) {//まだ開かれていない && イベントが配置されていた場合
						event_open[l] = event[i][j-1];
						l++;
					}
					open[i][j-1] = 1;//オープン

				}
				if(i != 0) {
					//左斜め上のマスをチェック
					if(open[i-1][j-1] == 0 && mine[i-1][j-1] == 0 && open[i-1][j-1] != 3) {//左斜め上のマスがまだ開かれていない && 0 && フラグが立っていない場合
						open_square(mine, event, i-1, j-1);
					}else if(open[i-1][j-1] != 3){
						if(open[i-1][j-1] == 0 && event[i-1][j-1] != 0) {//イベントが配置されていた場合
							event_open[l] = event[i-1][j-1];
							l++;
						}
						open[i-1][j-1] = 1;//オープン

					}
				}
				if(i != 6) {
					//左斜め下のマスをチェック
					if(open[i+1][j-1] == 0 && mine[i+1][j-1] == 0 && open[i+1][j-1] != 3) {//左斜め下のマスがまだ開かれていない && 0 && フラグが立っていない場合
						open_square(mine, event, i+1, j-1);
					}else if(open[i+1][j-1] != 3){
						if(open[i+1][j-1] == 0 && event[i+1][j-1] != 0) {//イベントが配置されていた場合
							event_open[l] = event[i+1][j-1];
							l++;
						}
						open[i+1][j-1] = 1;//オープン
						}
				}
			}
			if(j != 11) {
				//右のマスをチェック
				if(open[i][j+1] == 0 && mine[i][j+1] == 0 && open[i][j+1] != 3) {//右のマスがまだ開かれていない && 0 && フラグが立っていない場合
					open_square(mine, event, i, j+1);
				}else if(open[i][j+1] != 3) {
					if(open[i][j+1] == 0 && event[i][j+1] != 0) {//イベントが配置されていた場合
						event_open[l] = event[i][j+1];
						l++;
					}
					open[i][j+1] = 1;//オープン
				}
				if(i != 0) {
					//右斜め上のマスをチェック
					if(open[i-1][j+1] == 0 && mine[i-1][j+1] == 0 && open[i-1][j+1] != 3) {//右斜め上のマスがまだ開かれていない && 0 && フラグが立っていない場合
						open_square(mine, event, i-1, j+1);
					}else if(open[i-1][j+1] != 3){
						if(open[i-1][j+1] == 0 && event[i-1][j+1] != 0) {//イベントが配置されていた場合
							event_open[l] = event[i-1][j+1];
							l++;
						}
						open[i-1][j+1] = 1;//オープン
					}
				}
				if(i != 6) {
					//右斜め下のマスをチェック
					if(open[i+1][j+1] == 0 && mine[i+1][j+1] == 0 && open[i+1][j+1] != 3) {//右斜め下のマスがまだ開かれていない && 0 && フラグが立っていない場合
						open_square(mine, event, i+1, j+1);
					}else if(open[i+1][j+1] != 3){
						if(open[i+1][j+1] == 0 && event[i+1][j+1] != 0) {//イベントが配置されていた場合
							event_open[l] = event[i+1][j+1];
							l++;
						}
						open[i+1][j+1] = 1;//オープン
					}
				}
			}
			if(i != 0) {
				//上のマスをチェック
				if(open[i-1][j] == 0 && mine[i-1][j] == 0 && open[i-1][j] != 3) {//上のマスがまだ開かれていない && 0 && フラグが立っていない場合
					open_square(mine, event, i-1, j);
				}else if(open[i-1][j] != 3){
					if(open[i-1][j] == 0 && event[i-1][j] != 0) {//イベントが配置されていた場合
						event_open[l] = event[i-1][j];
						l++;
					}
					open[i-1][j] = 1;//オープン
				}
			}
			if(i != 6) {
				//下のマスをチェック
				if(open[i+1][j] == 0 && mine[i+1][j] == 0 && open[i+1][j] != 3) {//下のマスがまだ開かれていない && 0 && フラグが立っていない場合
					open_square(mine, event, i+1, j);
				}else if(open[i+1][j] != 3){
					if(open[i+1][j] == 0 && event[i+1][j] != 0) {//イベントが配置されていた場合
						event_open[l] = event[i+1][j];
						l++;
					}
					open[i+1][j] = 1;//オープン
				}
			}
		}
	return open;
	}


/************************************
 *
 * open[][]を初期化するメソッド
 *
 * **********************************/

	public void initOpen() {
		int i, j;
			for(i=0; i<7; i++) {
				for(j=0; j<12; j++) {
					this.open[i][j] = 0;
				}
			}

	}

/************************************
 *
 * open[][]のセッター
 *
 * **********************************/

	public void setOpen(int i, int j, int num) {//i:添え字  j:添え字  num:代入したい値

		this.open[i][j] = num;

	}

/************************************
 *
 * open[][]のゲッター
 *
 * **********************************/

	public int getOpen(int i, int j) {//i:添え字  j:添え字

		return this.open[i][j];

	}

/************************************
 *
 *event_ open[]のゲッター
 *
 * **********************************/
	public int[] getEvent_open() {

		return event_open;

	}


/************************************
 *
 *event_ open[]とその添え字を管理する変数lを初期化するメソッド
 *
 * **********************************/
	public void initEvent_open() {

		//event_open[]の初期化
		int k;
		for( k=0; k<7; k++) {
			event_open[k] = 0;
		}
		l = 0;

	}
}