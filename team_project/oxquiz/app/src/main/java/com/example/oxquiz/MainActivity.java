package com.example.oxquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TextView today, content;
    Button refresh, btn1, btn2, btn3, btn4;
    String[] contents;
    String[][] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        loadToday();
    }

    void init() {
        today = findViewById(R.id.today);
        content = findViewById(R.id.content);
        refresh = findViewById(R.id.refresh);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
    }

    void initQuizData() {
        //DB에 저장하는 값은 문제번호, 정답
        contents = new String[]{
                "통화정책에 관련해 '매파'에 대한 설명으로 거리가 먼 것은?",
                "현재 미국 중앙은행(Fed)의 의장을 맡고 있는 사람의 이름은?",
                "정부 지출을 늘리면 그 금액보다 많은 수요를 창출하게 되는 현상을 뜻하는 말은?",
                "지방자치단체의 총수입 중 지방세, 세외수입 등 자체수입 비율을 가리키는 것은?",
                "다음중 3대 소득분배 지표로 볼 수 없는 것을 고르면?", //5
                "코로나19 사태로 위축됐던 경제활동이 재개되는 현상을 가리키는 말은?",
                "볼로디미르 젤렌스키, 키이우, 흐리우냐에서 공통적으로 연상되는 국가는?",
        };
        options = new String[][] {
                {"물가 및 경기 안정 중시", "인플레이션 파이터", "긴축 선호", "완화 선호"}, //완화선호
                {"제롬 파월", "재닛 옐런", "조 바이든", "카멀라 해리스"}, //제롬 파월
                {"기저효과", "낙수효과", "승수효과", "구축효과"}, //승수효과
                {"조세부담율", "재정자립도", "통합재정수지", "관리재정수지"}, //재정자립도
                {"소득 5분위배율", "상대적 빈곤율", "지니계수", "엥겔계수"}, //엥겔계수
                {"리츠", "리파이낸싱", "리오프닝", "리쇼어링"}, //리오프닝
                {"러시아", "우크라이나", "인도", "시리아"}, //우크라이나
        };
    }

    void loadToday() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        today.setText(dateFormat.format(date));
    }

    void setQuizContent(String str) {
        content.setText(str);
    }

    void setBtnText(int idx, String str) {
        switch (idx) {
            case 1:
                btn1.setText("① " + str);
                return;
            case 2:
                btn1.setText("② " + str);
                return;
            case 3:
                btn1.setText("③ " + str);
                return;
            case 4:
                btn1.setText("④ " + str);
                return;
            default:
        }
    }
}