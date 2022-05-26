package com.example.oxquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    static int dailyCount, userAnswer, userScore; //일일 횟수(DB에서 불러오기), 유저 답안, 유저 점수
    TextView today, content;
    Button refresh, btn1, btn2, btn3, btn4;
    String[] contents;
    String[][] options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        initQuizData();
        showRandomQuiz();
        loadToday();
        setLayouts();
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
                "통화정책에 관련해 매파에 대한 설명으로 거리가 먼 것은?",
                "현재 미국 중앙은행(Fed)의 의장을 맡고 있는 사람의 이름은?",
                "정부 지출을 늘리면 그 금액보다 많은 수요를 창출하게 되는 현상을 뜻하는 말은?",
                "지방자치단체의 총수입 중 지방세, 세외수입 등 자체수입 비율을 가리키는 것은?",
                "다음중 3대 소득분배 지표로 볼 수 없는 것을 고르면?", //5
                "코로나19 사태로 위축됐던 경제활동이 재개되는 현상을 가리키는 말은?",
                "볼로디미르 젤렌스키, 키이우, 흐리우냐에서 공통적으로 연상되는 국가는?",
                "다음 중 OTT 서비스에 해당하는 것은?",
                "다음 중 절대 100 이상의 숫자가 나올 수 없는 지표는?",
                "술, 담배, 도박, 경마 등 사회에 부정적 영향을 주는 것에 부과하는 세금은?", //10
                "암호화폐 중 비트코인을 제외한 나머지 모든 암호화폐를 통칭하는 말은?",
                "실질성장률에 물가상승률을 더하면 무엇이 될까?",
                "엔젤 투자자는 다음 중 어떤 기업에 투자하는 사람을 가리키는 말일까?",
                "다음 중 비관세장벽과 가장 거리가 먼 조치는 무엇일까?",
                "도로, 항만, 공항, 철도 등의 사회간접자본을 뜻하는 약어는?", //15
                "한국 헌법에 따라 노동자들에게 보장된 노동 3권이 아닌 것은?",
                "곡물가격이 상승하는 영향으로 일반 물가가 오르는 현상을 가리키는 말은?",
                "경기가 나빠지고 있거나 침체에 빠진 상황과 거리가 가장 먼 단어는?",
                "재산이 많은 부자일수록 세율이 높아지는 세금이 아닌 것은?",
                "부동산과 정보기술(IT)을 결합한 서비스산업을 가리키는 말은?", //20
        };
        options = new String[][]{
                {"물가 및 경기 안정 중시", "인플레이션 파이터", "긴축 선호", "완화 선호"}, //완화선호
                {"제롬 파월", "재닛 옐런", "조 바이든", "카멀라 해리스"}, //제롬 파월
                {"기저효과", "낙수효과", "승수효과", "구축효과"}, //승수효과
                {"조세부담율", "재정자립도", "통합재정수지", "관리재정수지"}, //재정자립도
                {"소득 5분위배율", "상대적 빈곤율", "지니계수", "엥겔계수"}, //엥겔계수 //5
                {"리츠", "리파이낸싱", "리오프닝", "리쇼어링"}, //리오프닝
                {"러시아", "우크라이나", "인도", "시리아"}, //우크라이나
                {"카카오톡", "우버", "넷플릭스", " 위워크"}, //넷플릭스
                {"BSI", "CSI", "PER", " 지니계수"}, //PER
                {"누진세", "역진세", "죄악세", " 증여세"}, //죄악세 //10
                {"알트코인", "다크코인", "스테이블코인", " 라이트코인"}, //알트코인
                {"잠재성장률", "명목성장률", "경상성장률", " 적정성장률"}, //명목성장률
                {"다국적기업", "지주회사", "한계기업", " 스타트업"}, //스타트업
                {"탄력관세", "수입할당제", "무역금융제도", " 수출금지"}, //탄력관세
                {"ESG", "MCSI", "ICO", " SOC"}, //SOC //15
                {"단결권", "단체교섭권", "단체행동권", "참정권"}, //참정권
                {"스태그플레이션", "애그플레이션", "피시플레이션", "디플레이션"}, //애그플레이션
                {"스태그플레이션", "R의 공포", "어닝 서프라이즈", "더블 딥"}, //어닝 서프라이즈
                {"종합부동산세", "재산세", "상속세", "부가가치세"}, //부가가치세
                {"빅테크", "핀테크", "프롭테크", "리걸테크"}, //프롭테크
        };
    }

    void setLayouts() {
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRandomQuiz();
            }
        });
    }

    void loadToday() {
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        today.setText(dateFormat.format(date));
    }

    void showRandomQuiz() {
        if (dailyCount > 10) {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("10문제를 모두 풀었습니다!!")
                    .setMessage("오늘 맞힌 횟수: " + userScore)
                    .setPositiveButton("내일 만나요.", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            return;
        }
        Random random = new Random();
        final int quizNum = random.nextInt(20); //몇번째 퀴즈를 낼 것인지?
        setQuizContent(contents[quizNum]);
        setBtnText(1, options[quizNum][0]);
        setBtnText(2, options[quizNum][1]);
        setBtnText(3, options[quizNum][2]);
        setBtnText(4, options[quizNum][3]);
    }

    void setQuizContent(String str) {
        content.setText(str);
    }

    void setBtnText(final int idx, String str) {
        switch (idx) {
            case 1:
                btn1.setText("① " + str);
                btn1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer = idx;
                        dailyCount++;
                        showRandomQuiz();
                    }
                });
                return;
            case 2:
                btn2.setText("② " + str);
                btn2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer = idx;
                        dailyCount++;
                        showRandomQuiz();
                    }
                });
                return;
            case 3:
                btn3.setText("③ " + str);
                btn3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer = idx;
                        dailyCount++;
                        showRandomQuiz();
                    }
                });
                return;
            case 4:
                btn4.setText("④ " + str);
                btn4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        userAnswer = idx;
                        dailyCount++;
                        showRandomQuiz();
                    }
                });
                return;
            default:
        }
    }
}