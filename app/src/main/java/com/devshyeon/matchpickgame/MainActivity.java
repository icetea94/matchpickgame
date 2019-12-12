package com.devshyeon.matchpickgame;



import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    int no[] = new int[5];
    int drawimg[] = {R.drawable.a_ele,R.drawable.a_frog,R.drawable.a_lion,R.drawable.a_monkey,R.drawable.a_pig};
    int drawresult[] = {R.drawable.b_ele,R.drawable.b_frog,R.drawable.b_lion,R.drawable.b_monkey,R.drawable.b_pig};
    ImageView btn[] = new ImageView[5];
    ImageView start,howto,result;
    int pannum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = findViewById(R.id.startBtn);
        howto = findViewById(R.id.howtoBtn);
        result = findViewById(R.id.qBoard);

        start.setOnClickListener(gamestart);
        howto.setOnClickListener(howtogame);

        for(int i=0; i<btn.length; i++){
            btn[i]= findViewById(R.id.image1+i);
            btn[i].setOnClickListener(gameclear);
        }


    }

    View.OnClickListener gamestart = new View.OnClickListener(){
        public void onClick(android.view.View v) {
            Toast.makeText(MainActivity.this, "게임을 시작합니다!",Toast.LENGTH_SHORT).show();
            randomchangepicture();
        };
    };

    //Random하게 이미지들 위치 변경하기
    public void randomchangepicture(){

        Random random = new Random();

        //중복되지 않는 5개의 랜덤한 숫자(0~4)를 배열에 차례로 저장
        for(int i = 0; i<no.length; i++){
            no[i] = random.nextInt(5);
            for(int j = 0; j<i; j++){
                if(no[i]==no[j]){
                    i--;
                    break;
                }
            }
        }

        //아래 판넬에 표시될 정답번호(0~4) 랜덤하게 생성
        pannum = random.nextInt(5);

        //랜덤한 이미지를 이미지뷰에 설정하고 tag값 부여(정답값 확인위해)
        for(int j =0; j<5; j++){
            btn[j].setImageResource(drawimg[no[j]]);
            btn[j].setTag(no[j]);
        }

        //판넬에 정답 영어 글씨 표시
        result.setImageResource(drawresult[pannum]);
    }

    ImageView.OnClickListener howtogame = new ImageView.OnClickListener(){
        public void onClick(android.view.View v) {


            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("게임설명");
            alert.setMessage("Match Pick이란 게임은 동물이 나오고 하단에 있는 단어와 선택한 동물이 일치하면 승리합니다!");
            alert.setPositiveButton("확인",null );
            alert.show();
        };
    };

    ImageView.OnClickListener gameclear = new ImageView.OnClickListener(){
        public void onClick(android.view.View v) {
            //클릭한 그림의 tag값을 얻어오기
            int n= Integer.parseInt(v.getTag().toString());

            //정답그림을 선택하였는가?
            if(n==pannum){
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("정답!");
                alert.setMessage("정답 입니다. 확인 버튼을 누르세요.");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //다시 랜덤하게 그림들 바꾸기!!!
                        randomchangepicture();
                    }

                });
                alert.show();
            }
            else{
                Toast.makeText(MainActivity.this, "다시 고르세요.",Toast.LENGTH_SHORT).show();
            }
        };
    };


}
